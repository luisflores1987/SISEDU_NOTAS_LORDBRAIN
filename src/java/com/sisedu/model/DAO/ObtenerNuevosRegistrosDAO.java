package com.sisedu.model.DAO;

import com.sisedu.model.bean.AsistenciaProfesor;
import com.sisedu.model.bean.Persona;
import com.sisedu.model.bean.Profesor;
import com.sisedu.model.bean.ProfesorHorario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObtenerNuevosRegistrosDAO {
    
    List<Profesor> listaProfesor = new ArrayList<Profesor>();
    List<ProfesorHorario> listaProfesorHorario = new ArrayList<ProfesorHorario>();

    public ObtenerNuevosRegistrosDAO() {
    }

    public String obtenerRegistro() {
        Connection con = Conexion.conectar();
        String codigoProfesor = "";
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_verificarAsistencia](?)}");
            cs.setInt(1, 1);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                codigoProfesor = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return codigoProfesor;

    }

    public String colocarAsistencia(String sCodigo) {
        Connection con = Conexion.conectar();
        String sResultado = "";
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_verificarAsistencia](?, ?)}");
            cs.setInt(1, 2);
            cs.setString(2, sCodigo);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                sResultado = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return sResultado;
    }

    public List<ProfesorHorario> obtenerProfesorAsistencia(String sCodigo) {
        Connection con = Conexion.conectar();
        listaProfesorHorario = new ArrayList<ProfesorHorario>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_verificarAsistencia](?, ?)}");
            cs.setInt(1, 3);
            cs.setString(2, sCodigo);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                    
                   Persona persona = new Persona();
                   persona.setnIdPersona(Integer.valueOf(rs.getString(1)));
                   persona.setsDatosPersonales(rs.getString(2));
                   
                   Profesor profesor = new Profesor();
                   profesor.setPersona(persona);
                   profesor.setnCodigo(Integer.valueOf(rs.getString(3)));
                   profesor.setsMensaje(rs.getString(4));
                   profesor.setsColorMensaje(rs.getString(5));
                   
                   AsistenciaProfesor asistenciaProfesor = new AsistenciaProfesor();
                   asistenciaProfesor.setsHoraRegistro(rs.getString(7));
                   
                   profesor.setAsistenciaProfesor(asistenciaProfesor);
                   
                   ProfesorHorario profesorHorario = new ProfesorHorario();
                   profesorHorario.setsHoraIngreso(rs.getString(6));
                   profesorHorario.setProfesor(profesor);
                   listaProfesorHorario.add(profesorHorario);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }

        return listaProfesorHorario;
    }
    
    public String[] colocarAsistenciaManual(String sCodigo) {
        Connection con = Conexion.conectar();
        String[] sResultado = new String[2];
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_verificarAsistencia](?, ?)}");
            cs.setInt(1, 4);
            cs.setString(2, sCodigo);
            ResultSet rs  = cs.executeQuery();
            while(rs.next()){
                sResultado[0] = rs.getString(1);
                sResultado[1] = rs.getString(2);                
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return sResultado;
    }    
}
