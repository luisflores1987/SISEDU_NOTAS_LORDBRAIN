/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.controller;

import com.sisedu.model.DAO.habilitarNotasDAO;
import com.sisedu.model.bean.Curso;
import com.sisedu.model.bean.Grado;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MAQ
 */
public class habilitarNotaServletController extends HttpServlet {

    habilitarNotasDAO habilitarNotas = new habilitarNotasDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("obtenerGrado")) {
            obtenerGrado(request, response);
        } else if (action.equalsIgnoreCase("habilitarNotasTotal")) {
            habilitarNotasTotal(request, response);
        } else if (action.equalsIgnoreCase("obtenerFechas")) {
            obtenerFechas(request, response);
        } else if (action.equalsIgnoreCase("habilitarNotasDetalle")) {
            habilitarNotasDetalle(request, response);
        } else if (action.equalsIgnoreCase("habilitarNotasProfesor")) {
            habilitarNotasProfesor(request, response);
        } else {
            obtenerCurso(request, response);
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

    private void obtenerGrado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String sIdPersona = request.getParameter("IdPersona") == null ? "" : request.getParameter("IdPersona");
        List<Grado> listaGrado = habilitarNotas.obtenerGrado(sIdPersona);
        StringBuilder sb = new StringBuilder("");
        for (Grado g : listaGrado) {
            sb.append(
                    g.getnIdGrado() + "-"
                    + g.getsDescripcion() + ":");
        }
        response.setContentType("text/plain");
        out.print(sb);
    }

    private void obtenerCurso(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String sIdPersona = request.getParameter("IdPersona") == null ? "" : request.getParameter("IdPersona");
        String sIdGrado = request.getParameter("IdGrado") == null ? "" : request.getParameter("IdGrado");

        List<Curso> listaCurso = habilitarNotas.obtenerCursoPorProfesorGrado(sIdPersona, sIdGrado);
        StringBuilder sb = new StringBuilder("");
        for (Curso c : listaCurso) {
            sb.append(
                    c.getnIdCurso() + "-"
                    + c.getsDescripcion() + ":");
        }
        response.setContentType("text/plain");
        out.print(sb);
    }

    private void habilitarNotasTotal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String sFechaCierreInicial = request.getParameter("sFechaCierreInicial") == null ? "" : request.getParameter("sFechaCierreInicial");
        String sFechaCierreFinal = request.getParameter("sFechaCierreFinal") == null ? "" : request.getParameter("sFechaCierreFinal");
        int nTipoNota = request.getParameter("bTipoNota") == null ? 0 : Integer.valueOf(request.getParameter("bTipoNota"));
        String sIdBimestre = request.getParameter("sIdBimestre") == null ? "" : request.getParameter("sIdBimestre");



        int nHabilitar = habilitarNotas.obtenerHabilitacionNotas(sFechaCierreInicial, sFechaCierreFinal, nTipoNota, sIdBimestre);
        StringBuilder sb = new StringBuilder("");
        sb.append(nHabilitar);
        response.setContentType("text/plain");
        out.print(sb);
    }

    private void habilitarNotasDetalle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String sIdAlumno = request.getParameter("idAlumno") == null ? "" : request.getParameter("idAlumno");
        int nIdCurso = request.getParameter("idCurso") == null ? 0 : Integer.valueOf(request.getParameter("idCurso"));
        String sIdBimestre = request.getParameter("idBimestre") == null ? "" : request.getParameter("idBimestre");
        Boolean bHabilitar = request.getParameter("Habilitar") == null ? false : Boolean.valueOf(request.getParameter("Habilitar"));
        int nTipoNota = request.getParameter("TipoNota") == null ? 0 : Integer.valueOf(request.getParameter("TipoNota"));

        int nHabilitar = habilitarNotas.obtenerHabilitacionNotasDetalle(nTipoNota, nIdCurso, sIdBimestre, sIdAlumno, bHabilitar);
        StringBuilder sb = new StringBuilder("");
        sb.append(nHabilitar);
        response.setContentType("text/plain");
        out.print(sb);
    }

    private void obtenerFechas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String sTipoNota = request.getParameter("tipoNota") == null ? "" : request.getParameter("tipoNota");
        List<String> lstResultado = habilitarNotas.obtenerListadoFechas(sTipoNota);
        StringBuilder sb = new StringBuilder("");
        for (String s : lstResultado) {
            sb.append(s + ":");
        }
        response.setContentType("text/plain");
        out.println(sb);
    }

    private void habilitarNotasProfesor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String sIdPersonaProfesor = request.getParameter("idPersonaProfesor") == null ? "" : request.getParameter("idPersonaProfesor");
        int nIdGrado = request.getParameter("idGrado") == null || request.getParameter("idGrado") == "" ? 0 : Integer.valueOf(request.getParameter("idGrado"));
        String sIdCurso = request.getParameter("idCurso") == null ? "" : request.getParameter("idCurso");
        String sIdBimestre = request.getParameter("idBimestre") == null ?"" : request.getParameter("idBimestre");
        int nIdNota = request.getParameter("idNota") == null || request.getParameter("idNota") == "" ? 0 : Integer.valueOf(request.getParameter("idNota"));
        String sBloquear = request.getParameter("Bloquear") == null ? "": request.getParameter("Bloquear");
        
        int nHabilitar = habilitarNotas.obtenerHabilitacionNotasProfesor(sIdPersonaProfesor, nIdGrado, sIdCurso, sIdBimestre, nIdNota, sBloquear);
        StringBuilder sb = new StringBuilder("");
        sb.append(nHabilitar);
        response.setContentType("text/plain");
        out.print(sb);        
        
    }
}
