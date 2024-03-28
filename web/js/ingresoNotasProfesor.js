$(document).ready(function(){
    fnObtenerGrado();  
    fnInicializarPopup();
    fnIngresarNotas();
    fnInicializar(); 
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
                var sHabilitarI  =  arregloAlumnos[i].split("-")[2].split("|")[0];
                var sHabilitarF  =  arregloAlumnos[i].split("-")[2].split("|")[1];
                if (sHabilitarI == "0" || sHabilitarF == "0")
                {
                    $('#cboBimestre').append('<option value = '+idBimestre.trim()+'>'+ sDescripcion +'</option> ');
                }
                else
                {
                    $('#cboBimestre').append('<option value = '+idBimestre.trim()+' disabled>'+ sDescripcion +'</option> ');                                                    
                }
            }
        }
    });
    function limparselect(){
        $('#cboBimestre option').remove();
    }   
}

function fnObtenerGrado()
{
    $.ajax({
        url : "ingresoNotasProfesorServletController",
        data:{
            action:"obtenerGradoProfesor",
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
                
                $('#ulGradoProfesor').append('<li style="font-size: xx-large;"><label id=lbl' + nidGrado +'>'+ sDescripcion +'</label><input type="radio" name="grado" value="' + nidGrado + '" style="margin-left: 150px;width: 20px;height: 20px;" /></li>')
            }
            clickRadioButton();
        }
        function  fnLimpiar(){
            $('#ulGradoProfesor li').remove();
        }       
    });
}

function clickRadioButton(){
    
    $('input[type="radio"][name="grado"]').on("click", function(){
        
        if ($(this).is(':checked')){
            console.log($(this).val());
            console.log('Grado : ' + $('#lbl'+$(this).val()).text());
            fnObtenerCursos($(this).val(), $('#lblIdPersona').text().trim(),$('#lbl'+$(this).val()).text());    
        }
        else{
            console.log("Luis Flores ramos");  
        }
    })
}

function fnObtenerCursos(nidGrado, sIdPersonaText, sDescripcionGrado)
{
    
    $.ajax({
        url : "ingresoNotasProfesorServletController",
        data:{
            action:"obtenerCursosGradoProfesor",
            IdPersona: sIdPersonaText,
            IdGrado: nidGrado
        }
    })
    .done(function(data){
        var arregloCursoGrado = data.split("+");
        fnLimpiar();
        if(data == null){
            $('#ulGradoCursoProfesor').append('<li>No tiene grados asociados..</li>')
        }
        else{
            for(var i=0;i<arregloCursoGrado.length - 1;i++)
            {
                var nidArea = arregloCursoGrado[i].split("*")[0];
                var sDescripcionArea = arregloCursoGrado[i].split("*")[1];
                var nidCurso = arregloCursoGrado[i].split("*")[2];
                var sDescripcionCurso = arregloCursoGrado[i].split("*")[3];
                var nIdProfesor = arregloCursoGrado[i].split("*")[4];
                var nombreCurso = sDescripcionArea + ' / ' + sDescripcionCurso
                $('#ulGradoCursoProfesor').append('<li name="gradoCurso" value="' + nidCurso + '"><p style="display:none;">'+nIdProfesor+'</p><span style="display:none;">'+sDescripcionGrado+'</span><label style="font-size: larger;">'+ nombreCurso +'</label></li>')
            }
            $('#spGrado').text(nidGrado);
            fnObtenerAlumnosCurso();

        }

        function  fnLimpiar(){
            $('#ulGradoCursoProfesor li').remove();
        }         
    });
}

function fnInicializarPopup()
{
    dlgConsularProfesor = $("#mostrarAlumnos").dialog({
        autoOpen: false,
        dialogClass: 'myTitleClass',        
        modal: true,
        width: 900,
        height: 600
    }); 
}

function resultadosClickVerDocumentos() {
    fnInicializar();
    dlgConsularProfesor.dialog("open");
    fnObtenerIngresoNotas();
}

function fnObtenerAlumnosCurso()
{
    var cad = "";
    $("#ulGradoCursoProfesor li").each(function(i){ 
        $(this).click(function(){
            $('#spCurso').text($("#ulGradoCursoProfesor li > label").eq(i).text().split("/")[1].trim());
            $('#spGradoNota').text($("#ulGradoCursoProfesor li > span").eq(i).text().trim())
            $('#spIdProfesor').text($("#ulGradoCursoProfesor li > p").eq(i).text().trim())
            $('#spIdCurso').text($(this).val());
            resultadosClickVerDocumentos();
        });        
    });    
}

