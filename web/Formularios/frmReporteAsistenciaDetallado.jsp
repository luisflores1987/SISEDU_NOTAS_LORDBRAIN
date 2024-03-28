<%-- 
    Document   : frmReporteAsistenciaDetallado
    Created on : 04/04/2016, 12:25:10 PM
    Author     : MAQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <title>SISEDU</title>
        <meta name="keywords" content="" />
        <meta name="description" content="" />

        <link href="${pageContext.request.contextPath}/css/default.css"  rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/menuDesplegable.css"  rel="stylesheet"/>         
        <script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.ui.draggable.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.alerts.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath}/css/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" />           
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
        <script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/js/validate.js"></script>    
        <script src="${pageContext.request.contextPath}/js/cargarMenus2.js"></script>

        <!-- inicio Personalizados -->
        <script>
            
            $.datepicker.regional['es'] = {
                closeText: 'Cerrar',
                prevText: '<Ant',
                nextText: 'Sig>',
                currentText: 'Hoy',
                monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
                monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
                dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
                dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
                dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sá'],
                weekHeader: 'Sm',
                dateFormat: 'dd/mm/yy',
                firstDay: 1,
                isRTL: false,
                showMonthAfterYear: false,
                yearSuffix: ''
            };
            $.datepicker.setDefaults($.datepicker.regional['es']);
            $(function() {
                $( "#txtFechaConsultaINICIAL" ).datepicker({
                    changeMonth:true,
                    changeYear:true,
                    //showOn: "button",
                    //buttonImage: "${pageContext.request.contextPath}/images/ico.png",
                    buttonImageOnly: true,
                    showButtonPanel: true                    
                });
                $( "#txtFechaConsultaFIN" ).datepicker({
                    changeMonth:true,
                    changeYear:true,
                    //showOn: "button",
                    //buttonImage: "${pageContext.request.contextPath}/images/ico.png",
                    buttonImageOnly: true,
                    showButtonPanel: true                    
                });                
                
            });
        </script>                 

        <script src="${pageContext.request.contextPath}/js/obtenerReporteAsistencia.js"></script>
        <link href="${pageContext.request.contextPath}/css/tablasReporte.css"  rel="stylesheet"/> 

        <!-- fin Personalizados -->        
        <script  type = "text/javascript" >
        {
            if (history.forward(1)) {
                location.replace(history.forward(1))
            }
        }
        </script>    

    </head>

    <body>
        <div id="header">
            <!--TRAER EL MENU DESDE JQUERY -->
        </div>
        <!--end header -->
        <div id="logo">
            <div id="logoTitulo" align ="left">
                <!--Aqui va el nombre de la page..-->
            </div>
            <div id ="logoUsuario" align="right">
                <table class="tblusuario" border="0">
                    <tbody>
                        <tr>
                            <td>
                                <h2><label class="Usuario bannertext" id ="txtUsuario">
                                        ${sessionScope.sNombreUsuario}
                                    </label></h2>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h2><label class="tipoAcceso" id ="lblAcceso">
                                        ${sessionScope.ntipoAcceso}
                                    </label></h2>
                            </td>
                        </tr> 
                    </tbody>
                </table>

            </div>
        </div>

        <!-- start page -->
        <div id="page">
            <!-- start content -->
            <br>
            <br>
            <div id="content" style="width: 730px">
                <div class="post">
                    <form class="">
                        <table id="tblAsistencia" style="width: 734">
                            <tr>
                                <td><input type="checkbox" id ="ckFECHA" name="ckFecha" class="chkFiltro"><label>FECHA</label></td>
                                <td style="width: 567px;">
                                    <input type="text" id="txtFechaConsultaINICIAL" value="" placeholder="Fecha Inicial..." class="txtFiltro" style="height: 20px;width: 150px;"/>
                                    <input type="text" id="txtFechaConsultaFIN" value="" placeholder="Fecha final ..." class="txtFiltro" style="height: 20px;width: 150px;"/>
                                    <button id="btnBuscarAsistenciaProfesor" type="submit" style="height: 30px;width: 100px;margin-right: 2px;">Buscar</button>
                                    <button id="btnExportarBuscarAsistenciaProfesor" type="submit" style="height: 30px;width: 100px;margin-right: 2px;">Exportar Excel</button> 
                                </td> 
                            </tr>                        
                            <tr>
                                <td style="width: 140px;"><input type="checkbox" id ="chkProfesor" name="chkProfesor" class="chkFiltro"/><label>PROFESOR</label></td>
                                <td style="width: 150px;">
                                    <select id="cbProfesor" name="cbProfesor" class="txtFiltro" style="width: 309px;">
                                    </select>  

                                </td>                            
                            </tr>

                        </table>

                    </form>  
                </div>

            </div>

            <div id="tblTablaConsultaAsistencia" align="center" class="table-titleReporte">
                <div style="clear: both;">&nbsp;</div>
                <table  border=1 class="table-fillReporte">
                    <thead>
                        <tr>
                            <th  bgcolor="#FF0000" class="text-center" style="width: 80px;">DNI</th>
                            <th  bgcolor="#FF0000" class="text-center" style="width: 370px;">PROFESOR</th>
                            <th  bgcolor="#FF0000" class="text-center" style="width: 80px;">FECHA</th>
                            <th  bgcolor="#FF0000" class="text-center" style="width: 144px;">HORA ENTRADA</th>
                            <th  bgcolor="#FF0000" class="text-center" style="width: 144px;">HORA MARCADO</th>
                            <th  bgcolor="#FF0000" class="text-center" style="width: 144px;">MIN.</th>
                        </tr>
                    </thead>
                    <tbody id="tblProfesoresAsistencia">

                    </tbody>
                </table>
            </div>

            <div id="DivFooterRow" style="overflow:hidden">
            </div>


            <div id="divTablaReporte" class="table-titleReporte" style="margin-left: 0px;"></div>
            <!-- end content -->
            <!-- start sidebar _right side--> 

            <!-- end sidebar -->
            <div style="clear: both;">&nbsp;</div>
        </div>
        <!-- end page -->
        <!-- start footer -->
        <div id="footer">
            <p id="legal">( c ) 2017. All Rights Reserved. IEP. LORD BRAIN </a> designed by <a href="http://www.apiservi.com/">APISERVI SAC</a>.</p>
        </div>
        <!-- end footer -->      
    </body>
</html>
