package edu.pe.unmsm.modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import edu.pe.unmsm.modelo.dao.beans.ResumenBean;

public class ResumenDaoImpl implements ResumenDao {
	private Connection conexion;
	
	public ResumenDaoImpl(Connection conexion) {
		this.conexion = conexion;
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.ResumenDao#listResumenes(java.sql.Date, boolean)
	 */
	@Override
	public List<ResumenBean> listResumenes(Date fecha, boolean fReferencia) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"SELECT * FROM fe.resumenes "+
				"WHERE "+(fReferencia?"fecha_referencia":"fecha")+ "=? ",
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
			 ){
			pst.setDate(1, fecha);
			try(ResultSet rs = pst.executeQuery()){
				List<ResumenBean> resumenes = new ArrayList<>();
				while(rs.next()) {
					ResumenBean resumen = new ResumenBean();
					resumen.setId(rs.getInt(1));
					resumen.setFechaGeneracion(rs.getDate(2));
					resumen.setCorrelativo(rs.getInt(3));
					resumen.setTipo(rs.getString(4));
					resumen.setFechaReferencia(rs.getDate(5));
					resumen.setArchivo(rs.getBlob(6));
					resumen.setNombreArchivo(rs.getString(7));
					resumen.setTicket(rs.getString(8));
					resumen.setArchivoSunat(rs.getBlob(9));
					resumen.setNombreArchivoSunat(rs.getString(10));
					
					resumenes.add(resumen);
				}

				return resumenes;
			}
			
			
		}
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.ResumenDao#listResumenes(java.sql.Date, boolean, java.lang.String)
	 */
	@Override
	public List<ResumenBean> listResumenes(Date fecha, boolean fReferencia, String tipo) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"SELECT * FROM fe.resumenes "+
				"WHERE "+(fReferencia?"fecha_referencia":"fecha")+ "=?  AND tipo =  ?",
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
			 ){
			pst.setDate(1, fecha);
			pst.setString(2, tipo);
			try(ResultSet rs = pst.executeQuery()){
				List<ResumenBean> resumenes = new ArrayList<>();
				while(rs.next()) {
					ResumenBean resumen = new ResumenBean();
					resumen.setId(rs.getInt(1));
					resumen.setFechaGeneracion(rs.getDate(2));
					resumen.setCorrelativo(rs.getInt(3));
					resumen.setTipo(rs.getString(4));
					resumen.setFechaReferencia(rs.getDate(5));
					resumen.setArchivo(rs.getBlob(6));
					resumen.setNombreArchivo(rs.getString(7));
					resumen.setTicket(rs.getString(8));
					resumen.setArchivoSunat(rs.getBlob(9));
					resumen.setNombreArchivoSunat(rs.getString(10));
					
					resumenes.add(resumen);
				}

				return resumenes;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.ResumenDao#getResumenBean(java.lang.String)
	 */
	@Override
	public ResumenBean getResumenBean(String ticket) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"SELECT * FROM fe.resumenes "+
				"WHERE ticket = ?",
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY)){
			pst.setString(1, ticket);
			try(ResultSet rs = pst.executeQuery()){	
				ResumenBean resumen = null;
				if(rs.next()) {
					resumen = new ResumenBean();
					resumen.setId(rs.getInt(1));
					resumen.setFechaGeneracion(rs.getDate(2));
					resumen.setCorrelativo(rs.getInt(3));
					resumen.setTipo(rs.getString(4));
					resumen.setFechaReferencia(rs.getDate(5));
					resumen.setArchivo(rs.getBlob(6));
					resumen.setNombreArchivo(rs.getString(7));
					resumen.setTicket(rs.getString(8));
					resumen.setArchivoSunat(rs.getBlob(9));
					resumen.setNombreArchivoSunat(rs.getString(10));
					
					if(rs.next()) 
						resumen = null;
				}
				return resumen;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.ResumenDao#addResumen(edu.pe.unmsm.modelo.dao.beans.ResumenBean)
	 */
	@Override
	public int addResumen(ResumenBean resumen) throws SQLException{
		
		try(PreparedStatement pst = conexion.prepareStatement(
				"INSERT INTO fe.resumenes "+
				"(fecha,correlativo,tipo,fecha_referencia,archivo,nom_archivo,"+
				"ticket,archivo_sunat,nom_archivo_sunat) "+
				"VALUES (?,?,?,?,?,?,?,?,?) ")){
			
			if(resumen.getFechaGeneracion() == null)
				throw new SQLException("La fecha de generación del resumen no puede ser nula");
			pst.setDate(1, resumen.getFechaGeneracion());
			if(resumen.getCorrelativo() == null)
				throw new SQLException("El correlativo no puede ser nulo");
			pst.setInt(2, resumen.getCorrelativo());
			if(resumen.getTipo() == null)
				throw new SQLException("El tipo de resumen no puede ser nulo");
			pst.setString(3, resumen.getTipo());
			if(resumen.getFechaGeneracion() == null)
				throw new SQLException("La fecha de referencia del resumen no puede ser nula");
			pst.setDate(4, resumen.getFechaReferencia());
			if(resumen.getArchivo() == null)
				throw new SQLException("EL archivo de resumen no puede ser nulo");
			pst.setBlob(5, resumen.getArchivo());

			pst.setString(6, resumen.getNombreArchivo());
			pst.setString(7, resumen.getTicket());
			if(resumen.getArchivoSunat() != null)
				pst.setBlob(8, resumen.getArchivoSunat());
			else
				pst.setNull(8, Types.BLOB);
			pst.setString(9, resumen.getNombreArchivoSunat());
			
			return pst.executeUpdate();
		}
		
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.ResumenDao#updateResumen(edu.pe.unmsm.modelo.dao.beans.ResumenBean)
	 */
	@Override
	public int updateResumen(ResumenBean resumen) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"UPDATE fe.resumenes "+
				"SET fecha = ?,correlativo = ?,tipo = ?,fecha_referencia = ?,"+
				"archivo = ?,nom_archivo = ?,"+
				"ticket = ?,archivo_sunat = ?,nom_archivo_sunat = ?" )){
			
			pst.setDate(1, resumen.getFechaGeneracion());
			pst.setInt(2, resumen.getCorrelativo());
			pst.setString(3, resumen.getTipo());
			pst.setDate(4, resumen.getFechaReferencia());
			if(resumen.getArchivo() != null)
				pst.setBlob(5, resumen.getArchivo());
			else
				pst.setNull(5, Types.BLOB);

			pst.setString(6, resumen.getNombreArchivo());
			pst.setString(7, resumen.getTicket());
			if(resumen.getArchivoSunat() != null)
				pst.setBlob(8, resumen.getArchivoSunat());
			else
				pst.setNull(8, Types.BLOB);
			pst.setString(9, resumen.getNombreArchivoSunat());
			
			return pst.executeUpdate();
		}
	}
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.ResumenDao#instanceResumen()
	 */
	@Override
	public ResumenBean instanceResumen() {
		return new ResumenBean();
	}
	
}
