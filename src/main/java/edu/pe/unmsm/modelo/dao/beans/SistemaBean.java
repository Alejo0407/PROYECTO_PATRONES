package edu.pe.unmsm.modelo.dao.beans;

import java.io.Serializable;

public class SistemaBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8408684403794879264L;

	public SistemaBean(){}

	private String reporteador;
	private Boolean verificarBoletas;
	private String betaCode = "MODDATOS";
	
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

	public String getBetaCode() {
		return betaCode;
	}

	public void setBetaCode(String betaCode) {
		this.betaCode = betaCode;
	}
}