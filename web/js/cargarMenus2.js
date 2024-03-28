$(document).ready(function(){ 
    $.ajax({
        type: "GET",
        url: "cookiesServletController",
        data:{
            action:"TraerData"
        }
    })
    .done(function (data, status, jqXhr) {
        fnCargarMenus(data);
        
    });
    
    $('div a').click(function(){   
        fnClickMenus()       
    })    

});

function fnCargarMenus(cadAcceso){

    $.ajax({
        url: "obtenerMenus2ServletController",
        data: {
            acceso: cadAcceso
        //$('#lblAcceso').text().trim()
        }
    })
    .done(function (data, status, jqXhr) {
        $("#header").html(data);     
    });      
}

function fnClickMenus()
{
    $.ajax({
        url: "cookiesServletController",
        data: {
            action: "registroAlumno"
        }
    })
    .done(function (data, status, jqXhr) {
        fnCargarMenus(data);     
    });
}
