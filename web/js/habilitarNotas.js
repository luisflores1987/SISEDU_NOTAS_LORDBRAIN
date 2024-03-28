$(document).ready(function(){
    fnInicializarCombos();
    fnEventoClick();   
});

function fnInicializarCombos(){
    // deshabilitar los componentes
    $("#cboGradoNotas").attr("disabled", "disabled");
    $("#cboCursoNotas").attr("disabled", "disabled");
    // fin - deshabilitar
    fnLLenarProfesor();
    $("#cboProfesorNotas").on('change', function(){        
        fnLLenarGrado();
        if ($("#cboProfesorNotas").val().trim() === "")
        {
            $("#cboGradoNotas").attr("disabled", "disabled");           
        }else{
            $("#cboGradoNotas").removeAttr("disabled");        
        }
    });
    
    $("#cboGradoNotas").on('change', function(){        
        fnLLenarCurso();
        if ($("#cboGradoNotas").val().trim() === "")
        {
            $("#cboCursoNotas").attr("disabled", "disabled");           
        }else{
            $("#cboCursoNotas").removeAttr("disabled");        
        }        
    }); 
    
    $("#cboTipoNotaNotas").on('change',function(){
        fnCargarFechasActuales();
    });
}

function fnCargarFechasActuales(){
    $.ajax({
        url:"habilitarNotaServletController",
        data:{
            action:"obtenerFechas",
            tipoNota:$("#cboTipoNotaNotas").val().trim()
        }
    })
    .done(function(data){
        var arregloFechas = data.split(":");
        limparFechas();
        if (data == ''){
             
        }else{
            for(var i=0; i<arregloFechas.length - 1;i++)
            {
                if(arregloFechas[i].split(",")[0] == '1'){
                    $("#txtFiBimestre1").val(arregloFechas[i].split(",")[1]);
                    $("#txtFfBimestre1").val(arregloFechas[i].split(",")[2]);
                } else if(arregloFechas[i].split(",")[0] == '2'){
                    $("#txtFiBimestre2").val(arregloFechas[i].split(",")[1]);
                    $("#txtFfBimestre2").val(arregloFechas[i].split(",")[2]);
                } else if(arregloFechas[i].split(",")[0] == '3'){
                    $("#txtFiBimestre3").val(arregloFechas[i].split(",")[1]);
                    $("#txtFfBimestre3").val(arregloFechas[i].split(",")[2]);
                } else if(arregloFechas[i].split(",")[0] == '4'){
                    $("#txtFiBimestre4").val(arregloFechas[i].split(",")[1]);
                    $("#txtFfBimestre4").val(arregloFechas[i].split(",")[2]);
                }
            }
        }
        function limparFechas(){
            $("#txtFiBimestre1").val("");
            $("#txtFfBimestre1").val("");

            $("#txtFiBimestre2").val("");
            $("#txtFfBimestre2").val("");

            $("#txtFiBimestre3").val("");
            $("#txtFfBimestre3").val("");

            $("#txtFiBimestre4").val("");
            $("#txtFfBimestre4").val("");
        } 
    });    
}

function fnLLenarProfesor(){
    $.ajax({
        url:"obtenerReporteAsistenciaServletController",
        data:{
            action:"conseguirProfesores"           
        }
    })
    .done(function(data){
        var arregloProfesor = data.split("+");
        limparselectProfesores();
        if (data == "")
            $('#cboProfesorNotas').append('<option> No se encontraron datos ... </option>');
        else
        {
            $('#cboProfesorNotas').append('<option></option>');
            for(var i = 0;i< arregloProfesor.length - 1;i++)
            {
                var nidPersona = arregloProfesor[i].split("*")[0];
                var sDatosPersonales = arregloProfesor[i].split("*")[1];
                $('#cboProfesorNotas').append('<option value = '+ nidPersona + '>' + sDatosPersonales + '</option>');
            }                    
        }
        function limparselectProfesores(){
            $('#cboProfesorNotas option').remove();
        }                
    });       
}

function fnLLenarCurso(){

    $.ajax({
        url: 'habilitarNotaServletController',
        data:{
            action:'obtenerCurso',
            IdPersona:$('#cboProfesorNotas option:selected').val().trim(),
            IdGrado:$('#cboGradoNotas option:selected').val().trim()
        }
    })
        
    .done(function (data, status, jqXhr) {

        pegadados = data.split(":");
        limparselect();
        if(data == '')
            $('#cboCursoNotas').append('<option></option>');
        else{
            $('#cboCursoNotas').append('<option></option>');
            for(var i = 0; i < pegadados.length - 1; i++){
                var codigoCurso = pegadados[i].split("-")[0]; 
                var descripcionCurso = pegadados[i].split("-")[1];
                $('#cboCursoNotas').append('<option value ="'+codigoCurso.trim()+'">'+descripcionCurso+'</option>');
            }
        }
    });
        
    function limparselect(){
        $('#cboCursoNotas option').remove();
    } 
}

