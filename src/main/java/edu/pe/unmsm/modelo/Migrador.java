package edu.pe.unmsm.modelo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import edu.pe.unmsm.modelo.dao.DetalleDao;
import edu.pe.unmsm.modelo.dao.DocumentoDao;
import edu.pe.unmsm.modelo.dao.beans.DetalleBean;
import edu.pe.unmsm.modelo.dao.beans.DocumentoBean;

public abstract class Migrador {
	
	private DocumentoDao docDao;
	private DetalleDao detalleDao;
	
	public Migrador(DocumentoDao docDao, DetalleDao detalleDao) {
		this.docDao = docDao;
		this.detalleDao = detalleDao;
	}
	
	protected abstract void limpiarDatos(Date fecha,boolean corregido) throws SQLException;
	protected abstract List<DocumentoBean> extraerCabeceras( Date fecha ) throws SQLException;
	protected abstract List<DetalleBean> extraerDetalles( Date fecha ) throws SQLException;
	
	public List<DocumentoBean> migrar(Date fecha, boolean corregido) throws SQLException{
		
		limpiarDatos(fecha, corregido);
		List<DocumentoBean> cabeceras = this.extraerCabeceras(fecha);
		List<DetalleBean> detalles = this.extraerDetalles(fecha);
		
		for(DocumentoBean doc:cabeceras) {
			docDao.addDocumento(doc);
			List<DetalleBean> det = detalles.stream()
					.filter(p -> p.getTransaccion().equals(doc.getTransaccion()))
					.collect(Collectors.toList());
			
			for(DetalleBean d:det) 
				detalleDao.addDetalle(d);
			
		}
		
		return cabeceras;
	}

	public DocumentoDao getDocDao() {
		return docDao;
	}

	public void setDocDao(DocumentoDao docDao) {
		this.docDao = docDao;
	}

	public DetalleDao getDetalleDao() {
		return detalleDao;
	}

	public void setDetalleDao(DetalleDao detalleDao) {
		this.detalleDao = detalleDao;
	}
	
}
