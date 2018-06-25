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

public class GeneradorBoletas implements GeneradorDocumentos {

	public GeneradorBoletas(
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
				documentoDao.listDocumentos(fecha, TipoDocumento.TIPO_BOLETA)
				.stream()
				.filter(f -> f.getHomologado().intValue() == 0)
				.collect(Collectors.toList());
		
		List<DetalleBean> detalles = this.detalleDao.listDetalle(fecha);
		
		EmpresaBean empresa = empresaDao.getEmpresa();
		
		if(empresa.isNull())
			throw new NullPointerException("Los datos de la empresa están vacíos");
		
		
		URLBean url = urlDao.listUrl().stream()
				.filter(u -> u.getIdTipo() ==TipoURL.ENVIO_DOCUMENTOS_ELECTRONICOS 
					&& u.getLabel().equalsIgnoreCase("beta"))
				.findFirst()
				.get();
		
		Mensajero mensajero;
		Compresor compresor = new Compresor();
		
		if(empresa.isNull())
			throw new NullPointerException("Los datos de la empresa están vacíos");
	
		Logger.getGlobal().log(Level.INFO, "INICIANDO GENERACION BOLETAS...");
		
		for(DocumentoBean factura:facturas) {
			Logger.getGlobal().log(Level.INFO, "BOLETA - "+factura.getTransaccion()+"...");
			
			//EXTRAIGO EL DETALLE
			List<DetalleBean> detalleFactura = detalles.stream()
					.filter(d -> d.getTransaccion().equals(factura.getTransaccion()))
					.collect(Collectors.toList());
			
			//OBTENGO LA CORRELACION ACTUAL
			CorrelacionBean correlacion = correlacionDao
					.getCorrelacion(TipoDocumento.TIPO_BOLETA);
			//GENERO EL NOMBRE DEL ARCHIVO
			String nombre = empresa.getRuc()+"-03-B"+correlacion.getSerie()+
					"-"+  String.format("%d", correlacion.getCorrelativo() + 1);
			Logger.getGlobal().log(Level.INFO, "NOMBRE AUX. BOLETA - "+ nombre +"...");
			
			File xml = xmlFactory.getXMLFacturaBoleta(
					factura, detalleFactura, empresa, nombre,
					correlacion.getSerie(), String.format("%d", correlacion.getCorrelativo()+1), 
					XMLFactory.COD_BOLETA).generarDocumento();
			File zip = compresor.comprimir(xml.getName(), nombre + ".zip");
			
			mensajero = new MensajeroDocumento(url.getValor(),
					(url.getLabel().equalsIgnoreCase("beta")?
							sistema.getBetaCode():empresa.getUsuarioSecuandario()),
					(url.getLabel().equalsIgnoreCase("beta")?
							sistema.getBetaCode():empresa.getPassword()),
					empresa.getRuc(),
					zip
			);
			if(sistema.getVerificarBoletas()) {
				
				safeSend(mensajero);

				Logger.getGlobal().log(Level.INFO, "DOCUMENTO ENVIADO A LA SUNAT - "+ nombre +"... ");
			}
			else
				mensajero.setEstado(1);
			
			this.state = getState(mensajero.getEstado(),factura,xml,mensajero,correlacion);
			state.registrar();
			if(mensajero.getEstado() != -1 ) {
				correlacion.aumentarCorrelacion();
				correlacionDao.updateCorrelacion(correlacion);
			}
			
			Logger.getGlobal().log(Level.INFO, "DOCUMENTO GENERADO - "+ nombre +"... ");
			
			//BORRAMOS LOS ARCHIVOS
			
			if(mensajero.getResponse() != null)
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
