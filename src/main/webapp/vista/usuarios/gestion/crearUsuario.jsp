<%@ page language='java' contentType='text/html; charset=UTF-8'
pageEncoding='UTF-8'%>
<form action="#" class="m-5">
    <div class="form-group row">
        <label for="IdUser" class="col-sm-2 col-form-label">IdUsuario</label>
        <div class="col-sm-10">
        <input type="text" class="form-control" name="id_usuario" id="IdUser" placeholder="Id Usuario">
        </div>
    </div>
    <div class="form-group row">
        <label for="Pass" class="col-sm-2 col-form-label">Password</label>
        <div class="col-sm-10">
        <input type="text" class="form-control" name="password" id="Pass" placeholder="Password">
        </div>
    </div>
    <div class="form-group row">
        <label for="Correo" class="col-sm-2 col-form-label">Correo</label>
        <div class="col-sm-10">
            <input type="email" class="form-control" name="correo" id="Correo" placeholder="Correo">
        </div>
    </div>
    <div class="form-group row">
        <label for="Nombre" class="col-sm-2 col-form-label">Nombre</label>
        <div class="col-sm-10">
        <input type="text" class="form-control" name="nombre" id="Nombre" placeholder="Nombre">
        </div>
    </div>
    <div class="form-group row">
        <label for="Apellido" class="col-sm-2 col-form-label">Apellido</label>
        <div class="col-sm-10">
        <input type="text" class="form-control" name="apellido" id="Apellido" placeholder="Apellido">
        </div>
    </div>
    <div class="form-group row">
        <label for="Rango" class="col-sm-2 col-form-label">Rango</label>
        <div class="col-sm-10">
            <select class="form-control" name="rango" id="Rango">
                <!--Aca poner los rangos con un for-->
                <option value="admin">Administrador</option>
                <option value="ventas">Ventas</option>
            </select>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-sm-10">
        <button type="submit" class="btn btn-primary">Guardar</button>
        </div>
    </div>
</form>
