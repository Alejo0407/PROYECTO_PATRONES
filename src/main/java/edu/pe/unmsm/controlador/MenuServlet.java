package edu.pe.unmsm.controlador;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;

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
		HashMap<String,String[]> menu = new HashMap<>();
		if(pageName.equalsIgnoreCase("procesos")){

			String[] op4 = {"Reenvío de Documentos"};
			menu.put("Emergencia",op4);

			String[] op3 = {"Anular Documento","Anular Documento (Error del sistema)"};
			menu.put("Bajas",op3);

			String[] op2 = {"Resumen Diario","Estado de Resumen Diario","Estado de Resumen de Bajas"};
			menu.put("Diarios",op2);

			String[] op1 = {"Generador en lotes"};
			menu.put("Lotes",op1);

		}
		else if(pageName.equalsIgnoreCase("monitoreo")){
			String[] op1 = {"Monitoreo de documentos"};
			menu.put("Monitoreo",op1);
		}
		else if(pageName.equalsIgnoreCase("reportes")){
			String[] op1 = {"Resumen de Ventas"};
			menu.put("Ventas",op1);
		}

		System.gc();

		request.setAttribute("datosMenu",menu);
		request.getRequestDispatcher("vista/menu.jsp").forward(request,response);

	}

}