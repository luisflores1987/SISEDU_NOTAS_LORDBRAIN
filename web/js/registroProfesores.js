$(document).ready(function(){
    
    fnInicializarValores(); 
    
    eventoPresionarTab();  
    
    fneventoClickImagenes(); 
        
    fnHabilitarPopupBusqueda();    
  
});

function resultadosClickHandlerConsultaProfesores() {        
    dlgConsularProfesor.dialog("open");
}


function resultadosClickVerDocumentos() {        
    dlgVerDocumentos.dialog("open");
}


function eventoPresionarTab(){
    
    // Al presionar cualquier tecla en cualquier campo de texto, ejectuamos la siguiente función
    $('.classRegProfesor').on('keydown', function(e){
        // Solo nos importa si la tecla presionada fue ENTER... (Para ver el código de otras teclas: http://www.webonweboff.com/tips/js/event_key_codes.aspx)
        if(e.keyCode === 13)
        {
            // Obtenemos el número del tabindex del campo actual
            var currentTabIndex = $(this).attr('tabindex');
            // Le sumamos 1 :P
            var nextTabIndex    = parseInt(currentTabIndex) + 1;
            // Obtenemos (si existe) el siguiente elemento usando la variable nextTabIndex
            var nextField       = $('[tabindex='+nextTabIndex+']');
            // Si se encontró un elemento:
            if(nextField.length > 0)
            {
                // Hacerle focus / seleccionarlo
                nextField.focus();
                // Ignorar el funcionamiento predeterminado (enviar el formulario)
                e.preventDefault();
            }
        // Si no se encontro ningún elemento, no hacemos nada (se envia el formulario)
        }
    }); 
    
}

function ingresarProfesor() {
    validar();
    if (!validado) {
        return;
    }       
    jConfirm('Desea guardar los cambios efectuados?', 'Confirmación', function(r) { 
        if(r)
        {     
            $.ajax({
                url: "registroPersonaServletController",
                data: {               
                    action: "ingresarDatosProfesores",
                    tipoPersona : "3",
                    txtCodigo : $("#txtCodigo").val(),
                    txtDni: $("#txtDni").val(),
                    txtApPaterno: $("#txtApPaterno").val(),
                    txtApMaterno: $("#txtApMaterno").val(),
                    txtNombres: $("#txtNombres").val(),
                    txtFechaNacimiento: $("#txtFechaNacimiento").val(),
                    rbSexo: $("input:radio[name=SEXO]:checked").val(),
                    txtFechaIngreso: $("#txtFechaIngreso").val(),
                    txtDireccionActual: $("#txtDireccionActual").val(),
                    txtUsuario:$("#txtUsuario").text().trim()
                }
            })
            .done(function (data, status, jqXhr) { 
       
                resultado = data.split(",");
                var codResultado = resultado[0];
                var descResultado = resultado[1]; 
                jAlert(descResultado, 'mensaje');
                fnCancelarIngresoProfesor(codResultado);
            });
        }
    })
}

function validar() {
    validado = true;
    $(".classRegProfesor").each(function () {
        if ($(this).val().length < 1) {
            alert("El campo " + $(this).attr("name") + " es requerido");
            validado = false;
            return false;
        }
    });
}


function editarProfesor() {
    var i=""; 
    validar();
    if (!validado) {
        return;
    }           
    jConfirm('Desea guardar los cambios efectuados?', 'Confirmación', function(r) { 
        if(r)
        {     
            $.ajax({
                url: "registroPersonaServletController",
                data: {               
                    action: "editarDatosProfesores",
                    txtIdPersona: $("#spIdPersona").text(),
                    tipoPersona : "3",
                    txtCodigo : $("#txtCodigo").val(),
                    txtDni: $("#txtDni").val(),
                    txtApPaterno: $("#txtApPaterno").val(),
                    txtApMaterno: $("#txtApMaterno").val(),
                    txtNombres: $("#txtNombres").val(),
                    txtFechaNacimiento: $("#txtFechaNacimiento").val(),
                    rbSexo: $("input:radio[name=SEXO]:checked").val(),
                    txtFechaIngreso: $("#txtFechaIngreso").val(),
                    txtDireccionActual: $("#txtDireccionActual").val(),
                    txtUsuario:$("#txtUsuario").text().trim()
                }
            })
            .done(function (data, status, jqXhr) { 
       
                resultado = data.split(",");
                var codResultado = resultado[0];
                var descResultado = resultado[1]; 
                jAlert(descResultado, 'mensaje');
                fnCancelarIngresoProfesor(codResultado)
            });
        }
    })
}


