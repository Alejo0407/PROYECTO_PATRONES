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
  		<th scope="col">xml</th>
  		<th scope="col">sunat</th>
  	</tr>
  </thead>
  <tbody>
  <%
  	for(int i = 0 ; i < tabla.getDatos().size() ; i++){
  		String[] d = tabla.getDatos().get(i);
  	%>
  	<tr>
  		<td><%=d[0]%></td>
  		<td><%=d[1]%> </td>
  		<td><%=d[2]%></td>
  		<td><%=d[3]%></td>
  		<td><%=d[4]%></td>
  		<td><%=d[5]%></td>
  		<td><button onclick='mnDownloadXml("<%=tabla.getTransacciones().get(i)%>","xml")' type="button" 
				class="btn btn-link">xml</button></td>
		<td><button onclick='mnDownloadSunat("<%=tabla.getTransacciones().get(i)%>","sunat")' type="button" 
				class="btn btn-link">sunat</button></td>
  	</tr>
  	<% 
  	}
  	
  %>
  </tbody>
</table>
   