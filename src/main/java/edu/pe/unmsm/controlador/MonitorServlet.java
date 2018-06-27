package edu.pe.unmsm.controlador;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.pe.unmsm.controlador.beans.TablaMonitoreoBO;
import edu.pe.unmsm.modelo.Programa;
import edu.pe.unmsm.modelo.dao.beans.DocumentoBean;


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
		case "download":
			download(request,response);
			break;
		}
	}
	
	private void download(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		

		String transaccion = request.getParameter("transaccion");
		String content = request.getParameter("content");
		
		try {
			Programa p = new Programa();
			DocumentoBean doc = p.getDocumento(transaccion);
			p.closeResources();
			
			Logger.getGlobal().log(Level.INFO, doc.toString());
			
			try(ServletOutputStream out = response.getOutputStream()){
				InputStream in;
				String filename;
				String mime;
				if(content.equals("xml")) {
					in = doc.getArchivo().getBinaryStream();
					filename = doc.getNombreArchivo();

					Logger.getGlobal().log(Level.INFO, filename);
					
					mime = "text/xml";
				}
				else if( content.equals("sunat")) {
					in = doc.getRespuestaSunat().getBinaryStream();	
					filename = doc.getNombreRespuestaSunat();

					Logger.getGlobal().log(Level.INFO, filename);
					
					mime = "application/zip";
				}
				else {
					try(PrintWriter o = response.getWriter()){
						response.setContentType("application/json");
						o.write("{\"error\":\"Opción no soportada\"}");
						return;
					}
				}
				
				 byte[] buffer = new byte[4096];
				 int length;
				 while ((length = in.read(buffer)) > 0){
					 out.write(buffer, 0, length);
				 }
				 response.addHeader("Content-Disposition", "attachment; filename="+filename);
				 response.setHeader("Content-Length", Integer.toString(length));
				 response.addHeader("nombre", filename);
				 response.addHeader("mime", mime);
			     response.setContentType(mime);  
			     in.close();
			}
			
			
		}catch(Exception e) {
			try(PrintWriter out = response.getWriter()){
				response.setContentType("application/json");
				out.write("{\"error\":\""+e.getMessage()+"\"}");
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		
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
