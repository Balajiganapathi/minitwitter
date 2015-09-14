$(document).ready(function(){
    var query = {
        headers: {
            'Accept': 'application/json',
        },
        url: "http://localhost:8080/api/users/feed?sessionId=" + Cookies.get("sessionId"),
        type: "GET",
        data: "",
        dataType: "json",
        cache: false,
        success: function(data) {
            console.log(data);
            for(var i = 0; i < data.length; i++) {
                var tweet = data[i];
                var tweetElement = $("#tweet").clone();

                tweetElement.children('#author').text(tweet.user.name);
                tweetElement.children('#contents').text(tweet.contents);

                tweetElement.removeAttr('id');
                tweetElement.children("#author").removeAttr('id');
                tweetElement.children("#contents").removeAttr('id');

                $("#tweetContainer").append(tweetElement);
            }
            console.log("Feed retrieved successfully");
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