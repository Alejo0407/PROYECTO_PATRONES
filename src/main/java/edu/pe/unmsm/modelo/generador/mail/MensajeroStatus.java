package edu.pe.unmsm.modelo.generador.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

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

public class MensajeroStatus extends Mensajero {
	
	private String ticket;
	private String nombreResumen;
	public MensajeroStatus(String url, String usuario, String pass,
			String ruc, String ticket ,String nombreResumen) {
		super(url, usuario, pass, ruc,null);
		this.ticket = ticket;
		this.nombreResumen = nombreResumen;
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
		
		String nombreArchivo = this.nombreResumen;
		if(nombreArchivo.contains(".")) {
			nombreArchivo = nombreArchivo.substring(0,nombreArchivo.indexOf("."));
		}
		
		File resp = new File("response-"+nombreArchivo + ".xml");
		StreamResult rs = new StreamResult(new FileOutputStream(resp));
		transformer.transform(sourceContent, rs);
		return resp;
	}

	@Override
	void decode() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();	
		Document doc = dBuilder.parse(this.getResponse());

		NodeList nl = doc.getElementsByTagName("ns2:getStatusResponse");
		if(nl.getLength() == 0) {
			String msj = "";
			nl = doc.getElementsByTagName("soap-env:Fault");
			nl = nl.item(0).getChildNodes();
			Node n = nl.item(0);
			msj += ("["+n.getNodeName()+":"+n.getTextContent()+"]-" );
			n = nl.item(1);
			msj += ("["+n.getNodeName()+":"+n.getTextContent()+"]" );
			setMensaje(msj);
			//System.out.println("MENSAJE ERROR: "+msj);
			setEstado(null);
		}
		else{
			NodeList t= doc.getElementsByTagName("statusCode");
			String val = t.item(0).getTextContent();
			setEstado(Integer.valueOf(val));
			
			t = doc.getElementsByTagName("content");
			String respuesta = t.item(0).getTextContent();
			
			if(getEstado() != 0 && getEstado() != 99) {
				setMensaje(respuesta);
			}
			else {
				byte[] zip = Base64.getDecoder().decode(respuesta.getBytes());
				this.setRespuesta(zip);
				this.setNombreRespuesta("response-"+this.nombreResumen.replace(".xml", ".zip"));
				this.setMimeRespuesta("application/zip");
			}
			
			
		}

	}
	
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

}
