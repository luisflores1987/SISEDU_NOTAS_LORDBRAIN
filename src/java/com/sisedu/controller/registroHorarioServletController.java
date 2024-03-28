/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.controller;

import com.sisedu.model.DAO.registroHorariosDAO;
import com.sisedu.model.bean.Horario;
import com.sisedu.model.bean.Profesor;
import com.sisedu.model.bean.ProfesorHorario;
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
public class registroHorarioServletController extends HttpServlet {

    List<Profesor> listaProfesor = new ArrayList<Profesor>();
    List<ProfesorHorario> listProfesorHorario = new ArrayList<ProfesorHorario>();
    registroHorariosDAO registroHorarios = new registroHorariosDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("buscarProfesor")) {
            buscarProfesor(request, response);
        }
        if (action.equalsIgnoreCase("registroHorarioProfesor")) {
            registroHorarioProfesor(request, response);
        }

        if (action.equalsIgnoreCase("obtenerHorarioProfesor")) {
            obtenerHorarioProfesor(request, response);
        }
    }

    private void buscarProfesor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipoBusqueda = request.getParameter("tipoBusqueda");
        String busqueda = ((request.getParameter("busqueda") == null) ? "" : request.getParameter("busqueda"));
        listaProfesor = registroHorarios.obtenerProfesor(tipoBusqueda, busqueda);
        StringBuilder sb = new StringBuilder("");
        for (Profesor profe : listaProfesor) {
            sb.append(
                    profe.getnId_Profesor() + "-"
                    + profe.getPersona().getnNumeroDocumento() + "-"
                    + profe.getPersona().getsDatosPersonales() + "-"
                    + ":");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void registroHorarioProfesor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sMensaje = "";
        int nTipoTransaccion = Integer.parseInt(request.getParameter("tipoTransaccion"));
        String sFechaIngreso = request.getParameter("fechaIngreso");
        String sFechaSalida = request.getParameter("fechaSalida");
        String sIdHorario = ((request.getParameter("cadIdHorario") == null) ? null : request.getParameter("cadIdHorario"));
        int nidHorario = (request.getParameter("idHorario") == null || request.getParameter("idHorario") == "") ? null : Integer.valueOf(request.getParameter("idHorario"));
        Boolean bHorario = (request.getParameter("bHorario") == null || request.getParameter("bHorario") == "") ? null : Boolean.valueOf(request.getParameter("bHorario"));
        int nIdProfesor = Integer.valueOf((request.getParameter("IdProfesor") == null) ? "0" : request.getParameter("IdProfesor"));
        sMensaje = registroHorarios.registrarHorario(nTipoTransaccion, sFechaIngreso, sFechaSalida, sIdHorario, nIdProfesor, nidHorario, bHorario);
        StringBuilder sb = new StringBuilder("");
        sb.append(sMensaje);
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void obtenerHorarioProfesor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sIdProfesor = request.getParameter("idProfesor");
        listProfesorHorario = registroHorarios.obtenerHorarioProfesor(sIdProfesor);
        StringBuilder sb = new StringBuilder("");
        for (ProfesorHorario pfh : listProfesorHorario) {
            sb.append(
                    pfh.getProfesor().getnId_Profesor() + "*"
                    + pfh.getHorario().getnIdHorario() + "*"
                    + pfh.getsHoraIngreso() + "*"
                    + pfh.getsHoraSalida()
                    + "+");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
        System.out.println("sb  --- > " + sb);
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
