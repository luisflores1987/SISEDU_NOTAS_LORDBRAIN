$(document).ready(function(){
    
    fnInicializarPopup();
    fnCrearEventoBotonBusqueda();
    fnAbrirPopup();
    fneventoClick();
    fnOcultarDiv();
    fnInicializarHorario();
   
});

function fnInicializarPopup(){    
    dlgConsularProfesor = $("#buscarProfesor").dialog({
        autoOpen: false,
        dialogClass: 'myTitleClass',        
        modal: true,
        width: 900,
        height: 600
    });
}

function fnAbrirPopup(){
    $("#imgIconAlumno").click(function(){
        $('#txtProfesor').val("");
        $('#spProfesor').text("");
        dlgConsularProfesor.dialog("open");       
    });
}

function fnCrearEventoBotonBusqueda()
{
    $("#btnBuscar").on("click",function(e){
        $.ajax({
            url:"registroHorarioServletController",
            data:{
                action:"buscarProfesor",
                tipoBusqueda:$("#rbFiltro:checked").val(),
                busqueda:$("#txtProfesorBusqueda").val()
            }            
        })
        .done(function(data){
            arregloProfesores = data.split(":");
            limparselect();
            if (data=='') {
                $('#tblProfesores').append('<tr><td colspan = "3"><label> No se encontraron datos ... </label></td></tr>'); 
            }
            else{
                for (var i = 0 ;i < arregloProfesores.length - 1; i++){
                    var idProfesor        =  arregloProfesores[i].split("-")[0];
                    var numeroDocumento  =  arregloProfesores[i].split("-")[1];            
                    var sNombreCompleto  =  arregloProfesores[i].split("-")[2];            
                    $('#tblProfesores').append('<tr><td style="width: 60px; text-align: center"><label>'+numeroDocumento+'</label></td><td style="width: 175px;"><label>'+sNombreCompleto+'</label></td><td style="width: 175px;"><label>'+idProfesor+'</label></td></tr>');                 
                    $('#tblProfesores tr td:nth-child(3)').hide();
                // if your table has header(th), use this
                //$('td:nth-child(2),th:nth-child(2)').hide();                
                }
            }
            fnResultadosClick();

        });
        function limparselect(){
            $('#tblProfesores tr').remove();
        }   
        e.preventDefault();        
    })
    
}
function fnResultadosClick()
{
    $('#tblProfesores tr').each(function(i){
        $(this).click(function(){
            $('#spProfesor').hide();
            var nNombreCompleto = $('#tblProfesores tr td:nth-child(2)').eq(i).text();
            var idProfesor = $('#tblProfesores tr td:nth-child(3)').eq(i).text();
            $('#txtProfesor').val(nNombreCompleto);
            $('#spProfesor').text(idProfesor);
            dlgConsularProfesor.dialog("close");
            $('#tblProfesores tr').remove();
            $('#txtProfesorBusqueda').val("");
            if (idProfesor == null)
            {}else
            {
                fnObtenerHorario(idProfesor);
            }
        });        
    });      
}

function fnIngresarProfesores(TipoTransaccion){
    

    if (TipoTransaccion == 1){
    
        $.ajax({
            url:"registroHorarioServletController",
            data:{
                action:"registroHorarioProfesor",
                tipoTransaccion: TipoTransaccion,
                fechaIngreso:$("#txtEntradaMasivo").val(),
                fechaSalida:$("#txtSalidaMasivo").val()
            //                sCadIdHorario:"",
            //                nIdProfesor:""
            }
        })
        .done(function(done){
            var sMensaje = done;
            console.log(sMensaje);
            jAlert(sMensaje);
        });        
    }
    else if(TipoTransaccion == 2){
        
        var i=""; 
        $("input[type='checkbox']:checked").each(
            function() {
                i = i + $(this).val() + ",";
            }
            ); 
        $.ajax({
            url:"registroHorarioServletController",
            data:{
                action:"registroHorarioProfesor",
                tipoTransaccion: TipoTransaccion,
                cadfechaIngreso:$("#txtEntradaMasivo").val(),
                cadfechaSalida:$("#txtSalidaMasivo").val(),
                cadHorario:i
            }
        })
        .done(function(done){
            var sMensaje = done;
            console.log(sMensaje);
            jAlert(sMensaje);
        });                 
    }
    else if(TipoTransaccion == 3){

        var a=0;
        $("input[name='horarioSemanaProfesor']").each(function(){
            a = a + 1;
            $.ajax({
                
                url:"registroHorarioServletController",
                data:{                    
                    action:"registroHorarioProfesor",
                    tipoTransaccion: TipoTransaccion,
                    fechaIngreso:$("#txtEntradaProfesor" + a).val(),
                    fechaSalida:$("#txtSalidaProfesor" + a).val(),
                    idHorario: $(this).val(),
                    bHorario: $(this).is(":checked"),
                    IdProfesor:$('#spProfesor').text().trim()
                }
            })
            .done(function(done){
                var sMensaje = done;
                console.log(sMensaje);
                jAlert(sMensaje);
            }); 

        })
    }
}


