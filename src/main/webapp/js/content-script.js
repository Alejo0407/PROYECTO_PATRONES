/**
 * 
 * 
 */

function loggout(){
	var f = {
		action : 'loggout'	
	};
	$.post('IndexController',f, function(){
	}).done( () => location.reload() );
	
}

function invocarContenido(ruta){
	
	console.log(ruta);
	var formData = {
			vista : ruta,
			action : simpleLoad
	}

	$('#contenido-principal').empty();
	$('body').append('<div id="bloqueo"><div id="loader"></div></div>');
	$.post("DispatcherController",formData,function(response){
	})
	.done( (response) => {
		$('#bloqueo').remove();
		if(response.error){
			alert('Sesión terminada');
			location.reload();
		}
		else{
			$('#contenido-principal').append(response);
		}
	})
	.fail( () => {
		alert('Hubo un error en la llamada al Servidor');
		$('#bloqueo').remove();
	});
}

function invocarContenidoPreCargado(ruta,funcion){
	console.log(ruta);
	var formData = {
			vista : ruta,
			action : funcion
	}

	$('#contenido-principal').empty();
	$('body').append('<div id="bloqueo"><div id="loader"></div></div>');
	$.post("DispatcherController",formData,function(response){
	})
	.done( (response) => {
		$('#bloqueo').remove();
		if(response.error){
			alert('Sesión terminada');
			location.reload();
		}
		else{
			$('#contenido-principal').append(response);
		}
	})
	.fail( () => {
		alert('Hubo un error en la llamada al Servidor');
		$('#bloqueo').remove();
	});
}

function loggin(){
	var formData = {
			username : document.getElementById('form-usr').value,
			password : document.getElementById('form-pass').value,
			action : 'loggin'
	};
	
	console.log(formData);
	$('body').append('<div id="bloqueo"><div id="loader"></div></div>');
	
	$.post("IndexController", formData,function(response) {
	})
	.done(function(response){
		$('#bloqueo').remove();
		if(response.error){
			alert(response['error']);
		}
		else{
			$('body').append(response);
			$('#logginPanel').remove();	
		}
	})
	.fail(() => {$('#bloqueo').remove();alert('hubo un error en la llamada');});
	
}
