package edu.pe.unmsm.modelo.generador;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import edu.pe.unmsm.modelo.dao.ConstanciaRechazoDao;
import edu.pe.unmsm.modelo.dao.CorrelacionDao;
import edu.pe.unmsm.modelo.dao.DetalleDao;
import edu.pe.unmsm.modelo.dao.DocumentoDao;
import edu.pe.unmsm.modelo.dao.EmpresaDao;
import edu.pe.unmsm.modelo.dao.TipoDocumento;
import edu.pe.unmsm.modelo.dao.TipoURL;
import edu.pe.unmsm.modelo.dao.URLDao;
import edu.pe.unmsm.modelo.dao.beans.CorrelacionBean;
import edu.pe.unmsm.modelo.dao.beans.DetalleBean;
import edu.pe.unmsm.modelo.dao.beans.DocumentoBean;
import edu.pe.unmsm.modelo.dao.beans.EmpresaBean;
import edu.pe.unmsm.modelo.dao.beans.SistemaBean;
import edu.pe.unmsm.modelo.dao.beans.URLBean;
import edu.pe.unmsm.modelo.generador.mail.Mensajero;
import edu.pe.unmsm.modelo.generador.mail.MensajeroDocumento;
import edu.pe.unmsm.modelo.generador.xml.XMLFactory;
import edu.pe.unmsm.modelo.utils.Compresor;

public class GeneradorFacturas implements GeneradorDocumentos {
	
	
	public GeneradorFacturas(
			DocumentoDao documentoDao, DetalleDao detalleDao, EmpresaDao empresaDao,
			CorrelacionDao correlacionDao, URLDao urlDao, ConstanciaRechazoDao constancia,
			SistemaBean sistema
	) {
		super();
		this.documentoDao = documentoDao;
		this.detalleDao = detalleDao;
		this.empresaDao = empresaDao;
		this.correlacionDao = correlacionDao;
		this.sistema = sistema;
		this.urlDao = urlDao;
		this.constancia = constancia;
		this.xmlFactory = new XMLFactory();
	}

	private DocumentoDao documentoDao;
	private DetalleDao detalleDao;
	private EmpresaDao empresaDao;
	private CorrelacionDao correlacionDao;
	private URLDao urlDao;
	private ConstanciaRechazoDao constancia;
	
	
	private SistemaBean sistema;
	private XMLFactory xmlFactory;
	
	private SunatState state;
	
	@Override
	public List<DocumentoBean> generar(Date fecha) throws SQLException, NullPointerException,
			ParserConfigurationException, TransformerException,
			SOAPException, IOException, UnsupportedOperationException, SAXException {
		List<DocumentoBean> facturas = 
				documentoDao.listDocumentos(fecha, TipoDocumento.TIPO_FACTURA)
				.stream()
				.filter(f -> f.getHomologado().intValue() == 0)
				.collect(Collectors.toList());
		
		List<DetalleBean> detalles = this.detalleDao.listDetalle(fecha);
		
		EmpresaBean empresa = empresaDao.getEmpresa();
		
		if(empresa.isNull())
			throw new NullPointerException("Los datos de la empresa están vacíos");
		
		URLBean url = urlDao.getActivos().stream()
				.filter(u -> u.getIdTipo() ==TipoURL.ENVIO_DOCUMENTOS_ELECTRONICOS && u.getActivo())
				.findFirst()
				.get();
		Compresor compresor = new Compresor();
		Mensajero mensajero;
		
		
	
		Logger.getGlobal().log(Level.INFO, "INICIANDO GENERACION FACTURAS...");
		
		for(DocumentoBean factura:facturas) {
			Logger.getGlobal().log(Level.INFO, "FACTURA - "+factura.getTransaccion()+"...");
			
			//EXTRAIGO EL DETALLE
			List<DetalleBean> detalleFactura = detalles.stream()
					.filter(d -> d.getTransaccion().equals(factura.getTransaccion()))
					.collect(Collectors.toList());
			
			//OBTENGO LA CORRELACION ACTUAL
			CorrelacionBean correlacion = correlacionDao
					.getCorrelacion(TipoDocumento.TIPO_FACTURA);
			//GENERO EL NOMBRE DEL ARCHIVO
			String nombre = empresa.getRuc()+"-01-F"+correlacion.getSerie()+
					"-"+  String.format("%d", correlacion.getCorrelativo() + 1);
			Logger.getGlobal().log(Level.INFO, "NOMBRE AUX. FACTURA - "+ nombre +"...");
			
			File xml = xmlFactory.getXMLFacturaBoleta(
					factura, detalleFactura, empresa, nombre ,
					correlacion.getSerie(), String.format("%d", correlacion.getCorrelativo()+1), 
					XMLFactory.COD_FACTURA).generarDocumento();
			
			File zip = compresor.comprimir(xml.getName(), nombre + ".zip");
			
			mensajero = new MensajeroDocumento(url.getValor(),
					(url.getLabel().equalsIgnoreCase("beta")?
							sistema.getBetaCode():empresa.getUsuarioSecuandario()),
					(url.getLabel().equalsIgnoreCase("beta")?
							sistema.getBetaCode():empresa.getPassword()),
					empresa.getRuc(),
					zip
			);
			safeSend(mensajero);
			Logger.getGlobal().log(Level.INFO, "DOCUMENTO ENVIADO A LA SUNAT - "+ nombre +"... ");
			
			this.state = getState(mensajero.getEstado(),factura,xml,mensajero,correlacion);
			state.registrar();
			if(mensajero.getEstado() != -1 ) {
				correlacion.aumentarCorrelacion();
				correlacionDao.updateCorrelacion(correlacion);
			}
			
			Logger.getGlobal().log(Level.INFO, "DOCUMENTO GENERADO - "+ nombre +"... ");
			
			mensajero.getResponse().delete();
			xml.delete();
			zip.delete();
			if(mensajero.getNombreRespuesta() != null)
				new File(mensajero.getNombreRespuesta()).delete();
		}
		return facturas;
	}
	

	private void safeSend(Mensajero mensajero) throws UnsupportedOperationException,SOAPException 
		,IOException, TransformerException, SAXException, ParserConfigurationException {
		
		for(int i = 1 ; i < 4 ; i++) {
			Logger.getGlobal().log(Level.INFO, "ENVIO... intento " + i);
			try {
				mensajero.enviar();
				break;
			}
			catch(UnsupportedOperationException | SOAPException 
					| IOException | TransformerException e) {
				Logger.getGlobal().log(Level.WARNING, "ENVIO...  ERRROR" + e.getMessage());
				if(i == 3)
					throw e;
			}
		}
	}
	
	private SunatState getState(int tipo, DocumentoBean documento, 
			File archivo, Mensajero mensajero, CorrelacionBean correlativo) {
		switch(tipo) {
		case 1:
			return new SunatAceptado(correlativo,this.documentoDao, documento, archivo, mensajero);
		case -1:
			return new SunatExcepcion(this.documentoDao, documento, archivo,mensajero);
		case -2:
			return new SunatRechazado(correlativo,this.documentoDao, documento, archivo, mensajero,constancia);
		default:
				return null;
		}
	}
}
