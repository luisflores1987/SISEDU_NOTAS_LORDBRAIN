$(document).ready(function(){
    $('#cboGrado').hide();
    $('#ckNIVEL').attr("checked", "checked");
    $('#ckNIVEL').attr("disabled", "disabled");
    fnGetDateActual();
    
    fnCargarNivel();    

    inhabilitartextBoxConsulta()
   
    eventoCheckBoxConsulta();

    $("#btnBuscarDeudasAlumno").on("click",function(e){
        buscarAlumnoDeuda();
        e.preventDefault();
    })
    
    $('#spFechaActual').css('color','#1F0303');
    
    $("#btnExportarBuscarDeudasAlumno").click(function(e) {

        window.open('data:application/vnd.ms-excel,' + escape(($('#divTablaConsultaDeuda').html())));        // se coloca el metodo escape para evitar la codifcacion utf-8, la cual nos cambia la letra ñ
        e.preventDefault();
    });
});

function inhabilitartextBoxConsulta(){
    $('#cboGrado').attr("disabled", "disabled");  
}

function buscarAlumnoDeuda(){
    
    $.ajax({
        url: "consultaDeudaServletController",
        data: {
            action: "consultaAlumnoDeuda",
            cboGrado: $('#cboGrado option:selected').val(),
            cboNivel: $('#cboNivel option:selected').val(),
            NIVEL: $('input[type="checkbox"][id="ckNIVEL"]').is(':checked'),            
            GRADO: $('input[type="checkbox"][id="ckGRADO"]').is(':checked'),
            anioAcademico: $('#cboAñoAcademico option:selected').text().trim()
        }
    })
    .done(function (data, status, jqXhr) { 
        arregloAlumnos = data.split(":");
        console.log("filas  " + arregloAlumnos.length);
        limparselect();
        if (data=='') {
            $('#tblAlumnosDeudas').append('<tr><td colspan = "14"><label> No se encontraron datos ... </label></td></tr>'); 
        }
        else{
            for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
                
                var nombres =  arregloAlumnos[i].split("-")[0];
                var matricula  =  arregloAlumnos[i].split("-")[1];            
                var marzo=  arregloAlumnos[i].split("-")[2];            
                var abril =  arregloAlumnos[i].split("-")[3];
                var mayo =  arregloAlumnos[i].split("-")[4];          
                var junio =  arregloAlumnos[i].split("-")[5];          
                var julio =  arregloAlumnos[i].split("-")[6];          
                var agosto =  arregloAlumnos[i].split("-")[7];                                          
                var setiembre =  arregloAlumnos[i].split("-")[8];                                          
                var octubre =  arregloAlumnos[i].split("-")[9];
                var noviembre =  arregloAlumnos[i].split("-")[10]; 
                var diciembre =  arregloAlumnos[i].split("-")[11];
                var vacacional =  arregloAlumnos[i].split("-")[12];    
                $('#tblAlumnosDeudas').append('<tr>\n\
                                                <td bgcolor="#F2F2F2" class="text-right">'+(i+1)+'</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-left">'+nombres+'</td>\n\\n\
                                                <td bgcolor="#F2F2F2" class="text-right">'+vacacional+'</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-right">'+matricula+'</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-right">'+marzo+'</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-right">'+abril+'</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-right">'+mayo+'</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-right">'+junio+'</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-right">'+julio+'</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-right">'+agosto+'</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-right">'+setiembre+'</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-right">'+octubre+'</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-right">'+noviembre+'</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-right">'+diciembre+'</td>\n\
                                             </tr>'
                    );                 
            // if your table has header(th), use this
            //$('td:nth-child(2),th:nth-child(2)').hide(); 
            }
        }
    //        resultadosClickHandler();
    //        resultadosClickApoderadoHandler();        
    });  
    function limparselect(){
        $('#tblAlumnosDeudas tr').remove();       
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

function eventoCheckBoxConsulta(){
   
    $('#ckNIVEL').click(function(){
        if (($('#ckNIVEL').is(':checked'))){
            $('#cboNivel').removeAttr("disabled");
        }
        else
        {
            $('#cboNivel').attr("disabled", "disabled");         
        }        
    }) 
    
    $('#ckGRADO').click(function(){
        if (($('#ckGRADO').is(':checked'))){
            $('#cboGrado').removeAttr("disabled");
            $('#cboGrado').show()
        }
        else
        {
            $('#cboGrado').attr("disabled", "disabled");
            $('#cboGrado').hide()
        }        
    })     
}

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
            $('#spFechaActual').append('<h5>FECHA :' + codigoGrado + '</h5>');
        }        
    });
}
