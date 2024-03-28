/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.sisedu.model.DAO.Conexion;
import com.sisedu.model.DAO.ConsultaDeudasDAO;
import com.sisedu.util.NumberToLetterConverter;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author MAQ
 */
@WebServlet(name = "impresionPagoServletController", urlPatterns = {"/Formularios/impresionPagoServletController"})
public class impresionPagoServletController extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ConsultaDeudasDAO consultasDeudas = new ConsultaDeudasDAO();    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DocumentException {
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("exportarPdfPagos")) {
                exportarPdfPagos(request, response);
            } else if (action.equalsIgnoreCase("reimprimir")) {
                reimprimir(request, response);
            }
        } else {
            String nIdAlumno = request.getParameter("sNumeroRecibo");
            String sNumeroRecibo = request.getParameter("nIdAlumno");
            String sTtotal_numero_en_letras = null;
            String sSumaTotal = null;

            System.out.println("nIdAlumno       : " + nIdAlumno);
            System.out.println("sNumeroRecibo   : " + sNumeroRecibo);

            response.setHeader("Content-Disposition", "attachment; filename=\"reporte_" + sNumeroRecibo + ".pdf\";");
            ServletOutputStream out = response.getOutputStream();

            java.sql.Connection cn;
            cn = Conexion.conectar();

            try {
                Map<String, Object> parametros = new HashMap();
                sSumaTotal = consultasDeudas.obtenerMontoTotalDeuda(nIdAlumno, sNumeroRecibo);
                NumberToLetterConverter letterConverter = new NumberToLetterConverter();
                sTtotal_numero_en_letras =sSumaTotal!=null? letterConverter.convertNumberToLetter(sSumaTotal):"";
                parametros.put("nIdAlumno", nIdAlumno);
                parametros.put("sNumeroRecibo", sNumeroRecibo);
                parametros.put("p_total_en_letras",sTtotal_numero_en_letras);
                File reporFile = new File(request.getSession().getServletContext().getRealPath("/ReportesNotas/rptTicketVentaAtencionVuelto.jasper"));

                byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parametros, cn);
                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                out.write(bytes, 0, bytes.length);
                out.flush();
                out.close();

            } catch (Exception e) 
                e.printStackTrace();
            }
        }
    }

    public void exportarPdfPagos(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, DocumentException, IOException {

        String textHtml = request.getParameter("cadena");

        String sMensaje = "";
        // itextpdf-5.4.1.jar  http://sourceforge.net/projects/itext/files/iText/

        Document document = new Document(PageSize.LETTER);
        PdfWriter.getInstance(document, new FileOutputStream("D://Apiservi//Temp.pdf"));
        document.open();
        document.addAuthor("Real Gagnon");
        document.addCreator("Real's HowTo");
        document.addSubject("Thanks for your support");
        document.addCreationDate();
        document.addTitle("Please read this");

        HTMLWorker htmlWorker = new HTMLWorker(document);
        response.setHeader("Content-Disposition", "attachment; filename=\"reporte.pdf\";");
        htmlWorker.parse(new StringReader(textHtml));
        document.close();
        System.out.println("Done");
        response.setContentType("text/plain");
    }

    public void reimprimir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DocumentException {

        String nIdAlumno = request.getParameter("sNumeroRecibo");
        String sNumeroRecibo = request.getParameter("nIdAlumno");

        System.out.println("nIdAlumno       : " + nIdAlumno);
        System.out.println("sNumeroRecibo   : " + sNumeroRecibo);

        response.setHeader("Content-Disposition", "attachment; filename=\"reporte_" + sNumeroRecibo + ".pdf\";");
        ServletOutputStream out = response.getOutputStream();

        java.sql.Connection cn;
        cn = Conexion.conectar();

        try {
            Map<String, Object> parametros = new HashMap();
            parametros.put("nIdAlumno", nIdAlumno);
            parametros.put("sNumeroRecibo", sNumeroRecibo);
            File reporFile = new File(request.getSession().getServletContext().getRealPath("/ReportesNotas/reporteBoletaReimpresion.jasper"));

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
        } catch (DocumentException ex) {
            Logger.getLogger(impresionPagoServletController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (DocumentException ex) {
            Logger.getLogger(impresionPagoServletController.class.getName()).log(Level.SEVERE, null, ex);
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
