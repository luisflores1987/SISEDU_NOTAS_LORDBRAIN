<%-- 
    Document   : frmReporteGrado
    Created on : 23/03/2016, 05:29:45 AM
    Author     : lflores
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script  type = "text/javascript" >
            {
                if (history.forward(1)) {
                    location.replace(history.forward(1))
                }
            }
        </script>  
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/SlideShow/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/SlideShow/jssor.slider.mini.js"></script>        
        <meta http-equiv="Expires" content="0" />
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>SISEDU</title>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <link href="${pageContext.request.contextPath}/css/default.css"  rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/menuDesplegable.css"  rel="stylesheet"/>        
        <script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/js/cargarMenus2.js"></script>
        <script src="${pageContext.request.contextPath}/js/obtenerReporte.js"></script>
        <link href="${pageContext.request.contextPath}/css/tablasReporte.css"  rel="stylesheet"/>

    </head>

    <body>
        <div id="header">
            <!--TRAER EL MENU DESDE JQUERY -->
        </div>
        <!--end header -->
        <div id="logo">
            <div id="logoTitulo">

                <table border="0">
                    <tbody>
                        <tr>
                            <td>
                                <h2><label style="text-transform: uppercase" class="bannertext">
                                        REPORTE GERENCIAL
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

        <!-- start page -->
        <div id="page">
            <!-- start content -->
            <br>
            <br>               
            <div id="content">
                <div align="left">
                    <div style="float: left;width: 91%;">
                        <label>AÑO ACADEMICO :</label> 
                        <select id="cboAñoAcademico" style="height: 20px;width: 100px;">
                            <option value="1">2016</option>
                            <option value="2" selected>2017</option>
                        </select>                    
                    </div>                    
                    <div style="float: left;width: 9%;">
                        <button id="btnVerReporteGrado" type="submit" style="height: 30px;float: left;width: 100%;">Buscar
                            <img title="Buscar" src="${pageContext.request.contextPath}/images/icon_Search.png"/>
                        </button> 
                    </div>
                </div>
                <br>
                <br>
                <br>

                <div id="DivRoot" align="left">
                    <div style="overflow: hidden;" id="DivHeaderRow">
                    </div>
                    <div>
                        <table style ="color: #000000;">
                            <tr>
                                <td>Es :</td>
                                <td>Monto Esperado</td>
                            </tr>
                            <tr>
                                <td>Re :</td>
                                <td>Monto Real</td>
                            </tr>
                            <tr>
                                <td>Fa :</td>
                                <td>Monto Faltante</td>
                            </tr>                        
                        </table>
                    </div>
                    <br>
                    <br>
                    <div style="overflow:auto;" onscroll="OnScrollDiv(this)" class="table-titleReporte">
                        <table  border=1 class="table-fillReporte">
                            <thead>
                                <tr>
                                    <th  bgcolor="#89B1CB" class="text-center" style="padding-left: 5px;margin-left: 5px;margin-right: 5px;padding-right: 5px;">Grado</th>
                                    <th  bgcolor="#89B1CB" class="text-center" colspan="3">VAC</th>                                    
                                    <th  bgcolor="#89B1CB" class="text-center" colspan="3">Mat</th>
                                    <th  bgcolor="#89B1CB" class="text-center" colspan="3">Mar</th>
                                    <th  bgcolor="#89B1CB" class="text-center" colspan="3">Abr</th>
                                    <th  bgcolor="#89B1CB" class="text-center" colspan="3">May</th>
                                    <th  bgcolor="#89B1CB" class="text-center" colspan="3">Jun</th>                                
                                    <th  bgcolor="#89B1CB" class="text-center" colspan="3">Jul</th>                                
                                    <th  bgcolor="#89B1CB" class="text-center" colspan="3">Ago</th>                                
                                    <th  bgcolor="#89B1CB" class="text-center" colspan="3">Set</th>                                                                
                                    <th  bgcolor="#89B1CB" class="text-center" colspan="3">Oct</th>
                                    <th  bgcolor="#89B1CB" class="text-center" colspan="3">Nov</th> 
                                    <th  bgcolor="#89B1CB" class="text-center" colspan="3">Dic</th> 
                                </tr>
                            </thead>
                            <tbody id="tblAlumnosDeudas">

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