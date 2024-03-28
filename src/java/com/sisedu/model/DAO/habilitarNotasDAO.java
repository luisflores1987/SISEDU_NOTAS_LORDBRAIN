/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.model.DAO;

import com.sisedu.model.bean.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MAQ
 */
public class habilitarNotasDAO {

    public List<Grado> obtenerGrado(String sIdPersona) {
        Connection conn = Conexion.conectar();
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Grado> listaGrado = new ArrayList<Grado>();
        try {
            cs = conn.prepareCall("{call usp_obtenerDatosGenerales (?, ?)}");
            cs.setInt(1, 4);
            cs.setString(2, sIdPersona);
            rs = cs.executeQuery();
            while (rs.next()) {
                Grado grado = new Grado();
                grado.setnIdGrado(rs.getInt(1));
                grado.setsDescripcion(rs.getString(2));
                listaGrado.add(grado);
            }
            cs.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Conexion.cerrar(conn);
        }
        return listaGrado;
    }

    public List<Curso> obtenerCursoPorProfesorGrado(String sIdPersona, String sIdGrado) {
        Connection conn = Conexion.conectar();
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Curso> listaCurso = new ArrayList<Curso>();
        try {
            cs = conn.prepareCall("{call usp_obtenerDatosGenerales (?, ?, ?)}");
            cs.setInt(1, 3);
            cs.setString(2, sIdPersona);
            cs.setString(3, sIdGrado);
            rs = cs.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setnIdCurso(rs.getInt(1));
                curso.setsDescripcion(rs.getString(2));
                listaCurso.add(curso);
            }
            cs.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Conexion.cerrar(conn);
        }
        return listaCurso;
    }

    public int obtenerHabilitacionNotas(String sFechaCierreInicial, String sFechaCierreFinal, int nTipoNota, String sIdBimestre) {
        Connection conn = Conexion.conectar();
        CallableStatement cs = null;
        ResultSet rs = null;
        int nOk;
        try {
            cs = conn.prepareCall("{call usp_habilitarNotas(?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, 1);
            cs.setString(2, sFechaCierreInicial);
            cs.setString(3, sFechaCierreFinal);
            cs.setInt(4, nTipoNota);
            cs.setString(5, sIdBimestre);
            cs.setString(6, null);
            cs.setString(7, null);
            cs.setString(8, null);
            cs.setString(9, null);            
            nOk = cs.executeUpdate();
            cs.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Conexion.cerrar(conn);
        }
        return nOk;
    }

    public List<String> obtenerListadoFechas(String sTipoNota) {
        Connection conn = Conexion.conectar();
        CallableStatement cs = null;
        ResultSet rs = null;
        List<String> lstCadena = new ArrayList<String>();
        try {
            cs = conn.prepareCall("{call usp_habilitarNotas(?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, 2);
            cs.setString(2, null);
            cs.setString(3, null);
            cs.setString(4, sTipoNota);
            cs.setString(5, null);
            cs.setString(6, null);
            cs.setString(7, null);
            cs.setString(8, null);
            cs.setString(9, null);            
            rs = cs.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    lstCadena.add(rs.getString(1));
                }
            } else {
                lstCadena.add("");
            }
            cs.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Conexion.cerrar(conn);
        }
        return lstCadena;
    }

    public int obtenerHabilitacionNotasDetalle(int nTipoNota, int nIdCurso, String sIdBimestre, String sIdAlumno, Boolean bHabilitar) {
        Connection conn = Conexion.conectar();
        CallableStatement cs = null;
        ResultSet rs = null;
        int nOk = 0;
        try {
            cs = conn.prepareCall("{call usp_habilitarNotas(?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, 4);
            cs.setString(2, null);
            cs.setString(3, null);
            cs.setInt(4, nTipoNota);
            cs.setString(5, sIdBimestre);
            cs.setString(6, String.valueOf(nIdCurso));
            cs.setString(7, sIdAlumno);
            cs.setBoolean(8, bHabilitar);
            cs.setString(9, null);             
            rs = cs.executeQuery();
            if (rs.next()) {
                nOk = Integer.valueOf(rs.getString(1));
            }
            cs.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Conexion.cerrar(conn);
        }
        return nOk;
    }

    public int obtenerHabilitacionNotasProfesor(String sIdPersonaProfesor, int nIdGrado, String sIdCurso, String sIdBimestre, int nIdNota, String sBloquear) {
        Connection conn = Conexion.conectar();
        CallableStatement cs = null;
        ResultSet rs = null;
        int nOk = 0;
        try {
            cs = conn.prepareCall("{call usp_habilitarNotas(?,?,?,?,?,?,?,?,?)}");
            System.out.println("nIdNota : " + nIdNota);
            System.out.println("sIdBimestre : " + sIdBimestre);
            System.out.println("sIdCurso : " + sIdCurso);
            System.out.println("sIdPersonaProfesor : " + sIdPersonaProfesor);
            System.out.println("Boolean.valueOf(sBloquear) : " + sBloquear);
            System.out.println("Boolean.valueOf(sBloquear) : " + Boolean.valueOf(sBloquear));            
            System.out.println("nIdGrado : " + nIdGrado);
            
            cs.setInt(1, 5);
            cs.setString(2, null);
            cs.setString(3, null);
            cs.setInt(4, nIdNota);
            cs.setString(5, sIdBimestre);
            cs.setString(6, sIdCurso);
            cs.setString(7, sIdPersonaProfesor);
            cs.setString(8, sBloquear);
            cs.setInt(9,nIdGrado);
            rs = cs.executeQuery();
            if (rs.next()) {
                nOk = Integer.valueOf(rs.getString(1));
            }
            cs.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Conexion.cerrar(conn);
        }
        return nOk;
    }
}
