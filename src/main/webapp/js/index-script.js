/*Función del loggin*/
$(function() { 
    $('#logginPanel-logginForm').on('submit', function(e) { //use on if jQuery 1.7+
        e.preventDefault();
        var formData = {
			user:$('input[name=username]').val(),
			pass:$('input[name=password]').val()
		};

		$('#loader').css('visibility','visible');
		$('body').css('background-color','gray');
		$('#logginPanel').hide();
    	console.log('show invoked');
    	
    	$.post("IndexController", formData,function(responseText) {
    		
		}).done(function(responseText){
			$('body').append(responseText);
			
		}).fail(function(){
			alert('hubo un error');
			$('#logginPanel').show();

		}).always(function(){
			$('#loader').css('visibility','hidden');
			$('body').css('background-color','#fff');
    		console.log('hide invoked');
		});
    });
});

function loggout(){
	$('#navPanel').remove();
	$('#menuPanel').remove();
	$('#logginPanel').show();

}