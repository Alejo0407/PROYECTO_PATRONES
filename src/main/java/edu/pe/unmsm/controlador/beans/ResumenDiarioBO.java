package edu.pe.unmsm.controlador.beans;

import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.naming.NamingException;

import edu.pe.unmsm.modelo.Programa;
import edu.pe.unmsm.modelo.dao.beans.DocumentoBean;

public class ResumenDiarioBO {
	
	private Date fecha;
	
	public ResumenDiarioBO(String date) {
		
		String[] values = date.split("/");
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(GregorianCalendar.DATE, Integer.parseInt(values[1]));
		calendar.set(GregorianCalendar.MONTH, Integer.parseInt(values[0])-1);
		calendar.set(GregorianCalendar.YEAR, Integer.parseInt(values[2]));
		
		this.fecha = calendar.getTime();
	}
	
	public TablaProcesosBean getDocumentos() throws SQLException, NamingException{
		Programa p = new Programa();
		
		List<DocumentoBean> docs = p.listBoletas(this.fecha)
				.stream()
				.filter(x -> x.getResumenId() == null && x.getHomologado() == 1)
				.collect(Collectors.toList());

		TablaProcesosBean tabla = new TablaProcesosBean();
		String[] headers = {"Numeración Electrónica", "Monto","Cliente"};
		
		List<String> transacciones = docs.stream()
				.map(x -> x.getTransaccion())
				.collect(Collectors.toList());
		
		Map<String , String[]> content = new HashMap<>();
		for(DocumentoBean doc : docs) 
			content.put(doc.getTransaccion(), this.mapToString(doc));
		
		tabla.setHeaders(headers);
		tabla.setTransacciones(transacciones);
		tabla.setContent(content);
		
		return tabla;
	}
	
	private String[] mapToString(DocumentoBean doc) {
		String[] array = {
			String.format("%s-%d", doc.getSerieElectronica(),doc.getNumeroElectronico()),
			String.format("%.2f", doc.getTotal()),
			doc.getNombreCliente()
		};
		
		return array;
	}
	
	public Date getFecha() {
		return this.fecha;
	}
}
