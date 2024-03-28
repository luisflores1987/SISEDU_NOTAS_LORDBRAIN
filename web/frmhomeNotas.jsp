<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : frmhomeNotas
    Created on : 12/04/2016, 09:40:54 AM
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

        <script>
            jQuery(document).ready(function ($) {
            
                var jssor_1_SlideshowTransitions = [
                    {$Duration:1200,x:-0.3,$During:{$Left:[0.3,0.7]},$Easing:{$Left:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
                    {$Duration:1200,x:0.3,$SlideOut:true,$Easing:{$Left:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2}
                ];
            
                var jssor_1_options = {
                    $AutoPlay: true,
                    $SlideshowOptions: {
                        $Class: $JssorSlideshowRunner$,
                        $Transitions: jssor_1_SlideshowTransitions,
                        $TransitionsOrder: 1
                    },
                    $ArrowNavigatorOptions: {
                        $Class: $JssorArrowNavigator$
                    },
                    $BulletNavigatorOptions: {
                        $Class: $JssorBulletNavigator$
                    },
                    $ThumbnailNavigatorOptions: {
                        $Class: $JssorThumbnailNavigator$,
                        $Cols: 1,
                        $Align: 0
                    }
                };
            
                var jssor_1_slider = new $JssorSlider$("jssor_1", jssor_1_options);
            
                //responsive code begin
                //you can remove responsive code if you don't want the slider scales while window resizing
                function ScaleSlider() {
                    var refSize = jssor_1_slider.$Elmt.parentNode.clientWidth;
                    if (refSize) {
                        refSize = Math.min(refSize, 600);
                        jssor_1_slider.$ScaleWidth(refSize);
                    }
                    else {
                        window.setTimeout(ScaleSlider, 30);
                    }
                }
                ScaleSlider();
                $(window).bind("load", ScaleSlider);
                $(window).bind("resize", ScaleSlider);
                $(window).bind("orientationchange", ScaleSlider);
                //responsive code end
            });
        </script> 

        <style>

            /* jssor slider bullet navigator skin 01 css */
            /*
            .jssorb01 div           (normal)
            .jssorb01 div:hover     (normal mouseover)
            .jssorb01 .av           (active)
            .jssorb01 .av:hover     (active mouseover)
            .jssorb01 .dn           (mousedown)
            */
            .jssorb01 {
                position: absolute;
            }
            .jssorb01 div, .jssorb01 div:hover, .jssorb01 .av {
                position: absolute;
                /* size of bullet elment */
                width: 12px;
                height: 12px;
                filter: alpha(opacity=70);
                opacity: .7;
                overflow: hidden;
                cursor: pointer;
                border: #000 1px solid;
            }
            .jssorb01 div { background-color: gray; }
            .jssorb01 div:hover, .jssorb01 .av:hover { background-color: #d3d3d3; }
            .jssorb01 .av { background-color: #fff; }
            .jssorb01 .dn, .jssorb01 .dn:hover { background-color: #555555; }

            /* jssor slider arrow navigator skin 05 css */
            /*
            .jssora05l                  (normal)
            .jssora05r                  (normal)
            .jssora05l:hover            (normal mouseover)
            .jssora05r:hover            (normal mouseover)
            .jssora05l.jssora05ldn      (mousedown)
            .jssora05r.jssora05rdn      (mousedown)
            */
            .jssora05l, .jssora05r {
                display: block;
                position: absolute;
                /* size of arrow element */
                width: 40px;
                height: 40px;
                cursor: pointer;
                background: url('${pageContext.request.contextPath}/images/HomeAsistencia/a17.png') no-repeat;
                overflow: hidden;
            }
            .jssora05l { background-position: -10px -40px; }
            .jssora05r { background-position: -70px -40px; }
            .jssora05l:hover { background-position: -130px -40px; }
            .jssora05r:hover { background-position: -190px -40px; }
            .jssora05l.jssora05ldn { background-position: -250px -40px; }
            .jssora05r.jssora05rdn { background-position: -310px -40px; }

            /* jssor slider thumbnail navigator skin 09 css */

            .jssort09-600-45 .p {
                position: absolute;
                top: 0;
                left: 0;
                width: 600px;
                height: 45px;
            }

            .jssort09-600-45 .t {
                font-family: verdana;
                font-weight: normal;
                position: absolute;
                width: 100%;
                height: 100%;
                top: 0;
                left: 0;
                color:#fff;
                line-height: 45px;
                font-size: 20px;
                padding-left: 10px;
            }

        </style> 



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

        <script src="${pageContext.request.contextPath}/js/cargarMenus.js"></script>

    </head>
    <body>

        <div id="header">
            <!--TRAER EL MENU DESDE JQUERY -->
        </div>
        <!--end header -->
        <div id="logo">
            <div id="logoTitulo" align ="left">
                BIENVENIDOS A "SISEDU" GESTION DE NOTAS
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

        <!--<div id="banner"></div>-->

        <!-- start page -->
        <div id="page">
            <!-- start content -->
            <br>
            <br>               
            <div id="content">
                <div class="post">

                    <div id="jssor_1" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width: 600px; height: 300px; overflow: hidden; visibility: hidden;">
                        <!-- Loading Screen -->
                        <div data-u="loading" style="position: absolute; top: 0px; left: 0px;">
                            <div style="filter: alpha(opacity=70); opacity: 0.7; position: absolute; display: block; top: 0px; left: 0px; width: 100%; height: 100%;"></div>
                            <div style="position:absolute;display:block;background:url('${pageContext.request.contextPath}/images/HomeAsistencia/loading.gif') no-repeat center center;top:0px;left:0px;width:100%;height:100%;"></div>
                        </div>
                        <div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 600px; height: 300px; overflow: hidden;">
                            <div data-p="112.50" style="display: none;">
                                <img data-u="image" src="${pageContext.request.contextPath}/images/HomeAsistencia/01.jpg" />
                                <div data-u="thumb"></div>
                            </div>
                            <div data-p="112.50" style="display: none;">
                                <img data-u="image" src="${pageContext.request.contextPath}/images/HomeAsistencia/02.jpg" />
                                <div data-u="thumb"></div>
                            </div>
                            <div data-p="112.50" style="display: none;">
                                <img data-u="image" src="${pageContext.request.contextPath}/images/HomeAsistencia/03.jpg" />
                                <div data-u="thumb"></div>
                            </div>
                            <div data-p="112.50" style="display: none;">
                                <img data-u="image" src="${pageContext.request.contextPath}/images/HomeAsistencia/04.jpg" />
                                <div data-u="thumb"></div>
                            </div>
                        </div>
                        <!-- Thumbnail Navigator -->
                        <div data-u="thumbnavigator" class="jssort09-600-45" style="position:absolute;bottom:0px;left:0px;width:600px;height:45px;">
                            <div style="position: absolute; top: 0; left: 0; width: 100%; height:100%; background-color: #000; filter:alpha(opacity=40.0); opacity:0.4;"></div>
                            <!-- Thumbnail Item Skin Begin -->
                            <div data-u="slides" style="cursor: default;">
                                <div data-u="prototype" class="p">
                                    <div data-u="thumbnailtemplate" class="t"></div>
                                </div>
                            </div>
                            <!-- Thumbnail Item Skin End -->
                        </div>
                        <!-- Bullet Navigator -->
                        <div data-u="navigator" class="jssorb01" style="bottom:16px;right:16px;">
                            <div data-u="prototype" style="width:12px;height:12px;"></div>
                        </div>
                        <!-- Arrow Navigator -->
                        <span data-u="arrowleft" class="jssora05l" style="top:0px;left:8px;width:40px;height:40px;" data-autocenter="2"></span>
                        <span data-u="arrowright" class="jssora05r" style="top:0px;right:8px;width:40px;height:40px;" data-autocenter="2"></span>
                        <a href="http://www.jssor.com" style="display:none">Slideshow Maker</a>
                    </div>
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
        <p id="legal">( c ) 2016. All Rights Reserved. <a href="http://www.lordbrain.edu.pe/">Lord Brain</a> designed by <a href="http://www.apiservi.com/">APISERVI SAC</a>.</p>
    </div>
    <!-- end footer -->      
</body>
</html>
