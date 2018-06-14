package edu.pe.unmsm.modelo.dao;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Types;

import edu.pe.unmsm.modelo.dao.beans.SistemaBean;
public class SistemaDaoImpl{

	public Connection conexion;

	public SistemaDaoImpl(Connection conexion){
		this.conexion = conexion;
	}

	public SistemaBean getSistema() throws SQLException{
		SistemaBean sistema = null;
		try(Statement st = conexion.createStatement( 
				ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = st.executeQuery(
				"SELECT * FROM fe.sistema")
			){

			sistema = new SistemaBean();
			sistema.setReporteador(rs.getString(1));
			sistema.setVerificarBoletas(rs.getBoolean(2));
		}

		return sistema;
	}

	public int updateSistema(SistemaBean sistema) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
			"UPDATE fe.sistema SET reporter = ?, verificar_boletas = ?",
			ResultSet.TYPE_FORWARD_ONLY,
			ResultSet.CONCUR_UPDATABLE
			)){

			if(sistema.getReporteador() != null)
				pst.setString(1,sistema.getReporteador());
			else
				pst.setNull(1,Types.VARCHAR);

			if(sistema.getVerificarBoletas() != null)
				pst.setBoolean(2,sistema.getVerificarBoletas());
			else
				pst.setNull(2,Types.BOOLEAN);

			return pst.executeUpdate();
		}
	}

	public int insertSistema(SistemaBean sistema) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
			"INSERT INTO fe.sistema (reporter, verificar_boletas) VALUES (?,?) ",
			ResultSet.TYPE_FORWARD_ONLY,
			ResultSet.CONCUR_UPDATABLE
			)){

			if(sistema.getReporteador() != null)
				pst.setString(1,sistema.getReporteador());
			else
				pst.setNull(1,Types.VARCHAR);

			if(sistema.getVerificarBoletas() != null)
				pst.setBoolean(2,sistema.getVerificarBoletas());
			else
				pst.setNull(2,Types.BOOLEAN);

			return pst.executeUpdate();
		}
	}


}