/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.controller;

import com.sisedu.model.DAO.obtenerReporteDAO;
import com.sisedu.model.bean.AlumnoDeuda;
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
@WebServlet(name = "obtenerReporteServletController", urlPatterns = {"/Formularios/obtenerReporteServletController"})
public class obtenerReporteServletController extends HttpServlet {

    obtenerReporteDAO obtenerReporte = new obtenerReporteDAO();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("obtenerReporteGrado"))
        {
            obtenerReporteGrado(request, response);
        }
    }
    
    private void obtenerReporteGrado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        int nTipoTransaccion = 1;
        String sAñoAcademico = request.getParameter("anioAcademico") == null?"": request.getParameter("anioAcademico");        

        List<AlumnoDeuda> listaAlumnoDeuda = obtenerReporte.obtenerReporteGrado(nTipoTransaccion, sAñoAcademico);
        StringBuilder sb = new StringBuilder("");
        for (AlumnoDeuda lb : listaAlumnoDeuda) {
            String[] vacacional = lb.getVacacional().split(",");            
            String[] matricula = lb.getMatricula().split(",");
            String[] marzo = lb.getMarzo().split(",");
            String[] abril = lb.getAbril().split(",");
            String[] mayo = lb.getMayo().split(",");
            String[] junio = lb.getJunio().split(",");
            String[] julio = lb.getJulio().split(",");
            String[] agosto = lb.getAgosto().split(",");
            String[] setiembre = lb.getSetiembre().split(",");
            String[] octubre = lb.getOctubre().split(",");
            String[] noviembre = lb.getNoviembre().split(",");
            String[] diciembre = lb.getDiciembre().split(",");            
            
            
            sb.append(
                    lb.getAlumno().getGrado().getsDescripcion() + "-"  // 1
                    + vacacional[0] + "-"                              // 2 
                    + vacacional[1] + "-"                              // 3 
                    + matricula[0] + "-"                               // 4         
                    + matricula[1] + "-"                               // 5 
                    + marzo[0] + "-"                                   // 6
                    + marzo[1] + "-"                                   // 7
                    + abril[0] + "-"                                   // 8  
                    + abril[1] + "-"                                   // 9 
                    + mayo[0] + "-"                                    // 10
                    + mayo[1] + "-"                                    // 11
                    + junio[0] + "-"                                   // 12 
                    + junio[1] + "-"                                   // 13
                    + julio[0] + "-"                                   // 14
                    + julio[1] + "-"                                   // 15 
                    + agosto[0] + "-"                                  // 16 
                    + agosto[1] + "-"                                  // 17
                    + setiembre[0] + "-"                               // 18 
                    + setiembre[1] + "-"                               // 19 
                    + octubre[0] + "-"                                 // 20 
                    + octubre[1] + "-"                                 // 21 
                    + noviembre[0] + "-"                               // 22 
                    + noviembre[1] + "-"                               // 23 
                    + diciembre[0] + "-"                               // 24 
                    + diciembre[1]                                     // 25 
                    + ":");
   
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
