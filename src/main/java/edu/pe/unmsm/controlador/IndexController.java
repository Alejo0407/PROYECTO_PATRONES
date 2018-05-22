package edu.pe.unmsm.controlador;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.IOException;


@WebServlet(name = "IndexController", urlPatterns= {"/IndexController"})
public class IndexController extends HttpServlet{

	public void doGet(HttpServletRequest request, 
		HttpServletResponse response) throws ServletException,IOException{

		//String someText = "Esto es una prueba";
		HttpSession session = request.getSession();
		session.setAttribute("mensaje","Esta es una llamada al include.jsp");

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		try(PrintWriter out = response.getWriter()){
			out.print("vista/include.jsp");
		}
	}

}