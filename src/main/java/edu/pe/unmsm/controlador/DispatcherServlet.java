package edu.pe.unmsm.controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.pe.unmsm.modelo.Programa;


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
			
			String action = request.getParameter("action");
			
			switch(action) {
			case "simpleLoad":
				simpleLoad(request,response);
				break;
			case "getSistema":
				getSistema(request,response);
				break;
			case "getEmpresa":
				getEmpresa(request,response);
				break;
			case "getUsuarios":
				getUsuarios(request,response);
				break;
			}
		}
		else {
			try(PrintWriter out = response.getWriter()){
				response.setContentType("application/json");
				out.write("{\"error\":\"Sesion terminada\"}");
			}
		}
		
	}
	
	private void getUsuarios(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		try {
			Programa p = new Programa();
			request.setAttribute("usuarios", p.listUsuarios());
			simpleLoad(request,response);
		}catch(Exception e) {
			try(PrintWriter out = response.getWriter()){
				response.setContentType("application/json");
				out.write("{\"error\":\""+e.getMessage()+"\"}");
				e.printStackTrace();
			}
		}
	}

	private void simpleLoad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//REALIZAR EL DISPATCH
		String vista = (String) request.getParameter("vista");
		
		response.setContentType("text/html");
		System.out.println("vista/"+vista);
		request.getRequestDispatcher("vista/"+vista).forward(request, response);
	}

	private void getSistema(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Programa p = new Programa();
			request.setAttribute("sistema", p.getSistema());
			
			simpleLoad(request,response);
		}catch(Exception e) {
			try(PrintWriter out = response.getWriter()){
				response.setContentType("application/json");
				out.write("{\"error\":\""+e.getMessage()+"\"}");
				e.printStackTrace();
			}
		}
	}

	private void getEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Programa p = new Programa();
			request.setAttribute("empresa", p.getDatosEmpresa());
			
			simpleLoad(request,response);
		}catch(Exception e) {
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
			return false;
		}
		else
			return true;
		
	}
	
	
}
