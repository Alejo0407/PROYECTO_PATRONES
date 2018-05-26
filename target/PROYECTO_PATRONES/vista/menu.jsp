<%@ page language='java' contentType='text/html; charset=UTF-8'
    pageEncoding='UTF-8'%>
<%@ page import =  "java.util.HashMap" %>
<% HashMap<String,String[]> datosMenu = (HashMap<String,String[]>)request.getAttribute("datosMenu");%>
<div id = "accordion">
	<%
	int i=0;
	for(String key:datosMenu.keySet()){
	i++;
	%>
	<div class='card'>
	    <div class='card-header bg-dark text-light' id='headingOne'>
	      <h5 class='mb-0'>
	        <button class='btn bg-dark text-light btn-block text-left' 
	        	data-toggle='collapse' data-target='#<%=(key.toLowerCase()+"_"+i)%>' 
	        	aria-expanded='true' aria-controls='collapseOne'>
	          <%=key%>
	        </button>
	      </h5>
	    </div>
	    <div id='<%=(key.toLowerCase()+"_"+i)%>' 
	    	class='collapse' aria-labelledby='headingOne' data-parent='#accordion'>
      		<div class='card-body'>
      			<nav class='nav flex-column'>
      			<%
      			String[] opciones = datosMenu.get(key);
      			for(String opcion:opciones){
      			%>
      				<a class='nav-link' href='#'><%=opcion%></a>
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