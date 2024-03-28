$(document).ready(function(){
    $(".classRegAlumno").attr("disabled", "disabled");
    $(".classRegAlumnoBoton").attr("disabled", "disabled"); 
    $("#divDatosPersonales").hide();
    $("#ckbCancelado").hide();
    $('#tblRecibo').hide();
    $('#btnIngresarPago').hide();
    $('#btnCancelar').hide();    
    inhabilitartextBoxConsulta()
    fnGetDateActual();
    $('#spFechaActual').css('color','#2A0F0F');
    $('#logoTitulo').css('')
    eventoPresionarTab();
    
    $("#imgIconAlumno").click(function(){
        resultadosClickHandlerConsulta();        
    });
    
    fnCargarNivel();    
    
    dlgConsularAlumno = $("#buscarAlumno").dialog({
        autoOpen: false,
        dialogClass: 'myTitleClass',        
        modal: true,
        width: 600,
        height: 600
    });
        
    habilitarDialog('');
    
    $("#btnBuscarPagosAlumno").on('click', function(){        
        buscarAlumnoPago();
    });
   
    $("#imgIconNewAlumno").on("click",function(){
        $(".classRegAlumno").removeAttr("disabled"); 
        $(".classRegAlumnoBoton").removeAttr("disabled");        
        $(".classRegAlumno").css("background", "#FFFFAA");
        $("#txtDni").focus();         
    }) 
   
    $("#btnCancelar").on("click",function(e){
        cancelar();
        e.preventDefault();
    })
    
    eventoCheckBoxConsulta();

    $("#btnBuscar").on("click",function(e){
        buscarAlumno();
        e.preventDefault();
    })
    $("#btnExportarBuscarPagosAlumno").click(function(e) {
        window.open('data:application/vnd.ms-excel,' + escape(($('#tblTablaConsulta').html())));        // se coloca el metodo escape para evitar la codifcacion utf-8, la cual nos cambia la letra ñ
        e.preventDefault();
    });
    
    //       var doc = new jsPDF();
    //    var specialElementHandlers = {
    //        '#editor': function (element, renderer) {
    //            return true;
    //        }
    //    };

    //    $('#btnExportarBuscarPagosAlumno').click(function () {
    //        doc.fromHTML($('#tblTablaConsulta').html(), 15, 15, {
    //            'width': 170,
    //            'elementHandlers': specialElementHandlers
    //        });
    //        doc.save('sample-file.pdf');
    //    });

    habilitarCampos();
    $("#lblIdAlumno").hide();
    
    //obtener el numero recibo actual disponible
    obtenerNumeroRecibo();
});

