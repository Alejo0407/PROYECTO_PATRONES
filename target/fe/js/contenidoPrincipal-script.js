
function cargarMenu(menuName){
	var data = {
		menu:menuName
	};
	$('#navPanel.nav-link').prop("disabled",true);
	$('#navPanel.dropdown-item').prop("disabled",true);
	$.post('MenuController',data ,function(responseText){

	}).done(function(responseText){
		try{
			var json = $.parseJSON(responseText);
			console.log(json);
			alert(json['error']);
		}
		catch(e){
			$('#leftMenu').empty();
			$('#mainContent').empty();
			$('#leftMenu').append(responseText);		
		}
		
	}).fail(function(response){
		console.log(response);
		alert('error en la llamada');
	}).always(function(){
		$('#navPanel.nav-link').prop("disabled",false);
		$('#navPanel.dropdown-item').prop("disabled",false);
	});
}