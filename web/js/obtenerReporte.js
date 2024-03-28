$(document).ready(function(){
    $("#btnVerReporteGrado").on("click", function(){
        fnObtenerReporte();    
    });
})

function fnObtenerReporte(){
    $.ajax({
        url: "obtenerReporteServletController",
        data:{
            action:"obtenerReporteGrado",
            anioAcademico: $('#cboAñoAcademico option:selected').text().trim()
        }        
    })
    
    .done(function (data, status, jqXhr){
        arregloAlumnos = data.split(":");
        console.log("filas  " + arregloAlumnos.length);
        limparselect();
        if (data=='') {
            $('#tblAlumnosDeudas').append('<tr><td colspan = "37"><label> No se encontraron datos ... </label></td></tr>'); 
        }
        else{
            
            $('#tblAlumnosDeudas').append('<tr><td bgcolor="#F2F2F2" class="text-left">grado</td>\n\\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Es</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Re</td>\n\
                                                <td bgcolor="#BCD1E1" class="text-center">Fa</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Es</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Re</td>\n\
                                                <td bgcolor="#BCD1E1" class="text-center">Fa</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Es</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Re</td>\n\
                                                <td bgcolor="#BCD1E1" class="text-center">Fa</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Es</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Re</td>\n\
                                                <td bgcolor="#BCD1E1" class="text-center">Fa</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Es</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Re</td>\n\
                                                <td bgcolor="#BCD1E1" class="text-center">Fa</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Es</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Re</td>\n\
                                                <td bgcolor="#BCD1E1" class="text-center">Fa</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Es</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Re</td>\n\
                                                <td bgcolor="#BCD1E1" class="text-center">Fa</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Es</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Re</td>\n\
                                                <td bgcolor="#BCD1E1" class="text-center">Fa</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Es</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Re</td>\n\
                                                <td bgcolor="#BCD1E1" class="text-center">Fa</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Es</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Re</td>\n\
                                                <td bgcolor="#BCD1E1" class="text-center">Fa</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Es</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Re</td>\n\
                                                <td bgcolor="#BCD1E1" class="text-center">Fa</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Es</td>\n\
                                                <td bgcolor="#F2F2F2" class="text-center">Re</td>\n\
                                                <td bgcolor="#BCD1E1" class="text-center">Fa</td>\n\
                                           </tr>'
                );                 
            for (var i = 0 ;i < arregloAlumnos.length - 1; i++){
                var grado =  arregloAlumnos[i].split("-")[0];  
                var vacaEsperada  =  arregloAlumnos[i].split("-")[1];            
                var vacaReal=  arregloAlumnos[i].split("-")[2];                
                var matriculaEsperada  =  arregloAlumnos[i].split("-")[3];            
                var matriculaReal=  arregloAlumnos[i].split("-")[4];            
                var marzoEsperada =  arregloAlumnos[i].split("-")[5];
                var marzoReal =  arregloAlumnos[i].split("-")[6];          
                var abrilEsperada =  arregloAlumnos[i].split("-")[7];          
                var abrilReal =  arregloAlumnos[i].split("-")[8];          
                var mayoEsperada =  arregloAlumnos[i].split("-")[9];                                          
                var mayoReal =  arregloAlumnos[i].split("-")[10];                                          
                var junioEsperada =  arregloAlumnos[i].split("-")[11];
                var junioReal =  arregloAlumnos[i].split("-")[12]; 
                var julioEsperada =  arregloAlumnos[i].split("-")[13]; 
                var julioReal =  arregloAlumnos[i].split("-")[14]; 
                var agostoEsperada =  arregloAlumnos[i].split("-")[15]; 
                var agostoReal =  arregloAlumnos[i].split("-")[16]; 
                var setiembreEsperada =  arregloAlumnos[i].split("-")[17];                 
                var setiembreReal =  arregloAlumnos[i].split("-")[18];
                var octubreEsperada =  arregloAlumnos[i].split("-")[19];
                var octubreReal =  arregloAlumnos[i].split("-")[20];
                var noviembreEsperada =  arregloAlumnos[i].split("-")[21];
                var noviembreReal =  arregloAlumnos[i].split("-")[22];
                var diciembreEsperada =  arregloAlumnos[i].split("-")[23];
                var diciembreReal =  arregloAlumnos[i].split("-")[24];                
                
                if (grado=="TOTAL")
                {
                    
                    $('#tblAlumnosDeudas').append('<tr>\n\
                                                    <td style="background:#3bafcd;" class="text-left">'+grado+'</td>\n\\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+vacaEsperada+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+vacaReal+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+parseFloat(vacaEsperada - vacaReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+matriculaEsperada+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+matriculaReal+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+parseFloat(matriculaEsperada - matriculaReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+marzoEsperada+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+marzoReal+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+parseFloat(marzoEsperada - marzoReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+abrilEsperada+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+abrilReal+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+parseFloat(abrilEsperada - abrilReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+mayoEsperada+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+mayoReal+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+parseFloat(mayoEsperada - mayoReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+junioEsperada+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+junioReal+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+parseFloat(junioEsperada - junioReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+julioEsperada+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-left">'+julioReal+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-left">'+parseFloat(julioEsperada - julioReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+agostoEsperada+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+agostoReal+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-left">'+parseFloat(agostoEsperada - agostoReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+setiembreEsperada+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+setiembreReal+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+parseFloat(setiembreEsperada - setiembreReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+octubreEsperada+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+octubreReal+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+parseFloat(octubreEsperada - octubreReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+noviembreEsperada+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+noviembreReal+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+parseFloat(noviembreEsperada - noviembreReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+diciembreEsperada+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+diciembreReal+'</td>\n\
                                                    <td style="background:#3bafcd;" class="text-center">'+parseFloat(diciembreEsperada - diciembreReal).toFixed(2)+'</td>\n\
                                             </tr>'
                        );                         
                      
                }
                else
                {
                    $('#tblAlumnosDeudas').append('<tr>\n\
                                                    <td style="background:#F2F2F2;" class="text-left">'+grado+'</td>\n\\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+vacaEsperada+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+vacaReal+'</td>\n\
                                                    <td style="background:#BCD1E1;" class="text-center">'+parseFloat(vacaEsperada - vacaReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+matriculaEsperada+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+matriculaReal+'</td>\n\
                                                    <td style="background:#BCD1E1;" class="text-center">'+parseFloat(matriculaEsperada - matriculaReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+marzoEsperada+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+marzoReal+'</td>\n\
                                                    <td style="background:#BCD1E1;" class="text-center">'+parseFloat(marzoEsperada - marzoReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+abrilEsperada+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+abrilReal+'</td>\n\
                                                    <td style="background:#BCD1E1;" class="text-center">'+parseFloat(abrilEsperada - abrilReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+mayoEsperada+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+mayoReal+'</td>\n\
                                                    <td style="background:#BCD1E1;" class="text-center">'+parseFloat(mayoEsperada - mayoReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+junioEsperada+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+junioReal+'</td>\n\
                                                    <td style="background:#BCD1E1;" class="text-center">'+parseFloat(junioEsperada - junioReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+julioEsperada+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-left">'+julioReal+'</td>\n\
                                                    <td style="background:#BCD1E1;" class="text-left">'+parseFloat(julioEsperada - julioReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+agostoEsperada+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+agostoReal+'</td>\n\
                                                    <td style="background:#BCD1E1;" class="text-left">'+parseFloat(agostoEsperada - agostoReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+setiembreEsperada+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+setiembreReal+'</td>\n\
                                                    <td style="background:#BCD1E1;" class="text-center">'+parseFloat(setiembreEsperada - setiembreReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+octubreEsperada+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+octubreReal+'</td>\n\
                                                    <td style="background:#BCD1E1;" class="text-center">'+parseFloat(octubreEsperada - octubreReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+noviembreEsperada+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+noviembreReal+'</td>\n\
                                                    <td style="background:#BCD1E1;" class="text-center">'+parseFloat(noviembreEsperada - noviembreReal).toFixed(2)+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+diciembreEsperada+'</td>\n\
                                                    <td style="background:#F2F2F2;" class="text-center">'+diciembreReal+'</td>\n\
                                                    <td style="background:#BCD1E1;" class="text-center">'+parseFloat(diciembreEsperada - diciembreReal).toFixed(2)+'</td>\n\
                                             </tr>'
                        );                 
                // if your table has header(th), use this
                //$('td:nth-child(2),th:nth-child(2)').hide(); 
                }
            }
        }
    });
    function limparselect(){
        $('#tblAlumnosDeudas tr').remove();       
    }
    
}

function OnScrollDiv(Scrollablediv) {
    document.getElementById('DivHeaderRow').scrollLeft = Scrollablediv.scrollLeft;
    document.getElementById('DivFooterRow').scrollLeft = Scrollablediv.scrollLeft;
}