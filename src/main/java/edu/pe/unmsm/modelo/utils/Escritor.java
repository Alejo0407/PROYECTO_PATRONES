package edu.pe.unmsm.modelo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Escritor {
	FileWriter file = null;
	PrintWriter pw = null;
	String nombre;
	
	public Escritor(String nombre, boolean estado) throws IOException
	{
		this.nombre = nombre;
		file = new FileWriter(nombre,estado);
		pw = new PrintWriter(file);
	}
	
	public Escritor() {}
	
	public void escribir(String linea){
		pw.println(linea);
	}
	
	public void escribirSinSalto(String linea){
		pw.print(linea);
	}
	
	public void escribir(ArrayList<String> lineas){
		for(int i = 0; i< lineas.size()-1 ; i++){
			if(!lineas.get(i).trim().isEmpty())
				pw.println(lineas.get(i));
		}
		if(!lineas.get(lineas.size()-1).trim().isEmpty())
			pw.print(lineas.get(lineas.size()-1));
	}
	
	public void copiarArchivo(File f, String destino) throws IOException {
		// TODO Auto-generated method stub
		File folder = new File(destino);
		if(!folder.exists())folder.mkdirs();
		
		File nuevo = new File(destino+f.getName());
		InputStream in = new FileInputStream(f);
		OutputStream out = new FileOutputStream(nuevo);
		
		byte[] buf = new byte[1024];
        int len;

        while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
        }
        
        in.close();
        out.close();
		
	}
	
	public File escribirXML(String nombre, String path, org.w3c.dom.Document doc) throws TransformerException {
		// TODO Auto-generated method stub
		
		File f = new File(path);
		if(!f.exists())f.mkdirs();
		String nombre_xml = nombre;
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(path+nombre_xml+".xml"));
		transformer.transform(source, result);
		
		return new File(path+nombre_xml+".xml");
	}
}
