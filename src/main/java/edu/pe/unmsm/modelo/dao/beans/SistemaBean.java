package edu.pe.unmsm.modelo.dao.beans;

import java.io.Serializable;

public class SistemaBean implements Serializable{
	public SistemaBean(){}

	private String reporteador;
	private Boolean verificarBoletas;

	public void setReporteador(String reporteador){
		this.reporteador = reporteador;
	}

	public void setVerificarBoletas(Boolean verificarBoletas){
		this.verificarBoletas = verificarBoletas;
	}

	public String getReporteador(){
		return this.reporteador;
	}

	public Boolean getVerificarBoletas(){
		return this.verificarBoletas;
	}
}