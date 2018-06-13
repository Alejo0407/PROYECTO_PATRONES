package edu.pe.unmsm.modelo.dao.beans;

import java.io.Serializable;
import java.sql.Blob;

public class EmpresaBean implements Serializable{
	public EmpresaBean(){}

	private String ruc;
	private String nombre;
	private String direccion;
	private String nombreComercial;
	private String ubigeo;
	private String urbanizacion;
	private String distrito;
	private String provincia;
	private String departamento;
	private String telefono;
	private String email;
	private String web;
	private Blob certificado;
	private String nombreCertificado;
	private String pin;
	private String pinRevocar;
	private String alias;
	private String usuarioSecundario;
	private String password;

	public void setRuc(String ruc){
		this.ruc = ruc;	
	}
	public void setNombre(String nombre){
		this.nombre = nombre;	
	}
	public void setDireccion(String direccion){
		this.direccion = direccion;	
	}
	public void setNombreComercial(String nombreComercial){
		this.nombreComercial = nombreComercial;	
	}
	public void setUbigeo(String ubigeo){
		this.ubigeo = ubigeo;	
	}
	public void setUrbanizacion(String urbanizacion){
		this.urbanizacion = urbanizacion;	
	}
	public void setDistrito(String distrito){
		this.distrito = distrito;	
	}
	public void setProvincia(String provincia){
		this.provincia = provincia;	
	}
	public void setDepartamento(String departamento){
		this.departamento = departamento;	
	}
	public void setTelefono(String telefono){
		this.telefono = telefono;	
	}
	public void setEmail(String email){
		this.email = email;	
	}
	public void setWeb(String web){
		this.web = web;	
	}
	public void setCertificado(Blob certificado){
		this.certificado = certificado;
	}
	public void setNombreCertificado(String nombreCertificado){
		this.nombreCertificado = nombreCertificado;	
	}
	public void setPin(String pin){
		this.pin = pin;	
	}
	public void setPinRevocar(String pinRevocar){
		this.pinRevocar = pinRevocar;	
	}
	public void setAlias(String alias){
		this.alias = alias;	
	}
	public void setUsuarioSecuandario(String usuarioSecundario){
		this.usuarioSecundario = usuarioSecundario;	
	}
	public void setPassword(String password){
		this.password = password;	
	}
	
	public String getRuc(){
		return this.ruc;	
	}
	public String getNombre(){
		return this.nombre;	
	}
	public String getDireccion(){
		return this.direccion;	
	}
	public String getNombreComercial(){
		return this.nombreComercial;	
	}
	public String getUbigeo(){
		return this.ubigeo;	
	}
	public String getUrbanizacion(){
		return this.urbanizacion;	
	}
	public String getDistrito(){
		return this.distrito;	
	}
	public String getProvincia(){
		return this.provincia;	
	}
	public String getDepartamento(){
		return this.departamento;	
	}
	public String getTelefono(){
		return this.telefono;	
	}
	public String getEmail(){
		return this.email;	
	}
	public String getWeb(){
		return this.web;	
	}
	public Blob getCertificado(){
		return this.certificado;
	}
	public String getNombreCertificado(){
		return this.nombreCertificado;	
	}
	public String getPin(){
		return this.pin;	
	}
	public String getPinRevocar(){
		return this.pinRevocar;	
	}
	public String getAlias(){
		return this.alias;	
	}
	public String getUsuarioSecuandario(){
		return this.usuarioSecundario;	
	}
	public String getPassword(){
		return this.password;	
	}
}