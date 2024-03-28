$(document).ready(function(){
    $("#txtFechaNacimiento").attr("disabled", "disabled");
    $(".classRegAlumno").attr("disabled", "disabled");
    $(".classRegAlumnoBoton").attr("disabled", "disabled"); 
    $(".classRegAlumnoAp").attr("disabled", "disabled");            
    $(".classRegAlumnoAp").css("background", "#EBEBE4"); 
    
    $(".classRegAlumnoAlternativo").attr("disabled", "disabled");            
    $(".classRegAlumnoAlternativo").css("background", "#EBEBE4");
    
    $(".classRegAlumnoProc").attr("disabled", "disabled");            
    $(".classRegAlumnoProc").css("background", "#EBEBE4");
  
    $(".regAlumnoCbo").attr("disabled", "disabled");
    $(".regAlumnoCbo").css("background", "#EBEBE4");
    
    $("#btnIngresar").hide();
    $("#btnEditar").hide();
    $("#btnCancelar").hide();
    $("#imgIconDeleteAlumno").hide();   
    $("#imgIconEnabledAlumno").hide();   
   
    //   //  eventoPresionarTab();
    
    fnCargarDocumentos();
    fnCargarProcedencia();
    fnCargarParentesco();
    fnCargarGradoInstruccion();
    fnCargarNivel();
    fnCargarGrado();
    fnCargarTipoMatricula();
    fnCargarSeccion();
    
    
    $("#cboParentescoAp").change(function(){
        if ($(this).val() == 7){
            $("#txtOtroAp").removeAttr("disabled");
            $(".classRegAlumnoAp").css("background", "#FFFFAA"); 
            $("#txtOtroAp").focus();
        }
        else
        {
            $("#txtOtroAp").attr("disabled", "disabled");            
            $(".classRegAlumnoAp").css("background", "#EBEBE4");  
            $("#txtOtroAp").val("");
        }
    })
    
    $("#cboParentescoAa").change(function(){
        if ($(this).val() == 7){
            $("#txtOtroAa").removeAttr("disabled");
            $("#txtOtroAa").focus();
        }
        else
        {
            $("#txtOtroAa").attr("disabled", "disabled");            
            $(".classRegAlumnoAlternativo").css("background", "#EBEBE4");  
            $("#txtOtroAa").val("");
        }
    })    
    
    $("#cboProcedencia").change(function(){
        if ($(this).val() == 1 || $(this).val() == 3){
            $("#txtOtroProc").removeAttr("disabled");
            $(".classRegAlumnoProc").css("background", "#FFFFAA"); 
            $("#txtOtroProc").focus();
        }
        else
        {
            $("#txtOtroProc").attr("disabled", "disabled");            
            $(".classRegAlumnoProc").css("background", "#EBEBE4");   
            $("#txtOtroProc").val("");
        }
    })       
  
    
    $("#imgIconAlumno").click(function(e){
        e.preventDefault();
        resultadosClickHandlerConsulta();         
    });
    
    $("#imgIconNewAlumno").click(function(e){
        e.preventDefault();
        resultadosClickHandlerRegistrar();  
    });
    
    $("#lblDocumentos").click(function(){
        resultadosClickVerDocumentos();        
    });
    
    $("#cboNivel").on('change', function(){        
        fnObtenerGrado();
    });
        
    dlgConsularAlumno = $("#buscarAlumno").dialog({
        autoOpen: false,
        dialogClass: 'myTitleClass',        
        modal: true,
        width: 900,
        height: 600
    });
    
    dlgEliminarAlumno = $("#eliminarAlumno").dialog({
        autoOpen: false,
        dialogClass: 'Eliminar Alumno',        
        modal: true,
        width: 574,
        height: 104
        
    });    
    
    dlgHabilitarAlumno = $("#habilitarAlumno").dialog({
        autoOpen: false,
        dialogClass: 'Habilitar Alumno',        
        modal: true,
        width: 574,
        height: 104
        
    });    
    
    dlgRegistrarAlumno = $("#insertarAlumno").dialog({
        autoOpen: false,
        dialogClass: 'myTitleClass',        
        modal: true,
        width: 600,
        height: 300
    });
    
    dlgVerDocumentos = $("#verDocumentos").dialog({
        autoOpen: false,
        dialogClass: 'myTitleClass',        
        modal: true,
        width: 600,
        height: 300
    });    
    
    
    $("#imgIconNewAlumno").on("click",function(){
        $('input[type="checkbox"][name="nameDocumentos"]').removeAttr("disabled", "disabled"); 
        $(".classRegAlumno").removeAttr("disabled"); 
        $(".classRegAlumnoBoton").removeAttr("disabled");  
        $(".classRegAlumnoAlternativo").removeAttr("disabled"); 
        $(".classRegAlumno").css("background", "#FFFFAA");
        $("#txtFechaNacimiento").css("background", "#FFFFAA");
        $(".regAlumnoCbo").removeAttr("disabled");  
        $(".regAlumnoCbo").css("background", "#FFFFAA");
        $("#txtDni").focus();
        $("#btnIngresar").show();
        $("#btnEditar").hide();
        $("#imgIconAlumno").hide();
        $("#btnCancelar").show();
    }) 
    
    $("#imgIconEditAlumno").on("click",function(e){
        fnHabilitarEditar();    
        e.preventDefault();
    }) 
    
    $("#imgIconQuitAlumno").on("click",function(e){
        fnCancelarRegistro();
        e.preventDefault();
    }) 
        
    $("#btnIngresar").on("click",function(e){
        ingresarAlumno();        
        e.preventDefault();
    })   
    
    $("#btnEditar").on("click",function(e){
        editarAlumno();        
        e.preventDefault();
    })     
    
    $("#btnCancelar").on("click",function(){
        fnCancelarRegistro();
    })
    
    $("#imgIconDeleteAlumno").on("click",function(e){
        abrirVentanaEliminar();
        e.preventDefault();
    })
    
    $("#imgIconEnabledAlumno").on("click",function(e){
        abrirVentanaHabilitar();
        e.preventDefault();
    })
    
    $("#btnInhabilitar").on("click",function(e){
        fnEliminarRegistro(); 
        e.preventDefault();
    })  
    
    $("#btnCancelarInhabilitar").on("click",function(e){
        $("#txtFechaRegistro").val("");
        dlgEliminarAlumno.dialog("close");
    });
    /*
    Procedimiento para busqueda de pacientes    
    Inicio
     */
    $("#btnBuscar").on("click",function(e){
        buscarAlumno();
        e.preventDefault();
    })  
    
    /*Evento llamar apoderado*/
    
    conseguirAutocompletado();  

    
    $("#txtNombresAp").dblclick(function(){
        $.ajax({
            url: "registroPersonaServletController",
            data: {
                action: "conseguirApoderadoPorNombre",
                nombreApoderado : $("#txtNombresAp").val().trim() 
            }
        })
        .done(function(data, status, jqXhr){
            arregloResultado = data.split(","); // SIEMPRE SE TIENE QUE CAPTURAR EL ARREGLO DEL DATO DESDE LA PRIMERA POSICIÓN.
            if (data=='')
            {
                $("#lblMensajeApoderado").text("No existe el apoderado ... ");
                $('#cboParentescoAp option[value=0]').attr('selected','selected');
            
                //Observacion Parentesco
                $('#txtOtroAp').val('');
            
                //Telefono de contacto
                $('#txtTelefonoAp').val('');  
            
                // grado de instruccion Ap
                $('#cboGradoInstruccionAp option[value=0]').attr('selected','selected');                             
            }else{
                       
                //parentesco
                $('#cboParentescoAp option[value=' + arregloResultado[0] + ']').attr('selected','selected');
            
                //Observacion Parentesco
                $('#txtOtroAp').val(arregloResultado[1]);
            
                //Telefono de contacto
                $('#txtTelefonoAp').val(arregloResultado[2]);  
            
                // grado de instruccion Ap
                $('#cboGradoInstruccionAp option[value=' + arregloResultado[3] + ']').attr('selected','selected');             
                $("#lblMensajeApoderado").text("");                
            }

        } );
    });   
    
    $('div#buscarAlumno').on('dialogclose', function(event) {
        $('#txtPaciente').val('');
        $('#tblAlumnos tr').remove();  
    });

});

