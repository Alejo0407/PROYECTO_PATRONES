package edu.pe.unmsm.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pe.unmsm.modelo.dao.beans.CorrelacionBean;

public class CorrelacionDaoImpl implements CorrelacionDao {

	public Connection conexion;

	public CorrelacionDaoImpl(Connection conexion){
		this.conexion = conexion;
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.CorrelacionDao#getCorrelacion(int)
	 */
	@Override
	public CorrelacionBean getCorrelacion(int tipo) throws SQLException {
		try(PreparedStatement pst = conexion.prepareStatement(
				"SELECT tipo_doc,serie,correlativo "+
				"FROM fe.correlacion "+
				"WHERE tipo_doc = ?",
				ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = pst.executeQuery()){
			
			CorrelacionBean cor = null;
			if(rs.next()) {
				cor = new CorrelacionBean();
				cor.setTipoDocumento(rs.getInt(1));
				cor.setSerie(rs.getString(2));
				cor.setCorrelativo(rs.getInt(3));
			}
			return cor;
		}
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.CorrelacionDao#listCorrelacion()
	 */
	@Override
	public List<CorrelacionBean> listCorrelacion() throws SQLException{
		try(Statement pst = conexion.createStatement(
				ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = pst.executeQuery("SELECT tipo_doc,serie,correlativo "+
					"FROM fe.correlacion")){
			
			List<CorrelacionBean> correlacion = new ArrayList<>();
			while(rs.next()) {
				CorrelacionBean c = new CorrelacionBean();
				c.setTipoDocumento(rs.getInt(1));
				c.setSerie(rs.getString(2));
				c.setCorrelativo(rs.getInt(3));
				correlacion.add(c);
			}
			
			return correlacion;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.CorrelacionDao#updateCorrelacion(edu.pe.unmsm.modelo.dao.beans.CorrelacionBean)
	 */
	@Override
	public int updateCorrelacion(CorrelacionBean cor) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"UPDATE fe.correlacion set serie = ?, correlativo = ? "+
				"WHERE tipo_doc = ? ")){
			
			pst.setString(1, cor.getSerie());
			pst.setInt(2, cor.getCorrelativo());
			pst.setInt(3, cor.getTipoDocumento());
			
			return pst.executeUpdate();
		}
		
	}
}
