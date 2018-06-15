package edu.pe.unmsm.modelo.dao.beans;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

public class ResumenBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5261091661435849408L;

	public ResumenBean(){}

	private Integer id;
	private Date fechaGeneracion;
	private Integer correlativo;
	private String tipo;
	private Date fechaReferencia;
	private Blob archivo;
	private String nombreArchivo;
	private String ticket;
	private Blob archivoSunat;
	private String nombreArchivoSunat;

	public void setId(Integer id){
		this.id = id;
	}
	public void setFechaGeneracion(Date fechaGeneracion){
		this.fechaGeneracion = fechaGeneracion;
	}
	public void setCorrelativo(Integer correlativo){
		this.correlativo = correlativo;
	}
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
	public void setFechaReferencia(Date fechaReferencia){
		this.fechaReferencia = fechaReferencia;
	}
	public void setArchivo(Blob archivo){
		this.archivo = archivo;
	}
	public void setNombreArchivo(String nombreArchivo){
		this.nombreArchivo = nombreArchivo;
	}
	public void setTicket(String ticket){
		this.ticket = ticket;
	}
	public void setArchivoSunat(Blob archivoSunat){
		this.archivoSunat = archivoSunat;
	}
	public void setNombreArchivoSunat(String nombreArchivoSunat){
		this.nombreArchivoSunat = nombreArchivoSunat;
	}
	public Integer getId() {
		return id;
	}
	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}
	public Integer getCorrelativo() {
		return correlativo;
	}
	public String getTipo() {
		return tipo;
	}
	public Date getFechaReferencia() {
		return fechaReferencia;
	}
	public Blob getArchivo() {
		return archivo;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public String getTicket() {
		return ticket;
	}
	public Blob getArchivoSunat() {
		return archivoSunat;
	}
	public String getNombreArchivoSunat() {
		return nombreArchivoSunat;
	}
}