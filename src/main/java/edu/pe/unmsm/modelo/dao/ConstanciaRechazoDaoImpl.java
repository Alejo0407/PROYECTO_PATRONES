package edu.pe.unmsm.modelo.dao;


import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;
import java.sql.Types;

import edu.pe.unmsm.modelo.dao.beans.ConstanciaRechazoBean;

public class ConstanciaRechazoDaoImpl{

	public Connection conexion;

	public ConstanciaRechazoDaoImpl(Connection conexion){
		this.conexion = conexion;
	}

	public ConstanciaRechazoBean instanceConstancia(){
		return new ConstanciaRechazoBean();
	}

	public int addConstancia(ConstanciaRechazoBean constancia) throws SQLException{
		try(PreparedStatement pst = conexion.prepareStatement(
			"INSERT INTO fe.cdr_rechazos VALUES (?,?,?,?,?)",
			ResultSet.TYPE_FORWARD_ONLY,
			ResultSet.CONCUR_UPDATABLE
			)){
			
			if(constancia.getSerieElectronica() != null)
				pst.setString(1,constancia.getSerieElectronica());
			else
				pst.setNull(1,Types.VARCHAR);

			if(constancia.getNumeroElectronico() != null)
				pst.setInt(2,constancia.getNumeroElectronico());
			else
				pst.setNull(2,Types.INTEGER);

			if(constancia.getSerie() != null)
				pst.setString(3,constancia.getSerie());
			else
				pst.setNull(3,Types.VARCHAR);

			if(constancia.getNumero() != null)
				pst.setInt(4,constancia.getNumero());
			else
				pst.setNull(4,Types.INTEGER);
			
			if(constancia.getCdr() != null)
				pst.setBlob(5,constancia.getCdr());
			else
				pst.setNull(5,Types.BLOB);
			return pst.executeUpdate();
		}
	}
}