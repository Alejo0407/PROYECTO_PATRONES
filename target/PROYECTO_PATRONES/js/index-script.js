$(function() { 
    $('#logginPanel-logginForm').on('submit', function(e) { //use on if jQuery 1.7+
        e.preventDefault();
        var formData = {
			user:$('input[name=username]').val(),
			pass:$('input[name=password]').val()
		};
        console.log(formData['user']); //use the console for debugging, F12 in Chrome, not alerts
    	console.log(formData['pass']);
    	$.post("IndexController", formData,function(responseText) {
			$('body').append(responseText);
			$('#logginPanel').hide();
		});
    });
});

function loggout(){
	$('#navPanel').remove();
	$('#menuPanel').remove();
	$('#logginPanel').show();

}

function actionHome(variable){
	console.log(variable);
	if($(variable).is(':visible')){
		$(variable).hide();	
	}
	else{
		$(variable).show();
	}
	
}