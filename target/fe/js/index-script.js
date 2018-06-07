/*Función del loggin*/
$(function() { 
    $('#logginPanel-logginForm').on('submit', function(e) { //use on if jQuery 1.7+
        e.preventDefault();
        var formData = {
			user:$('input[name=username]').val(),
			pass:$('input[name=password]').val()
		};

		//$('#logginPanel').hide();
		$('body').append('<div id="bloqueo"><div id="loader"></div></div>');
    	
    	$.post("IndexController", formData,function(responseText) {
    		
		}).done(function(responseText){
			$('body').append(responseText);
			$('#logginPanel').hide();
			
		}).fail(function(){
			alert('hubo un error');

		}).always(function(){
			$('#bloqueo').remove();
		});
    });
});

function loggout(){
	$('#navPanel').remove();
	$('#menuPanel').remove();
	$('#logginPanel').show();

}