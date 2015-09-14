function getFollowee(userId) {
    var query = {
        url : "http://localhost:8080/api/users/"+userId+"/followee",
        type : "GET",
        data : "",
        dataType : "text",
        cache:false,
        success : function(json){
            console.log(json);
            data = $.parseJSON(json);
            console.log(data);
            $('#following').text("following " + data.length);
        },
        error: function(xhr, status, errorThrown){
             console.log("error");
             $("#error").text("Some error occurred (" + errorThrown + ")");
         },
         complete: function( xhr, status ) {
             console.log( "The request is complete!" );
         }

    };
    $.ajax(query);
}
function getFollowers(userId) {
    var query = {
        url : "http://localhost:8080/api/users/"+userId+"/followers",
        type : "GET",
        data : "",
        dataType : "text",
        cache:false,
        success : function(json){
            console.log(json);
            data = $.parseJSON(json);
            console.log(data);
            $('#followers').text(data.length + " followers");
        },
        error: function(xhr, status, errorThrown){
             console.log("error");
             $("#error").text("Some error occurred (" + errorThrown + ")");
         },
         complete: function( xhr, status ) {
             console.log( "The request is complete!" );
         }

    };
    $.ajax(query);
}
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
                    getFollowers(userId);
                    getFollowee(userId);
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