function fneventoClick(){
    
    $('#btnIngresarMasivo').on("click",function(){
        fnValidarDatos();
        if (!validado)
            return;
        fnValidarIngresoDias();
        if (!validadoDias)
            return;
        fnValidarSalidaHorarios();
        if (!validadoSalidaHorario)
            return;
        fnValidarIngresoHorarios();
        if (!validadoEntradaHorario)
            return;
        jConfirm('Desea ingresar los horarios?', 'Confirmación', function(r) { 
            if(r)
            { 
                fnIngresarProfesores(1);
                fnLimpiarText(1);
            }
        })
    })
 
    $('#btnIngresarDias').on("click",function(){
        
        fnValidarDatos();
        if (!validado)
            return;
        fnValidarIngresoDias();
        if (!validadoDias)
            return;
        fnValidarSalidaHorarios();
        if (!validadoSalidaHorario)
            return;
        fnValidarIngresoHorarios(); 
        if (!validadoEntradaHorario)
            return;
    
        jConfirm('Desea ingresar los horarios?', 'Confirmación', function(r) { 
            if(r)
            { 
                fnIngresarProfesores(2);
                fnLimpiarText(2);
            }
        })
    })
 
    $('#btnIngresarProfesor').on("click",function(){
        
        fnValidarDatos();
        if (!validado)
            return;
        fnValidarIngresoDias();
        if (!validadoDias)
            return;
        fnValidarSalidaHorarios();
        if (!validadoSalidaHorario)
            return;
        fnValidarIngresoHorarios();
        if (!validadoEntradaHorario)
            return; 
    
        jConfirm('Desea ingresar los horarios?', 'Confirmación', function(r) { 
            if(r)
            { 
            
                fnIngresarProfesores(3);
                fnLimpiarText(3);
            }
        })
    })
    
    $('#btnBloque').on("click", function(){
        $('.masivo').attr("disabled", "disabled");
        fnLimpiarText(1);
        $('#HorarioBloque').show();
        $('#HorarioProfesor').hide(); 
    })
    
    $('#btnDni').on("click", function(){
        $('.masivo').attr("disabled", "disabled");
        fnLimpiarText(1);
        $('#HorarioBloque').hide();
        $('#HorarioProfesor').show(); 
    })  
    
    $('#btnCancelarMasivo').on("click", function(){
        fnLimpiarText(1);
    })
    
    $('#btnCancelarDias').on("click", function(){
        $('#HorarioBloque').hide();
        fnLimpiarText(2);
        $('.masivo').removeAttr("disabled");
    })    
    
    $('#btnCancelarProfesor').on("click", function(){
        $('#HorarioProfesor').hide(); 
        fnLimpiarText(3);
        $('.masivo').removeAttr("disabled");
    })
    
    fnClickCheckBox();
    
}
 
function fnOcultarDiv(){
    $('#HorarioBloque').hide();
    $('#HorarioProfesor').hide();    
}

function fnLimpiarText(tipo){
    if (tipo=1) // masivo
    {
        $('#txtEntradaMasivo').val("");
        $('#txtSalidaMasivo').val("");
            
    }
    if(tipo=2) // por dias
    {
        $('#txtEntradaDias').val("");
        $('#txtSalidaDias').val("");
        $('input[type="checkbox"][name="horarioSemana"]').each(function(){
            $(this).prop('checked', false);
        })            
    }
    if(tipo=3) // por profesor

    {
        $('#txtProfesor').val("");    
        $('input[type="text"][name="EntradaProfesor"]').each(function(){
            $(this).val("");
        })
        $('input[type="text"][name="SalidaProfesor"]').each(function(){
            $(this).val("");
        }) 
                
        $('input[type="checkbox"][name="horarioSemanaProfesor"]').each(function(){
            $(this).prop('checked', false);
        })            
    }
}

function fnInicializarHorario(){
    $('input[type="text"][name="EntradaProfesor"]').each(function(){
        $(this).attr('disabled','disabled');
    })
    $('input[type="text"][name="SalidaProfesor"]').each(function(){
        $(this).attr('disabled','disabled');
    })     
    
    $('.entrada').timepicker({
        'step': 10
    });
    $('.salida').timepicker({
        'step': 10
    });
    $('#txtEntradaMasivo').timepicker({
        'step': 10
    });
    $('#txtSalidaMasivo').timepicker({
        'step': 10
    });   
    $('#txtEntradaDias').timepicker({
        'step': 10
    });    
    $('#txtSalidaDias').timepicker({
        'step': 10
    });   
}

