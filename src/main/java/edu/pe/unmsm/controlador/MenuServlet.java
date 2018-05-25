package edu.pe.unmsm.controlador;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.IOException;


@WebServlet(name = "MenuController", urlPatterns= {"/MenuController"})
public class MenuServlet extends HttpServlet{

	public void doPost(HttpServletRequest request, 
		HttpServletResponse response) throws ServletException,IOException{

		String pageName = request.getParameter("menu");
		String folder = request.getParameter("folder");
		
		if(pageName == null || folder == null){
			throw new NullPointerException("error");
		}
		pageName = "vista/"+folder+"/"+pageName+".jsp";
		request.getRequestDispatcher(pageName).forward(request,response);

	}

}