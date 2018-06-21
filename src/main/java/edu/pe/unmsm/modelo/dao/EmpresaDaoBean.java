package edu.pe.unmsm.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.pe.unmsm.modelo.dao.beans.EmpresaBean;
import edu.pe.unmsm.modelo.dao.beans.NullEmpresaBean;

public class EmpresaDaoBean implements EmpresaDao {
	public Connection conexion;

	public EmpresaDaoBean(Connection conexion){
		this.conexion = conexion;
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.EmpresaDao#getEmpresa()
	 */
	@Override
	public EmpresaBean getEmpresa() throws SQLException{
		try(Statement st = conexion.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery(
				"SELECT * FROM fe.empresa ")){
			
			EmpresaBean empresa = null;
			if(rs.next()) {
				empresa = new EmpresaBean();
				empresa.setRuc(rs.getString(1));
				empresa.setNombre(rs.getString(2));
				empresa.setDireccion(rs.getString(3));
				empresa.setNombreComercial(rs.getString(4));
				empresa.setUbigeo(rs.getString(5));
				empresa.setUrbanizacion(rs.getString(6));
				empresa.setProvincia(rs.getString(7));
				empresa.setDepartamento(rs.getString(8));
				empresa.setDistrito(rs.getString(9));
				empresa.setTelefono(rs.getString(10));
				empresa.setEmail(rs.getString(11));
				empresa.setWeb(rs.getString(12));
				empresa.setCertificado(rs.getBlob(13));
				empresa.setNombreCertificado(rs.getString(14));
				empresa.setPin(rs.getString(15));
				empresa.setPinRevocar(rs.getString(16));
				empresa.setAlias(rs.getString(17));
				empresa.setUsuarioSecuandario(rs.getString(18));
				empresa.setPassword(rs.getString(19));
			}
			else
				empresa = new NullEmpresaBean();
			
			return empresa;
		}	
	}
	
	/* (non-Javadoc)
	 * @see edu.pe.unmsm.modelo.dao.EmpresaDao#updateEmpresa(edu.pe.unmsm.modelo.dao.beans.EmpresaBean)
	 */
	@Override
	public int updateEmpresa(EmpresaBean empresa) throws SQLException{
		
		String sqlInsert = "INSERT INTO fe.empresa "+
				"(nombre,direccion,nombre_comercial,ubigeo,urbanizacion,"+
				"provincia,departamento,distrito,telefono,mail_empresa,web,certificado,"+
				"nombre_certificado,pin,pin_revocar,alias,usr_secundario,pass,ruc) "+
				"VALUES "+
				"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		String sqlUpdate = "UPDATE fe.empresa "+
				"SET nombre = ?, direccion = ?, nombre_comercial = ?, ubigeo = ?, urbanizacion = ?,"+
				"provincia = ?, departamento = ?, distrito = ?, telefono = ?, mail_empresa = ?,web = ?,"+
				"certificado = ?, nombre_certificado = ?, pin = ?, pin_revocar = ?, alias = ?, "+
				"usr_secundario = ?, pass ? "+
				"WHERE ruc = ? ";
		try(PreparedStatement pst = conexion.prepareStatement(
				empresa.isNull()?sqlInsert:sqlUpdate)){
			
			pst.setString(1, empresa.getNombre());
			pst.setString(2, empresa.getDireccion());
			pst.setString(3, empresa.getNombreComercial());
			pst.setString(4, empresa.getUbigeo());
			pst.setString(5, empresa.getUrbanizacion());
			pst.setString(6, empresa.getProvincia());
			pst.setString(7, empresa.getDepartamento());
			pst.setString(8, empresa.getDistrito());
			pst.setString(9, empresa.getTelefono());
			pst.setString(10, empresa.getEmail());
			pst.setString(11, empresa.getWeb());
			pst.setBlob(12, empresa.getCertificado());
			pst.setString(13, empresa.getNombreCertificado());
			pst.setString(14, empresa.getPin());
			pst.setString(15, empresa.getPinRevocar());
			pst.setString(16, empresa.getAlias());
			pst.setString(17, empresa.getUsuarioSecuandario());
			pst.setString(18, empresa.getPassword());
			pst.setString(19, empresa.getRuc());
			
			return pst.executeUpdate();
		}
	}
	
}
