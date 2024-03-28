$(document).ready(function(){
    fnInicializarElementos();
    fnEventoClick();
    fnEventoLimpiarElementos();

})

function fnObtenerReporte()
{
   
    $.ajax({
        url:"obtenerReporteAsistenciaServletController",
        data:{
            action:"obtenerReporteAsistencia",
            txtApNom: $('#cbProfesor option:selected').val().trim(),
            txtFechaInicial: $("#txtFechaConsultaINICIAL").val(),
            txtFechaFin: $("#txtFechaConsultaFIN").val(),            
            
            FECHA: $('input[type="checkbox"][id="ckFECHA"]').is(':checked'), 
            APNOM: $('input[type="checkbox"][id="chkProfesor"]').is(':checked')
        }        
    })
    .done(function(data){
        
        arregloAsistencia = data.split("+");
        limparselectAsistencia();
        if (data=='') {
            $('#tblProfesoresAsistencia').append('<tr><td bgcolor="#F2F2F2" colspan = "5" style="width: 594px;"><label> No se encontraron datos ... </label></td></tr>'); 
        }
        else{
            for (var i = 0 ;i < arregloAsistencia.length - 1; i++){
                var idPersona           =  arregloAsistencia[i].split("*")[0];
                var idProfesor          =  arregloAsistencia[i].split("*")[1];            
                var NombreProfesor      =  arregloAsistencia[i].split("*")[2];            
                var fechaRegistro       =  arregloAsistencia[i].split("*")[3];
                var fechaRegistroHora   =  arregloAsistencia[i].split("*")[4];
                var fechaEntradaHora    =  arregloAsistencia[i].split("*")[5];
                var cantidad            =  arregloAsistencia[i].split("*")[6];

                $('#tblProfesoresAsistencia').append('<tr>\n\
                                                        <td bgcolor="#F2F2F2" class="text-center">'+idProfesor+'</td>\n\
                                                        <td bgcolor="#F2F2F2" class="text-center">'+NombreProfesor+'</td>\n\
                                                        <td bgcolor="#F2F2F2" class="text-center">'+fechaRegistro+'</td>\n\
                                                        <td bgcolor="#F2F2F2" class="text-center">'+fechaEntradaHora+'</td>\n\
                                                        <td bgcolor="#F2F2F2" class="text-center">'+fechaRegistroHora+'</td>\n\
                                                        <td bgcolor="#F2F2F2" class="text-center">'+cantidad+'</td>\n\
                                                      </tr>'
                    );  
            }
            
        }
        $("#btnExportarBuscarAsistenciaProfesor").removeAttr("disabled");
        function limparselectAsistencia(){
            $('#tblProfesoresAsistencia tr').remove();       
        }        
    });
    
}

function fnObtenerReporteConsolidado()
{
    $.ajax({
        url:"obtenerReporteAsistenciaServletController",
        data:{
            action:"obtenerReporteAsistenciaConsolidado",
            chkFecha :  $('input[type="checkbox"][id="ckFECHA"]').is(':checked'),
            chkProfesor :  $('input[type="checkbox"][id="chkProfesor"]').is(':checked'),
            txtFechaInicial: $("#txtFechaConsultaINICIAL").val(),
            txtFechaFin: $("#txtFechaConsultaFIN").val(),
            cbProfesor: $('#cbProfesor option:selected').val().trim()
        }
        
    })
    .done(function(data){
        
        arregloAsistencia = data.split("+");
        limparselectAsistencia();
        if (data=='') {
            $('#tblProfesoresAsistenciaConsolidado').append('<tr><td bgcolor="#F2F2F2" colspan = "4" style="width: 594px;"><label> No se encontraron datos ... </label></td></tr>'); 
        }
        else{
            for (var i = 0 ;i < arregloAsistencia.length - 1; i++){
                var nIdProfesor         =  arregloAsistencia[i].split("*")[0];
                var sNombreProfesor     =  arregloAsistencia[i].split("*")[1];            
                var fCantidad           =  arregloAsistencia[i].split("*")[2];

                $('#tblProfesoresAsistenciaConsolidado').append('<tr>\n\
                                                        <td bgcolor="#F2F2F2" class="text-center">'+nIdProfesor+'</td>\n\
                                                        <td bgcolor="#F2F2F2" class="text-center">'+sNombreProfesor+'</td>\n\
                                                        <td bgcolor="#F2F2F2" class="text-center">'+fCantidad+'</td>\n\
                                                      </tr>'
                    );  
            }            
        }
        $("#btnExportarBuscarAsistenciaProfesorConsolidado").removeAttr("disabled");
        function limparselectAsistencia(){
            $('#tblProfesoresAsistenciaConsolidado tr').remove();       
        }        
    });
    
}

