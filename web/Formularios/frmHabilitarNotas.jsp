<%-- 
    Document   : frmHabilitarNotas
    Created on : 26/09/2016, 08:18:11 AM
    Author     : MAQ
    Description : Pagina para poder habilitar las notas
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
        <!--        <style>        
                    .cbo {                        
                        width: 100px;
                    }
                </style>-->
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
                $(".clsObligatorioTotal").datepicker({
                    changeMonth:true,
                    changeYear:true,
                    //                    showOn: "button",
                    //                    buttonImage: "${pageContext.request.contextPath}/images/ico.png",
                    buttonImageOnly: true,
                    showButtonPanel: true                    
                });                     
            });
        </script>        
        <script src="${pageContext.request.contextPath}/js/habilitarNotas.js"></script>
        <!-- fin Personalizados -->        
        <script  type = "text/javascript" >
            {
                if (history.forward(1)) {
                    location.replace(history.forward(1))
                }
            }
        </script>  
        <link>
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
                <div class="post">
                    <fieldset>
                        <table>
                            <tr style="height: 10px"></tr>
                            <tr>
                                <td style="text-align:center;width: 102px"><label>TIPO NOTA</label></td>  
                                <td style="width: 20px"> 
                                <td style="text-align: left">
                                    <select id="cboTipoNotaNotas" name="TIPO NOTAS" tabindex="5" class="clsObligatorioTotalTipo" style="width: 250%">
                                        <option value=""></option>
                                        <option value="1">I</option>
                                        <option value="2">II</option>
                                    </select>
                                </td>   
                            </tr>
                        </table>
                        <table>
                            <thead>
                                <tr style="height: 10px"></tr>
                                <tr>
                                    <td style="text-align: center"><label>BIMESTRE</label></td> 
                                    <td style="width: 20px">                                    
                                    </td>
                                    <td style="text-align: center"><label>F. INICIO</label></td>
                                    <td  colspan="2" style="text-align: center"><label>F. FINAL</label></td>
                                </tr>
                            </thead>                            
                            <tbody>
                                <tr style="height: 5px"></tr>
                                <tr> 
                                    <td style="width: 100px;text-align: center"><label>I BIMESTRE</label></td> 
                                    <td style="width: 20px"> 
                                    <td><input id="txtFiBimestre1" name="Fecha Inicial 1 Bimestre" class="clsObligatorioTotal" style="width: 95%;"/></td>
                                    <td  colspan="2"><input id="txtFfBimestre1" name="Fecha Final 1 Bimestre" class="clsObligatorioTotal" style="width: 95%;"/></td>                                
                                </tr>
                                <tr> 
                                    <td style="width: 100px;text-align: center"><label>II BIMESTRE</label></td>
                                    <td style="width: 20px"> 
                                    <td><input id="txtFiBimestre2" name="Fecha Inicial 2 Bimestre" class="clsObligatorioTotal" style="width: 95%;"/></td>
                                    <td  colspan="2"><input id="txtFfBimestre2" name="Fecha Final 2 Bimestre" class="clsObligatorioTotal" style="width: 95%;"/></td>                                
                                </tr>
                                <tr> 
                                    <td style="width: 100px;text-align: center"><label>III BIMESTRE</label></td>
                                    <td style="width: 20px"> 
                                    <td><input id="txtFiBimestre3" name="Fecha Inicial 3 Bimestre" class="clsObligatorioTotal" style="width: 95%;"/></td>
                                    <td  colspan="2"><input id="txtFfBimestre3" name="Fecha Final 3 Bimestre" class="clsObligatorioTotal" style="width: 95%;"/></td>                                
                                </tr>
                                <tr> 
                                    <td style="width: 100px;text-align: center"><label>IV BIMESTRE</label></td> 
                                    <td style="width: 20px"> 
                                    <td><input id="txtFiBimestre4" name="Fecha Inicial 4 Bimestre" class="clsObligatorioTotal" style="width: 95%;"/></td>
                                    <td colspan="2"><input id="txtFfBimestre4" name="Fecha Final 4 Bimestre" class="clsObligatorioTotal" style="width: 95%;"/></td>                                
                                </tr>
                                <tr style="height: 5px">
                                    <td/>
                                    <td/>
                                    <td/>                                       
                                    <td style="text-align: right">
                                        <button id="btnhabilitarTotalGuardar" type="submit" style="height: 30px;width: 69px;">ACEPTAR</button>
                                    </td>
                                    <td style="text-align: right">
                                        <button id="btnhabilitarTotalLimpiar" type="submit" style="height: 30px;width: 69px;">LIMPIAR</button>
                                    </td>
                                </tr>
                                <tr style="height: 30px"></tr>  
                            </tbody>
                        </table>                        
                    </fieldset>
                    <div>&nbsp</div>
                    <fieldset>  
                        <table> 
                            <tr style="height: 30px"></tr>
                            <tr>
                                <td><label>Profesor</label></td>
                                <td style="width: 10px"></td>                                
                                <td colspan="5">
                                    <select id="cboProfesorNotas" name="PROFESOR" tabindex="1" class="clsObligatorio" style="width: 400px">
                                    </select>
                                </td>  
                            </tr>
                            <tr>
                                <td><label>Grado</label></td>
                                <td style="width: 10px"></td>   
                                <td>
                                    <select id="cboGradoNotas" name="GRADONOTAS" tabindex="2" class="clsNoObligatorio">
                                    </select>                                    
                                </td>
                                <td  style="width: 10px;text-align: left"><label>Curso</label></td> 
                                <td colspan="4">
                                    <select id="cboCursoNotas" name="CURSONOTAS" tabindex="3" class="clsNoObligatorio" style="width: 100%">
                                    </select>                                    
                                </td>                        
                            </tr>
                            <tr>
                                <td><label>Bimestre</label></td>
                                <td  style="width: 10px"></td> 
                                <td>
                                    <select id="cboBimestreNotas" name="BIMESTRE" tabindex="4" class="clsObligatorio" style="width: 100%">
                                        <option value=""></option>
                                        <option value="1">I   BIMESTRE</option>
                                        <option value="2">II  BIMESTRE</option>
                                        <option value="3">III BIMESTRE</option>
                                        <option value="4">IV  BIMESTRE</option>
                                    </select>                                    
                                </td> 
                                <td style="text-align: left"><label>Tipo</label></td>                                                                
                                <td colspan="2">
                                    <select id="cboTipoNotaNotas" name="TIPONOTANOTAS" tabindex="5" class="clsNoObligatorio" style="width: 100%">
                                        <option value=""></option>
                                        <option value="1">NOTA I </option>
                                        <option value="2">NOTA II</option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                        <table style="width: 466px;">
                            <tr>
                                <td>
                                    <label>
                                        HABILITAR
                                    </label>
                                    <input type="radio" name="bloquear" value="0"  tabindex="6" checked="checked" />
                                </td>
                                <td>
                                    <label>
                                        DESHABILITAR
                                    </label>  
                                    <input type="radio" name="bloquear"  tabindex="7" value="1"/>   
                                </td>
                                <td/>
                                <td/>
                            </tr>
                            <tr style="height: 10px"></tr>
                            <tr>
                                <td colspan="4" style="text-align: left;">
                                    <button id="btnhabilitarDetalle" type="submit" tabindex="8" style="height: 30px;width: 75px">ACEPTAR</button>
                                </td>
                                <td style="text-align: left;width: 17px;">
                                    <button id="btnLimpiarDetalle" type="submit" tabindex="9" style="height: 30px;width: 75px">LIMPIAR</button>
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
    </body>
</html>
