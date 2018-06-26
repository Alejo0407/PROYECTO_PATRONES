package edu.pe.unmsm.controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.pe.unmsm.controlador.beans.TablaProcesosBO;

@WebServlet(name = "ProcesosController", urlPatterns= {"/ProcesosController"})
public class ProcesosServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException,IOException{
		
		//request.getRequestDispatcher("index.jsp").forward(request, response);
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
		case "migrar":
			migrar(request,response);
			break;
		case "generar":
			generar(request,response);
			break;
		case "descarga":
			descargar(request,response);
			break;		
		}
	}
	
	
	private void migrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String fecha = (String) request.getParameter("fecha");
			boolean facturas = Boolean.valueOf(request.getParameter("facturas"));
			boolean boletas = Boolean.valueOf(request.getParameter("boletas"));
			boolean corregido= Boolean.valueOf(request.getParameter("corregido"));
			
			TablaProcesosBO bo = new TablaProcesosBO(facturas,boletas,corregido,fecha);
			
			request.getSession().setAttribute("rGenerador", bo.getTablaMigraciones());
			response.setContentType("text/html");
			request.setAttribute("descarga", new Boolean(false));
			request.getRequestDispatcher("vista/procesos/lotes/tabla.jsp")
				.forward(request, response);;
		}catch(Exception e) {
			
			try(PrintWriter out = response.getWriter()){
				response.setContentType("application/jason");
				out.write("{\"error\":\""+e.getMessage()+"\"}");
				e.printStackTrace();
			}
		}
	}

	private void generar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		try {
			String fecha = request.getParameter("fecha");
			boolean facturas = Boolean.valueOf(request.getParameter("facturas"));
			boolean boletas = Boolean.valueOf(request.getParameter("boletas"));
			boolean corregido= Boolean.valueOf(request.getParameter("corregido"));
			
			TablaProcesosBO bo = new TablaProcesosBO(facturas,boletas,corregido,fecha);
			
			request.getSession().setAttribute("rGenerador", bo.getTablaGeneracion());
			response.setContentType("text/html");
			request.setAttribute("descarga", new Boolean(true));
			request.getRequestDispatcher("vista/procesos/lotes/tabla.jsp")
				.forward(request, response);;
		}catch(Exception e) {
			
			try(PrintWriter out = response.getWriter()){
				response.setContentType("application/jason");
				out.write("{\"error\":\""+e.getMessage()+"\"}");
				e.printStackTrace();
			}
		}
	}

	private void descargar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
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
