package edu.pe.unmsm.modelo.generador;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;

import edu.pe.unmsm.modelo.dao.beans.ResumenBean;

public interface GeneradorResumenes {
	public ResumenBean generar() throws SQLException, ParserConfigurationException, TransformerException, NullPointerException, IOException, UnsupportedOperationException, SOAPException;
}
