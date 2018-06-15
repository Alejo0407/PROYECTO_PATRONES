package edu.pe.unmsm.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pe.unmsm.modelo.dao.beans.URLBean;

public class URLDaoImpl {
	public Connection conexion;

	public URLDaoImpl(Connection conexion){
		this.conexion = conexion;
	}
	
	public List<URLBean> listUrl() throws SQLException{ 	
		try(Statement st = conexion.createStatement(
				ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery(
				"SELECT u.id,t.descripcion,u.valor,u.descripcion,u.activo "+
				"FROM fe.url_sunat u "+
				"INNER JOIN url_tipo t ON (t.id_url_tipo = u.id_url_tipo ) "+
				"ORDER BY u.id ")){
			
			List<URLBean> urls = new ArrayList<>();
			while(rs.next()) {
				URLBean url = new URLBean();
				url.setId(rs.getInt(1));
				url.setTipo(rs.getString(2));
				url.setValor(rs.getString(3));
				url.setLabel(rs.getString(4));
				url.setActivo(rs.getBoolean(5));
				urls.add(url);
			}
			
			return urls;
		}
	}
	
	public List<URLBean> getActivos() throws SQLException{
		try(Statement st = conexion.createStatement(
				ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery(
				"SELECT u.id,t.descripcion,u.valor,u.descripcion,u.activo "+
				"FROM fe.url_sunat u "+
				"INNER JOIN url_tipo t ON (t.id_url_tipo = u.id_url_tipo ) "+
				"WHERE u.activo = true "+
				"ORDER BY u.id ")){
			
			List<URLBean> urls = new ArrayList<>();
			while(rs.next()) {
				URLBean url = new URLBean();
				url.setId(rs.getInt(1));
				url.setTipo(rs.getString(2));
				url.setValor(rs.getString(3));
				url.setLabel(rs.getString(4));
				url.setActivo(rs.getBoolean(5));
				urls.add(url);
			}
			
			return urls;
		}
	}
	
	public int updateUrl(URLBean url) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"UPDATE fe.url_sunat set valor = ?, activo = ? "+
				"WHERE id = ? ")){
			
			pst.setString(1, url.getValor());
			pst.setBoolean(2, url.getActivo());
			pst.setInt(3, url.getId());
			
			return pst.executeUpdate();
		}
	}
}
