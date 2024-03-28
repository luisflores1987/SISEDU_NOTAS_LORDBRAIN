/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.model.DAO;

import com.sisedu.model.bean.Horario;
import com.sisedu.model.bean.Persona;
import com.sisedu.model.bean.Profesor;
import com.sisedu.model.bean.ProfesorHorario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MAQ
 */
public class registroHorariosDAO {

    public registroHorariosDAO() {
    }
    List<Profesor> listaProfesor = new ArrayList<Profesor>();
    List<ProfesorHorario> listaProfesorHorario = new ArrayList<ProfesorHorario>();

    public List<Profesor> obtenerProfesor(String tipoBusqueda, String busqueda) {
        Connection con = Conexion.conectar();
        listaProfesor = new ArrayList<Profesor>();
        Profesor profesor = null;
        Persona persona = null;
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroConsultaProfesor](?,?,?,?)}");
            cs.setInt(1, 1);
            cs.setString(2, tipoBusqueda);
            cs.setString(3, (tipoBusqueda.equalsIgnoreCase("1")) ? busqueda : null);
            cs.setString(4, (tipoBusqueda.equalsIgnoreCase("2")) ? busqueda : null);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                persona = new Persona();
                persona.setnNumeroDocumento(Integer.valueOf(rs.getString(2)));
                persona.setsDatosPersonales(rs.getString(3));
                profesor = new Profesor();
                profesor.setnId_Profesor(Integer.valueOf(rs.getString(1)));
                profesor.setPersona(persona);
                listaProfesor.add(profesor);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }

        return listaProfesor;
    }

    public String registrarHorario(int tipoTransaccion, String sFechaIngreso, String sFechaSalida, String sCadIdHorario, int nIdProfesor, int nIdHorario, Boolean bHorario) {
        Connection con = Conexion.conectar();
        String sMensaje = "";
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroRegistroHorarios](?,?,?,?,?,?,?)}");
            cs.setInt(1, tipoTransaccion);
            cs.setString(2, sFechaIngreso);
            cs.setString(3, sFechaSalida);
            cs.setString(4, sCadIdHorario);
            cs.setInt(5, nIdProfesor);
            cs.setBoolean(6, bHorario);
            cs.setInt(7, nIdHorario);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                sMensaje = rs.getString(1);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return sMensaje;
    }

    public List<ProfesorHorario> obtenerHorarioProfesor(String sIdProfesor) {
    Connection con = Conexion.conectar();
        listaProfesorHorario = new ArrayList<ProfesorHorario>();
        ProfesorHorario profesorHorario = null;
        Profesor profesor = null;
        Horario horario = null;
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroObtenerHorarioProfesor](?)}");
            cs.setString(1, sIdProfesor);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                profesorHorario = new ProfesorHorario();
                profesor = new Profesor();
                horario = new Horario();
                profesor.setnId_Profesor(Integer.valueOf(rs.getString(1)));
                horario.setnIdHorario(Integer.valueOf(rs.getString(2)));
                profesorHorario.setsHoraIngreso(rs.getString(3));
                profesorHorario.setsHoraSalida(rs.getString(4));
                profesorHorario.setProfesor(profesor);
                profesorHorario.setHorario(horario);
                
                listaProfesorHorario.add(profesorHorario);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }

        return listaProfesorHorario;
    }
}
