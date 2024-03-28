<%-- 
    Document   : frmConsultaPagos
    Created on : 02/03/2016, 10:08:35 AM
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
        <link href="${pageContext.request.contextPath}/css/tablasConsultaDeuda.css"  rel="stylesheet"/>

        <script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.ui.draggable.js" type="text/javascript"></script>

        <script src="${pageContext.request.contextPath}/js/jquery.alerts.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath}/css/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" />        

        <%-- Inicio de jquery para calendario --%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
        <script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/js/validate.js"></script> 
        <%-- Inicio de jquery para calendario --%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
        <script src="${pageContext.request.contextPath}/js/consultaDeudas.js"></script> 
        <script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/js/registroAlumnos.js"></script>  
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
    <div id="logo">
        <div id="logoTitulo">

            <table border="0">
                <tbody>
                    <tr>
                        <td>
                            <h2><label style="text-transform: uppercase" class="bannertext">
                                    CONSULTA DE DEUDAS
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
        <div id="content">
            <div class="post">
                <form class="">
                    <table id style="width: 600">
                        <tr>
                            <td>
                                <label>AÑO ACADEMICO :</label> 
                            </td>
                            <td>
                                <select id="cboAñoAcademico" style="height: 20px;">
                                    <option value="1">2016</option>
                                    <option value="2" selected>2017</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" id ="ckNIVEL" name="ckNIVEL"><label>NIVEL</label></input></td>
                            <td>
                                <select id="cboNivel" name="NIVEL" style="height: 20px;">
                                    <option>
                                        Seleccione
                                    </option>
                                </select>
                            </td>                          
                        </tr>
                        <tr>
                            <td><input type="checkbox" id ="ckGRADO" name="ckGRADO"><label>GRADO</label></input></td>
                            <td>
                                <select id="cboGrado" name="GRADO" style="height: 20px;">
                                    <option>
                                        Seleccione
                                    </option>
                                </select>
                            </td>                          
                        </tr>                       
                    </table>                      
                </form>  
            </div>
            <div id="botoneraDeuda">
                <button id="btnExportarBuscarDeudasAlumno" type="submit" style="height: 30px;">Exportar a Excel
                    <img title="Exportar Excel" src="${pageContext.request.contextPath}/images/icon_excel.png"/>
                </button> 
                <button id="btnBuscarDeudasAlumno" type="submit" style="height: 30px;">Buscar
                    <img title="Buscar" src="${pageContext.request.contextPath}/images/icon_Search.png"/>
                </button>            
            </div>
        </div> 
        <div style="clear: both;">&nbsp;</div>
        <div style="clear: both;">&nbsp;</div>
        <div id="divTablaConsultaDeuda" class="table-titleCONSULTA" style="overflow:auto;" onscroll="OnScrollDiv(this)">
            <table class="datosTablaPension"><tr><td colspan="2" style="font-size: 16px;color:#2A0F0F;">CONSULTA DE DEUDA</td></tr><tr><td colspan="2"><label id="spFechaActual"></label></td></tr></table>
            <table  border=1 class="table-fillCONSULTA" align="center">
                <thead>
                    <tr>
                        <th  bgcolor="#FF0000" class="text-left">N</th>
                        <th  bgcolor="#FF0000" class="text-left" style="width:30%">Nombres</th>
                        <th  bgcolor="#FF0000" class="text-left" style="width:5%">Vac</th>                        
                        <th  bgcolor="#FF0000" class="text-left" style="width:5%">Mat</th>
                        <th  bgcolor="#FF0000" class="text-left" style="width:5%">Mar</th>
                        <th  bgcolor="#FF0000" class="text-left" style="width:5%">Abr</th>
                        <th  bgcolor="#FF0000" class="text-left" style="width:5%">May</th>
                        <th  bgcolor="#FF0000" class="text-left" style="width:5%">Jun</th>                                
                        <th  bgcolor="#FF0000" class="text-left" style="width:5%">Jul</th>                                
                        <th  bgcolor="#FF0000" class="text-left" style="width:5%">Ago</th>                                
                        <th  bgcolor="#FF0000" class="text-left" style="width:5%">Set</th>                                                                
                        <th  bgcolor="#FF0000" class="text-left" style="width:5%">Oct</th>
                        <th  bgcolor="#FF0000" class="text-left" style="width:5%">Nov</th> 
                        <th  bgcolor="#FF0000" class="text-left" style="width:5%">Dic</th> 
                    </tr>
                </thead>
                <tbody id="tblAlumnosDeudas">

                </tbody>
            </table>
        </div>

    </div>
    <!-- end content -->
    <!-- start sidebar _right side--> 
    <div id="sidebar" align="right">
        <!--Colocar imagen del colegio
        <img src="${pageContext.request.contextPath}/images/logoLordBrain_1.png" width="180" height="180"/>
        -->
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

</body>
</html>

