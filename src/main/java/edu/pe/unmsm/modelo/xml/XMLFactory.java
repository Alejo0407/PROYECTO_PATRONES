package edu.pe.unmsm.modelo.xml;

import java.util.List;

import edu.pe.unmsm.modelo.dao.beans.DetalleBean;
import edu.pe.unmsm.modelo.dao.beans.DocumentoBean;
import edu.pe.unmsm.modelo.dao.beans.EmpresaBean;


public class XMLFactory {
	
	private static final int COD_FACTURA = 1;
	private static final int COD_BOLETA = 2;
	
	private static final int COD_RESUMEN_DIARIO = 1;
	private static final int COD_RESUMEN_BAJAS=  2;
	/**
	 * Retorna una factura o boleta dependiendo del codigo ingresado en el campo tipo 
	 * el cual compara con los codigos de BeanManager
	 * @param cabecera
	 * @param detalle
	 * @param empresa
	 * @param filename
	 * @param serie
	 * @param correlativo
	 * @param tipo
	 * @return
	 */
	public XMLDocument getXMLFacturaBoleta(DocumentoBean cabecera, List<DetalleBean> detalle,EmpresaBean empresa,
			String filename,String serie, String correlativo, int tipo) {
		
		switch(tipo) {
			case COD_FACTURA:
				return new XMLFactura(cabecera,detalle,empresa,filename,serie,correlativo);
			case COD_BOLETA:
				return new XMLBoleta(cabecera,detalle,empresa,filename,serie,correlativo);
			default:
				return null;
		}
	}
	
	/**
	 * Retorna un documento de resumenes de Bajas para generar
	 * @param documentos -> documentos a anular
	 * @param empresa -> datos de la empresa
	 * @param razones -> razones de anulacion
	 * @param filename ->  nombre del archivo
	 * @param correlacion -> correlativo del archivo
	 * @return
	 */
	public XMLDocument getXMLResumen(List<DocumentoBean> documentos, EmpresaBean empresa,List<String> razones,
			String filename,String correlacion , int tipo) {
		switch(tipo) {
		case COD_RESUMEN_DIARIO:
			return new XMLResumenDiario(documentos,empresa , filename,correlacion);
		case COD_RESUMEN_BAJAS:
			return new XMLResumenBaja(documentos,empresa ,razones, filename,correlacion);
		default:
			return null;
	}
	}
	
	
}
