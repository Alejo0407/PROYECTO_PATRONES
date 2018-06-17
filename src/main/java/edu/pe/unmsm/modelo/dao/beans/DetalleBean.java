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

	public String getTransaccion() {
		return transaccion;
	}
	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}
	public String getNumeroItem() {
		return numeroItem;
	}
	public void setNumeroItem(String numeroItem) {
		this.numeroItem = numeroItem;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigoUnidad() {
		return codigoUnidad;
	}
	public void setCodigoUnidad(String codigoUnidad) {
		this.codigoUnidad = codigoUnidad;
	}
	public Double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	public Double getIgv() {
		return igv;
	}
	public void setIgv(Double igv) {
		this.igv = igv;
	}
	public String getCodigoIgv() {
		return codigoIgv;
	}
	public void setCodigoIgv(String codigoIgv) {
		this.codigoIgv = codigoIgv;
	}
	public Double getIsc() {
		return isc;
	}
	public void setIsc(Double isc) {
		this.isc = isc;
	}
	public String getCodigoIsc() {
		return codigoIsc;
	}
	public void setCodigoIsc(String codigoIsc) {
		this.codigoIsc = codigoIsc;
	}
	public Double getOtrosTributos() {
		return otrosTributos;
	}
	public void setOtrosTributos(Double otrosTributos) {
		this.otrosTributos = otrosTributos;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	

}