function eventoPresionarTab(){
    
    // Al presionar cualquier tecla en cualquier campo de texto, ejectuamos la siguiente función
    $('.classRegAlumno').on('keydown', function(e){
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

function resultadosClickHandlerConsulta() {        
    dlgConsularAlumno.dialog("open");
    $('#tblAlumnos tr').remove();
    $('#txtNombres').val("");      
}

function buscarAlumno(){
    $.ajax({
        type: 'GET',        
        url: "registroPagoServletController",
        data: {
            action: "consultaDatos",
            txtPaciente: $("#txtNombres").val(),
            rbFiltro: $("#rbFiltro:checked").val()            
        }
    })
    .done(function (data, status, jqXhr) { 
        arregloAlumnos = data.split(":");
        limparselect();
        if (data=='') {
            $('#tblAlumnos').append('<tr><td class="text-left" colspan = "6"><label> No se encontraron datos ... </label></td></tr>'); 
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
                                                                     \n\
                                                                    <td style="width: 60px; text-align: center;background:'+ sCodigoColor+';"><label>'+numeroDocumento+'</label></td>\n\
                                                                    <td style="width: 175px;background:'+ sCodigoColor+';"><label>'+apellidoPaterno+'</label></td>\n\
                                                                    <td style="width: 175px;background:'+ sCodigoColor+';"><label>'+apellidoMaterno+'</label></td>\n\
                                                                    <td style="background:'+ sCodigoColor+';" class="text-left"><label>'+nombres+'</label></td>\n\
                                                                    <td style="background:'+ sCodigoColor+';"><label>'+idPersona+'</label></td>\n\
                                                                    <td style="background:'+ sCodigoColor+';"><label>'+sTipoMatricula+'</label></td>\n\
                                                            </tr>');                 
                $('#tblAlumnos tr td:nth-child(5)').hide();
            // if your table has header(th), use this
            //$('td:nth-child(2),th:nth-child(2)').hide();                         
            }
        }
        resultadosClickHandler();
    });  
    function limparselect(){
        $('#tblAlumnos tr').remove();        
    }
}

function resultadosClickHandler() { 
    
    $("#tblAlumnos tr").each(function(i){
        $(this).click(function(){
            var idPersona = $('#tblAlumnos tr td:nth-child(5)').eq(i).text();
            $.ajax({
                url: "registroPagoServletController",
                data: {
                    action: "rowclickAlumno",
                    id: idPersona
                }                
            })
            .done(function (data, status, jqXhr) {     
                arregloAlumnos = data.split(":");  
                limparselect(); 
                console.log("data" + data);
                if (data=='') {             
                    $('#tblAlumnos').append('<tr><td class="text-left" colspan = "4"><label> No se encontraron datos ... </label></td></tr>'); 
                }
                else{
                    for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
                        var idPersona =  arregloAlumnos[i].split("-")[0];
                        var numeroDocumento  =  arregloAlumnos[i].split("-")[1];            
                        var apellidoPaterno=  arregloAlumnos[i].split("-")[2];            
                        var apellidoMaterno =  arregloAlumnos[i].split("-")[3];
                        var nombres =  arregloAlumnos[i].split("-")[4]; 
                        var nombreCompleto = (arregloAlumnos[i].split("-")[2] + ' ' + arregloAlumnos[i].split("-")[3] + ' ' + arregloAlumnos[i].split("-")[4]);
                        var fechaNacimiento =  arregloAlumnos[i].split("-")[5]; 
                        var sexo =  arregloAlumnos[i].split("-")[6]; 
                        var descripcionNivel =  arregloAlumnos[i].split("-")[7];   
                        var descripcionGrado =  arregloAlumnos[i].split("-")[8];                        
                        
                        $('#lblNombre').text(nombreCompleto);
                        $('#lblDNI').text(numeroDocumento);
                        $('#lblNivel').text(descripcionNivel);
                        $('#lblGrado').text(descripcionGrado);                         
                        $('#divDatosPersonales').show(); 
                    //console.log(nombreCompleto);

                    // if your table has header(th),'). use this
                    //$('td:nth-child(2),th:nth-child(2)').hide();                
                    }
                }
                buscarPago($('#lblDNI').text().trim()); 
            });   
            function limparselect(){
                $('#txtNombresFormulario').val("");
                $('#lblNombre').text("");      
                $('#lblDNI').text("");      
                $('#lblNivel').text("");                      
                $('#lblGrado').text("");
                //$('#tblRecibo tr').remove();
                //$('#tblRecibo').hide();
                $('#btnIngresarPago').hide();
                $('#btnCancelar').hide();                
                dlgConsularAlumno.dialog("close");
            }             
        });        
    });    
}

function buscarPago(numeroDocumento){
    $.ajax({
        type: 'GET',        
        url: "registroPagoServletController",
        data: {
            action: "buscarAlumno",
            nDocumento: numeroDocumento
        }
    })
    .done(function (data, status, jqXhr) { 
        arregloAlumnos = data.split(":");
        limparselect();
        if (data=='') {
            $('#tblPagos').append('<tr><td colspan = "5"><label> No se encontraron deudas ... </label></td></tr>'); 
        }
        else{
            var Total = 0;
            for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
                var mes =  arregloAlumnos[i].split("-")[0];
                var monto  =  arregloAlumnos[i].split("-")[1];            
                var mora=  arregloAlumnos[i].split("-")[2];            
                var pago =  arregloAlumnos[i].split("-")[3];
                var nIdDeuda =  arregloAlumnos[i].split("-")[4];
                var nIdAlumno =  arregloAlumnos[i].split("-")[5];  
                var nIdAlumnoDeuda = arregloAlumnos[i].split("-")[6]; 
                var montoOtros = arregloAlumnos[i].split("-")[7];
                var sBoleta = arregloAlumnos[i].split("-")[8];
                var sAnioAcademico = arregloAlumnos[i].split("-")[9];                
                var sIndex = arregloAlumnos[i].split("-")[10];
                var nIdPersona = arregloAlumnos[i].split("-")[11];
                

                //var sNumeroRecibo = arregloAlumnos[i].split("-")[9];
                //var sNombreCompleto =  arregloAlumnos[i].split("-")[5]; 
                $('#tblPagos').append('<tr>\n\
                                        <td class="text-left" style="display:none"><img id="imgIconAlumno'+nIdAlumnoDeuda+'" src="/SISEDU/images/Reprinter.png" width="24" height="24"/></td>\n\
                                        <td class="text-left"><input type="checkbox" name="chkSeleccion'+nIdAlumnoDeuda+'" id="chkSeleccion'+nIdAlumnoDeuda+'"/></td>\n\
                                        <td class="text-left"><label>'+mes+'</label></td>\n\
                                        <td class="text-right"><label>'+monto+'</label></td>\n\
                                        <td class="text-left" style="text-align: center;" ><input type="text" style="width: 50px;" id="txtMora'+nIdAlumnoDeuda+'"></td>\n\
                                        <td class="text-left" style="text-align: center;" ><input type="text" style="width: 50px;" id="txtPension'+nIdAlumnoDeuda+'"></td>\n\
                                        <td><label>'+nIdDeuda+'</label></td>\n\
                                        <td><label>'+nIdAlumno+'</label></td>\n\
                                        <td><label>'+nIdAlumnoDeuda+'</label></td>\n\
                                        <td><label>'+montoOtros+'</label></td>\n\
                                        <td><label>'+sBoleta+'</label></td>\n\\n\
                                        <td><label>'+sIndex+'</label></td>\n\
                                        </tr>');                  
                $('#tblPagos tr td:nth-child(7)').hide();
                $('#tblPagos tr td:nth-child(8)').hide();
                $('#tblPagos tr td:nth-child(9)').hide();
                $('#tblPagos tr td:nth-child(10)').hide();
                $('#tblPagos tr td:nth-child(11)').hide();
                $('#tblPagos tr td:nth-child(12)').hide();
                $('#txtMora'+nIdAlumnoDeuda).hide();   
                $('#txtPension'+nIdAlumnoDeuda).hide();
                
                $('#lblIdAlumno').text(nIdPersona);
                
                if($('#tblPagos tr td:nth-child(4)').eq(i).text() == 0){                    
                    $('input:checkbox[id="chkSeleccion'+nIdAlumnoDeuda+'"]').attr("disabled", "disabled");
                }else{
                 
                }

                if(sBoleta == "0"){
                    $('#imgIconAlumno'+nIdAlumnoDeuda).css("display", "none");
                }else{
                    $('#imgIconAlumno'+nIdAlumnoDeuda).val(nIdAlumnoDeuda+';'+$('#tblPagos tr td:nth-child(8) label').eq(i).text().trim());
                    $('#imgIconAlumno'+nIdAlumnoDeuda).click(function(){
                        fnReimprimir($(this).val().split(";")[0], $(this).val().split(";")[1]);
                    });
                }                 
                  
                fnHabilitarTextBox(nIdAlumnoDeuda);
            }

            // VALIDAR SI NO EXISTE PAGOS ANTERIORES
            //            var Total = 0;
            //            $('#tblPagos tr td:nth-child(4) > label').each(function(){
            //                Total = Total + parseInt($(this).text());                                
            //            })
            fnIngresoBoleta();
        }
       
    //resultadosClickHandlerIngresarPagos();
    });  
    function limparselect(){
        $('#tblPagos tr').remove();       
    }
}

