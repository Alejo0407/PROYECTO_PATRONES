package edu.pe.unmsm.modelo.dao;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Types;

import edu.pe.unmsm.modelo.dao.beans.UsuarioBean;
import java.util.List;

public class UsuarioDaoImpl{

	public Connection conexion;

	public UsuarioDaoImpl(Connection conexion){
		this.conexion = conexion;
	}

	public List<UsuarioBean> listUsuarios(){
		return null;
	}

}