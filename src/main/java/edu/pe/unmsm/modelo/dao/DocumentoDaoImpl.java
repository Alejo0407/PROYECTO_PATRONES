package edu.pe.unmsm.modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import edu.pe.unmsm.modelo.dao.beans.DocumentoBean;

public class DocumentoDaoImpl {
	
	private Connection conexion;
	
	public DocumentoDaoImpl(Connection conexion) {
		this.conexion = conexion;
	}
	public List<DocumentoBean> listDocumentos() throws SQLException{
		
		try(PreparedStatement pst = conexion.prepareStatement(
				"SELECT * FROM fe.cabdocumentos ",
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = pst.executeQuery()){
			List<DocumentoBean> documentos = new ArrayList<>();
			while(rs.next()) {
				DocumentoBean documento = new DocumentoBean();
				documento.setTransaccion(rs.getString("transaccion"));
				documento.setPeriodo(rs.getString("periodo"));
				documento.setTipo(rs.getInt("tipodocumento"));
				documento.setSerieOriginal(rs.getString("serie"));
				documento.setNumeroOriginal(rs.getInt("numero"));
				documento.setFechaEmision(rs.getDate("fechaemision"));
				documento.setFechaVencimiento(rs.getDate("fechavencimiento"));
				documento.setTipoCliente(rs.getString("tipocliente"));
				documento.setNumeroCliente(rs.getString("numcliente"));
				documento.setNombreCliente(rs.getString("nomcliente"));
				documento.setDireccion(rs.getString("direccion"));
				documento.setDepartamento(rs.getString("departamento"));
				documento.setProvincia(rs.getString("provincia"));
				documento.setDistrito(rs.getString("distrito"));
				documento.setEmail(rs.getString("email"));
				documento.setValorVentaAfecta(rs.getDouble("valventaafe"));
				documento.setValorVentaInafecta(rs.getDouble("valventaina"));
				documento.setValorVentaExonerada(rs.getDouble("valventaexo"));
				documento.setIsc(rs.getDouble("isc"));
				documento.setCodigoIsc(rs.getString("codisc"));
				documento.setIgv(rs.getDouble("igv"));
				documento.setCodigoIgv(rs.getString("codigv"));
				documento.setOtrosTributos(rs.getDouble("otros"));
				documento.setTotal(rs.getDouble("totaldoc"));
				documento.setSerieElectronica(rs.getString("serieelec"));
				documento.setNumeroElectronico(rs.getInt("numeroelec"));
				documento.setHomologado(rs.getInt("homologado"));
				documento.setFechaHomologado(rs.getDate("fechomologado"));
				documento.setArchivo(rs.getBlob("archivo"));
				documento.setNombreArchivo(rs.getString("nom_archivo"));
				documento.setMensajeSunat(rs.getString("mensaje_homologado"));
				documento.setRespuestaSunat(rs.getBlob("archivo_homologado"));
				documento.setNombreRespuestaSunat(rs.getString("nom_archivo_homologado"));
				documento.setAnulado(rs.getBoolean("anulado"));
				documento.setResumenId(rs.getInt("resumen_id"));
				documentos.add(documento);
			}
			return documentos;
		}
	}

	public List<DocumentoBean> listDocumentos(Date fecha, int tipo) throws SQLException{
		
		try(PreparedStatement pst = conexion.prepareStatement(
				"SELECT * FROM fe.cabdocumentos "+
				"WHERE fechaemision = ? AND tipodocumento = ? ",
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY)){
			
			pst.setDate(1, fecha);
			pst.setInt(2, tipo);
			
			try(ResultSet rs = pst.executeQuery()){
				List<DocumentoBean> documentos = new ArrayList<>();
				while(rs.next()) {
					DocumentoBean documento = new DocumentoBean();
					documento.setTransaccion(rs.getString("transaccion"));
					documento.setPeriodo(rs.getString("periodo"));
					documento.setTipo(rs.getInt("tipodocumento"));
					documento.setSerieOriginal(rs.getString("serie"));
					documento.setNumeroOriginal(rs.getInt("numero"));
					documento.setFechaEmision(rs.getDate("fechaemision"));
					documento.setFechaVencimiento(rs.getDate("fechavencimiento"));
					documento.setTipoCliente(rs.getString("tipocliente"));
					documento.setNumeroCliente(rs.getString("numcliente"));
					documento.setNombreCliente(rs.getString("nomcliente"));
					documento.setDireccion(rs.getString("direccion"));
					documento.setDepartamento(rs.getString("departamento"));
					documento.setProvincia(rs.getString("provincia"));
					documento.setDistrito(rs.getString("distrito"));
					documento.setEmail(rs.getString("email"));
					documento.setValorVentaAfecta(rs.getDouble("valventaafe"));
					documento.setValorVentaInafecta(rs.getDouble("valventaina"));
					documento.setValorVentaExonerada(rs.getDouble("valventaexo"));
					documento.setIsc(rs.getDouble("isc"));
					documento.setCodigoIsc(rs.getString("codisc"));
					documento.setIgv(rs.getDouble("igv"));
					documento.setCodigoIgv(rs.getString("codigv"));
					documento.setOtrosTributos(rs.getDouble("otros"));
					documento.setTotal(rs.getDouble("totaldoc"));
					documento.setSerieElectronica(rs.getString("serieelec"));
					documento.setNumeroElectronico(rs.getInt("numeroelec"));
					documento.setHomologado(rs.getInt("homologado"));
					documento.setFechaHomologado(rs.getDate("fechomologado"));
					documento.setArchivo(rs.getBlob("archivo"));
					documento.setNombreArchivo(rs.getString("nom_archivo"));
					documento.setMensajeSunat(rs.getString("mensaje_homologado"));
					documento.setRespuestaSunat(rs.getBlob("archivo_homologado"));
					documento.setNombreRespuestaSunat(rs.getString("nom_archivo_homologado"));
					documento.setAnulado(rs.getBoolean("anulado"));
					documento.setResumenId(rs.getInt("resumen_id"));
					documentos.add(documento);
				}
				return documentos;
			}
		}
	}
	
	public List<DocumentoBean> listDocumentos(Date fechaInicio, Date fechaFin, int tipo) throws SQLException{
		
		try(PreparedStatement pst = conexion.prepareStatement(
				"SELECT * FROM fe.cabdocumentos "+
				"WHERE ( fechaemision BETWEEN ? AND ? ) AND tipodocumento = ? ",
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY)){
			
			pst.setDate(1, fechaInicio);
			pst.setDate(2, fechaFin);
			pst.setInt(3, tipo);
			
			try(ResultSet rs = pst.executeQuery()){
				List<DocumentoBean> documentos = new ArrayList<>();
				while(rs.next()) {
					DocumentoBean documento = new DocumentoBean();
					documento.setTransaccion(rs.getString("transaccion"));
					documento.setPeriodo(rs.getString("periodo"));
					documento.setTipo(rs.getInt("tipodocumento"));
					documento.setSerieOriginal(rs.getString("serie"));
					documento.setNumeroOriginal(rs.getInt("numero"));
					documento.setFechaEmision(rs.getDate("fechaemision"));
					documento.setFechaVencimiento(rs.getDate("fechavencimiento"));
					documento.setTipoCliente(rs.getString("tipocliente"));
					documento.setNumeroCliente(rs.getString("numcliente"));
					documento.setNombreCliente(rs.getString("nomcliente"));
					documento.setDireccion(rs.getString("direccion"));
					documento.setDepartamento(rs.getString("departamento"));
					documento.setProvincia(rs.getString("provincia"));
					documento.setDistrito(rs.getString("distrito"));
					documento.setEmail(rs.getString("email"));
					documento.setValorVentaAfecta(rs.getDouble("valventaafe"));
					documento.setValorVentaInafecta(rs.getDouble("valventaina"));
					documento.setValorVentaExonerada(rs.getDouble("valventaexo"));
					documento.setIsc(rs.getDouble("isc"));
					documento.setCodigoIsc(rs.getString("codisc"));
					documento.setIgv(rs.getDouble("igv"));
					documento.setCodigoIgv(rs.getString("codigv"));
					documento.setOtrosTributos(rs.getDouble("otros"));
					documento.setTotal(rs.getDouble("totaldoc"));
					documento.setSerieElectronica(rs.getString("serieelec"));
					documento.setNumeroElectronico(rs.getInt("numeroelec"));
					documento.setHomologado(rs.getInt("homologado"));
					documento.setFechaHomologado(rs.getDate("fechomologado"));
					documento.setArchivo(rs.getBlob("archivo"));
					documento.setNombreArchivo(rs.getString("nom_archivo"));
					documento.setMensajeSunat(rs.getString("mensaje_homologado"));
					documento.setRespuestaSunat(rs.getBlob("archivo_homologado"));
					documento.setNombreRespuestaSunat(rs.getString("nom_archivo_homologado"));
					documento.setAnulado(rs.getBoolean("anulado"));
					documento.setResumenId(rs.getInt("resumen_id"));
					documentos.add(documento);
				}
				return documentos;
			}
		}
	}
	
	public int addDocumento(DocumentoBean documento) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"INSERT INTO fe.cabdocumentos "+
				"(transaccion,periodo,tipodocumento,serie,numero,fechaemision,"+
				"fechavencimiento,tipocliente,numcliente,nomcliente,direccion,departamento,"+
				"provincia,distrito,email,valventaafe,valventaina,valventaexo,isc,codisc,"+
				"igv,codigv,otros,totaldoc,serieelec,numeroelec,homologado,fechahomologado,"+
				"archivo,nom_archivo,mensaje_homologado,archivo_homologado,nom_archivo_homologado,"+
				"anulado,resumen_id) "+
				"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ")){
			
			if(documento.getTransaccion() == null)
				throw new SQLException("El campo transacción no puede estar vacío");
			pst.setString(1, documento.getTransaccion());
			pst.setString(2, documento.getPeriodo());
			if(documento.getTipo() == null)
				throw new SQLException("Tipo de documento no puede estar vacío");
			pst.setInt(3, documento.getTipo());
			if(documento.getSerieOriginal() == null)
				throw new SQLException("La serie de referencia no puede estar vacío");
			pst.setString(4, documento.getSerieOriginal());
			if(documento.getNumeroOriginal() == null)
				throw new SQLException("El correlativo original no puede estar vacío");
			pst.setInt(5, documento.getNumeroOriginal());
			if(documento.getFechaEmision() == null)
				throw new SQLException("La fecha de emisión no puede estar vacía");
			pst.setDate(6, documento.getFechaEmision());
			pst.setDate(7, documento.getFechaVencimiento());
			pst.setString(8, documento.getTipoCliente());
			pst.setString(9, documento.getNumeroCliente());
			pst.setString(10, documento.getNombreCliente());
			pst.setString(11, documento.getDireccion());
			pst.setString(12, documento.getDepartamento());
			pst.setString(13, documento.getProvincia());
			pst.setString(14, documento.getDistrito());
			pst.setString(15, documento.getEmail());
			pst.setDouble(16, documento.getValorVentaAfecta() != null?documento.getValorVentaAfecta():0.00);
			pst.setDouble(17, documento.getValorVentaInafecta() != null?documento.getValorVentaInafecta():0.00);
			pst.setDouble(18, documento.getValorVentaExonerada() != null?documento.getValorVentaExonerada():0.00);
			pst.setDouble(19, documento.getIsc() != null?documento.getIsc():0.00);
			pst.setString(20, documento.getCodigoIsc());
			pst.setDouble(21, documento.getIgv() != null?documento.getIgv():0.00);
			pst.setString(22, documento.getCodigoIgv());
			pst.setDouble(23, documento.getOtrosTributos() != null?documento.getOtrosTributos():0.00);
			pst.setDouble(24, documento.getTotal() != null?documento.getTotal():0.00);
			pst.setString(25, documento.getSerieElectronica());
			if(documento.getNumeroElectronico() != null)
				pst.setInt(26, documento.getNumeroElectronico());
			else
				pst.setNull(26, Types.DOUBLE);
			pst.setInt(27, documento.getHomologado() != null ? documento.getHomologado():0);
			pst.setDate(28, documento.getFechaHomologado());
			pst.setBlob(29, documento.getArchivo());
			pst.setString(30, documento.getNombreArchivo());
			pst.setString(31, documento.getMensajeSunat());
			pst.setBlob(32, documento.getRespuestaSunat());
			pst.setString(33,documento.getNombreRespuestaSunat());
			pst.setBoolean(34, documento.getAnulado() != null? documento.getAnulado() : false);
			if(documento.getResumenId() != null)
				pst.setInt(35, documento.getResumenId());
			else
				pst.setNull(35, Types.INTEGER);
			
			return pst.executeUpdate();
		}
	}
	
	public int updateAllDocumento(DocumentoBean documento) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"UPDATE fe.cabdocumentos "+
				"SET periodo = ?,tipodocumento = ?,serie = ?,numero = ?,fechaemision = ?,"+
				"fechavencimiento = ?,tipocliente = ?,numcliente = ?,nomcliente = ?,direccion = ?,departamento = ?,"+
				"provincia = ?,distrito = ?,email = ?,valventaafe = ?,valventaina = ?,valventaexo = ?,"+
				"isc = ?,codisc = ?,igv = ?,codigv = ?,otros = ?,totaldoc = ?,serieelec = ?,numeroelec = ?,"+
				"homologado = ?,fechahomologado = ?,archivo = ?,nom_archivo = ?,mensaje_homologado = ?,"+
				"archivo_homologado = ?,nom_archivo_homologado = ?, anulado = ?,resumen_id = ? "+
				"WHERE transaccion = ? ")){
			
			pst.setString(1, documento.getPeriodo());
			if(documento.getTipo() == null)
				throw new SQLException("Tipo de documento no puede estar vacío");
			pst.setInt(2, documento.getTipo());
			if(documento.getSerieOriginal() == null)
				throw new SQLException("La serie de referencia no puede estar vacío");
			pst.setString(3, documento.getSerieOriginal());
			if(documento.getNumeroOriginal() == null)
				throw new SQLException("El correlativo original no puede estar vacío");
			pst.setInt(4, documento.getNumeroOriginal());
			if(documento.getFechaEmision() == null)
				throw new SQLException("La fecha de emisión no puede estar vacía");
			pst.setDate(5, documento.getFechaEmision());
			pst.setDate(6, documento.getFechaVencimiento());
			pst.setString(7, documento.getTipoCliente());
			pst.setString(8, documento.getNumeroCliente());
			pst.setString(9, documento.getNombreCliente());
			pst.setString(10, documento.getDireccion());
			pst.setString(11, documento.getDepartamento());
			pst.setString(12, documento.getProvincia());
			pst.setString(13, documento.getDistrito());
			pst.setString(14, documento.getEmail());
			pst.setDouble(15, documento.getValorVentaAfecta() != null?documento.getValorVentaAfecta():0.00);
			pst.setDouble(16, documento.getValorVentaInafecta() != null?documento.getValorVentaInafecta():0.00);
			pst.setDouble(17, documento.getValorVentaExonerada() != null?documento.getValorVentaExonerada():0.00);
			pst.setDouble(18, documento.getIsc() != null?documento.getIsc():0.00);
			pst.setString(19, documento.getCodigoIsc());
			pst.setDouble(20, documento.getIgv() != null?documento.getIgv():0.00);
			pst.setString(21, documento.getCodigoIgv());
			pst.setDouble(22, documento.getOtrosTributos() != null?documento.getOtrosTributos():0.00);
			pst.setDouble(23, documento.getTotal() != null?documento.getTotal():0.00);
			pst.setString(24, documento.getSerieElectronica());
			if(documento.getNumeroElectronico() != null)
				pst.setInt(25, documento.getNumeroElectronico());
			else
				pst.setNull(25, Types.DOUBLE);
			pst.setInt(26, documento.getHomologado() != null ? documento.getHomologado():0);
			pst.setDate(27, documento.getFechaHomologado());
			pst.setBlob(28, documento.getArchivo());
			pst.setString(29, documento.getNombreArchivo());
			pst.setString(30, documento.getMensajeSunat());
			pst.setBlob(31, documento.getRespuestaSunat());
			pst.setString(32,documento.getNombreRespuestaSunat());
			pst.setBoolean(33, documento.getAnulado() != null? documento.getAnulado() : false);
			if(documento.getResumenId() != null)
				pst.setInt(34, documento.getResumenId());
			else
				pst.setNull(34, Types.INTEGER);
			
			if(documento.getTransaccion() == null)
				throw new SQLException("El campo transacción no puede estar vacío");
			pst.setString(35, documento.getTransaccion());
			
			return pst.executeUpdate();
		}
	}
	
	public int updatePartDocumento(DocumentoBean documento) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"UPDATE fe.cabdocumentos "+
				"SET serieelec = ?,numeroelec = ?,"+
				"homologado = ?,fechahomologado = ?,archivo = ?,nom_archivo = ?,mensaje_homologado = ?,"+
				"archivo_homologado = ?,nom_archivo_homologado = ?, anulado = ?,resumen_id = ? "+
				"WHERE transaccion = ? ")){
			
			
			pst.setString(1, documento.getSerieElectronica());
			if(documento.getNumeroElectronico() != null)
				pst.setInt(2, documento.getNumeroElectronico());
			else
				pst.setNull(2, Types.DOUBLE);
			pst.setInt(3, documento.getHomologado() != null ? documento.getHomologado():0);
			pst.setDate(4, documento.getFechaHomologado());
			pst.setBlob(5, documento.getArchivo());
			pst.setString(6, documento.getNombreArchivo());
			pst.setString(7, documento.getMensajeSunat());
			pst.setBlob(8, documento.getRespuestaSunat());
			pst.setString(9,documento.getNombreRespuestaSunat());
			pst.setBoolean(10, documento.getAnulado() != null? documento.getAnulado() : false);
			if(documento.getResumenId() != null)
				pst.setInt(11, documento.getResumenId());
			else
				pst.setNull(11, Types.INTEGER);
			
			if(documento.getTransaccion() == null)
				throw new SQLException("El campo transacción no puede estar vacío");
			pst.setString(12, documento.getTransaccion());
			
			return pst.executeUpdate();
		}
	}
	
	public int deleteDocumento(String transaccion) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"DELETE FROM fe.cabdocumentos "+
				"WHERE transaccion = ? ")){
			pst.setString(1, transaccion);
			return pst.executeUpdate();
		}
	}
	
	public int deleteDocumento(Date fechaEmision) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"DELETE FROM fe.cabdocumentos "+
				"WHERE fechaemision = ? ")){
			pst.setDate(1, fechaEmision);
			return pst.executeUpdate();
		}
	}
	
	public DocumentoBean instanceDocumento() {
		return new DocumentoBean();
	}
}
