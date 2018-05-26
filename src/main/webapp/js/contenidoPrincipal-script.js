
function cargarMenu(menuName){
	var data = {
		menu:menuName
	};
	$.post('MenuController',data ,function(responseText){
		
	}).done(function(responseText){
		$('#leftMenu').empty();
		$('#mainContent').empty();
		$('#leftMenu').append(responseText);
	}).fail(function(){
		alert('error');
	});
}