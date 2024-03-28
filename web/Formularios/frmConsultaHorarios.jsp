<%-- 
    Document   : frmConsultaHorarios
    Created on : 12/04/2016, 07:07:14 AM
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
        <script src="${pageContext.request.contextPath}/js/consulaHorarios.js"></script>
        <link href="${pageContext.request.contextPath}/css/tablasConsultaHorarioProfesor.css"  rel="stylesheet"/> 
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
        <div id="page" style="height:  480px">
            <br>
            <br>               
            <div id="content" style="width: 745px">
                <form class="">
                    <table id="tblAsistencia" style="width: 734">
                        <tr>
                            <td><input type="checkbox" id ="ckProfesor" name="ckProfesor" class="chkFiltro"><label>Profesor </label></td>
                            <td style="width: 567px;">
                                <select id="cbProfesor" name="cbProfesor" class="txtFiltro" style="width: 309px;">

                                </select>  
                                <button id="btnBuscarHorarioProfesor" type="submit" style="height: 30px;width: 100px;margin-right: 2px;">Buscar</button>
                            </td> 
                        <tr>
                            <td><input type="checkbox" id ="chkDiaSemana" name="chkDiaSemana" class="chkFiltro"><label>Dia de Semana</label></td>
                            <td style="width: 567px;">
                                <select id="cbDiaDeSemana" name="cbDiaDeSemana" class="txtFiltro" style="width: 309px;">

                                </select>  
                            </td> 
                        </tr>
                    </table>

                </form>  
                <br>
                <br>
                <div style="overflow:auto;" class="table-titleHorarios">
                    <table  class="table-fillHorarios">
                        <thead>
                            <tr>
                                <th  bgcolor="#FF0000" class="text-center" style="width: 414px;">Profesor</th>
                                <th  bgcolor="#FF0000" class="text-center" style="width: 101px;">Dia</th>
                                <th  bgcolor="#FF0000" class="text-center" style="width: 100px;">Hora de Ingreso</th>
                                <th  bgcolor="#FF0000" class="text-center" style="width: 117px;">Hora de Salida</th>
                            </tr>
                        </thead>
                        <tbody id="tblProfesorHorario">

                        </tbody>
                    </table>
                </div>

                <div id="DivFooterRow" style="overflow:hidden">
                </div>
            </div>

            <div id="divTablaReporte" class="table-titleReporte" style="margin-left: 0px;"></div>
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
