<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ page import = "edu.pe.unmsm.controlador.beans.TablaProcesosBean" %>

<%
	Boolean descarga = (Boolean) request.getAttribute("descarga");
	TablaProcesosBean tabla = (TablaProcesosBean) session.getAttribute("rGenerador");
	
	if(tabla.getTransacciones().isEmpty()){
		%>
		<div class="mx-auto" style="width: 200px;">
		  No Hay elementos
		</div>
		<%
	}
	else{
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
		if(descarga.booleanValue()){
		%>
			<th>Xml</th>
			<th>Sunat</th>
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
		if(descarga.booleanValue()){
			%>
			<td><button onclick='prLotesDownload("<%=transaccion %>","xml")' type="button" 
				class="btn btn-link">xml</button></td>
			<td><button onclick='prLotesDownload("<%=transaccion %>","sunat")' type="button" 
				class="btn btn-link">sunat</button></td>
			<%
		}
		%>
		<tr>
		<% 	
	}
	%>
	</tbody>
</table>
<%
}
%>