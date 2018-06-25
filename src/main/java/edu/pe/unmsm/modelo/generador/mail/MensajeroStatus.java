package edu.pe.unmsm.modelo.generador.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

public class MensajeroStatus extends Mensajero {
	
	private String ticket;
	public MensajeroStatus(String url, String usuario, String pass,
			String ruc, File xml, String ticket) {
		super(url, usuario, pass, ruc,xml);
		this.ticket = ticket;
	}

	@Override
	SOAPMessage request(String ruc, String usr, String password) throws SOAPException {
		MessageFactory mf = MessageFactory.newInstance();
		SOAPMessage message = mf.createMessage();
		
		message.getSOAPPart().getEnvelope().addNamespaceDeclaration("soap", "http:"
				+ "//schemas.xmlsoap.org/soap/envelope/");
		message.getSOAPPart().getEnvelope().addNamespaceDeclaration("ser", "http://service.sunat.gob.pe");
		message.getSOAPPart().getEnvelope().addNamespaceDeclaration("wsse", "http://docs.oasis-open.org/"
				+ "wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
		
		//CABECERA
		SOAPHeader header = message.getSOAPHeader();
		SOAPElement he1 = header.addChildElement("Security", "wsse");
		SOAPElement he2 = he1.addChildElement("UsernameToken", "wsse");
		SOAPElement user = he2.addChildElement("Username", "wsse");
		SOAPElement pass = he2.addChildElement("Password", "wsse");
		
		
		user.addTextNode(ruc+usr);//RUC+USUARIO
		pass.addTextNode(password);
		
		//CUERPO
		SOAPBody body = message.getSOAPBody();
		SOAPElement be1 = body.addChildElement("getStatus", "ser");
		SOAPElement filename = be1.addChildElement("ticket");
		filename.addTextNode(ticket);
	
		//System.out.println("SOAP-REQUEST");
		//message.writeTo(System.out);
		
		return message;
	}

	@Override
	File response(SOAPMessage response) throws SOAPException, TransformerException, FileNotFoundException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source sourceContent = response.getSOAPPart().getContent();
		
		String nombreArchivo = this.getArchivo().getName();
		if(nombreArchivo.contains(".")) {
			nombreArchivo = nombreArchivo.substring(0,nombreArchivo.indexOf("."));
		}
		
		File resp = new File("response-"+nombreArchivo + ".xml");
		StreamResult rs = new StreamResult(new FileOutputStream(resp));
		transformer.transform(sourceContent, rs);
		return resp;
	}

	@Override
	void decode() {
		// TODO Auto-generated method stub

	}
	
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

}
