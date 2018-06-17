package edu.pe.unmsm.modelo.dao;


import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
			"INSERT INTO fe.cdr_rechazos VALUES (?,?,?,?,?)"
			)){
			
			if(constancia.getSerieElectronica() == null)
				throw new SQLException("La serie electrónica no puede estar vacía");			
			pst.setString(1,constancia.getSerieElectronica());
			if(constancia.getNumeroElectronico() == null)
				throw new SQLException("El correlativo electrónico no puede estar vacío");
			pst.setInt(2,constancia.getNumeroElectronico());
			if(constancia.getSerie() == null)
				throw new SQLException("La serie original no puede estar vacía");
			pst.setString(3,constancia.getSerie());

			if(constancia.getNumero() == null)
				throw new SQLException("El número original no puede estar vacío");
			pst.setInt(4,constancia.getNumero());
			if(constancia.getCdr() == null)
				throw new SQLException("El archvio de CDR no debe estar vacío");
			pst.setBlob(5,constancia.getCdr());
			
			return pst.executeUpdate();
		}
	}
}