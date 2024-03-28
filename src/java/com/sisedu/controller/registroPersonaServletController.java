/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.controller;

import com.sisedu.model.DAO.LoginDAO;
import com.sisedu.model.DAO.RegistroPersonasDAO;
import com.sisedu.model.bean.Alumno;
import com.sisedu.model.bean.AlumnoApoderado;
import com.sisedu.model.bean.AlumnoDeuda;
import com.sisedu.model.bean.AlumnoProcedencia;
import com.sisedu.model.bean.Documento;
import com.sisedu.model.bean.General;
import com.sisedu.model.bean.Grado;
import com.sisedu.model.bean.GradoInstruccion;
import com.sisedu.model.bean.Nivel;
import com.sisedu.model.bean.Parentesco;
import com.sisedu.model.bean.Persona;
import com.sisedu.model.bean.Procedencia;
import com.sisedu.model.bean.Profesor;
import com.sisedu.model.bean.TipoMatricula;
import com.sisedu.util.Constantes;
import com.sisedu.util.Constantes;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.spi.Context;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lflores
 */
@WebServlet(name = "registroPersonaServletController", urlPatterns = {"/Formularios/registroPersonaServletController"})
public class registroPersonaServletController extends HttpServlet {

    LoginDAO login = new LoginDAO();
    RegistroPersonasDAO registroPersona = new RegistroPersonasDAO();

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html; charset=iso-8859-1"); // para que aparezca la ñ al momento de llamarlo.
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        String stipoPersona = request.getParameter("tipoPersona"); // 3 tipo de persona = null
        if (action != null) {
            if (stipoPersona != null && stipoPersona.equalsIgnoreCase("3")) {
                if (action.equals("ingresarDatosProfesores")) {
                    ingresarDatosProfesores(request, response, stipoPersona);
                }
                if (action.equals("editarDatosProfesores")) {
                    editarDatosProfesores(request, response);
                }
                if (action.equals("consultaDatosProfesor")) {
                    consultaDatosProfesor(request, response);
                }
                if (action.equals("obtenerDatosProfesor")) {
                    obtenerDatosProfesor(request, response);
                }
            } else {
                if (action.equals("ingresarDatos")) {
                    ingresarDatos(request, response);
                }
                if (action.equals("editarDatos")) {
                    editarDatos(request, response);
                }
                if (action.equals("eliminarDatos")) {
                    eliminarDatos(request, response);
                }
                if (action.equals("consultaDatos")) {
                    consultaDatos(request, response);
                }
                if (action.equals("rowclick")) {
                    rowclick(request, response);
                }
                if (action.equals("rowclickApoderado")) {
                    rowclickApoderado(request, response);
                }
                if (action.equals("obtenerDocumentos")) {
                    obtenerDocumentos(request, response);
                }
                if (action.equals("obtenerParentesco")) {
                    obtenerParentesco(request, response);
                }
                if (action.equals("obtenerGradoInstruccion")) {
                    obtenerGradoInstruccion(request, response);
                }
                if (action.equals("obtenerNivel")) {
                    obtenerNivel(request, response);
                }
                if (action.equals("obtenerGrado")) {
                    obtenerGrado(request, response);
                }
                if (action.equals("obtenerProcedencia")) {
                    obtenerProcedencia(request, response);
                }
                if (action.equals("obtenerTipoMatricula")) {
                    obtenerTipoMatricula(request, response);
                }
                if (action.equals("obtenerSeccion")) {
                    obtenerSeccion(request, response);
                }
                if (action.equals("buscarAlumnoApoderado")) {
                    buscarAlumnoApoderado(request, response);
                }
                if (action.equals("conseguirApoderadoPorNombre")) {
                    conseguirApoderadoPorNombre(request, response);
                }
                if (action.equals("habilitarDatos")) {
                    habilitarDatos(request, response);
                }
            }
        } else {
            try {
                String sdescripcionNivel = request.getParameter("descripcionNivel");
                List<Grado> listGrado = login.obtenerGrado(sdescripcionNivel);
                StringBuilder sb = new StringBuilder("");
                for (Grado lg : listGrado) {
                    sb.append(lg.getnIdGrado() + "-" + lg.getsDescripcion() + ":");
                }
                out.write(sb.toString());
            } finally {
                out.close();
            }
        }
    }

    public void ingresarDatos(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {

        String sMensaje = "";
        int nNumeroDocumento = Integer.valueOf(request.getParameter("txtDni"));
        String apellidoPaterno = request.getParameter("txtApPaterno");
        String apellidoMaterno = request.getParameter("txtApMaterno");
        String nombres = request.getParameter("txtNombres");

        String fechaNacimiento = request.getParameter("txtFechaNacimiento");
        String sexo = request.getParameter("rbSexo");
        String DireccionActual = request.getParameter("txtDireccionActual");
        int nProcedencia = Integer.valueOf(request.getParameter("cboProcedencia"));
        String OtroProc = request.getParameter("txtOtroProc");
        //-- Apoderado P
        String NombresAp = request.getParameter("txtNombresAp");
        String nTelefonoAp = request.getParameter("txtTelefonoAp");
        int nParentescoAp = Integer.valueOf(request.getParameter("cboParentescoAp").equalsIgnoreCase("") ? "8" : request.getParameter("cboParentescoAp"));
        String sObservacionAp = request.getParameter("txtOtroAp");
        int nGradoInstruccionAp = Integer.valueOf(request.getParameter("cboGradoInstruccionAp").equalsIgnoreCase("") ? "5" : request.getParameter("cboGradoInstruccionAp"));
        //-- Apoderado alterno
        String NombresAa = request.getParameter("txtNombresAa");
        String nTelefonoAa = request.getParameter("txtTelefonoAa");
        int nParentescoAa = Integer.valueOf(request.getParameter("cboParentescoAa").equalsIgnoreCase("") ? "8" : request.getParameter("cboParentescoAa"));
        String sObservacionAa = request.getParameter("txtOtroAa");
        int nGradoInstruccionAa = Integer.valueOf(request.getParameter("cboGradoInstruccionAa").equalsIgnoreCase("") ? "5" : request.getParameter("cboGradoInstruccionAa"));
        //--
        int nIdNivel = Integer.valueOf(request.getParameter("cboNivel"));
        int nIdGrado = Integer.valueOf(request.getParameter("cboGrado"));
        String txtUsuario = request.getParameter("txtUsuario");

        String sDocumentos = request.getParameter("nidDocumentos");
        String anioAcademico = Constantes.anioAcademico;
        int nTipoMatricula = request.getParameter("cboTipoMatricula") == null ? 0 : Integer.valueOf(request.getParameter("cboTipoMatricula"));
        sMensaje = registroPersona.ingresarDatosAlumnos(nNumeroDocumento, apellidoPaterno, apellidoMaterno, nombres, fechaNacimiento, sexo, nIdNivel, nIdGrado, txtUsuario, DireccionActual,
                NombresAp, nTelefonoAp, nProcedencia, OtroProc, nParentescoAp, nGradoInstruccionAp, sObservacionAp, sDocumentos,
                NombresAa, nTelefonoAa, nParentescoAa, sObservacionAa, nGradoInstruccionAa, anioAcademico, nTipoMatricula);
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sMensaje);
    }

    private void consultaDatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paciente = request.getParameter("txtPaciente");
        String filtro = request.getParameter("rbFiltro");

        List<Alumno> listaAlumno = registroPersona.consultaDatosAlumnos(paciente, filtro);
        StringBuilder sb = new StringBuilder("");
        for (Alumno alu : listaAlumno) {
            sb.append(alu.getPersona().getnIdPersona() + "-"
                    + alu.getPersona().getnNumeroDocumento() + "-"
                    + alu.getPersona().getsApPaterno() + "-"
                    + alu.getPersona().getsApMaterno() + "-"
                    + alu.getPersona().getsNombreCompleto() + "-"
                    + alu.getsAnioAcademico() + "-"
                    + alu.getTipoMatricula().getsDescripcion() + "-"
                    + alu.getsCodigoColor()
                    + ":");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void rowclick(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int nIdPersona = Integer.parseInt(request.getParameter("id"));
        String sAnio = request.getParameter("sAnio");

        List<AlumnoProcedencia> listaAlumnoProcedencia = registroPersona.consultaDatosAlumnosDetalle(nIdPersona, sAnio);
        StringBuilder sb = new StringBuilder("");
        for (AlumnoProcedencia alu : listaAlumnoProcedencia) {
            sb.append(
                    alu.getAlumno().getPersona().getnIdPersona() + "+" // 0
                    + alu.getAlumno().getPersona().getnNumeroDocumento() + "+" // 1
                    + alu.getAlumno().getPersona().getsApPaterno() + "+" // 2
                    + alu.getAlumno().getPersona().getsApMaterno() + "+" // 3
                    + alu.getAlumno().getPersona().getsNombreCompleto() + "+" // 4
                    + alu.getAlumno().getPersona().getsFechaNacimiento() + "+" // 5
                    + alu.getAlumno().getPersona().getsSexo() + "+" // 6
                    + alu.getAlumno().getNivel().getnIdNivel() + "+" // 7
                    + alu.getAlumno().getGrado().getnIdGrado() + "+" // 8
                    + alu.getAlumno().getPersona().getsDireccionActual() + "+" // 9
                    + alu.getProcedencia().getnIdProcedencia() + "+" // 10
                    + alu.getAlumno().getDocumentos() + "+" // 11
                    + alu.getnIdAlumnoProcedencia() + "+"// 12
                    + alu.getsObservacion() + "+" // 13
                    + alu.getAlumno().getsEstado() + "+" // 14 
                    + alu.getAlumno().getTipoMatricula().getnIdMatricula() + "+" // 15
                    + alu.getAlumno().getsValidarAnioAcademico() // 16                    
                    + ";");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void rowclickApoderado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int nIdPersona = Integer.parseInt(request.getParameter("id"));
        String sAnioAcademico = request.getParameter("sAnio");

        List<AlumnoApoderado> listaAlumno = registroPersona.consultaDatosAlumnosApoderadoDetalle(nIdPersona, sAnioAcademico);
        StringBuilder sb = new StringBuilder("");
        for (AlumnoApoderado alu : listaAlumno) {
            sb.append(
                    alu.getApoderado().getsNombreCompleto() + "-" // 0
                    + alu.getApoderado().getsTelefono() + "-" //1
                    + alu.getApoderado().getGradoInstruccion().getnIdGradoInstruccion() + "-" //2
                    + alu.getParentesco().getnIdParentesco() + "-" // 3                    
                    + alu.getsObservacion() // 4
                    + ":");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void obtenerDocumentos(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Documento> listaDocumento = registroPersona.obtenerDocumentos();
        StringBuilder sb = new StringBuilder("");
        for (Documento ld : listaDocumento) {
            sb.append(ld.getnIdDocumento() + "-" + ld.getsDescripcion() + ":");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void obtenerParentesco(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Parentesco> listaDocumento = registroPersona.obtenerParentesco();
        StringBuilder sb = new StringBuilder("");
        for (Parentesco lp : listaDocumento) {
            sb.append(lp.getnIdParentesco() + "-" + lp.getsDescripcion() + ":");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void obtenerGradoInstruccion(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<GradoInstruccion> listaDocumento = registroPersona.obtenerGradoInstruccion();
        StringBuilder sb = new StringBuilder("");
        for (GradoInstruccion lgi : listaDocumento) {
            sb.append(lgi.getnIdGradoInstruccion() + "-" + lgi.getsDescripcion() + ":");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void obtenerNivel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Nivel> listaNivel = registroPersona.obtenerNivel();
        StringBuilder sb = new StringBuilder("");
        for (Nivel ln : listaNivel) {
            sb.append(ln.getnIdNivel() + "-" + ln.getsDescripcion() + ":");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void obtenerGrado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Grado> listaGrado = registroPersona.obtenerGrado();
        StringBuilder sb = new StringBuilder("");
        for (Grado ln : listaGrado) {
            sb.append(ln.getnIdGrado() + "-" + ln.getsDescripcion() + ":");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void obtenerProcedencia(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Procedencia> listaProcedencia = registroPersona.obtenerProcedencia();
        StringBuilder sb = new StringBuilder("");
        for (Procedencia lp : listaProcedencia) {
            sb.append(lp.getnIdProcedencia() + "-" + lp.getsDescripcion() + ":");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        System.out.println(sb);
        out.print(sb);
    }

    private void editarDatos(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String sMensaje = "";

        int nIdPersona = Integer.valueOf(request.getParameter("txtIdPersona"));
        int nNumeroDocumento = Integer.valueOf(request.getParameter("txtDni"));
        String apellidoPaterno = request.getParameter("txtApPaterno");
        String apellidoMaterno = request.getParameter("txtApMaterno");
        String nombres = request.getParameter("txtNombres");

        String fechaNacimiento = request.getParameter("txtFechaNacimiento");
        System.out.println(request.getParameter("txtFechaNacimiento"));
        String sexo = request.getParameter("rbSexo");
        String DireccionActual = request.getParameter("txtDireccionActual");
        int nProcedencia = Integer.valueOf(request.getParameter("cboProcedencia"));
        String OtroProc = request.getParameter("txtOtroProc");
        //-- Apoderado P
        String NombresAp = request.getParameter("txtNombresAp");
        String nTelefonoAp = request.getParameter("txtTelefonoAp");
        int nParentescoAp = Integer.valueOf(request.getParameter("cboParentescoAp").equalsIgnoreCase("seleccione") ? "7" : request.getParameter("cboParentescoAp"));
        String sObservacionAp = request.getParameter("txtOtroAp");
        int nGradoInstruccionAp = Integer.valueOf(request.getParameter("cboGradoInstruccionAp"));
        //-- Apoderado alterno
        String NombresAa = request.getParameter("txtNombresAa");
        String nTelefonoAa = request.getParameter("txtTelefonoAa");
        int nParentescoAa = Integer.valueOf(request.getParameter("cboParentescoAa").equalsIgnoreCase("seleccione") ? "7" : request.getParameter("cboParentescoAa"));
        String sObservacionAa = request.getParameter("txtOtroAa");
        int nGradoInstruccionAa = Integer.valueOf(request.getParameter("cboGradoInstruccionAa"));
        //--
        int nIdNivel = Integer.valueOf(request.getParameter("cboNivel"));
        int nIdGrado = Integer.valueOf(request.getParameter("cboGrado"));
        String txtUsuario = request.getParameter("txtUsuario");

        String sDocumentos = request.getParameter("nidDocumentos");
        String anioAcademico = Constantes.anioAcademico;
        int nTipoMatricula = request.getParameter("cboTipoMatricula") == null ? 0 : Integer.valueOf(request.getParameter("cboTipoMatricula"));
        boolean bRepite = request.getParameter("repite") == null ? false : Boolean.valueOf(request.getParameter("repite"));

        sMensaje = registroPersona.editarDatosAlumnos(nNumeroDocumento, apellidoPaterno, apellidoMaterno, nombres, fechaNacimiento, sexo, nIdNivel, nIdGrado, txtUsuario, DireccionActual,
                NombresAp, nTelefonoAp, nProcedencia, OtroProc, nParentescoAp, nGradoInstruccionAp, sObservacionAp, sDocumentos,
                NombresAa, nTelefonoAa, nParentescoAa, sObservacionAa, nGradoInstruccionAa, nIdPersona, 1, anioAcademico, nTipoMatricula, bRepite);
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sMensaje);

    }

    public void ingresarDatosProfesores(HttpServletRequest request, HttpServletResponse response, String sTipoPersona) throws ParseException, IOException {

        String sMensaje = "";
        int nNumeroDocumento = Integer.valueOf(request.getParameter("txtDni"));
        String sCodigo = request.getParameter("txtCodigo");
        String apellidoPaterno = request.getParameter("txtApPaterno");
        String apellidoMaterno = request.getParameter("txtApMaterno");
        String nombres = request.getParameter("txtNombres");
        String fechaNacimiento = request.getParameter("txtFechaNacimiento");
        String sexo = request.getParameter("rbSexo");
        String DireccionActual = request.getParameter("txtDireccionActual");
        String txtUsuario = request.getParameter("txtUsuario");
        String sFechaIngreso = request.getParameter("txtFechaIngreso");
        int nTipoPersona = Integer.parseInt(sTipoPersona);

        sMensaje = registroPersona.ingresarDatosProfesor(nNumeroDocumento, apellidoPaterno, apellidoMaterno, nombres, fechaNacimiento, sexo, sCodigo, txtUsuario, DireccionActual, nTipoPersona, sFechaIngreso);
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sMensaje);
    }

    private void editarDatosProfesores(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String sMensaje = "";
        int nIdPersona = Integer.valueOf(request.getParameter("txtIdPersona"));
        int nNumeroDocumento = Integer.valueOf(request.getParameter("txtDni"));
        String sCodigo = request.getParameter("txtCodigo");
        String apellidoPaterno = request.getParameter("txtApPaterno");
        String apellidoMaterno = request.getParameter("txtApMaterno");
        String nombres = request.getParameter("txtNombres");
        String fechaNacimiento = request.getParameter("txtFechaNacimiento");
        String sexo = request.getParameter("rbSexo");
        String DireccionActual = request.getParameter("txtDireccionActual");
        String txtUsuario = request.getParameter("txtUsuario");
        String sFechaIngreso = request.getParameter("txtFechaIngreso");

        sMensaje = registroPersona.editarDatosProfesor(nIdPersona, nNumeroDocumento, apellidoPaterno, apellidoMaterno, nombres, fechaNacimiento, sexo, sCodigo, txtUsuario, DireccionActual, sFechaIngreso);
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sMensaje);

    }

    private void consultaDatosProfesor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String profesor = request.getParameter("txtProfesor");
        String filtro = request.getParameter("rbFiltro");


        List<Profesor> listaProfesor = registroPersona.consultaDatosProfesor(profesor, filtro);
        StringBuilder sb = new StringBuilder("");
        for (Profesor pro : listaProfesor) {
            sb.append(pro.getPersona().getnIdPersona() + "-" + pro.getPersona().getnNumeroDocumento() + "-" + pro.getPersona().getsDatosPersonales() + ":");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void obtenerDatosProfesor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int nIdPersona = Integer.parseInt(request.getParameter("id"));

        List<Profesor> listaProfesor = registroPersona.obtenerDatosProfesor(nIdPersona);
        StringBuilder sb = new StringBuilder("");
        for (Profesor pro : listaProfesor) {
            sb.append(
                    pro.getPersona().getnIdPersona() + "+" // 0
                    + pro.getPersona().getnNumeroDocumento() + "+" // 1
                    + pro.getPersona().getsApPaterno() + "+" // 2
                    + pro.getPersona().getsApMaterno() + "+" // 3
                    + pro.getPersona().getsNombreCompleto() + "+" // 4
                    + pro.getPersona().getsFechaNacimiento() + "+" // 5
                    + pro.getPersona().getsSexo() + "+" // 6
                    + pro.getsFechaIngreso() + "+" // 7
                    + pro.getnCodigo() // 8
                    + "*");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void eliminarDatos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sMensaje = "";
        int nIdPersona = Integer.valueOf(request.getParameter("txtIdPersona"));
        String sFechaRegistro = request.getParameter("txtFechaRegistro");
        String sUsuario = request.getParameter("sUsuario") == null ? "Sistema" : request.getParameter("sUsuario");
        String sAnioAcademico = request.getParameter("sAnioAcademico") == null ? "" : request.getParameter("sAnioAcademico");

        sMensaje = registroPersona.eliminarDatosAlumnos(nIdPersona, sFechaRegistro, sUsuario, sAnioAcademico);
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sMensaje);
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
            request.setCharacterEncoding("UTF-8"); // para guardar con la letra ñ
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(registroPersonaServletController.class.getName()).log(Level.SEVERE, null, ex);
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
            response.setContentType("text/html; charset=iso-8859-1");
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(registroPersonaServletController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void obtenerTipoMatricula(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<TipoMatricula> listaMatricula = registroPersona.obtenerTipoMatricula();
        StringBuilder sb = new StringBuilder("");
        for (TipoMatricula tm : listaMatricula) {
            sb.append(tm.getnIdMatricula() + "-" + tm.getsDescripcion() + ":");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void obtenerSeccion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<General> listaGeneral = registroPersona.obtenerSeccion();
        StringBuilder sb = new StringBuilder("");
        for (General gn : listaGeneral) {
            sb.append(gn.getnIdGeneral() + "-" + gn.getsDescripcion() + ":");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void buscarAlumnoApoderado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<AlumnoApoderado> listaApoderado = registroPersona.obtenerAlumnoApoderado();
        StringBuilder sb = new StringBuilder("");
        for (AlumnoApoderado aa : listaApoderado) {
            sb.append(aa.getAlumno().getPersona().getsDatosPersonales().trim() + ":");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void conseguirApoderadoPorNombre(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String snombreApoderado = request.getParameter("nombreApoderado");
        List<AlumnoApoderado> listaApoderado = registroPersona.conseguirApoderado(snombreApoderado);
        System.out.println("listaApoderado : " + listaApoderado.toString());
        StringBuilder sb = new StringBuilder("");
        for (AlumnoApoderado aa : listaApoderado) {
            sb.append(
                    aa.getParentesco().getnIdParentesco() + ","
                    + aa.getsObservacion() + ","
                    + aa.getApoderado().getsTelefono() + ","
                    + aa.getApoderado().getGradoInstruccion().getnIdGradoInstruccion());
        }
        System.out.println("sb  ----    >> " + sb);
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void habilitarDatos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sMensaje = "";
        int nIdPersona = Integer.valueOf(request.getParameter("txtIdPersona"));
        String sFechaRegistro = request.getParameter("txtFechaRegistro");
        String sUsuario = request.getParameter("sUsuario") == null ? "Sistema" : request.getParameter("sUsuario");
        String sAnioAcademico = request.getParameter("sAnioAcademico") == null ? "" : request.getParameter("sAnioAcademico");

        sMensaje = registroPersona.habilitarDatosAlumnos(nIdPersona, sFechaRegistro, sUsuario, sAnioAcademico);
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sMensaje);
    }
}
