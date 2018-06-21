package edu.pe.unmsm.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.pe.unmsm.modelo.dao.DetalleDao;
import edu.pe.unmsm.modelo.dao.DocumentoDao;
import edu.pe.unmsm.modelo.dao.TipoDocumento;
import edu.pe.unmsm.modelo.dao.beans.DetalleBean;
import edu.pe.unmsm.modelo.dao.beans.DocumentoBean;

public class MigradorFacturas extends Migrador {
	
	private Connection origen;
	public MigradorFacturas(DocumentoDao docDao, DetalleDao detalleDao
			, Connection origen) {
		super(docDao, detalleDao);
		this.origen = origen;
	}

	@Override
	protected void limpiarDatos(Date fecha, boolean corregido) throws SQLException{
		int afectadas = this.getDetalleDao().deleteDetalle(fecha, TipoDocumento.TIPO_FACTURA ,corregido);
		
		if(afectadas == 0)
			Logger.getGlobal().log(Level.WARNING, "Ningúna línea fue afectada en el borrado del Detalle");
		
		afectadas = this.getDocDao().deleteDocumento(fecha, TipoDocumento.TIPO_FACTURA ,corregido);
		if(afectadas == 0)
			Logger.getGlobal().log(Level.WARNING, "Ningúna línea fue afectada en el borrado de la Cabecera");
		
	}

