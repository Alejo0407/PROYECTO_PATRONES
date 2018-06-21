package edu.pe.unmsm.modelo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;



public class Lector {
	private File file;
	private String mensaje = null;//Mensaje de Homologacion 
	private File zip = null; //Apunta al zip del cdr
	
	private FileInputStream  ref1,ref2;
	
	public Lector() {
		
	}
	
	public Lector(File file) {
		setFile(file);
	}
	public Lector(String file) {
		if(file == null)
			setFile(null);
		else
			setFile(new File(file));
	}

	public byte[] getZipAsByteArray() throws IOException {
		FileInputStream in = new FileInputStream(zip);
		byte[] array = new byte[ (int)zip.length()];
		in.read(array);

		in.close();
		
		return array;
	}
	
	public FileInputStream getZipAsFileInputStream() throws FileNotFoundException {
		ref1 = new FileInputStream(zip);
		return ref1;
	}
	
	public byte[] getFileAsByteArray() throws IOException {
		FileInputStream in = new FileInputStream(file);
		byte[] array = new byte[ (int)file.length()];
		in.read(array);
		in.close();
		return array;
	}
	
	public FileInputStream getFileAsFileInputStream() throws FileNotFoundException {
		ref2 = new FileInputStream(file);
		return ref2;
	}
	
	public byte[] loadFile(File file) throws IOException {
	    FileInputStream is = new FileInputStream(file);

	    long length = file.length();
	    if (length > Integer.MAX_VALUE) {
	        // File is too large
	    }
	    byte[] bytes = new byte[(int)length];
	    
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }

	    if (offset < bytes.length) {
	        extracted(file);
	    }

	    is.close();
	    return bytes;
	}
	
	public String encodeToBase64(String fileName)
			throws IOException {

		File file = new File(fileName);
		byte[] bytes = loadFile(file);
		byte[] encoded = Base64.getEncoder().encode(bytes);
		String encodedString = new String(encoded);

		return encodedString;
	}
	
	private void extracted(File file) throws IOException {
		throw new IOException("Could not completely read file "+file.getName());
	}
	
	public void close() {
		try {this.ref1.close();}catch(Exception e) {}
		try {this.ref2.close();}catch(Exception e) {}
	}
	public void deleteFiles() {
		try {this.zip.delete();}catch(Exception e) {}
		try {this.file.delete();}catch(Exception e) {}
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public File getZip() {
		return zip;
	}

	public void setZip(File zip) {
		this.zip = zip;
	}
}
