<%-- 
    Document   : frmRegistroPensiones
    Created on : 16/02/2016, 08:59:48 AM
    Author     : lflores
--%>
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
        <link href="${pageContext.request.contextPath}/css/tablaPensiones.css"  rel="stylesheet"/>  
        <link href="${pageContext.request.contextPath}/css/tablasConsultaPensionMes.css"  rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/clickDerecho.css"  rel="stylesheet"/>

        <script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.ui.draggable.js" type="text/javascript"></script>

        <script src="${pageContext.request.contextPath}/js/jquery.alerts.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath}/css/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" />        

        <%-- Inicio de jquery para calendario --%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
        <script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/js/registroPensiones.js"></script>     
        <script src="${pageContext.request.contextPath}/js/validate.js"></script>    


        <script  type = "text/javascript" >

            {
                if (history.forward(1)) {
                    location.replace(history.forward(1))

                }
            }
        </script>          
        <%-- Inicio de jquery para calendario--%>  
        <script src="${pageContext.request.contextPath}/js/cargarMenus2.js"></script>
        <script src="${pageContext.request.contextPath}/js/validate.js"></script>
    </head>
    <div id="header">
        <!--TRAER EL MENU DESDE JQUERY -->
    </div>
    <!-- end header -->    
    <div id="logo">
        <div id="logoTitulo">

            <table border="0">
                <tbody>
                    <tr>
                        <td>
                            <h2><label style="text-transform: uppercase" class="bannertext">
                                    REGISTRO DE PENSIONES
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

    <!--<div id="banner"></div>-->
    <!-- start page -->
    <div id="page">
        <!-- start content -->
        <div id="content" align="center">

            <fieldset>
                <legend>INGRESO DE PENSIONES</legend>
                <div style="clear: both;">&nbsp;</div>
                <section id="AñoAcademico">
                    <label>AÑO ACADEMICO :</label>
                    <select id="cboAñoAcademico">
                        <option value="1">2016</option>
                        <option value="2" selected>2017</option>
                    </select>
                </section>

                <div style="clear: both;">&nbsp;</div>
                <table border="0" cellspacing="2" cellpadding="5" id="tblNiveles" align="center">
                 </table>
                <div style="clear: both;">&nbsp;</div>
                <button class="pensionBtn" type="button" id="btnNivel" style="height: 30px;float: right" >INGRESAR
                    <img src="${pageContext.request.contextPath}/images/OK.png" alt=""/>
                </button>
                &nbsp;&nbsp;
                <button class="pensionBtn" type="button" id="btnNivelCancelar" style="height: 30px;float: right">CANCELAR
                    <img src="${pageContext.request.contextPath}/images/Cancel.png" alt=""/>
                </button>
                <div style="clear: both;">&nbsp;</div>
            </fieldset>  
        </div>
        <!-- end content -->
        <!-- start sidebar _right side--> 
        <!-- end sidebar -->

    </div>
    <!-- end page -->    


    <!-- start footer -->
    <div id="footer">
        <p id="legal">( c ) 2017. All Rights Reserved. IEP. LORD BRAIN </a> designed by <a href="http://www.apiservi.com/">APISERVI SAC</a>.</p>
    </div>
    <!-- end footer -->  

    <!-- inicio dialog  --> 
    <div id="pensionAlumno" title="Ingresar Pension Por Alumno">
        <form class="formulariodemo cf">
            <input type="text" id="txtNombres" placeholder="Ingresar texto..." style="height: 10px;" required />
            <button id="btnBuscarInicial" style="height: 30px;">Buscar</button>
            <button id="btnBuscarPrimaria"  style="height: 30px;">Buscar</button>
            <button id="btnBuscarSecundaria" style="height: 30px;">Buscar</button>
        </form>
        <div class="TITLE-CONSULTA-PENSIONES">
            <br/>
            <br/>
            <table cellspacing="2" cellpadding="5" class="TABLE-PENSIONPAGO">
                <thead>
                    <tr>
                        <th class="text-left" style="text-align: center">DNI</th>
                        <th class="text-left" style="text-align: center;width:240px;">ALUMNO</th>
                        <th class="text-left" style="text-align: center">PENSION</th>
                        <th class="text-left" style="text-align: center">INGRESAR</th>

                    </tr>
                </thead>
                <tbody id="tblAlumnoPension">

                </tbody>
            </table>
        </div>
        <div id="clickDerecho">
            <ul style="padding-left: 5px;">
                <li>Pensión</li>
            </ul>
        </div>
    </div>

    <div id="pensionGrado" title="Ingresar Pension Por Grado">
        <table cellspacing="2" cellpadding="5" id="tblGrado">
        </table>  
        <button type="button" id="btnGrado" style="height:24px;width:100px;">INGRESAR</button></td> 
</div> 

<div id="pensionGradoAlumno" title="Ingresar Pension Por Mes">
    <div class="TITLE-PENSIONPAGO">
        <table  class="TABLE-PENSIONPAGO">
            <thead>
                <tr>
                    <th class="text-left">MES DE PAGO</th>
                    <th class="text-left">PENSIÓN</th>
                    <th class="text-left">INGRESAR</th>
                </tr>
            </thead>
            <tbody id="tblPagosMes">

            </tbody>
        </table>
    </div> 


</div>
<!-- fin dialog -->    
</body>
</html>
