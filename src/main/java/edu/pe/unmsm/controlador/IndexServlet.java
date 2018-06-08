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


			request.setAttribute("menuProcesos",menuProcesos);
			request.setAttribute("menuMonitoreo",menuMonitoreo);
			request.setAttribute("menuReportes",menuReportes);

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
			{"procesos/lotes/generador.jsp"}
		};
		
		String[][] opciones2 = {
			{"Resumen Diario"
			,"Estado de Resumen Diario"
			,"Estado de Resumen de Bajas"},
			
			{"procesos/resumen/resumenDiario.jsp",
			"procesos/resumen/estadoResumenDiario.jsp",
			"procesos/resumen/estadoResumenBajas.jsp"}
		};

		String[][] opciones3 = {
			{"Anular Documento",
			"Anular Documento (Error del sistema)"},

			{"procesos/bajas/anular.jsp",
			"procesos/bajas/anularError.jsp"}
		};

		String[][] opciones4 = {
			{"Reenvío de Documentos"},
			{"procesos/emergencia/reenvio.jsp"}
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
			{"monitoreo/monitoreo/monitoreo.jsp"}
		};
		menuMonitoreo.put("Visualizar",opciones);

		return menuMonitoreo;
	}
	
	private MenuBean generarMenuReportes(){

		//Menu de procesos
		MenuBean menuReportes = new MenuBean("Reportes");
		String[][] opciones = {
			{"Resumen de Ventas"},
			{"resumen/ventas/resumenVentas.jsp"}
		};
		menuReportes.put("Ventas",opciones);

		return menuReportes;
	}
}