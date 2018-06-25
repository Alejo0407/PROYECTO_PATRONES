package edu.pe.unmsm.modelo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import edu.pe.unmsm.modelo.dao.ConstanciaRechazoDaoImpl;
import edu.pe.unmsm.modelo.dao.CorrelacionDaoImpl;
import edu.pe.unmsm.modelo.dao.DetalleDaoImpl;
import edu.pe.unmsm.modelo.dao.DocumentoDao;
import edu.pe.unmsm.modelo.dao.DocumentoDaoImpl;
import edu.pe.unmsm.modelo.dao.EmpresaDao;
import edu.pe.unmsm.modelo.dao.EmpresaDaoImpl;
import edu.pe.unmsm.modelo.dao.ResumenDaoImpl;
import edu.pe.unmsm.modelo.dao.SistemaDao;
import edu.pe.unmsm.modelo.dao.SistemaDaoImpl;
import edu.pe.unmsm.modelo.dao.TipoDocumento;
import edu.pe.unmsm.modelo.dao.URLDao;
import edu.pe.unmsm.modelo.dao.URLDaoImpl;
import edu.pe.unmsm.modelo.dao.UsuarioDao;
import edu.pe.unmsm.modelo.dao.UsuarioDaoImpl;
import edu.pe.unmsm.modelo.dao.beans.DocumentoBean;
import edu.pe.unmsm.modelo.dao.beans.EmpresaBean;
import edu.pe.unmsm.modelo.dao.beans.ResumenBean;
import edu.pe.unmsm.modelo.dao.beans.SistemaBean;
import edu.pe.unmsm.modelo.dao.beans.URLBean;
import edu.pe.unmsm.modelo.dao.beans.UsuarioBean;
import edu.pe.unmsm.modelo.generador.GeneradorBoletas;
import edu.pe.unmsm.modelo.generador.GeneradorDocumentos;
import edu.pe.unmsm.modelo.generador.GeneradorFacturas;
import edu.pe.unmsm.modelo.generador.GeneradorResumenBajas;
import edu.pe.unmsm.modelo.generador.GeneradorResumenDiario;
import edu.pe.unmsm.modelo.generador.GeneradorResumenes;
import edu.pe.unmsm.modelo.migracion.Migrador;
import edu.pe.unmsm.modelo.migracion.MigradorFactory;

public class Programa {
	
	public static SistemaBean sistema = null;
	
	private Connection origen,fe;
	
	public Programa() throws SQLException, NamingException {

		Logger.getGlobal().log(Level.INFO, "INTENTANDO CONECTAR A BD DE ORIGEN....");
		InitialContext ctx = new InitialContext();
		this.setOrigen(((DataSource)ctx.lookup("jdbc/origen")).getConnection());
		Logger.getGlobal().log(Level.INFO, "CONEXION EXITOSA....");

		Logger.getGlobal().log(Level.INFO, "INTENTANDO CONECTAR A BD DE SISTEMA....");
		this.setFe(((DataSource)ctx.lookup("jdbc/fe")).getConnection());	
		Logger.getGlobal().log(Level.INFO, "CONEXION EXITOSA....");
		
		init();
	}
	/**
	 * 
	 * @param origen -> Fuente de origen de daots
	 * @param fe -> BD del sistema
	 * @throws SQLException
	 */
	public Programa(Connection origen, Connection fe) throws SQLException {
		this.setOrigen(origen);
		this.setFe(fe);
		
		init();
	}
	
	public synchronized void init() throws SQLException {
		SistemaDao sistemaDao = new SistemaDaoImpl(this.getFe());
		if(Programa.sistema == null)
			Programa.sistema = sistemaDao.getSistema();
	}
	
	//PARA LOS USUARIOS
	public UsuarioBean getUsuarioVacio() {
		return new UsuarioDaoImpl(this.getFe()).instanceUsuario();
	}
	public synchronized void addUsuario(UsuarioBean usuario) throws SQLException {
		UsuarioDao dao = new UsuarioDaoImpl(this.getFe());
		dao.addUsuario(usuario);
		Logger.getGlobal().log(Level.INFO, "USUARIO "+ usuario.getId() + " REGISTRADO");
	}
	public synchronized void deleteUsuario(UsuarioBean usuario) throws SQLException {
		
		if(usuario.getRango() != 1) {
			UsuarioDao dao = new UsuarioDaoImpl(this.getFe());
			dao.deleteUsuario(usuario.getId());
			Logger.getGlobal().log(Level.INFO, "USUARIO "+ usuario.getId() + " ELIMINADO");
		}
		else 
			throw new IllegalArgumentException("EL USUARIO A ELIMINAR NO PUEDE SER DE CLASE ADMINISTRADOR");		
	}
	public List<UsuarioBean> listarUsuarios() throws SQLException{
		UsuarioDao dao = new UsuarioDaoImpl(this.getFe());
		return dao.listUsuarios();
	}
	public void updateUsuario(UsuarioBean usuario) throws SQLException {
		UsuarioDao dao = new UsuarioDaoImpl(this.getFe());
		int i = dao.updateUsuario(usuario);
		if( i == 0)
			Logger.getGlobal().log(Level.WARNING, "NO SE HAN AFECTADO DATOS EN LA BD");
		else
			Logger.getGlobal().log(Level.INFO, "USUARIO "+ usuario.getId() + " MODIFICADO");
	}
	