function resultadosClickHandlerConsulta() {        
    dlgConsularAlumno.dialog("open");
}

function abrirVentanaEliminar() {        
    dlgEliminarAlumno.dialog("open");
}

function abrirVentanaHabilitar() {        
    dlgHabilitarAlumno.dialog("open");
}


function resultadosClickHandlerRegistrar() {        
    dlgRegistrarAlumno.dialog("open");
}

function resultadosClickVerDocumentos() {        
    dlgVerDocumentos.dialog("open");
}


//function eventoPresionarTab(){
//    
//    // Al presionar cualquier tecla en cualquier campo de texto, ejectuamos la siguiente función
//    $('.classRegAlumno .regAlumnoCbo').on('keydown', function(e){
//        // Solo nos importa si la tecla presionada fue ENTER... (Para ver el código de otras teclas: http://www.webonweboff.com/tips/js/event_key_codes.aspx)
//        if(e.keyCode === 13)
//        {
//            // Obtenemos el número del tabindex del campo actual
//            var currentTabIndex = $(this).attr('tabindex');
//            // Le sumamos 1 :P
//            var nextTabIndex    = parseInt(currentTabIndex) + 1;
//            // Obtenemos (si existe) el siguiente elemento usando la variable nextTabIndex
//            var nextField       = $('[tabindex='+nextTabIndex+']');
//            // Si se encontró un elemento:
//            if(nextField.length > 0)
//            {
//                // Hacerle focus / seleccionarlo
//                nextField.focus();
//                // Ignorar el funcionamiento predeterminado (enviar el formulario)
//                e.preventDefault();
//            }
//        // Si no se encontro ningún elemento, no hacemos nada (se envia el formulario)
//        }
//    }); 
//    
//}

function ingresarAlumno() {
    var i=""; 
    validar();
    if (!validado) {
        return;
    }
       
    $("input[type='checkbox'][name='nameDocumentos']:checked").each(
        function() {
            i = i + $(this).val() + ",";
        }
        );
            
    jConfirm('Desea guardar los cambios efectuados?', 'Confirmación', function(r) { 
        if(r)
        {     
            $.ajax({
                url: "registroPersonaServletController",
                data: {
                    action: "ingresarDatos",
                    txtDni: $("#txtDni").val(),
                    txtApPaterno: $("#txtApPaterno").val(),
                    txtApMaterno: $("#txtApMaterno").val(),
                    txtNombres: $("#txtNombres").val(),
                    txtFechaNacimiento: $("#txtFechaNacimiento").val(),
                    rbSexo: $("input:radio[name=SEXO]:checked").val(),
                    cboNivel: $("#cboNivel").val(),
                    cboGrado: $("#cboGrado").val(),
                    txtDireccionActual: $("#txtDireccionActual").val(),
                    cboProcedencia: $("#cboProcedencia").val(),
                    txtOtroProc: $("#txtOtroProc").val(),
                    //------ Apoderado Principal
                    txtNombresAp: $("#txtNombresAp").val(),
                    cboParentescoAp: $("#cboParentescoAp").val(),
                    txtOtroAp : $("#txtOtroAp").val(),            
                    txtTelefonoAp: $("#txtTelefonoAp").val(),    
                    cboGradoInstruccionAp: $("#cboGradoInstruccionAp").val(), 
                    //------ Apoderado Alternativo
                    txtNombresAa: $("#txtNombresAa").val(),
                    cboParentescoAa: $("#cboParentescoAa").val(),
                    txtOtroAa : $("#txtOtroAa").val(),            
                    txtTelefonoAa: $("#txtTelefonoAa").val(),    
                    cboGradoInstruccionAa: $("#cboGradoInstruccionAa").val(), 
                    //------            
                    documentos :  $('input:radio[name="SEXO"]').val(),
                    txtUsuario:$("#txtUsuario").text().trim(),            
                    nidDocumentos:i,
                    cboTipoMatricula:$("#cboTipoMatricula").val()
                }
            })
            .done(function (data, status, jqXhr) { 
       
                resultado = data.split(",");
                var codResultado = resultado[0];
                var descResultado = resultado[1]; 
                jAlert(descResultado, 'mensaje');
                limpiar(codResultado)
                fnAcciondeChekbox();
            });
        }
    })
}

