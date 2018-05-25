<%@ page language='java' contentType='text/html; charset=UTF-8'
    pageEncoding='UTF-8'%>
<!-- Barra de navegación ver 0.1 configurada-->
<nav class='navbar navbar-expand-lg navbar-dark bg-dark' id = 'navPanel'>
	<a class='navbar-brand' href='#'>Facturación Electrónica</a>
    <button class='navbar-toggler' type='button' data-toggle='collapse' 
    	data-target='#navbarNav' aria-controls='navbarNav' aria-expanded='false' 
        aria-label='Toggle navigation'>
        <span class='navbar-toggler-icon'></span>
	</button>
    <div class='collapse navbar-collapse justify-content-end' id='navbarNav'>
    	<ul class='navbar-nav '>
	        <li class='nav-item'><a class='nav-link' href='#' onclick='actionHome('#leftMenu')'>Procesos</a></li>
	        <li class='nav-item'><a class='nav-link' href='#'>Monitoreo</a></li>
	        <li class='nav-item'><a class='nav-link' href='#'>Reportes</a></li>
	        <li class='nav-item dropdown'>
	        	<a  class='nav-link dropdown-toggle' href='#' id='navbarDropdownMenuLink'
	            data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>
	            Usuario
	            </a>
	            <div class='dropdown-menu dropdown-menu-right' aria-labelledby='navbarDropdownMenuLink'>
		            <a class='dropdown-item' href='#'>Perfil</a>
		            <a class='dropdown-item' href='#'>Configuración</a>
		            <a class='dropdown-item' href='#' onclick='loggout()'>Salir</a>
	            </div>
	        </li>
    	</ul>
	</div>
</nav>

<!-- Menu principal-->
<%@ include file='menu.jsp' %>  