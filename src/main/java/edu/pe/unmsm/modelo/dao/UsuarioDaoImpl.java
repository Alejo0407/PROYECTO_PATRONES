package edu.pe.unmsm.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import edu.pe.unmsm.modelo.dao.beans.UsuarioBean;

public class UsuarioDaoImpl implements UsuarioDao{

	public Connection conexion;

	public UsuarioDaoImpl(Connection conexion){
		this.conexion = conexion;
	}

	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.UsuarioDao#listUsuarios()
	 */
	@Override
	public List<UsuarioBean> listUsuarios() throws SQLException{
		try(Statement st = conexion.createStatement(
				ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery(""
					+ "SELECT id_usuario,pass,correo,nombres,apellido,id_rango "
					+ "FROM fe.usuarios ")){
			
			List<UsuarioBean> usuarios = new ArrayList<>();
			while(rs.next()) {
				UsuarioBean u = new UsuarioBean();
				u.setId(rs.getString(1));
				u.setPass(rs.getString(2));
				u.setCorreo(rs.getString(3));
				u.setNombres(rs.getString(4));
				u.setApellidos(rs.getString(5));
				u.setRango(rs.getInt(6));
				
				usuarios.add(u);
			}
			
			return usuarios;
		}
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.UsuarioDao#getUsuario(java.lang.String)
	 */
	@Override
	public UsuarioBean getUsuario(String id) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"SELECT id_usuario,pass,correo,nombres,apellido,id_rango "+ 
				"FROM fe.usuarios WHERE id_usuario = ? ", 
				ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
			){
			
			pst.setString(1, id);
			try(ResultSet rs = pst.executeQuery()){
				UsuarioBean u = null;
				
				if(rs.next()) {
					u = new UsuarioBean();
					u.setId(rs.getString(1));
					u.setPass(rs.getString(2));
					u.setCorreo(rs.getString(3));
					u.setNombres(rs.getString(4));
					u.setApellidos(rs.getString(5));
					u.setRango(rs.getInt(6));
				}
				
				return u;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.UsuarioDao#addUsuario(edu.pe.unmsm.modelo.dao.beans.UsuarioBean)
	 */
	@Override
	public int addUsuario(UsuarioBean usuario) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"INSERT INTO fe.usuarios "+
				"(id_usuario,pass,correo,nombres,apellido,id_rango) VALUES (?,?,?,?,?,?)")){
			
			pst.setString(1, usuario.getId());
			pst.setString(2, usuario.getPass());
			pst.setString(3, usuario.getCorreo());
			if(usuario.getNombres() != null)
				pst.setString(4, usuario.getNombres());
			else
				pst.setNull(4, Types.VARCHAR);
			if(usuario.getApellidos() != null)
				pst.setString(5, usuario.getApellidos());
			else
				pst.setNull(5, Types.VARCHAR);
			pst.setInt(6, usuario.getRango());
			
			return pst.executeUpdate();
		}
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.UsuarioDao#updateUsuario(edu.pe.unmsm.modelo.dao.beans.UsuarioBean)
	 */
	@Override
	public int updateUsuario(UsuarioBean usuario) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"UPDATE fe.usuarios "+
				"SET pass = ?, correo = ?,nombres = ?,apellido = ?,id_rango = ? "+
				"WHERE id_usuario = ?")){
			
			pst.setString(1, usuario.getPass());
			pst.setString(2, usuario.getCorreo());
			if(usuario.getNombres() != null)
				pst.setString(3, usuario.getNombres());
			else
				pst.setNull(3, Types.VARCHAR);
			if(usuario.getApellidos() != null)
				pst.setString(4, usuario.getApellidos());
			else
				pst.setNull(4, Types.VARCHAR);
			pst.setInt(5, usuario.getRango());
			pst.setString(6, usuario.getId());
			
			return pst.executeUpdate();
		}
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.UsuarioDao#deleteUsuario(java.lang.String)
	 */
	@Override
	public int deleteUsuario(String id) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
				"DELETE FROM fe.usuarios "+
				"WHERE id_usuario = ? ")){
			pst.setString(1, id);
			return pst.executeUpdate();
		}
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.UsuarioDao#instanceUsuario()
	 */
	@Override
	public UsuarioBean instanceUsuario() {
		return new UsuarioBean();
	}

}