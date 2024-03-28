
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.controller;

import com.sisedu.model.DAO.RegistroPagosDAO;
import com.sisedu.model.bean.Alumno;
import com.sisedu.model.bean.Boleta;
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
 * @author lflores
 */
@WebServlet(name = "registroPagoServletController", urlPatterns = {"/Formularios/registroPagoServletController"})
public class registroPagoServletController extends HttpServlet {

    RegistroPagosDAO registroPagos = new RegistroPagosDAO();

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if (action.equals("buscarAlumno")) {
            buscarAlumno(request, response);
        }
//        if (action.equals("rowclickPago")) {
//            rowclickPago(request, response);
//        }
        if (action.equals("rowclickAlumno")) {
            rowclickAlumno(request, response);
        }
        if (action.equals("ingresarPagos")) {
            ingresarPagos(request, response);
        }
        if (action.equals("editarPagos")) {
            editarPagos(request, response);
        }
        if (action.equals("verificarBoleta")) {
            verificarBoleta(request, response);
        }
        if (action.equals("consultaAlumnoPago")) {
            consultaAlumnoPago(request, response);
        }
        if (action.equals("obtenerMonto")) {
            obtenerMonto(request, response);
        }
        if (action.equals("consultaDatos")) {
            consultaDatos(request, response);
        }
        
       if (action.equals("obtenerNumeroRecibo")) {
            obtenerNumeroRecibo(request, response);
        }
       
