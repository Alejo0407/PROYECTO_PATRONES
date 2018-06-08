<!-- Unused
<%@ page language='java' contentType='text/html; charset=UTF-8'
    pageEncoding='UTF-8'%>
<%@ page import =  "java.util.ArrayList" %>
<% 
ArrayList<String> cabeceras = (ArrayList<String>)request.getAttribute("cabeceras");
ArrayList<String[]> detalles = (ArrayList<String[]>)request.getAttribute("detalles");
ArrayList<String[]> rutas = (ArrayList<String[]>)request.getAttribute("rutas");
%>
<div id = "accordion">
	<%
	for(int i = 0 ; i < cabeceras.size() ; i++){
	%>
	<div class='card'>
	    <div class='card-header bg-dark text-light' id='headingOne'>
	      <h5 class='mb-0'>
	        <button class='btn bg-dark text-light btn-block text-left' 
	        	data-toggle='collapse' data-target='#<%=(cabeceras.get(i).toLowerCase()+"_"+i)%>' 
	        	aria-expanded='true' aria-controls='collapseOne'>
	          <%=cabeceras.get(i)%>
	        </button>
	      </h5>
	    </div>
	    <div id='<%=(cabeceras.get(i).toLowerCase()+"_"+i)%>' 
	    	class='collapse' aria-labelledby='headingOne' data-parent='#accordion'>
      		<div class='card-body'>
      			<nav class='nav flex-column'>
      			<%
      			String[] opciones = detalles.get(i);
      			String[] r = rutas.get(i);
      			System.out.println("Entro aqui... " + i);
      			for(int j = 0 ; j < opciones.length ; j++){
      			%>
      				<a class='nav-link' href='#' 
      				onclick='invocarContenido("<%= ( "vista/" + r[j] )%>")'>
      					<%=opciones[j]%>	
      				</a>
      			<%
      			}
      			%>
      			</nav>
      		</div>
    	</div>
  	</div>
	<%
	}
	%>

</div>
-->