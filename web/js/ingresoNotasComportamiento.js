$(document).ready(function(){
    fnObtenerGrado();  
    fnObtenerAlumnosCurso();
    fnInicializarPopUp();
    fnEventoBotones();
    fnObtenerParticipacion();
    fnObtenerBimestre();
})

function fnObtenerBimestre()
{
    
    $.ajax({
        url : "ingresoNotasProfesorServletController",
        data:{
            action:"obtenerBimestre"
        }
    })
    .done(function (data, status, jqXhr) {     
        arregloAlumnos = data.split(":");  
        limparselect();                
        if (data=='') {
            $('#cboBimestre').append('<option> No se encontraron datos ... </option>'); 
        }
        else{
            $('#cboBimestre').append('<option value = 0></option>');          
            for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
                var idBimestre =  arregloAlumnos[i].split("-")[0];
                var sDescripcion  =  arregloAlumnos[i].split("-")[1];
                var sHabilitar  =  arregloAlumnos[i].split("-")[2];
                if (sHabilitar == "1")
                {
                    $('#cboBimestre').append('<option value = '+idBimestre.trim()+' disabled>'+ sDescripcion +'</option> ');                         
                }
                else
                {
                    $('#cboBimestre').append('<option value = '+idBimestre.trim()+'>'+ sDescripcion +'</option> ');                                                 
                }
            }
        }
    });
    function limparselect(){
        $('#cboBimestre option').remove();
    }   
}

function fnObtenerAlumnosCurso()
{
    $('#cboBimestre').change(function(){
        
        $('#divNotas').css('display', 'block'); 
        $.ajax({
            url: "ingresoNotasProfesorServletController",
            data: {
                action: "obtenerAlumnoxTutor",
                IdPersona : $('#lblIdPersona').text().trim(),
                IdBimestre : $(this).val()
            }                
        })
        .done(function (data, status, jqXhr) {     
            var arregloCursoAlumno = data.split("+"); 
            fnLimpiar();
            if (data == null){
                $('#tblAlumnoNotas').append('<tr><td colspan = "3">No se encuentran datos ...</td></tr>');
            }else{
                for(var i = 0;i<arregloCursoAlumno.length -1 ;i++)
                {
                    var nIdAlumno = arregloCursoAlumno[i].split("*")[0]
                    var sNombreCompleto = arregloCursoAlumno[i].split("*")[1]
                    var nNota1 = arregloCursoAlumno[i].split("*")[2]
                    var sSugerencia = arregloCursoAlumno[i].split('*')[3]
                    var sSeccion = arregloCursoAlumno[i].split('*')[4].trim()
                    var sSeccionProfesor   = arregloCursoAlumno[i].split('*')[5].trim()
                              
                    if (sSeccion == sSeccionProfesor){
                                
                        $('#tblAlumnoNotas').append('<tr>\n\
                                                        <td><label>'+ nIdAlumno +'</label></td>\n\
                                                        <td><label>'+ $('#spIdCurso').text() +'</label></td>\n\
                                                        <td><label>'+ $('#cboBimestre').val() +'</label></td>\n\
                                                        <td><label>1</label></td>\n\
                                                        <td style="width: 80%;"><label>'+ nNota1 +'</label></td>\n\\n\
                                                        <td style="width: 80%;"><label>'+ sSugerencia +'</label></td>\n\
                                                        <td style="width: 80%;"><label>'+ sNombreCompleto +'</label></td>\n\
                                                     </tr>');  
                        
                    }else{
                            
                        $('#tblAlumnoNotas').append('<tr style="display:none;">\n\
                                                        <td><label>'+ nIdAlumno +'</label></td>\n\
                                                        <td><label>'+ $('#spIdCurso').text() +'</label></td>\n\
                                                        <td><label>'+ $('#cboBimestre').val() +'</label></td>\n\
                                                        <td><label>'+ nIdAlumno +'</label></td>\n\
                                                        <td style="width: 80%;"><label>'+ sNombreCompleto +'</label></td>\n\
                                                        <td><input style="width: 95%;" class = "clsnotas'+ nIdAlumno +'" type="text" value= "'+ nNota1 +'" disabled/></td>\n\
                                                     </tr>');    
                    }                            
                }
                fnEventoFila();
                $('#tblAlumnoNotas tr td:nth-child(1)').hide();
                $('#tblAlumnoNotas tr td:nth-child(2)').hide();
                $('#tblAlumnoNotas tr td:nth-child(3)').hide();
                $('#tblAlumnoNotas tr td:nth-child(4)').hide();
                $('#tblAlumnoNotas tr td:nth-child(5)').hide();
                $('#tblAlumnoNotas tr td:nth-child(6)').hide();
            }

            function  fnLimpiar(){
                $('#tblAlumnoNotas tr').remove();
            }
        }); 
    }) 
}

