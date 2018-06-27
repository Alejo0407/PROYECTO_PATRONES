package edu.pe.unmsm.controlador.beans;

import java.io.Serializable;
import java.util.List;

public class TablaMonitoreoBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> cabeceras;
	private List<String[]> datos;
	private List<String> transacciones;
	
	public List<String> getCabeceras() {
		return cabeceras;
	}
	public void setCabeceras(List<String> cabeceras) {
		this.cabeceras = cabeceras;
	}
	public List<String[]> getDatos() {
		return datos;
	}
	public void setDatos(List<String[]> datos) {
		this.datos = datos;
	}
	public List<String> getTransacciones() {
		return transacciones;
	}
	public void setTransacciones(List<String> transacciones) {
		this.transacciones = transacciones;
	}
	
	
}
