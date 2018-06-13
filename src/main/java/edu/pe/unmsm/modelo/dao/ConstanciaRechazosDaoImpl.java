package edu.pe.unmsm.modelo.dao;


import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import edu.pe.unmsm.modelo.dao.beans.ConstanciaRechazoBean;

public class ConstanciaRechazoDaoImpl{

	public Connection conexion;

	public SistemaDaoImpl(Connection conexion){
		this.conexion = conexion;
	}

	public ConstanciaRechazoBean instanceConstancia(){
		return new ConstanciaRechazoBean();
	}

	public int addConstancia(ConstanciaRechazoBean constancia) throws SQLException{
		try(PreparedStatement pst = conexion.preparedStatement(
			"INSERT INTO fe.cdr_rechazos VALUES (?,?,?,?,?)",
			ResultSet.TYPE_FORWARD_ONLY,
			ResultSet.CONCUR_UPDATABLE
			)){
			
		}
	}
}