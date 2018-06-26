<%@ page language='java' contentType='text/html; charset=UTF-8'
    pageEncoding='UTF-8'%>


<%@ page import =  "edu.pe.unmsm.controlador.beans.MenuBean" %>
<%@ page import =  "edu.pe.unmsm.controlador.beans.MenuBO" %>
<%@ page import =  "java.util.List" %>

<%
if(request.getSession().getAttribute("usr") == null)
	request.getRequestDispatcher("index.jsp").forward(request,response);

MenuBO menuGenerator = (MenuBO)request.getSession().getAttribute("menu");
List<MenuBean> menus = menuGenerator.getMenus();
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
    		System.out.println(menu.getId());
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
            	<%
            	if(!menu.getCabeceras().get(k).trim().isEmpty()){
            	%>
            	<h6 class="dropdown-header"><%=menu.getCabeceras().get(k)%></h6>
            	<%
            	}
            	String[][] valor = menu.getContenido().get(k);
            	for(int i = 0 ; i < valor[0].length ; i++){
            	%>
	            		<a class='dropdown-item' href='#' 
            			onclick='<%=valor[1][i]%>'>
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
    	</ul>
	</div>
</nav>
		
<div class='container' id = "contenido-principal" style = 'margin-top: 2px; padding-left: 2px;'>
	
</div>