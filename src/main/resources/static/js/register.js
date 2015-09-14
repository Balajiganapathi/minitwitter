$(document).ready(function(){
    var register = function(event) {
        var query = {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: "http://localhost:8080/api/users/register",
            type: "POST",
            data: '{"name": "' + $("#username").val() + '", "email": "' + $("#email").val() + '", "password" : "'+ $("#password").val() + '"}',
            dataType: "json",
            cache: false,
            success: function(data) {
                console.log(data);
                console.log("registered successfully");
            },
            error: function(xhr, status, errorThrown){
                console.log("error");
                $("#error").text("Some error occurred (" + errorThrown + ")");
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

    $('#register').on('click', register);
    console.log("Ready!");
});