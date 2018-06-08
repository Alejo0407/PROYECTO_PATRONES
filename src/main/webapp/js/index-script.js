/*Función del loggin*/
$( document ).ready(function() {

    $('#logginPanel-logginForm').on('submit', function(e) { 
        e.preventDefault();

        var formData = {
			user:$('input[name=username]').val(),
			pass:$('input[name=password]').val()
		};
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
				$('#logginPanel').hide();	
			}
		})
		.fail(() => {$('#bloqueo').remove();alert('hubo un error en la llamada');});
    });
});

function loggout(){
	//Aqui falta una llamada para matar la session
	$('#navPanel').remove();
	$('#menuPanel').remove();
	$('#logginPanel').show();
}