package edu.pe.unmsm.controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.pe.unmsm.controlador.beans.TablaMonitoreoBO;


@WebServlet(name = "MonitorController", urlPatterns= {"/MonitorController"})
public class MonitorServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -729077424678962244L;

	public void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException,IOException{
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//Siempre va para los caracteres especiales
		response.setCharacterEncoding("UTF-8");
		verificarUsuario(request, response);
		
		response.setCharacterEncoding("UTF-8");
		//VERIFICAMOS USUARIO
		if( !verificarUsuario(request,response) )
			return;
		
		String action = (String)request.getParameter("action");
		
		switch(action) {
		
		case "getDatosTabla":
			getDatosTabla(request,response);
			break;
			
		}
	}
	
	private void getDatosTabla(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		try {
			String date = request.getParameter("fecha");
			TablaMonitoreoBO d = new TablaMonitoreoBO(date);
			
			response.setContentType("text/html");
			request.setAttribute("documentos", d.getTabla());
			request.getRequestDispatcher("vista/monitoreo/monitoreo/tabla.jsp").forward(request, response);
		}
		catch(Exception e) {
			try(PrintWriter out = response.getWriter()){
				response.setContentType("application/json");
				out.write("{\"error\":\""+e.getMessage()+"\"}");
				e.printStackTrace();
			}
		}
		
	}

	private boolean verificarUsuario(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		if ( request.getSession().getAttribute("usr") == null ) {
			request.getSession().invalidate();
			try(PrintWriter out = response.getWriter()){
				response.setContentType("application/json");
				out.write("{\"error\":\"Sesion terminada\"}");
			}
			return false;
		}
		else
			return true;
		
	}
}