function fnIngresarNotas()
{
    $("#btnIngresar").on("click", function(){
        jConfirm('Desea guardar las notas ingresados?', 'Confirmación', function(r) { 
            if(r)
            {    
                var cadNotas = '';
                $("#tblAlumnoNotas tr").each(function(i){
                    
                    if ($('#tblAlumnoNotas tr td:nth-child(4)').eq(i).text().trim() == '1')
                    {
                        $('.clsnotas' + $('#tblAlumnoNotas tr td:nth-child(1)').eq(i).text().trim()).each(function(){                    
                            cadNotas = cadNotas + $(this).val() + ','
                        }) 
                
                        cadNotas = cadNotas.substr(0, cadNotas.length - 1) 
            
                        $('.clsnotas2').each(function(){                    
                            cadNotas = cadNotas + $(this).val() + ','
                        })                    
                        
                
                        $.ajax({
                            url:"ingresoNotasProfesorServletController",
                            data:{
                                action      :"registroAlumnoxCursoxGradoxProfesor",
                                idAlumno    :$('#tblAlumnoNotas tr td:nth-child(1)').eq(i).text().trim(),
                                idCurso     :$('#spIdCurso').text(),
                                idBimestre  :$('#cboBimestre').val(),
                                CadenaNotas : cadNotas
                            }
                        })
                        .done(function(data){
                            console.log(data);
                            jAlert("Se ingreso Correctamente los registros", "Mensaje");   
                        });                                
                        cadNotas = ''; 
                    }
                    else
                    {                           
                        console.log('No ingreso... ')
                    }
                })
            }
            fnRefrescarNotas();
        })
    })
}

