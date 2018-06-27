function msGenerarTabla(){
	var f = document.getElementById('fecha');
	
	var formData = {
			action : 'getDatosTabla',
			fecha : f.value
	};

	$('#monitoreo-tabla').empty();
	$('body').append('<div id="bloqueo"><div id="loader"></div></div>');
	$.post('MonitorController', formData , function(response){
		
	}).done((response) => {
		$('#bloqueo').remove();
		if(response.error){
			alert(response.error);
		}
		else{
			$('#monitoreo-tabla').append(response);
		}
	}).fail(() => {
		$('#bloqueo').remove();
		alert('Error en la llamada al servidor');
	});
}