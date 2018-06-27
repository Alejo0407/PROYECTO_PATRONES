<%@ page language='java' contentType='text/html; charset=UTF-8'
pageEncoding='UTF-8'%>

<%@ page import = "edu.pe.unmsm.modelo.dao.beans.UsuarioBean" %>

<%
UsuarioBean usuario;
if(request.getAttribute("usuario") != null)
	usuario = (UsuarioBean)request.getAttribute("usuario");
else
	usuario = (UsuarioBean)session.getAttribute("usr");

String rango = (usuario.getRango() == 1 ? "Administrador":"Usuario de Ventas");

if(usuario.getApellidos() == null)usuario.setApellidos("");
if(usuario.getNombres() == null)usuario.setNombres("");
if(usuario.getCorreo() == null)usuario.setCorreo("");
%>

<div class="row">
    <div class="m-5">
        <div class="form-group row">
        	<label for="IdUser" class="col-sm-2 col-form-label">IdUsuario</label>
            <div class="col-sm-10">
            <input value ="<%=usuario.getId() %>" type="text" class="form-control" 
            	name="id_usuario" id="IdUser" placeholder="Id Usuario" readonly>
            </div>
        </div>
        <div class="form-group row">
        	<label for="Pass" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
            <input value = "<%=usuario.getPass() %>" type="text" class="form-control" 
            	name="password" id="Pass" placeholder="Password">
            </div>
        </div>
        <div class="form-group row">
            <label for="Correo" class="col-sm-2 col-form-label">Correo</label>
            <div class="col-sm-10">
                <input value = "<%=usuario.getCorreo() %>" type="email" class="form-control" 
                	name="correo" id="Correo" placeholder="Correo">
            </div>
        </div>
        <div class="form-group row">
            <label for="Nombre" class="col-sm-2 col-form-label">Nombre</label>
            <div class="col-sm-10">
            <input value = "<%=usuario.getNombres() %>" type="text" class="form-control" name="nombre" id="Nombre" placeholder="Nombre">
            </div>
        </div>
        <div class="form-group row">
            <label for="Apellido" class="col-sm-2 col-form-label">Apellido</label>
            <div class="col-sm-10">
            <input value = "<%=usuario.getApellidos() %>" type="text" class="form-control" name="apellido" id="Apellido" placeholder="Apellido">
            </div>
        </div>
        <div class="form-group row">
            <label for="rango" class="col-sm-2 col-form-label">Rango</label>
            <div class="col-sm-10">
            <input value = "<%=rango %>" type="text" class="form-control" name="apellido" id="rango" placeholder="rango" readonly>
            <input type="hidden" id="Rango" name="custId" value="<%=usuario.getRango()%>">
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-10">
            <button type="submit" class="btn btn-primary" onclick = 'usEnviar("modificarUsuario")'>Guardar</button>
            </div>
        </div>
    </div>
</div>