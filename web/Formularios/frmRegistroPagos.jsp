<%-- 
    Document   : frmRegistroPagos
    Created on : 26/01/2016, 08:59:48 AM
    Author     : lflores
--%>
<%@page import="org.w3c.dom.Document"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>SISEDU</title>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <link href="${pageContext.request.contextPath}/css/default.css"  rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/menuDesplegable.css"  rel="stylesheet"/> 
        <link href="${pageContext.request.contextPath}/css/tablasConsultaAlumno.css"  rel="stylesheet"/> 
        <link href="${pageContext.request.contextPath}/css/registroPagos.css"  rel="stylesheet"/>

        <script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.ui.draggable.js" type="text/javascript"></script>

        <script src="${pageContext.request.contextPath}/js/jquery.alertsImpresion.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath}/css/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" />           

        <%-- Inicio de jquery para calendario --%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
        <script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/js/registroPagos.js"></script>     
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
                $( "#fecha1" ).datepicker({
                    changeMonth:true,
                    changeYear:true,
                    showOn: "button",
                    buttonImage: "${pageContext.request.contextPath}/images/ico.png",
                    buttonImageOnly: true,
                    showButtonPanel: true                    
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

        <script type="text/javascript">
            /*  $(document).ready(function(){
            $("#impresionPDF").click(function(){
                fnImprimir($('#txtReciboDetalle').val(),$('#lblIdAlumno').text());
            });
        });
            
        function fnImprimir(recibo,pago) {
             document.location.href='impresionPagoServletController?nIdAlumno='+ recibo +'&sNumeroRecibo=' + pago;
        }*/
        </script>
        <%-- Inicio de jquery para calendario--%>     
        <script src="${pageContext.request.contextPath}/js/cargarMenus2.js"></script>
        <%--<script src="${pageContext.request.contextPath}/js/cargarMenus2.js"></script>--%>
    </head>

    <body>
        <!-- start header -->
        <div id="header">
            <!--TRAER EL MENU DESDE JQUERY -->
        </div>
        <!--<div id="banner"></div>-->

        <!-- start page -->

       <div id="logo">
            <div id="logoTitulo">

                <table border="0">
                    <tbody>
                        <tr>
                            <td>
                                <h2><label style="text-transform: uppercase" class="bannertext">
                                        REGISTRO DE PAGOS
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

            <div id="capaImagen">
                <img id="imgIconAlumno" src="${pageContext.request.contextPath}/images/1454970620_contact-new.png" width="48" height="48"/>
            </div>
            <br>
            <div id ="divDatosPersonales">
                <fieldset id="marco" style="border:0px">
                    <div id="field">
                        <div class="recibopagos">
                            <table id="tblDatosPersonales" border="0">
                                <tr>
                                    <td>Nombre:</td><td><label id="lblNombre"></label></td>
                                    <td>DNI:</td><td><label id="lblDNI"></label></td>
                                </tr>
                                <tr>
                                    <td>Nivel:</td><td><label id="lblNivel"></label></td>
                                    <td>Grado:</td><td><label id="lblGrado"></label></td>
                                </tr>
                            </table>
                        </div>
                        <br>
                        <br>
                        <label id="lblIdAlumno" disabled="disabled"></label>
                        <div class="otrosrecibopagos">
                            <table id="tblRecibo" align ="right">
                                <tr>
                                    <td><label>Número de Recibo</label></td>
                                    <td><input type="text" name="Número Recibo" id="txtReciboDetalle" class="classRegRecibo" maxlength="7"></td>                                
                                </tr>
                            </table>
                        </div>
                        <div style="clear: both;">&nbsp;</div>
                        <div class="table-title">
                            <table  class="tablePagos">
                                <thead>
                                    <tr>
                                        <th class="text-left" style="display:none"></th>
                                        <th class="text-left"></th>                                    
                                        <th class="text-left">MES DE PAGO</th>
                                        <th class="text-left">DEUDA</th>
                                        <th class="text-left">MORA</th> 
                                        <th class="text-left">PAGO</th>                                     
                                    </tr>
                                </thead>
                                <tbody id="tblPagos">

                                </tbody>
                            </table>
                        </div> 
                        <div style="clear: both;">&nbsp;</div>
                        <div class="otrosrecibopagos" align = "right">                        
                            <button id="btnCancelar" type="submit" style="height: 30px;" tabindex="8" class="classRegPagoBoton" value="1">Cancelar
                                <img src="${pageContext.request.contextPath}/images/Cancel.png" alt=""/>
                            </button>
                            <a href="#" id="impresionPDF">
                                <button id="btnIngresarPago" type="submit" style="height: 30px;float: right" tabindex="8" class="classRegPagoBoton" value="1">Ingresar Pago
                                    <img src="${pageContext.request.contextPath}/images/OK.png" alt=""/>
                                </button> 
                            </a>
                        </div>  
                        <div style="clear: both;">&nbsp;</div>
                    </div>
                </fieldset>
            </div> 


            <!-- end content -->

        </div>
        <!-- end page -->
        <!-- start footer -->
        <div id="footer">
            <p id="legal">( c ) 2017. All Rights Reserved. IEP. LORD BRAIN </a> designed by <a href="http://www.apiservi.com/">APISERVI SAC</a>.</p>
        </div>
        <!-- end footer -->        

        <!-- start popup Consulta Alumno-->        

        <div id="buscarAlumno" title="Buscar Alumno">
            <form class="formulariodemo cf">
                <input type="text" id="txtNombres" value="" placeholder="Ingresar texto..." required style="height: 10px;"/>
                <button id="btnBuscar" type="submit" style="height: 30px;">Buscar</button>
            </form>
            <input type="radio" id ="rbFiltro" name="rbFiltro" value="1" ><label>DNI</label></input>
            <input type="radio" id ="rbFiltro" name="rbFiltro" value="2" checked="checked"><label>Apellidos y Nombres</label></input>  
            <br>
            <br>
            <div class="TITLE-CONSULTAALUMNO">
                <table  class="TABLE-CONSULTAALUMNO">
                    <thead>
                        <tr>
                            <th class="text-left" style="width: 60px; text-align: center">DNI</th>
                            <th class="text-left" style="width: 800px" colspan="3">ALUMNO</th>
                            <th class="text-left" style="width: 20%">TIPO</th>                   
                        </tr>
                    </thead>
                    <tbody id="tblAlumnos">

                    </tbody>
                </table>
            </div>       
        </div>

        <!-- end popup -->          
        <!--        <div id="frmIngresoPago" style="background: #1F0303">
                    <form id="frmHome" style="padding-bottom: 35px;padding-right: 6px;">
                        <ul>
                            <div align ="right">
        
                            </div>                                
                            <li id="fo1li1" class="notranslate      ">
                                <label class="desc " id="title6" for="Field6">
                                    Recibo
                                </label>
                                <div>
                                    <input id="txtRecibo" name="Recibo" type="text" class="classRegPago" tabindex="1" >
                                </div>
                            </li>
        
                            <li id="fo1li8" class="date notranslate      ">
                                <label>Mes</label><br/>
                                <input type="text" name="Mes" id="txtMes" class="classRegPago" tabindex="2" readonly="readonly"><br/>
                            </li>
        
                            <li id="fo1li7" class="notranslate       ">
                                <label class="desc" id="title7" for="Field7">
                                    Monto de pensión
                                </label>
                                <div>
                                    <input id="txtMontoPension" name="Monto de Pensión" type="text" class="classRegPago" tabindex="3">
                                </div>
                            </li>
        
                            <li id="fo1li8" class="notranslate       ">
                                <label class="desc" id="title7" for="Field7">
                                    Mora de pensión
                                </label>
                                <div>
                                    <input id="txtMoraPension" name="Mora de Pensión" type="text" class="classRegPago" tabindex="4">
                                </div>
                            </li>   
        
                            <li id="fo1li8" class="notranslate       ">
                                <label class="desc" id="title7" for="Field7">
                                    Total
                                </label>
                                <div>
                                    <input id="txtTotalPension" name="Total :" type="text" class="classRegPago" tabindex="5" readonly="readonly">
                                </div>
                                <label class="desc " id="title4" for="Field4">
                                Cancelado
                                </label> 
                                <input type="checkbox" class="classRegCanc" id ="ckbCancelado" name="CANCELADO" checked="checked"/>
                            </li>   
        
                            <li id="fo1li9" class="notranslate       ">
                                <label class="desc" id="title7" for="Field7">
                                    Observaciones:
                                </label>
                                <div>
                                    <textarea id ="txtObservaciones" class="classRegPago" name="Observaciones" rows="4" cols="20" tabindex="7">
                                    </textarea>  
                                </div>
                            </li>                       
                        </ul>
        
                        <div  align ="center">
                            <button id="btnIngresarPago" type="submit" style="height: 30px;" tabindex="8" class="classRegPagoBoton" value="1">Ingresar Pago</button>
                        </div>
                    </form>
                    <table id="tblBoleta" border="1" class="table_format">
                    </table>
                </div>-->
    </body>
</html>
