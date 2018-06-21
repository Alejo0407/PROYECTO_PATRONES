package edu.pe.unmsm.modelo.mail;

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
		
		this.setArchivo(response(resp));
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
	
}
