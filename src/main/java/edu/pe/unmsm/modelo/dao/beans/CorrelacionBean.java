package edu.pe.unmsm.modelo.dao.beans;

import java.io.Serializable;

public class CorrelacionBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8399814305619461773L;

	public CorrelacionBean(){}

	private Integer tipoDocumento;
	private String serie;
	private Integer correlativo;

	public void setTipoDocumento(Integer tipoDocumento){
		this.tipoDocumento = tipoDocumento;
	}
	public void setSerie(String serie){
		this.serie =  serie;
	}
	public void setCorrelativo(Integer correlativo){
		this.correlativo = correlativo;
	}
	public Integer getTipoDocumento(){
		return this.tipoDocumento;
	}
	public String getSerie(){
		return this.serie;
	}
	public Integer getCorrelativo(){
		return this.correlativo;
	}
	
	public void aumentarCorrelacion()  {
		if(correlativo.intValue() == 99999999) {
			char c[] = getSerie().toCharArray();
			
			for(int i = serie.length() -1 ; i >= 0 ; i-- ) {
				int val =  (int)serie.charAt( i );
				if( (val >='0' && val < '9') || (val >= 'A' && val < 'Z') ) 
					val++;
				else if( val == '9') 
					val = 'A';
				else 
					val = '0';
				c[i] = (char)val;
				if(val == '0' && i == 0)
					System.err.println("ERROR");
				if( val != '0')
					break;
			}
			setCorrelativo(1);
			setSerie(String.valueOf(c[0]) + String.valueOf(c[1])  + String.valueOf(c[2]));
		}else
			setCorrelativo(correlativo.intValue()+1);		
	}
}