function validar() {
    validado = true;
    $(".classRegAlumno:not(.opcional)").each(function () {
        if ($(this).val().length < 1) {
            alert("El campo " + $(this).attr("name") + " es requerido");
            validado = false;
            return false;
        }
    }); 
    
    $(".regAlumnoCbo").each(function () {
        if ($(this).val().length < 1) {
            alert("El campo " + $(this).attr("name") + " es requerido");
            validado = false;
            return false;
        }
    });     
}

function validarInhabilitar() {
    validado = true;
    if ($("#txtFechaRegistro").val().length < 1) {
        alert("El campo " + $("#txtFechaRegistro").attr("name") + " es requerido");
        validado = false;
        return false;
    }     

}


function editarAlumno() {
    var i=""; 
    validar();
    if (!validado) {
        return;
    }
       
    $("input[type='checkbox'][name='nameDocumentos']:checked").each(
        function() {
            i = i + $(this).val() + ",";
        }
        );
    jConfirm('Desea guardar los cambios efectuados?', 'Confirmación', function(r) { 
        if(r)
        {     
            $.ajax({
                url: "registroPersonaServletController",
                data: {
               
                    action: "editarDatos",
                    txtIdPersona : $("#spIdPersona").text(),
                    txtDni: $("#txtDni").val(),
                    txtApPaterno: $("#txtApPaterno").val(),
                    txtApMaterno: $("#txtApMaterno").val(),
                    txtNombres: $("#txtNombres").val(),
                    txtFechaNacimiento: $("#txtFechaNacimiento").val(),
                    rbSexo: $("input:radio[name=SEXO]:checked").val(),
                    cboNivel: $("#cboNivel").val(),
                    cboGrado: $("#cboGrado").val(),
                    txtDireccionActual: $("#txtDireccionActual").val(),
                    cboProcedencia: $("#cboProcedencia").val(),
                    txtOtroProc: $("#txtOtroProc").val(),
                    //------ Apoderado Principal
                    txtNombresAp: $("#txtNombresAp").val(),
                    cboParentescoAp: $("#cboParentescoAp").val(),
                    txtOtroAp : $("#txtOtroAp").val(),            
                    txtTelefonoAp: $("#txtTelefonoAp").val(),    
                    cboGradoInstruccionAp: $("#cboGradoInstruccionAp").val(), 
                    //------ Apoderado Alternativo
                    txtNombresAa: $("#txtNombresAa").val(),
                    cboParentescoAa: $("#cboParentescoAa").val(),
                    txtOtroAa : $("#txtOtroAa").val(),            
                    txtTelefonoAa: $("#txtTelefonoAa").val(),    
                    cboGradoInstruccionAa: $("#cboGradoInstruccionAa").val(), 
                    //------            
                    documentos :  $('input:radio[name="SEXO"]').val(),
                    txtUsuario:$("#txtUsuario").text().trim(),            
                    nidDocumentos:i,
                    cboTipoMatricula:$("#cboTipoMatricula").val(),
                    repite : $("input[type='checkbox'][name='chkRepite']").is(':checked')
                }
            })
            .done(function (data, status, jqXhr) {       
                resultado = data.split(",");
                var codResultado = resultado[0];
                var descResultado = resultado[1]; 
                jAlert(descResultado, 'mensaje');
                limpiar(codResultado);
                fnAcciondeChekbox();
            });
        }
    })
}

function fnEliminarRegistro()
{
    validarInhabilitar();
    if (!validado) {
        return;
    }
    console.log();
    jConfirm('Desea eliminar el registro?', 'Confirmación', function(r) { 
        if(r)
        {     
            $.ajax({
                url: "registroPersonaServletController",
                data: {
               
                    action: "eliminarDatos",
                    txtIdPersona : $("#spIdPersona").text(),
                    txtFechaRegistro : $("#txtFechaRegistro").text(),
                    sUsuario : $("#txtUsuario").text().trim(),
                    sAnioAcademico : $('#spEstadoAlumno').text().trim().substr($('#spEstadoAlumno').text().trim().indexOf("2"), 4)                 
                }
            })
            .done(function (data, status, jqXhr) {        
                resultado = data.split(",");
                var codResultado = resultado[0];
                var descResultado = resultado[1]; 
                jAlert(descResultado, 'mensaje');
                limpiar(codResultado)
                // INICIO LIMPIAR Y CERRAR LA VENTANA DE INHABILITAR
                $("#txtFechaRegistro").val("");
                dlgEliminarAlumno.dialog("close");
                // FIN 
                fnAcciondeChekbox();
            });
        }
    })    
}

