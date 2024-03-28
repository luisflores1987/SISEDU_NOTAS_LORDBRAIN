$(document).ready(function(){
    fnObtenerGrado();
    //fnPresionarBoton();
    
    $("#btnExportarBuscarPagosAlumno").click(function() {
        //window.open('data:application/vnd.ms-excel,' + escape(($('#divConsolidado').html())));        // se coloca el metodo escape para evitar la codifcacion utf-8, la cual nos cambia la letra ñ
        $.ajax({
            url: 'obtenerConsolidadoNotasServletController',
            data:{
                action:'imprimir',
                idGrado: $('#cboGrado').val(),
                idBimestre : $('#cboBimestre').val(),
                sBimestre : $('#cboBimestre option:selected').text(),
                sGrado : $('#cboGrado option:selected').text()
            }, 
            beforeSend: function () {
                $('body').addClass('loading'); //Agregamos la clase loading al body
            }, 
            error: function () {
                $('body').removeClass('loading'); //Removemos la clase loading
            }            
        })
        .done(function(data){
            var link = document.createElement("a");
            link.download = $('#cboGrado option:selected').text() + "_" + $('#cboBimestre option:selected').text() + "_Consolidado_Notas.xls";
            link.href = data;
            link.click();
            $('body').removeClass('loading'); //Removemos la clase loading
        });    
    });
});


function fnObtenerGrado(){
    $.ajax({
        url: 'reporteNotasAlumnoServletController',
        data:{
            action:'obtenerGrado'
        }                   
    })
    .done(function(data){
        var arregloGrado = data.split("+");
        fnLimpiar();
        if(data == null){
            $('#cboGrado').append('<option>No existe registro..</option>')
        }
        else{
            for(var i=0;i<arregloGrado.length - 1;i++)
            {
                var nidGrado = arregloGrado[i].split("*")[0];
                var sDescripcion = arregloGrado[i].split("*")[1];
                $('#cboGrado').append('<option value ="'+nidGrado.trim()+'">'+sDescripcion+'</option>');
                
            }
        }
        function  fnLimpiar(){
            $('#cboGrado option').remove();
        }       
    });    
}

function fnMostrarTablas()
{
    $('#mostrarConsolidado').css("display","display");
}

