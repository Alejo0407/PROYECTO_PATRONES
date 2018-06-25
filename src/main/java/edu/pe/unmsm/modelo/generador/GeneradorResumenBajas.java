package edu.pe.unmsm.modelo.generador;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;

import edu.pe.unmsm.modelo.dao.EmpresaDao;
import edu.pe.unmsm.modelo.dao.ResumenDao;
import edu.pe.unmsm.modelo.dao.TipoDocumento;
import edu.pe.unmsm.modelo.dao.TipoURL;
import edu.pe.unmsm.modelo.dao.URLDao;
import edu.pe.unmsm.modelo.dao.beans.DocumentoBean;
import edu.pe.unmsm.modelo.dao.beans.EmpresaBean;
import edu.pe.unmsm.modelo.dao.beans.ResumenBean;
import edu.pe.unmsm.modelo.dao.beans.SistemaBean;
import edu.pe.unmsm.modelo.dao.beans.URLBean;
import edu.pe.unmsm.modelo.generador.mail.Mensajero;
import edu.pe.unmsm.modelo.generador.mail.MensajeroResumen;
import edu.pe.unmsm.modelo.generador.xml.XMLFactory;
import edu.pe.unmsm.modelo.utils.Compresor;
import edu.pe.unmsm.modelo.utils.Lector;

public class GeneradorResumenBajas implements GeneradorResumenes {
	
	public GeneradorResumenBajas(List<DocumentoBean> documentos, List<String> razones, EmpresaDao empresaDao,
			URLDao urlDao, ResumenDao resumenDao, SistemaBean sistema) {
		super();
		this.documentos = documentos;
		this.razones = razones;
		this.empresaDao = empresaDao;
		this.urlDao = urlDao;
		this.resumenDao = resumenDao;
		this.sistema = sistema;
	}

	private List<DocumentoBean> documentos;
	private List<String> razones;
	private EmpresaDao empresaDao;
	private URLDao urlDao;
	private ResumenDao resumenDao;
	private SistemaBean sistema;
	
	@Override
	public ResumenBean generar() throws SQLException, ParserConfigurationException, TransformerException, NullPointerException, IOException, UnsupportedOperationException, SOAPException {
		GregorianCalendar calendar = new GregorianCalendar();
		Date date = new Date(calendar.getTimeInMillis());
		
		EmpresaBean empresa = empresaDao.getEmpresa();
		if(empresa.isNull())
			throw new NullPointerException("Los datos de la empresa están vacíos");
		
		URLBean url = urlDao.getActivos().stream()
				.filter(u -> u.getId() ==TipoURL.ENVIO_DOCUMENTOS_ELECTRONICOS )
				.findFirst()
				.get();
		
		int correlativo = resumenDao.listResumenes(date, false, TipoDocumento.RESUMEN_BAJAS).size() + 1;
		String nombre = empresa.getRuc() + "-RA-"+
				this.dateAsString(date)+"-"+correlativo;
		
		Logger.getGlobal().log(Level.INFO,"INICIANDO GENERACION DE RESUMEN DIARIO...."+nombre);
		Mensajero mensajero;
		
		File xml = new XMLFactory().getXMLResumen(documentos, empresa, razones, 
				nombre + ".xml",
				String.valueOf(correlativo), XMLFactory.COD_RESUMEN_BAJAS)
				.generarDocumento();
		
		File zip = new Compresor().comprimir(xml.getName(), nombre+".zip");
		
		mensajero = new MensajeroResumen(url.getValor(),
				(url.getLabel().equalsIgnoreCase("beta")?
						sistema.getBetaCode():empresa.getUsuarioSecuandario()),
				(url.getLabel().equalsIgnoreCase("beta")?
						sistema.getBetaCode():empresa.getPassword()),
				empresa.getRuc(),
				zip
		);
		
		this.safeSend(mensajero);
		
		ResumenBean res = this.generateResumen(mensajero,xml, correlativo,date, documentos.get(0).getFechaEmision());
		
		this.resumenDao.addResumen(res);
		
		return res;
	}
	
	private ResumenBean generateResumen(Mensajero mensajero, File xml, int correlativo, Date date , Date ref) throws SerialException, SQLException, IOException {
		ResumenBean ret = this.resumenDao.instanceResumen();
		
		Lector in  = new Lector(xml);
		
		ret.setTipo(TipoDocumento.RESUMEN_DIARIO);
		ret.setTicket(mensajero.getTicket());
		ret.setFechaGeneracion(date);
		ret.setFechaReferencia(ref);
		ret.setArchivo(new SerialBlob(in.getFileAsByteArray()));
		ret.setNombreArchivo(xml.getName());
		if(mensajero.getRespuesta() != null) {
			ret.setArchivoSunat(new SerialBlob(mensajero.getRespuesta()));
			ret.setNombreArchivoSunat(mensajero.getNombreRespuesta());
		}
		
		return ret;
	}

	private void safeSend(Mensajero mensajero) throws UnsupportedOperationException,SOAPException 
		,IOException, TransformerException{
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
	
	String dateAsString(Date fecha) {
		GregorianCalendar date = new GregorianCalendar();
		date.setTime(fecha);
		
		return String.format("%04d%02d%02d", date.get(GregorianCalendar.YEAR),
				date.get(GregorianCalendar.MONTH)+1,date.get(GregorianCalendar.DATE));
	}

	public List<DocumentoBean> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<DocumentoBean> documentos) {
		this.documentos = documentos;
	}

	public List<String> getRazones() {
		return razones;
	}

	public void setRazones(List<String> razones) {
		this.razones = razones;
	}

	public EmpresaDao getEmpresaDao() {
		return empresaDao;
	}

	public void setEmpresaDao(EmpresaDao empresaDao) {
		this.empresaDao = empresaDao;
	}

	public URLDao getUrlDao() {
		return urlDao;
	}

	public void setUrlDao(URLDao urlDao) {
		this.urlDao = urlDao;
	}

	public ResumenDao getResumenDao() {
		return resumenDao;
	}

	public void setResumenDao(ResumenDao resumenDao) {
		this.resumenDao = resumenDao;
	}

	public SistemaBean getSistema() {
		return sistema;
	}

	public void setSistema(SistemaBean sistema) {
		this.sistema = sistema;
	}
	
}
