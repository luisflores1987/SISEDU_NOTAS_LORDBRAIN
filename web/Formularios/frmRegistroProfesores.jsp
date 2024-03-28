<%-- 
    Document   : frmRegistroProfesores
    Created on : 16/03/2016, 10:24:54 AM
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
        <link href="${pageContext.request.contextPath}/css/tablasConsultaAlumno.css"  rel="stylesheet"/>        
        <link href="${pageContext.request.contextPath}/css/menuDesplegable.css"  rel="stylesheet"/>         
        <script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.ui.draggable.js" type="text/javascript"></script>

        <script src="${pageContext.request.contextPath}/js/jquery.alerts.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath}/css/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" />           



        <%-- Inicio de jquery para calendario --%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">

        <script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/js/registroProfesores.js"></script>  
        <script src="${pageContext.request.contextPath}/js/validate.js"></script>   
        <%-- Carga de menus 
        <script src="${pageContext.request.contextPath}/js/cargarMenus2.js"></script>  
        <%-- menus--%>

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
                $( "#txtFechaNacimiento" ).datepicker({
                    changeMonth:true,
                    changeYear:true
                    /*   showOn: "button",
                    buttonImage: "${pageContext.request.contextPath}/images/ico.png",
                    buttonImageOnly: true,
                    showButtonPanel: true                    */
                });
                
                $( "#txtFechaIngreso" ).datepicker({
                    changeMonth:true,
                    changeYear:true
                    //showOn: "button",
                    //buttonImage: "${pageContext.request.contextPath}/images/ico.png",
                    //buttonImageOnly: true,
                    //showButtonPanel: true                    
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
    <body>
        <!-- start header  -->
        <div id="header">
            <!--TRAER EL MENU DESDE JQUERY -->
        </div>
        <!-- end header -->
        <div id="logo">
            <div id="logoTitulo" align ="left">
                REGISTRO DE PROFESORES
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
        <!--<div id="banner"></div>-->

        <!-- start page -->
        <div id="page">
            <!-- start content -->
            <div id="content">
                <div class="post">
                    <div id="formulario">
                        <form style="padding-bottom: 35px;padding-right: 6px;">
                            <div align ="right">
                                <table style="margin-left: 340px;">
                                    <tr>
                                        <td>
                                            <img id="imgIconEditAlumno" src="${pageContext.request.contextPath}/images/Edit_page.png" width="25" height="25" />                                    
                                        </td>
                                        <td>
                                            <img id="imgIconNewAlumno" src="${pageContext.request.contextPath}/images/icon_contact-new.png" width="25" height="25" />                                    
                                        </td>
                                    </tr>
                                </table>
                            </div> 
                            <fieldset>
                                <legend>DATOS DE PROFESORES</legend>

                                <ul>
                                    <li id="fo1li1" class="notranslate">
                                        <label class="desc " id="title6" for="Field6">
                                            CODIGO:
                                        </label>
                                        <div>
                                            <input id="txtCodigo" name="CODIGO:" type="text" class="classRegProfesor" value="" tabindex="1" style="width: 250px;">  
                                            <img id="imgIconAlumno" class="classRegProfesorBoton" src="${pageContext.request.contextPath}/images/icon_Search.png" width="16" height="16"/>
                                        </div>
                                    </li>


                                    <li id="fo1li3" class="notranslate      ">
                                        <span id="spIdPersona"></span>
                                        <label class="desc " id="title3" for="Field3">
                                            DNI:
                                        </label>
                                        <div>
                                            <input id="txtDni" name="DNI:" type="text" class="classRegProfesor" value="" tabindex="2" maxlength="8">
                                        </div>
                                    </li>                                
                                    <li id="fo1li1" class="notranslate">
                                        <label class="desc " id="title6" for="Field6">
                                            APELLIDO PATERNO:
                                        </label>
                                        <div>
                                            <input id="txtApPaterno" name="APELLIDO PATERNO:" type="text" class="classRegProfesor" value="" tabindex="3" style="width: 250px;">                                    </div>
                                    </li>

                                    <li id="fo1li1" class="notranslate      ">
                                        <label class="desc " id="title6" for="Field6">
                                            APELLIDO MATERNO:
                                        </label>
                                        <div>
                                            <input id="txtApMaterno" name="APELLIDO MATERNO:" type="text" class="classRegProfesor" value="" tabindex="4" style="width: 250px;">
                                        </div>
                                    </li>    

                                    <li id="fo1li1" class="notranslate      ">
                                        <label class="desc " id="title6" for="Field6">
                                            NOMBRES:
                                        </label>
                                        <div>
                                            <input id="txtNombres" name="NOMBRES:" type="text" class="classRegProfesor" value="" tabindex="5" style="width: 300px;">
                                        </div>
                                    </li>                                   
                                    <li id="fo1li8" class="date notranslate      ">
                                        <label>FECHA DE NACIMIENTO:</label><br/>
                                        <input type="text" class="classProfesoresFecha" name="FECHA DE NACIMIENTO:" id="txtFechaNacimiento" tabindex="6"><br/>
                                    </li>

                                    <li id="fo1li4" class="notranslate      ">
                                        <label class="desc " id="title4" for="Field4">
                                            SEXO:
                                        </label>
                                        <div>
                                            <label class="desc " id="title4" for="Field4">
                                                MASCULINO
                                            </label>
                                            <input type="radio" id="#rbSexoM" name="SEXO" value="M" checked="checked" tabindex="7"/>
                                            <label class="desc " id="title4" for="Field4">
                                                FEMENINO
                                            </label>  
                                            <input type="radio" id="#rbSexoF" name="SEXO"  value="F" tabindex="8"/>                                        

                                        </div>  
                                    </li>

                                    <li id="fo1li8" class="date notranslate      ">
                                        <label>FECHA DE INGRESO</label><br/>
                                        <input type="text" class="classProfesoresFecha" name="FECHA DE INGRESO:" id="txtFechaIngreso" tabindex="9"><br/>
                                    </li>
                            </fieldset>

                            <!--<fieldset>
                                <legend>DATOS DE PROCEDENCIA</legend>
                                <ul>
                                    <li id="fo1li5" class="notranslate">

                                        <label class="desc " id="title5" for="Field5">PROCEDENCIA:</label>
                                        <br>
                                        <div>                                                
                                            <select id="cboProcedencia" name="PROCEDENCIA" class="classRegAlumno" tabindex="11">
                                            </select>
                                            <input type="text" id ="txtOtroProc" class="classRegAlumnoProc" name="Otro" value="" style="width: 330px;"/>                                            
                                        </div>  
                                    </li>       
                                </ul>
                            </fieldset> -->

                            <br>
                            <div  align ="center">
                                <button id="btnCancelar" type="submit" style="height: 30px;" tabindex="11" class="classRegProfesorBoton">Cancelar</button>                                    
                                <button id="btnIngresar" type="submit" style="height: 30px;" tabindex="12" class="classRegProfesorBoton">Ingresar</button>
                                <button id="btnEditar" type="submit" style="height: 30px;" tabindex="13" class="classRegProfesorBoton">Editar</button>
                            </div>                                        

                        </form>
                    </div>

                </div>
            </div>
            <!-- end content -->
            <!-- start sidebar _right side--> 
            <div id="sidebar" align="right">
                <!--Colocar imagen del colegio-->
                <img src="${pageContext.request.contextPath}/images/logoLordBrain_1.png" width="180" height="180"/>
                <br>
                <br>
                <br>                
                <div style="background: #1F0303" id="divDocumentos">
                </div>                   
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
        <!-- start popup Consulta Alumno-->        
        <div id="buscarProfesor" title="Buscar Profesor" style="background: #1F0303">
            <form class="formulariodemo cf">
                <input type="text" id="txtProfesor" value="" placeholder="Ingresar texto..." required style="height: 10px;"/>
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
                            <th class="text-left" style="width: 800px" >PROFESOR</th>
                        </tr>
                    </thead>
                    <tbody id="tblProfesores">

                    </tbody>
                </table>
            </div>
        </div>  
        <!-- end popup -->   

        <!-- start popup Consulta Alumno     
        <div id="verDocumentos" title="Buscar Alumno" style="background: #1F0303">
            <fieldset>
                <legend>Documentos :</legend>
                <ul id="liDocumentos">

                </ul>
            </fieldset>
        </div>            
         end popup -->   

    </body>
</html>