function fnValidarDatos(){
    validado = true;
    if ($("#txtProfesor").val().length <= 0)
    {
        alert("Debe ingresar el nombre para poder realizar el registro");
        validado = false;
        return false;
    }    
}

function fnClickCheckBox()
{    
    $('input[type="checkbox"][name="horarioSemanaProfesor"]').each(function(){
        $(this).click(function(){
            if ($('#txtProfesor').val().length < 1){
                alert("Primero debe ingresar al profesor..");
                $('#txtProfesor').focus();
                $(this).prop('checked', false);
            }
            else{
                if ($(this).is(":checked"))
                {   
                    $("#txtEntradaProfesor" + $(this).val()).removeAttr('disabled');
                    $("#txtSalidaProfesor" + $(this).val()).removeAttr('disabled');
                
                    $("#txtEntradaProfesor" + $(this).val()).val("7:30am");
                    $("#txtSalidaProfesor" + $(this).val()).val("3:00pm");
                
                }else{
                    $("#txtEntradaProfesor" + $(this).val()).attr('disabled', 'disabled');
                    $("#txtSalidaProfesor" + $(this).val()).attr('disabled', 'disabled');

                    $("#txtEntradaProfesor" + $(this).val()).val("");
                    $("#txtSalidaProfesor" + $(this).val()).val("");
                }
            }
        })
    })  
}

function fnValidarIngresoDias() // valida datos si no se ha ingresado datos.
{
    validadoDias = true;
    var cantx = 0;
    var canty = 0;
    
    $("input[name='horarioSemanaProfesor']").each(function(){
        cantx = cantx + 1;
        if ($(this).val().length < 1) {
            canty = canty + 1;  
        }
    });
    
    if (cantx == canty)
    {
        alert("Debe Seleccionar un dia a ingresar..", "Mensaje"); 
        validadoDias = false;
        return false;
    }
}

function fnValidarIngresoHorarios() // valida datos si no se ha ingresado datos.
{
    validadoEntradaHorario = true;
    var cantx = 0;
    var canty = 0;
    
    $("input[name='EntradaProfesor']").each(function(){
        cantx = cantx + 1;
        if ($(this).val().length < 1) {
            canty = canty + 1;  
        }
    });
    
    if (cantx == canty)
    {
        alert("Debe Seleccionar al menos una hora de ingreso..", "Mensaje"); 
        validadoEntradaHorario = false;
        return false;
    }

}

function fnValidarSalidaHorarios() // valida datos si no se ha ingresado datos.
{
    validadoSalidaHorario = true;
    var cantx = 0;
    var canty = 0;
    
    $("input[name='SalidaProfesor']").each(function(){
        cantx = cantx + 1;
        if ($(this).val().length < 1) {
            canty = canty + 1;  
        }
    });
    
    if (cantx == canty)
    {
        alert("Debe Seleccionar al menos una hora de salida..", "Mensaje"); 
        validadoSalidaHorario = false;
        return false;
    }

}

function fnObtenerHorario(varIdProfesor)
{
    console.log("Ingreso al metodo");
    $.ajax({
        url:"registroHorarioServletController",
        data:{
            action:"obtenerHorarioProfesor",
            idProfesor:varIdProfesor
        }
    })
    .done(function(data){
        var arregloProfesorHorario = data.split('+');        
        if (data == null)
        {}
        else
        {
            fnLimpiarComponentesProfesor();
            for(var i = 0;i<arregloProfesorHorario.length - 1;i++)
            {
                var nIdProfesor = arregloProfesorHorario[i].split("*")[0];
                var nIdHorario = arregloProfesorHorario[i].split("*")[1];
                var sHoraIngreso = arregloProfesorHorario[i].split("*")[2];
                var sHoraSalida = arregloProfesorHorario[i].split("*")[3];

                $('#chkLu' + nIdHorario).prop('checked',true);
                $('#txtEntradaProfesor' + nIdHorario).val(sHoraIngreso);
                $('#txtSalidaProfesor' + nIdHorario).val(sHoraSalida);          
                
                //habilitar elementos
                $('#txtEntradaProfesor' + nIdHorario).removeAttr('disabled');
                $('#txtSalidaProfesor' + nIdHorario).removeAttr('disabled');
                //---
            }
            
        }
        
    });
}

function fnLimpiarComponentesProfesor()
{
    $('input[type="text"][name="EntradaProfesor"]').each(function(){
        $(this).val("");
    })
    $('input[type="text"][name="SalidaProfesor"]').each(function(){
        $(this).val("");
    }) 
                
    $('input[type="checkbox"][name="horarioSemanaProfesor"]').each(function(){
        $(this).prop('checked', false);
    })        
}