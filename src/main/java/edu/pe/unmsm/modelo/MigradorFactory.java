package edu.pe.unmsm.modelo;

import java.sql.Connection;

import edu.pe.unmsm.modelo.dao.DetalleDao;
import edu.pe.unmsm.modelo.dao.DocumentoDao;

public class MigradorFactory {
	
	public final static int MIG_FACTURA = 1;
	public final static int MIG_BOLETA  = 2;
	
	public Migrador getMigrador(DocumentoDao docDao, DetalleDao detDao,
			Connection origen, int tipo) {
		
		if(tipo == MigradorFactory.MIG_BOLETA)
			return new MigradorBoletas(docDao,detDao, origen);
		else if(tipo == MigradorFactory.MIG_FACTURA)
			return new MigradorFacturas(docDao,detDao, origen);
		else 
			return null;
	}
}
