package edu.pe.unmsm.controlador;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.IOException;
import edu.pe.unmsm.controlador.beans.MenuBean;


@WebServlet(name = "IndexController", urlPatterns= {"/IndexController"})
public class IndexServlet extends HttpServlet{

	public void doPost(HttpServletRequest request, 
		HttpServletResponse response) throws ServletException,IOException{

		//Siempre va para los caracteres especiales
		response.setCharacterEncoding("UTF-8");

		//logica del controlador
		String user = (String)request.getParameter("user");
		String pass = (String)request.getParameter("pass");

		boolean loggin = !(user.trim().isEmpty() || pass.trim().isEmpty());

		//prueba, va el loggin el usuario del modelo
		loggin = loggin && user.equals("admin")&&pass.equals("admin");

		if(loggin){

			MenuBean menuProcesos 	= this.generarMenuProcesos();
			MenuBean menuMonitoreo 	= this.generarMenuMonitoreo();
			MenuBean menuReportes 	= this.generarMenuReportes();
			MenuBean menuUsuarios  	= this.generarMenuUsuarios();

			request.setAttribute("menuProcesos",menuProcesos);
			request.setAttribute("menuMonitoreo",menuMonitoreo);
			request.setAttribute("menuReportes",menuReportes);
			request.setAttribute("menuUsuarios",menuUsuarios);

			response.setContentType("text/html");
			request.getRequestDispatcher("vista/contenidoPrincipal.jsp")
				.forward(request,response);
		}
		else{
			try(PrintWriter out = response.getWriter()){
				response.setContentType("application/json");
				out.write(
					"{\"error\":\"Usuario con contraseña inválida\"}"
				);
			}
		}


	}
	
	private MenuBean generarMenuProcesos(){

		//Menu de procesos
		MenuBean menuProcesos = new MenuBean("Procesos");
		String[][] opciones1 = {
			{"Generador en lotes"},
			{"invocarContenido(\"procesos/lotes/generador.jsp\")"}
		};
		
		String[][] opciones2 = {
			{"Resumen Diario"
			,"Estado de Resumen Diario"
			,"Estado de Resumen de Bajas"},
			
			{"invocarContenido(\"procesos/resumen/resumenDiario.jsp\")",
			"invocarContenido(\"procesos/resumen/estadoResumenDiario.jsp\")",
			"invocarContenido(\"procesos/resumen/estadoResumenBajas.jsp\")"}
		};

		String[][] opciones3 = {
			{"Anular Documento",
			"Anular Documento (Error del sistema)"},

			{"invocarContenido(\"procesos/bajas/anular.jsp\")",
			"invocarContenido(\"procesos/bajas/anularError.jsp\")"}
		};

		String[][] opciones4 = {
			{"Reenvío de Documentos"},
			{"invocarContenido(\"procesos/emergencia/reenvio.jsp\")"}
		};

		menuProcesos.put("Lotes",opciones1);
		menuProcesos.put("Resumen",opciones2);
		menuProcesos.put("Bajas",opciones3);
		menuProcesos.put("Emergencia",opciones4);
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
		String[][] opciones3 = {
			{"Perfil","Salir"},
			{"invocarContenido(\"usuarios/gestion/modificarUsuario.jsp\")","loggout()"}
		};


		menuUsuarios.put("Gestión de Usuarios",opciones1);
		menuUsuarios.put("Configuraciones",opciones2);
		menuUsuarios.put("",opciones3);

		return menuUsuarios;
	}
}