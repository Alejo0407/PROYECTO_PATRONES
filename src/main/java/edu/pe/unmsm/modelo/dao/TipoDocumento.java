package edu.pe.unmsm.modelo.dao;

public interface TipoDocumento {

	final int TIPO_FACTURA = 1;
	final int TIPO_BOLETA = 3;
	final int TIPO_NOTA_CREDITO = 7;
	final int TIPO_NOTA_DEBITO = 8;

	final String RESUMEN_BAJAS = "RA";
	final String RESUMEN_DIARIO = "RC";
}