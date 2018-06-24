package edu.pe.unmsm.modelo.generador;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;

import edu.pe.unmsm.modelo.dao.beans.DocumentoBean;

public interface GeneradorDocumentos {
	public List<DocumentoBean> generar(Date fecha) throws SQLException, NullPointerException, 
			ParserConfigurationException, TransformerException,
			SOAPException, IOException, 
			UnsupportedOperationException; 
}
