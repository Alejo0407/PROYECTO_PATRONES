function prueba(){
	$.get("IndexController", function(responseText) {
                    $("#somediv").load(responseText);
	});
}