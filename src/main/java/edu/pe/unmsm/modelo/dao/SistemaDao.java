package edu.pe.unmsm.modelo.dao;

import java.sql.SQLException;

import edu.pe.unmsm.modelo.dao.beans.SistemaBean;

public interface SistemaDao {

	SistemaBean getSistema() throws SQLException;

	int updateSistema(SistemaBean sistema) throws SQLException;

	int insertSistema(SistemaBean sistema) throws SQLException;

}