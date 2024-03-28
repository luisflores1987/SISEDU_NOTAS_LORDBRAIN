<%-- 
    Document   : frmRegistroPensiones
    Created on : 02/03/2016, 08:59:48 AM
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
        <link href="${pageContext.request.contextPath}/css/tablas.css"  rel="stylesheet"/> 
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
    </head>
    <div id="header">
        <!--TRAER EL MENU DESDE JQUERY -->
    </div>
    <!-- end header -->    
    <div id="logo">
        <div id="logoTitulo" align ="left">
            <label>COPALORD</label>
        </div>
        <div id ="logoUsuario" align="right">
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
                    </tr>
                </tbody>
            </table>

        </div>
    </div>

    <!--<div id="banner"></div>-->
    <!-- start page -->
    <div id="page">
        <!-- start content -->
        <div id="content">
            <div class="post">
                <fieldset>
                    <legend>INGRESO DE PENSIONES</legend>
                    <p>
                    <table border="0" cellspacing="2" cellpadding="5" id="tblNiveles">
                        <!--<tr><td><label>INICIAL</label></td><td><input type="text" id="txtIncial" value="" style="text-align: center"/></td><td><button type="button" id="btnIncial" style="height:24px;width:60px;">DETALLE</button></td></tr>
                        <tr id="trIncial"><td colspan="3"></td></tr>
                        <tr>
                            <td><label>PRIMARIA</label></td>
                            <td><input type="text" id="txtPrimaria" value="" style="text-align: center"/></td>
                            <td><button type="button" id="btnPrimaria" style="height:24px;width:60px;">DETALLE</button></td>                                 
                        </tr>
                        <tr>
                            <td><label>SECUNDARIA</label></td>
                            <td><input type="text" id="txtSecundaria" value="" style="text-align: center"/></td>
                            <td><button type="button" id="btnSecundaria" style="height:24px;width:60px;">DETALLE</button></td>                                 
                        </tr>-->
                    </table>
                    </p>
                    <button type="button" id="btnNivel" style="height:24px;width:60px;">INGRESAR</button>
                </fieldset>  
            </div>
        </div>
        <!-- end content -->
        <!-- start sidebar _right side--> 
        <div id="sidebar" align="right">
            <!--Colocar imagen del colegio-->
            <img src="${pageContext.request.contextPath}/images/logoLordBrain_1.png" width="180" height="180"/>
        </div>
        <!-- end sidebar -->
        <div style="clear: both;">&nbsp;</div>
    </div>
    <!-- end page -->    


    <!-- start footer -->
    <div id="footer">
        <p id="legal">( c ) 2016. All Rights Reserved. <a href="http://www.lordbrain.edu.pe/">Lord Brain</a> designed by <a href="http://www.apiservi.com/">APISERVI SAC</a>.</p>
    </div>
    <!-- end footer -->  

    <!-- inicio dialog  --> 
    <div id="pensionAlumno" title="Ingresar Pension Por Alumno" style="background: #1F0303">
        <form class="formulariodemo cf">
            <input type="text" id="txtNombres" value="" placeholder="Ingresar texto..." required style="height: 10px;"/>
            <button id="btnBuscar" type="submit" style="height: 30px;">Buscar</button>
        </form>
        <div class="table-title">
            <table cellspacing="2" cellpadding="5" class="table-fill">
                <thead>
                    <tr>
                        <th class="text-left" style="text-align: center">DNI</th>
                        <th class="text-left" style="text-align: center" colspan="3">ALUMNO</th>
                        <th class="text-left" style="text-align: center">PENSION</th>                        
                    </tr>
                </thead>
                <tbody id="tblAlumnoPension">

                </tbody>
            </table>
        </div>         
    </div>

    <div id="pensionGrado" title="Ingresar Pension Por Grado" style="background: #1F0303">
        <table cellspacing="2" cellpadding="5" id="tblGrado">
        </table>  
        <button type="button" id="btnGrado" style="height:24px;width:60px;">INGRESAR</button></td> 
</div> 

<div id="pensionGradoAlumno" title="Ingresar Pension Por Grado" style="background: #1F0303>
     <div class="table-title">
     <table  class="table-fill">
        <thead>
            <tr>
                <th class="text-left">MES DE PAGO</th>
                <th class="text-left">PENSIÓN</th>
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
