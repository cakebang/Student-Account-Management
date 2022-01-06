/**
 * 
 */


$("document").ready(function(){
	$.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "/api/delete",
		dataType: "json",
		success: function(data) {
			var obj = JSON.parse(JSON.stringify(data));
			$("#h3Id").append(obj.success.firstName);
			
		}
		
	});
});