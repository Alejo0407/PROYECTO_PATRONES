package edu.pe.unmsm.controlador.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuBO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int tipoUsuario;
	
	private List<MenuBean> menus;
	public MenuBO(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public List<MenuBean> loadMenu() {
		
		this.menus = new ArrayList<MenuBean>();
		this.menus.add(this.generarMenuProcesos());
		this.menus.add(this.generarMenuMonitoreo());
		this.menus.add(this.generarMenuReportes());
		this.menus.add(this.generarMenuUsuarios());
		
		
		return this.menus;
	}
	
	public List<MenuBean> getMenus() {
		return this.menus;
	}
	
	private MenuBean generarMenuProcesos(){
		//Menu de procesos
		MenuBean menuProcesos = new MenuBean("Procesos");
		String[][] opciones1 = {
			{"Generador en lotes"},
			{"invocarContenido(\"procesos/lotes/generador.jsp\")"}
		};
		menuProcesos.put("Lotes",opciones1);
		
		String[][] opciones2 = {
			{"Resumen Diario"
			,"Estado de Resumen Diario"
			,"Estado de Resumen de Bajas"},
			
			{"invocarContenido(\"procesos/resumen/resumenDiario.jsp\")",
			"invocarContenido(\"procesos/resumen/estadoResumenDiario.jsp\")",
			"invocarContenido(\"procesos/resumen/estadoResumenBajas.jsp\")"}
		};
		menuProcesos.put("Resumen",opciones2);
		
		if(this.tipoUsuario == 1) {
			String[][] opciones3 = {
					{"Anular Documento",
					"Anular Documento (Error del sistema)"},

					{"invocarContenido(\"procesos/bajas/anular.jsp\")",
					"invocarContenido(\"procesos/bajas/anularError.jsp\")"}
			};
			menuProcesos.put("Bajas",opciones3);
			
			String[][] opciones4 = {
				{"Reenvío de Documentos"},
				{"invocarContenido(\"procesos/emergencia/reenvio.jsp\")"}
			};
			menuProcesos.put("Emergencia",opciones4);
		}
		
		return menuProcesos;
	}

	private MenuBean generarMenuMonitoreo(){
		//Menu de procesos
		MenuBean menuMonitoreo = new MenuBean("Monitoreo");
		String[][] opciones = {
			{"Monitoreo de documentos"},
			{"invocarContenido(\"monitoreo/monitoreo/monitoreo.jsp\")"}
		};
		menuMonitoreo.put("Visualizar",opciones);

		return menuMonitoreo;
	}
	
	private MenuBean generarMenuReportes(){

		//Menu de procesos
		MenuBean menuReportes = new MenuBean("Reportes");
		String[][] opciones = {
			{"Resumen de Ventas"},
			{"invocarContenido(\"resumen/ventas/resumenVentas.jsp\")"}
		};
		menuReportes.put("Ventas",opciones);

		return menuReportes;
	}

	private MenuBean generarMenuUsuarios(){
		//Menu de procesos
		MenuBean menuUsuarios = new MenuBean("Usuario");
		
		if(this.tipoUsuario == 1) {
			String[][] opciones1 = {
					{"Crear Usuario"
					,"Modificar Usuario"
					,"Eliminar Usuario"},
					
					{"invocarContenido(\"usuarios/gestion/crearUsuario.jsp\")",
					"invocarContenido(\"usuarios/gestion/modificarUsuario.jsp\")",
					"invocarContenido(\"usuarios/gestion/eliminarUsuario.jsp\")"}
				};
				String[][] opciones2 = {
					{"Configuración de Sistema"
					,"Datos de la Empresa"},
					
					{"invocarContenido(\"usuarios/config/configSistema.jsp\")",
					"invocarContenido(\"usuarios/config/configEmpresa.jsp\")"}
				};
				
				menuUsuarios.put("Gestión de Usuarios",opciones1);
				menuUsuarios.put("Configuraciones",opciones2);
		}
		
		String[][] opciones3 = {
			{"Perfil","Salir"},
			{"invocarContenido(\"usuarios/gestion/modificarUsuario.jsp\")","loggout()"}
		};
		menuUsuarios.put("",opciones3);

		return menuUsuarios;
	}
}
