package edu.pe.unmsm.controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.pe.unmsm.modelo.Programa;
import edu.pe.unmsm.modelo.dao.beans.UsuarioBean;

@WebServlet(name = "UsuarioController", urlPatterns= {"/UsuarioController"})
public class UsuariosServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		if(!verificarUsuario(request,response) )
			return;	
		
		String action = (String)request.getParameter("action");
		
		switch(action) {
		
		case "crearUsuario":
			crearUsuario(request,response);
			break;
		case "modificarUsuario":
			modificarUsuario(request,response);
			break;
		case "eliminarUsuario":
			eliminarUsuario(request,response);
			break;
		case "modificarUsuarioTabla":
			modificarUsuarioTabla(request,response);
			break;
		}
	}
	
	private void modificarUsuarioTabla(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		try {
			String x = request.getParameter("id");
			
			Programa p = new Programa();
			
			UsuarioBean usuario = p.getUsuario(x);
			p.closeResources();
			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher("vista/usuarios/gestion/modificarUsuario.jsp").forward(request, response);
			
		}catch(Exception e) {
			try(PrintWriter out = response.getWriter()){
				response.setContentType("application/json");
				out.write("{\"error\":\""+e.getMessage().replace("\"", "'")+"\"}");
				e.printStackTrace();
			}
		}
	}

	private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String x = request.getParameter("id");
			Programa p = new Programa();
			int rango = p.listUsuarios().stream()
			.filter( temp -> temp.getId().equals(x))
			.findFirst()
			.get().getRango();
			
			p.deleteUsuario(x,rango);
			
			request.setAttribute("usuarios", p.listUsuarios());
			p.closeResources();
			request.getRequestDispatcher("vista/usuarios/gestion/listarUsuario.jsp").forward(request, response);
			
		}catch(Exception e) {
			try(PrintWriter out = response.getWriter()){
				response.setContentType("application/json");
				out.write("{\"error\":\""+e.getMessage().replace("\"", "'")+"\"}");
				e.printStackTrace();
			}
		}
		
	}

	private void modificarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		try {
			String idUser = request.getParameter("idUser");
			String Pass = request.getParameter("Pass");
			String Correo = request.getParameter("Correo");
			String Nombre = request.getParameter("Nombre");
			String Apellido = request.getParameter("Apellido");
			int Rango = Integer.parseInt(request.getParameter("Rango"));
			
			Programa p = new Programa();
			UsuarioBean d = p.getUsuarioVacio();
			d.setId(idUser);
			d.setPass(Pass);
			d.setCorreo(Correo);
			d.setNombres(Nombre);
			d.setApellidos(Apellido);
			d.setRango(Rango);
			
			response.setContentType("text/html");
			
			//request.setAttribute("usuario", d);
			Programa pr = new Programa();
			pr.updateUsuario(d);
			
			request.setAttribute("usuarios", p.listUsuarios());
			p.closeResources();
			request.getRequestDispatcher("vista/usuarios/gestion/listarUsuario.jsp").forward(request, response);
		}
		catch(Exception e) {
			try(PrintWriter out = response.getWriter()){
				response.setContentType("application/json");
				out.write("{\"error\":\""+e.getMessage().replace("\"", "'")+"\"}");
				e.printStackTrace();
			}
		}
	}

	private void crearUsuario(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		try {
			String idUser = request.getParameter("idUser");
			String Pass = request.getParameter("Pass");
			String Correo = request.getParameter("Correo");
			String Nombre = request.getParameter("Nombre");
			String Apellido = request.getParameter("Apellido");
			int Rango = Integer.parseInt(request.getParameter("Rango"));
			
			Programa p = new Programa();
			UsuarioBean d = p.getUsuarioVacio();
			d.setId(idUser);
			d.setPass(Pass);
			d.setCorreo(Correo);
			d.setNombres(Nombre);
			d.setApellidos(Apellido);
			d.setRango(Rango);
			
			response.setContentType("text/html");
			
			//request.setAttribute("usuario", d);
			Programa pr = new Programa();
			pr.addUsuario(d);
			
			request.setAttribute("usuarios", p.listUsuarios());
			p.closeResources();
			request.getRequestDispatcher("vista/usuarios/gestion/listarUsuario.jsp").forward(request, response);
		}
		catch(Exception e) {
			try(PrintWriter out = response.getWriter()){
				response.setContentType("application/json");
				out.write("{\"error\":\""+e.getMessage().replace("\"", "'")+"\"}");
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