	@Override
	protected List<DocumentoBean> extraerCabeceras(Date fecha) throws SQLException{
		// TODO Auto-generated method stub
		List<DocumentoBean> docs = this.getDocDao().listDocumentos(fecha, TipoDocumento.TIPO_FACTURA);
		
		String valor = "";
		for(DocumentoBean doc:docs) {
			valor += doc.getTransaccion() + ",";
		}
		if(!valor.isEmpty())
			valor = " AND m_sali.sali_p_incodsal NOT IN (" + valor.substring(0,valor.length()-1) + ")";
		
		try(Statement st = this.origen.createStatement(
				ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
			ResultSet tabla = st.executeQuery(
					"SELECT  m_sali.sali_p_incodsal AS Transaccion, " + 
					"SUBSTRING(TRIM(m_sali.SALI_chFECSAL),1,6) AS periodo, " + 
					"m_sali.SALI_chFECSAL AS fechaemision, " + 
					"IF(TRIM(m_tipo_docu_impr.TDOI_chNOMTIP)=\"FACTURA\",\"01\",\"03\") AS TIPODOCUMENTO, " + 
					"m_sali.SALI_inSITSAL AS ANULADO, " + 
					"m_sali.SALI_chSERDOC AS serie, " + 
					"m_sali.SALI_chNRODOC AS numero, " + 
					"IF(LENGTH(TRIM(m_pers.Pers_chrucdniper))=11,\"6\",\"1\") AS tipocliente, " + 
					"m_pers.Pers_chrucdniper AS numcliente, " + 
					"m_pers.Pers_chnomcom AS nomcliente, " + 
					"m_pers.Pers_chdirper AS direccion, " + 
					"m_pers.Pers_chemaper AS email, " + 
					"m_sali.SALI_deTOTSAL AS valventaexo, " + 
					"m_sali.SALI_deTOTSAL AS totaldoc " + 
					"FROM  " + 
					"carbuss.m_sali " + 
					"INNER JOIN m_pers " + 
					"ON m_sali.PERS_P_inCODPER=m_pers.Pers_P_incodper " + 
					"INNER JOIN m_tipo_docu_impr " + 
					"ON m_sali.TDOI_P_inCODTIP=m_tipo_docu_impr.TDOI_P_inCODTIP " + 
					"WHERE m_sali.sali_chfecsal=\""+getDateAsString(fecha)+"\" \"" +valor +"\"  " + 
					"AND m_tipo_docu_impr.TDOI_chNOMTIP = 'FACTURA' AND m_sali.SALI_inSITSAL <> 4  " + 
					"ORDER BY m_sali.TDOI_P_inCODTIP,m_sali.SALI_chNRODOC\"");
				
			){
			
			List<DocumentoBean> documentos = new ArrayList<>();
			while(tabla.next()) {
				DocumentoBean factura = this.getDocDao().instanceDocumento();
				factura.setTransaccion(tabla.getString("transaccion"));
				factura.setPeriodo(tabla.getString("periodo"));
				factura.setTipo(Integer.parseInt(tabla.getString("TIPODOCUMENTO")));
				factura.setSerieOriginal(tabla.getString("serie"));
				factura.setNumeroOriginal(Integer.parseInt(tabla.getString("numero")));
				factura.setFechaEmision(fecha);
				factura.setTipoCliente(tabla.getString("tipocliente"));
				factura.setNumeroCliente(tabla.getString("numcliente"));
				factura.setNombreCliente( tabla.getString("nomcliente"));;
				factura.setDireccion(tabla.getString("direccion"));;
				factura.setEmail(tabla.getString("email"));
				factura.setValorVentaExonerada( tabla.getDouble("valventaexo"));
				factura.setCodigoIgv("20");
				factura.setIgv(0.00);
				factura.setTotal( tabla.getDouble("totaldoc"));
				factura.setHomologado(0);
				
				documentos.add(factura);
			}
			
			return documentos;
		}
		
	}

	@Override
	protected List<DetalleBean> extraerDetalles(Date fecha) throws SQLException{
		try(Statement st2 = this.origen.createStatement(
				ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
			ResultSet detalle = st2.executeQuery(
				"SELECT v_sali.sali_p_incodsal AS transaccion, " + 
				" m_cata.cata_chcodext AS codigo, " + 
				" m_cata.cata_chnomcat AS denominacion, " + 
				" m_unid.unid_chSUNAT  AS unidad, " + 
				" v_sali.sald_decansal AS cantidad, " + 
				" v_sali.sald_depreuni AS valunitario, " + 
				" v_sali.sald_detotdet AS valtotal, " + 
				" v_sali.estado, " + 
				" v_cata_deta.CATD_P_inCODCAT, " + 
				" v_cata_deta.CATA_P_inCODCAT " + 
				" FROM v_sali " + 
				" INNER JOIN v_cata_deta " + 
				" ON v_sali.catd_p_incodcat=v_cata_deta.CATD_P_inCODCAT " + 
				" INNER JOIN m_cata " + 
				" ON v_cata_deta.CATA_P_inCODCAT=m_cata.cata_p_incodcat " + 
				" INNER JOIN m_unid " + 
				" ON v_cata_deta.UNID_P_inCODUNI=m_unid.unid_p_incoduni " + 
				" INNER JOIN m_sali " + 
				" ON m_sali.SALI_P_inCODSAL = v_sali.sali_p_incodsal " + 
				" WHERE m_sali.sali_chfecsal = \""+getDateAsString(fecha)+"\" AND v_sali.estado = 1 ")){
			
			List<DetalleBean> detalles = new ArrayList<>();
			String transaccion = "";
			int sec = 0;
			
			while(detalle.next()) {
				DetalleBean det = this.getDetalleDao().instanceDetalle();
				if(sec == 0)
					transaccion = detalle.getString("transaccion");
				if(!transaccion.equals(detalle.getString("transaccion"))) {
					sec = 0;
					transaccion = detalle.getString("transaccion");
				}
				det.setTransaccion(detalle.getString("transaccion"));
				det.setNumeroItem(String.format("%03d", ++sec));
				det.setCodigo(detalle.getString("codigo"));
				det.setDescripcion(detalle.getString("denominacion"));
				det.setCodigoUnidad(detalle.getString("unidad"));
				det.setValorUnitario( detalle.getDouble("valunitario"));
				det.setCantidad(detalle.getDouble("cantidad"));
				det.setIgv(0.00);
				det.setCodigoIgv("20");
				det.setTotal(detalle.getDouble("valtotal"));
				
				detalles.add(det);
			}
			
			return detalles;
			
		}
	}
	
	private String getDateAsString(Date fecha) {
		GregorianCalendar date = new GregorianCalendar();
		date.setTime(fecha);
			
		String anio = String.format("%04d", date.get(GregorianCalendar.YEAR)),
				mes = String.format("%02d", date.get(GregorianCalendar.MONTH)+1),
				dia = String.format("%02d", date.get(GregorianCalendar.DATE));
		return anio+mes+dia;
	}
}