function fnEventoClick()
{
    $("#btnBuscarAsistenciaProfesor").on("click", function(e){
        fnValidarDatos();
        if (!validado) {                    
            return;
        }
        fnObtenerReporte();
        e.preventDefault();
    });
    
    $("#btnBuscarAsistenciaProfesorConsolidado").on("click", function(e){
        fnValidarDatos();
        if (!validado) {                    
            return;
        }
        fnObtenerReporteConsolidado();
        e.preventDefault();
    });
    
    // INICIO Exportar a excel
    
    $("#btnExportarBuscarAsistenciaProfesor").click(function(e) {
        window.open('data:application/vnd.ms-excel,' + escape(($('#tblTablaConsultaAsistencia').html())));        // se coloca el metodo escape para evitar la codifcacion utf-8, la cual nos cambia la letra ñ
        e.preventDefault();
    });
    
    $("#btnExportarBuscarAsistenciaProfesorConsolidado").click(function(e) {
        window.open('data:application/vnd.ms-excel,' + escape(($('#tblTablaConsultaConsolidado').html())));        // se coloca el metodo escape para evitar la codifcacion utf-8, la cual nos cambia la letra ñ
        e.preventDefault();
    });    
// FINAL Exportar a excel
}

function fnEventoLimpiarElementos()
{
    $("#ckFECHA").on("click", function(){
        if ($(this).is(":checked"))
        {
            $("#txtFechaConsultaINICIAL").val("");
            $("#txtFechaConsultaFIN").val("");
            $("#txtFechaConsultaINICIAL").removeAttr("disabled");
            $("#txtFechaConsultaFIN").removeAttr("disabled");            
        }
        else
        {
            $("#txtFechaConsultaINICIAL").val("");
            $("#txtFechaConsultaFIN").val("");
            $("#txtFechaConsultaINICIAL").attr("disabled","disabled");
            $("#txtFechaConsultaFIN").attr("disabled","disabled"); 
            fnValidarBoton();
        }
    });


    $("#chkProfesor").on("click", function(){
        if ($(this).is(":checked"))
        {            
            $("#cbProfesor").val("");
            $("#cbProfesor").removeAttr("disabled");      
        }
        else
        {
            $("#cbProfesor").val("");
            $("#cbProfesor").attr("disabled","disabled");
            fnValidarBoton();
        }        
    });    
}

function fnInicializarElementos()
{
    $("#txtFechaConsultaINICIAL").attr("disabled","disabled");
    $("#txtFechaConsultaFIN").attr("disabled","disabled");        
    $("#txtDni").attr("disabled","disabled");    
    $("#cbProfesor").attr("disabled","disabled"); 
    $(".txtFiltro").each(function(){
        $(this).val("");
    });
    // reporte detallado
    $("#btnExportarBuscarAsistenciaProfesor").attr("disabled","disabled");
    // reporte consolidado
    $("#btnExportarBuscarAsistenciaProfesorConsolidado").attr("disabled","disabled");
    fnLlenarCombo();
}

function fnValidarDatos() // valida datos si no se ha ingresado datos.
{
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
        alert("Debe ingresar al menos un tipo de busqueda", "Mensaje"); 
        validado = false;
        return false;
    }

}

function fnValidarBoton()
{
    var cantx = 0;
    var canty = 0;
    
    $(".chkFiltro").each(function(){        
        cantx = cantx + 1;
        if ($(this).is(":checked")) {    
        }
        else{
            canty = canty + 1;
        }
    });
    if (cantx == canty)
    {
        $("#btnExportarBuscarAsistenciaProfesor").attr("disabled","disabled");
        $("#btnExportarBuscarAsistenciaProfesorConsolidado").attr("disabled","disabled");
        
    }  
}

function fnLlenarCombo()
{
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
            $('#cbProfesor').append('<option> No se encontraron datos ... </option>');
        else
        {
            $('#cbProfesor').append('<option></option>');
            for(var i = 0;i< arregloProfesor.length - 1;i++)
            {
                var nidPersona = arregloProfesor[i].split("*")[0];
                var sDatosPersonales = arregloProfesor[i].split("*")[1];
                $('#cbProfesor').append('<option value = '+ nidPersona + '>' + sDatosPersonales + '</option>');
            }                    
        }
        function limparselectProfesores(){
            $('#cbProfesor option').remove();
        }                
    });
}
