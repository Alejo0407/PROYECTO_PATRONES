package edu.pe.unmsm.modelo.dao.beans;

import java.io.Serializable;

public class CorrelacionBean implements Serializable{
	
	public CorrelacionBean(){}

	private Integer tipoDocumento;
	private String serie;
	private Integer correlativo;

	public void setTipoDocumento(Integer tipoDocumento){
		this.tipoDocumento = tipoDocumento;
	}
	public void setSerie(String serie){
		this.serie =  serie;
	}
	public void setCorrelativo(Integer correlativo){
		this.correlativo = correlativo;
	}
	public Integer getTipoDocumento(){
		return this.tipoDocumento;
	}
	public String getSerie(){
		return this.serie;
	}
	public Integer getCorrelativo(){
		return this.correlativo;
	}
}