
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.controller;

import com.sisedu.model.DAO.obtenerReporteAsistenciaDAO;
import com.sisedu.model.bean.Horario;
import com.sisedu.model.bean.Profesor;
import com.sisedu.model.bean.ProfesorHorario;
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
public class obtenerReporteAsistenciaServletController extends HttpServlet {

    obtenerReporteAsistenciaDAO obtenerReporteAsistencia = new obtenerReporteAsistenciaDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("obtenerReporteAsistencia")) {
            obtenerReporteAsistencia(request, response);
        }
        if (action.equalsIgnoreCase("obtenerReporteAsistenciaConsolidado")) {
            obtenerReporteAsistenciaConsolidado(request, response);
        }
        if (action.equalsIgnoreCase("conseguirProfesores")) {
            conseguirProfesores(request, response);
        }
        if (action.equalsIgnoreCase("conseguirDiasSemana")) {
            conseguirDiasSemana(request, response);
        }
        if (action.equalsIgnoreCase("obtenerHorariosProfesor")) {
            obtenerHorariosProfesor(request, response);
        }
    }

    private void obtenerReporteAsistencia(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sNombres = (request.getParameter("txtApNom") == null ? "0" : request.getParameter("txtApNom"));
        String sFechaInicial = (request.getParameter("txtFechaInicial") == null ? "" : request.getParameter("txtFechaInicial"));
        String sFechaFinal = (request.getParameter("txtFechaFin") == null ? "" : request.getParameter("txtFechaFin"));

        boolean bFecha = Boolean.valueOf(request.getParameter("FECHA"));
        boolean bApenom = Boolean.valueOf(request.getParameter("APNOM"));

        List<ProfesorHorario> listaBoleta = obtenerReporteAsistencia.obtenerReporteAsistenciaProfesor(bApenom, bFecha, sNombres, sFechaInicial, sFechaFinal);
        StringBuilder sb = new StringBuilder("");
        for (ProfesorHorario lph : listaBoleta) {
            sb.append(
                    lph.getProfesor().getPersona().getnIdPersona() + "*"
                    + lph.getProfesor().getnId_Profesor() + "*"
                    + lph.getProfesor().getPersona().getsDatosPersonales() + "*"
                    + lph.getProfesor().getAsistenciaProfesor().getsHoraRegistro().substring(0, 10) + "*"
                    + lph.getProfesor().getAsistenciaProfesor().getsHoraRegistro().substring(11, 19) + "*"
                    + lph.getsHoraIngreso() + "*"
                    + lph.getProfesor().getfCantidad() + "+");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void obtenerReporteAsistenciaConsolidado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sFechaInicial = (request.getParameter("txtFechaInicial") == null ? "" : request.getParameter("txtFechaInicial"));
        String sFechaFinal = (request.getParameter("txtFechaFin") == null ? "" : request.getParameter("txtFechaFin"));
        Boolean bFecha = Boolean.valueOf(request.getParameter("chkFecha") == null ? "0" : request.getParameter("chkFecha"));
        Boolean bProfesor = Boolean.valueOf(request.getParameter("chkProfesor") == null ? "0" : request.getParameter("chkProfesor"));
        String sProfesor = (request.getParameter("cbProfesor") == null ? "0" : request.getParameter("cbProfesor"));

        List<Profesor> listaBoleta = obtenerReporteAsistencia.obtenerReporteAsistenciaProfesorConsolidado(bFecha, bProfesor, sFechaInicial, sFechaFinal, sProfesor);
        StringBuilder sb = new StringBuilder("");
        for (Profesor lp : listaBoleta) {
            sb.append(
                    lp.getnId_Profesor() + "*"
                    + lp.getPersona().getsDatosPersonales() + "*"
                    + lp.getfCantidad() + "+");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void conseguirProfesores(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Profesor> listaBoleta = obtenerReporteAsistencia.conseguirProfesores();
        StringBuilder sb = new StringBuilder("");
        for (Profesor lp : listaBoleta) {
            sb.append(
                    lp.getPersona().getnIdPersona() + "*"
                    + lp.getPersona().getsDatosPersonales() + "+");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void conseguirDiasSemana(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Horario> listaHorario = obtenerReporteAsistencia.conseguirHorario();
        StringBuilder sb = new StringBuilder("");
        for (Horario lh : listaHorario) {
            sb.append(
                    lh.getnIdHorario() + "*"
                    + lh.getsDescripcion() + "+");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void obtenerHorariosProfesor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Boolean bProfesor = Boolean.valueOf(request.getParameter("cbProfesor"));
        Boolean bDiaSemana = Boolean.valueOf(request.getParameter("cbDiaSemana"));
        String sIdPersona = request.getParameter("txtIdPersona");
        String sIdDiaSemana = request.getParameter("txtIdDiaSemana");
        List<ProfesorHorario> listaHorarioProfesor = obtenerReporteAsistencia.conseguirHorarioProfesor(bProfesor, bDiaSemana, sIdPersona, sIdDiaSemana);
        StringBuilder sb = new StringBuilder("");
        for (ProfesorHorario lph : listaHorarioProfesor) {
            sb.append(
                    lph.getProfesor().getPersona().getnIdPersona() + "*"            
                    + lph.getProfesor().getPersona().getsDatosPersonales() + "*"
                    + lph.getHorario().getsDescripcion() + "*"
                    + lph.getsHoraIngreso() + "*"
                    + lph.getsHoraSalida() + "+"
                    );
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
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
}
