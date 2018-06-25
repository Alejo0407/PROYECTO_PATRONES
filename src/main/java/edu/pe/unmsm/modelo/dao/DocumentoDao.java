package edu.pe.unmsm.modelo.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import edu.pe.unmsm.modelo.dao.beans.DocumentoBean;

public interface DocumentoDao {

	List<DocumentoBean> listDocumentos() throws SQLException;

	List<DocumentoBean> listDocumentos(Date fecha, int tipo) throws SQLException;

	List<DocumentoBean> listDocumentos(Date fechaInicio, Date fechaFin, int tipo) throws SQLException;

	int addDocumento(DocumentoBean documento) throws SQLException;

	int updateAllDocumento(DocumentoBean documento) throws SQLException;

	int updatePartDocumento(DocumentoBean documento) throws SQLException;

	int deleteDocumento(String transaccion) throws SQLException;

	int deleteDocumento(Date fechaEmision) throws SQLException;
	
	int deleteDocumento(Date fechaEmision, int tipo) throws SQLException;
	
	int deleteDocumento(Date fechaEmision, int tipo, boolean borrarRechazados) throws SQLException;

	DocumentoBean instanceDocumento();

	int updateDocumento(String serieElectronica, int numeroElectronico, int homologado, String transaccion)
			throws SQLException;

	int updateDocumento(int idResumen, String transaccion) throws SQLException;

	int anular(String transaccion) throws SQLException;

}