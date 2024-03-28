package com.sisedu.controller;

import com.sisedu.model.DAO.ingresoNotasDAO;
import com.sisedu.model.bean.Alumno_Curso;
import com.sisedu.model.bean.Bimestre;
import com.sisedu.model.bean.Grado;
import com.sisedu.model.bean.Grado_Curso;
import com.sisedu.model.bean.Participacionppff;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ingresoNotasProfesorServletController extends HttpServlet {

    ingresoNotasDAO ingresoNotas = new ingresoNotasDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("obtenerGradoProfesor")) {
            obtenerGradoProfesor(request, response);
        }
        if (action.equalsIgnoreCase("obtenerCursosGradoProfesor")) {
            obtenerCursosGradoProfesor(request, response);
        }
        if (action.equalsIgnoreCase("obtenerAlumnoxCursoxGradoxProfesor")) {
            obtenerAlumnoxCursoxGradoxProfesor(request, response);
        }
        if (action.equalsIgnoreCase("registroAlumnoxCursoxGradoxProfesor")) {
            registroAlumnoxCursoxGradoxProfesor(request, response);
        }
        if (action.equalsIgnoreCase("obtenerAlumnoxTutor")) {
            obtenerAlumnoxTutor(request, response);
        }
        if (action.equalsIgnoreCase("obtenerGradoTutor")) {
            obtenerGradoTutor(request, response);
        }
        if (action.equalsIgnoreCase("ingresarNotaSugerenciaxAlumnoxTutor")) {
            ingresarNotaSugerenciaxAlumnoxTutor(request, response);
        }
        if (action.equalsIgnoreCase("obtenerParticipacionPPFF")) {
            obtenerParticipacionPPFF(request, response);
        }
        if (action.equalsIgnoreCase("obtenerBimestre")) {
            obtenerBimestre(request, response);
        }
    }

    private void obtenerGradoProfesor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sIdPersona = request.getParameter("nIdPersona");
        List<Grado> listaGrado = ingresoNotas.obtenerReporteAsistenciaProfesorConsolidado(sIdPersona);

        StringBuilder sb = new StringBuilder("");
        for (Grado grado : listaGrado) {
            sb.append(
                    grado.getnIdGrado() + "*"
                    + grado.getsDescripcion() + "+");

        }

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void obtenerCursosGradoProfesor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sIdPersona = request.getParameter("IdPersona");
        int nIdGrado = Integer.valueOf(request.getParameter("IdGrado"));
        List<Grado_Curso> listaGradoCurso = ingresoNotas.obtenerCursosxGradoxProfesor(sIdPersona, nIdGrado);

        StringBuilder sb = new StringBuilder("");
        for (Grado_Curso gradoCurso : listaGradoCurso) {
            sb.append(
                    gradoCurso.getCurso().getArea().getnIdArea() + "*"
                    + gradoCurso.getCurso().getArea().getsDescripcion() + "*"
                    + gradoCurso.getCurso().getnIdCurso() + "*"
                    + gradoCurso.getCurso().getsDescripcion() + "*"
                    + gradoCurso.getProfesor().getnId_Profesor() + "+");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void obtenerAlumnoxCursoxGradoxProfesor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int nIdCurso = Integer.valueOf(request.getParameter("IdCurso"));
        int nIdGrado = Integer.valueOf(request.getParameter("IdGrado"));
        int nIdBimestre = Integer.valueOf(request.getParameter("IdBimestre"));
        int nIdProfesor = Integer.valueOf(request.getParameter("IdProfesor"));

        List<Alumno_Curso> listaAlumnoCurso = ingresoNotas.obtenerAlumnoxCursoxGradoxProfesor(nIdGrado, nIdCurso, nIdBimestre, nIdProfesor);

        StringBuilder sb = new StringBuilder("");
        for (Alumno_Curso ac : listaAlumnoCurso) {
            sb.append(
                    ac.getAlumno().getnId_Alumno() + "*"
                    + ac.getAlumno().getPersona().getsDatosPersonales() + "*"
                    + ac.getsNota1() + "*"
                    + ac.getsNota2() + "*"
                    + ac.getAlumno().getSeccion() + "*"
                    + ac.getsSeccionGrado() + "*"
                    + ac.getnTipoAcceso() + "+");
        }

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void registroAlumnoxCursoxGradoxProfesor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int nIdAlumno = Integer.valueOf(request.getParameter("idAlumno"));
        int nIdCurso = Integer.valueOf(request.getParameter("idCurso"));
        int nIdBimestre = Integer.valueOf(request.getParameter("idBimestre"));
        String scadNotas = request.getParameter("CadenaNotas");

        String smensaje = ingresoNotas.registroAlumnoxCursoxGradoxProfesor(nIdAlumno, nIdCurso, nIdBimestre, scadNotas);
        StringBuilder sb = new StringBuilder();
        sb.append(smensaje);
        response.setContentType("type/plain");
        PrintWriter out = response.getWriter();
        out.print(out);

    }

    private void obtenerAlumnoxTutor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int nIdPersona = Integer.valueOf(request.getParameter("IdPersona"));
        int nIdBimestre = Integer.valueOf(request.getParameter("IdBimestre"));

        List<Alumno_Curso> listaAlumnoCurso = ingresoNotas.obtenerAlumnoxTutor(nIdPersona, nIdBimestre);

        StringBuilder sb = new StringBuilder("");
        for (Alumno_Curso ac : listaAlumnoCurso) {
            sb.append(
                    ac.getAlumno().getnId_Alumno() + "*"
                    + ac.getAlumno().getPersona().getsDatosPersonales() + "*"
                    + ac.getsNota1() + "*"
                    + ac.getsNota2() + "*"
                    + ac.getAlumno().getSeccion() + "*"
                    + ac.getsSeccionGrado() + "*"
                    + ac.getsTardanzas() + "*"
                    + ac.getsInasJustificadas() + "*"
                    + ac.getsInasInjustificadas() + "*"
                    + ac.getnIdParticipacionPPFF()
                    + "+");
        }

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void obtenerGradoTutor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sIdPersona = request.getParameter("nIdPersona");
        List<Grado> listaGrado = ingresoNotas.obtenerReporteTutorAlumno(sIdPersona);

        StringBuilder sb = new StringBuilder("");
        for (Grado grado : listaGrado) {
            sb.append(
                    grado.getnIdGrado() + "*"
                    + grado.getsDescripcion() + "*"
                    + grado.getsSeccion() + "+");
        }

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void obtenerParticipacionPPFF(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Participacionppff> listaParticipacion = ingresoNotas.obtenerParticipacionPPFF();

        StringBuilder sb = new StringBuilder("");
        for (Participacionppff participacion : listaParticipacion) {
            sb.append(
                    participacion.getnIdParticipacionppff() + "*"
                    + participacion.getsDescripcion() + "+");
        }

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void ingresarNotaSugerenciaxAlumnoxTutor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int nIdAlumno = Integer.valueOf(request.getParameter("IdPersona"));
        int nIdBimestre = Integer.valueOf(request.getParameter("IdBimestre"));
        String sNotasSugerencia = request.getParameter("Nota");
        String sSugerencia = request.getParameter("Sugerencia");
        String sAsistTardanza = request.getParameter("AsistTardanza");
        String sAsistJustificada = request.getParameter("AsistJustificada");
        String sAsistInjustificada = request.getParameter("AsistInjustificada");
        int nParticipacion = (request.getParameter("Participacion") == null) ? '0' : Integer.valueOf(request.getParameter("Participacion"));
        // para que el textarea, codifique las ñ y tildes
        // -----
        byte[] bytes = sSugerencia.getBytes(StandardCharsets.ISO_8859_1);
        sSugerencia = new String(bytes, StandardCharsets.UTF_8);
        // -----

        String smensaje = ingresoNotas.registroAlumnoxSugerenciaxTutor(nIdAlumno, nIdBimestre, sNotasSugerencia, sSugerencia, sAsistTardanza, sAsistJustificada, sAsistInjustificada, nParticipacion);
        StringBuilder sb = new StringBuilder();
        sb.append(smensaje);
        response.setContentType("type/plain");
        PrintWriter out = response.getWriter();
        out.print(out);
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

    private void obtenerBimestre(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Bimestre> listaBimestre = ingresoNotas.obtenerBimestre();

        StringBuilder sb = new StringBuilder("");
        for (Bimestre bimestre : listaBimestre) {
            sb.append(
                    bimestre.getnIdBimestre() + "-"
                    + bimestre.getsDescripcion() + "-"
                    + bimestre.getsHabilitar() + ":");
        }

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
        System.out.println("el listado es " + sb);
    }
}