function fnLLenarGrado(){

    $.ajax({
        url: 'habilitarNotaServletController',
        data:{
            action:'obtenerGrado',            
            IdPersona:$('#cboProfesorNotas option:selected').val().trim()           
        }
    })
        
    .done(function (data, status, jqXhr) {

        pegadados = data.split(":");
        limparselect();
        if(data == '')
            $('#cboGradoNotas').append('<option></option>');
        else{
            $('#cboGradoNotas').append('<option></option>');
            for(var i = 0; i < pegadados.length - 1; i++){
                var codigoCurso = pegadados[i].split("-")[0]; 
                var descripcionCurso = pegadados[i].split("-")[1];
                $('#cboGradoNotas').append('<option value ="'+codigoCurso.trim()+'">'+descripcionCurso+'</option>');
            }
        }
    });
        
    function limparselect(){
        $('#cboGradoNotas option').remove();
    } 
}

function fnAceptarTotal(){
    var cantidad = 4; 
    
    validarIngresoTotal();
    if(!validado){
        return;
    }
    for(var i = 1; i < cantidad + 1; i++){             
    
        $.ajax({
            url: 'habilitarNotaServletController',
            data:{
                action:'habilitarNotasTotal',            
                sFechaCierreInicial:$("#txtFiBimestre" + i).val(),
                sFechaCierreFinal:$("#txtFfBimestre"+i).val(),
                bTipoNota:$('#cboTipoNotaNotas').val(),
                sIdBimestre:i            
            }
        })
        
        .done(function (data, status, jqXhr) {

            if(data == '0' && data == '')
            {
                jAlert("Error", "Mensaje");   
            }
            else {
                jAlert("Se ingreso Correctamente los registros", "Mensaje");   
            }
        });
    } 
}

function fnAceptarDetalle(){
    validarIngreso();
    if(!validado){
        return;
    }
    
    $.ajax({
        url: 'habilitarNotaServletController',
        data:{
            action:'habilitarNotasProfesor',            
            idPersonaProfesor:$("#cboProfesorNotas").val(),
            idGrado:$("#cboGradoNotas").val(),
            idCurso:$("#cboCursoNotas").val(),  
            idBimestre:$("#cboBimestreNotas").val(),
            idNota:$("#cboTipoNotaNotas").val(),
            Bloquear:$('input:radio[name="bloquear"]:checked').val()
        }
    })
        
    .done(function (data, status, jqXhr) {
        console.log(data);
        if(data == '0' && data == '')
        {
            jAlert("Se han bloqueado correctamente los registros", "Mensaje");   
        }
        else{
            jAlert("Se han bloqueado correctamente los registros", "Mensaje");   
        }
    }); 
}

function fnEventoClick(){
    $("#btnhabilitarTotalGuardar").click(function(){
        fnAceptarTotal();
    });  
    
    $("#btnhabilitarDetalle").click(function(){
        fnAceptarDetalle();
    }); 
    
    $("#btnLimpiarDetalle").click(function(){
        limpiarDetalle();
    });
    
    $("#btnhabilitarTotalLimpiar").click(function(){
        limpiarTotal();
    });    
}

function validarIngreso() {
    validado = true;
    $(".clsObligatorio:not(.opcional)").each(function () {
        if ($(this).val().length < 1) {
            jAlert("El campo " + $(this).attr("name") + " es requerido","Mensaje");
            validado = false;
            return false;
        }
    });
}

function validarIngresoTotal() {
    validado = true;
    $(".clsObligatorioTotal:not(.opcional)").each(function () {
        if ($(this).val().length < 1) {
            jAlert("El campo " + $(this).attr("name") + " es requerido","Mensaje");
            validado = false;
            return false;
        }
    });
    $(".clsObligatorioTotalTipo:not(.opcional)").each(function () {
        if ($(this).val().length < 1) {
            jAlert("El campo " + $(this).attr("name") + " es requerido","Mensaje");
            validado = false;
            return false;
        }
    });    
}

function limpiarDetalle() {
    $(".clsObligatorio:not(.opcional)").each(function () {
        $(this).val("");
    });
    
    $(".clsNoObligatorio:not(.opcional)").each(function () {
        $(this).val("");
    });
}

function limpiarTotal() {
    
    $(".clsObligatorioTotal:not(.opcional)").each(function () {
        $(this).val("");
    });
    
    $(".clsObligatorioTotalTipo:not(.opcional)").each(function () {
        $(this).val("");
    });    
}



