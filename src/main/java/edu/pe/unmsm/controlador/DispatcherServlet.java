package edu.pe.unmsm.controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "DispatcherController", urlPatterns= {"/DispatcherController"})
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 3564316228693168974L;
	
	
	public void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException,IOException{	
		response.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, 
		HttpServletResponse response) throws ServletException,IOException{
		
		response.setCharacterEncoding("UTF-8");
		//VERIFICAMOS USUARIO
		if( verificarUsuario(request,response) ) {
			
			//REALIZAR EL DISPATCH
			String vista = (String) request.getParameter("vista");
			
			response.setContentType("text/html");
			System.out.println("vista/"+vista);
			request.getRequestDispatcher("vista/"+vista).forward(request, response);
		
		}
		else {
			try(PrintWriter out = response.getWriter()){
				response.setContentType("application/json");
				out.write("{\"error\":\"Sesion terminada\"}");
			}
		}
		
	}
	
	private boolean verificarUsuario(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		if ( request.getSession().getAttribute("usr") == null ) {
			request.getSession().invalidate();
			return false;
		}
		else
			return true;
		
	}
	
	
}
