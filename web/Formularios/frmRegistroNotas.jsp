<%-- 
    Document   : frmRegistroNotas
    Created on : 12/04/2016, 01:59:07 PM
    Author     : MAQ
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <script src="${pageContext.request.contextPath}/js/ingresoNotasProfesor.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tablasRegistroNotas.css">
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
                        <tr>
                            <td>
                                <label class="tipoAcceso" id ="lblIdPersona">
                                    ${sessionScope.nIdUsuario}
                                </label>
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
            <div id="content" style="width: 374px;">
                <div class="post">
                    <fieldset style="width: 350px;">
                        <legend>Grado</legend>
                        <ul id ="ulGradoProfesor">
                        </ul>
                    </fieldset>

                </div>
            </div>
            <!-- end content -->
            <!-- start sidebar _right side--> 
            <div id="sidebar" style="margin-right: 144px;">                <!--Colocar imagen del colegio-->

                <fieldset style="width: 350px;">
                    <span id = "spGrado" style="display:none"></span>

                    <ul type= disk id ="ulGradoCursoProfesor"></ul>
                </fieldset>
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
        <!-- start popup-->        
        <div id="mostrarAlumnos" title="LISTADO DE ALUMNOS" style="background: #1F0303">
            <fieldset style="background: #FFFFFF;">
                <span id="spIdProfesor" style="display:none"></span>
                <label style="color: #000;font-weight: bold;">GRADO:&nbsp;&nbsp;&nbsp;</label><span id="spGradoNota" style="color: #000;text-transform: uppercase;"></span>
                <br>
                <span id="spIdCurso" style="display:none"></span>
                <label style="color: #000;font-weight: bold;">CURSO:&nbsp;&nbsp;&nbsp;</label><span id="spCurso" style="color: #000;"></span>
                <span id="spIdAlumno" style="display:none"></span>
            </fieldset>     
            <br>
            <br>
            <div class="TITLE-CONSULTAALUMNO">
                <div style="float:left;margin-bottom: 10px;margin-right: 10px;margin-top: 10px;margin-left: 10px;">
                    <table>
                        <tr><td><label>SELECCIONE EL BIMESTRE:</label></td></tr>
                        <tr><td><div style="clear: both;">&nbsp;</div></td></tr>
                        <tr>
                            <td>
                                <select  style="width: 100%" id="cboBimestre" name="bimestre">
                                    <!--                                    <option value="0"></option>
                                    
                                                                        <option value="1">I BIMESTRE</option>
                                                                        <option value="2">II BIMESTRE</option>
                                                                        <option value="3">III BIMESTRE</option>
                                                                        <option value="4">IV BIMESTRE</option>-->

                                </select>
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="divNotas">
                    <fieldset>
                        <div style="float:right;margin-bottom: 10px;margin-right: 10px;margin-top: 10px;margin-left: 10px;">
                            <table>
                                <tr>
                                    <td colspan="5"></td>
                                    <td colspan="2">
                                        <button id="btnCancelar" type="submit" style="height: 30px;" class="classNotaAlumnoBoton">Cancelar</button>
                                    </td>
                                    <td colspan="2">
                                        <button id="btnIngresar" type="submit" style="height: 30px;" class="classNotaAlumnoBoton">Ingresar</button>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div style="margin-bottom: 10px;margin-left: 10px;margin-right: 10px;margin-top: 50px;">
                            <hgroup>
                                <table  class="TABLE-CONSULTAALUMNONOTAS" style="width: 100%;">
                                    <thead id="tblTituloAlumnoNotas">
                                        <tr>
                                            <th style="width: 10px;"> 
                                                <label style="color: #E2DEDE;font-weight: bold;">Nº</label>
                                            </th>
                                            <th style="width: 113px;"> 
                                                <label style="color: #E2DEDE;font-weight: bold;">ALUMNO</label>
                                            </th>
                                            <th>
                                                <label style="color: #E2DEDE;font-weight: bold;">NOTA 1</label>
                                            </th>
                                            <th>
                                                <label style="color: #E2DEDE;font-weight: bold;">NOTA 2</label>
                                            </th>                                    
                                            <th>
                                                <label style="color: #E2DEDE;font-weight: bold;">N1</label>
                                            </th>  
                                            <th>
                                                <label style="color: #E2DEDE;font-weight: bold;">N2</label>
                                            </th>  
                                        </tr>
                                    </thead>                          
                                    <tbody id="tblAlumnoNotas">
                                    </tbody>
                                </table>
                            </hgroup>
                        </div>
                    </fieldset>
                </div>
            </div>
        </div>  
        <!-- end popup -->   

    </body>
</html>