function fnCancelarIngresoProfesor(codResultado){    
    if (codResultado == "-1"){
        $("#txtCodigo").focus();
    }else
    {
        $(".classRegProfesor").each(function () {
            if ($(this).val() == null){}
            else{
                if ($(this).val().length > 0) {
                    $(this).val("");                     
                }
            }                
        });
        
        $(".classProfesoresFecha").each(function () {
            if ($(this).val() == null){}
            else{
                if ($(this).val().length > 0) {
                    $(this).val("");                     
                }
            }                
        });        
        fnCancelarIngresoDatos(); 
    }
}

function buscarProfesor(){
    $.ajax({
        type: 'GET',        
        url: "registroPersonaServletController",
        data: {
            action: "consultaDatosProfesor",
            tipoPersona : "3",
            txtProfesor: $("#txtProfesor").val(),
            rbFiltro: $("#rbFiltro:checked").val()     
        }
    })
    .done(function (data, status, jqXhr) { 
        arregloAlumnos = data.split(":");
        limparselect();
        if (data=='') {
            $('#tblProfesores').append('<tr><td colspan = "4"><label> No se encontraron datos ... </label></td></tr>'); 
        }
        else{
            for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
                var idPersona =  arregloAlumnos[i].split("-")[0];
                var numeroDocumento  =  arregloAlumnos[i].split("-")[1];            
                var sNombreCompleto=  arregloAlumnos[i].split("-")[2];            
                $('#tblProfesores').append('<tr><td style="width: 60px; text-align: center"><label>'+numeroDocumento+'</label></td><td style="width: 175px;"><label>'+sNombreCompleto+'</label></td><td><label>'+idPersona+'</label></td></tr>');                 
                $('#tblProfesores tr td:nth-child(3)').hide();
            // if your table has header(th), use this
            //$('td:nth-child(2),th:nth-child(2)').hide();                
            }
            fnCambiarIngresoDeCampos();
        }
        resultadosClickHandler(); 
        
    });  
    function limparselect(){
        $('#tblProfesores tr').remove();       
    }
}

function resultadosClickHandler() { 
    
    $("#tblProfesores tr").each(function(i){ 
        $(this).click(function(){
            $("#spIdPersona").hide();
            
            var idPersona = $('#tblProfesores tr td:nth-child(3)').eq(i).text();
            $.ajax({
                url: "registroPersonaServletController",
                data: {
                    action: "obtenerDatosProfesor",
                    tipoPersona : "3",
                    id: idPersona
                }                
            })
            .done(function (data, status, jqXhr) {     
                arregloAlumnos = data.split("*");  
                limparselect();                
                if (data=='') {
                    $('#tblProfesores').append('<tr><td colspan = "4"><label> No se encontraron datos ... </label></td></tr>'); 
                }
                else{
                    for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
                        var idPersona =  arregloAlumnos[i].split("+")[0];
                        var numeroDocumento  =  arregloAlumnos[i].split("+")[1];            
                        var apellidoPaterno=  arregloAlumnos[i].split("+")[2];            
                        var apellidoMaterno =  arregloAlumnos[i].split("+")[3];
                        var nombres =  arregloAlumnos[i].split("+")[4]; 
                        var fechaNacimiento =  arregloAlumnos[i].split("+")[5]; 
                        var sexo =  arregloAlumnos[i].split("+")[6].trim(); 
                        var sFechaIngreso =  arregloAlumnos[i].split("+")[7];   
                        var nCodigo =  arregloAlumnos[i].split("+")[8];

                        /* match de variables con id de html*/ 
                        $('#spIdPersona').text(idPersona);
                        $('#txtCodigo').val(nCodigo);
                        $('#txtDni').val(numeroDocumento);
                        $('#txtApPaterno').val(apellidoPaterno);
                        $('#txtApMaterno').val(apellidoMaterno);
                        $('#txtNombres').val(nombres);
                        $('#txtFechaNacimiento').val(fechaNacimiento);
                        // sexo es radio buton
                        if(sexo === 'M'){
                            $('input:radio[name="SEXO"][value="M"]').prop('checked', true);
                            $('input:radio[name="SEXO"][value="F"]').prop('checked', false);                             
                        }
                        else if(sexo === 'F'){
                            $('input:radio[name="SEXO"][value="F"]').prop('checked', true);
                            $('input:radio[name="SEXO"][value="M"]').prop('checked', false);                             
                        }                        
                        $('#txtFechaIngreso').val(sFechaIngreso);                        
                    }
                }                
            });   
            function limparselect(){
                $('#tblProfesores tr').remove();
                dlgConsularProfesor.dialog("close");
            }             
        });        
    });    
}

