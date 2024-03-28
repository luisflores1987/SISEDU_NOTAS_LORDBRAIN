/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.controller;

import com.sisedu.model.DAO.LoginDAO;
import com.sisedu.model.bean.Persona;
import com.sisedu.model.bean.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MAQ
 */
@WebServlet(name = "cookiesServletController", urlPatterns = {"/Formularios/cookiesServletController"})
public class cookiesServletController extends HttpServlet {

    LoginDAO login = new LoginDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("TraerData")) {
            TraerData(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(43200);
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

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void TraerData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        StringBuilder sb = new StringBuilder();
        sb.append(session.getAttribute("ntipoAcceso"));
        out.print(sb);
    }
}
