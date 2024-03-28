package com.sisedu.model.DAO;

import com.sisedu.model.bean.AsistenciaProfesor;
import com.sisedu.model.bean.Horario;
import com.sisedu.model.bean.Profesor;
import com.sisedu.model.bean.Persona;
import com.sisedu.model.bean.ProfesorHorario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class obtenerReporteAsistenciaDAO {

    public obtenerReporteAsistenciaDAO() {
    }
    List<ProfesorHorario> listaProfesorHorario = new ArrayList<ProfesorHorario>();
    List<Profesor> listaProfesorAsistencia = new ArrayList<Profesor>();
    List<Profesor> listaProfesor = new ArrayList<Profesor>();
    List<Horario> listaHorario = new ArrayList<Horario>();
    List<ProfesorHorario> listaHorarioProfesor = new ArrayList<ProfesorHorario>();

    public List<ProfesorHorario> obtenerReporteAsistenciaProfesor(boolean bApenom, boolean bFecha, String sNombres, String sFechaINICIAL, String sFechaFINAL) {
        Connection con = Conexion.conectar();
        listaProfesorHorario = new ArrayList<ProfesorHorario>();

        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroConsultaAsistenciaProfesor](?,?,?,?,?)}");
            cs.setBoolean(1, bApenom);
            cs.setBoolean(2, bFecha);
            cs.setString(3, sNombres);
            cs.setString(4, sFechaINICIAL);
            cs.setString(5, sFechaFINAL);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Persona persona = new Persona();
                persona.setnIdPersona(Integer.valueOf(rs.getString(1)));
                persona.setsDatosPersonales(rs.getString(3));

                AsistenciaProfesor asistenciaProfesor = new AsistenciaProfesor();
                asistenciaProfesor.setsHoraRegistro(rs.getString(4));

                Profesor profesor = new Profesor();
                profesor.setnId_Profesor(Integer.valueOf(rs.getString(2)));
                profesor.setfCantidad(Double.valueOf(rs.getString(6)));
                profesor.setPersona(persona);
                profesor.setAsistenciaProfesor(asistenciaProfesor);

                ProfesorHorario profesorHorario = new ProfesorHorario();
                profesorHorario.setsHoraIngreso(rs.getString(5));
                profesorHorario.setProfesor(profesor);
                listaProfesorHorario.add(profesorHorario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaProfesorHorario;
    }

    public List<Profesor> obtenerReporteAsistenciaProfesorConsolidado(Boolean bFecha, Boolean bProfesor, String sFechaINICIAL, String sFechaFINAL, String sProfesor) {
        Connection con = Conexion.conectar();
        listaProfesorAsistencia = new ArrayList<Profesor>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroConsultaAsistenciaProfesorConsolidado](?,?,?,?,?)}");
            cs.setBoolean(1, bFecha);
            cs.setBoolean(2, bProfesor);
            cs.setString(3, sFechaINICIAL);
            cs.setString(4, sFechaFINAL);
            cs.setString(5, sProfesor);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Persona persona = new Persona();
                persona.setsDatosPersonales(rs.getString(2));
                Profesor profesor = new Profesor();
                profesor.setnId_Profesor(Integer.valueOf(rs.getString(1)));
                profesor.setPersona(persona);
                profesor.setfCantidad(rs.getDouble(3));
                listaProfesorAsistencia.add(profesor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaProfesorAsistencia;
    }

    public List<Profesor> conseguirProfesores() {

        Connection con = Conexion.conectar();
        listaProfesor = new ArrayList<Profesor>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_obtenerVarios](?)}");
            cs.setInt(1, 8);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Persona persona = new Persona();
                persona.setnIdPersona(Integer.valueOf(rs.getString(1)));
                persona.setsDatosPersonales(rs.getString(2));
                Profesor profesor = new Profesor();
                profesor.setPersona(persona);

                listaProfesor.add(profesor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaProfesor;

    }

    public List<Horario> conseguirHorario() {

        Connection con = Conexion.conectar();
        listaHorario = new ArrayList<Horario>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_obtenerVarios](?)}");
            cs.setInt(1, 9);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                listaHorario.add(new Horario(Integer.valueOf(rs.getString(1)), rs.getString(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaHorario;

    }

    public List<ProfesorHorario> conseguirHorarioProfesor(boolean bProfesor, boolean bDiaSemana, String sIdPersona, String sIdDiaSemana) {
        Connection con = Conexion.conectar();
        listaHorarioProfesor = new ArrayList<ProfesorHorario>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroConsultaHorariosProfesor](?,?,?,?)}");
            cs.setBoolean(1, bProfesor);
            cs.setBoolean(2, bDiaSemana);
            cs.setString(3, sIdPersona);
            cs.setString(4, sIdDiaSemana);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Persona persona = new Persona();
                persona.setnIdPersona(Integer.valueOf(rs.getString(1)));
                persona.setsDatosPersonales(rs.getString(2));
                Profesor profesor = new Profesor();
                profesor.setPersona(persona);
                Horario horario = new Horario();
                horario.setsDescripcion(rs.getString(3));
                ProfesorHorario profesorHorario = new ProfesorHorario();
                profesorHorario.setProfesor(profesor);
                profesorHorario.setHorario(horario);

                profesorHorario.setsHoraIngreso(rs.getString(4));
                profesorHorario.setsHoraSalida(rs.getString(5));      
                listaHorarioProfesor.add(profesorHorario);
            }            
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaHorarioProfesor;
    }
}
