/*EVENT THAT HANDLES SESSION*/

$( document ).ready(function() {	
	var data = {
		action : 'load'
	};
	$.post('IndexController', data ,function(response){
	})
	.done((response) =>  $('body').append(response))
	.fail(() => alert('Hubo un error en el servidor'));	
});