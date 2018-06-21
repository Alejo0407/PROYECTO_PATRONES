package edu.pe.unmsm.modelo.dao;

import java.sql.SQLException;

import edu.pe.unmsm.modelo.dao.beans.EmpresaBean;

public interface EmpresaDao {

	EmpresaBean getEmpresa() throws SQLException;

	int updateEmpresa(EmpresaBean empresa) throws SQLException;

}