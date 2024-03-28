/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.controller;

import com.sisedu.model.DAO.ConsultaDeudasDAO;
import com.sisedu.model.bean.AlumnoDeuda;
import com.sisedu.model.bean.Boleta;
import com.sisedu.util.Constantes;
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
@WebServlet(name = "consultaDeudaServletController", urlPatterns = {"/Formularios/consultaDeudaServletController"})
public class consultaDeudaServletController extends HttpServlet {

    ConsultaDeudasDAO consultasDeudas = new ConsultaDeudasDAO();

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("consultaAlumnoDeuda")) {
                consultaAlumnoDeuda(request, response);
            }
            if (action.equals("fecha")) {
                obtenerFecha(request, response);
            }
        } else {
        }
    }

    private void consultaAlumnoDeuda(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String sNivel = request.getParameter("cboNivel");
        String sGrado = request.getParameter("cboGrado");

        boolean bNivel = Boolean.valueOf(request.getParameter("NIVEL"));
        boolean bGrado = Boolean.valueOf(request.getParameter("GRADO"));
        
        String sAñoAcademico = request.getParameter("anioAcademico") == null?"": request.getParameter("anioAcademico");

        List<AlumnoDeuda> listaAlumnoDeuda = consultasDeudas.consultaDatosDeudas(bNivel, bGrado, sNivel, sGrado, sAñoAcademico);
        StringBuilder sb = new StringBuilder("");
        for (AlumnoDeuda lb : listaAlumnoDeuda) {
            sb.append(
                    lb.getAlumno().getPersona().getsDatosPersonales() + "-"
                    + lb.getMatricula() + "-"
                    + lb.getMarzo() + "-"
                    + lb.getAbril() + "-"
                    + lb.getMayo() + "-"
                    + lb.getJunio() + "-"
                    + lb.getJulio() + "-"
                    + lb.getAgosto() + "-"
                    + lb.getSetiembre() + "-"
                    + lb.getOctubre() + "-"
                    + lb.getNoviembre() + "-"
                    + lb.getDiciembre() + "-"
                    + lb.getVacacional() 
                    + ":");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void obtenerFecha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sb = consultasDeudas.conseguirFecha();
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        System.out.println("sb  " + sb);
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
