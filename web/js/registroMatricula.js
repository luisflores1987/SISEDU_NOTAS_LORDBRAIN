$(document).ready(function(){

    
    dlgGradopension = $("#pensionGrado").dialog({
        autoOpen: false,
        dialogClass: 'myTitleClass',        
        modal: true,
        width: 300,
        height: 300
    });
    $("#btnBuscarInicial").hide();
    $("#btnBuscarPrimaria").hide();
    $("#btnBuscarSecundaria").hide();   
    
    
    dlgAlumnopension = $("#pensionAlumno").dialog({
        autoOpen: false,
        dialogClass: 'myTitleClass',        
        modal: true,
        width: 650,
        height: 600
    });
    
    dlgAlumnoGradopension = $("#pensionGradoAlumno").dialog({
        autoOpen: false,
        dialogClass: 'myTitleClass',        
        modal: true,
        width: 300,
        height: 400
    });          
    $("#clickDerecho").hide();     
    
    $("#AñoAcademico  select").change(function(){
        fnCargarNiveles();
        $("#tblNiveles").show();
        $(".pensionBtn").show();
    });
    
    $(".pensionBtn").hide();
    
});

function fnObtenerAlumno(Descripcion, idNivel){    
    $.ajax({
        type: 'GET',        
        url: "registroMatriculaServletController",
        data: {
            action: "consultaDatos",
            txtPaciente: $("#txtNombres").val(),
            nIdNivel:idNivel,
            anioAcademico : $('#cboAñoAcademico option:selected').text().trim()
        }
    })
    .done(function (data, status, jqXhr) { 
        arregloAlumnos = data.split(":");
        limparselect();
        if (data=='') {
            $('#tblAlumnoPension').append('<tr><td class="text-left" colspan = "4"><label> No se encontraron datos ... </label></td></tr>'); 
        }
        else{
            for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
                var idPersona =  arregloAlumnos[i].split("-")[0];
                var numeroDocumento  =  arregloAlumnos[i].split("-")[1];            
                var apellidoPaterno=  arregloAlumnos[i].split("-")[2];            
                var apellidoMaterno =  arregloAlumnos[i].split("-")[3];
                var nombres =  arregloAlumnos[i].split("-")[4];
                var nombreCompleto = apellidoPaterno + " " + apellidoMaterno + " " + nombres;
                var pension =  arregloAlumnos[i].split("-")[5]; 
                $('#tblAlumnoPension').append('<tr>\n\
                                                     <td class="text-left"><label><strong>'+numeroDocumento+'</strong></label></td>\n\
                                                     <td class="text-left"><label>'+nombreCompleto+ '</label></td>\n\
                                                     <td><input type="text" class="clsPension" value="'+pension+ '" id="txtAlumno' + idPersona + '" style="text-align: center;width:60px;"/></td>\n\
                                                     <td><label>'+idPersona+'</label></td>\n\
                                                     <td><button type="button" id="btnPersona' + idPersona + '" style="height:24px;width:60px;">√</button></td>\n\
                                                     <td><span id = "lbl'+ idPersona+'">' + idPersona + '</span></td>\n\
                                               </tr>');                 
                $('#tblAlumnoPension tr td:nth-child(4)').hide();
                $('#tblAlumnoPension tr td:nth-child(6)').hide(); 
                fnPensionAlumno(i, idPersona);                
            }
        }
        $('#tblAlumnoPension tr td:nth-child(3) input').keypress(function(e) {
            var verified = (e.which == 8 || e.which == undefined || e.which == 0) ? null : String.fromCharCode(e.which).match(/[^0-9.]/);
            if (verified) {
                e.preventDefault();
            }
        });     
        //fnDlbClick();  
        resultadosClickHandlerConsultaAlumno(Descripcion);
               
    });    
    function limparselect(){
        $('#tblAlumnoPension tr').remove();
    }
}

function resultadosClickHandlerConsultaAlumnosGrado(idPersona) {
    dlgAlumnoGradopension.dialog("open");
    buscarPago(idPersona);
}



