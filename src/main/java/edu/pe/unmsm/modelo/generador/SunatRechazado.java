package edu.pe.unmsm.modelo.generador;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import edu.pe.unmsm.modelo.dao.ConstanciaRechazoDao;
import edu.pe.unmsm.modelo.dao.DocumentoDao;
import edu.pe.unmsm.modelo.dao.TipoDocumento;
import edu.pe.unmsm.modelo.dao.beans.ConstanciaRechazoBean;
import edu.pe.unmsm.modelo.dao.beans.CorrelacionBean;
import edu.pe.unmsm.modelo.dao.beans.DocumentoBean;
import edu.pe.unmsm.modelo.generador.mail.Mensajero;
import edu.pe.unmsm.modelo.utils.Lector;

class SunatRechazado implements SunatState {
	public SunatRechazado(CorrelacionBean correlativo, DocumentoDao documentoDao, DocumentoBean documento, File archivo,
			Mensajero mensajero, ConstanciaRechazoDao constanciaDao) {
		super();
		this.correlativo = correlativo;
		this.documentoDao = documentoDao;
		this.documento = documento;
		this.archivo = archivo;
		this.mensajero = mensajero;
		this.constanciaDao = constanciaDao;
	}
	private CorrelacionBean correlativo;
	private DocumentoDao documentoDao;
	private DocumentoBean documento;
	private File archivo;
	private Mensajero mensajero;
	private ConstanciaRechazoDao constanciaDao;
	
	@Override
	public void registrar() throws SQLException, IOException {
		documento.setHomologado(-2);

		documento.setFechaHomologado(new Date(new java.util.Date().getTime()));
		documento.setSerieElectronica((documento.getTipo() == TipoDocumento.TIPO_FACTURA?"F":"B")
				+correlativo.getSerie());
		documento.setNumeroElectronico(correlativo.getCorrelativo()+1);
		Lector in = new Lector(archivo);
		
		
		documento.setArchivo(new SerialBlob(in.getFileAsByteArray()));
		documento.setNombreArchivo(archivo.getName());
		if(mensajero.getRespuesta() != null) {
			SerialBlob cdr = new SerialBlob(mensajero.getRespuesta());
			documento.setRespuestaSunat(cdr);
			documento.setNombreRespuestaSunat(mensajero.getNombreRespuesta());
			documento.setMensajeSunat(mensajero.getMensaje());
				
			ConstanciaRechazoBean constancia = constanciaDao.instanceConstancia();
			constancia.setNumero(documento.getNumeroOriginal());
			constancia.setNumeroElectronico(documento.getNumeroElectronico());
			constancia.setSerie(documento.getSerieOriginal());
			constancia.setSerieElectronica(documento.getSerieElectronica());
			constancia.setCdr(cdr);
				
			constanciaDao.addConstancia(constancia);
		}
		
		
		documentoDao.updatePartDocumento(documento);
	}

}
