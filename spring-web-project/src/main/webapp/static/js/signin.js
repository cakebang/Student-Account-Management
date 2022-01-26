/**
 * 
 */
$(function(){//use function to initialize the function
	$("#submit").click(function() {
		
		var email = $("#email").val();
		var password = $("#password").val();
		var signInRequest = {'email': email, 'password': password};
	
	$.ajax({
		type: "POST",
		contentType: "application/json; charset=utf-8",
		url: "/api/signin",// relatvie path
		data: JSON.stringify(signInRequest),//stringify is very important, stringify ensure json format
		dataType: 'json',
		success: function(){
        	 //var obj = JSON.parse(JSON.stringify(data));//stringify is very important
        	 //$('#result').append("<p>First Name:- " + obj.firstName + "</p>");

   			window.location.href = "/profile";
        },
		error: function() {
			$('#result').append("<p>Wrong email or password </p>");
		}

		});
	});
});

