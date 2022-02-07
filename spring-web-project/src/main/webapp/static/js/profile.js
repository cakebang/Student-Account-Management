/**
 * 
 */

$("document").ready(function(){
	$.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "/api/profile",
		dataType: "json",
		success: function(data) {
			var obj = JSON.parse(JSON.stringify(data));
			$("#firstName").append("first name: " + obj.firstName);
			$("#lastName").append("last name: " + obj.lasttName);
			$("#email").append("email: " + obj.email);
			$("#address").append("address: " + obj.address);
			$("#phone").append("phone: " + obj.phone);
		}
		
	});
	$.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "/api/major/profile",
		dataType: "json",
		success: function(data) {
			var obj = JSON.parse(JSON.stringify(data));
			$("#major").append("major: " + obj.name);
		}
	});
});