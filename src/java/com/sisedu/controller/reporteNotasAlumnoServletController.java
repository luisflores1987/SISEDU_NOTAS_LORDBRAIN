/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.controller;

import com.sisedu.model.DAO.ReporteNotasAlumnoDAO;
import com.sisedu.model.bean.Alumno_Curso;
import com.sisedu.model.bean.Grado;
import com.sisedu.model.bean.Nivel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MAQ
 */
public class reporteNotasAlumnoServletController extends HttpServlet {

    ReporteNotasAlumnoDAO reporteNotas = new ReporteNotasAlumnoDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action.equals("ReporteNotasAlumno")) {
            reporteNotasAlumno(request, response);
        } else if (action.equals("obtenerGrado")) {
            obtenerGrado(request, response); 
        } else if (action.equals("obtenerNivelxGrado")) {
            obtenerNivelxGrado(request, response);
        } else if (action.equals("ReporteNotasAlumnoFinal")) {
            ReporteNotasAlumnoFinal(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

    private void reporteNotasAlumno(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sNombres = request.getParameter("nombres");
        int nIdBimestre = Integer.valueOf(request.getParameter("IdBimestre"));
        String sFiltro = request.getParameter("filtro");
        String sIdGrado = (request.getParameter("IdGrado").equalsIgnoreCase(null) || request.getParameter("IdGrado").equals("")) ? null : request.getParameter("IdGrado");

        List<Alumno_Curso> listAlumno = reporteNotas.obtenerAlumnosxBimestre(sNombres, nIdBimestre, sFiltro, sIdGrado);
        
        StringBuilder sb = new StringBuilder();
        for (Alumno_Curso ac : listAlumno) {
            sb.append(
                    ac.getAlumno().getnId_Alumno() + "*"
                    + ac.getBimestre().getnIdBimestre() + "*"
                    + ac.getAlumno().getPersona().getnNumeroDocumento() + "*"
                    + ac.getAlumno().getPersona().getsDatosPersonales() + "*"
                    + ac.getAlumno().getNivel().getsDescripcion() + "*"
                    + ac.getAlumno().getGrado().getsDescripcion() + "*"
                    + ac.getAlumno().getNivel().getnIdNivel() + "+");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void obtenerGrado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Grado> Listgrado = reporteNotas.obtenerGrado();
        StringBuilder sb = new StringBuilder();
        for (Grado g : Listgrado) {
            sb.append(
                    g.getnIdGrado() + "*"
                    + g.getsDescripcion() + "+");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        out.print(sb);        
    }

    private void obtenerNivelxGrado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int nIdGrado = Integer.valueOf(request.getParameter("idGrado"));
        List<Nivel> Listnivel = reporteNotas.obtenerNivelxGrado(nIdGrado);
        StringBuilder sb = new StringBuilder();
        for (Nivel n : Listnivel) {
            sb.append(
                    n.getnIdNivel() + "*"
                    + n.getsDescripcion() + "+");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);           
    }

    private void ReporteNotasAlumnoFinal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sNombres = request.getParameter("nombres");
        int nIdBimestre = Integer.valueOf(request.getParameter("IdBimestre"));
        String sFiltro = request.getParameter("filtro");
        String sIdGrado = (request.getParameter("IdGrado").equalsIgnoreCase(null) || request.getParameter("IdGrado").equals("")) ? null : request.getParameter("IdGrado");

        List<Alumno_Curso> listAlumno = reporteNotas.obtenerAlumnosxBimestre(sNombres, nIdBimestre, sFiltro, sIdGrado);

        StringBuilder sb = new StringBuilder();
        for (Alumno_Curso ac : listAlumno) {
            sb.append(
                    ac.getAlumno().getnId_Alumno() + "*"
                    + ac.getBimestre().getnIdBimestre() + "*"
                    + ac.getAlumno().getPersona().getnNumeroDocumento() + "*"
                    + ac.getAlumno().getPersona().getsDatosPersonales() + "*"
                    + ac.getAlumno().getNivel().getsDescripcion() + "*"
                    + ac.getAlumno().getGrado().getsDescripcion() + "*"
                    + ac.getAlumno().getNivel().getnIdNivel() + "+");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }
}