	//PARA LA INFO DE LA EMPRESA
	public EmpresaBean getDatosEmpresa() throws SQLException {
		EmpresaDao dao = new EmpresaDaoImpl(this.getFe());
		return dao.getEmpresa();
	}
	public synchronized void updateEmpresa(EmpresaBean empresa) throws SQLException {
		EmpresaDao dao = new EmpresaDaoImpl(this.getFe());
		int i = dao.updateEmpresa(empresa);
		if(i == 0)
			Logger.getGlobal().log(Level.INFO, "DATOS DE EMPRESA INGRESADOS");
		else
			Logger.getGlobal().log(Level.INFO, "DATOS DE EMPRESA ACTUALIZADOS");
	}
	
	//PARA LA INFO DE SISTEMA
	public synchronized void updateSistema () throws SQLException {
		SistemaDao dao = new SistemaDaoImpl(this.getFe());
		dao.updateSistema(sistema);
	}
	public synchronized void updateURL(List<URLBean> urls) throws SQLException{
		this.getFe().setAutoCommit(false);
		URLDao dao = new URLDaoImpl(this.getFe());
		try {
			for(URLBean url : urls) {
				Logger.getGlobal().log(Level.INFO, "ACTUALIZANDO URL..."+url.getTipo());
				dao.updateUrl(url);
			}
			this.getFe().commit();
			this.getFe().setAutoCommit(true);
			
		}catch(SQLException e) {
			Logger.getGlobal().log(Level.SEVERE, "ERROR DURANTE LAS ACTUALIZACIONES DE URL " + e.getMessage());
			this.getFe().rollback();
			this.getFe().setAutoCommit(true);
			throw  e;
		}
	}
	public List<URLBean> getURL() throws SQLException{
		URLDao dao = new URLDaoImpl(this.getFe());
		return dao.listUrl();
	}
	
	//MIGRACIONES
	public synchronized List<DocumentoBean> migrarFacturas(Date fecha, boolean corregido) throws SQLException{
		this.getFe().setAutoCommit(false);
		try {
			
			Migrador migrador = new MigradorFactory().getMigrador(
					new DocumentoDaoImpl(this.getFe()),
					new DetalleDaoImpl(this.getFe()), 
					getOrigen(), 
					MigradorFactory.MIG_FACTURA
				);
			Logger.getGlobal().log(Level.INFO, "INICIANDO MIGRACION DE FACTURAS....");
			List<DocumentoBean> docs =  migrador.migrar(new java.sql.Date(fecha.getTime()), corregido);
			Logger.getGlobal().log(Level.INFO, "MIGRACION EXITOSA");
			this.getFe().commit();
			this.getFe().setAutoCommit(true);
			return docs;
		}
		catch(SQLException e) {
			Logger.getGlobal().log(Level.SEVERE, "ERROR DURANTE LA MIGRACION...." + e.getMessage());
			this.getFe().rollback();
			this.getFe().setAutoCommit(true);
			throw e;
		}
	}
	public synchronized List<DocumentoBean> migrarBoletas(Date fecha, boolean corregido) throws SQLException{
		this.getFe().setAutoCommit(false);
		try {
			
			Migrador migrador = new MigradorFactory().getMigrador(
					new DocumentoDaoImpl(this.getFe()),
					new DetalleDaoImpl(this.getFe()), 
					getOrigen(), 
					MigradorFactory.MIG_BOLETA
				);
			Logger.getGlobal().log(Level.INFO, "INICIANDO MIGRACION DE BOLETAS....");
			List<DocumentoBean> docs =  migrador.migrar(new java.sql.Date(fecha.getTime()), corregido);
			Logger.getGlobal().log(Level.INFO, "MIGRACION EXITOSA");
			this.getFe().commit();
			this.getFe().setAutoCommit(true);
			return docs;
		}
		catch(SQLException e) {
			Logger.getGlobal().log(Level.SEVERE, "ERROR DURANTE LA MIGRACION...." + e.getMessage());
			this.getFe().rollback();
			this.getFe().setAutoCommit(true);
			throw e;
		}
	}
	//GENERACIONES
	public synchronized List<DocumentoBean> generarFacturas(Date fecha) throws NullPointerException, 
			UnsupportedOperationException, SQLException, 
			ParserConfigurationException, TransformerException, 
			SOAPException, IOException, SAXException{
		
		GeneradorDocumentos generador = new GeneradorFacturas(
				new DocumentoDaoImpl(this.getFe()),
				new DetalleDaoImpl(this.getFe()),
				new EmpresaDaoImpl(this.getFe()),
				new CorrelacionDaoImpl(this.getFe()),
				new URLDaoImpl(this.getFe()),
				new ConstanciaRechazoDaoImpl(this.getFe()),
				Programa.sistema
		);
		return generador.generar(new java.sql.Date(fecha.getTime()));
	}
	public synchronized List<DocumentoBean> generarBoletas(Date fecha) throws NullPointerException, 
			UnsupportedOperationException, SQLException, 
			ParserConfigurationException, TransformerException, 
			SOAPException, IOException, SAXException{
			
		GeneradorDocumentos generador = new GeneradorBoletas(
				new DocumentoDaoImpl(this.getFe()),
				new DetalleDaoImpl(this.getFe()),
				new EmpresaDaoImpl(this.getFe()),
				new CorrelacionDaoImpl(this.getFe()),
				new URLDaoImpl(this.getFe()),
				new ConstanciaRechazoDaoImpl(this.getFe()),
				Programa.sistema
		);
		return generador.generar(new java.sql.Date(fecha.getTime()));
	}
	
