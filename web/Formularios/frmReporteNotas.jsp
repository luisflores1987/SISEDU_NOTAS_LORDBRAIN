<%-- 
    Document   : frmReporteNotas
    Created on : 25/04/2016, 07:13:03 AM
    Author     : MAQ
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
        <script src="${pageContext.request.contextPath}/js/reporteNotas.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tablasReporteNotas.css">
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
            <div id="content" style="width: 100%;">
                <div class="post">  
                    <fieldset>

                        <div style="clear: both;">&nbsp;</div>

                        <hgroup style="margin-left: 10px;">
                            <label>BIMESTRE :</label>
                            <select id="cboBimestre" name="bimestre">
                                <option value="0"></option>
                                <option value="1">I BIMESTRE</option>
                                <option value="2">II BIMESTRE</option>
                                <option value="3">III BIMESTRE</option>
                                <option value="4">IV BIMESTRE</option>
                            </select> 
                        </hgroup>
                        <div style="clear: both;">&nbsp;</div>
                    </fieldset>
                    <div style="clear: both;">&nbsp;</div>
                    <fieldset>
                        <div style="clear: both;">&nbsp;</div>
                        <hgroup style="margin-left: 10px;">
                            <input type="radio" name="filtroReporteNotas" value="1"/><label>IMPRESION DE REPORTE POR GRADO</label>
                            <br>
                            <br>
                            <input type="radio" name="filtroReporteNotas" value="2"/><label>IMPRESION DE REPORTE POR ALUMNO</label>
                        </hgroup>
                        <div style="clear: both;">&nbsp;</div>
                    </fieldset>
                    <div style="clear: both;">&nbsp;</div>
                    <fieldset id="fsReporteGrado">
                        <div style="clear: both;">&nbsp;</div>   

                        <hgroup id="hgReporteGrado" style="margin-left: 10px;">
                            <div style="clear: both;">&nbsp;</div>
                            <label class="desc " id="title4" for="Field4">
                                GRADO
                            </label>
                            <select id="cboGrado" name="GRADO" class="classRegAlumno" tabindex="9">
                                <option selected="selected">
                                    Seleccione
                                </option>
                            </select>
                            <span id="spNivel" style="display:none"></span>
                            <button id="btnBuscarReporteNotasGrado" type="submit" style="height: 30px;">Buscar</button>
                            <div style="clear: both;">&nbsp;</div>
                            <div class="TABLE-TITLEALUMNOREPORTE">
                                <table  class="TABLE-CONSULTAALUMNOREPORTE">
                                    <thead>
                                        <tr>
                                            <th class="text-left" style="width: 60px; text-align: center">DNI</th>
                                            <th class="text-left" style="width: 80%;">ALUMNO</th>
                                            <th class="text-left" style="width: 800px">NIVEL</th>
                                            <th class="text-left" style="width: 800px">GRADO</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tblAlumnosReporteNotasGrado">

                                    </tbody>
                                    <tfoot id="tblAlumnosReporteNotasGradoPie">
                                        
                                    </tfoot>
                                </table>
                            </div>
                        </hgroup>

                        <hgroup id="hgReporteAlumno" style="margin-left: 10px;">
                            <label>Alumno :</label>
                            <input type="text" id="txtPaciente" value="" placeholder="Ingresar texto..." required style="height: 20px;width: 250px"/>
                            <button id="btnBuscarReporteNotasAlumno" type="submit" style="height: 30px;">Buscar</button>
                            <br>
                            <div style="margin-left: 50px;">
                                <input type="radio" id ="rbFiltro" name="rbFiltro" value="1"/><label>DNI</label>
                                <input type="radio" id ="rbFiltro" name="rbFiltro" value="2" checked="checked"/><label>Apellidos y Nombres</label>                    
                            </div>
                            <br>
                            <div style="clear: both;">&nbsp;</div>
                            <div class="TABLE-TITLEALUMNOREPORTE">
                                <table  class="TABLE-CONSULTAALUMNOREPORTE">
                                    <thead>
                                        <tr>
                                            <th class="text-left" style="width: 60px; text-align: center">DNI</th>
                                            <th class="text-left" style="width: 80%;">ALUMNO</th>
                                            <th class="text-left" style="width: 800px">NIVEL</th>
                                            <th class="text-left" style="width: 800px">GRADO</th>
                                            <th class="text-left" style="width: 800px">REPORTE</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tblAlumnosReporteNotas">

                                    </tbody>
                                </table>
                            </div>
                        </hgroup>   

                        <div style="clear: both;">&nbsp;</div>
                    </fieldset>
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
        <!-- end footer -->      
    </body>
</html>

