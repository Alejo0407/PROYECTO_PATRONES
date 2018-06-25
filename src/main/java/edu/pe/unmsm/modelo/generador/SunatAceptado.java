package edu.pe.unmsm.modelo.generador;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import edu.pe.unmsm.modelo.dao.DocumentoDao;
import edu.pe.unmsm.modelo.dao.beans.CorrelacionBean;
import edu.pe.unmsm.modelo.dao.beans.DocumentoBean;
import edu.pe.unmsm.modelo.generador.mail.Mensajero;
import edu.pe.unmsm.modelo.utils.Lector;

class SunatAceptado implements SunatState {
	
	public SunatAceptado(CorrelacionBean correlativo, DocumentoDao documentoDao, DocumentoBean documento, File archivo,
			Mensajero mensajero) {
		super();
		this.correlativo = correlativo;
		this.documentoDao = documentoDao;
		this.documento = documento;
		this.archivo = archivo;
		this.mensajero = mensajero;
	}
	private CorrelacionBean correlativo;
	private DocumentoDao documentoDao;
	private DocumentoBean documento;
	private File archivo;
	private Mensajero mensajero;
	@Override
	public void registrar() throws SerialException, SQLException, IOException {
		// TODO Auto-generated method stub
		documento.setHomologado(1);
		documento.setFechaHomologado(new Date(new java.util.Date().getTime()));
		documento.setSerieElectronica(correlativo.getSerie());
		documento.setNumeroElectronico(correlativo.getCorrelativo()+1);
		Lector in = new Lector(archivo);
	
		documento.setArchivo(new SerialBlob(in.getFileAsByteArray()));
		documento.setNombreArchivo(archivo.getName());
		
		if(mensajero.getRespuesta() != null) {
			documento.setRespuestaSunat(new SerialBlob(mensajero.getRespuesta()));
			documento.setNombreRespuestaSunat(mensajero.getNombreRespuesta());
			documento.setMensajeSunat(mensajero.getMensaje());
		}
		documentoDao.updatePartDocumento(documento);
		
	}

}
