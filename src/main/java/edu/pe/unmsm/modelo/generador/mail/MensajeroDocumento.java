package edu.pe.unmsm.modelo.generador.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

import edu.pe.unmsm.modelo.utils.Lector;

public class MensajeroDocumento extends Mensajero {

	public MensajeroDocumento(String url, String usuario, String pass, String ruc, File xml) {
		super(url, usuario, pass, ruc,xml);
	}

	@Override
	SOAPMessage request(String ruc, String usr, String password) throws SOAPException, IOException {
		// TODO Auto-generated method stub
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
		SOAPElement be1 = body.addChildElement("sendBill", "ser");
		SOAPElement filename = be1.addChildElement("fileName");
		filename.addTextNode(getArchivo().getName());
		SOAPElement content = be1.addChildElement("contentFile");
		String cont = new Lector().encodeToBase64(getArchivo().getAbsolutePath());
		content.addTextNode(cont);
	
		//System.out.println("SOAP-REQUEST");
		//message.writeTo(System.out);
		
		return message;
	}

	@Override
	File response(SOAPMessage response) throws SOAPException, TransformerException, FileNotFoundException {
		// TODO Auto-generated method stub
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source sourceContent = response.getSOAPPart().getContent();
		
		File resp = new File("response-"+this.getArchivo().getName());
		StreamResult rs = new StreamResult(new FileOutputStream("response-"+this.getArchivo().getName()));
		transformer.transform(sourceContent, rs);
		return resp;
	}

	@Override
	void decode() {
		
	}

}