function fnIngresoBoleta(){        
    $("#btnIngresarPago").on("click",function(e){
        ingresarBoleta();
        setTimeout(function() {
                            actualizarRecibo();
                            obtenerNumeroRecibo(); // para obtener el recibo ya actualizado y colocar en el textbox de recibo
        }, 2000); // Retraso de 5000 milisegundos (5 segundos)                           
        e.preventDefault();
    }) 
}

function fnHabilitarTextBox(nIdAlumnoDeuda){
    
    $('input:checkbox[id="chkSeleccion'+nIdAlumnoDeuda+'"]').on("click", function(){
        if ($('input[type="checkbox"][name="chkSeleccion'+nIdAlumnoDeuda+'"]').is(':checked')) 
        {           
            $.ajax({
                url: "registroPagoServletController",
                data: {
                    action: "obtenerMonto",
                    idAlumnoDeuda: nIdAlumnoDeuda
                }
            })
            .done(function (data, status, jqXhr) {
                var nMonto = data            
                $('#txtMora'+nIdAlumnoDeuda).val(0);                
                $('#txtPension'+nIdAlumnoDeuda).val(nMonto);              
                $('#txtMora'+nIdAlumnoDeuda).show();   
                $('#txtPension'+nIdAlumnoDeuda).show();            
                $('#tblRecibo').show();
                $('#btnIngresarPago').show();
                $('#btnCancelar').show();                
            })
        }
        else{
            $('#txtMora'+nIdAlumnoDeuda).hide();   
            $('#txtPension'+nIdAlumnoDeuda).hide();
            $('#tblRecibo').hide();
            $('#btnIngresarPago').hide();
            $('input[type="checkbox"]').each(function(i){
                if (i != 10)
                {
                    if ($('input[type="checkbox"]').eq(i).is(':checked')){
                        $('#tblRecibo').show();
                        $('#btnIngresarPago').show();
                        $('#btnCancelar').show();                         
                    }
                }
            })
        }
    });         
}

