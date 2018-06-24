package edu.pe.unmsm.modelo.generador;

import java.io.IOException;
import java.sql.SQLException;


interface SunatState {
	
	void registrar() throws  SQLException, IOException;
}