function resultadosClickHandlerConsulta(Descripcion) {
    $('#btnGrado' + Descripcion).on("click", function(){
        dlgGradopension.dialog("open");
    })    
}

function resultadosClickHandlerConsultaAlumno(Descripcion) { 
    
    $('#btnAlumno' + Descripcion).on("click", function(){
        dlgAlumnopension.dialog("open");
        $('#tblAlumnoPension tr').remove();
        $('#txtNombres').val("");  
    })
}
 
function fnCargarNiveles()
{
    $('#tblNiveles tr td button').hide();
    $.ajax({
        url: "registroMatriculaServletController",
        data: {
            action: "obtenerNivel",
            anioAcademico : $('#cboAñoAcademico option:selected').text().trim()
        }                
    })
    .done(function (data, status, jqXhr) {     
        arregloAlumnos = data.split(":");  
        limparselect();                
        if (data=='') {
            $('#tblNiveles').append('<tr> No se encontraron datos ... </tr>'); 
        }
        else{
            for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
                var idNivel =  arregloAlumnos[i].split("-")[0];
                var Descripcion  =  arregloAlumnos[i].split("-")[1];
                var Pension  =  arregloAlumnos[i].split("-")[2];                
                $('#tblNiveles').append(
                                        '<tr>\n\
                                            <td><input type="radio" name = pension id="rd' + Descripcion + '" value="'+ Descripcion +'"/></td>\n\
                                            <td><label>' + Descripcion + '</label></td>\n\
                                            <td><input type="text" value="'+ Pension +'" id="txt' + Descripcion + '" style="text-align: center" /></td>\n\
                                            <td><button type="button" id="btn' + Descripcion + '" style="height:24px;width:100px;">Mas detalle</button></td>\n\
                                            <td><span id = "lbl'+ idNivel+'">' + idNivel + '</span></td>\n\
        `                                </tr>'+
                                        '<tr id="tr' + Descripcion + '">\n\
                                            <td colspan="2"><button type="button" id="btnGrado' + Descripcion + '" style="height:24px;width:100px;margin-right: 0px;">GRADO</button></td>\n\
                                            <td colspan="2"><button type="button" id="btnAlumno' + Descripcion + '" style="height:24px;width:100px;margin-right: 0px;">ALUMNO</button></td>\n\
                                        </tr>'
                                        );
                $('#tr'+ Descripcion).hide();
                $('#btn'+ Descripcion).hide();
                $('#tblNiveles tr td:nth-child(5)').hide();


            }
            clickNivel(Descripcion, idNivel);
            
            $('#tblNiveles tr td input[type=text]').each(function(e){
                $('#btnNivel').on("click", function(){ 

                    ingresarPensionMatricula($('#tblNiveles tr td:nth-child(5) span').eq(e).text().trim(), 0, 0,  $('#tblNiveles tr td:nth-child(3) input').eq(e).val().trim(), 0); 
                    $('#trINICIAL').hide(500);
                    $('#trPRIMARIA').hide(500);
                    $('#trSECUNDARIA').hide(500);
                    $("#tblNiveles input[name='pension']:radio").removeAttr("checked");                    
                })                
            })
        }
        
        $('#btnNivelCancelar').on("click", function(){ 
            $('#trINICIAL').hide(500);
            $('#trPRIMARIA').hide(500);
            $('#trSECUNDARIA').hide(500);
            $("#tblNiveles input[name='pension']:radio").removeAttr("checked");  
            fnCargarNiveles();
            $("#tblNiveles").hide();
            $(".pensionBtn").hide();
            
        })         
        
        $('#tblNiveles tr td input').keypress(function(e) {
            var verified = (e.which == 8 || e.which == undefined || e.which == 0) ? null : String.fromCharCode(e.which).match(/[^0-9.]/);
            if (verified) {
                e.preventDefault();
            }
        }); 
 
    });
    function limparselect(){
        $('#tblNiveles tr').remove();
    }
}

