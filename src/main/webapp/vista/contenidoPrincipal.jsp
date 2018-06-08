<%@ page language='java' contentType='text/html; charset=UTF-8'
    pageEncoding='UTF-8'%>
<%@ page import =  "edu.pe.unmsm.controlador.beans.MenuBean" %>
<%@ page import =  "java.util.List" %>

<%
System.out.println("Esto es una prueba");
MenuBean[] menus = new MenuBean[3];
menus[0] 	= (MenuBean)request.getAttribute("menuProcesos");
menus[1] 	= (MenuBean)request.getAttribute("menuMonitoreo");
menus[2] 	= (MenuBean)request.getAttribute("menuReportes");
%>

<nav class='navbar navbar-expand-lg navbar-dark bg-dark' id = 'navPanel'>
	<a class='navbar-brand' href='#'>Facturación Electrónica</a>
    <button class='navbar-toggler' type='button' data-toggle='collapse' 
    	data-target='#navbarNav' aria-controls='navbarNav' aria-expanded='false' 
        aria-label='Toggle navigation'>
        <span class='navbar-toggler-icon'></span>
	</button>
    <div class='collapse navbar-collapse justify-content-end' id='navbarNav'>
    	<ul class='navbar-nav '>
    	<%
    	for(MenuBean menu:menus){
    	%>
		<li class='nav-item dropdown'>
			<a  class='nav-link dropdown-toggle' href='#' id='navbarDropdownMenuLink'
            data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>
            <%=menu.getId()%>
            </a>
            <div class='dropdown-menu dropdown-menu-right' aria-labelledby='navbarDropdownMenuLink'>
            <%
            for(int k = 0 ; k < menu.getCabeceras().size() ; k++){
            %>
            	<h6 class="dropdown-header"><%=menu.getCabeceras().get(k)%></h6>
            	<%
            	String[][] valor = menu.getContenido().get(k);
            	for(int i = 0 ; i < valor[0].length ; i++){
            	%>
            		<a class='dropdown-item' href='#' 
            			onclick='invocarContenido("<%=valor[1][i]%>")'>
            			<%=valor[0][i]%>		
            		</a>
            	<%
            	}
            	if(k < menu.getCabeceras().size() - 1 ){
            	%>
            		<div class="dropdown-divider"></div>
            	<%
            	}
        	}
        	%>
        	</div>
        </li>
        <%
    	}
    	%>
    	<li class='nav-item dropdown'>
	        	<a  class='nav-link dropdown-toggle' href='#' id='navbarDropdownMenuLink'
	            data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>
	            Usuario
	            </a>
	            <div class='dropdown-menu dropdown-menu-right' aria-labelledby='navbarDropdownMenuLink'>
		            <a class='dropdown-item' href='#' >Perfil</a>
		            <a class='dropdown-item' href='#' >Configuración</a>
		            <a class='dropdown-item' href='#' onclick='loggout()'>Salir</a>
	            </div>
	        </li>
   		</ul>
	</div>
</nav>

<div class='container-fluid' id = "menuPanel" style = 'margin-top: 2px; padding-left: 2px;'>
	<div class='row'>
		<div class='col-lg-2' id ='leftMenu'>

    	</div>
		<div class='col-lg-10' id = 'mainContent'>
        
    	</div>
	</div>
</div>