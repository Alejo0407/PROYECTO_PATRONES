package edu.pe.unmsm.modelo.xml;

import java.io.File;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public interface XMLDocument{
	public File generarDocumento() throws ParserConfigurationException, TransformerException;
	default String dateAsString(Date fecha) {
		GregorianCalendar date = new GregorianCalendar();
		date.setTime(fecha);
		
		return String.format("%04d-%02d-%02d", date.get(GregorianCalendar.YEAR),
				date.get(GregorianCalendar.MONTH)+1,date.get(GregorianCalendar.DATE));
	}
}