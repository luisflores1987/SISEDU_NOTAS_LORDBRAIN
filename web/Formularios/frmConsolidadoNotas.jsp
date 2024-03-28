<%-- any content can be specified here e.g.: --%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sisedu.model.bean.Area"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- 
    Document   : frmConsolidadoNotas
    Created on : 12/04/2016, 01:59:24 PM
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
        <script src="${pageContext.request.contextPath}/js/ingresoNotasProfesor.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tablasRegistroNotas.css">
        <script src="${pageContext.request.contextPath}/js/consolidadoNotas.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loading.css">        
        <!-- fin Personalizados -->        
        <script  type = "text/javascript" >
            {
                if (history.forward(1)) {
                    location.replace(history.forward(1))
                }
            }
        </script> 
        <script>
            function mostrarTablas() {
                $('#mostrarConsolidado').css("display","display");
            }
        </script>
        <%
            List listaArea = request.getAttribute("lstArea") != null ? (ArrayList) request.getAttribute("lstArea") : null;
            int tamanio = listaArea == null ? 0 : listaArea.size();
            String idGrado = request.getAttribute("nIdGrado") == null ? "0" : String.valueOf(request.getAttribute("nIdGrado"));
            String idBimestre = request.getAttribute("nIdBimestre") == null ? "0" : String.valueOf(request.getAttribute("nIdBimestre"));
        %>

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
                                    </label>
                                </h2>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h2><label class="tipoAcceso" id ="lblAcceso">
                                        ${sessionScope.ntipoAcceso}
                                    </label>
                                </h2>
                            </td>
                        </tr> 
                    </tbody>
                </table>
            </div>
        </div>

        <!-- start page -->
        <div id="page">
            <!-- start content -->
            <div align="center">
                <img src="../images/32_32_Warning.png" alt="Icono de Alerta" /> <h3><label style="text-transform:uppercase">Para visualizar el consolidado anual. Seleccioné el grado y en bimestre, la opción TOTAL</label></h3>
            </div>
            <br>
            <br> 

            <form action="obtenerConsolidadoNotasServletController?action=obtenerVisorConsolidadoNotas" method="POST">
                <table>
                    <tr>
                        <td>
                            <label class="desc " id="title4">
                                GRADO
                            </label>
                        </td>
                        <td>
                            <select id="cboGrado" name="IdGrado">
                                <option selected="selected">
                                    Seleccione
                                </option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label>BIMESTRE:</label></td>
                        <td>
                            <select  style="width: 100%" id="cboBimestre" name="IdBimestre">
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>

                            <!--                            <button type="submit" style="height: 30px;" onclick="mostrarTablas()">Buscar</button>-->
                        </td>
                    </tr>
                </table>
            </form>
            <button id="btnExportarBuscarPagosAlumno" type="submit" style="height: 30px;">Exportar Excel</button> 
            <div style="clear: both;">&nbsp;</div>
            <!--            <div id="mostrarConsolidado">
            
                                            <div style="position:relative;float: left;height: auto;float:left;margin-top:5px;">
            <sql:query var ="resultAlumno" scope="page" dataSource="jdbc/siseduDesarrolloNotasWallon">
                DECLARE
                @SQL    VARCHAR(1000),
                @IDGRADO    VARCHAR(5)
                SET
                @IDGRADO = ?
                SET
                @SQL = '
                SELECT
                ROW_NUMBER() OVER(ORDER BY P.sNombreCompleto ASC) [Nº],
                P.sNombreCompleto [ALUMNOS]
                FROM
                dbo.Persona AS P
                INNER JOIN dbo.Alumno AS A ON	A.nId_Persona = P.nId_Persona
                WHERE
                A.nIdGrado = ' + @IDGRADO + '
                AND
                A.sEstado = 1
                ORDER BY
                P.sNombreCompleto'
                exec(@SQL)                        
                <sql:param value="<%=idGrado%>" />
            </sql:query>
            <table border="1">
                 column headers 
                <tr>
            <c:forEach var="columnName" items="${resultAlumno.columnNames}">
                <th valign="middle" scope="col" style="background: #897B55;"><p style="writing-mode: horizontal-lr;transform: rotate(360deg);height: 180px;font-size:9px;text-align: center;background: #897B55;margin-top: 0px;margin-bottom: 0px;"><c:out value="${columnName}"/></p></th>
            </c:forEach>
        </tr>
         column data 
            <c:forEach var="row" items="${resultAlumno.rowsByIndex}">
                <tr>
                <c:forEach var="column" items="${row}">
                    <td style="font-size: 9px;font-weight: bold;"><label><c:out value="${column}"/></label></td>
                </c:forEach>
            </tr>
            </c:forEach>
        </table>
    </div>
            <div id="divConsolidado" style="position:relative;float: left;height: auto;float:left;width:475px;overflow-x: scroll;margin-top:5px;">
                                    <div style="width:3000px;">
            <c:forEach  var = "area" items="<%=listaArea%>" varStatus="index">
                <div id="divConsolidadoNotas" style="position:relative;float: left;height: auto;float:left">
                <sql:query var="result" scope="page" dataSource="jdbc/siseduDesarrolloNotasWallon">
                    declare 
                    @attr varchar(max),
                    @columnas varchar(max),
                    @suma varchar(max),
                    @cantidad varchar(max),
                    @sql varchar(max),
                    @idbimestre	varchar(5),
                    @idarea varchar(5),
                    @idGrado varchar(5)

                    set @idarea = ?
                    set @idGrado = ?
                    set @idbimestre = ?

                    set @attr=''
                    set @columnas=''
                    SET @suma =''
                    SET @cantidad =''
                    set 
                    @sql='
                    select 
                    #columnas#,
                    ROUND(CAST(#suma# AS FLOAT)/CAST(#cantidad# AS FLOAT),2,0) [PROMEDIO]
                    from
                    (
                    SELECT
                    P.SNOMBRECOMPLETO	[PERSONA],
                    A.nId_Alumno		[NIDALUMNO],
                    C.sDescripcion		[CURSO],
                    CONVERT(FLOAT,AC.sNota) [NOTAS],
                    AC.nIdBimestre		[IDBIMESTRE],
                    A2.nIdArea			[IDAREA],
                    G.nIdGrado			[IDGRADO]
                    FROM
                    dbo.Persona AS P
                    INNER JOIN dbo.Alumno AS A ON A.nId_Persona = P.nId_Persona AND A.sEstado = 1
                    INNER JOIN dbo.Nivel AS N ON	N.nIdNivel = A.nIdNivel
                    INNER JOIN dbo.Grado AS G ON G.nIdGrado = A.nIdGrado
                    INNER JOIN dbo.Alumno_Curso AS AC ON AC.nId_Alumno = A.nId_Alumno  AND AC.sEstado = 1
                    INNER JOIN dbo.Curso AS C ON C.nIdCurso = AC.nIdCurso AND C.sEstado = 1
                    INNER JOIN dbo.Area AS A2 ON A2.nIdArea = C.nIdArea                                
                    )A pivot(
                    AVG(NOTAS) FOR [CURSO] IN  (
                    #attr#
                    )
                    ) as PVTNOTAS
                    WHERE
                    [IDGRADO] = ' + @idGrado + ' 
                    AND
                    [IDBIMESTRE] = ' + @idbimestre + '
                    AND
                    [IDAREA] = ' + @idarea  + '
                    ORDER BY
                    [PERSONA]	
                    '
                    select @attr=@attr+'['+V.[CURSO]+'],'from (    
                    SELECT
                    DISTINCT
                    C.sDescripcion		[CURSO]
                    FROM
                    dbo.Persona AS P
                    INNER JOIN dbo.Alumno AS A ON A.nId_Persona = P.nId_Persona
                    INNER JOIN dbo.Nivel AS N ON	N.nIdNivel = A.nIdNivel
                    INNER JOIN dbo.Grado AS G ON G.nIdGrado = A.nIdGrado
                    INNER JOIN dbo.Alumno_Curso AS AC ON AC.nId_Alumno = A.nId_Alumno  AND AC.sEstado = 1
                    INNER JOIN dbo.Curso AS C ON C.nIdCurso = AC.nIdCurso  AND C.sEstado = 1
                    INNER JOIN dbo.Area AS A2 ON A2.nIdArea = C.nIdArea
                    WHERE
                    G.nIdGrado = @idGrado
                    AND
                    AC.nIdBimestre = @idbimestre
                    AND
                    A2.nIdArea = @idarea
                    ) as V

                    select @columnas=@columnas+'['+ISNULL(D.[CURSO],'0')+'] AS [' + D.[CURSO] + '],'from (    
                    SELECT
                    DISTINCT
                    C.sDescripcion		[CURSO]
                    FROM
                    dbo.Persona AS P
                    INNER JOIN dbo.Alumno AS A ON A.nId_Persona = P.nId_Persona
                    INNER JOIN dbo.Nivel AS N ON	N.nIdNivel = A.nIdNivel
                    INNER JOIN dbo.Grado AS G ON G.nIdGrado = A.nIdGrado
                    INNER JOIN dbo.Alumno_Curso AS AC ON AC.nId_Alumno = A.nId_Alumno  AND AC.sEstado = 1
                    INNER JOIN dbo.Curso AS C ON C.nIdCurso = AC.nIdCurso  AND C.sEstado = 1
                    INNER JOIN dbo.Area AS A2 ON A2.nIdArea = C.nIdArea
                    WHERE
                    G.nIdGrado = @idGrado
                    AND
                    AC.nIdBimestre = @idbimestre
                    AND
                    A2.nIdArea = @idarea
                    ) as D

                    select @suma=@suma+'['+B.[CURSO]+']+'from (    
                    SELECT
                    DISTINCT
                    C.sDescripcion		[CURSO]
                    FROM
                    dbo.Persona AS P
                    INNER JOIN dbo.Alumno AS A ON A.nId_Persona = P.nId_Persona
                    INNER JOIN dbo.Nivel AS N ON	N.nIdNivel = A.nIdNivel
                    INNER JOIN dbo.Grado AS G ON G.nIdGrado = A.nIdGrado
                    INNER JOIN dbo.Alumno_Curso AS AC ON AC.nId_Alumno = A.nId_Alumno  AND AC.sEstado = 1
                    INNER JOIN dbo.Curso AS C ON C.nIdCurso = AC.nIdCurso  AND C.sEstado = 1
                    INNER JOIN dbo.Area AS A2 ON A2.nIdArea = C.nIdArea
                    WHERE
                    G.nIdGrado = @idGrado
                    AND
                    AC.nIdBimestre = @idbimestre
                    AND
                    A2.nIdArea = @idarea
                    ) as B

                    select @cantidad=B.[CANTIDAD] from (    
                    SELECT	
                    COUNT(C.CURSO) AS CANTIDAD
                    FROM
                    (SELECT
                    DISTINCT
                    C.nIdCurso	[CURSO]
                    FROM
                    dbo.Alumno AS A 
                    INNER JOIN dbo.Grado AS G ON G.nIdGrado = A.nIdGrado
                    INNER JOIN dbo.Alumno_Curso AS AC ON AC.nId_Alumno = A.nId_Alumno AND AC.sEstado = 1
                    INNER JOIN dbo.Curso AS C ON C.nIdCurso = AC.nIdCurso  AND C.sEstado = 1
                    INNER JOIN dbo.Area AS A2 ON A2.nIdArea = C.nIdArea
                    WHERE
                    G.nIdGrado = @idGrado
                    AND
                    AC.nIdBimestre = @idbimestre
                    AND
                    A2.nIdArea = @idarea
                    GROUP BY C.nIdCurso) AS C
                    ) as B

                    set @attr=SUBSTRING(@attr,0,len(@attr))
                    set @columnas=SUBSTRING(@columnas,0,len(@columnas))
                    set @suma=SUBSTRING(@suma,0,len(@suma))

                    set @sql=REPLACE(@sql,'#attr#',@attr)
                    set @sql=REPLACE(@sql,'#suma#',@suma)
                    set @sql=REPLACE(@sql,'#cantidad#',@cantidad)
                    set @sql=REPLACE(@sql,'#columnas#',@columnas)
                    exec(@sql)
                    <sql:param value="${area.nIdArea}" />
                    <sql:param value="<%=idGrado%>" />
                    <sql:param value="<%=idBimestre%>" />
                </sql:query>
                <table border="1">
                     column headers 
                    <tr>
                <c:forEach var="columnName" items="${result.columnNames}">
                    <th scope="col" style="background: #897B55;"><p style="writing-mode: vertical-lr;transform: rotate(180deg);height: 180px;font-size:9px;text-align: center;background: #897B55;/*font-weight: bold;*/"><c:out value="${columnName}"/></p></th>
                </c:forEach>
            </tr>
             column data 
                <c:forEach var="row" items="${result.rowsByIndex}">
                    <tr>
                    <c:forEach var="column" items="${row}">
                        <td style="font-size: 9px;font-weight: bold;"><label><c:out value="${column}"/></label></td>
                    </c:forEach>
                </tr>
                </c:forEach>
            </table>
        </div>
            </c:forEach> 
            <div style="position:relative;float: left;height: auto;float:left;">
            <sql:query var ="resultAlumno" scope="page" dataSource="jdbc/siseduDesarrolloNotasWallon">
                SELECT
                B.PUNTAJE,
                B.PROMEDIO,
                B.[CONDUCTA],
                B.PUESTO
                FROM
                (
                SELECT
                A.[ALUMNO],
                ROW_NUMBER() OVER(ORDER BY SUM(ROUND(CONVERT(FLOAT, A.[NOTAS]),0,0)) DESC)	[PUESTO],
                SUM(A.[NOTAS]) [PUNTAJE],
                ROUND(AVG(A.[NOTAS]),0,0) [PROMEDIO],
                ISNULL(A.[CONDUCTA], '') [CONDUCTA]			
                FROM
                (
                SELECT
                (SELECT ROUND(AVG(ROUND(CONVERT(FLOAT, sNota),0,0)),0,0) FROM dbo.Alumno_Curso WHERE nIdCurso = ac.nIdCurso AND nId_Alumno = AC.nId_Alumno AND sEstado = 1) [NOTAS],
                P.sNombreCompleto				[ALUMNO],
                ISNULL(NC.Nota,'0')															[CONDUCTA],
                AC.nId_Alumno	
                FROM
                dbo.Persona AS P
                INNER JOIN dbo.Alumno AS A1 ON	A1.nId_Persona = P.nId_Persona  AND A1.sEstado = 1
                INNER JOIN dbo.Alumno_Curso AS AC ON	AC.nId_Alumno = A1.nId_Alumno
                LEFT JOIN dbo.NotaComportamiento AS NC	ON NC.nIdAlumno = A1.nId_Alumno
                WHERE
                A1.nIdGrado = ?
                GROUP BY
                P.sNombreCompleto,
                NC.Nota,
                ac.nIdCurso,
                AC.nId_Alumno
                ) A
                GROUP BY 
                A.[ALUMNO],
                A.[CONDUCTA],
                A.nId_Alumno
                ) B
                ORDER BY
                B.[ALUMNO]
                <sql:param value="<%=idGrado%>" />
            </sql:query>
            <table border="1">
                 column headers 
                <tr>
            <c:forEach var="columnName" items="${resultAlumno.columnNames}">
                <th scope="col" style="background: #897B55;"><p style="writing-mode: vertical-lr;transform: rotate(180deg);height: 180px;font-size:9px;text-align: center;background: #897B55;/*font-weight: bold;*/"><c:out value="${columnName}"/></p></th>
            </c:forEach>
        </tr>
         column data 
            <c:forEach var="row" items="${resultAlumno.rowsByIndex}">
                <tr>
                <c:forEach var="column" items="${row}">
                    <td style="font-size: 9px;font-weight: bold;"><label><c:out value="${column}"/></label></td>
                </c:forEach>
            </tr>
            </c:forEach>
        </table>
    </div>
</div>

        </div>-->

            <!-- end content -->
            <div style="clear: both;">&nbsp;</div>
        </div>
        <!-- end page -->
        <!-- start footer -->
        <div id="footer">
            <p id="legal">( c ) 2017. All Rights Reserved. IEP. LORD BRAIN </a> designed by <a href="http://www.apiservi.com/">APISERVI SAC</a>.</p>
        </div>
        <!-- end footer --> 
        <div class='modal'></div>
    </body>
</html>