function fnObtenerGrado()
{
    $.ajax({
        url : "ingresoNotasProfesorServletController",
        data:{
            action:"obtenerGradoTutor",
            nIdPersona: $('#lblIdPersona').text().trim()
        }
    })
    .done(function(data){
        var arregloGrado = data.split("+");
        fnLimpiar();
        if(data == null){
            $('#ulGradoProfesor').append('<li>No tiene grados asociados..</li>')
        }
        else{
            for(var i=0;i<arregloGrado.length - 1;i++)
            {
                var nidGrado = arregloGrado[i].split("*")[0];
                var sDescripcion = arregloGrado[i].split("*")[1];
                var sSeccion = arregloGrado[i].split("*")[2];
                
                $('#spGradoNota').text(sDescripcion);
                $('#spSeccion').text(sSeccion);
            }
        }
        function  fnLimpiar(){
            $('#spGradoNota').text('');
        }       
    });
}

function fnInicializarPopUp(){
    $('#divIngresoRegistros').css("display","none");
};

function resultadosClickHandlerConsulta() {        
    dlgSugerencia.dialog("open");
}

function fnEventoFila(){
    $('#tblAlumnoNotas tr td:nth-child(7)').each(function(i){
        $(this).click(function(){
            $.ajax({
                url: "ingresoNotasProfesorServletController",
                data: {
                    action: "obtenerAlumnoxTutor",
                    IdPersona : $('#lblIdPersona').text().trim(),
                    IdBimestre : $('#cboBimestre').val()
                }                
            })
            .done(function (data, status, jqXhr){
                var arregloCursoAlumno = data.split("+"); 
                var nIdAlumno = arregloCursoAlumno[i].split("*")[0]
                var sNombreCompleto = arregloCursoAlumno[i].split("*")[1]
                var nNota1 = arregloCursoAlumno[i].split("*")[2]
                var sSugerencia = arregloCursoAlumno[i].split('*')[3]
                var sSeccion = arregloCursoAlumno[i].split('*')[4].trim()
                var sSeccionProfesor   = arregloCursoAlumno[i].split('*')[5].trim()
                ///---
                var sTardanzas   = arregloCursoAlumno[i].split('*')[6].trim()
                var sInasJustificadas   = arregloCursoAlumno[i].split('*')[7].trim()
                var sInasInjustificadas   = arregloCursoAlumno[i].split('*')[8].trim()
                var nIdParticipacionPPFF   = arregloCursoAlumno[i].split('*')[9].trim() ==  0?1: arregloCursoAlumno[i].split('*')[9].trim()  
                
                $('#divIngresoRegistros').css("display","block");
                if (sSeccion == sSeccionProfesor){
                    $('#idAlumno').text(nIdAlumno);
                    $('#nombreAlumno').text(sNombreCompleto);
                    $('.clsTardanzas').val(sTardanzas);
                    $('.clsJustificadas').val(sInasJustificadas);
                    $('.clsInjustificadas').val(sInasInjustificadas);
                    $('#cboParticipacion').val(nIdParticipacionPPFF);
                    if (sSugerencia != '')
                    {
                        $('.clsnotasSugerencia').val(nNota1);
                        $('#txtSugerencia').val(sSugerencia);
                      
                    }else
                    {
                        $('.clsnotasSugerencia').val('');
                        $('#txtSugerencia').val(''); 
                        $('.clsTardanzas').val('');
                        $('.clsJustificadas').val('');
                        $('.clsInjustificadas').val('');
                    }
                }else{
            }         
            }) 
        })
    });          
}

function fnEventoBotones(){
    
    $('#btnIngresar').click(function(){
        jConfirm('Desea guardar las notas ingresados?', 'Confirmación', function(r) { 
            if(r)
            {           
                $.ajax({
                    url: "ingresoNotasProfesorServletController",
                    data: {
                        action: "ingresarNotaSugerenciaxAlumnoxTutor",
                        IdPersona : $('#idAlumno').text().trim(),
                        IdBimestre : $('#cboBimestre').val(),
                        Nota : $('.clsnotasSugerencia').val(),
                        Sugerencia : $('#txtSugerencia').val(),
                        AsistTardanza:$('.clsTardanzas').val(),
                        AsistJustificada:$('.clsJustificadas').val(),
                        AsistInjustificada:$('.clsInjustificadas').val(),
                        Participacion: $('#cboParticipacion').val()                        
                    }                
                })
                
                .done(function (data, status, jqXhr) {
                    jAlert("Se ingreso Correctamente los registros", "Mensaje");   
                });    
            }//fnRefrescarNotas();
        })
    })
}

function fnObtenerParticipacion()
{
    $.ajax({
        url : "ingresoNotasProfesorServletController",
        data:{
            action:"obtenerParticipacionPPFF"
        }
    })
    .done(function(data){
        var arregloParticipacion = data.split("+");
        fnLimpiar();
        if(data == null){
            $('#cboParticipacion').append('<option>No tiene datos asociados</option>')
        }
        else{
            for(var i=0;i<arregloParticipacion.length - 1;i++)
            {
                var nIdParticipacion = arregloParticipacion[i].split("*")[0];
                var sDescripcion = arregloParticipacion[i].split("*")[1];
               
                $('#cboParticipacion').append('<option value='+ nIdParticipacion +'>' +sDescripcion+ '</option>')
            }
        }
        function  fnLimpiar(){
            $('#cboParticipacion option').remove();
        }       
    });
}

