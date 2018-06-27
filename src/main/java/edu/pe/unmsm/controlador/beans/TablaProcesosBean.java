package edu.pe.unmsm.controlador.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class TablaProcesosBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] headers;
	private List<String> transacciones;
	private Map<String,String[]> content;
	
	public String[] getHeaders() {
		return headers;
	}
	public void setHeaders(String[] headers) {
		this.headers = headers;
	}
	public List<String> getTransacciones() {
		return transacciones;
	}
	public void setTransacciones(List<String> transacciones) {
		this.transacciones = transacciones;
	}
	public Map<String, String[]> getContent() {
		return content;
	}
	public void setContent(Map<String, String[]> content) {
		this.content = content;
	}
	
}
