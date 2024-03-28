$(document).ready(function(){
    setInterval(getRandValue, 500);    
    show5();  // funcio para colocar el reloj en el div
    window.onload=show5 // lo coloca en la pantalla.    
    //fnColocarFecha();
    fnEventoBoton();
    inicializarCapa();
    fnValidarIngreso();
    clickEvento();
});
    
function show5(){
    if (!document.layers&&!document.all&&!document.getElementById)
        return
    
    var Digital=new Date()
    var hours=Digital.getHours()
    var minutes=Digital.getMinutes()
    var seconds=Digital.getSeconds()
    var dn="PM"
    if (hours<12)
        dn="AM"
    if (hours>12)
        hours=hours-12
    if (hours==0)
        hours=12

    if (minutes<=9)
        minutes="0"+minutes
    if (seconds<=9)
        seconds="0"+seconds
    //change font size here to your desire
    myclock='<center><label style="font-size: 120px;color:#FFFFFF">'+hours+':'+minutes+':'+seconds+' '+dn+'</label></center>'
    if (document.layers){
        document.layers.liveclock.document.write(myclock)
        document.layers.liveclock.document.close()
    }
    else if (document.all)
        liveclock.innerHTML=myclock
    else if (document.getElementById)
        document.getElementById("liveclock").innerHTML=myclock
    setTimeout("show5()",1000)
}

//function fnColocarFecha(){
//
//    $("#btnIngresarMarcado").on ("click", function(){
//        var tiempo =  new Date();
//        
//        var tiempoFormato = moment().format("DD/MM/YYYY"); //se llama al objeto moment, que pertenece al moment.js
//
//        var hora = tiempo.getHours();
//        var minuto = tiempo.getMinutes();
//        var segundo = tiempo.getSeconds();    
//    });    
//}
    
function getRandValue(){
    $.ajax({
        type: "GET",
        url: "obtenerNuevosRegistros",
        data: {
            action: "obtenerAsistencia"
        }
    })
    .done(function(data, nativeStatusText, responses){
        if (data != "0"){
            var codigo = data;
            $('#txtCodigo').val(codigo);
            $('#asistenciaProfesor').show(500, function(){
                setAsistencia()
                fnMostrarDatosAsistencia();   
            })
                           
        }
    });

}
function setAsistencia(){

    $.ajax({
        type: "GET",
        url: "obtenerNuevosRegistros",
        data:{
            action: "colocarAsistencia",
            sCodigo: $('#txtCodigo').val()
        }        
    })
    
    .done(function(data, nativeStatusText, responses){
        if (data == "1") 
            console.log("Ingreso"); 
    });    
    
}

function fnMostrarDatosAsistencia(){
    $.ajax({
        type: "Get",
        url:  "obtenerNuevosRegistros",
        data:{
            action: "obtenerDatosAsistencia",
            sCodigo: $('#txtCodigo').val()
        }
    })
    
    .done(function (data, status, jqXhr) { 
        arregloAlumnos = data.split("*");
        limparselect();
        if (data=='') {
            $('#tblAsistenciaProfesor').append('<tr><td colspan = "4"><label> No se encontraron datos ... </label></td></tr>'); 
        }
        else{
            for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
                var nIdPersona =  arregloAlumnos[i].split("+")[0];
                var sNombreCompleto  =  arregloAlumnos[i].split("+")[1];            
                var nCodigo=  arregloAlumnos[i].split("+")[2];  
                var sMensaje =  arregloAlumnos[i].split("+")[3];
                var sColor =  arregloAlumnos[i].split("+")[4].trim();
                var sHoraEntrada = arregloAlumnos[i].split("+")[5].trim();
                var sMensajeMarcado =  arregloAlumnos[i].split("+")[6].trim();
                if (sColor == 0)                    
                    $('#tblAsistenciaProfesor').append('<tr><td style="width: 100%;text-align: center;font-size: xx-large;"><label style="color: #222222">'+sNombreCompleto+'</label></td></tr><tr><td style="width: 175px;text-align: center;font-size: xx-large;background: #B9B5B5" colspan="2"><label style="color:red">'+sMensajeMarcado+'</label></td></tr><tr><td style="width: 175px;text-align: center;font-size: -webkit-xxx-large;" colspan="2"><label style="color:red">'+sMensaje+'</label></td></tr>');                 
                else
                    $('#tblAsistenciaProfesor').append('<tr><td style="width: 100%;text-align: center;font-size: xx-large;"><label style="color: #222222">'+sNombreCompleto+'</label></td></tr><tr><td style="width: 175px;text-align: center;font-size: xx-large;background: #B9B5B5" colspan="2"><label style="color: #3CEF3C;font-family: "Verdana", serif;">'+sMensajeMarcado+'</label></td></tr><tr><td style="width: 175px;text-align: center;font-size: -webkit-xxx-large;" colspan="2"><label style="color: #3CEF3C;font-family: "Verdana", serif;">'+sMensaje+'</label></td></tr>');                 
               
                $('#lblHorasIngreso').text('HORA DE INGRESO : ' + sHoraEntrada);
            }   
        }    
    });
    
    function limparselect(){
        $('#tblAsistenciaProfesor tr').remove();
        $('#lblHorasIngreso').text("");
    }    
}

function fnEventoBoton(){
    $('#btnIngresarMarcado').on('click',function(){
        fnValidarIngreso();        
        if (!validado) {    
            return;
        }
        $.ajax({
            url : "obtenerNuevosRegistros",
            data:{
                action: "colocarAsistenciaManual",
                sCodigo: $('#txtCodigo').val()
            }  
        })
        .done(function(data){
            var arregloMensaje = data.split("+");
            if (arregloMensaje[0]==1){
                console.log(arregloMensaje[1])
            }
            else{
                jAlert(arregloMensaje[1], "Mensaje");
            }
            fnClearElementos();
        }); 

    })
} 

function inicializarCapa(){
    $('#asistenciaProfesor').hide();
}

function fnValidarIngreso(){
    
    validado = true;
    var cantx = 0;
    var canty = 0;
    
    $(".txtFiltro").each(function(){
        cantx = cantx + 1;
        if ($(this).val().length < 1) {
            canty = canty + 1;  
        }
    });
    
    if (cantx == canty)
    {
        validado = false;
        return false;
    }
    
}

function fnClearElementos()
{
    console.log('Ingreso');
    $('#tblAsistenciaProfesor tr').remove(); 
    $('#lblHorasIngreso').text("");
    $('#txtCodigo').val("");
    $('#txtCodigo').focus();
}

function clickEvento()
{
    $('#btnLimpiarMarcado').on("click",function(e){
        fnClearElementos();
        e.preventDefault();
    })
}