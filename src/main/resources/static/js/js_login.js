$(document).ready(function(){
    var login = function(event) {
        var query = {
            url: "http://localhost:8080/api/users/login?username=" + $('#username').val() + "&" + "password=" + $('#password').val(),
            type: "POST",
            data: "",
            dataType: "text",
            cache: false,
            success: function(json) {
                alert("logged in successfully");
                console.log(json);
            },
            error: function(xhr, status, errorThrown){
                console.log("error");
            },
            complete: function( xhr, status ) {
                console.log( "The request is complete!" );
            }
        };
        console.log(query);
        $.ajax(query);
        console.log("Request sent!");
        event.preventDefault();
    };
    $('#login').on('click', login);
    console.log("Ready!");
});