function fnHabilitarRegistro()
{
    validarInhabilitar();
    if (!validado) {
        return;
    }
    console.log();
    jConfirm('Desea Activar el registro?', 'Confirmación', function(r) { 
        if(r)
        {     
            $.ajax({
                url: "registroPersonaServletController",
                data: {
               
                    action: "habilitarDatos",
                    txtIdPersona : $("#spIdPersona").text(),
                    txtFechaRegistro : $("#txtFechaRegistro").text(),
                    sUsuario : $("#txtUsuario").text().trim(),
                    sAnioAcademico : $('#spEstadoAlumno').text().trim().substr($('#spEstadoAlumno').text().trim().indexOf("2"), 4)                 
                }
            })
            .done(function (data, status, jqXhr) {        
                resultado = data.split(",");
                var codResultado = resultado[0];
                var descResultado = resultado[1]; 
                jAlert(descResultado, 'mensaje');
                limpiar(codResultado)
                // INICIO LIMPIAR Y CERRAR LA VENTANA DE INHABILITAR
                $("#txtFechaRegistro").val("");
                dlgHabilitarAlumno.dialog("close");
                // FIN 
                fnAcciondeChekbox();
            });
        }
    })    
}

function limpiar(codResultado){
    if (codResultado === "-1"){
        $("#txtDni").focus();
    }else
    {
        $(".classRegAlumno:not(.opcional)").each(function () {
        
            if ($(this).val().length > 0) {
                $(this).val("");                     
            }
        });
        
        $(".classRegAlumnoAp:not(.opcional)").each(function () {

            if ($(this).val().length > 0) {
                $(this).val("");                     
            }
        });   
        
        $(".classRegAlumnoAlternativo:not(.opcional)").each(function () {
            if ($(this).val().length > 0) {
                $(this).val("");                     
            }
        });   
        
        $(".regAlumnoCboAA option").removeAttr("selected");
        
        $(".regAlumnoCboAA option[value=1]").attr("selected", true);        
        
        $(".classRegAlumnoProc:not(.opcional)").each(function () {
            if ($(this).val().length > 0) {
                $(this).val("");                     
            }
        }); 
        
        $("input[type='checkbox'][name='chkRepite']").prop('checked', false); 
        
        $(".regAlumnoCbo option").removeAttr("selected");
        
        $(".regAlumnoCbo option[value=1]").attr("selected", true);
        
        $(".regAlumnoCbo option[name=PROCEDENCIA][value=2]").attr("selected", true);
        
        $("#txtFechaNacimiento").val("");
        $("#spEstadoAlumno").text("");
        
        $('input[type="checkbox"][name="nameDocumentos"]').prop('checked', false);               
        $('input[type="checkbox"][name="nameDocumentos"]').attr("disabled", "disabled");         
        $(".classRegAlumno").css("background", "#EBEBE4");
        $(".classRegAlumno").attr("disabled", "disabled");
        
        $(".regAlumnoCbo").css("background", "#EBEBE4");
        $(".regAlumnoCbo").attr("disabled", "disabled");         
        
        $(".classRegAlumnoAlternativo").attr("disabled", "disabled");   
        $(".classRegAlumnoBoton").attr("disabled", "disabled"); 
        $(".classRegAlumnoBoton").hide();
        $("#txtOtroAp").attr("disabled", "disabled");            
        $(".classRegAlumnoAp").css("background", "#EBEBE4");             
        $("#txtOtroAa").attr("disabled", "disabled");            
        $(".classRegAlumnoAlternativo").css("background", "#EBEBE4");     
        $("#txtOtroProc").attr("disabled", "disabled");            
        $(".classRegAlumnoProc").css("background", "#EBEBE4"); 
        
        $("#txtFechaNacimiento").attr("disabled", "disabled"); 
        $("#txtFechaNacimiento").css("background", "#EBEBE4");
        $("#imgIconAlumno").show();
        $("#imgIconDeleteAlumno").hide();
        $("#imgIconEnabledAlumno").hide();        
        $("#imgIconNewAlumno").show();
        $('#lblMensajeApoderado').remove();
    }  
}

function fnBloquear()
{
    $('input[type="checkbox"][name="nameDocumentos"]').attr("disabled", "disabled");    
    $(".classRegAlumno").attr("disabled", "disabled"); 
    $(".regAlumnoCbo").attr("disabled", "disabled");
    $(".classRegAlumnoAlternativo").attr("disabled", "disabled");     
    $("#txtOtroAp").attr("disabled", "disabled");   
    $("#txtOtroAa").attr("disabled", "disabled");   
    $("#txtOtroProc").attr("disabled", "disabled");   
    $("#txtFechaNacimiento").attr("disabled", "disabled");     


    $(".classRegAlumno").css("background", "#EBEBE4");
    $(".regAlumnoCbo").css("background", "#EBEBE4");    
    $(".classRegAlumnoAp").css("background", "#EBEBE4");              
    $(".classRegAlumnoAlternativo").css("background", "#EBEBE4");        
    $(".classRegAlumnoProc").css("background", "#EBEBE4"); 
    $("#txtFechaNacimiento").css("background", "#EBEBE4");    
    $("#imgIconDeleteAlumno").hide();
}

function fnDesbloquear()
{
    $('input[type="checkbox"][name="nameDocumentos"]').removeAttr("disabled", "disabled"); 
    $(".classRegAlumno").removeAttr("disabled"); 
    $(".regAlumnoCbo").removeAttr("disabled");
    $(".classRegAlumnoAlternativo").removeAttr("disabled"); 
    $("#txtOtroAp").attr("disabled", "disabled");   
    $("#txtOtroAa").attr("disabled", "disabled");   
    $("#txtOtroProc").attr("disabled", "disabled");   
    $("#txtFechaNacimiento").attr("disabled", "disabled");        
    
    $(".classRegAlumno").css("background", "#FFFFAA");
    $(".regAlumnoCbo").css("background", "#FFFFAA");    
               
    $("#txtFechaNacimiento").css("background", "#FFFFAA");   
    $("#imgIconDeleteAlumno").show();
    $("#imgIconEnabledAlumno").hide();    
}