function habilitarCampos(){
    $(".classRegPago").removeAttr("disabled"); 
    $(".classRegPagoBoton").removeAttr("disabled");        
    $(".classRegPago").css("background", "#FFFFAA");
    $("#txtRecibo").focus();       
}

function habilitarDialog(titulo){
    dlgRegistrarPago = $("#frmIngresoPago").dialog({
        autoOpen: false,
        dialogClass: 'myTitleClass', 
        title: titulo,
        modal: true,
        width: 600,
        height: 600
    });    
}

function ingresarBoleta() {
    validarBoleta();
    var bPagoSeleccionado=false;
    var nCuota;
    var nTotal = 0;
    var sCadDeuda = '';
    var numeroBoleta = $("#txtReciboDetalle").val();
    var IdAlumno = $("#lblIdAlumno").text();
    if (!validado) {
        return;
    }
    
    $.ajax({
        url: "registroPagoServletController",
        data: {
            action: "verificarBoleta",
            txtRecibo: $("#txtReciboDetalle").val()
        }
    })
    .done(function (data, status, jqXhr) { 
        resultado = data.split(",");
        var descResultado0 = resultado[0];
        var descResultado1 = resultado[1];
        var pago = 0;

        if (descResultado0 == 0)
        {
            jAlert(descResultado1);
        }else
        {
            $('#tblPagos tr').each(function(i){
                if($('#tblPagos tr td:nth-child(2) input:checkbox').eq(i).is(':checked'))
                {
                    nCuota = parseInt($('#tblPagos tr td:nth-child(12)').eq(i).text());
                    bPagoSeleccionado = true;            
                    return false;
                }              
            })                               
            
            for (var j = 1; j <= nCuota; j++) {

                if (parseInt($('#tblPagos tr td:nth-child(4)').eq(j-1).text()) > 0)
                {
                    pago = parseInt($('#tblPagos tr td:nth-child(4)').eq(j-1).text())    
                    if (j == (nCuota - 1))  
                    {
                        sCadDeuda = sCadDeuda + $('#tblPagos tr td:nth-child(3)').eq(j-1).text() + '.';
                    }
                    else
                    {
                        sCadDeuda = sCadDeuda + $('#tblPagos tr td:nth-child(3)').eq(j-1).text() + ', ';
                    }
                }                
                nTotal = nTotal +  pago;
            }  
            

//            if (nTotal > 0)
//            {
//                jAlert('TIENE PAGOS PENDIENTES EN : ' + sCadDeuda);
//                limpiarselectPago();                
//                return;                    
//            }              
            
            if (bPagoSeleccionado){                            
                jConfirm('Desea guardar los cambios efectuados?', 'Confirmación', function(r) {                            
                    if(r)
                    {                         
                        $('#tblPagos tr').each(function(c){

                            if($('#tblPagos tr td:nth-child(2) input:checkbox').eq(c).is(':checked'))
                            {
                               
                                $.ajax({
                                    url: "registroPagoServletController",
                                    data: {
                                        action: "ingresarPagos",
                                        txtRecibo: $("#txtReciboDetalle").val(),
                                        txtMonto:  $('#tblPagos tr td:nth-child(6) input:text').eq(c).val(),
                                        txtMontoOtros:  $('#tblPagos tr td:nth-child(10)').eq(c).text(),                                    
                                        txtMora:   $('#tblPagos tr td:nth-child(5) input:text').eq(c).val(),
                                        IdAlumnoDeuda: $("#tblPagos tr td:nth-child(9)").eq(c).text(),
                                        IdAlumno: $("#tblPagos tr td:nth-child(8)").eq(c).text(),
                                        IdDeuda: $("#tblPagos tr td:nth-child(7)").eq(c).text(),
                                        Usuario:  $("#txtUsuario").text().trim()   
                                    }
                                })
                                .done(function (data, status, jqXhr) { 
                                    resultado = data.split(",");
                                    var descResultado = resultado[0];
                                    var montoResultado = resultado[1];
                          
                        
                                    // se ocultan los componentes depspues de haber realizado el pago
                                    $('#tblPagos tr td:nth-child(4)').eq(c).text(montoResultado);
                                    $('#tblPagos tr td:nth-child(6) input:text').eq(c).val("");
                                    $('#tblPagos tr td:nth-child(5) input:text').eq(c).val(""); 
                                    $('#tblPagos tr td:nth-child(5) input:text').eq(c).hide();
                                    $('#tblPagos tr td:nth-child(6) input:text').eq(c).hide();
                                    
                                    
                                    $('#tblRecibo').hide();
                                    $('#btnIngresarPago').hide();
                                    $('input:checkbox').attr("checked", false);                          
                                    $("#btnCancelar").hide();                      
                                    
                                    // fin se ocultan los componentes depspues de haber realizado el pago
                                    
                                    if($('#tblPagos tr td:nth-child(4)').eq(c).text() == 0){                    
                                        $('#tblPagos tr td:nth-child(2) input:checkbox').eq(c).attr("disabled", "disabled");
                                    }                                                              
                                });
                                
                              
                            }
                        }) 
                                                //$("#txtReciboDetalle").val("");
                           limpiarselectPago();
                          buscarPago($("#lblDNI").text().trim());                        
                          fnImprimir($('#txtReciboDetalle').val(),$('#lblIdAlumno').text());           
             
                        jAlert('Se genero el pago correctamente');
                          
                    }
                });
            }
            else
            {
                jAlert('Debe seleccionar al menos un mes'); 
                limpiarselectPago();
                return;                
            }
        }
    });

}

