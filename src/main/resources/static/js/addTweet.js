$(document).ready(function(){
    var tweet = function(event) {
        var query = {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: "http://localhost:8080/api/users/tweets?sessionId=" + Cookies.get("sessionId"),
            type: "POST",
            data: '{"contents":"' + $("#contents").val() + '"}',
            dataType: "json",
            cache: false,
            success: function(data) {
                console.log(data);
                console.log("Tweeted successfully");
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

    $('#tweet').on('click', tweet);
    console.log("Ready!");
});