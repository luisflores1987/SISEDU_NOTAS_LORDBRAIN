/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.controller;

import com.sisedu.model.DAO.ObtenerNuevosRegistrosDAO;
import com.sisedu.model.bean.Profesor;
import com.sisedu.model.bean.ProfesorHorario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MAQ
 */
@WebServlet(name = "obtenerNuevosRegistros", urlPatterns = {"/Formularios/obtenerNuevosRegistros"})
public class obtenerNuevosRegistros extends HttpServlet {

    ObtenerNuevosRegistrosDAO obtenerRegistro = new ObtenerNuevosRegistrosDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("obtenerAsistencia")) {
            obtenerAsistencia(request, response);
        }
        if (action.equalsIgnoreCase("colocarAsistencia")) {
            colocarAsistencia(request, response);
        }

        if (action.equalsIgnoreCase("obtenerDatosAsistencia")) {
            obtenerDatosAsistencia(request, response);
        }

        if (action.equalsIgnoreCase("colocarAsistenciaManual")) {
            colocarAsistenciaManual(request, response);
        }
    }

    private void obtenerAsistencia(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String codigoAsistencia = obtenerRegistro.obtenerRegistro();
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(codigoAsistencia);
    }

    private void colocarAsistencia(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sCodigo = request.getParameter("sCodigo");
        String codigoAsistencia = obtenerRegistro.colocarAsistencia(sCodigo);
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(codigoAsistencia);
    }

    private void obtenerDatosAsistencia(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sCodigo = request.getParameter("sCodigo");

        List<ProfesorHorario> listaProfesor = obtenerRegistro.obtenerProfesorAsistencia(sCodigo);
        StringBuilder sb = new StringBuilder("");
        for (ProfesorHorario Proh : listaProfesor) {
            sb.append(
                    Proh.getProfesor().getPersona().getnIdPersona() + "+" //1
                    + Proh.getProfesor().getPersona().getsDatosPersonales() + "+" //2
                    + Proh.getProfesor().getnCodigo() + "+" //3
                    + Proh.getProfesor().getsMensaje() + "+" //4
                    + Proh.getProfesor().getsColorMensaje() + "+" //5
                    + Proh.getsHoraIngreso() + "+" //5
                    + Proh.getProfesor().getAsistenciaProfesor().getsHoraRegistro() + "*");    //6
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void colocarAsistenciaManual(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sCodigo = request.getParameter("sCodigo");
        String[] sMensaje = new String[2];
        sMensaje = obtenerRegistro.colocarAsistenciaManual(sCodigo);
        StringBuilder sb = new StringBuilder("");
        for(String sm : sMensaje){
            sb.append(sm + "+");
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
