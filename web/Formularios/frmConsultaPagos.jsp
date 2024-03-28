<%-- 
    Document   : frmConsultaPagos
    Created on : 23/02/2016, 10:08:35 AM
    Author     : MAQ
--%>

<%@page contentType="text/html"%>
<!DOCTYPE html>
<html>
    <head>
        <title>SISEDU</title>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <link href="${pageContext.request.contextPath}/css/default.css"  rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/menuDesplegable.css"  rel="stylesheet"/> 
        <link href="${pageContext.request.contextPath}/css/tablasConsulta.css"  rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/clickDerecho.css"  rel="stylesheet"/>

        <script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.ui.draggable.js" type="text/javascript"></script>

        <script src="${pageContext.request.contextPath}/js/jquery.alerts.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath}/css/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" />        

        <%-- Inicio de jquery para calendario --%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
        <script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/js/jspdf.min.js"></script> 
        <script src="${pageContext.request.contextPath}/js/registroPagos.js"></script> 
        <script src="${pageContext.request.contextPath}/js/validate.js"></script> 
        <%-- Inicio de jquery para calendario --%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">

        <script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/js/registroAlumnos.js"></script>  
        <script src="${pageContext.request.contextPath}/js/validate.js"></script>         

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
                    changeYear:true
                    //                   showOn: "button"
                    //                    buttonImage: "${pageContext.request.contextPath}/images/ico.png",
                    //                    buttonImageOnly: true,
                    //                    showButtonPanel: true                    
                });
                $( "#txtFechaConsultaFIN" ).datepicker({
                    changeMonth:true,
                    changeYear:true,
                    //                    showOn: "button",
                    //                    buttonImage: "${pageContext.request.contextPath}/images/ico.png",
                    //                    buttonImageOnly: true,
                    //                    showButtonPanel: true                    
                });                
                
            });
        </script>         

        <script  type = "text/javascript" >

            {
                if (history.forward(1)) {
                    location.replace(history.forward(1))

                }
            }
        </script>  
        <%-- Inicio de jquery para calendario--%>   
        <script src="${pageContext.request.contextPath}/js/cargarMenus2.js"></script>
    </head>
    <div id="header">
        <!--TRAER EL MENU DESDE JQUERY -->
    </div>
    <div id="logo">
        <div id="logoTitulo">

            <table border="0">
                <tbody>
                    <tr>
                        <td>
                            <h2><label style="text-transform: uppercase" class="bannertext">
                                    CONSULTA DE PAGOS
                                </label></h2>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h2><label>
                                </label></h2>
                        </td>
                    </tr> 
                </tbody>
            </table>                
        </div>
        <div id ="logoUsuario">
            <table class="tblusuario" border="0">
                <tbody>
                    <tr>
                        <td>
                            <h2>
                                <label class="Usuario bannertext" id ="txtUsuario">
                                    ${sessionScope.sNombreUsuario}
                                </label>
                            </h2>
                        </td>
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
    <div id="page">
        <!-- start content -->
        <div id="field">
            <div id="content">
                <form class="">
                    <table style="width: 100%">
                        <tr>
                            <td><input type="checkbox" id ="ckFECHA" name="ckFecha"><label>Fecha</label></td>
                            <td>
                                </input><input type="text" id="txtFechaConsultaINICIAL" value="" placeholder="Fecha Inicial..." required style="height: 20px;width:60%"/>
                                </input><input type="text" id="txtFechaConsultaFIN" value="" placeholder="Fecha final ..." required style="height: 20px;width:60%"/>
                            </td> 
                        </tr>                        
                        <tr>
                            <td><input type="checkbox" id ="ckDNI" name="ckDNI" ><label>DNI</label></td>
                            <td></input><input type="text" id="txtDni" value="" placeholder="Ingresar número..." required style="height: 20px;"/></td>                            
                        </tr>
                        <tr>
                            <td style="width:30%"><input type="checkbox" id ="ckAPNOM" name="ckAPNOM"><label>Alumnos</label></input></td>
                            <td><input type="text" id="txtApNom" value="" placeholder="Ingresar texto..." required style="height: 20px;"/></td>                            
                        </tr>
                        <tr>
                            <td><input type="checkbox" id ="ckNIVEL" name="ckNIVEL"><label>Nivel</label></input></td>
                            <td>
                                <select id="cboNivel" name="NIVEL" style="height: 20px;">
                                    <option>
                                        Seleccione
                                    </option>
                                </select>
                            </td>                          
                        </tr>
                        <tr>
                            <td><input type="checkbox" id ="ckRECIBO" name="ckDNI"><label>Nº Boleta</label></td>
                            <td></input><input type="text" id="txtNRecibo" value="" placeholder="Ingresar número..." required style="height: 20px;"/></td>                            
                        </tr>                        
                    </table>
                </form>  
            </div>
            <div id="botones">
                <button id="btnExportarBuscarPagosAlumno" type="submit" style="height: 30px;">Exportar Excel
                    <img title="Exportar Excel" src="${pageContext.request.contextPath}/images/icon_excel.png"/>
                </button> 
                <button id="btnBuscarPagosAlumno" type="submit" style="height: 30px;">Buscar
                    <img title="Buscar" src="${pageContext.request.contextPath}/images/icon_Search.png"/>
                </button>
            </div>
        </div>
        <div style="clear: both;">&nbsp;</div>
        <div id="tblTablaConsulta" class="table-titleCONSULTA" style="overflow:auto;" onscroll="OnScrollDiv(this)">
            <table class="datosTablaPension">
                <tr>
                    <td colspan="2" style="font-size: 16px;color:#2A0F0F;">
                        CONSULTA DE PAGOS
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <label id="spFechaActual"></label>
                    </td>
                </tr>
                <tr id ="idRegistro">                        
                </tr>
                <tr>
                    <td>
                        <span id= "spSumaAlumnosPagos"></span> 
                    </td>
                </tr>
            </table>
            <table  class="table-fillCONSULTA" align="center">
                <thead id="tblCabeceraPagos">                    
                </thead>
                <tbody id="tblAlumnosPagos">

                </tbody>
            </table>
        </div>
    </div>  
</div>
<!-- end content -->
<!-- start sidebar _right side--> 
<div id="sidebar" align="right">
    <!--Colocar imagen del colegio
    <img src="${pageContext.request.contextPath}/images/logoLordBrain_1.png" width="180" height="180"/>
    -->
</div>
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
