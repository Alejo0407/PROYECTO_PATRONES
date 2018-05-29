package edu.pe.unmsm.controlador;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.PrintWriter;

@WebServlet(name = "MenuController", urlPatterns= {"/MenuController"})
public class MenuServlet extends HttpServlet{

	public void doPost(HttpServletRequest request, 
		HttpServletResponse response) throws ServletException,IOException{

		String pageName = request.getParameter("menu");
		if(pageName == null){
			throw new NullPointerException("error");
		}

		//La llave el mapa debe ser la cabecera de la función,
		//También se convertirá en los id de paneles
		//Debe ser de maximo una palabra
		ArrayList<String> cabeceras 	= new ArrayList<>();
		ArrayList<String[]> detalles 	= new ArrayList<>();
		ArrayList<String[]> rutas 		= new ArrayList<>();

		if(pageName.equalsIgnoreCase("procesos")){

			String[] op1 = {"Generador en lotes"};
			String[] r1 = {"procesos/lotes/generador.jsp"};
			cabeceras.add("Lotes");
			detalles.add(op1);
			rutas.add(r1);

			String[] op2 = {"Resumen Diario","Estado de Resumen Diario","Estado de Resumen de Bajas"};
			String[] r2 = {
				"procesos/resumen/resumenDiario.jsp",
				"procesos/resumen/estadoResumenDiario.jsp",
				"procesos/resumen/estadoResumenBajas.jsp"
			};
			cabeceras.add("Resumen");
			detalles.add(op2);
			rutas.add(r2);

			String[] op3 = {"Anular Documento","Anular Documento (Error del sistema)"};
			String[] r3 = {
				"procesos/bajas/anular.jsp",
				"procesos/bajas/anularError.jsp"
			};
			cabeceras.add("Bajas");
			detalles.add(op3);
			rutas.add(r3);

			String[] op4 = {"Reenvío de Documentos"};
			String[] r4 = {"procesos/emergencia/reenvio.jsp"};
			cabeceras.add("Emergencia");
			detalles.add(op4);
			rutas.add(r4);

		}
		else if(pageName.equalsIgnoreCase("monitoreo")){
			String[] op1 = {"Monitoreo de documentos"};
			String[] r1 = {"monitoreo/monitoreo/monitoreo.jsp"};
			cabeceras.add("Monitoreo");
			detalles.add(op1);
			rutas.add(r1);
		}
		else if(pageName.equalsIgnoreCase("reportes")){
			String[] op1 = {"Resumen de Ventas"};
			String[] r1 = {"resumen/ventas/resumenVentas.jsp"};
			cabeceras.add("Ventas");
			detalles.add(op1);
			rutas.add(r1);
		}
		else{
			try(PrintWriter out = response.getWriter()){
				out.write("{\"error\":\"Error en el procesamiento de la vista\"}");
				return;
			}
		}
		System.gc();
		request.setAttribute("cabeceras",cabeceras);
		request.setAttribute("detalles",detalles);
		request.setAttribute("rutas",rutas);
		request.getRequestDispatcher("vista/menu.jsp").forward(request,response);

	}

}