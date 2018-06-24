package edu.pe.unmsm.modelo.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import edu.pe.unmsm.modelo.dao.beans.DetalleBean;

public interface DetalleDao {

	List<DetalleBean> listDetalle(String transaccion) throws SQLException;

	int addDetalle(DetalleBean detalle) throws SQLException;

	int deleteDetalle(DetalleBean detalle) throws SQLException;

	int deleteDetalle(Date fecha) throws SQLException;

	int deleteDetalle(String transaccion) throws SQLException;
	
	int deleteDetalle(Date fecha, int tipo) throws SQLException;
	
	int deleteDetalle(Date fecha, int tipo, boolean borrarRechazados) throws SQLException;

	int updateDetalle(DetalleBean detalle) throws SQLException;

	DetalleBean instanceDetalle() throws SQLException;

	List<DetalleBean> listDetalle(Date fecha) throws SQLException;

}