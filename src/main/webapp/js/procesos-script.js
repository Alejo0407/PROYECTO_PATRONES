/*Para los procesos principales*/
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

/*Para los resumenes*/
function prRDHandleNext(step){
	if(step == 4)
		return;
	
	if(step != 2){	
		var formData = {
			action : 'loadResumenPage',
			paso   : step
		};
	}
	else{
		var f = document.getElementById('fecha').value;
		var formData = {
			action : 'loadResumenPage',
			paso   : step,
			fecha : f
		}
	}
	$('#resumen-content').append('<div id="bloqueo"><div id="loader"></div></div>');
	
	$('#rd-atras').prop('disabled',true);
	$('#rd-siguiente').prop('disabled',true);
	
	$.post('ProcesosController',formData, function(){
	}).done( (response) => {
		$('#bloqueo').remove();
		if(response.error){
			alert(response.error);
		}
		else{
			$('#resumen-content').empty();
			$('#resumen-content').append(response);
			$('#rd-atras').attr('onClick', 'prRDHandlePrev('.concat(step+1,')'));
			$('#rd-siguiente').attr('onClick', 'prRDHandleNext('.concat(step+1,')'));
		}
	}).fail( () => {
		$('#bloqueo').remove();
		alert('Error en la llamada al Servidor');
	});
	
	if(step != 3){
		$('#rd-atras').prop('disabled',false);
		$('#rd-siguiente').prop('disabled',false);
	}
	
}
function prRDHandlePrev(step){
	if(step == 0)
		return;
	if(step != 2){	
		var formData = {
			action : 'loadResumenPage',
			paso   : step
		};
	}
	else{
		var f = document.getElementById('fecha').value;
		var formData = {
			action : 'loadResumenPage',
			paso   : step,
			fecha : f
		}
	}
	$('#resumen-content').append('<div id="bloqueo"><div id="loader"></div></div>');
	
	$('#rd-atras').prop('disabled',true);
	$('#rd-siguiente').prop('disabled',true);
	
	$.post('ProcesosController',formData, function(){
	}).done( (response) => {
		$('#bloqueo').remove();
		
		if(response.error){
			alert(response.error);
		}
		else{

			$('#resumen-content').empty();
			$('#resumen-content').append(response);
			$('#rd-atras').attr('onClick', 'prRDHandlePrev('.concat(step-1,')'));
			$('#rd-siguiente').attr('onClick', 'prRDHandleNext('.concat(step-1,')'));
		}
	}).fail( () => {
		$('#bloqueo').remove();
		alert('Error en la llamada al Servidor');
	});
	
	$('#rd-atras').prop('disabled',false);
	$('#rd-siguiente').prop('disabled',false);
}
