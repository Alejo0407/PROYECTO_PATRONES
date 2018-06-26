function msGenerarTabla(){
	var f = document.getElementById('fecha');
	
	var formData = {
			action : 'getDatosTabla',
			fecha : f.value
	};
	$.post('MonitorController', formData , function(response){
		
	}).done((response) => {
		$('#monitoreo-tabla').append(response);
		
	}).fail(() => {
		alert('Error en la llamada al servidor');
	});
}