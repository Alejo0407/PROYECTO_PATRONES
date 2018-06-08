package edu.pe.unmsm.controlador.beans;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class MenuBean implements Serializable{

	public MenuBean(){}

	public MenuBean(String id){
		super();
		this.id = id;
		this.cabeceras 	= new ArrayList<>();
		this.contenido 	= new ArrayList<>();
	}

	private String id;
	private List<String> cabeceras;
	private List<String[][]> contenido;


	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
	public List<String> getCabeceras(){
		return this.cabeceras;
	}
	public void setCabeceras (List<String> cabeceras){
		this.cabeceras = cabeceras;
	}
	public List<String[][]> getContenido(){
		return this.contenido;
	}
	public void setContenido(List<String[][]> contenido){
		this.contenido = contenido;
	}
	public void put(String key, String[][] datos){
		this.getCabeceras().add(key);
		this.getContenido().add(datos);
	}
}