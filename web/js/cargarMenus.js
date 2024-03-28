$(document).ready(function(){
    fnCargarMenus();
});

function fnCargarMenus(){
    console.log($('#txtUsuario').eq(1).text());
    $.ajax({
        url: "obtenerMenusServletController",
        data: {
            acceso: $('#lblAcceso').text().trim()
        }
    })
    .done(function (data, status, jqXhr) {
        $("#header").html(data);     
    }); 
     
}