function cancelar(){
    limpiar("-1");
}

function buscarAlumno(){
    $.ajax({
        type: 'GET',        
        url: "registroPersonaServletController",
        data: {
            action: "consultaDatos",
            txtPaciente: $("#txtPaciente").val(),
            rbFiltro: $("#rbFiltro:checked").val()     
        }
    })
    .done(function (data, status, jqXhr) { 
        arregloAlumnos = data.split(":");
        limparselect();
        if (data=='') {
            $('#tblAlumnos').append('<tr><td colspan = "7"><label> No se encontraron datos ... </label></td></tr>'); 
        }
        else{
            for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
                var idPersona =  arregloAlumnos[i].split("-")[0];
                var numeroDocumento  =  arregloAlumnos[i].split("-")[1];            
                var apellidoPaterno=  arregloAlumnos[i].split("-")[2];            
                var apellidoMaterno =  arregloAlumnos[i].split("-")[3];
                var nombres =  arregloAlumnos[i].split("-")[4];  
                var sAnioAcademico = arregloAlumnos[i].split("-")[5];
                var sTipoMatricula = arregloAlumnos[i].split("-")[6];
                var sCodigoColor = arregloAlumnos[i].split("-")[7];
                $('#tblAlumnos').append('<tr>\n\
                                                                    <td style="width: 60px; text-align: center;background:'+ sCodigoColor+';"><label>'+numeroDocumento+'</label></td>\n\
                                                                    <td style="width: 175px;background:'+ sCodigoColor+';"><label>'+apellidoPaterno+'</label></td>\n\
                                                                    <td style="width: 175px;background:'+ sCodigoColor+';"><label>'+apellidoMaterno+'</label></td>\n\
                                                                    <td style="background:'+ sCodigoColor+';" class="text-left"><label>'+nombres+'</label></td>\n\
                                                                    <td style="background:'+ sCodigoColor+';"><label>'+idPersona+'</label></td>\n\
                                                                    <td style="background:'+ sCodigoColor+';"><label>'+sTipoMatricula+'</label></td>\n\
                                                                    <td style="background:'+ sCodigoColor+';"><label>'+sAnioAcademico+'</label></td>\n\
                                                            </tr>');                 
                $('#tblAlumnos tr td:nth-child(5)').hide();
            // if your table has header(th), use this
            //$('td:nth-child(2),th:nth-child(2)').hide();                
            }
        }
        resultadosClickHandler();
        resultadosClickApoderadoHandler();   
    
    });  
    function limparselect(){
        $('#tblAlumnos tr').remove();       
    }
}

function fnObtenerGrado(){
    $.ajax({
        type: 'GET',
        url: 'registroPersonaServletController',
        data: 'descripcionNivel='+$('#cboNivel option:selected').text(),
        statusCode: {
            404: function() {
                console.log('Pagina no encontrada');
            },
            500: function(){
                console.log('Error de servidor');
            }
        }
    /*
            success: function(resultado){
                pegadados = resultado.split(":");
                console.log(resultado);
                limparselect();
                if(resultado == '')
                    $('#cboGrado').append('<option>Grado no encontrado</option>');
                else{
                    for(var i = 0; i < pegadados.length - 1; i++){
                        var codigoGrado = pegadados[i].split("-")[0]; 
                        var descripcionGrado = pegadados[i].split("-")[1];
                        $('#cboGrado').append('<option value ="'+codigoGrado+'">'+descripcionGrado+'</option>');
                    }
                }
            }*/
    })
        
    .done(function (data, status, jqXhr) {

        pegadados = data.split(":");
        limparselect();
        if(data == '')
            $('#cboGrado').append('<option>Grado no encontrado</option>');
        else{
            for(var i = 0; i < pegadados.length - 1; i++){
                var codigoGrado = pegadados[i].split("-")[0]; 
                var descripcionGrado = pegadados[i].split("-")[1];
                $('#cboGrado').append('<option value ="'+codigoGrado.trim()+'">'+descripcionGrado+'</option>');
            }
        }
    });
        
    function limparselect(){
        $('#cboGrado option').remove();
    }     
}

