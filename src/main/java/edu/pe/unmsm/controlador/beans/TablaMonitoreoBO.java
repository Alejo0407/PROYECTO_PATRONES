package edu.pe.unmsm.controlador.beans;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.NamingException;

import edu.pe.unmsm.modelo.Programa;
import edu.pe.unmsm.modelo.dao.beans.DocumentoBean;

public class TablaMonitoreoBO {
	private List<DocumentoBean> docs;
	
	private TablaMonitoreoBean tabla;
	
	public TablaMonitoreoBO(String date) throws SQLException, NamingException {
		String[] values = date.split("/");
		
		System.out.printf("%s-%s-%s",values[0],values[1],values[2]);
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(GregorianCalendar.DATE, Integer.parseInt(values[1]));
		calendar.set(GregorianCalendar.MONTH, Integer.parseInt(values[0])-1);
		calendar.set(GregorianCalendar.YEAR, Integer.parseInt(values[2]));
		
		Programa p = new Programa();
		this.docs = p.listFacturas(calendar.getTime())
				.stream()
				.filter(x -> x.getHomologado() == 1)
				.collect(Collectors.toList());
		
		docs.addAll(p.listBoletas(calendar.getTime())
				.stream()
				.filter(x -> x.getHomologado() == 1)
				.collect(Collectors.toList()));

		System.out.println(calendar.getTime().toString());
		System.out.println(docs.size());
		p.closeResources();
		
		initTabla();
	}
	
	public TablaMonitoreoBean getTabla() {
		return this.tabla;
	}
	public void initTabla() {
		List<String> s = Arrays.asList("Numeración Electrónica",
				"Cliente","Monto","Numeración Original","Fecha","Estado");
		
		List<String[]> datos = docs.stream()
				.map(p -> this.getAsArray(p))
				.collect(Collectors.toList());
		
		if(docs.isEmpty())
			datos = Arrays.asList();
		
		this.tabla = new TablaMonitoreoBean();
		tabla.setCabeceras(s);
		tabla.setDatos(datos);
	}
	
	private String[] getAsArray(DocumentoBean d) {
		String estado = "";
  		switch (d.getHomologado()){
  		case 1:
  			estado = "Aceptado";
  			break;
  		case -2:
  			estado = "Rechazado";
  			break;
  		case -1:
  			estado = "Error";
  			break;
  		}
  		
		String[] x = new String[6];
		x[0] = d.getSerieElectronica() + "-" + d.getNumeroElectronico();
		x[1] = d.getNombreCliente();
		x[2] = String.format("%.2f", d.getTotal());
		x[3] = d.getSerieOriginal()+"-"+d.getNumeroOriginal();
		x[4] = d.getFechaEmision().toString();
		x[5] = estado;
		
		return x;
	}
}
