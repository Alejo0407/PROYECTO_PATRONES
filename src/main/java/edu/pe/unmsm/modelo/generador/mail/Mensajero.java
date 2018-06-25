package edu.pe.unmsm.modelo.generador.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerException;

public abstract class Mensajero {
	private String url;
	private String usuario;
	private String pass;
	private String ruc;
	
	private File archivo;
	
	//DECODED FILES
	
	//PARA LOS DOCUMENTOS y el CDR
	private byte[] respuesta;
	private String nombreRespuesta;
	private String mimeRespuesta;
	private int estado;
	
	//PARA LOS RESUMENES
	private String ticket;
	//GENERICO
	private String mensaje;
	//
	private File response;
	
	public Mensajero(String url, String usuario, String pass, String ruc, File xml) {
		this.url = url;
		this.usuario = usuario;
		this.pass = pass;
		this.ruc = ruc;
		this.archivo = xml;
	}
	
	public void enviar() throws UnsupportedOperationException, SOAPException, IOException, TransformerException {
		SOAPConnectionFactory connFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection conn = connFactory.createConnection();
		SOAPMessage msj = request(ruc,usuario,pass);
		SOAPMessage resp = conn.call(msj, url);
		
		this.setResponse(response(resp));
		decode();
	}
	
	abstract SOAPMessage request(String ruc,String usr,String password) throws SOAPException, IOException;
	abstract File response(SOAPMessage response) throws SOAPException,TransformerException,FileNotFoundException;
	abstract void decode();

	public File getArchivo() {
		return archivo;
	}

	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

	public byte[] getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(byte[] respuesta) {
		this.respuesta = respuesta;
	}

	public String getNombreRespuesta() {
		return nombreRespuesta;
	}

	public void setNombreRespuesta(String nombreRespuesta) {
		this.nombreRespuesta = nombreRespuesta;
	}

	public String getMimeRespuesta() {
		return mimeRespuesta;
	}

	public void setMimeRespuesta(String mimeRespuesta) {
		this.mimeRespuesta = mimeRespuesta;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public File getResponse() {
		return response;
	}

	public void setResponse(File response) {
		this.response = response;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
}