function resultadosClickHandler() { 
    
    $("#tblAlumnos tr").each(function(i){ 
        $(this).click(function(){ // ACTIVO EL EVENTO CLICK A UNA FILA DE LA TABLA ALUMNO 
            $("#spIdPersona").hide(); 
            
            var idPersona = $('#tblAlumnos tr td:nth-child(5)').eq(i).text();
            var sAnioAcademico = $('#tblAlumnos tr td:nth-child(7)').eq(i).text(); 
            $.ajax({
                url: "registroPersonaServletController",
                data: {
                    action: "rowclick",
                    id: idPersona,
                    sAnio: sAnioAcademico
                }                
            })
            .done(function (data, status, jqXhr) {     
                arregloAlumnos = data.split(";");       
                if (data=='') {
                    $('#tblAlumnos').append('<tr><td colspan = "4"><label> No se encontraron datos ... </label></td></tr>'); 
                }
                else{
                    for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
                        var idPersona =  arregloAlumnos[i].split("+")[0];
                        var numeroDocumento  =  arregloAlumnos[i].split("+")[1];            
                        var apellidoPaterno=  arregloAlumnos[i].split("+")[2];            
                        var apellidoMaterno =  arregloAlumnos[i].split("+")[3];
                        var nombres =  arregloAlumnos[i].split("+")[4]; 
                        var fechaNacimiento =  arregloAlumnos[i].split("+")[5]; 
                        var sexo =  arregloAlumnos[i].split("+")[6]; 
                        var idNivel =  arregloAlumnos[i].split("+")[7];   
                        var idGrado =  arregloAlumnos[i].split("+")[8];
                        var sDireccion = arregloAlumnos[i].split("+")[9];
                        var nIdProcedencia = arregloAlumnos[i].split("+")[10];
                        var sDocumento  = arregloAlumnos[i].split("+")[11]
                        var sObsProc  = arregloAlumnos[i].split("+")[13]
                        var sEstadoAlumno  = arregloAlumnos[i].split("+")[14]
                        var nIdTipoMatricula = arregloAlumnos[i].split("+")[15]
                        var sValidarAnio = arregloAlumnos[i].split("+")[16]                        
                        var sIdDocumento = sDocumento.split(",");
                        /* match de variables con id de html*/ 
                        $('#spIdPersona').text(idPersona);
                        $('#spEstadoAlumno').text(sEstadoAlumno);
                        if(sEstadoAlumno.indexOf("IN") > -1){
                            $('#spEstadoAlumno').css("color","red");
                            fnBloquear();
                            // 
                            (sValidarAnio == '1')?$("#imgIconEnabledAlumno").show():$("#imgIconEnabledAlumno").hide();
                        }
                        else{
                            $('#spEstadoAlumno').css("color","green");
                            fnDesbloquear();   
                        }
                        $('#txtDni').val(numeroDocumento);
                        $('#txtApPaterno').val(apellidoPaterno);
                        $('#txtApMaterno').val(apellidoMaterno);
                        $('#txtNombres').val(nombres);
                        $('#txtFechaNacimiento').val(fechaNacimiento);
                        // sexo es radio buton
                        if(sexo == 'M'){
                            $('input:radio[name="SEXO"][value="M"]').prop('checked', true);
                            $('input:radio[name="SEXO"][value="F"]').prop('checked', false);                             
                        }
                        else if(sexo == 'F'){
                            $('input:radio[name="SEXO"][value="F"]').prop('checked', true);
                            $('input:radio[name="SEXO"][value="M"]').prop('checked', false);                             
                        }                        
                        // nivel es lista  
                        $('#cboNivel option[value=' + idNivel + ']').attr('selected','selected');                        
                        fnObtenerGradoDetalle(idGrado);
                        
                        //Tipo de matricula
                        $('#cboTipoMatricula option[value='+nIdTipoMatricula+']').attr('selected','selected');
                        
                        //Direccion actual
                        $('#txtDireccionActual').val(sDireccion);
                        
                        //Procedencia 
                        $('#cboProcedencia option[value=' + nIdProcedencia + ']').attr('selected','selected');
                        $('#txtOtroProc').val(sObsProc);                        
                        // Ingresar los id de documentos                        
                        for (var i = 0 ;i < sIdDocumento.length ; i++){
                            $('input[type=checkbox][name=nameDocumentos][value="'+ sIdDocumento[i] + '"]').prop('checked', true); 
                        }                        
                    }
                    fnHabilitarEditar();
                }                
            });   
        });        
    });    
}


function resultadosClickApoderadoHandler() { 
    
    $("#tblAlumnos tr").each(function(i){
        $(this).click(function(){
            var idPersona = $('#tblAlumnos tr td:nth-child(5)').eq(i).text();
            var sAnioAcademico = $('#tblAlumnos tr td:nth-child(7)').eq(i).text();             
            $.ajax({
                url: "registroPersonaServletController",
                data: {
                    action: "rowclickApoderado",
                    id: idPersona,
                    sAnio:sAnioAcademico
                }                
            })
            .done(function (data, status, jqXhr) {     
                arregloAlumnos = data.split(":");  
                if (data=='') {
                    $('#txtNombresAp').val("");
                    $('#lblMensajeApoderado').remove();
                    
                    //parentesco
                    $('#cboParentescoAp option[value=0]').attr('selected','selected');

                    //Observacion Parentesco
                    $('#txtOtroAp').val("");

                    //Telefono de contacto
                    $('#txtTelefonoAp').val();  

                    // grado de instruccion Ap
                    $('#cboGradoInstruccionAp option[value=0]').attr('selected','selected'); 
                }
                else{
                    for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
                        var sNombreCompleto =  arregloAlumnos[i].split("-")[0];
                        var nTelefono  =  arregloAlumnos[i].split("-")[1];            
                        var nIdGradoInstruccionAp =  arregloAlumnos[i].split("-")[2];            
                        var nIdParentesco =  arregloAlumnos[i].split("-")[3];
                        var sObservacionParentesco =  arregloAlumnos[i].split("-")[4]; 

                        /* match de variables con id de html*/                        
                        // Nombre de apoderado
                        if (i == 0){
  
                            $('#txtNombresAp').val(sNombreCompleto);

                            //parentesco
                            $('#cboParentescoAp option[value=' + nIdParentesco + ']').attr('selected','selected');

                            //Observacion Parentesco
                            $('#txtOtroAp').val(sObservacionParentesco);

                            //Telefono de contacto
                            $('#txtTelefonoAp').val(nTelefono);  

                            // grado de instruccion Ap
                            $('#cboGradoInstruccionAp option[value=' + nIdGradoInstruccionAp + ']').attr('selected','selected'); 
                        }
                        else{
                            $('#txtNombresAa').val(sNombreCompleto);

                            //parentesco
                            $('#cboParentescoAa option[value=' + nIdParentesco + ']').attr('selected','selected');

                            //Observacion Parentesco
                            $('#txtOtroAa').val(sObservacionParentesco);

                            //Telefono de contacto
                            $('#txtTelefonoAa').val(nTelefono);  

                            // grado de instruccion Aa
                            $('#cboGradoInstruccionAa option[value=' + nIdGradoInstruccionAp + ']').attr('selected','selected');                             
                            
                        }
                    }
                }

                dlgConsularAlumno.dialog("close");
                $('#tblAlumnos tr').remove();  
            });   
         
        });        
    });    
}