function validarBoleta() {
    validado = true;
    $(".classRegRecibo:not(.opcional)").each(function () {
        if ($(this).val().length < 1) {
            jAlert("El campo " + $(this).attr("name") + " es requerido");
            validado = false;
            return false;
        }
    });
}

function validar() {
    validado = true;
    $(".classRegPago:not(.opcional)").each(function () {
        if ($(this).val().length < 1) {
            alert("El campo " + $(this).attr("name") + " es requerido");
            validado = false;
            return false;
        }
    });
}

function buscarAlumnoPago(){
    
    $.ajax({
        type: 'GET',        
        url: "registroPagoServletController",
        data: {
            action: "consultaAlumnoPago",
            txtDni: $("#txtDni").val(),
            txtApNom: $("#txtApNom").val(),
            cboNivel: $('#cboNivel option:selected').val(),
            txtRecibo: $("#txtNRecibo").val(),
            txtFechaInicial: $("#txtFechaConsultaINICIAL").val(),
            txtFechaFin: $("#txtFechaConsultaFIN").val(),            
            
            FECHA: $('input[type="checkbox"][id="ckFECHA"]').is(':checked'), 
            DNI: $('input[type="checkbox"][id="ckDNI"]').is(':checked'),     
            APNOM: $('input[type="checkbox"][id="ckAPNOM"]').is(':checked'),    
            NIVEL: $('input[type="checkbox"][id="ckNIVEL"]').is(':checked'),
            RECIBO: $('input[type="checkbox"][id="ckRECIBO"]').is(':checked')
        }
    })
    .done(function (data, status, jqXhr) { 
        var suma = 0.00
        arregloAlumnos = data.split("+");
        limpiarselectPago();
        if (data=='') {
            $('#tblAlumnosPagos').append('<tr><td colspan = "9" style="width: 594px;"><label> No se encontraron datos ... </label></td></tr>'); 
        }
        else{
            if (arregloAlumnos.length > 14)
            {
                $('#tblCabeceraPagos').append('<tr>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 20px;">N</th>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 60px;">DNI</th>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 233px;">ALUMNO</th>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 33px;">NIV</th>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 58px;">GRA</th>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 58px;">RECIBO</th>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 74px;">MES</th>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 20px;">M</th>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 40px;">PA</th>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 64px;">FECHA</th>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 73px;">USER</th>\n\
                                        </tr>');                
            }
            else{
                 
                $('#tblCabeceraPagos').append('<tr>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 20px;">N</th>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 59px;">DNI</th>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 240px;">ALUMNO</th>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 34px;">NIV</th>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 58px;">GRA</th>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 58px;">RECIBO</th>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 74px;">MES</th>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 21px;">M</th>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 38px;">PA</th>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 67px;">FECHA</th>\n\
                                            <th bgcolor="#FF0000" class="text-left" style="width: 64px;">USER</th>\n\
                                        </tr>');                 
                 
            }
            for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
                var idBoleta =  arregloAlumnos[i].split("*")[0];
                var alumno  =  arregloAlumnos[i].split("*")[1];            
                var nivel=  arregloAlumnos[i].split("*")[2];            
                var grado =  arregloAlumnos[i].split("*")[3];
                var numeroRecibo =  arregloAlumnos[i].split("*")[4];          
                var mes =  arregloAlumnos[i].split("*")[5];          
                var mora =  arregloAlumnos[i].split("*")[6];          
                var monto =  arregloAlumnos[i].split("*")[7];                                          
                var fechaCreacion =  arregloAlumnos[i].split("*")[8];                                          
                var username =  arregloAlumnos[i].split("*")[9];
                var documento =  arregloAlumnos[i].split("*")[10];

                $('#tblAlumnosPagos').append('<tr>\n\
                                            <td bgcolor="#F2F2F2" class="text-center" style="width: 20px">'+(i+1)+'</td>\n\
                                            <td bgcolor="#F2F2F2" class="text-center">'+documento+'</td>\n\
                                            <td bgcolor="#F2F2F2" class="text-left" style="width: 250px;padding-left: 10px;">'+alumno+'</td>\n\
                                            <td bgcolor="#F2F2F2" class="text-center" style="width: 35px;">'+nivel+'</td>\n\
                                            <td bgcolor="#F2F2F2" class="text-center">'+grado+'</td>\n\
                                            <td bgcolor="#F2F2F2" class="text-center">'+numeroRecibo+'</td>\n\
                                            <td bgcolor="#F2F2F2" class="text-center" style="width: 75px;padding-left: 0px;padding-right: 0px;">'+mes+'</td>\n\
                                            <td bgcolor="#F2F2F2" class="text-center" style="width: 25px;padding-right: 0px;">'+mora+'</td>\n\
                                            <td bgcolor="#F2F2F2" class="text-center" style="width: 33px;">'+monto+'</td>\n\
                                            <td bgcolor="#F2F2F2" class="text-center" style="width: 65px;">'+fechaCreacion+'</td>\n\
                                            <td bgcolor="#F2F2F2" class="text-center" style="padding-left: 5px;">'+username+'</td>\n\
                                            </tr>'
                    ); 
                suma = suma + parseFloat(monto);
            }
            $('#spSumaAlumnosPagos').append('<label>El total de los pagos es :' + suma + '</label>');
            $('#idRegistro').append('<td><label>/ M = MORA</label></td><td><label> / PA = PAGO /</label></td>');
        }
    });
}