       if (action.equals("actualizarNumeroRecibo")) {
            actualizarNumeroRecibo(request, response);
        }       
    }

    public void buscarAlumno(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
        String documento = request.getParameter("nDocumento");
        int nTipoTransaccion = 1;
        int contador = 0;
        List<Boleta> listaBoleta = registroPagos.consultaDatosPagos(documento, nTipoTransaccion);
        StringBuilder sb = new StringBuilder("");
        for (Boleta lb : listaBoleta) {
            sb.append(
                    lb.getDeuda().getsDescripcion() + "-"
                    + lb.getAlumnoDeuda().getMonto() + "-"
                    + lb.getsMora() + "-"
                    + lb.getsMonto() + "-"
                    + lb.getDeuda().getNidDeuda() + "-"
                    + lb.getAlumno().getnId_Alumno() + "-"
                    + lb.getAlumnoDeuda().getnIdAlumnoDeuda() + "-"
                    + lb.getAlumnoDeuda().getsMontoOtros() + "-"
                    + lb.getsBoletaReimpresion() + "-"
                    + lb.getAlumno().getsAnioAcademico() + "-"
                    + contador + "-"
                    + lb.getAlumno().getPersona().getnIdPersona()
                    + ":");
            contador++;
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

//    private void rowclickPago(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String idDeuda = request.getParameter("idDeuda");
//        String idAlumno = request.getParameter("idAlumno");
//        String usuario = request.getParameter("usuario");
//        String nTipo = request.getParameter("nTipo");
//        List<Boleta> listaBoleta = registroPagos.consultaDatosPagosDetalle(idDeuda, idAlumno);
//        StringBuilder sb = new StringBuilder("");
//        for (Boleta lb : listaBoleta) {
//            sb.append(
//                    lb.getAlumno().getPersona().getsDatosPersonales() + "-"
//                    + lb.getnIdBoleta() + "-"
//                    + lb.getnNumeroRecibo() + "-"
//                    + lb.getDeuda().getsDescripcion() + "-"
//                    + lb.getAlumnoDeuda().getMonto() + "-"
//                    + lb.getsMora() + "-"
//                    + lb.getsObservaciones() + '-'
//                    + lb.getAlumno().getnId_Alumno() + "-"
//                    + lb.getDeuda().getNidDeuda() + "-"
//                    + lb.getAlumnoDeuda().getnIdAlumnoDeuda() + "-"
//                    + usuario + "-"
//                    + lb.getbCancelado() + "-"
//                    + nTipo + ":");
//        }
//        response.setContentType("text/plain");
//        PrintWriter out = response.getWriter();
//        out.print(sb);
//
//    }
    private void rowclickAlumno(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int nIdPersona = Integer.parseInt(request.getParameter("id"));

        List<Alumno> listaAlumno = registroPagos.consultaDatosAlumnosPagoDetalle(nIdPersona);
        StringBuilder sb = new StringBuilder("");
        for (Alumno alu : listaAlumno) {
            sb.append(alu.getPersona().getnIdPersona() + "-" + alu.getPersona().getnNumeroDocumento() + "-" + alu.getPersona().getsApPaterno() + "-" + alu.getPersona().getsApMaterno() + "-"
                    + alu.getPersona().getsNombreCompleto() + "-" + alu.getPersona().getsFechaNacimiento() + "-" + alu.getPersona().getsSexo() + "-" + alu.getNivel().getsDescripcion() + "-" + alu.getGrado().getsDescripcion() + ":");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    public void ingresarPagos(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
        String sMensaje = "";
        String sNumeroRecibo = request.getParameter("txtRecibo");
        String sMonto = request.getParameter("txtMonto");
        String sMontoOtros = request.getParameter("txtMontoOtros");
        String sMora = request.getParameter("txtMora");
        int nIdAlumnoDeuda = Integer.valueOf(request.getParameter("IdAlumnoDeuda"));
        int nIdAlumno = Integer.valueOf(request.getParameter("IdAlumno"));
        int nIdDeuda = Integer.valueOf(request.getParameter("IdDeuda"));
        String Usuario = request.getParameter("Usuario");
        boolean bCancelado = Boolean.valueOf(request.getParameter("Cancelado"));

        sMensaje = registroPagos.ingresarDatosPagos(sNumeroRecibo, sMonto, sMontoOtros, sMora, nIdAlumnoDeuda, nIdAlumno, nIdDeuda, Usuario, bCancelado);
        
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        System.out.println("verificar cadena : " + sMensaje);
        out.print(sMensaje);
    }

    public void editarPagos(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
        String sMensaje = "";
        int nNumeroRecibo = Integer.valueOf(request.getParameter("txtRecibo"));
        String sMonto = request.getParameter("txtMonto");
        String sMora = request.getParameter("txtMora");
        String sObservaciones = request.getParameter("txtObservacion");
        int nIdAlumno = Integer.valueOf(request.getParameter("IdAlumno"));
        int nIdDeuda = Integer.valueOf(request.getParameter("IdDeuda"));
        int IdAlumnoDeuda = Integer.valueOf(request.getParameter("IdAlumnoDeuda"));
        String Usuario = request.getParameter("Usuario");
        boolean bCancelado = Boolean.valueOf(request.getParameter("Cancelado"));
        sMensaje = registroPagos.editarDatosPagos(nNumeroRecibo, sMonto, sMora, sObservaciones, nIdAlumno, nIdDeuda, IdAlumnoDeuda, Usuario, bCancelado);
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sMensaje);
    }

    private void verificarBoleta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sMensaje = "";
        String sNumeroRecibo = request.getParameter("txtRecibo");
        sMensaje = registroPagos.verificarBoleta(sNumeroRecibo);
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sMensaje);
    }
    
        private void obtenerNumeroRecibo(HttpServletRequest request, HttpServletResponse response) throws IOException {
            String sMensaje = "";
            sMensaje = registroPagos.obtenerNumeroRecibo();
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.print(sMensaje);
        }
        
        private void  actualizarNumeroRecibo(HttpServletRequest request, HttpServletResponse response) throws IOException {
            String sMensaje = "";
            sMensaje = registroPagos.actualizarNumeroRecibo();
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.print(sMensaje);
        }        

    private void consultaAlumnoPago(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int Dni = Integer.valueOf(request.getParameter("txtDni").equals("") ? "0" : request.getParameter("txtDni"));
        String sNombres = request.getParameter("txtApNom");
        int nNivel = Integer.valueOf(request.getParameter("cboNivel").equals("") ? "0" : request.getParameter("cboNivel"));
        String sRecibo = request.getParameter("txtRecibo");
        String sFechaInicial = request.getParameter("txtFechaInicial");
        String sFechaFinal = request.getParameter("txtFechaFin");

        boolean bFecha = Boolean.valueOf(request.getParameter("FECHA"));
        boolean bDni = Boolean.valueOf(request.getParameter("DNI"));
        boolean bApenom = Boolean.valueOf(request.getParameter("APNOM"));
        boolean bNivel = Boolean.valueOf(request.getParameter("NIVEL"));
        boolean bRecibo = Boolean.valueOf(request.getParameter("RECIBO"));

        List<Boleta> listaBoleta = registroPagos.consultaDatosPagos(bDni, bApenom, bNivel, bRecibo, bFecha, Dni, sNombres, nNivel, sRecibo, sFechaInicial, sFechaFinal);
        StringBuilder sb = new StringBuilder("");
        for (Boleta lb : listaBoleta) {
            sb.append(
                    lb.getnIdBoleta() + "*"
                    + lb.getAlumno().getPersona().getsDatosPersonales() + "*"
                    + lb.getAlumno().getNivel().getsDescripcion() + "*"
                    + lb.getAlumno().getGrado().getsDescripcion() + "*"
                    + lb.getsNumeroRecibo() + "*"
                    + lb.getDeuda().getsDescripcion() + "*"
                    + lb.getsMora() + "*"
                    + lb.getsMonto() + "*"
                    + lb.getsFechaCreacion() + "*"
                    + lb.getsUserName() + "*"
                    + lb.getAlumno().getPersona().getnNumeroDocumento() + "+");
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sb);
    }

    private void obtenerMonto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sMensaje = "";
        int nIdAlumnoDeuda = Integer.valueOf(request.getParameter("idAlumnoDeuda"));
        sMensaje = registroPagos.obtenerMonto(nIdAlumnoDeuda);
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(sMensaje);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(registroPagoServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
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
            Logger.getLogger(registroPagoServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void consultaDatos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String paciente = request.getParameter("txtPaciente");
        String filtro = request.getParameter("rbFiltro");

        List<Alumno> listaAlumno = registroPagos.consultaDatosAlumnos(paciente, filtro);
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
}