function fnObtenerGradoDetalle(idGrado){
    $.ajax({
        type: 'GET',
        url: 'registroPersonaServletController',
        data: 'descripcionNivel='+$('#cboNivel option:selected').text(),
        statusCode: {
            404: function() {
                console.log('Pagina no encontrada');
            },
            500: function(){
                console.log('Error de servidor');
            }
        }
    /*
            success: function(resultado){
                pegadados = resultado.split(":");
                console.log(resultado);
                limparselect();
                if(resultado == '')
                    $('#cboGrado').append('<option>Grado no encontrado</option>');
                else{
                    for(var i = 0; i < pegadados.length - 1; i++){
                        var codigoGrado = pegadados[i].split("-")[0]; 
                        var descripcionGrado = pegadados[i].split("-")[1];
                        $('#cboGrado').append('<option value ="'+codigoGrado+'">'+descripcionGrado+'</option>');
                    }
                }
            }*/
    })
        
    .done(function (data, status, jqXhr) {

        pegadados = data.split(":");
        limparselect();
        if(data == '')
            $('#cboGrado').append('<option>Grado no encontrado</option>');
        else{
            for(var i = 0; i < pegadados.length - 1; i++){
                var codigoGrado = pegadados[i].split("-")[0]; 
                var descripcionGrado = pegadados[i].split("-")[1];
                $('#cboGrado').append('<option value ="'+codigoGrado.trim()+'">'+descripcionGrado+'</option>');
            }
            $('#cboGrado option[value=' + idGrado.trim() + ']').attr('selected','selected'); 
        }
    });
        
    function limparselect(){
        $('#cboGrado option').remove();
    }
}    
    
function fnCargarDocumentos(){
        
    $.ajax({
        url: "registroPersonaServletController",
        data: {
            action: "obtenerDocumentos"
        }                
    })
    .done(function (data, status, jqXhr) {     
        arregloAlumnos = data.split(":");  
        limparselect();                
        if (data=='') {
            $('#liDocumentos').append(''); 
        }
        else{
            for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
                var idDocumento =  arregloAlumnos[i].split("-")[0];
                var Descripcion  =  arregloAlumnos[i].split("-")[1]; 
                $('#liDocumentos').append('<li><input type="checkbox" id = "'+idDocumento.trim()+'" value = "'+idDocumento.trim()+'" name="nameDocumentos"/>'+Descripcion.trim()+'</li> '); 
            }
            $('input[type="checkbox"][name="nameDocumentos"]').attr("disabled", "disabled"); 
        }
    }); 
    function limparselect(){
        $('#liDocumentos li').remove();
    }  
}
    
function fnCargarParentesco(){
    $.ajax({
        url: "registroPersonaServletController",
        data: {
            action: "obtenerParentesco"
        }                
    })
    .done(function (data, status, jqXhr) {     
        arregloAlumnos = data.split(":");  
        limparselect();                
        if (data=='') {
            $('#cboParentescoAp').append('<option> No se encontraron datos ... </option>'); 
            $('#cboParentescoAa').append('<option> No se encontraron datos ... </option>');             
        }
        else{
            for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
                var idParentesco =  arregloAlumnos[i].split("-")[0];
                var Descripcion  =  arregloAlumnos[i].split("-")[1]; 
                $('#cboParentescoAp').append('<option value = '+idParentesco.trim()+'>'+Descripcion.trim()+'</option> '); 
                $('#cboParentescoAa').append('<option value = '+idParentesco.trim()+'>'+Descripcion.trim()+'</option> ');          
            }
        }
    });  
    function limparselect(){
        $('#cboParentescoAA option').remove();
    }        
}   
    
function fnCargarGradoInstruccion(){
    $.ajax({
        url: "registroPersonaServletController",
        data: {
            action: "obtenerGradoInstruccion"
        }                
    })
    .done(function (data, status, jqXhr) {     
        arregloAlumnos = data.split(":");  
        limparselect();                
        if (data=='') {
            $('#cboGradoInstruccionAp').append('<option> No se encontraron datos ... </option>'); 
            $('#cboGradoInstruccionAa').append('<option> No se encontraron datos ... </option>');             
        }
        else{
            for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
                var idGradoInstruccion =  arregloAlumnos[i].split("-")[0];
                var Descripcion  =  arregloAlumnos[i].split("-")[1]; 
                $('#cboGradoInstruccionAp').append('<option value = '+idGradoInstruccion.trim()+'>'+Descripcion.trim()+'</option> '); 
                $('#cboGradoInstruccionAa').append('<option value = '+idGradoInstruccion.trim()+'>'+Descripcion.trim()+'</option> ');                 
            
            }
        }
    }); 
    function limparselect(){
        $('#cboGradoInstruccionAp option').remove();
        $('#cboGradoInstruccionAa option').remove();        
    }          
        
} 
    
function fnCargarNivel(){
    $.ajax({
        url: "registroPersonaServletController",
        data: {
            action: "obtenerNivel"
        }                
    })
    .done(function (data, status, jqXhr) {     
        arregloAlumnos = data.split(":");  
        limparselect();                
        if (data=='') {
            $('#cboNivel').append('<option> No se encontraron datos ... </option>'); 
        }
        else{
            for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
                var idNivel =  arregloAlumnos[i].split("-")[0];
                var Descripcion  =  arregloAlumnos[i].split("-")[1]; 
                $('#cboNivel').append('<option value = '+idNivel.trim()+'>'+ Descripcion +'</option> '); 
            }
        }
    });
    function limparselect(){
        $('#cboNivel option').remove();
    }         
} 

