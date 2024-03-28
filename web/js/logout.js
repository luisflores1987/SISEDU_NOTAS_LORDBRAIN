$(document).ready(function(){
    $("#logout").click(function(e){
        e.preventDefault();
        $.ajax({ 
            url: "LogoutServletController",
            cache: false
        });
    });
});