function fnInicializarIngresoDatos(varnTipoIngreso)
{

    if(varnTipoIngreso == 1) // ingreso
    {
        
        fnCambiarIngresoDeCampos();
        $("#btnIngresar").show();
        $("#btnEditar").hide();
        $("#btnCancelar").show();
        $("#imgIconAlumno").hide()
        
        
    }else if(varnTipoIngreso == 2) // editar
    {
        $("#btnIngresar").hide();
        $("#btnEditar").show();
        $("#btnCancelar").show();  
        $("#imgIconAlumno").show()
    }
}

function fnCancelarIngresoDatos()
{
    $(".classRegProfesor").attr("disabled", "disabled");
    $(".classRegProfesorBoton").attr("disabled", "disabled");        
    $(".classRegProfesor").css("background", "#EBEBE4");
    $(".classProfesoresFecha").attr("disabled", "disabled");    
    $(".classProfesoresFecha").css("background", "#EBEBE4");
    $(".classRegProfesorBoton").hide();
    
}

function fnInicializarValores()
{
    // deshabilitarCampos
    //los demas campos
    $(".classRegProfesor").attr("disabled", "disabled");       
    $(".classRegProfesor").css("background", "#EBEBE4");
    //los campo de fecha
    $(".classProfesoresFecha").attr("disabled", "disabled");       
    $(".classProfesoresFecha").css("background", "#EBEBE4");    
    //los botones.   
    $(".classRegProfesorBoton").hide(); 
// fin       
}

function fneventoClickImagenes()
{
    $("#imgIconAlumno").click(function(){
        resultadosClickHandlerConsultaProfesores();        
    });
    
    $("#imgIconNewAlumno").click(function(){
        fnInicializarIngresoDatos(1);         
    });   
        
    $("#imgIconEditAlumno").on("click",function(){
        fnInicializarIngresoDatos(2);     
    }) 
           
    $("#btnIngresar").on("click",function(e){
        ingresarProfesor();        
        e.preventDefault();
    })   
    
    $("#btnEditar").on("click",function(e){
        editarProfesor();        
        e.preventDefault();
    })     
    
    $("#btnCancelar").on("click",function(){
        fnCancelarIngresoProfesor();
    }) 

    $("#btnBuscar").on("click",function(e){
        buscarProfesor();
        e.preventDefault();
    })     
}
function fnHabilitarPopupBusqueda(){
    dlgConsularProfesor = $("#buscarProfesor").dialog({
        autoOpen: false,
        dialogClass: 'myTitleClass',        
        modal: true,
        width: 900,
        height: 600
    });  
}  

function resultadosClickHandlerConsultaProfesores() {        
    dlgConsularProfesor.dialog("open");
}

function fnCambiarIngresoDeCampos(){
    $(".classRegProfesor").removeAttr("disabled"); 
    $(".classRegProfesorBoton").removeAttr("disabled");
    $(".classProfesoresFecha").removeAttr("disabled");
    
    $(".classRegProfesor").css("background", "#FFFFAA");
    $(".classProfesoresFecha").css("background", "#FFFFAA");
    $("#txtCodigo").focus();    
}