function fnCargarGrado(){
    $.ajax({
        url: "registroPersonaServletController",
        data: {
            action: "obtenerGrado"
        }                
    })
    .done(function (data, status, jqXhr) {     
        arregloAlumnos = data.split(":");  
        limparselect();                
        if (data=='') {
            $('#cboGrado').append('<option> No se encontraron datos ... </option>'); 
        }
        else{
            for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
                var idGrado =  arregloAlumnos[i].split("-")[0];
                var Descripcion  =  arregloAlumnos[i].split("-")[1]; 
                $('#cboGrado').append('<option value = '+idGrado.trim()+'>'+ Descripcion +'</option> '); 
                console.log("idGrado " + idGrado);
                console.log("Descripcion " + Descripcion);
            }
        }
    });
    function limparselect(){
        $('#cboGrado option').remove();
    }         
}  

function fnCargarTipoMatricula(){
    $.ajax({
        url: "registroPersonaServletController",
        data: {
            action: "obtenerTipoMatricula"
        }                
    })
    .done(function (data, status, jqXhr) {     
        arregloAlumnos = data.split(":");  
        limparselect();                
        if (data=='') {
            $('#cboTipoMatricula').append('<option> No se encontraron datos ... </option>'); 
        }
        else{
            for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
                var idTipoMatricula =  arregloAlumnos[i].split("-")[0];
                var Descripcion  =  arregloAlumnos[i].split("-")[1]; 
                $('#cboTipoMatricula').append('<option value = '+idTipoMatricula.trim()+'>'+ Descripcion +'</option> '); 
            }
        }
    });
    function limparselect(){
        $('#cboTipoMatricula option').remove();
    }         
} 

function fnCargarSeccion(){
    $.ajax({
        url: "registroPersonaServletController",
        data: {
            action: "obtenerSeccion"
        }                
    })
    .done(function (data, status, jqXhr) {     
        arregloAlumnos = data.split(":");  
        limparselect();                
        if (data=='') {
            $('#cboSeccion').append('<option> No se encontraron datos ... </option>'); 
        }
        else{
            for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
                var idsSeccion =  arregloAlumnos[i].split("-")[0];
                var Descripcion  =  arregloAlumnos[i].split("-")[1]; 
                $('#cboSeccion').append('<option value = '+idsSeccion.trim()+'>'+ Descripcion +'</option> '); 
            }
        }
    });
    function limparselect(){
        $('#cboSeccion option').remove();
    }         
} 

    
function fnCargarProcedencia(){
    $.ajax({
        type: 'GET',
        url: "registroPersonaServletController",
        data: {
            action: "obtenerProcedencia"
        }                
    })
    .done(function (data, status, jqXhr) {     
        arregloAlumnos = data.split(":");  
        limparselect();            
        if (data=='') {
            $('#cboProcedencia').append('<option> No se encontraron datos ... </option>'); 
        }
        else{
            for (var i = 0 ;i < arregloAlumnos.length - 1; i++)
            {
                var idProcedencia =  arregloAlumnos[i].split("-")[0];
                var Descripcion  =  arregloAlumnos[i].split("-")[1];
            
                $('#cboProcedencia').append('<option value = '+idProcedencia.trim()+'>' + Descripcion.trim() + '</option>');             

            }
        }
    });
    function limparselect(){
        $('#cboProcedencia option').remove();
    }         
}     

function fnAcciondeChekbox()

{
    $('#lblDocumentos').click(function(){        
        console.log(i.substr(0, i.length -1));
        console.log(i);
    });
}

function fnCancelarRegistro()
{
    $("#btnIngresar").hide();
    $("#btnEditar").hide();
    $("#btnCancelar").hide();
    $("#imgIconDeleteAlumno").hide(); 
    $("#imgIconEnabledAlumno").hide();     
    $("#imgIconNewAlumno").show();        
    cancelar();
}

function fnHabilitarEditar()
{
    // ELEMENTOS QUE APARECEN Y DESAPARACEN BOTONES, PARA REALIZAR LA ACTUALIZACIÓN DE LOS ALUMNOS.
    $('input[type="checkbox"][name="nameDocumentos"]').removeAttr("disabled", "disabled"); 
    $(".classRegAlumno").removeAttr("disabled"); 
    $(".regAlumnoCbo").removeAttr("disabled"); 
    $(".classRegAlumnoBoton").removeAttr("disabled"); 
    $(".classRegAlumnoAlternativo").removeAttr("disabled"); 
    $(".classRegAlumno").css("background", "#FFFFAA");
    $(".regAlumnoCbo").css("background", "#FFFFAA");
    $("#txtFechaNacimiento").css("background", "#FFFFAA");
    $("#txtDni").focus();
    $("#btnIngresar").hide();
    $("#btnEditar").show();
    $("#btnCancelar").show();
    $("#imgIconNewAlumno").hide();
    $("#imgIconAlumno").hide();                           
    
}

function conseguirAutocompletado(){
    $.ajax({
        type: 'GET',        
        url: "registroPersonaServletController",
        data: {
            action: "buscarAlumnoApoderado"
        }
    })
    .done(function (data, status, jqXhr) { 

        if (data=='') {
        }
        else{
            var arreglosApoderado = data.split(":");
            console.log(arreglosApoderado[0]);   // muestra en la consola 'bar'        
            $("#txtNombresAp").autocomplete({  
                source: arreglosApoderado  
            });
            $("#txtNombresAa").autocomplete({  
                source: arreglosApoderado  
            });
        }
    });         
}

