package edu.pe.unmsm.modelo.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import edu.pe.unmsm.modelo.dao.beans.ResumenBean;

public interface ResumenDao {

	String RESUMEN_BAJAS = "RA";
	String RESUMEN_DIARIO = "RC";

	List<ResumenBean> listResumenes(Date fecha, boolean fReferencia) throws SQLException;

	List<ResumenBean> listResumenes(Date fecha, boolean fReferencia, String tipo) throws SQLException;

	ResumenBean getResumenBean(String ticket) throws SQLException;

	int addResumen(ResumenBean resumen) throws SQLException;

	int updateResumen(ResumenBean resumen) throws SQLException;

	ResumenBean instanceResumen();

}