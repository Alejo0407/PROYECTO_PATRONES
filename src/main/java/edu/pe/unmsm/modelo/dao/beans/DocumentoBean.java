package edu.pe.unmsm.modelo.dao.beans;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

public class DocumentoBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8993614219633060256L;

	public DocumentoBean(){}

	private String transaccion;
	private String periodo;
	private Integer tipo;
	private String serieOriginal;
	private Integer numeroOriginal;
	private Date fechaEmision;
	private Date fechaVencimiento;
	private String tipoCliente;
	private String numeroCliente;
	private String nombreCliente;
	private String direccion;
	private String provincia;
	private String departamento;
	private String distrito;
	private String email;
	private Double valorVentaAfecta;
	private Double valorVentaInafecta;
	private Double valorVentaExonerada;
	private Double igv;
	private String codigoIgv;
	private Double isc;
	private String codigoIsc;
	private Double otrosTributos;
	private Double total;
	private String serieElectronica;
	private Integer numeroElectronico;
	private Integer homologado;
	private Date fechaHomologado;
	private Blob archivo;
	private String nombreArchivo;
	private Blob respuestaSunat;
	private String nombreRespuestaSunat;
	private String mensajeSunat;
	private Boolean anulado;
	private Integer resumenId;

	public void setTransaccion(String transaccion){
		this.transaccion = transaccion;
	}
	public void setPeriodo(String periodo){
		this.periodo = periodo;
	}
	public void setTipo(Integer tipo){
		this.tipo = tipo;
	}
	public void setSerieOriginal(String serieOriginal){
		this.serieOriginal = serieOriginal;
	}
	public void setNumeroOriginal(Integer numeroOriginal){
		this.numeroOriginal = numeroOriginal;
	}
	public void setFechaEmision(Date fechaEmision){
		this.fechaEmision = fechaEmision;
	}
	public void setFechaVencimiento(Date fechaVencimiento){
		this.fechaVencimiento = fechaVencimiento;
	}
	public void setTipoCliente(String tipoCliente){
		this.tipoCliente = tipoCliente;
	}
	public void setNumeroCliente(String numeroCliente){
		this.numeroCliente = numeroCliente;
	}
	public void setDireccion(String direccion){
		this.direccion = direccion;
	}
	public void setProvincia(String provincia){
		this.provincia = provincia;	
	}
	public void setDepartamento(String departamento){
		this.departamento = departamento;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public void setValorVentaAfecta(Double valorVentaAfecta){
		this.valorVentaAfecta = valorVentaAfecta;
	}
	public void setValorVentaInafecta(Double valorVentaInafecta){
		this.valorVentaInafecta = valorVentaInafecta;
	}
	public void setValorVentaExonerada(Double valorVentaExonerada){
		this.valorVentaExonerada = valorVentaExonerada;
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
	public void setSerieElectronica(String serieElectronica){
		this.serieElectronica = serieElectronica;
	}
	public void setNumeroElectronico(Integer numeroElectronico){
		this.numeroElectronico = numeroElectronico;
	}
	public void setHomologado(Integer homologado){
		this.homologado = homologado;
	}
	public void setFechaHomologado(Date fechaHomologado){
		this.fechaHomologado = fechaHomologado;
	}
	public void setArchivo(Blob archivo){
		this.archivo = archivo;
	}
	public void setNombreArchivo(String nombreArchivo){
		this.nombreArchivo = nombreArchivo;
	}
	public void setRespuestaSunat(Blob respuestaSunat){
		this.respuestaSunat = respuestaSunat;
	}
	public void setNombreRespuestaSunat(String nombreRespuestaSunat){
		this.nombreRespuestaSunat = nombreRespuestaSunat;
	}
	public void setMensajeSunat(String mensajeSunat){
		this.mensajeSunat = mensajeSunat;
	}
	public void setAnulado(Boolean anulado){
		this.anulado = anulado;
	}
	public void setResumenId(Integer resumenId){
		this.resumenId = resumenId;
	}


	public String getTransaccion(){
		return this.transaccion;
	}
	public String getPeriodo(){
		return this.periodo;
	}
	public Integer getTipo(){
		return this.tipo;
	}
	public String getSerieOriginal(){
		return this.serieOriginal;
	}
	public Integer getNumeroOriginal(){
		return this.numeroOriginal;
	}
	public Date getFechaEmision(){
		return this.fechaEmision;
	}
	public Date getFechaVencimiento(){
		return this.fechaVencimiento;
	}
	public String getTipoCliente(){
		return this.tipoCliente;
	}
	public String getNumeroCliente(){
		return this.numeroCliente;
	}
	public String getDireccion(){
		return this.direccion;
	}
	public String getProvincia(){
		return this.provincia;	
	}
	public String getDepartamento(){
		return this.departamento;
	}
	public String getEmail(){
		return this.email;
	}
	public Double getValorVentaAfecta(){
		return this.valorVentaAfecta;
	}
	public Double getValorVentaInafecta(){
		return this.valorVentaInafecta;
	}
	public Double getValorVentaExonerada(){
		return this.valorVentaExonerada;
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
	public String getSerieElectronica(){
		return this.serieElectronica;
	}
	public Integer getNumeroElectronico(){
		return this.numeroElectronico;
	}
	public Integer getHomologado(){
		return this.homologado;
	}
	public Date getFechaHomologado(){
		return this.fechaHomologado;
	}
	public Blob getArchivo(){
		return this.archivo;
	}
	public String getNombreArchivo(){
		return this.nombreArchivo;
	}
	public Blob getRespuestaSunat(){
		return this.respuestaSunat;
	}
	public String getNombreRespuestaSunat(){
		return this.nombreRespuestaSunat;
	}
	public String getMensajeSunat(){
		return this.mensajeSunat;
	}
	public Boolean getAnulado(){
		return this.anulado;
	}
	public Integer getResumenId(){
		return this.resumenId;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	@Override
	public String toString() {
		return "DocumentoBean [transaccion=" + transaccion + ", periodo=" + periodo + ", tipo=" + tipo
				+ ", serieOriginal=" + serieOriginal + ", numeroOriginal=" + numeroOriginal + ", fechaEmision="
				+ fechaEmision + ", fechaVencimiento=" + fechaVencimiento + ", tipoCliente=" + tipoCliente
				+ ", numeroCliente=" + numeroCliente + ", nombreCliente=" + nombreCliente + ", direccion=" + direccion
				+ ", provincia=" + provincia + ", departamento=" + departamento + ", distrito=" + distrito + ", email="
				+ email + ", valorVentaAfecta=" + valorVentaAfecta + ", valorVentaInafecta=" + valorVentaInafecta
				+ ", valorVentaExonerada=" + valorVentaExonerada + ", igv=" + igv + ", codigoIgv=" + codigoIgv
				+ ", isc=" + isc + ", codigoIsc=" + codigoIsc + ", otrosTributos=" + otrosTributos + ", total=" + total
				+ ", serieElectronica=" + serieElectronica + ", numeroElectronico=" + numeroElectronico
				+ ", homologado=" + homologado + ", fechaHomologado=" + fechaHomologado + ", archivo=" + archivo
				+ ", nombreArchivo=" + nombreArchivo + ", respuestaSunat=" + respuestaSunat + ", nombreRespuestaSunat="
				+ nombreRespuestaSunat + ", mensajeSunat=" + mensajeSunat + ", anulado=" + anulado + ", resumenId="
				+ resumenId + "]";
	}
}