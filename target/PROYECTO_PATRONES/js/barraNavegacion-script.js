
function cargarMenu(menuName,folderName){
	var data = {
		menu:menuName,
		folder:folderName
	};
	$.post('MenuController',data ,function(responseText){
		
	}).done(function(responseText){
		$('#leftMenu').empty();
		$('#leftMenu').append(responseText);
	}).fail(function(){
		alert('error');
	});
}