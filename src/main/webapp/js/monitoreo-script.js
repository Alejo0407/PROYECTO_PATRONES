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

 function save(filename, data,t) {
    var blob = new Blob([data], {type: t});
    if(window.navigator.msSaveOrOpenBlob) {
        window.navigator.msSaveBlob(blob, filename);
    }
    else{
        var elem = window.document.createElement('a');
        elem.href = window.URL.createObjectURL(blob);
        elem.download = filename;        
        document.body.appendChild(elem);
        elem.click();        
        document.body.removeChild(elem);
    }
}

function mnDownloadXml(id){
	var formData = {
			action : 'download',
			transaccion : id,
			content : 'xml'
	};
	
	$.post('MonitorController', formData , function(response){
		
	})
	.done( (response,status,xhr) => {
		
		if(response.error){
			alert(response.error);
		}
		else{
			save(xhr.getResponseHeader("nombre"),response,xhr.getResponseHeader("mime"));
		}
	})
	.fail( () => {
		alert('Error en la llamada al servidor');
	});
	
}

function mnDownloadSunat(id){
	var formData = {
			action : 'download',
			transaccion : id,
			content : 'sunat'
	};
	
	$.post('MonitorController', formData , function(response){
		
	})
	.done( (response,status,xhr) => {
		
		if(response.error){
			alert(response.error);
		}
		else{
			save(xhr.getResponseHeader("nombre"),response,xhr.getResponseHeader("mime"));
		}
	})
	.fail( () => {
		alert('Error en la llamada al servidor');
	});
	
}
