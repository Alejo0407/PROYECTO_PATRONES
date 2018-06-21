package edu.pe.unmsm.modelo.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pe.unmsm.modelo.dao.beans.URLBean;

public interface URLDao {

	List<URLBean> listUrl() throws SQLException;

	List<URLBean> getActivos() throws SQLException;

	int updateUrl(URLBean url) throws SQLException;

}