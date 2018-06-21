package edu.pe.unmsm.modelo.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pe.unmsm.modelo.dao.beans.CorrelacionBean;

public interface CorrelacionDao {

	/**
	 * Usar uno de los parametros de la CLASE Tipo Documento para definir el tipo
	 * @param tipo
	 * @return
	 * @throws SQLException 
	 */
	CorrelacionBean getCorrelacion(int tipo) throws SQLException;

	List<CorrelacionBean> listCorrelacion() throws SQLException;

	int updateCorrelacion(CorrelacionBean cor) throws SQLException;

}