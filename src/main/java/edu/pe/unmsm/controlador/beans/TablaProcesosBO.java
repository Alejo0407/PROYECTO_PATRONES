package edu.pe.unmsm.controlador.beans;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import edu.pe.unmsm.modelo.Programa;
import edu.pe.unmsm.modelo.dao.beans.DocumentoBean;

public class TablaProcesosBO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TablaProcesosBO(boolean facturas, boolean boletas, boolean corregido, String date) {
		this.facturas = facturas;
		this.boletas = boletas;
		this.corregido = corregido;
		
		String[] values = date.split("/");
		
		System.out.printf("%s-%s-%s",values[0],values[1],values[2]);
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(GregorianCalendar.DATE, Integer.parseInt(values[1]));
		calendar.set(GregorianCalendar.MONTH, Integer.parseInt(values[0])-1);
		calendar.set(GregorianCalendar.YEAR, Integer.parseInt(values[2]));
		
		this.date = calendar.getTime();
	}
	
	private boolean facturas;
	private boolean boletas;
	private boolean corregido;
	private Date date;
	
	public TablaProcesosBean getTablaMigraciones() throws SQLException, NamingException, NullPointerException, UnsupportedOperationException, ParserConfigurationException, TransformerException, SOAPException, IOException, SAXException {
		TablaProcesosBean tabla = new TablaProcesosBean(); 
		
		Programa p = new Programa();
		
		List<DocumentoBean> docsMigrados = new ArrayList<>(); 
		
		if(facturas)
			docsMigrados.addAll(p.migrarFacturas(date,corregido));
		
		if(boletas)
			docsMigrados.addAll(p.migrarBoletas(date, corregido));
		
		
		String[] headers = {"Numeracion","Cliente","Monto","Tipo"};
		List<String> transacciones = new ArrayList<>();
		Map<String, String[]> datos = new HashMap<>();
		
		tabla.setHeaders(headers);
		for(DocumentoBean doc:docsMigrados) {
			String[] d = new String[4];
			d[0] = String.format("%s-%d", doc.getSerieOriginal(),doc.getNumeroOriginal());
			d[1] = doc.getNombreCliente();
			d[2] = String.format("%.2f", doc.getTotal());
			d[3] = (doc.getTipo() == 1? "FACTURA" : "BOLETA");
			
			transacciones.add(doc.getTransaccion());
			datos.put(doc.getTransaccion(), d);
		}
		tabla.setTransacciones(transacciones);
		tabla.setContent(datos);
		
		return tabla;
	}
	
	public TablaProcesosBean getTablaGeneracion() throws SQLException, 
		NamingException, NullPointerException, UnsupportedOperationException, 
		ParserConfigurationException, TransformerException, SOAPException, 
		IOException, SAXException {
		
		TablaProcesosBean tabla = new TablaProcesosBean(); 
		
		Programa p = new Programa();
		
		List<DocumentoBean> docsMigrados = new ArrayList<>(); 
		
		if(facturas)
			docsMigrados.addAll(p.generarFacturas(date));
		
		if(boletas)
			docsMigrados.addAll(p.generarBoletas(date));
		
		
		String[] headers = {"Numeracion Electronica","Numeracion Original",
				"Cliente", "Monto","Tipo","Estado"};
		List<String> transacciones = new ArrayList<>();
		Map<String, String[]> datos = new HashMap<>();
		
		tabla.setHeaders(headers);
		for(DocumentoBean doc:docsMigrados) {
			String[] d = new String[6];
			d[0] = String.format("%s-%d", doc.getSerieElectronica(),doc.getNumeroElectronico());
			d[1] = String.format("%s-%d", doc.getSerieOriginal(),doc.getNumeroOriginal());
			d[2] = doc.getNombreCliente();
			d[3] = String.format("%.2f", doc.getTotal());
			d[4] = (doc.getTipo() == 1? "FACTURA" : "BOLETA") + " ELECTRÓNICA";
			d[5] = (doc.getHomologado() == 1 ? "ACEPTADO" : 
				(doc.getHomologado() == -2) ? "RECHAZADO" : "ERROR" );
			
			transacciones.add(doc.getTransaccion());
			datos.put(doc.getTransaccion(), d);
		}
		tabla.setTransacciones(transacciones);
		tabla.setContent(datos);
		
		return tabla;
	}
}
