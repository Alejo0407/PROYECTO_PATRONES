<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ page import = "edu.pe.unmsm.modelo.dao.beans.ResumenBean" %>
<%
ResumenBean resumen = (ResumenBean)session.getAttribute("prResumenDiario");
%>

<div class="form-group row">
    <label for="inputPassword" class="col-sm-2 col-form-label">Archivo: </label>
    <div class="col-sm-10">
      <button class = 'btn btn-link'><%=resumen.getNombreArchivo() %></button>
    </div>
</div>

<br>

<div class="form-group row">
    <label for="inputPassword" class="col-sm-2 col-form-label">Ticket: </label>
    <div class="col-sm-10">
      <input value = "<%=resumen.getTicket() %>" class="form-control" type="text" readonly>
    </div>
</div>

<br>

<div class="form-group row">
    <label for="inputPassword" class="col-sm-2 col-form-label">Estado: </label>
    <div class="col-sm-10">
      <input value = "Peticion enviada a Sunat" class="form-control" type="text" readonly>
    </div>
</div>
