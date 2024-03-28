/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.model.DAO;

import com.sisedu.model.bean.Alumno;
import com.sisedu.model.bean.AlumnoDeuda;
import com.sisedu.model.bean.Boleta;
import com.sisedu.model.bean.Deuda;
import com.sisedu.model.bean.Grado;
import com.sisedu.model.bean.Nivel;
import com.sisedu.model.bean.Persona;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MAQ
 */
public class ConsultaDeudasDAO {

    List<AlumnoDeuda> listaAlumnoDeuda = new ArrayList<AlumnoDeuda>();

    public ConsultaDeudasDAO() {
    }

    public List<AlumnoDeuda> consultaDatosDeudas(boolean bNivel, boolean bGrado, String sNivel, String sGrado, String sAñoAcademico) {
        Connection con = Conexion.conectar();
        listaAlumnoDeuda = new ArrayList<AlumnoDeuda>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroConsultaDeudasAlumno](?, ?, ?, ?, ?)}");

            cs.setBoolean(1, bNivel);
            cs.setBoolean(2, bGrado);
            cs.setString(3, sNivel);
            cs.setString(4, sGrado);
            cs.setString(5, sAñoAcademico);

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                listaAlumnoDeuda.add(new AlumnoDeuda(
                                                        new Alumno(0, new Persona(0, rs.getString(1))),
                                                        rs.getString(2),
                                                        rs.getString(3),
                                                        rs.getString(4),
                                                        rs.getString(5),
                                                        rs.getString(6),
                                                        rs.getString(7),
                                                        rs.getString(8),
                                                        rs.getString(9),
                                                        rs.getString(10),
                                                        rs.getString(11),
                                                        rs.getString(12),
                                                        rs.getString(13)
                                                    )
                                    );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaAlumnoDeuda;
    }

    public String conseguirFecha() {

        Connection con = Conexion.conectar();
        String fecha="";
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_obtenerVarios](?)}");
            cs.setInt(1, 7);
            ResultSet rs = cs.executeQuery();
            if(rs.next()){
                fecha = rs.getString(1);
            }            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return fecha;

    }
    
    public String obtenerMontoTotalDeuda(String idAlumno, String numeroRecibo) {

        Connection con = Conexion.conectar();
        String montoTotalPagar="";
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[s_obtenerMontoTotalPagar](?, ?)}");
            cs.setString(1, idAlumno);
            cs.setString(2, numeroRecibo);            
            ResultSet rs = cs.executeQuery();
            if(rs.next()){
                montoTotalPagar = rs.getString(6); //suma monto total
            }            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return montoTotalPagar;

    }    
    
}
