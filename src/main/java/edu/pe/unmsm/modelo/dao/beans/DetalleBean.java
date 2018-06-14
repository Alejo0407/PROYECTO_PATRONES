package edu.pe.unmsm.modelo.dao.beans;

import java.io.Serializable;
import java.sql.Date;

public class DetalleBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5076695033112913643L;

	public DetalleBean(){}

	private String transaccion;
	private String numeroItem;
	private String codigo;
	private String descripcion;
	private String codigoUnidad;
	private Double valorUnitario;
	private Double cantidad;
	private Double igv;
	private String codigoIgv;
	private Double isc;
	private String codigoIsc;
	private Double otrosTributos;
	private Double total;
	private Date fecha;
	
	public void setTransaccion(String transaccion){
		this.transaccion = transaccion;
	}
	public void setNumeroItem(String numeroItem){
		this.numeroItem = numeroItem;
	}
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
	public void setCodigoUnidad(String codigoUnidad){
		this.codigoUnidad = codigoUnidad;
	}
	public void setValorUnitario(Double valorUnitario){
		this.valorUnitario = valorUnitario;
	}
	public void setCantidad(Double cantidad){
		this.cantidad = cantidad;
	}
	public void setIgv(Double igv){
		this.igv = igv;
	}
	public void setCodigoIgv(String codigoIgv){
		this.codigoIgv = codigoIgv;
	}
	public void setIsc(Double isc){
		this.isc = isc;
	}
	public void setCodigoIsc(String codigoIsc){
		this.codigoIsc = codigoIsc;
	}
	public void setOtrosTributos(Double otrosTributos){
		this.otrosTributos = otrosTributos;
	}
	public void setTotal(Double total){
		this.total = total;
	}

	public String getTransaccion(){
		return this.transaccion;
	}
	public String setNumeroItem(){
		return this.numeroItem;
	}
	public String setCodigo(){
		return this.codigo;
	}
	public String setDescripcion(){
		return this.descripcion;
	}
	public String setCodigoUnidad(){
		return this.codigoUnidad;
	}
	public Double setValorUnitario(){
		return this.valorUnitario;
	}
	public Double setCantidad(){
		return this.cantidad;
	}
	public Double getIgv(){
		return this.igv;
	}
	public String getCodigoIgv(){
		return this.codigoIgv;
	}
	public Double getIsc(){
		return this.isc;
	}
	public String getCodigoIsc(){
		return this.codigoIsc;
	}
	public Double getOtrosTributos(){
		return this.otrosTributos;
	}
	public Double getTotal(){
		return this.total;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}