function prMigrar(){
	var f = document.getElementById('fecha');
	var checkFactura = document.getElementById('ch-factura');
	var checkBoleta  = document.getElementById('ch-boleta');
	var corregido = document.getElementById('ch-corregido');
	
	var formData ={
		fecha : f.value,
		facturas : checkFactura.checked,
		boletas : checkFactura.checked,
		corregido : corregido.checked,
		action : 'migrar'
	};
	
	$('#tabla-generacion').empty();
	$('#tabla-generacion').append('<div id="bloqueo"><div id="loader"></div></div>');
	
	$.post('ProcesosController', formData , function (response){
	}).done( (response) => {
		$('#bloqueo').remove();
		
		if(response.error){
			alert(response.error);
		}
		else{
			
			$('#tabla-generacion').append(response);
		}
		
	}).fail( () => {
		$('#bloqueo').remove();
		alert('Error en la llamada al Servidor');
	});
	
}

function prGenerar(){
	var f = document.getElementById('fecha');
	var checkFactura = document.getElementById('ch-factura');
	var checkBoleta  = document.getElementById('ch-boleta');
	
	var formData ={
		fecha : f.value,
		facturas : checkFactura.checked,
		boletas : checkFactura.checked,
		action : 'generar'
	};
	$('#tabla-generacion').empty();
	$('#tabla-generacion').append('<div id="bloqueo"><div id="loader"></div></div>');
	
	
	$.post('ProcesosController', formData , function (response){
	}).done( (response) => {
		$('#bloqueo').remove();
		
		if(response.error){
			alert(response.error);
		}
		else{
			
			$('#tabla-generacion').append(response);
		}
		
	}).fail( () => {
		$('#bloqueo').remove();
		alert('Error en la llamada al Servidor');
	});
	
}