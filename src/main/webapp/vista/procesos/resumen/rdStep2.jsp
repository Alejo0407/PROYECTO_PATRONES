<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>

<%@ page import = "edu.pe.unmsm.controlador.beans.TablaProcesosBean" %>
<%
	TablaProcesosBean tabla = (TablaProcesosBean) request.getAttribute("tabla");
%>
<table class = 'table table-hover table-bordered'>
	<thead class="thead-dark">
		<tr>
		<%
		for(String head : tabla.getHeaders()){
		%>
			<th><%=head %></th>	
		<%
		} 
		%>
		</tr>
	</thead>
	
	<tbody>
	<%
	for(String transaccion : tabla.getTransacciones()){
		%>
		<tr>
		<%
		for(String contenido : tabla.getContent().get(transaccion)){
			%>
			<td><%=contenido%></td>
		<%	
		}
		%>
		<tr>
		<% 	
	}
	%>
	</tbody>
</table>