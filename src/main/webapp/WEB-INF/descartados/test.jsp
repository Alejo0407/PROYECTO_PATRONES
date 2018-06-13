<%@ page language='java' contentType='text/html; charset=UTF-8'
    pageEncoding='UTF-8'%>
    <!DOCTYPE html>
<%@ page import = "edu.pe.unmsm.controlador.beans.TestBean"%>
<html lang='es'>
	<head>
		<title>Prueba de Bean</title>
	</head>
	<body>
		<jsp:useBean id="bean" class="edu.pe.unmsm.controlador.beans.TestBean" scope = "page"/>  
		<form>
			<jsp:getProperty property = "dni" name = "bean"/><br/>
			<jsp:getProperty property = "nombre" name = "bean"/><br/>
			<jsp:getProperty property = "edad" name = "bean"/><br/>
		</form>
	</body>
</html>