function limpiarselectPago(){
    $('#tblAlumnosPagos tr').remove();
    $('#tblCabeceraPagos tr').remove();
    $('#spSumaAlumnosPagos').text("");
    $('#idRegistro').text("");
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

function inhabilitartextBoxConsulta(){
    
    $('#txtFechaConsultaINICIAL').attr("disabled", "disabled");
    $('#txtFechaConsultaFIN').attr("disabled", "disabled"); 
    $('#txtDni').attr("disabled", "disabled"); 
    $('#cboNivel').attr("disabled", "disabled");  
    $('#txtApNom').attr("disabled", "disabled");
    $('#txtNRecibo').attr("disabled", "disabled");   
    
}

function eventoCheckBoxConsulta(){
    
    $('#ckFECHA').click(function(){
        if (($('#ckFECHA').is(':checked'))){
            $('#txtFechaConsultaINICIAL').removeAttr("disabled");
            $('#txtFechaConsultaFIN').removeAttr("disabled");             
        }
        else
        {
            $('#txtFechaConsultaINICIAL').val("");
            $('#txtFechaConsultaFIN').val("");    
            $('#txtFechaConsultaINICIAL').attr("disabled", "disabled");
            $('#txtFechaConsultaFIN').attr("disabled", "disabled");              
        }        
    })
    
    $('#ckDNI').click(function(){
        if (($('#ckDNI').is(':checked'))){
            $('#txtDni').removeAttr("disabled");
        }
        else
        {
            $('#txtDni').val("");
            $('#txtDni').attr("disabled", "disabled");         
        }        
    })   
    
    $('#ckAPNOM').click(function(){
        if (($('#ckAPNOM').is(':checked'))){
            $('#txtApNom').removeAttr("disabled");
        }
        else
        {
            $('#txtApNom').val("");
            $('#txtApNom').attr("disabled", "disabled");         
        }        
    })   
    
    $('#ckNIVEL').click(function(){
        if (($('#ckNIVEL').is(':checked'))){
            $('#cboNivel').removeAttr("disabled");
        }
        else
        {
            $('#cboNivel').attr("disabled", "disabled");         
        }        
    }) 
    
    $('#ckRECIBO').click(function(){
        if (($('#ckRECIBO').is(':checked'))){
            $('#txtNRecibo').removeAttr("disabled");
        }
        else
        {
            $('#txtNRecibo').val("");
            $('#txtNRecibo').attr("disabled", "disabled");         
        }        
    })    
}

//function fnImprimir(recibo,pago) {
//    document.location.href='impresionPagoServletController?nIdAlumno='+ recibo +'&sNumeroRecibo=' + pago;
//}

//function fnExportarAPDF(cadenaHTML){
//    
//    $('#btnExportarBuscarPagosAlumno').click(function(){ 
//
//        var cadenaText = '<html><table border=1 style="font-size: 10px;font-family: "Arial", serif;">'+ cadenaHTML+ '</table></html>';
//        console.log(cadenaText);
//        $.ajax({
//            url: "impresionPagoServletController",
//            data: {
//                action: "exportarPdfPagos",
//                cadena: cadenaText
//            }                
//        })
//        .done(function (data, status, jqXhr) {     
//            //console.log(data);
//            });
//    })
//}

function fnGetDateActual()
{   
    $.ajax({
        url: 'consultaDeudaServletController',
        data: {
            action: 'fecha'
        }            
    })    
    .done(function(data){
        if(data == '')
            $('#spFechaActual').append('');
        else{
            
            var codigoGrado = data; 
            $('#spFechaActual').append('FECHA :' + codigoGrado);
        }        
    });
}

function fnImprimir(recibo, alumno) {
    document.location.href='impresionPagoServletController?nIdAlumno='+ recibo +'&sNumeroRecibo=' + alumno;
}

function fnReimprimir(idAlumnoDeuda, alumno) {
    document.location.href='impresionPagoServletController?action=reimprimir&nIdAlumno='+ idAlumnoDeuda +'&sNumeroRecibo=' + alumno;
}

function cancelar(){
    
   /*$('#tblPagos tr td:nth-child(4)').each(function(i){
        $('#chkSeleccion'+i).prop('checked', false);
        ;
        $('#txtPension'+i).val('');         
        $('#txtMora'+i).hide();   
        $('#txtPension'+i).hide();       
          
    }); */  
    $('#tblPagos input[type="checkbox"]').prop('checked', false);
    $('#tblPagos input[type="text"]').val('').hide();
        
    //$('#txtReciboDetalle').val(''); 
    obtenerNumeroRecibo();
    
    $('#tblRecibo').hide();
    $('#btnIngresarPago').hide();    
    $('#btnCancelar').hide();  
    

}

function obtenerNumeroRecibo(){
            console.log("entro obtenerNumeroRecibo");
$.ajax({
        url: "registroPagoServletController",
        data: {
            action: "obtenerNumeroRecibo"
        }
    })
    .done(function (data, status, jqXhr) { 
        resultado = data.split(",");
        var descResultado0 = resultado[0];
        var descResultado1 = resultado[1];
        
        $('#txtReciboDetalle').val(descResultado1);

    })
    .fail(function (jqXhr, status, error) {
        console.error("Error en la solicitud obtenerNumeroRecibo:", status, error);
    });
}

function actualizarRecibo(){
            console.log("entro actualizarRecibo");
$.ajax({
        url: "registroPagoServletController",
        data: {
            action: "actualizarNumeroRecibo"
        }
    })
    .done(function (data, status, jqXhr) { 
        resultado = data.split(",");
        var descResultado0 = resultado[0];
        var descResultado1 = resultado[1];
        
        $('#txtReciboDetalle').val(descResultado1);

    })
    .fail(function (jqXhr, status, error) {
        console.error("Error en la solicitud obtenerNumeroRecibo:", status, error);
    });
}