function fnRefrescarNotas(){
    
   $('#cboBimestre').change(function(){
        console.log("Ingreso al textbox... !!!");
        $('#divNotas').css('display', 'block');
 
        $.ajax({
            url: "ingresoNotasProfesorServletController",
            data: {
                action: "obtenerAlumnoxCursoxGradoxProfesor",
                IdCurso : $('#spIdCurso').text(),
                IdGrado : $('#spGrado').text(),
                IdBimestre : $(this).val(),
                IdProfesor : $('#spIdProfesor').text()
            }                
        })
        .done(function (data, status, jqXhr) {    
            var arregloCursoAlumno = data.split("+"); 
            var contador = 0;
            fnLimpiar();
            if (data == null){
                $('#tblAlumnoNotas').append('<tr><td colspan = "3">No se encuentran datos ...</td></tr>');
            }else{
                for(var i = 0;i<arregloCursoAlumno.length -1 ;i++)
                {
                    var nIdAlumno = arregloCursoAlumno[i].split("*")[0]
                    var sNombreCompleto = arregloCursoAlumno[i].split("*")[1]
                    var nNota1 = arregloCursoAlumno[i].split("*")[2].split("|")[0]
                    var nIngreso1 = (arregloCursoAlumno[i].split("*")[2].split("|")[1] == 1)?"disabled":"" 
                    var nNota2 = arregloCursoAlumno[i].split('*')[3].split("|")[0]
                    var nIngreso2 = (arregloCursoAlumno[i].split("*")[3].split("|")[1] == 1)?"disabled":"" 
                    var sSeccion = arregloCursoAlumno[i].split('*')[4].trim()
                    var sSeccionProfesor   = arregloCursoAlumno[i].split('*')[5].trim()
                    var nTipoAcceso = arregloCursoAlumno[i].split('*')[6].trim()
                    var nBloquear1 = (nIngreso1 == "disabled")?"checked":""
                    var nBloquear2 = (nIngreso2 == "disabled")?"checked":""
 
                        
                    if(nTipoAcceso == '8')
                    { 
                        if (sSeccionProfesor == 'C'){
                            contador = contador +1 ;  
                            $('#tblAlumnoNotas').append('<tr>\n\
                                                        <td><label class="clsIdAlumno">'+ nIdAlumno +'</label></td>\n\
                                                        <td><label>'+ $('#spIdCurso').text() +'</label></td>\n\
                                                        <td><label>'+ $('#cboBimestre').val() +'</label></td>\n\
                                                        <td><label>1</label></td>\n\
                                                        <td style="text-align:center"><label>'+ contador +'</label></td>\n\
                                                        <td style="width: 80%;"><label>'+ sNombreCompleto +'</label></td>\n\
                                                        <td><input style="width: 95%;" class = "clsnotas'+ nIdAlumno +'" type="text" value= "'+ nNota1 +'" maxlength = 5/></td>\n\
                                                        <td><input style="width: 95%;" class = "clsnotas'+ nIdAlumno +'" type="text" value= "'+ nNota2 +'" maxlength = 5/></td>\n\
                                                        <td><input style="width: 95%;" id = "txtNota1'+ nIdAlumno +'" type="checkbox" ' + nBloquear1 + ' /></td>\n\
                                                        <td><input style="width: 95%;" class = "clsBloquear2'+ nIdAlumno +'" type="checkbox" ' + nBloquear2 + ' /></td>\n\
                                                        </tr>');                            

                        }
                                      
                    }else
                    {
                        $('#tblTituloAlumnoNotas tr th:nth-child(5)').hide();              
                        $('#tblTituloAlumnoNotas tr th:nth-child(6)').hide(); 
                        if (sSeccion == sSeccionProfesor){
                            contador = contador +1 ;
                            $('#tblAlumnoNotas').append('<tr>\n\
                                                        <td><label class="clsIdAlumno">'+ nIdAlumno +'</label></td>\n\
                                                        <td><label>'+ $('#spIdCurso').text() +'</label></td>\n\
                                                        <td><label>'+ $('#cboBimestre').val() +'</label></td>\n\
                                                        <td><label>1</label></td>\n\
                                                        <td style="text-align:center"><label>'+ contador +'</label></td>\n\
                                                        <td style="width: 80%;"><label>'+ sNombreCompleto +'</label></td>\n\
                                                        <td><input style="width: 95%;" class = "clsnotas'+ nIdAlumno +'" type="text" value= "'+ nNota1 +'" ' + nIngreso1 + ' maxlength = 5/></td>\n\
                                                        <td><input style="width: 95%;" class = "clsnotas'+ nIdAlumno +'" type="text" value= "'+ nNota2 +'" ' + nIngreso2 + ' maxlength = 5/></td>\n\
                                                        </tr>');                                  
                        }
                    }                    
                    
                    $('.clsnotas'+ nIdAlumno).each(function() {
                        $(this).keydown(function (e) {
                            if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 || (e.keyCode == 65 && e.ctrlKey === true) || (e.keyCode >= 35 && e.keyCode <= 39)) {
                                return;
                            } 
                            if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                                e.preventDefault();
                            }
                        })
                    });                            
                }
                
                $('#tblAlumnoNotas tr td:nth-child(9) input:checkbox').each(function(i){
                    $(this).change(function(){
                        fnCrearEventoHabilitar($('#tblAlumnoNotas tr td:nth-child(1)').eq(i).text(),$('#tblAlumnoNotas tr td:nth-child(2)').eq(i).text(),$('#cboBimestre').val(),$(this).is(":checked"));
                    });
                });
                
                $('#tblAlumnoNotas tr td:nth-child(1)').hide();
                $('#tblAlumnoNotas tr td:nth-child(2)').hide();
                $('#tblAlumnoNotas tr td:nth-child(3)').hide();
                $('#tblAlumnoNotas tr td:nth-child(4)').hide();
                             

            }


            function  fnLimpiar(){
                $('#tblAlumnoNotas tr').remove();
            }
        }); 
    });   
    
    function fnCrearEventoHabilitar(nIdAlumno, nIdCurso, nIdBimestre, bHabilitar){
        console.log("nIdAlumno : " + nIdAlumno);   
        console.log("nIdCurso : " + nIdCurso);   
        console.log("nIdBimestre : " + nIdBimestre);   
        console.log("bHabilitar : " + bHabilitar);                   
        $.ajax({
            url: "habilitarNotaServletController",
            data: {
                action: "habilitarNotasDetalle",
                idAlumno: nIdAlumno,
                idCurso: nIdCurso,
                idBimestre:nIdBimestre,
                Habilitar:bHabilitar,
                TipoNota:1                                                
            }                
        })
        .done(function (data, status, jqXhr) {
            console.log("data :" + data);
            var sEstado = ($.trim(data) == '1')?"Bloqueado":"Desbloqueado"                                                
            jAlert("Se han " + sEstado + " los registros satisfactoriamente", "Mensaje"); 
            fnRefrescarNotas();
                                                
        });        
    }
