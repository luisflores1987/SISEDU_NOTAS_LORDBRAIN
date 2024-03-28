<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="menunav">
    <ul class="nav">
        <li><a href="${pageContext.request.contextPath}/LoginServletController">INICIO</a></li>  
        <c:forEach var="menu" items="${listaAccesoMenu}">
            <c:if test="${menu.nivelMenu == 'PADRE'}">
                <li><a href="${pageContext.request.contextPath}${menu.listaMenu.getsFormulario()}">${menu.listaMenu.getsDescripcion()}</a>
                    <ul class="navSubMenu">
                        <c:forEach var="menuHijo" items="${listaAccesoMenu}">
                            <c:if test="${menuHijo.nivelMenu == 'HIJO'}">
                                <c:if test="${menu.listaMenu.getnIdListaMenu() == menuHijo.listaMenu.getListaMenu().getnIdListaMenu()}">
                                    <li><a id="${menuHijo.listaMenu.getListaMenu().getnIdListaMenu()}" href="${pageContext.request.contextPath}${menuHijo.listaMenu.getsFormulario()}">${menuHijo.listaMenu.getsDescripcion()}</a></li>
                                </c:if>    
                            </c:if>
                        </c:forEach>
                    </ul>
                </li>
            </c:if>
        </c:forEach>
        <li><a id ="logout" href="${pageContext.request.contextPath}/LoginServletController?action=logout">CERRAR SESIÓN</a></li>
    </ul>
</div>
<div class="logoTipo">
    <img src="${pageContext.request.contextPath}/images/logoSiseduGeneralIzq.png" alt=""/>
</div>   


