/**
 * 
 */

$("document").ready(function() {
	$("#submit").click(function() {
		var data = {"email": $("#email").val(),
		"firstName":$("#firstName").val()
		};
		var url = "/api/register" + "?email=" + $("#email").val() 
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
				$("#result").append(obj.firstName + " registered!");
				window.location.href = "/signin";
			}
			
			
			
		});
	}
		
		
		
		
	);
	
	
	
	
});