/*Función del loggin*/
$(function() { 
    $('#logginPanel-logginForm').on('submit', function(e) { //use on if jQuery 1.7+
        e.preventDefault();
        var formData = {
			user:$('input[name=username]').val(),
			pass:$('input[name=password]').val()
		};

		$('#logginPanel').hide();
		$('body').css('background-color','gray');
		$('body').append('<div id="loader"></div>');
    	
    	$.post("IndexController", formData,function(responseText) {
    		
		}).done(function(responseText){
			$('body').append(responseText);
			
		}).fail(function(){
			alert('hubo un error');
			$('#logginPanel').show();

		}).always(function(){
			$('#loader').remove();
			$('body').css('background-color','#fff');
		});
    });
});

function loggout(){
	$('#navPanel').remove();
	$('#menuPanel').remove();
	$('#logginPanel').show();

}