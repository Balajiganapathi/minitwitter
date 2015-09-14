$(document).ready(function(){
    $("#again").hide();
    if(!userLoggedIn()) {
        $("#message").text("No active session");
        $("#again").show();
        return;
    }
    var query = {
        url: "http://localhost:8080/api/users/logout?sessionId=" + Cookies.get("sessionId"),
        type: "POST",
        data: "",
        dataType: "text",
        cache: false,
        success: function(json) {
            Cookies.remove("sessionId");
            $("#message").text("Successfully logged out!");
            $("#again").show();
            console.log(json);
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
});