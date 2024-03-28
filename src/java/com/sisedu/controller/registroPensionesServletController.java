/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.controller;

import com.sisedu.model.DAO.LoginDAO;
import com.sisedu.model.bean.Nivel;
import com.sisedu.model.DAO.RegistroPersonasDAO;
import com.sisedu.model.DAO.RegistroPagosDAO;
import com.sisedu.model.DAO.RegistroPensionDAO;
import com.sisedu.model.bean.Alumno;
import com.sisedu.model.bean.AlumnoDeuda;
import com.sisedu.model.bean.Boleta;
import com.sisedu.model.bean.Grado;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MAQ
 */
@WebServlet(name = "registroPensionesServletController", urlPatterns = {"/Formularios/registroPensionesServletController"})
public class registroPensionesServletController extends HttpServlet {

    LoginDAO login = new LoginDAO();
    RegistroPersonasDAO registroAlumno = new RegistroPersonasDAO();
    RegistroPensionDAO registroPension = new RegistroPensionDAO();
    RegistroPagosDAO registroPagos = new RegistroPagosDAO();

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if (action.equals("obtenerNivel")) {
            obtenerNivel(request, response);
        }
        if (action.equals("rowclick")) {
            rowclick(request, response);
        }
        if (action.equals("consultaDatos")) {
            consultaDatos(request, response);
        }
        if (action.equals("ingresarDatos")) {
            ingresarDatos(request, response);
        }
        if (action.equals("buscarPensionMes")) {
            buscarPensionMes(request, response);
        }               
    }

    private void obtenerNivel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sAñoAcademico = request.getParameter("anioAcademico") == null ? "" : request.getParameter("anioAcademico");

        List<Nivel> listaNivel = registroPension.obtenerNivelPension(sAñoAcademico);
        StringBuilder sb = new StringBuilder("");
        for (Nivel ln : listaNivel) {
            sb.append(ln.getnIdNivel() + "-" + ln.getsDescripcion() + "-" + ln.getsPension() + "-" + ln.getsAnioAcademico() + ":");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void rowclick(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sdescripcionNivel = request.getParameter("descripcionNivel");
        String sAñoAcademico = request.getParameter("anioAcademico") == null ? "" : request.getParameter("anioAcademico");
        List<Grado> listGrado = registroPension.obtenerGradoPension(sdescripcionNivel, sAñoAcademico);

        StringBuilder sb = new StringBuilder("");
        for (Grado lg : listGrado) {
            sb.append(lg.getnIdGrado() + "-" + lg.getsDescripcion() + "-" + lg.getsPension() + ":");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void consultaDatos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String paciente = request.getParameter("txtPaciente");
        int nIdNivel = Integer.valueOf(request.getParameter("nIdNivel"));
        String sAñoAcademico = request.getParameter("anioAcademico") == null ? "" : request.getParameter("anioAcademico");

        List<Alumno> listaAlumno = registroPension.consultaDatosAlumnos(paciente, nIdNivel, sAñoAcademico);
        StringBuilder sb = new StringBuilder("");
        for (Alumno alu : listaAlumno) {
            sb.append(alu.getPersona().getnIdPersona() + "-" + alu.getPersona().getnNumeroDocumento() + "-" + alu.getPersona().getsApPaterno() + "-" + alu.getPersona().getsApMaterno() + "-"
                    + alu.getPersona().getsNombreCompleto() + "-" + alu.getPersona().getsPension() + ":");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    public void ingresarDatos(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
        String sMensaje = "";
        int nIdNivel = Integer.valueOf(request.getParameter("IDNIVEL"));
        int nIdGrado = Integer.valueOf(request.getParameter("IDGRADO"));
        int nIdPersona = Integer.valueOf(request.getParameter("IDPERSONA"));
        String sPension = request.getParameter("PENSION");
        int nIdPersonaDeuda = Integer.valueOf(request.getParameter("IDPERSONADEUDA"));
        String sAñoAcademico = request.getParameter("anioAcademico") == null ? "" : request.getParameter("anioAcademico");
        sMensaje = registroPension.ingresarPensiones(nIdNivel, nIdGrado, nIdPersona, sPension, nIdPersonaDeuda, sAñoAcademico);
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sMensaje);
    }

    public void buscarPensionMes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int nIdPersona = request.getParameter("idPersona")==null?0:Integer.valueOf(request.getParameter("idPersona"));
        String sAñoAcademico = request.getParameter("anioAcademico") == null ? "" : request.getParameter("anioAcademico");
        List<AlumnoDeuda> listaAlumnoDeuda = registroPension.consultaDatosPagos(nIdPersona, sAñoAcademico);
        StringBuilder sb = new StringBuilder("");
        for (AlumnoDeuda ad : listaAlumnoDeuda) {
            //sTipoTransaccion = (lb.getsMonto().equalsIgnoreCase("") ? "1" : "0");
            sb.append(
                    ad.getDeuda().getsDescripcion() + "-" + //1
                    ad.getMonto() + "-" + //2
                    ad.getnIdAlumnoDeuda() + "-" + //3
                    ad.getAlumno().getPersona().getnIdPersona() //4
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(registroPensionesServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(registroPensionesServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
