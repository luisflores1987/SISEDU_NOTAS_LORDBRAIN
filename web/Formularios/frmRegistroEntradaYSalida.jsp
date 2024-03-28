<%-- 
    Document   : frmRegistroEntradaYSalida
    Created on : 17/03/2016, 02:50:54 AM
    Author     : lflores
--%>

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

        <script src="${pageContext.request.contextPath}/js/gestionAsistencia.js"></script>        
        <script src="${pageContext.request.contextPath}/js/moment.js"></script>        
        <script src="${pageContext.request.contextPath}/js/timer.jquery.js"></script>
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
            <center><label style="font-size: 25px;">SISEDU  ASISTENCIA</label></center>
            <div style="clear: both;">&nbsp;</div>
            <table width=100%>
                <tr>
                    <td><label>CÓDIGO:</label></td>
                    <td><input type="text" id="txtCodigo" class="txtFiltro" value="" name="CODIGO" maxlength="8" style="width: 100px"></td>
                    <td><button id="btnIngresarMarcado" type="submit" style="width: 110px;float: left;height: 30px;" class="classRegProfesorBoton">OK</button></td>
                    <td style="color: #fff;font-size: large;FONT-FAMILY: sans-serif;"><span id="lblHorasIngreso"></span> </td>
                    <td><button id="btnLimpiarMarcado" type="submit" style="width: 110px;float: left;height: 30px;" class="classRegProfesorBoton">Limpiar</button></td>
                </tr>
            </table>
            <HR width=100% align="center" bgColor="#897B55">  
            <div id="contenido">
                <!--<center><label style="font-size: 200px;background-color:#FFFFFF">HORA</label></center>-->
                <span id="liveclock">

                </span>
            </div>
            <HR width=100% align="center" bgColor="#897B55">  

            <div id="asistenciaProfesor" title="Asistencia Profesor" style="background: #1F0303">
                <div>
                    <table id="tblAsistenciaProfesor" style="width: 740px;background: #EEEEEE;">

                    </table>
                </div>
            </div>  
            <!-- end content -->
            <div style="clear: both;">&nbsp;</div>
        </div>
        <!-- end page -->
        <!-- start footer -->
        <div id="footer">
            <p id="legal">( c ) 2017. All Rights Reserved. IEP. LORD BRAIN </a> designed by <a href="http://www.apiservi.com/">APISERVI SAC</a>.</p>
        </div>


        <!-- start popup Consulta Alumno-->        

        <!-- end popup -->   
        <!-- end footer -->      
    </body>
</html>
