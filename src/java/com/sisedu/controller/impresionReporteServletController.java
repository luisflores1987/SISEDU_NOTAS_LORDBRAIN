/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.controller;

import com.itextpdf.text.pdf.PdfCopyFields;
import com.itextpdf.text.pdf.PdfReader;
import com.sisedu.model.DAO.Conexion;
import com.sisedu.util.Constantes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author MAQ
 */
public class impresionReporteServletController extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("reporteGrado")) {
                reporteGrado(request, response);

            } else if (action.equals("reporteGradoFinal")) {
                reporteGradoFinal(request, response);
            } else if (action.equals("reporteGradoFinalAlumno")) {
                reporteGradoFinalAlumno(request, response);
            }
        } else {
            int nIdAlumno = Integer.valueOf(request.getParameter("nIdBimestre"));
            int sNumeroRecibo = Integer.valueOf(request.getParameter("nIdAlumno"));
            int nIdNivel = Integer.valueOf(request.getParameter("IdNivel"));
            File reporFile = null;
            response.setHeader("Content-Disposition", "attachment; filename=\"reporte_" + sNumeroRecibo + ".pdf\";");
            ServletOutputStream out = response.getOutputStream();
            String sTab = "\\";
            FacesContext context = FacesContext.getCurrentInstance();
            ServletContext scontext = (ServletContext) context.getExternalContext().getContext();
            String rutaReportes = scontext.getRealPath(sTab + "ReportesNotas");
            String rutaImagen = scontext.getRealPath(sTab + "images" + sTab + "Reporte");

            java.sql.Connection cn;
            cn = Conexion.conectar();

            try {
                Map<String, Object> parametros = new HashMap();
                parametros.put("IDALUMNO", nIdAlumno);
                parametros.put("IDBIMESTRE", sNumeroRecibo);
                String rutaImagenCabecera = "";


                if (nIdNivel == 1) {
                    if (!"".equals(Constantes.Cabecera_inicial)) {
                        rutaImagenCabecera = rutaImagen + sTab + Constantes.Cabecera_inicial + ".png";
                        System.out.print(rutaImagenCabecera);
                        parametros.put("V_CABECERA", rutaImagenCabecera);
                    }

                    reporFile = new File(rutaReportes + sTab + "reporteNotasGradoInicial.jasper");

//                    reporFile = new File(request.getSession().getServletContext().getRealPath("/ReportesNotas/reporteNotasInicial.jasper"));
                }
                if (nIdNivel == 2) {

                    if (!"".equals(Constantes.Cabecera)) {
                        rutaImagenCabecera = rutaImagen + sTab + Constantes.Cabecera + ".png";
                        parametros.put("V_CABECERA", rutaImagenCabecera);
                    }

                    reporFile = new File(request.getSession().getServletContext().getRealPath("D:\\Apiservi\\Aplicativo\\SISEDU ORIGINAL\\SISEDU_NOTAS\\web\\ReportesNotas\\reporteNotasPrimaria.jasper"));
                } else {

                    if (!"".equals(Constantes.Cabecera)) {
                        rutaImagenCabecera = rutaImagen + sTab + Constantes.Cabecera + ".png";
                        parametros.put("V_CABECERA", rutaImagenCabecera);
                    }

                    reporFile = new File(request.getSession().getServletContext().getRealPath("D:\\Apiservi\\Aplicativo\\SISEDU ORIGINAL\\SISEDU_NOTAS\\web\\ReportesNotas\\reporteNotas.jasper"));
                }
                FileInputStream in = new FileInputStream(reporFile);

                byte[] bytes = JasperRunManager.runReportToPdf(in, parametros, cn);

                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                out.write(bytes, 0, bytes.length);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cn.close();
            }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(impresionReporteServletController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(impresionReporteServletController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void reporteGrado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int nIdBimestre = Integer.valueOf(request.getParameter("IdBimestre"));
        String sIdNivel = request.getParameter("IdNivel").trim();
        int nIdGrado = Integer.valueOf(request.getParameter("IdGrado"));
        File reporFile = null;
        response.setHeader("Content-Disposition", "attachment; filename=\"reporte_" + nIdGrado + ".pdf\";");
        ServletOutputStream out = response.getOutputStream();

        java.sql.Connection cn;
        cn = Conexion.conectar();

        try {
            Map<String, Object> parametros = new HashMap();
            parametros.put("IDBIMESTRE", nIdBimestre);
            parametros.put("IDGRADO", nIdGrado);

            if (sIdNivel.equalsIgnoreCase("1")) {

                reporFile = new File(request.getSession().getServletContext().getRealPath("/ReportesNotas/reporteNotasGradoInicial.jasper"));
            }
            if (sIdNivel.equalsIgnoreCase("2")) {
                reporFile = new File(request.getSession().getServletContext().getRealPath("/ReportesNotas/reporteNotasGradoPrimaria.jasper"));
            }

            if (sIdNivel.equalsIgnoreCase("3")) {
                reporFile = new File(request.getSession().getServletContext().getRealPath("/ReportesNotas/reporteNotasGrado.jasper"));
            }

            byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parametros, cn);

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            out.write(bytes, 0, bytes.length);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reporteGradoFinal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int nIdGrado = Integer.valueOf(request.getParameter("IdGrado"));
        File reporFile = null;
        response.setHeader("Content-Disposition", "attachment; filename=\"reporte_" + nIdGrado + ".pdf\";");
        ServletOutputStream out = response.getOutputStream();

        java.sql.Connection cn;
        cn = Conexion.conectar();

        try {
            Map<String, Object> parametros = new HashMap();
            parametros.put("IDGRADO", nIdGrado);

            reporFile = new File(request.getSession().getServletContext().getRealPath("/ReportesNotas/reporteFinal/reporteNotaFinalInicial.jasper"));
            byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parametros, cn);

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            out.write(bytes, 0, bytes.length);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reporteGradoFinalAlumno(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int nIdAlumno = request.getParameter("nIdAlumno") == null ? 0 : Integer.valueOf(request.getParameter("nIdAlumno"));
        String sNombreCompleto = request.getParameter("sNombreCompleto") == null ? "" : request.getParameter("sNombreCompleto");
        String sGrado = request.getParameter("sGrado") == null ? "" : request.getParameter("sGrado");
        File reporFile = null;
        // hace que se descargue el PDF
        response.setHeader("Content-Disposition", "attachment; filename=\"Libreta_" + sGrado + "_" + sNombreCompleto + ".pdf\";");
        ServletOutputStream out = response.getOutputStream();

        java.sql.Connection cn;
        cn = Conexion.conectar();
        try {
            Map<String, Object> parametros = new HashMap();
            parametros.put("IDALUMNO", nIdAlumno);

            reporFile = new File(request.getSession().getServletContext().getRealPath("/ReportesNotas/reporteFinal/reporteNotaFinalAlumno.jasper"));

            byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parametros, cn);

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            out.write(bytes, 0, bytes.length);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
