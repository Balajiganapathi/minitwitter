$(document).ready(function(){
    var urlParts = window.location.pathname.split('/');
    var tweetId = urlParts[urlParts.length - 1];

    var query = {
        headers: {
            'Accept': 'application/json',
        },
        url: "http://localhost:8080/api/tweets/" + tweetId,
        type: "GET",
        data: "",
        dataType: "json",
        cache: false,
        success: function(data) {
            console.log(data);
            $("#author").text(data["user"]["name"]);
            $("#contents").text(data["contents"]);
            console.log("Tweet retrieved successfully");
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