/**
 * 
 */
$("document").ready(function() {
	$.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "/api/update",
		dataType: "json",
		success: function(data) {
			var obj = JSON.parse(JSON.stringify(data));
			$("#updateId").append(obj.firstName);
			
		}
	});
	
	$("#submit").click(function() {
		var data = {"email": $("#email").val(),
		"firstName":$("#firstName").val()
		};
		var url = "/api/update" + "?email=" + $("#email").val() 
		          + "&firstName=" + $("#firstName").val()
				  + "&lastName=" + $("#lastName").val()
				  + "&password=" + $("#password").val()
				  + "&address=" + $("#address").val() 
		+ "&phone=" + $("#phone").val()
		+ "&major=" + $("#major").val(); 
		
		$.ajax({
			type: "POST",
			contentType: "application/json, charset=utf-8",
			url: url,
			//data: JSON.stringify(data),
			dataType: "json",
			success: function(response) {
				var obj = JSON.parse(JSON.stringify(response));
				$("#result").append("success update profile of " + obj.success.firstName);
				
			},
			error: function(response) {
				//var obj = JSON.parse(JSON.stringify(response));
				//alert(obj.error);
			}
			
		
		
	});
	
	});
});
