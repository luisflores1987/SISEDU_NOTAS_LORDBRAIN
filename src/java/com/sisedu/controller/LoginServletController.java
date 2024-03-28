/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.controller;

import com.sisedu.model.DAO.LoginDAO;
import com.sisedu.model.bean.Grado;
import com.sisedu.model.bean.Nivel;
import com.sisedu.model.bean.Persona;
import com.sisedu.model.bean.Usuario;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServletController", urlPatterns = {"/LoginServletController"})
public class LoginServletController extends HttpServlet {

    LoginDAO login = new LoginDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60);
        if (action == null) {
            Usuario usuario = checkCookie(request);
            if (usuario == null) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                if (login.obtenerUsuarios(usuario.getsNombreUsuario(), usuario.getsPassword())) {
                    session.removeAttribute("sMensaje");
                    for (Persona persona : login.getListaPersona()) {;
                        session.setAttribute("nIdUsuario", persona.getnIdPersona());
                        session.setAttribute("sNombreUsuario", persona.getsDatosPersonales());
                        session.setAttribute("ntipoAcceso", persona.getnIdTipoAcceso());
                    }
                    //request.getRequestDispatcher("/Formularios/frmhome.jsp").forward(request, response);
                    if (session.getAttribute("ntipoAcceso").toString().equals("3")) {
                        request.getRequestDispatcher("frmhomeAsistencia.jsp").forward(request, response);
                    }else if (session.getAttribute("ntipoAcceso").toString().equals("5")) {
                        request.getRequestDispatcher("frmhomeNotas.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("frmhome.jsp").forward(request, response);
                    }

                } else {
                    session.setAttribute("sMensaje", "La contraseña y/o usuario es incorrecto...");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }
        } else {
            if (action.equalsIgnoreCase("logout")) {
                //Remove Session
                session.removeAttribute("nIdUsuario");
                session.removeAttribute("sNombreUsuario");
                session.removeAttribute("ntipoAcceso");
                //Remove Cookie
                Cookie[] cookies = request.getCookies();
                for (Cookie ck : cookies) {
                    if (ck.getName().equalsIgnoreCase("txtUsuario")) {
                        ck.setMaxAge(0);
                        response.addCookie(ck);
                    }
                    if (ck.getName().equalsIgnoreCase("txtPassword")) {
                        ck.setMaxAge(0);
                        response.addCookie(ck);
                    }
                }
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }

    private Usuario checkCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Usuario usuario = null;
        if (cookies == null) {
            return null;
        } else {
            String username = "", password = "";
            for (Cookie ck : cookies) {
                if (ck.getName().equalsIgnoreCase("txtUsuario")) {
                    username = ck.getValue();
                }
                if (ck.getName().equalsIgnoreCase("txtPassword")) {
                    password = ck.getValue();
                }
            }
            if (!username.isEmpty() && !password.isEmpty()) {
                usuario = new Usuario(username, password);
            }
        }
        return usuario;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = request.getSession();
        String sUsername = request.getParameter("txtUsuario");
        String sPassword = request.getParameter("txtPassword");

        if (login.obtenerUsuarios(sUsername, sPassword)) {
            // mantener iniciada la session.
            Cookie ckUsername = new Cookie("txtUsuario", sUsername);
            ckUsername.setMaxAge(3600);
            response.addCookie(ckUsername);
            Cookie ckPassword = new Cookie("txtPassword", sPassword);
            ckPassword.setMaxAge(3600);
            response.addCookie(ckPassword);
            // fin 
            session.removeAttribute("sMensaje");
            for (Persona persona : login.getListaPersona()) {;
                session.setAttribute("nIdUsuario", persona.getnIdPersona());
                session.setAttribute("sNombreUsuario", persona.getsDatosPersonales());
                session.setAttribute("ntipoAcceso", persona.getnIdTipoAcceso());
            }
            if (session.getAttribute("ntipoAcceso").toString().equals("3")) {
                request.getRequestDispatcher("frmhomeAsistencia.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("frmhome.jsp").forward(request, response);
            }
        } else {
            session.setAttribute("sMensaje", "La contraseña y/o usuario es incorrecto...");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