	public synchronized ResumenBean generarResumenDiario(Date fechaReferencia) throws NullPointerException, 
		UnsupportedOperationException, SQLException, ParserConfigurationException, 
		TransformerException, IOException, SOAPException, SAXException {
		
		GeneradorResumenes generador = new GeneradorResumenDiario(
			new DocumentoDaoImpl(this.getFe()),
			new java.sql.Date(fechaReferencia.getTime()),
			new EmpresaDaoImpl(this.getFe()),
			new URLDaoImpl(this.getFe()),
			new ResumenDaoImpl(this.getFe()),
			Programa.sistema
		);
		
		return generador.generar();
	}
	
	public synchronized ResumenBean generarResumenBajas(List<DocumentoBean> docs, List<String> razones) 
			throws NullPointerException, 
			UnsupportedOperationException, SQLException, ParserConfigurationException, 
			TransformerException, IOException, SOAPException, SAXException {
	
		GeneradorResumenes generador = new GeneradorResumenBajas(
			docs,
			razones,
			new DocumentoDaoImpl(this.getFe()),
			new EmpresaDaoImpl(this.getFe()),
			new URLDaoImpl(this.getFe()),
			new ResumenDaoImpl(this.getFe()),
			Programa.sistema
		);
		
		return generador.generar();
	}
	
	//PARA LAS TABLAS
	public List<DocumentoBean> listBoletas(Date fecha) throws SQLException{
		DocumentoDao dao = new DocumentoDaoImpl(this.getFe());
		return dao.listDocumentos(new java.sql.Date(fecha.getTime()), TipoDocumento.TIPO_BOLETA);
	}
	
	public List<DocumentoBean> listFacturas(Date fecha) throws SQLException{
		DocumentoDao dao = new DocumentoDaoImpl(this.getFe());
		return dao.listDocumentos(new java.sql.Date(fecha.getTime()), TipoDocumento.TIPO_FACTURA);
	}
	public List<UsuarioBean> listUsuarios() throws SQLException{
		UsuarioDao dao = new UsuarioDaoImpl(this.getFe());
		return dao.listUsuarios();
	}

	public void closeResources() {
		try {
			Logger.getGlobal().log(Level.INFO, "INTENTANDO CERRAR CONEXIONES A BD");
			this.getFe().close();
			this.getOrigen().close();

			Logger.getGlobal().log(Level.INFO, "CONEXIONES CERRADAS...");
		}catch(SQLException e) {
			e.printStackTrace();
			Logger.getGlobal().log(Level.INFO, "ERROR DURANTE EL CIERRE DE CONEXIONES");
		}
	}
	
	public Connection getOrigen() {
		return origen;
	}

	public void setOrigen(Connection origen) {
		this.origen = origen;
	}

	public Connection getFe() {
		return fe;
	}

	public void setFe(Connection fe) {
		this.fe = fe;
	}
}
