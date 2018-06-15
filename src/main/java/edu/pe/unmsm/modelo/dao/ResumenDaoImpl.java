package edu.pe.unmsm.modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.pe.unmsm.modelo.dao.beans.ResumenBean;

public class ResumenDaoImpl {
	public static final String RESUMEN_BAJAS = "RA";
	public static final String RESUMEN_DIARIO = "RC";
	
	
	private Connection conexion;
	
	public ResumenDaoImpl(Connection conexion) {
		this.conexion = conexion;
	}
	
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
	
}
