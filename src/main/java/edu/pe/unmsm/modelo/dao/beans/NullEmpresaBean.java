package edu.pe.unmsm.modelo.dao.beans;

public class NullEmpresaBean extends EmpresaBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3338131781545795107L;

	@Override
	public String getRuc(){
		if(super.getRuc() == null)
			return "ruc";
		else
			return super.getRuc();
	}
	@Override
	public String getNombre(){
		if(super.getNombre() == null)
			return "Empresa A";
		else
			return super.getNombre();	
	}
	@Override
	public String getDireccion(){
		if(super.getDireccion() == null)
			return "Av. x 110";	
		else
			return super.getDireccion();
	}
	@Override
	public String getNombreComercial(){
		if(super.getNombreComercial() == null)
			return "Empresa A SAC";
		else
			return super.getNombreComercial();
	}
	@Override
	public String getUbigeo(){
		if(super.getUbigeo() == null)
			return "-";
		else
			return super.getUbigeo();
	}
	@Override
	public String getUrbanizacion(){
		if(super.getUrbanizacion() == null)
			return "-";	
		else
			return super.getUrbanizacion();
	}
	@Override
	public String getDistrito(){
		if(super.getDistrito() == null)
			return "-";	
		else
			return super.getDistrito();
	}
	@Override
	public String getProvincia(){
		if(super.getProvincia() == null)
			return "-";	
		else
			return super.getProvincia();
	}
	@Override
	public String getDepartamento(){
		if(super.getDepartamento() == null)
			return "-";	
		else
			return super.getDepartamento();
	}
	@Override
	public String getTelefono(){
		if(super.getTelefono() == null)
			return "123456789";
		else
			return super.getTelefono();	
	}
	@Override
	public String getEmail(){
		if(super.getEmail() == null)
			return "corporativo@email.com";
		else
			return super.getEmail();	
	}
	@Override
	public String getWeb(){
		if(super.getWeb() == null)
			return "www.empresaA.com";	
		else
			return super.getWeb();
	}
	@Override
	public String getPin(){
		if(super.getPin() == null)
			return "Pin del CE";	
		else
			return super.getPin();
	}
	@Override
	public String getPinRevocar(){
		if(super.getPinRevocar() == null)
			return "Pin para anular el CE";	
		else
			return super.getPinRevocar();
	}
	@Override
	public String getAlias(){
		if(super.getAlias() == null)
			return "racer (pvp ....)";	
		else
			return super.getAlias();
	}
	@Override
	public String getUsuarioSecuandario(){
		if(super.getUsuarioSecuandario() == null)
			return "Usuario SOL";	
		else
			return super.getUsuarioSecuandario();
	}
	@Override
	public String getPassword(){
		if(super.getPassword() == null)
			return "1234";	
		else
			return super.getPassword();
	}

	@Override
	public boolean isNull(){
		return true;
	}
}