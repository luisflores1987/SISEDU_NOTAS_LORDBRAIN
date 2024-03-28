$(document).ready(function(){
   
    fnInicializarElementos();
    fnEventoClick();
    fnEventoLimpiarElementos();
   
});

function fnInicializarElementos()
{
    
    $("#cbDiaDeSemana").attr("disabled","disabled");    
    $("#cbProfesor").attr("disabled","disabled"); 
    $(".txtFiltro").each(function(){
        $(this).val("");
    });
    // reporte detallado
    //$("#btnBuscarHorarioProfesor").attr("disabled","disabled");
    // reporte consolidado
    fnLlenarCombo();    
}


function fnLlenarCombo()
{
    fnComboProfesor();
    fnComboDiaDeSemana();
}

function fnComboProfesor()
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

function fnComboDiaDeSemana()
{
    $.ajax({
        url:"obtenerReporteAsistenciaServletController",
        data:{
            action:"conseguirDiasSemana"           
        }
    })
    .done(function(data){
        var arregloHorario = data.split("+");
        limparselectHorario();
        if (data == "")
            $('#cbDiaDeSemana').append('<option> No se encontraron datos ... </option>');
        else
        {
            $('#cbDiaDeSemana').append('<option></option>');
            for(var i = 0;i< arregloHorario.length - 1;i++)
            {
                var nidHorario = arregloHorario[i].split("*")[0];
                var sDescricionHorario = arregloHorario[i].split("*")[1];
                $('#cbDiaDeSemana').append('<option value = '+ nidHorario + '>' + sDescricionHorario + '</option>');
            }                    
        }
        function limparselectHorario(){
            $('#cbDiaDeSemana option').remove();
        }                
    }); 
}

function fnEventoClick(){
    
    $('#btnBuscarHorarioProfesor').on("click", function(e){
        fnValidarDatos();
        if (!validado) {                    
            return;
        }
        fnObtenerProfesor();
        e.preventDefault();
    });        
}

function fnObtenerProfesor(){
    
    $.ajax({
        url:"obtenerReporteAsistenciaServletController",
        data:{
            action:"obtenerHorariosProfesor",
            txtIdPersona    : $('#cbProfesor option:selected').val().trim(),
            txtIdDiaSemana  : $('#cbDiaDeSemana option:selected').val().trim(),            
            cbProfesor      : $('input[type="checkbox"][id="ckProfesor"]').is(':checked'), 
            cbDiaSemana     : $('input[type="checkbox"][id="chkDiaSemana"]').is(':checked')
        }
        
    })
    .done(function(data){
        
        arregloHorario = data.split("+");
        limparselectAsistencia();
        if (data=='') {
            $('#tblProfesorHorario').append('<tr><td bgcolor="#F2F2F2" colspan = "5" style="width: 594px;"><label> No se encontraron datos ... </label></td></tr>'); 
        }
        else{
            for (var i = 0 ;i < arregloHorario.length - 1; i++){
                var nidPersona           =  arregloHorario[i].split("*")[0];
                var sNombreCompleto     =  arregloHorario[i].split("*")[1];            
                var sDia                =  arregloHorario[i].split("*")[2];            
                var sHoraIngreso       =  arregloHorario[i].split("*")[3];
                var sHoraSalida  =  arregloHorario[i].split("*")[4];
                if(arregloHorario.length  > 17)
                {
                    $('#tblProfesorHorario').append('<tr>\n\
                                                        <td bgcolor="#F2F2F2" class="text-center" style="width:414px">'+sNombreCompleto+'</td>\n\
                                                        <td bgcolor="#F2F2F2" class="text-center" style="width:100px">'+sDia+'</td>\n\
                                                        <td bgcolor="#F2F2F2" class="text-center" style="width:100px">'+sHoraIngreso+'</td>\n\
                                                        <td bgcolor="#F2F2F2" class="text-center" style="width:100px">'+sHoraSalida+'</td>\n\
                                                     </tr>'
                        );                              
                }
                else
                {
                    $('#tblProfesorHorario').append('<tr>\n\
                                                        <td bgcolor="#F2F2F2" class="text-center" style="width:414px">'+sNombreCompleto+'</td>\n\
                                                        <td bgcolor="#F2F2F2" class="text-center" style="width:100px">'+sDia+'</td>\n\
                                                        <td bgcolor="#F2F2F2" class="text-center" style="width:100px">'+sHoraIngreso+'</td>\n\
                                                        <td bgcolor="#F2F2F2" class="text-center" style="width:117px">'+sHoraSalida+'</td>\n\
                                                     </tr>'
                        );         
                }

            }            
        }
        //$("#btnExportarBuscarAsistenciaProfesor").removeAttr("disabled");
        function limparselectAsistencia(){
            $('#tblProfesorHorario tr').remove();       
        }        
    });
    
}

function fnEventoLimpiarElementos()
{
    $("#chkDiaSemana").on("click", function(){
        if ($(this).is(":checked"))
        {
            $("#cbDiaDeSemana").val("");
            $("#cbDiaDeSemana").removeAttr("disabled");     
        }
        else
        {
            $("#cbDiaDeSemana").val("");
            $("#cbDiaDeSemana").attr("disabled","disabled");
        }
    });


    $("#ckProfesor").on("click", function(){
        if ($(this).is(":checked"))
        {            
            $("#cbProfesor").val("");
            $("#cbProfesor").removeAttr("disabled");      
        }
        else
        {
            $("#cbProfesor").val("");
            $("#cbProfesor").attr("disabled","disabled");
        }        
    });    
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