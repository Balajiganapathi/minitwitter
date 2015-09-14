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

function toggleFollow(curId, userId) {
    var f = $("#followLink").attr("value");
    var query = {
        url: "http://localhost:8080/api/users/follow/" + userId + "?sessionId=" + Cookies.get("sessionId"),
        type: (f == "follow"? "POST": "DELETE"),
        data: "",
        dataType: "text",
        cache: false,
        success: function(json) {
            console.log(json);
            console.log(f + "ed successfully");
            if(f == "follow") {
                $("#followLink").attr("value", "unfollow");
            } else {
                $("#followLink").attr("value", "follow");
            }
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
            if(!Cookies.get("sessionId")) {
                $("#followLink").hide();
                return;
            }

            function determineStatus(json) {
                console.log("Status: " + json);
                console.log($.parseJSON(json));
                var curId = $.parseJSON(json).id;
                console.log("c/u: " + curId + " " + userId);
                if(curId == userId) {
                    $("#followLink").hide();
                    return;
                }

                var following = false;
                console.log("Loop: " + data.length);
                for(var i = 0; i < data.length; i++) {
                    var f = data[i];
                    console.log(f);
                    console.log(f.follower);
                    console.log(f.follower.id);
                    if(f.follower.id == curId) {
                        following = true;
                    }
                }

                if(following) {
                    $("#followLink").attr("value", "unfollow");
                } else {
                    $("#followLink").attr("value", "follow");
                }

                $("#followLink").on('click', function(event) {
                    toggleFollow(curId, userId);
                    event.preventDefault();
                });

            }

            var userquery = {
                    url : "http://localhost:8080/api/users/profile?sessionId=" + Cookies.get("sessionId"),
                    type : "GET",
                    data : "",
                    dataType : "text",
                    success : determineStatus,
                    error: function(xhr, status, errorThrown){
                         console.log("error");
                         $("#error").text("Some error occurred (" + errorThrown + ")");
                     },
                     complete: function( xhr, status ) {
                         console.log( "The request is complete!" );
                     }
            }
            console.log(userquery);
            $.ajax(userquery);

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