function clickNivel(Descripcion, idNivel){
    $("#tblNiveles input[name='pension']:radio").click(function() {
        if ($(this).is(':checked')){
                        
            if ($(this).val().trim() == "INICIAL"){
                $('#trINICIAL').fadeIn(500);
                $('#trPRIMARIA').hide(500);
                $('#trSECUNDARIA').hide(500);
                fnObtenerGrado("INICIAL");
                fnObtenerAlumno("INICIAL", 1);             
                $("#btnBuscarInicial").show();
                $("#btnBuscarPrimaria").hide();
                $("#btnBuscarSecundaria").hide();
                $("#btnBuscarInicial").on("click",function(e){
                    fnObtenerAlumno("INICIAL", 1);
                    e.preventDefault();
                })  
            }
            else if ($(this).val().trim() == "PRIMARIA"){
                $('#trINICIAL').hide(500);
                $('#trPRIMARIA').fadeIn(500);
                $('#trSECUNDARIA').hide(500);                         
                fnObtenerGrado("PRIMARIA");
                fnObtenerAlumno("PRIMARIA", 2);               
                $("#btnBuscarInicial").hide();
                $("#btnBuscarPrimaria").show();
                $("#btnBuscarSecundaria").hide();                
                $("#btnBuscarPrimaria").on("click",function(e){
                    fnObtenerAlumno("PRIMARIA", 2);
                    e.preventDefault();
                })  
            }
            else if ($(this).val().trim() == "SECUNDARIA"){
                $('#trINICIAL').hide(500);
                $('#trPRIMARIA').hide(500);
                $('#trSECUNDARIA').fadeIn(500);  
                fnObtenerGrado("SECUNDARIA");
                fnObtenerAlumno("SECUNDARIA", 3);            
                $("#btnBuscarInicial").hide();
                $("#btnBuscarPrimaria").hide();
                $("#btnBuscarSecundaria").show();                
                $("#btnBuscarSecundaria").on("click",function(e){
                    fnObtenerAlumno("SECUNDARIA", 3);
                    e.preventDefault();
                })                 
            }else {
        }
    }
});      
}

function fnObtenerGrado(Descripcion){
//$("#tblNiveles tr").each(function(i){  
       
$.ajax({
    url: 'registroMatriculaServletController',
    data: {
        action: "rowclick",
        descripcionNivel: Descripcion.trim(),
        anioAcademico : $('#cboAñoAcademico option:selected').text().trim()
    }          
})
        
.done(function (data, status, jqXhr) {
    pegadados = data.split(":");
    limparselect();
    if(data == '')
        $('#tblGrado').append('<tr>Grado no encontrado</tr>');
    else{
        for(var i = 0; i < pegadados.length - 1; i++){
            var codigoGrado = pegadados[i].split("-")[0]; 
            var descripcionGrado = pegadados[i].split("-")[1];
            var pension = pegadados[i].split("-")[2]; 
            $('#tblGrado').append('<tr>\n\
                                        <td><label>' + descripcionGrado + '</label></td>\n\
                                        <td><input type="text" value="'+ pension +'" id="txt' + codigoGrado + '" style="text-align: center"/></td>\n\
                                        <td><button type="button" id="btn' + codigoGrado + '" style="height:24px;width:60px;">√</button></td>\n\
                                        <td><span id = "lbl'+ codigoGrado+'">' + codigoGrado + '</span></td>\n\
                                      </tr>');
                
            $('#tblGrado tr td:nth-child(4)').hide();
            $('#tblGrado tr td:nth-child(3)').hide();
        }
        $('#tblGrado tr td input').each(function(e){
            $('#btnGrado').on("click", function(){ 
                ingresarPensionMatricula(0, $('#tblGrado tr td:nth-child(4) span').eq(e).text(), 0,  $('#tblGrado tr td:nth-child(2) input').eq(e).val(), 0);                                
            })                
        })            
    }
        
    $('#tblGrado tr td input').keypress(function(e) {
        var verified = (e.which == 8 || e.which == undefined || e.which == 0) ? null : String.fromCharCode(e.which).match(/[^0-9.]/);
        if (verified) {
            e.preventDefault();
        }
    }); 
    resultadosClickHandlerConsulta(Descripcion);
});
        
function limparselect(){
    $('#tblGrado tr').remove();
}
//})
}

