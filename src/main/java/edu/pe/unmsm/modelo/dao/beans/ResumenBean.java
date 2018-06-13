package edu.pe.unmsm.modelo.dao.beans;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

public class ResumenBean implements Serializable{

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
}