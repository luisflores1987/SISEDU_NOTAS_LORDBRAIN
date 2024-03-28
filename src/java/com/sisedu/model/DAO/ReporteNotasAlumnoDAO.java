package com.sisedu.model.DAO;

import com.sisedu.model.bean.Alumno;
import com.sisedu.model.bean.Alumno_Curso;
import com.sisedu.model.bean.Bimestre;
import com.sisedu.model.bean.Persona;
import com.sisedu.model.bean.Grado;
import com.sisedu.model.bean.Nivel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteNotasAlumnoDAO {

    List<Alumno_Curso> listaAlumno = new ArrayList<Alumno_Curso>();
    List<Grado> listaGrado = new ArrayList<Grado>();
    List<Nivel> listaNivel = new ArrayList<Nivel>();

    public List<Alumno_Curso> obtenerAlumnosxBimestre(String sNombres, int nIdBimestre, String filtro, String sIdGrado) {
        Connection con = Conexion.conectar();
        listaAlumno = new ArrayList<Alumno_Curso>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroAlumnoxBimestre](?, ?, ?, ?)}");
            cs.setString(1, filtro);
            cs.setString(2, sNombres);
            cs.setInt(3, nIdBimestre);
            cs.setString(4, sIdGrado);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Persona persona = new Persona();
                persona.setnNumeroDocumento(Integer.valueOf(rs.getString(3)));
                persona.setsDatosPersonales(rs.getString(4));

                Nivel nivel = new Nivel();
                nivel.setnIdNivel(Integer.valueOf(rs.getString(7)));
                nivel.setsDescripcion(rs.getString(5));

                Grado grado = new Grado();
                grado.setsDescripcion(rs.getString(6));

                Alumno alumno = new Alumno();
                alumno.setnId_Alumno(Integer.valueOf(rs.getString(1)));
                alumno.setPersona(persona);
                alumno.setNivel(nivel);
                alumno.setGrado(grado);

                Bimestre bimestre = new Bimestre();
                bimestre.setnIdBimestre(Integer.valueOf(rs.getString(2)));

                Alumno_Curso alumnoCurso = new Alumno_Curso();
                alumnoCurso.setBimestre(bimestre);
                alumnoCurso.setAlumno(alumno);

                listaAlumno.add(alumnoCurso);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaAlumno;
    }

    public List<Grado> obtenerGrado() {
        Connection con = Conexion.conectar();
        listaGrado = new ArrayList<Grado>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_obtenerVarios](?)}");
            cs.setInt(1, 10);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Grado grado = new Grado();
                grado.setnIdGrado(rs.getInt(1));
                grado.setsDescripcion(rs.getString(2));

                listaGrado.add(grado);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaGrado;
    }

    public List<Nivel> obtenerNivelxGrado(int nIdGrado) {
        Connection con = Conexion.conectar();
        listaNivel = new ArrayList<Nivel>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_obtenerVarios](?,?,?)}");
            cs.setInt(1, 11);
            cs.setString(2, null);
            cs.setInt(3, nIdGrado);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Nivel nivel = new Nivel();
                nivel.setnIdNivel(rs.getInt(1));
                nivel.setsDescripcion(rs.getString(2));

                listaNivel.add(nivel);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaNivel;
    }
}
