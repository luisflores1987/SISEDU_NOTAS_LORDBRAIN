<%-- 
    Document   : frmRegistroHorarios
    Created on : 01/04/2016, 03:00:34 PM
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
        <link href="${pageContext.request.contextPath}/css/tablasConsultaAlumno.css"  rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/registroHorario.css"  rel="stylesheet"/>
        <script src="${pageContext.request.contextPath}/js/registroHorarios.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.timepicker.js"></script>
        <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/jquery.timepicker.css">
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
            <div id="content">
                <div id="HorarioGeneral">
                    <fieldset>
                        <legend>HORARIO</legend>
                        <table align="center">
                            <tr>
                                <td>
                                    <p>
                                        <label>INGRESO</label>
                                    </p>
                                    <p>
                                        <input type="text" id="txtEntradaMasivo" class="ingresoMasivo" name="Entrada" value="" />
                                    </p>
                                </td>
                                <td>
                                    <p>
                                        <label>SALIDA</label>
                                    </p>                            
                                    <p>
                                        <input type="text" id="txtSalidaMasivo" class="salidaMasivo" name="Salida" value="" />
                                    </p>
                                </td>
                                <td>
                                    <p style="margin-bottom: 24px;">
                                        <label></label>
                                    </p>                            
                                    <p>
                                        <button id="btnCancelarMasivo" class="masivo" type="submit" style="width: 45px;height: 25px;padding-left: 0px;margin-left: 7px;">Cancel</button>   
                                        <button id="btnIngresarMasivo" class="masivo" type="submit" style="width: 45px;height: 25px;">OK</button>                                    
                                    </p>
                                </td>                            
                            </tr>
                            <tr>
                                <td style="float:left">
                                    <p></p>
                                    <p>
                                        <button id="btnBloque" type="submit" style="width: 80px;height: 20px;">Bloque</button>                                    
                                    </p>
                                </td>
                                <td style="float:left">
                                    <p></p>                            
                                    <p>
                                        <button id="btnDni" type="submit" style="width: 80px;height: 20px;">Dni</button>                                    
                                    </p>
                                </td>
                            </tr>                        

                        </table>
                    </fieldset>
                </div>
                <br>
                <br>
                <br>
                <div id="HorarioBloque">
                    <fieldset>
                        <legend>HORARIO POR DIAS</legend>
                        <table align="center" style="width:90%">
                            <tr>
                                <td>
                                    <p>
                                        <label>INGRESO</label>
                                    </p>
                                    <p>
                                        <input type="text" id="txtEntradaDias" class="entradaDias" name="Entrada" value="" />
                                    </p>
                                </td>
                                <td>
                                    <p>
                                        <label>SALIDA</label>
                                    </p>                            
                                    <p>
                                        <input type="text" id="txtSalidaDias" class ="salidaDias" name="Salida" value="" />
                                    </p>
                                </td>
                                <td>
                                    <p style="margin-bottom: 24px;">
                                        <label></label>
                                    </p>                            
                                    <p>

                                    </p>
                                </td>                            
                            </tr>

                            <tr>
                                <td colspan="2">
                                    <p></p>                            
                                    <p>
                                        <input type="checkbox" id = "chkLu" value = "1" name="horarioSemana"/><label>Lu</label>
                                        <input type="checkbox" id = "chkLu" value = "2" name="horarioSemana"/><label>Ma</label>
                                        <input type="checkbox" id = "chkLu" value = "3" name="horarioSemana"/><label>Mi</label>
                                        <input type="checkbox" id = "chkLu" value = "4" name="horarioSemana"/><label>Ju</label>
                                        <input type="checkbox" id = "chkLu" value = "5" name="horarioSemana"/><label>Vi</label>
                                        <input type="checkbox" id = "chkLu" value = "6" name="horarioSemana"/><label>Sa</label>                                   
                                    </p>
                                </td>
                            </tr>   

                            <tr>
                                <td colspan="2">
                                    <p></p>
                                    <p>
                                        <button id="btnCancelarDias" type="submit" style="width: 100px;height: 25px;">Cerrar</button>     
                                        <button id="btnIngresarDias" type="submit" style="width: 100px;height: 25px;">Grabar</button>                                             
                                    </p>
                                </td>
                            </tr>                              

                        </table>
                    </fieldset>
                </div>
                <br>
                <br>
                <br>
                <div id="HorarioProfesor">
                    <fieldset>
                        <legend>HORARIO POR PROFESOR</legend>
                        <table id="tblHorarioProfesor" align="center" style="width:90%">
                            <tr>
                                <td colspan="3">
                                    <p></p>
                                    <label>PROFESOR</label>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <p>                                        
                                        <span id="spProfesor"></span><input type="text" id="txtProfesor" name="txtSalida" value="" style="width: 345px;background-color: #BABABA" disabled="disabled"/><img id="imgIconAlumno" src="${pageContext.request.contextPath}/images/icon_Search.png" width="16" height="16"/>                                   
                                    </p>
                                </td>
                            </tr> 
                            <tr>
                                <td>
                                    <p>
                                        <label>DIAS</label>
                                    </p>
                                </td>                                
                                <td>
                                    <p>
                                        <label>INGRESO</label>
                                    </p>
                                </td>
                                <td>                        
                                    <p>
                                        <label>SALIDA</label>
                                    </p>
                                </td>                         
                            </tr>                            
                            <tr>
                                <td>
                                    <p>
                                        <input type="checkbox" id = "chkLu1" value = "1" name="horarioSemanaProfesor"/><label>Lu</label>
                                    </p>
                                </td>                                
                                <td>
                                    <p>
                                        <input type="text" id="txtEntradaProfesor1" class="entrada" name="EntradaProfesor" value="" />
                                    </p>
                                </td>
                                <td>                        
                                    <p>
                                        <input type="text" id="txtSalidaProfesor1" class="salida" name="SalidaProfesor" value="" />
                                    </p>
                                </td>                        
                            </tr>

                            <tr>
                                <td>
                                    <p>
                                        <input type="checkbox" id = "chkLu2" value = "2" name="horarioSemanaProfesor"/><label>Ma</label>
                                    </p>
                                </td>                                
                                <td>
                                    <p>
                                        <input type="text" id="txtEntradaProfesor2" class="entrada" name="EntradaProfesor" value="" />
                                    </p>
                                </td>
                                <td>                        
                                    <p>
                                        <input type="text" id="txtSalidaProfesor2" class="salida" name="SalidaProfesor" value="" />
                                    </p>
                                </td>                        
                            </tr>                            
                            <tr>
                                <td>
                                    <p>
                                        <input type="checkbox" id = "chkLu3" value = "3" name="horarioSemanaProfesor"/><label>Mi</label>
                                    </p>
                                </td>                                
                                <td>
                                    <p>
                                        <input type="text" id="txtEntradaProfesor3" class="entrada" name="EntradaProfesor" value="" />
                                    </p>
                                </td>
                                <td>                        
                                    <p>
                                        <input type="text" id="txtSalidaProfesor3" class="salida" name="SalidaProfesor" value="" />
                                    </p>
                                </td>                        
                            </tr>
                            
                            <tr>
                                <td>
                                    <p>
                                        <input type="checkbox" id = "chkLu4" value = "4" name="horarioSemanaProfesor"/><label>Ju</label>
                                    </p>
                                </td>                                
                                <td>
                                    <p>
                                        <input type="text" id="txtEntradaProfesor4" class="entrada" name="EntradaProfesor" value="" />
                                    </p>
                                </td>
                                <td>                        
                                    <p>
                                        <input type="text" id="txtSalidaProfesor4" class="salida" name="SalidaProfesor" value="" />
                                    </p>
                                </td>                        
                            </tr>
                            
                            <tr>
                                <td>
                                    <p>
                                        <input type="checkbox" id = "chkLu5" value = "5" name="horarioSemanaProfesor"/><label>Vi</label>
                                    </p>
                                </td>                                
                                <td>
                                    <p>
                                        <input type="text" id="txtEntradaProfesor5" class="entrada" name="EntradaProfesor" value="" />
                                    </p>
                                </td>
                                <td>                        
                                    <p>
                                        <input type="text" id="txtSalidaProfesor5" class="salida" name="SalidaProfesor" value="" />
                                    </p>
                                </td>                        
                            </tr>
                            
                            <tr>
                                <td>
                                    <p>
                                        <input type="checkbox" id = "chkLu6" value = "6" name="horarioSemanaProfesor"/><label>Sa</label>
                                    </p>
                                </td>                                
                                <td>
                                    <p>
                                        <input type="text" id="txtEntradaProfesor6" class="entrada" name="EntradaProfesor" value="" />
                                    </p>
                                </td>
                                <td>                        
                                    <p>
                                        <input type="text" id="txtSalidaProfesor6" class="salida" name="SalidaProfesor" value="" />
                                    </p>
                                </td>                        
                            </tr>                              
                            <tr>
                                <td style="padding-right: 67px;" colspan="3">
                                        <button id="btnCancelarProfesor" type="submit" style="width: 100px;height: 25px;">Cerrar</button> 
                                        <button id="btnIngresarProfesor" type="submit" style="width: 100px;height: 25px;">Grabar</button>                                    
                                </td>
                            </tr>                              

                        </table>
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
            <p id="legal">( c ) 2017. All Rights Reserved. IEP. LORD BRAIN </a> designed by <a href="http://www.apiservi.com/">APISERVI SAC</a>.</p>
        </div>
        <!-- end footer -->    

        <div id="buscarProfesor" title="Buscar Profesor" style="background: #1F0303">
            <form class="formulariodemo cf">
                <input type="text" id="txtProfesorBusqueda" value="" placeholder="Ingresar texto..." required style="height: 10px;"/>
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
                            <th class="text-left" style="width: 800px" colspan="3">PROFESOR</th>
                        </tr>
                    </thead>
                    <tbody id="tblProfesores">

                    </tbody>
                </table>
            </div>
        </div>         
    </body>
</html>
