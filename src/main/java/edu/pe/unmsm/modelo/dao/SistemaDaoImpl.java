package edu.pe.unmsm.modelo.dao;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Types;

import edu.pe.unmsm.modelo.dao.beans.SistemaBean;
public class SistemaDaoImpl implements SistemaDao{

	public Connection conexion;

	public SistemaDaoImpl(Connection conexion){
		this.conexion = conexion;
	}

	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.SistemaDao#getSistema()
	 */
	@Override
	public SistemaBean getSistema() throws SQLException{
		SistemaBean sistema = null;
		try(Statement st = conexion.createStatement( 
				ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = st.executeQuery(
				"SELECT * FROM fe.sistema")
			){
			
			if(rs.next()) {
				sistema = new SistemaBean();
				sistema.setReporteador(rs.getString(1));
				sistema.setVerificarBoletas(rs.getBoolean(2));
			}
		}

		return sistema;
	}

	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.SistemaDao#updateSistema(edu.pe.unmsm.modelo.dao.beans.SistemaBean)
	 */
	@Override
	public int updateSistema(SistemaBean sistema) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
			"UPDATE fe.sistema SET reporter = ?, verificar_boletas = ?"
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

	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.SistemaDao#insertSistema(edu.pe.unmsm.modelo.dao.beans.SistemaBean)
	 */
	@Override
	public int insertSistema(SistemaBean sistema) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
			"INSERT INTO fe.sistema (reporter, verificar_boletas) VALUES (?,?) "
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