function fnDlbClick(){
$('#tblAlumnoPension tr td').dblclick(function(){
    var idPersona = $(this).parent().children().eq(5).text();
    resultadosClickHandlerConsultaAlumnosGrado(idPersona)          
    //        }
});
}

function buscarPago(numeroDocumento){
$.ajax({
    type: 'GET',        
    url: "registroPensionesServletController",
    data: {
        action: "buscarPensionMes",
        idPersona: numeroDocumento,
        anioAcademico : $('#cboAñoAcademico option:selected').text().trim()
    }
})
.done(function (data, status, jqXhr) { 
    arregloAlumnos = data.split(":");
    limparselect();
    if (data=='') {
        $('#tblPagosMes').append('<tr><td colspan = "3"><label> No se encontraron datos ... </label></td></tr>'); 
    }
    else{
        for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
            var mes =  arregloAlumnos[i].split("-")[0];
            var monto  =  arregloAlumnos[i].split("-")[1]; 
            var nIdAlumnoDeuda = arregloAlumnos[i].split("-")[2];

            if (monto == null)
            {
            }else{
                $('#tblPagosMes').append('<tr>\n\
                                            <td class="text-left"><label>'+mes+'</label></td>\n\
                                            <td><input type="text" id="txt' + mes + '" value="' + monto + '"style="text-align: center;width:70px;"/></td>\n\
                                            <td><button type="button" id="btnMes' + nIdAlumnoDeuda + '" style="height:24px;width:60px;">√</button></td>\n\
                                            <td><span id = "lblMes'+ nIdAlumnoDeuda+'">' + nIdAlumnoDeuda + '</span></td>\n\
                                         </tr>');                 
                   
                $('#tblPagosMes tr td:nth-child(4)').hide();
                // if your table has header(th), use this0234228
                //$('td:nth-child(2),th:nth-child(2)').hide();  
                fnPensionAlumnoDetalle(i, nIdAlumnoDeuda)
            }                
        } 
    } 
        
    $('#tblPagosMes tr td input').keypress(function(e) {
        var verified = (e.which == 8 || e.which == undefined || e.which == 0) ? null : String.fromCharCode(e.which).match(/[^0-9.]/);
        if (verified) {
            e.preventDefault();
        }
    }); 


});  
function limparselect(){
    $('#tblPagosMes tr').remove();       
}
}

function ingresarPensionMatricula(iNivel, iGrado, iAlumno, iPension, iPersonaDeuda) {
//validar();        
//if (!validado) {return;}
console.log(iGrado);
$.ajax({
    url: "registroMatriculaServletController",
    data: {
        action: "ingresarDatosMatricula",
        IDNIVEL: iNivel,
        IDGRADO: iGrado,
        IDPERSONA: iAlumno,
        PENSION: iPension,
        IDPERSONADEUDA : iPersonaDeuda,
        anioAcademico : $('#cboAñoAcademico option:selected').text().trim()
    }
})
.done(function (data, status, jqXhr) { 
    resultado = data.split(",");
    var codResultado = resultado[0];
    var descResultado = resultado[1];            
    jAlert(descResultado);          
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

function fnPensionAlumno(i, nIdPersona){
$('#btnPersona' + nIdPersona).on("click", function(){ 
    ingresarPensionMatricula(0, 0, $('#tblAlumnoPension tr td:nth-child(6) span').eq(i).text(),  $('#tblAlumnoPension tr td:nth-child(3) input').eq(i).val(), 0);                                
});    
}

function fnPensionAlumnoDetalle(i, nIdPersonaDeuda){
$('#btnMes' + nIdPersonaDeuda).on("click", function(){ 
    ingresarPensionMatricula(0, 0, 0, $('#tblPagosMes tr td:nth-child(2) input').eq(i).val(), $('#tblPagosMes tr td:nth-child(4) span').eq(i).text());                                
});    
}


