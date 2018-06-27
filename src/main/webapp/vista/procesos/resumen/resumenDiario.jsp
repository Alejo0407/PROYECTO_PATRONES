<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>

<div class = 'alert alert-dark row mt-4'>
	<div id = 'head' class=" d-flex" role="alert">
	  	Resumen Diario de Boletas
	</div>
</div>

<div class = 'container' id = 'resumen-content mt-4'>
	<%@ include file="rdStep1.jsp" %>  
</div>

<div class = 'row justify-content-between mt-3' id = 'resumen-btn-controller'>
	<div class = 'col-4' >
		<button id = 'rd-atras' class = 'btn btn-dark' onclick = 'prRDHandlePrev(0)'>Atras</button>
	</div>
	<div class = 'col-4' >
		<button id = 'rd-siguiente' class = 'btn btn-dark' onclick = 'prRDHandleNext(2)'>Siguiente</button>
	</div>
</div>
