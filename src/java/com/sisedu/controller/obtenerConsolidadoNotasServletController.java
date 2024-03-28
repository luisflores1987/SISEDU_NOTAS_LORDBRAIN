/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.controller;

import com.sisedu.model.DAO.obtenerConsolidadoDeNotasDAO;
import com.sisedu.model.bean.Area;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author LUIS
 */
public class obtenerConsolidadoNotasServletController extends HttpServlet {

    obtenerConsolidadoDeNotasDAO obtenerConsolidadoNotas = new obtenerConsolidadoDeNotasDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {

        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("obtenerVisorConsolidadoNotas")) {
            obtenerVisorConsolidadoNotas(request, response);
        }
        if (action.equalsIgnoreCase("imprimir")) {
            imprimir(request, response);
        }
    }

    private void obtenerVisorConsolidadoNotas(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int nIdGrado = (request.getParameter("IdGrado") == null ? 0 : Integer.valueOf(request.getParameter("IdGrado")));
        int nIdBimestre = (request.getParameter("IdBimestre") == null ? 0 : Integer.valueOf(request.getParameter("IdBimestre")));

        List<Area> listaArea = obtenerConsolidadoNotas.obtenerAreaxNotas(nIdGrado);
        request.setAttribute("lstArea", listaArea);
        request.setAttribute("nIdGrado", nIdGrado);
        request.setAttribute("nIdBimestre", nIdBimestre);
        getServletContext().getRequestDispatcher("/Formularios/frmConsolidadoNotas.jsp").forward(request, response);
//        StringBuilder sb = new StringBuilder("");
//        for (Area la : listaArea) {
//            sb.append(
//                    la.getnIdArea() + "*"
//                    + la.getsDescripcion() + "+");
//        }
//        response.setContentType("text/plain");
//        PrintWriter out = response.getWriter();
//        out.print(sb);
//        System.out.println("sb ----- >" + sb);
    }

    private void imprimir(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException, ServletException {
//        HttpSession session = request.getSession();

        String idGrado = request.getParameter("idGrado") == null ? "" : request.getParameter("idGrado").toString();
        String idBimestre = request.getParameter("idBimestre") == null ? "" : request.getParameter("idBimestre").toString();
        String sBimestre = request.getParameter("sBimestre") == null ? "" : request.getParameter("sBimestre").toString();
        String sGrado = request.getParameter("sGrado") == null ? "" : request.getParameter("sGrado").toString();
        
        System.out.println("El grado es  : " + sGrado.replace("Ã±", "ñ"));
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        HSSFWorkbook wb = null;
        List<List<Object>> lstConsolidadoAlumnosExportar = obtenerConsolidadoNotas.obtenerDatosAlumnos(idGrado);

        wb = exportarReporte(lstConsolidadoAlumnosExportar, idGrado, idBimestre, sBimestre, sGrado.replace("Ã±", "ñ"));
        wb.write(out);
        out.flush();
        String data = Base64.encodeBase64String(out.toByteArray());
        request.setAttribute("data", data);
        getServletContext().getRequestDispatcher("/Formularios/frmExportarExcelConsolidado.jsp").forward(request, response);
    }

    private HSSFWorkbook exportarReporte(List<List<Object>> lstConsolidadoExportar, String idGrado, String idBimestre, String sBimestre, String sGrado) {
        try {

            HSSFWorkbook workBook = null;
            ServletContext scontext = getServletContext();
            POIFSFileSystem fs = new POIFSFileSystem(scontext.getResourceAsStream("/template/AptitudCliente.xls"));
            List<List<Object>> lstConsolidadoExportarDetalle = new ArrayList<List<Object>>();

            for (List<Object> lst : lstConsolidadoExportar) {
                lstConsolidadoExportarDetalle.add(lst);
            }

            try {
                workBook = new HSSFWorkbook(fs);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(obtenerConsolidadoNotasServletController.class.getName()).log(Level.SEVERE, null, ex);
            }
            HSSFSheet hssfSheetMatriz = workBook.getSheetAt(0); // selecciona la hoja.
            //HSSFRow row, rowDetalle,rowMatriz;
            HSSFCell cell = null;

            HSSFCellStyle cellStyle = workBook.createCellStyle();
            cellStyle.setDataFormat((short) 49);
            cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
            cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
            cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
            cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            cellStyle.setTopBorderColor(HSSFColor.BLACK.index);
            //mapaCantidadesAptitud
//            for (Entry<String, Integer> ocita : mapaCantidadesAptitud.entrySet()) {
//                row = hssfSheet.getRow(i + 4);
//                row.getCell(1).setCellValue(ocita.getKey().toString());
//                row.getCell(2).setCellValue((Integer) ocita.getValue());
//                i = i + 1;
//            }

            HSSFCellStyle cellstyleSub = workBook.createCellStyle();
            cellstyleSub.setRotation((short) 90);
            cellstyleSub.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellstyleSub.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            cellstyleSub.setBottomBorderColor(HSSFColor.BLACK.index);
            cellstyleSub.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cellstyleSub.setLeftBorderColor(HSSFColor.BLACK.index);
            cellstyleSub.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cellstyleSub.setRightBorderColor(HSSFColor.BLACK.index);
            cellstyleSub.setBorderTop(HSSFCellStyle.BORDER_THIN);
            cellstyleSub.setTopBorderColor(HSSFColor.BLACK.index);
            HSSFFont font = workBook.createFont();
            font.setFontName(HSSFFont.FONT_ARIAL);
            font.setFontHeightInPoints((short) 10);
            font.setColor(HSSFColor.BLACK.index);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            cellstyleSub.setFont(font);

            HSSFCellStyle cellstyleSub1 = workBook.createCellStyle();
            cellstyleSub1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellstyleSub1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            cellstyleSub1.setBottomBorderColor(HSSFColor.BLACK.index);
            cellstyleSub1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cellstyleSub1.setLeftBorderColor(HSSFColor.BLACK.index);
            cellstyleSub1.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cellstyleSub1.setRightBorderColor(HSSFColor.BLACK.index);
            cellstyleSub1.setBorderTop(HSSFCellStyle.BORDER_THIN);
            cellstyleSub1.setTopBorderColor(HSSFColor.BLACK.index);
            HSSFFont font1 = workBook.createFont();
            font1.setFontName(HSSFFont.FONT_ARIAL);
            font1.setFontHeightInPoints((short) 10);
            font1.setColor(HSSFColor.BLACK.index);
            font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            cellstyleSub1.setFont(font);

            try {
                if (!lstConsolidadoExportarDetalle.isEmpty()) {

                    //  Crea el titulo del excel.... 



                    int nContColumnas = 0;
                    int nContMatriz = 2;
                    List<HSSFRow> rowMatriz = new ArrayList<HSSFRow>();
                    List<Object> obj = null;
                    
 
                    Object objTitulo = "CONSOLIDADO DEL " + sBimestre + " - GRADO : " + sGrado;
                    
                    creaCelda(hssfSheetMatriz.createRow((short) (1)), cell, cellStyle, 1, objTitulo, 0, cellstyleSub, cellstyleSub1);
                    for (int K = 0; K < lstConsolidadoExportarDetalle.size(); K++) {  // k ---> son las filas de la tabla
                        obj = lstConsolidadoExportarDetalle.get(K);
                        //crearCabecera(workBook, 1, hssfSheetMatriz, cellstyleSub);
                        rowMatriz.add(creaFila(nContMatriz, hssfSheetMatriz)); // aqui se crea la fila de la tabla 
                        nContMatriz++; // se aumenta una linea 
                        crearCeldaPorAptitud(lstConsolidadoExportarDetalle, rowMatriz.get(K), cell, cellStyle, obj, cellstyleSub, nContColumnas, cellstyleSub1);
                    }

                    nContColumnas = nContColumnas + lstConsolidadoExportarDetalle.get(0).size();

                    List<Area> listaArea = obtenerConsolidadoNotas.obtenerAreaxNotas(Integer.valueOf(idGrado));

                    //rowMatriz = null;
                    for (Area a : listaArea) {
                        //nContMatriz = 2;
                        List<List<Object>> lstConsolidadoAlumnosNotasExportar = obtenerConsolidadoNotas.obtenerDatosAlumnosNotas(idGrado, String.valueOf(a.getnIdArea()), idBimestre);


                        for (int K = 0; K < lstConsolidadoAlumnosNotasExportar.size(); K++) {  // k ---> son las filas de la tabla
                            obj = lstConsolidadoAlumnosNotasExportar.get(K);
                            //crearCabecera(workBook, 1, hssfSheetMatriz, cellstyleSub);
                            // rowMatriz = creaFila(nContMatriz, hssfSheetMatriz); // aqui se crea la fila de la tabla 
                            //nContMatriz++; // se aumenta una linea 
                            crearCeldaPorAptitud(lstConsolidadoAlumnosNotasExportar, rowMatriz.get(K), cell, cellStyle, obj, cellstyleSub, nContColumnas, cellstyleSub1);
                        }
                        nContColumnas = nContColumnas + lstConsolidadoAlumnosNotasExportar.get(0).size();
                    }
                    List<List<Object>> lstConsolidadoAlumnosPuntajeExportar = obtenerConsolidadoNotas.obtenerDatosAlumnosPuntaje(idGrado, idBimestre);


                    for (int K = 0; K < lstConsolidadoAlumnosPuntajeExportar.size(); K++) {  // k ---> son las filas de la tabla
                        obj = lstConsolidadoAlumnosPuntajeExportar.get(K);
                        //crearCabecera(workBook, 1, hssfSheetMatriz, cellstyleSub);
                        // rowMatriz = creaFila(nContMatriz, hssfSheetMatriz); // aqui se crea la fila de la tabla 
                        //nContMatriz++; // se aumenta una linea 
                        crearCeldaPorAptitud(lstConsolidadoAlumnosPuntajeExportar, rowMatriz.get(K), cell, cellStyle, obj, cellstyleSub, nContColumnas, cellstyleSub1);
                    }
                    nContColumnas = nContColumnas + lstConsolidadoAlumnosPuntajeExportar.get(0).size();


                    //Autoajuste
                    for (int y = 0; y < nContColumnas; y++) {
                        hssfSheetMatriz.autoSizeColumn((short) y, true);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return workBook;

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public HSSFRow creaFila(int rownum, HSSFSheet hoja) {
        return hoja.createRow((short) (rownum));
    }

    private void crearCeldaPorAptitud(List<List<Object>> lstDetalleTipoAptitud, HSSFRow rowMatriz, HSSFCell cell, HSSFCellStyle cellStyle, List<Object> obj, HSSFCellStyle cellStyleCabecera, int nContador, HSSFCellStyle cellStyleCabecera1) {
        for (int j = 0; j < lstDetalleTipoAptitud.get(0).size(); j++) { // j es el numero de columnas...
            cell = creaCelda(rowMatriz, cell, cellStyle, j + nContador, obj.get(j), nContador, cellStyleCabecera, cellStyleCabecera1);
        }
    }

    public HSSFCell creaCelda(HSSFRow row, HSSFCell cell, HSSFCellStyle cellStyle, int columnIndex, Object valor, int contador, HSSFCellStyle cellStyleCabecera, HSSFCellStyle cellStyleCabecera1) { /// columnIndex ---> el numero de la columna
        if (valor instanceof Date) {
            cell = row.createCell(columnIndex, 4);  // los numeros enteros indican el tipo de dato de la columna -- >> 4 es fecha
        } else {
            cell = row.createCell(columnIndex, 1);
        }

        if (row.getRowNum() == 2 || row.getRowNum() == 1) {
            if (columnIndex > 1) {
                cell.setCellStyle(cellStyleCabecera);
            } else {
                cell.setCellStyle(cellStyleCabecera1);
            }
        } else {
            cell.setCellStyle(cellStyle);
        }

        if (valor == null) {
            cell.setCellValue("");
        } else {
            if (valor instanceof Date) {
                cell.setCellValue((Date) valor);
            } else {
                cell.setCellValue(valor.toString());
            }
        }
        return cell;
    }

    private void crearCabecera(HSSFWorkbook workBook, int k, HSSFSheet hssfSheet, HSSFCellStyle cellstyleSub) {
        HSSFRow rowDetalle;
        HSSFCell cell;
        rowDetalle = hssfSheet.createRow((short) (k));
        cell = rowDetalle.createCell(0);
        cell.setCellStyle(cellstyleSub);
        cell.setCellValue("");
        cell = rowDetalle.createCell(1);
        cell.setCellStyle(cellstyleSub);
        cell.setCellValue("");
//        cell = rowDetalle.createCell(2);
//        cell.setCellStyle(cellstyleSub);
//        cell.setCellValue("Paciente");
//        cell = rowDetalle.createCell(3);
//        cell.setCellStyle(cellstyleSub);
//        cell.setCellValue("Edad");
//        cell = rowDetalle.createCell(4);
//        cell.setCellStyle(cellstyleSub);
//        cell.setCellValue("Sexo");
//        cell = rowDetalle.createCell(5);
//        cell.setCellStyle(cellstyleSub);
//        cell.setCellValue("Ocupación");
//        cell = rowDetalle.createCell(6);
//        cell.setCellStyle(cellstyleSub);
//        cell.setCellValue("Tipo de Atención");
//        cell = rowDetalle.createCell(7);
//        cell.setCellStyle(cellstyleSub);
//        cell.setCellValue("Grupo Sanguineo");
//        cell = rowDetalle.createCell(8);
//        cell.setCellStyle(cellstyleSub);
//        cell.setCellValue("Perfil Médico");
//        cell = rowDetalle.createCell(9);
//        cell.setCellStyle(cellstyleSub);
//        cell.setCellValue("Fecha Cita");
//        cell = rowDetalle.createCell(10);
//        cell.setCellStyle(cellstyleSub);
//        cell.setCellValue("Aptitud");
//        cell = rowDetalle.createCell(11);
//        cell.setCellStyle(cellstyleSub);
//        cell.setCellValue("Fecha Vencimiento");
//        cell = rowDetalle.createCell(12);
//        cell.setCellStyle(cellstyleSub);
//        cell.setCellValue("Observaciones");
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
            Logger.getLogger(obtenerConsolidadoNotasServletController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(obtenerConsolidadoNotasServletController.class.getName()).log(Level.SEVERE, null, ex);
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