//    $('#tblAlumnoNotas tr td:nth-child(6) input').each(function(){
//        console.log("El click es :  " + $(this).val());
//    //        $(this).keydown(function (e) {
//    //            console.log("Solo numeros");
//    //            if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 || (e.keyCode == 65 && e.ctrlKey === true) || (e.keyCode >= 35 && e.keyCode <= 39)) {
//    //                return;
//    //            } 
//    //            if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
//    //                e.preventDefault();
//    //            }
//    //        })
//    }) 

}

function fnInicializar(){
 
    $('#cboBimestre option[value=0]').prop('selected', true);
    $('#tblAlumnoNotas tr').remove();
    $('#divNotas').css('display', 'none');
    fnCancelar();
}

function fnCancelar()
{
    $('#btnCancelar').click(function(){
        fnInicializar();      
    })
}

function fnObtenerIngresoNotas(){
    $('#cboBimestre').change(function(){
        console.log("Ingreso al textbox... !!!");
        $('#divNotas').css('display', 'block');
 
        $.ajax({
            url: "ingresoNotasProfesorServletController",
            data: {
                action: "obtenerAlumnoxCursoxGradoxProfesor",
                IdCurso : $('#spIdCurso').text(),
                IdGrado : $('#spGrado').text(),
                IdBimestre : $(this).val(),
                IdProfesor : $('#spIdProfesor').text()
            }                
        })
        .done(function (data, status, jqXhr) {    
            var arregloCursoAlumno = data.split("+"); 
            var contador = 0;
            fnLimpiar();
            if (data == null){
                $('#tblAlumnoNotas').append('<tr><td colspan = "3">No se encuentran datos ...</td></tr>');
            }else{
                for(var i = 0;i<arregloCursoAlumno.length -1 ;i++)
                {
                    var nIdAlumno = arregloCursoAlumno[i].split("*")[0]
                    var sNombreCompleto = arregloCursoAlumno[i].split("*")[1]
                    var nNota1 = arregloCursoAlumno[i].split("*")[2].split("|")[0]
                    var nIngreso1 = (arregloCursoAlumno[i].split("*")[2].split("|")[1] == 1)?"disabled":"" 
                    var nNota2 = arregloCursoAlumno[i].split('*')[3].split("|")[0]
                    var nIngreso2 = (arregloCursoAlumno[i].split("*")[3].split("|")[1] == 1)?"disabled":"" 
                    var sSeccion = arregloCursoAlumno[i].split('*')[4].trim()
                    var sSeccionProfesor   = arregloCursoAlumno[i].split('*')[5].trim()
                    var nTipoAcceso = arregloCursoAlumno[i].split('*')[6].trim()
                    var nBloquear1 = (nIngreso1 == "disabled")?"checked":""
                    var nBloquear2 = (nIngreso2 == "disabled")?"checked":""
 
                        
                    if(nTipoAcceso == '8')
                    { 
                        if (sSeccionProfesor == 'C'){
                            contador = contador +1 ;  
                            $('#tblAlumnoNotas').append('<tr>\n\
                                                        <td><label class="clsIdAlumno">'+ nIdAlumno +'</label></td>\n\
                                                        <td><label>'+ $('#spIdCurso').text() +'</label></td>\n\
                                                        <td><label>'+ $('#cboBimestre').val() +'</label></td>\n\
                                                        <td><label>1</label></td>\n\
                                                        <td style="text-align:center"><label>'+ contador +'</label></td>\n\
                                                        <td style="width: 80%;"><label>'+ sNombreCompleto +'</label></td>\n\
                                                        <td><input style="width: 95%;" class = "clsnotas'+ nIdAlumno +'" type="text" value= "'+ nNota1 +'" maxlength = 5/></td>\n\
                                                        <td><input style="width: 95%;" class = "clsnotas'+ nIdAlumno +'" type="text" value= "'+ nNota2 +'" maxlength = 5/></td>\n\
                                                        <td><input style="width: 95%;" id = "txtNota1'+ nIdAlumno +'" type="checkbox" ' + nBloquear1 + ' /></td>\n\
                                                        <td><input style="width: 95%;" class = "clsBloquear2'+ nIdAlumno +'" type="checkbox" ' + nBloquear2 + ' /></td>\n\
                                                        </tr>');                            

                        }
                                      
                    }else
                    {
                        $('#tblTituloAlumnoNotas tr th:nth-child(5)').hide();              
                        $('#tblTituloAlumnoNotas tr th:nth-child(6)').hide(); 
                        if (sSeccion == sSeccionProfesor){
                            contador = contador +1 ;
                            $('#tblAlumnoNotas').append('<tr>\n\
                                                        <td><label class="clsIdAlumno">'+ nIdAlumno +'</label></td>\n\
                                                        <td><label>'+ $('#spIdCurso').text() +'</label></td>\n\
                                                        <td><label>'+ $('#cboBimestre').val() +'</label></td>\n\
                                                        <td><label>1</label></td>\n\
                                                        <td style="text-align:center"><label>'+ contador +'</label></td>\n\
                                                        <td style="width: 80%;"><label>'+ sNombreCompleto +'</label></td>\n\
                                                        <td><input style="width: 95%;" class = "clsnotas'+ nIdAlumno +'" type="text" value= "'+ nNota1 +'" ' + nIngreso1 + ' maxlength = 5/></td>\n\
                                                        <td><input style="width: 95%;" class = "clsnotas'+ nIdAlumno +'" type="text" value= "'+ nNota2 +'" ' + nIngreso2 + ' maxlength = 5/></td>\n\
                                                        </tr>');                                  
                        }
                    }                    
                    
                    $('.clsnotas'+ nIdAlumno).each(function() {
                        $(this).keydown(function (e) {
                            if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 || (e.keyCode == 65 && e.ctrlKey === true) || (e.keyCode >= 35 && e.keyCode <= 39)) {
                                return;
                            } 
                            if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                                e.preventDefault();
                            }
                        })
                    });                            
                }
                
                $('#tblAlumnoNotas tr td:nth-child(9) input:checkbox').each(function(i){
                    $(this).change(function(){
                        fnCrearEventoHabilitar($('#tblAlumnoNotas tr td:nth-child(1)').eq(i).text(),$('#tblAlumnoNotas tr td:nth-child(2)').eq(i).text(),$('#cboBimestre').val(),$(this).is(":checked"));
                    });
                });
                
                $('#tblAlumnoNotas tr td:nth-child(1)').hide();
                $('#tblAlumnoNotas tr td:nth-child(2)').hide();
                $('#tblAlumnoNotas tr td:nth-child(3)').hide();
                $('#tblAlumnoNotas tr td:nth-child(4)').hide();
                             

            }


            function  fnLimpiar(){
                $('#tblAlumnoNotas tr').remove();
            }
        }); 
    });   
    
    function fnCrearEventoHabilitar(nIdAlumno, nIdCurso, nIdBimestre, bHabilitar){
        console.log("nIdAlumno : " + nIdAlumno);   
        console.log("nIdCurso : " + nIdCurso);   
        console.log("nIdBimestre : " + nIdBimestre);   
        console.log("bHabilitar : " + bHabilitar);                   
        $.ajax({
            url: "habilitarNotaServletController",
            data: {
                action: "habilitarNotasDetalle",
                idAlumno: nIdAlumno,
                idCurso: nIdCurso,
                idBimestre:nIdBimestre,
                Habilitar:bHabilitar,
                TipoNota:1                                                
            }                
        })
        .done(function (data, status, jqXhr) {
            console.log("data :" + data);
            var sEstado = ($.trim(data) == '1')?"Bloqueado":"Desbloqueado"                                                
            jAlert("Se han " + sEstado + " los registros satisfactoriamente", "Mensaje"); 
            fnRefrescarNotas();
                                                
        });        
    }
//    $('#tblAlumnoNotas tr td:nth-child(6) input').each(function(){
//        console.log("El click es :  " + $(this).val());
//    //        $(this).keydown(function (e) {
//    //            console.log("Solo numeros");
//    //            if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 || (e.keyCode == 65 && e.ctrlKey === true) || (e.keyCode >= 35 && e.keyCode <= 39)) {
//    //                return;
//    //            } 
//    //            if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
//    //                e.preventDefault();
//    //            }
//    //        })
//    })
    
}