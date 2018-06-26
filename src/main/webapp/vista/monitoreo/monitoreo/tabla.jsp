<%@ page language='java' contentType='text/html; charset=UTF-8'
    pageEncoding='UTF-8'%>
<%@ page import = "edu.pe.unmsm.controlador.beans.TablaMonitoreoBean" %>
<%
	
	TablaMonitoreoBean tabla = (TablaMonitoreoBean)request.getAttribute("documentos");	
%> 

<table class = 'table table-hover table-bordered'>
  <thead class="thead-dark">
  	<tr>
  		<th scope="col">Numeración<br>Electrónica</th>
  		<th scope="col">Cliente</th>
  		<th scope="col">Monto</th>
  		<th scope="col">Numeración<br>Original</th>
  		<th scope="col">Fecha</th>
  		<th scope="col">Estado</th>
  	</tr>
  </thead>
  <tbody>
  <%
  	for(String[] d : tabla.getDatos()){
  	%>
  	<tr>
  		<td><%=d[0]%></td>
  		<td><%=d[1]%> </td>
  		<td><%=d[2]%></td>
  		<td><%=d[3]%></td>
  		<td><%=d[4]%></td>
  		<td><%=d[5]%></td>
  	</tr>
  	<% 
  	}
  	
  %>
  </tbody>
</table>
   