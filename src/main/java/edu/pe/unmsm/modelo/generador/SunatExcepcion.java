package edu.pe.unmsm.modelo.generador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import edu.pe.unmsm.modelo.dao.DocumentoDao;
import edu.pe.unmsm.modelo.dao.beans.DocumentoBean;
import edu.pe.unmsm.modelo.generador.mail.Mensajero;
import edu.pe.unmsm.modelo.utils.Lector;

class SunatExcepcion implements SunatState {
	
	public SunatExcepcion(DocumentoDao documentoDao, DocumentoBean documento, File archivo,
			Mensajero mensajero) {
		super();
		this.documentoDao = documentoDao;
		this.documento = documento;
		this.archivo = archivo;
		this.mensajero = mensajero;
	}
	private DocumentoDao documentoDao;
	private DocumentoBean documento;
	private File archivo;
	private Mensajero mensajero;
	
	@Override
	public void registrar() throws  SQLException, IOException {
		documento.setHomologado(-1);

		documento.setFechaHomologado(new Date(new java.util.Date().getTime()));
		documento.setSerieElectronica(null);
		documento.setNumeroElectronico(null);
		Lector in = new Lector(archivo);
	
		documento.setArchivo(new SerialBlob(in.getFileAsByteArray()));
		documento.setNombreArchivo(archivo.getName());
		
		documento.setRespuestaSunat(new SerialBlob(Files.readAllBytes(mensajero.getResponse().toPath())));
		documento.setNombreRespuestaSunat(mensajero.getResponse().getName());
		documento.setMensajeSunat(mensajero.getMensaje());
		documentoDao.updatePartDocumento(documento);

	}

}
