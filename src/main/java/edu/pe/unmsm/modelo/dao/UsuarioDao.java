package edu.pe.unmsm.modelo.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pe.unmsm.modelo.dao.beans.UsuarioBean;

public interface UsuarioDao {

	List<UsuarioBean> listUsuarios() throws SQLException;

	UsuarioBean getUsuario(String id) throws SQLException;

	int addUsuario(UsuarioBean usuario) throws SQLException;

	int updateUsuario(UsuarioBean usuario) throws SQLException;

	int deleteUsuario(String id) throws SQLException;

	UsuarioBean instanceUsuario();

}