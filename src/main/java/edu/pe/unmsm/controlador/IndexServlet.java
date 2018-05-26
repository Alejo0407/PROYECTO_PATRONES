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
public class IndexServlet extends HttpServlet{

	public void doPost(HttpServletRequest request, 
		HttpServletResponse response) throws ServletException,IOException{

		request.setAttribute("user",request.getParameter("user"));
		request.setAttribute("pass",request.getParameter("pass"));
		request.getRequestDispatcher("vista/contenidoPrincipal.jsp").forward(request,response);
	}

}