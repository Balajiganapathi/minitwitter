$(document).ready(function() {
        var ul = window.location.href;
        var index = ul.split('/');
        var userId = index.pop();
        var query = {
            url : "http://localhost:8080/api/users/"+ userId +"/profile",
            type: "GET",
            data: "",
            dataType: "text",
            cache: false,
            success: function(json) {
                    console.log(json);
                    data = $.parseJSON(json);
                    $('#name').text(data.name);
                    $('#email').text(data.email);
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
});