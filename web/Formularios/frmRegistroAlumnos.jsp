<%-- 
    Document   : frmRegistroPersonas
    Created on : 26/01/2016, 08:59:30 AM
    Author     : lflores
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.sisedu.model.bean.Nivel"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>        
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
        <link href="${pageContext.request.contextPath}/css/tooltiptext.css" rel="stylesheet" type="text/css" media="screen" />

        <link href="${pageContext.request.contextPath}/css/registroAlumno.css"  rel="stylesheet"/>



        <%-- Inicio de jquery para calendario --%>  
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">

        <script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/js/registroAlumnos.js"></script>  
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
                    changeYear:true,
                    showOn: "button",
                    buttonImage: "${pageContext.request.contextPath}/images/ico.png",
                    buttonImageOnly: true,
                    showButtonPanel: true                    
                });
            });
                
            $(function() {
                $( "#txtFechaRegistro" ).datepicker({
                    changeMonth:true,
                    changeYear:true,
                    //showOn: "button",
                    //buttonImage: "${pageContext.request.contextPath}/images/ico.png",
                    buttonImageOnly: false,
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
            <div id="logoTitulo">

                <table border="0">
                    <tbody>
                        <tr>
                            <td>
                                <h2><label style="text-transform: uppercase" class="bannertext">
                                        REGISTRO DE ALUMNOS
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

            <form style="padding-bottom: 35px;padding-right: 6px;">

                <div id="divbotonera" align ="right">
                    <table id="tblBotonera">
                        <tr>
                            <td>
                                <button id="btnIngresar" class="classRegAlumnoBoton">
                                    <img src="${pageContext.request.contextPath}/images/OK.png" alt=""/>
                                    Ingresar
                                </button>                                    
                            </td>
                            <td>
                                <button id="btnEditar"  class="classRegAlumnoBoton">Editar
                                    <img src="${pageContext.request.contextPath}/images/Edit_page.png" alt=""/>
                                </button>                                 
                            </td>                            
                            <td>
                                <button id="btnCancelar" type="submit" class="classRegAlumnoBoton">
                                    <img src="${pageContext.request.contextPath}/images/Cancel.png" alt=""/>
                                    Cancelar
                                </button>  
                            </td>
                            <td>
                                <button id="imgIconDeleteAlumno">
                                    <img title="Inhabilitar Alumno" src="${pageContext.request.contextPath}/images/disabledUserUltimo.png"/>                                    
                                    Inhabilitar Alumno
                                </button>
                                <button id="imgIconEnabledAlumno">
                                    <img title="Habilitar Alumno" src="${pageContext.request.contextPath}/images/EnabledUser.png"/>                                    
                                    Habilitar Alumno
                                </button>
                            </td>

                            <td>
                                <button id="imgIconAlumno">Buscar Alumno
                                    <img title="Buscar Alumno" src="${pageContext.request.contextPath}/images/icon_Search.png"/>
                                </button>
                            </td>
                            <td>
                                <button id="imgIconNewAlumno">Agregar Alumno
                                    <img title="Agregar Alumno" src="${pageContext.request.contextPath}/images/icon_contact-new.png"/>  
                                </button>
                            </td>
                        </tr>
                    </table>

                </div>
                <div id="contenido">
                    <div id="divLeft">
                        <fieldset>
                            <legend>DATOS DE ALUMNOS</legend>
                            <div align ="right">
                                <table style="margin-left: 340px;">
                                    <tr>
                                    <label> ESTADO : </label><span id="spEstadoAlumno" name ="estado"></span>
                                    </tr>
                                </table>
                            </div> 
                            <ul>
                                <li id="fo1li5" class="notranslate">
                                    <label class="desc" id="title0" for="Field0">
                                        TIPO MATRICULA :
                                    </label>
                                    <div>
                                        <div>                                                
                                            <select id="cboTipoMatricula" name="Tipo Matricula" class="regAlumnoCbo" tabindex="0">
                                            </select>
                                        </div>   
                                    </div>
                                </li>

                                <li id="fo1li3" class="notranslate">
                                    <span id="spIdPersona"></span>
                                    <label class="desc " id="title3" for="Field3">
                                        DNI:
                                    </label>
                                    <div>
                                        <input id="txtDni" name="DNI:" type="text" class="classRegAlumno" value="" tabindex="1" maxlength="8">                                            
                                    </div>
                                </li>                                
                                <li id="fo1li1" class="notranslate">
                                    <label class="desc " id="title6" for="Field6">
                                        APELLIDO PATERNO:
                                    </label>
                                    <div>
                                        <input id="txtApPaterno" name="APELLIDO PATERNO:" type="text" class="classRegAlumno" value="" tabindex="2" style="width: 40%;">                                    
                                    </div>
                                </li>

                                <li id="fo1li1" class="notranslate      ">
                                    <label class="desc " id="title6" for="Field6">
                                        APELLIDO MATERNO:
                                    </label>
                                    <div>
                                        <input id="txtApMaterno" name="APELLIDO MATERNO:" type="text" class="classRegAlumno" value="" tabindex="3" style="width: 40%;">
                                    </div>
                                </li>    

                                <li id="fo1li1" class="notranslate      ">
                                    <label class="desc " id="title6" for="Field6">
                                        NOMBRES:
                                    </label>
                                    <div>
                                        <input id="txtNombres" name="NOMBRES:" type="text" class="classRegAlumno" value="" tabindex="4" style="width: 80%;">
                                    </div>
                                </li>                                   
                                <li id="fo1li8" class="date notranslate      ">
                                    <label>FECHA DE NACIMIENTO:</label><br/>
                                    <input type="text" name="FECHA DE NACIMIENTO:" id="txtFechaNacimiento" tabindex="5"><br/>
                                </li>

                                <li id="fo1li4" class="notranslate      ">
                                    <label class="desc " id="title4" for="Field4">
                                        SEXO:
                                    </label>
                                    <div>
                                        <label class="desc " id="title4" for="Field4">
                                            MASCULINO
                                        </label>
                                        <input type="radio" id="#rbSexoM" name="SEXO" tabindex="6" value="M" checked="checked" tabindex="6"/>
                                        <label class="desc " id="title4" for="Field4">
                                            FEMENINO
                                        </label>  
                                        <input type="radio" id="#rbSexoF" name="SEXO"  tabindex="7" value="F" tabindex="7"/>                                        

                                    </div>  
                                </li>

                                <li id="fo1li5" class="notranslate">
                                    <label class="desc " id="title5" for="Field5">
                                        NIVEL
                                    </label>
                                    <div>
                                        <div>                                                
                                            <select id="cboNivel" name="NIVEL" class="regAlumnoCbo" tabindex="8">
                                            </select>
                                        </div>                                            
                                    </div>
                                </li>

                                <li id="fo1li4" class="notranslate">

                                    <div>
                                        <div>
                                            <input style="width: 15px; height: 15px;position: relative;vertical-align: bottom;top: -2px;" type="checkbox" name = "chkRepite" value = "1" />
                                            <label>¿REPITE EL GRADO?</label>                                                
                                        </div> 
                                        <label class="desc " id="title4" for="Field4">
                                            GRADO
                                        </label>
                                        <div>
                                            <select id="cboGrado" name="GRADO" class="regAlumnoCbo" tabindex="10">
                                                <option selected="selected">
                                                    Seleccione
                                                </option>
                                            </select>
                                        </div>
                                    </div>  
                                </li>
                                <li>
                                    <label class="desc " id="title4" for="Field4">
                                        SECCIÓN
                                    </label>
                                    <div>
                                        <select id="cboSeccion" name="SECCION" class="regAlumnoCbo" tabindex="9">
                                            <option selected="selected">
                                                Seleccione
                                            </option>
                                        </select>
                                    </div> 
                                </li>

                                <li id="fo1li8" class="date notranslate">
                                    <label>DIRECCIÓN ACTUAL:</label>
                                    <br>
                                    <input type="text" name="Dirección Actual:" id="txtDireccionActual" class="classRegAlumno" tabindex="10" style="width: 80%;"><br/>
                                </li>
                                <li id="fo1li5" class="notranslate">

                                    <label class="desc " id="title5" for="Field5">PROCEDENCIA:</label>
                                    <br>
                                    <div>                                                
                                        <select id="cboProcedencia" name="PROCEDENCIA" class="regAlumnoCbo" tabindex="11">
                                        </select>
                                        <br>
                                        <input type="text" id ="txtOtroProc" class="classRegAlumnoProc" name="Otro" value="" style="width: 80%;"/>                                            
                                    </div>  
                                </li>       
                        </fieldset>
                    </div>
                    <div id="divRight">
                        <fieldset>
                            <legend>DATOS DE APODERADO</legend>
                            <br>
                            <li id="fo1li1" class="apoderado">
                                <fieldset class="formApoderado">
                                    <legentd>APODERADO :</legentd>      
                                    <ul>
                                        <li id="fo1li1" class="notranslate      ">
                                            <label class="desc " id="title6" for="Field6">
                                                NOMBRE COMPLETO:
                                            </label>
                                            <div>
                                                <input id="txtNombresAp" name="NOMBRES APODERADO:" type="text" class="classRegAlumno" value="" tabindex="12" style="width: 80%;">
                                            </div>
                                            <label style="font-size: 12px;color:red" id="lblMensajeApoderado"></label>
                                        </li>
                                        <li id="fo1li1" class="notranslate      ">
                                            <label class="desc " id="title6" for="Field6">
                                                PARENTESCO:
                                            </label>
                                            <div>
                                                <select id="cboParentescoAp" name="NIVEL" class="regAlumnoCbo" tabindex="13">
                                                </select>
                                                <input type="text" id ="txtOtroAp" class="classRegAlumnoAp" name="Otro" value=""/>
                                            </div>
                                        </li> 
                                        <li id="fo1li1" class="notranslate      ">
                                            <label class="desc " id="title6" for="Field6">
                                                TELEFÓNO O CELULAR:
                                            </label>
                                            <div>
                                                <input id="txtTelefonoAp" name="TELEFONO:" type="text" class="classRegAlumno" value="" tabindex="14" style="width: 80%;">
                                            </div>
                                        </li>   
                                        <li id="fo1li1" class="notranslate      ">
                                            <label class="desc " id="title6" for="Field6">
                                                GRADO O INSTRUCCIÓN:
                                            </label>
                                            <div>
                                                <select id="cboGradoInstruccionAp" name="GRADOINSTRUCCION" class="regAlumnoCbo" tabindex="15">
                                                </select>
                                            </div>
                                        </li> 
                                    </ul>
                                </fieldset>
                            </li>   
                            <li id="fo1li1" class="apoderadoAlterno">

                                <fieldset class="formApoderado">
                                    <legentd>APODERADO ALTERNO:</legentd>
                                    <ul>
                                        <li id="fo1li1" class="notranslate">
                                            <label class="desc " id="title6" for="Field6">
                                                NOMBRE COMPLETO:
                                            </label>
                                            <div>
                                                <input id="txtNombresAa" name="NOMBRES APODERADO ALTERNO:" type="text" value="" class="classRegAlumnoAlternativo" tabindex="16" style="width: 80%;">
                                            </div>
                                        </li>
                                        <li id="fo1li1" class="notranslate      ">
                                            <label class="desc " id="title6" for="Field6">
                                                PARENTESCO:
                                            </label>
                                            <div>
                                                <select id="cboParentescoAa" name="PARENTESCO" tabindex="17" class="classRegAlumnoAlternativo regAlumnoCboAA">

                                                </select>
                                                <input type="text" id ="txtOtroAa"  name="Otro" value=""/>                                                        
                                            </div>
                                        </li> 
                                        <li id="fo1li1" class="notranslate      ">
                                            <label class="desc " id="title6" for="Field6">
                                                TELEFÓNO O CELULAR:
                                            </label>
                                            <div>
                                                <input id="txtTelefonoAa" name="TELEFONO:" class="classRegAlumnoAlternativo" type="text" value="" tabindex="18" style="width: 80%;">
                                            </div>
                                        </li>   
                                        <li id="fo1li1" class="notranslate      ">
                                            <label class="desc " id="title6" for="Field6">
                                                GRADO O INSTRUCCIÓN:
                                            </label>
                                            <div>
                                                <select id="cboGradoInstruccionAa" class="classRegAlumnoAlternativo regAlumnoCboAA" name="GRADOINSTRUCCION" tabindex="19">
                                                </select>
                                            </div>
                                        </li>                                                   
                                    </ul>
                                </fieldset>
                            </li>      
                            </ul>
                        </fieldset>
                        <div id="divDocumentos">
                            <fieldset>
                                <legend>DOCUMENTOS </legend>
                                <br>
                                <br>                        
                                <ul id="liDocumentos">

                                </ul>
                            </fieldset>
                        </div> 
                    </div>
                </div>

                <br>


            </form>
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
                <input type="text" id="txtPaciente" value="" placeholder="Ingresar texto..." required style="height: 10px;"/>
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
                            <th class="text-left" style="width: 10%">AÑO</th>                      
                        </tr>
                    </thead>
                    <tbody id="tblAlumnos">

                    </tbody>
                </table>
            </div>
        </div>  
        <!-- end popup -->   

        <!-- start popup Consulta Alumno  -->     
        <div id="eliminarAlumno" title="Ingresar fecha " style="background: #1F0303; margin: 0 auto;">
            <table style="width: 95%">
                <tr>
                    <td> 
                        <input style="font-family: ''Trebuchet MS'', Arial, Helvetica, sans-serif;font-size: 13px;color: #333333;background: rgb(255, 255, 170);width: 300px;" type="text" id="txtFechaRegistro" name ="Fecha" value="" placeholder="Ingrese fecha de retiro de alumno ..." required style="height: 10px;"/>
                    </td>
                    <td>
                        <button id="btnInhabilitar" style="height: 23px;padding-bottom: 4px;padding-top: 3px;">
                            <img src="${pageContext.request.contextPath}/images/OK.png" alt=""/>
                            OK
                        </button>                      
                    </td>
                    <td>
                        <button id="btnCancelarInhabilitar" style="height: 23px;padding-bottom: 4px;padding-top: 3px;">
                            <img src="${pageContext.request.contextPath}/images/Cancel.png" alt=""/>
                            Cancelar
                        </button> 
                    </td>
                </tr>
            </table>
        </div>          

        <div id="habilitarAlumno" title="Ingresar fecha " style="background: #1F0303; margin: 0 auto;">
            <table style="width: 95%">
                <tr>
                    <td> 
                        <input style="font-family: ''Trebuchet MS'', Arial, Helvetica, sans-serif;font-size: 13px;color: #333333;background: rgb(255, 255, 170);width: 300px;" type="text" id="txtFechaRegistro" name ="Fecha" value="" placeholder="Ingrese fecha de retiro de alumno ..." required style="height: 10px;"/>
                    </td>
                    <td>
                        <button id="btnhabilitar" style="height: 23px;padding-bottom: 4px;padding-top: 3px;">
                            <img src="${pageContext.request.contextPath}/images/OK.png" alt=""/>
                            OK
                        </button>                      
                    </td>
                    <td>
                        <button id="btnCancelarhabilitar" style="height: 23px;padding-bottom: 4px;padding-top: 3px;">
                            <img src="${pageContext.request.contextPath}/images/Cancel.png" alt=""/>
                            Cancelar
                        </button> 
                    </td>
                </tr>
            </table>
        </div>    
        <!--  end popup -->   
    </body>
</html>
