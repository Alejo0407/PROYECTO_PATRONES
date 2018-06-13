package edu.pe.unmsm.modelo.dao.beans;

import java.io.Serializable;

public class URLBean implements Serializable{
	
	public URLBean(){}

	private Integer id;
	private String tipo;
	private String valor;
	private String label;
	private Boolean activo;

	public void setId(Integer id){
		this.id = id;
	}
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
	public void setValor(String valor){
		this.valor = valor;
	}
	public void setDescripcion(String label){
		this.label = label;
	}
	public void setActivo(Boolean activo){
		this.activo = activo;
	}

	public Integer getId(){
		return this.id;
	}
	public String getTipo(){
		return this.tipo;
	}
	public String getValor(){
		return this.valor;
	}
	public String getLabel(){
		return this.label;
	}
	public Boolean getActivo(){
		return this.activo;
	}
}