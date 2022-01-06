/**
 * 
 */
$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/json"
    }).then(function(data) {
       $('#myHead').append("<h1>" + data.id +"</h1>");
    });
});