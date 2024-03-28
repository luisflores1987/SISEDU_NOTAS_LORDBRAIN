<%-- 
    Document   : index
    Created on : 19/01/2016, 10:01:22 AM
    Author     : lflores
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- General meta information -->
        <title>SISEDU</title>
        <!-- Load stylesheets -->
        <link type="text/css" rel="stylesheet" href="css/style.css" media="screen" />
        <link type="text/css" rel="stylesheet" href="css/login.css"/>
        <!-- // Load stylesheets -->
    <body>
        <div id="header">
            <!--TRAER EL MENU DESDE JQUERY -->
            <div class="logoTipo">
                <img src="${pageContext.request.contextPath}/images/logoSisedu.png" alt=""/>
            </div>   
        </div>
        <div id="wrapper">

            <div id="wrappertop"></div>
            <div id="wrappermiddle">
                <form action="LoginServletController" method="post">                    
                    <div id="username_input">

                        <div id="username_inputleft"></div>

                        <div id="username_inputmiddle">
                            <input type="text" class="txt" name="txtUsuario" id="txtUsuario" value="Usuario" onclick="this.value = ''">
                            <img id="url_user" src="./images/mailicon.png" alt="">
                        </div>

                        <div id="username_inputright"></div>

                    </div>

                    <div id="password_input">

                        <div id="password_inputleft"></div>

                        <div id="password_inputmiddle">

                            <input type="password" class="txt" name="txtPassword" id="txtPassword" value="Password" onclick="this.value = ''">

                        </div>

                        <div id="password_inputright"></div>

                    </div>

                    <div id="submit">
                        <input type="image" src="./images/submit_hover.png" id="submit1" value="Sign In">
                        <a href="./Formularios/home.jsp"><input type="image" src="./images/submit.png" id="submit2" value="Sign In"></a>
                    </div>                    
                </form>
            </div>

            <table class="tblusuario" border="0">
                <tbody>
                    <tr>
                        <td>
                            <h2>
                                <label class="Usuario bannertext" id ="txtUsuario">
                                    <c:set value="${sMensaje}" var="cadMensaje" scope="session"/>
                                    ${cadMensaje}
                                </label>
                            </h2>
                        </td>
                    </tr>

                </tbody>
            </table>

            <div id="wrapperbottom"></div>
        </div>
        <div id="pieDePagina">
            <div id="PpDerecho">
                <p>
                    <label style="text-transform: uppercase">
                        Developed by Apiservi
                    </label>
                </p>

            </div>

            <div id="PpIzquierdo">
                <p>
                    <label style="text-transform: uppercase">
                        Copyright Lord Brain 2016
                    </label>
                </p>

            </div>
        </div>
    </body>
</html>
