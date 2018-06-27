package edu.pe.unmsm.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Base64;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.pe.unmsm.controlador.beans.MenuBO;
import edu.pe.unmsm.modelo.Programa;
import edu.pe.unmsm.modelo.dao.beans.UsuarioBean;


@WebServlet(name = "IndexController", urlPatterns= {"/IndexController"})
public class IndexServlet extends HttpServlet{
	
	private Programa p;
	private UsuarioBean usr;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8147367836198537361L;
	
	public void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException,IOException{
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, 
		HttpServletResponse response) throws ServletException,IOException{

		//Siempre va para los caracteres especiales
		response.setCharacterEncoding("UTF-8");
		
		String action = (String)request.getParameter("action");
		
		if(action == null)
			action = "loggin";
		
		
		switch(action) {
		case "load":
			this.actionLoad(request, response);
			break;
		case "loggin":
			this.actionLoggin(request, response);
			break;
		case "loggout":
			actionLoggout(request,response);
			break;
		}
	
	}
	private void actionLoggout(HttpServletRequest request, 
			HttpServletResponse response) {
		request.getSession().invalidate();
	}
	
	private void actionLoad(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		
		response.setContentType("text/html");
		/*try(PrintWriter out = response.getWriter()){
			out.println("<h1>llamo al action load</h1>");
		}*/
		
		
		if(request.getSession().getAttribute("usr") != null) 
			request.getRequestDispatcher("vista/content.jsp").forward(request, response);
		else
			request.getRequestDispatcher("vista/loggin.jsp").forward(request, response);
		
	}
	
	private void actionLoggin(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
			try {
				if( this.validateLoggin(request, response) ) {
					
					response.setContentType("text/html");
					MenuBO menu = new MenuBO( this.usr.getRango() );
					request.getSession().setAttribute("menu", menu);
					request.setAttribute("m", menu.loadMenu());
					
					request.getRequestDispatcher("vista/content.jsp").forward(request, response);
				}
				else {
					try(PrintWriter out = response.getWriter()){
						response.setContentType("application/json");
						out.write(
							"{\"error\":\"Usuario con contraseña inválida\"}"
						);
					}
				}
			} catch (SQLException | NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try(PrintWriter out = response.getWriter()){
					response.setContentType("application/json");
					out.write(
						"{\"error\":\""+e.getMessage()+"\"}"
					);
				}
			}
	}
	
	
	private boolean validateLoggin(HttpServletRequest request, 
			HttpServletResponse response) throws SQLException, NamingException, UnsupportedEncodingException {
		
		//if(request.getSession().getAttribute("usr") == null) {
			//LOGGIN
			String user = (String)request.getParameter("username");
			String pass = (String)request.getParameter("password");
			
			
			if(user == null || pass == null)
				return false;
			else if (user.isEmpty() || pass.isEmpty())
				return false;
			else {
				p = new Programa();
				this.usr = p.getUsuario(user);
				
				p.closeResources();
				
				if(usr == null)
					return false;
				else {
					
					String decoded = new String(Base64.getDecoder().decode(this.usr.getPass()),"UTF-8");
					boolean b = (pass.equals(decoded));
					if(b) request.getSession().setAttribute("usr", this.usr);
					return b;
				}
			}
		//}
		//return false;
		
	}

}
	