<%@ page language='java' contentType='text/html; charset=UTF-8'
pageEncoding='UTF-8'%>
<%@ page import = "java.util.List" %>
<%@ page import = "edu.pe.unmsm.modelo.dao.beans.UsuarioBean" %>

<%
	List<UsuarioBean> usuarios = (List<UsuarioBean>)request.getAttribute("usuarios");
%>

<div class="row">
    <table class="table m-2">
        <thead class="thead-dark">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Nombre</th>
            <th scope="col">Apellido</th>
            <th scope="col" class="text-center">Opciones</th>
        </tr>
        </thead>
        <tbody>
        <%for(UsuarioBean usuario : usuarios) {
        %>
       		<tr>
                <th scope="row"><%= usuario.getId() %></th>
                <td><%=usuario.getNombres() %></td>
                <td><%=usuario.getApellidos() %></td>
                <td class="text-center">
                    <div>
                        <button type="button" class="btn btn-info" 
                        	onclick = 'usEditar("<%=usuario.getId()%>")'>Editar</button>
                        <button type="button" class="btn btn-danger" 
                        	onclick = 'usBorrar("<%=usuario.getId()%>")'>Borrar</button>
                    </div>
                </td>
            </tr>
		<%} 
		%>
        </tbody>
    </table>
</div>
