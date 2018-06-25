package edu.pe.unmsm.modelo.dao.beans;

import java.io.Serializable;
import java.sql.Blob;

public class ConstanciaRechazoBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2085158666294491584L;

	public ConstanciaRechazoBean(){}

	private String serieElectronica;
	private Integer numeroElectronico;
	private String serie;
	private Integer numero;
	private Blob cdr;

	public void setSerieElectronica(String serieElectronica){
		this.serieElectronica = serieElectronica;
	} 
	public void setNumeroElectronico(Integer numeroElectronico){
		this.numeroElectronico = numeroElectronico;
	}
	public void setSerie(String serie){
		this.serie = serie;
	}
	public void setNumero(Integer numero){
		this.numero = numero;
	}
	public void setCdr(Blob cdr){
		this.cdr = cdr;
	}

	public String getSerieElectronica(){
		return this.serieElectronica;
	}
	public Integer getNumeroElectronico(){
		return this.numeroElectronico;
	}
	public String getSerie(){
		return this.serie;
	}
	public Integer getNumero(){
		return this.numero;
	}
	public Blob getCdr(){
		return this.cdr;
	}
	@Override
	public String toString() {
		return "ConstanciaRechazoBean [serieElectronica=" + serieElectronica + ", numeroElectronico="
				+ numeroElectronico + ", serie=" + serie + ", numero=" + numero + ", cdr=" + cdr + "]";
	}
}