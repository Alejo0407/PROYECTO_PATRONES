package edu.pe.unmsm.modelo.generador.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import edu.pe.unmsm.modelo.utils.Lector;

public class MensajeroResumen extends Mensajero {

	public MensajeroResumen(String url, String usuario, String pass, String ruc, File xml) {
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
		SOAPElement be1 = body.addChildElement("sendSummary", "ser");
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
		
		String nombreArchivo = this.getArchivo().getName();
		if(nombreArchivo.contains(".")) {
			nombreArchivo = nombreArchivo.substring(0,nombreArchivo.indexOf("."));
		}
		
		File resp = new File("response-"+nombreArchivo + ".xml");
		FileOutputStream fout = new FileOutputStream(resp);
		StreamResult rs = new StreamResult(fout);
		
		transformer.transform(sourceContent, rs);
		try {
			fout.close();
		}catch(Exception e) {}
		return resp;
	}

	@Override
	void decode() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();	
		Document doc = dBuilder.parse(this.getResponse());
		
		NodeList nl = doc.getElementsByTagName("ser:sendSummaryResponse");
		if(nl != null)
			nl = doc.getElementsByTagName("br:sendSummaryResponse");
		
		if(nl == null)
			doc.getElementsByTagName("br:sendSummaryResponse");
				
		if(nl.getLength() == 0) {
			String msj = "";
			nl = doc.getElementsByTagName("soap-env:Fault");
			nl = nl.item(0).getChildNodes();
			Node n = nl.item(0);
			msj += ("["+n.getNodeName()+":"+n.getTextContent()+"]-" );
			n = nl.item(1);
			msj += ("["+n.getNodeName()+":"+n.getTextContent()+"]" );
			setMensaje(msj);

			this.setTicket("-1");
		}
		else{
			NodeList t= doc.getElementsByTagName("ticket");
			String ticket = t.item(0).getTextContent();
			this.setTicket(ticket);
		}
	}

}
