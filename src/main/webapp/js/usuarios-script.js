function usEnviar(tipo){
	
	var formData = {
			idUser : document.getElementById('IdUser').value,
			Pass : document.getElementById('Pass').value,
			Correo : document.getElementById('Correo').value,
			Nombre : document.getElementById('Nombre').value,
			Apellido : document.getElementById('Apellido').value,
			Rango : document.getElementById('Rango').value,
			action : tipo
	};
	
	console.log(formData);
	$('body').append('<div id="bloqueo"><div id="loader"></div></div>');
	
	$.post("UsuarioController", formData,function(response) {
	})
	.done(function(response){
		$('#bloqueo').remove();
		if(response.error){
			alert(response['error']);
		}
		else{
			$('#contenido-principal').empty();
			$('#contenido-principal').append(response);
		}
	})
	.fail(() => {$('#bloqueo').remove();alert('hubo un error en la llamada');});
	
}

function usEditar(ident){
	var formData = {
			id : ident,
			action : 'modificarUsuarioTabla'
	};
	
	console.log(formData);
	$('body').append('<div id="bloqueo"><div id="loader"></div></div>');
	
	$.post("UsuarioController", formData,function(response) {
	})
	.done(function(response){
		$('#bloqueo').remove();
		if(response.error){
			alert(response['error']);
		}
		else{
			$('#contenido-principal').empty();
			$('#contenido-principal').append(response);
		}
	})
	.fail(() => {$('#bloqueo').remove();alert('hubo un error en la llamada');});
}
function usBorrar(ident){
	var formData = {
			id : ident,
			action : 'eliminarUsuario'
	};
	
	console.log(formData);
	$('body').append('<div id="bloqueo"><div id="loader"></div></div>');
	
	$.post("UsuarioController", formData,function(response) {
	})
	.done(function(response){
		$('#bloqueo').remove();
		if(response.error){
			alert(response['error']);
		}
		else{
			$('#contenido-principal').empty();
			$('#contenido-principal').append(response);
		}
	})
	.fail(() => {$('#bloqueo').remove();alert('hubo un error en la llamada');});
}

