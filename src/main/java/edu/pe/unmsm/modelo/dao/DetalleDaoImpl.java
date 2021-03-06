package edu.pe.unmsm.modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.pe.unmsm.modelo.dao.beans.DetalleBean;

public class DetalleDaoImpl implements DetalleDao {
	
	private Connection conexion;
	
	public DetalleDaoImpl(Connection conexion) {
		this.conexion = conexion;
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.DetalleDao#listDetalle(java.lang.String)
	 */
	@Override
	public List<DetalleBean> listDetalle(String transaccion) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"SELECT * FROM fe.detdocumentos "+
				"WHERE transaccion = ? ",
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY)){
			pst.setString(1, transaccion);
			try(ResultSet rs = pst.executeQuery()){
				List<DetalleBean> detalles = new ArrayList<>();
				while(rs.next()) {
					DetalleBean detalle = new DetalleBean();
					detalle.setTransaccion(rs.getString(1));
					detalle.setNumeroItem(rs.getString(2));
					detalle.setCodigo(rs.getString(3));
					detalle.setDescripcion(rs.getString(4));
					detalle.setCodigoUnidad(rs.getString(5));
					detalle.setValorUnitario(rs.getDouble(6));
					detalle.setCantidad(rs.getDouble(7));
					detalle.setIgv(rs.getDouble(8));
					detalle.setCodigoIgv(rs.getString(9));
					detalle.setIsc(rs.getDouble(10));
					detalle.setCodigoIsc(rs.getString(11));
					detalle.setOtrosTributos(rs.getDouble(12));
					detalle.setTotal(rs.getDouble(13));
					detalle.setFecha(rs.getDate(14));
					
					detalles.add(detalle);
				}
				return detalles;
			}
		}
	}
	@Override
	public List<DetalleBean> listDetalle(Date fecha) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"SELECT * FROM fe.detdocumentos "+
				"WHERE fecha = ? ",
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY)){
			pst.setDate(1, fecha);
			try(ResultSet rs = pst.executeQuery()){
				List<DetalleBean> detalles = new ArrayList<>();
				while(rs.next()) {
					DetalleBean detalle = new DetalleBean();
					detalle.setTransaccion(rs.getString(1));
					detalle.setNumeroItem(rs.getString(2));
					detalle.setCodigo(rs.getString(3));
					detalle.setDescripcion(rs.getString(4));
					detalle.setCodigoUnidad(rs.getString(5));
					detalle.setValorUnitario(rs.getDouble(6));
					detalle.setCantidad(rs.getDouble(7));
					detalle.setIgv(rs.getDouble(8));
					detalle.setCodigoIgv(rs.getString(9));
					detalle.setIsc(rs.getDouble(10));
					detalle.setCodigoIsc(rs.getString(11));
					detalle.setOtrosTributos(rs.getDouble(12));
					detalle.setTotal(rs.getDouble(13));
					detalle.setFecha(rs.getDate(14));
					
					detalles.add(detalle);
				}
				return detalles;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.DetalleDao#addDetalle(edu.pe.unmsm.modelo.dao.beans.DetalleBean)
	 */
	@Override
	public int addDetalle(DetalleBean detalle) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"INSERT INTO fe.detdocumentos "+
				"(transaccion,sec,codigo,denominacion,unidad,valunitario,cantidad,"+
				"igv,codigv,isc,codisc,otros,valtotal,fecha) "+
				"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ")){
			if(detalle.getTransaccion() == null)
				throw new SQLException("La transaccion en el detalle no puede ser nulo");
			pst.setString(1, detalle.getTransaccion());
			if(detalle.getNumeroItem() == null)
				throw new SQLException("El numero del item no puede ser nulo");
			pst.setString(2, detalle.getNumeroItem());
			pst.setString(3, detalle.getCodigo());
			pst.setString(4, detalle.getDescripcion());
			pst.setString(5, detalle.getCodigoUnidad());
			pst.setDouble(6, detalle.getValorUnitario()!=null?detalle.getValorUnitario():0.00);
			pst.setDouble(7, detalle.getCantidad()!=null?detalle.getCantidad():0.00);
			pst.setDouble(8, detalle.getIgv()!=null?detalle.getIgv():0.00);
			pst.setString(9, detalle.getCodigoIgv());
			pst.setDouble(10, detalle.getIsc()!=null?detalle.getIsc():0.00);
			pst.setString(11, detalle.getCodigoIsc());
			pst.setDouble(12, detalle.getOtrosTributos()!=null?detalle.getOtrosTributos():0.00);
			pst.setDouble(13, detalle.getTotal()!=null?detalle.getTotal():0.00);
			pst.setDate(14, detalle.getFecha());
			return pst.executeUpdate();
		}
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.DetalleDao#deleteDetalle(edu.pe.unmsm.modelo.dao.beans.DetalleBean)
	 */
	@Override
	public int deleteDetalle(DetalleBean detalle) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"DELETE FROM fe.detdocumentos "+
				"WHERE transaccion = ? and sec = ? ")){
			pst.setString(1, detalle.getTransaccion());
			pst.setString(2, detalle.getNumeroItem());
			
			return pst.executeUpdate();
		}
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.DetalleDao#deleteDetalle(java.sql.Date)
	 */
	@Override
	public int deleteDetalle(Date fecha) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"DELETE FROM fe.detdocumentos "+
				"WHERE fecha = ? ")){
			pst.setDate(1, fecha);
			return pst.executeUpdate();
		}
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.DetalleDao#deleteDetalle(java.lang.String)
	 */
	@Override
	public int deleteDetalle(String transaccion) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"DELETE FROM fe.detdocumentos "+
				"WHERE transaccion = ? ")){
			pst.setString(1, transaccion);
			
			return pst.executeUpdate();
		}
	}
	
	public int deleteDetalle(Date fecha, int tipo) throws SQLException {
		try(PreparedStatement pst = conexion.prepareStatement(
				"DELETE FROM fe.detdocumentos "+
				"WHERE transaccion IN (SELECT transaccion FROM fe.cabdocumentos "+
				"WHERE fechaemision = ? and tipodocumento = ? and homologado != 1 ) ")){
			pst.setDate(1, fecha);
			pst.setInt(2, tipo);
			
			return pst.executeUpdate();
		}
	}
	
	public int deleteDetalle(Date fecha, int tipo, boolean borrarRechazados) throws SQLException {
		try(PreparedStatement pst = conexion.prepareStatement(
				"DELETE FROM fe.detdocumentos "+
				"WHERE transaccion IN (SELECT transaccion FROM fe.cabdocumentos "+
				"WHERE fechaemision = ? and tipodocumento = ? and homologado != 1 "+
				(borrarRechazados?"":" AND homologado != -2 ")+") ")){
			
			pst.setDate(1, fecha);
			pst.setInt(2, tipo);
			
			return pst.executeUpdate();
		}
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.DetalleDao#updateDetalle(edu.pe.unmsm.modelo.dao.beans.DetalleBean)
	 */
	@Override
	public int updateDetalle(DetalleBean detalle) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"UPDATE fe.detdocumentos "+
				"SET codigo = ?,denominacion = ?,unidad = ?,valunitario = ?,cantidad = ?," + 
				"igv = ?,codigv = ?,isc = ?,codisc = ?,otros = ?,valtotal = ?,fecha = ? "+
				"WHERE transaccion = ? AND sec = ? ")){
			pst.setString(1, detalle.getCodigo());
			pst.setString(2, detalle.getDescripcion());
			pst.setString(3, detalle.getCodigoUnidad());
			pst.setDouble(4, detalle.getValorUnitario()!=null?detalle.getValorUnitario():0.00);
			pst.setDouble(5, detalle.getCantidad()!=null?detalle.getCantidad():0.00);
			pst.setDouble(6, detalle.getIgv()!=null?detalle.getIgv():0.00);
			pst.setString(7, detalle.getCodigoIgv());
			pst.setDouble(8, detalle.getIsc()!=null?detalle.getIsc():0.00);
			pst.setString(9, detalle.getCodigoIsc());
			pst.setDouble(10, detalle.getOtrosTributos()!=null?detalle.getOtrosTributos():0.00);
			pst.setDouble(11, detalle.getTotal()!=null?detalle.getTotal():0.00);
			pst.setDate(12, detalle.getFecha());
			pst.setString(13, detalle.getTransaccion());
			pst.setString(14, detalle.getNumeroItem());
			return pst.executeUpdate();
		}
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.DetalleDao#instanceDetalle()
	 */
	@Override
	public DetalleBean instanceDetalle() throws SQLException{
		return new DetalleBean();
	}
}
