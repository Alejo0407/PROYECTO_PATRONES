package edu.pe.unmsm.modelo.dao;

import java.sql.SQLException;

import edu.pe.unmsm.modelo.dao.beans.ConstanciaRechazoBean;

public interface ConstanciaRechazoDao {

	ConstanciaRechazoBean instanceConstancia();

	int addConstancia(ConstanciaRechazoBean constancia) throws SQLException;

}