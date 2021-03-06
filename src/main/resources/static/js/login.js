$(document).ready(function(){
    var login = function(event) {
        var query = {
            url: "http://localhost:8080/api/users/login?username=" + $('#username').val() + "&" + "password=" + $('#password').val(),
            type: "POST",
            data: "",
            dataType: "text",
            cache: false,
            success: function(json) {
                console.log(json);
                data = $.parseJSON(json);
                console.log(data);
                console.log(data["sessionId"]);
                Cookies.set("sessionId", data["sessionId"]);
                console.log("logged in successfully");
                window.location = "/";
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

    $('#login').on('click', login);
    console.log("Ready!");
});