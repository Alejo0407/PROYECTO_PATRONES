package edu.pe.unmsm.modelo.dao.beans;

import java.io.Serializable;

public class UsuarioBean implements Serializable{

	public UsuarioBean(){}

	private String id;
	private String pass;
	private String nombres;
	private String apellidos;
	private String correo;
	private Integer rango;

	public void setId(String id){
		this.id = id;
	}
	public void setPass(String pass){
		this.pass = pass;
	}
	public void setNombres(String nombres){
		this.nombres = nombres;
	}
	public void setApellidos(String apellidos){
		this.apellidos = apellidos;
	}
	public void setCorreo(String correo){
		this.correo = correo;
	}
	public void setRango(Integer rango){
		this.rango = rango;
	}

	public String getId(){
		return this.id;
	}
	public String getPass(){
		return this.pass;
	}
	public String getNombres(){
		return this.nombres;
	}
	public String getApellidos(){
		return this.apellidos;
	}
	public String getCorreo(){
		return this.correo;
	}
	public Integer getRango(){
		return this.rango;
	}
}