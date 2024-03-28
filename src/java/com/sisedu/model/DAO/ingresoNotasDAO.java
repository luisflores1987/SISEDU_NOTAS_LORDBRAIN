package com.sisedu.model.DAO;

import com.sisedu.model.bean.Alumno;
import com.sisedu.model.bean.Alumno_Curso;
import com.sisedu.model.bean.AreaEspecialidad;
import com.sisedu.model.bean.Bimestre;
import com.sisedu.model.bean.Curso;
import com.sisedu.model.bean.Grado;
import com.sisedu.model.bean.Grado_Curso;
import com.sisedu.model.bean.Participacionppff;
import com.sisedu.model.bean.Persona;
import com.sisedu.model.bean.Profesor;
import com.sisedu.model.bean.TipoNota;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class ingresoNotasDAO {

    List<Grado> listaProfesorGrado = new ArrayList<Grado>();
    List<Grado_Curso> listaProfesorGradoCurso = new ArrayList<Grado_Curso>();
    List<Alumno_Curso> listaAlumnoTutorCurso = new ArrayList<Alumno_Curso>();
    List<Participacionppff> listaAsistencia = new ArrayList<Participacionppff>();
    List<Bimestre> listaBimestre = new ArrayList<Bimestre>();
    Logger logger = Logger.getLogger(ingresoNotasDAO.class.getName());

    public ingresoNotasDAO() {
    }

    public List<Grado> obtenerReporteAsistenciaProfesorConsolidado(String sIdPersona) {
        Connection con = Conexion.conectar();
        listaProfesorGrado = new ArrayList<Grado>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroConsultaGradoNotas](?, ?)}");
            cs.setInt(1, 1);
            cs.setString(2, sIdPersona);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Grado grado = new Grado();
                grado.setnIdGrado(Integer.valueOf(rs.getString(1)));
                grado.setsDescripcion(rs.getString(2));
                listaProfesorGrado.add(grado);
            }
            cs.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaProfesorGrado;
    }

    public List<Grado_Curso> obtenerCursosxGradoxProfesor(String sIdPersona, int nIdGrado) {
        Connection con = Conexion.conectar();
        listaProfesorGradoCurso = new ArrayList<Grado_Curso>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroConsultaGradoNotas](?, ?, ?)}");
            cs.setInt(1, 2);
            cs.setString(2, sIdPersona);
            cs.setInt(3, nIdGrado);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                AreaEspecialidad area = new AreaEspecialidad();
                area.setnIdArea(Integer.valueOf(rs.getString(1)));
                area.setsDescripcion(rs.getString(3));
                Curso curso = new Curso();
                curso.setnIdCurso(Integer.valueOf(rs.getString(2)));
                curso.setsDescripcion(rs.getString(4));
                curso.setArea(area);
                Profesor profesor = new Profesor();
                profesor.setnId_Profesor(Integer.valueOf(rs.getString(5)));
                Grado_Curso grado_curso = new Grado_Curso();
                grado_curso.setProfesor(profesor);
                grado_curso.setCurso(curso);
                listaProfesorGradoCurso.add(grado_curso);
            }
            cs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaProfesorGradoCurso;
    }

    public List<Alumno_Curso> obtenerAlumnoxCursoxGradoxProfesor(int nIdGrado, int nIdCurso, int nIdBimestre, int nIdProfesor) {
        Connection con = Conexion.conectar();
        List<Alumno_Curso> listaAlumnoCurso = new ArrayList<Alumno_Curso>();
        int i = 0;
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroObtenerCursoAlumno](?,?,?,?,?)}");
            cs.setInt(1, 1);
            cs.setInt(2, nIdGrado);
            cs.setInt(3, nIdCurso);
            cs.setInt(4, nIdBimestre);
            cs.setInt(5, nIdProfesor);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setnId_Alumno(rs.getInt(1));

                Persona persona = new Persona();
                persona.setsDatosPersonales(rs.getString(2));
                alumno.setPersona(persona);
                alumno.setSeccion(rs.getString(5));

                Grado_Curso gradoCurso = new Grado_Curso();

                Alumno_Curso alumnoCurso = new Alumno_Curso();
                alumnoCurso.setsNota1(rs.getString(3));
                alumnoCurso.setsNota2(rs.getString(4));
                alumnoCurso.setsSeccionGrado(rs.getString(6));
                alumnoCurso.setAlumno(alumno);
                alumnoCurso.setnTipoAcceso(rs.getInt(7));
                listaAlumnoCurso.add(alumnoCurso);
            }
            cs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaAlumnoCurso;
    }

    public String registroAlumnoxCursoxGradoxProfesor(int nIdAlumno, int nIdCurso, int nIdBimestre, String scadNotas) {
        Connection con = Conexion.conectar();
        String sMensaje = "";
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroRegistroNotasAlumno](?,?,?,?)}");
            cs.setInt(1, nIdAlumno);
            cs.setInt(2, nIdCurso);
            cs.setString(3, scadNotas);
            cs.setInt(4, nIdBimestre);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                sMensaje = rs.getString(1);
            }
            cs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return sMensaje;
    }

    public List<Alumno_Curso> obtenerAlumnoxTutor(int nIdPersona, int nIdBimestre) {
        Connection con = Conexion.conectar();
        listaAlumnoTutorCurso = new ArrayList<Alumno_Curso>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroObtenerGradoTutor](?,?,?)}");
            cs.setInt(1, 2);
            cs.setInt(2, nIdPersona);
            cs.setInt(3, nIdBimestre);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setnId_Alumno(rs.getInt(1));

                Persona persona = new Persona();
                persona.setsDatosPersonales(rs.getString(2));
                alumno.setPersona(persona);
                alumno.setSeccion(rs.getString(5));

                Grado_Curso gradoCurso = new Grado_Curso();

                Alumno_Curso alumnoCurso = new Alumno_Curso();
                alumnoCurso.setsNota1(rs.getString(3));
                alumnoCurso.setsNota2(rs.getString(4));
                alumnoCurso.setsSeccionGrado(rs.getString(6));
                alumnoCurso.setsTardanzas(rs.getString(7));
                alumnoCurso.setsInasJustificadas(rs.getString(8));
                alumnoCurso.setsInasInjustificadas(rs.getString(9));
                alumnoCurso.setnIdParticipacionPPFF(rs.getInt(10));
                alumnoCurso.setAlumno(alumno);

                listaAlumnoTutorCurso.add(alumnoCurso);
            }
            cs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaAlumnoTutorCurso;
    }

    public List<Grado> obtenerReporteTutorAlumno(String sIdPersona) {
        Connection con = Conexion.conectar();
        listaProfesorGrado = new ArrayList<Grado>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroConsultaGradoNotas](?, ?)}");
            cs.setInt(1, 3);
            cs.setString(2, sIdPersona);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Grado grado = new Grado();
                grado.setnIdGrado(Integer.valueOf(rs.getString(1)));
                grado.setsDescripcion(rs.getString(2));
                grado.setsSeccion(rs.getString(3));
                listaProfesorGrado.add(grado);
            }
            cs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaProfesorGrado;
    }

//    public String registroAlumnoxSugerenciaxTutor(int nIdAlumno, int nIdBimestre, String sNotasSugerencia, String sSugerencia) {
//        Connection con = Conexion.conectar();
//        String sMensaje = "";
//        try {
//            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroRegistroNotasSugerencia](?,?,?,?)}");
//            cs.setInt(1, nIdAlumno);
//            cs.setInt(2, nIdBimestre);
//            cs.setString(3, sNotasSugerencia);
//            cs.setString(4, sSugerencia);
//            logger.info(nIdAlumno);
//            logger.info(nIdBimestre);
//            logger.info(sNotasSugerencia);
//            logger.info(sSugerencia);
//            ResultSet rs = cs.executeQuery();
//            if (rs.next()) {
//                sMensaje = rs.getString(1);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            Conexion.cerrar(con);
//        }
//        return sMensaje;
//    }
    public String registroAlumnoxSugerenciaxTutor(int nIdAlumno, int nIdBimestre, String sNotasSugerencia, String sSugerencia, String sAsistTardanza, String sAsistJustificada, String sAsistInjustificada, int nParticipacion) {
        Connection con = Conexion.conectar();
        String sMensaje = "";
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroRegistroNotasSugerencia](?,?,?,?,?,?,?,?)}");
            cs.setInt(1, nIdAlumno);
            cs.setInt(2, nIdBimestre);
            cs.setString(3, sNotasSugerencia);
            cs.setString(4, sSugerencia);
            cs.setString(5, sAsistTardanza);
            cs.setString(6, sAsistJustificada);
            cs.setString(7, sAsistInjustificada);
            cs.setInt(8, nParticipacion);

            logger.info(nIdAlumno);
            logger.info(nIdBimestre);
            logger.info(sNotasSugerencia);
            logger.info(sSugerencia);
            logger.info(sAsistTardanza);
            logger.info(sAsistJustificada);
            logger.info(sAsistInjustificada);
            logger.info(nParticipacion);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                sMensaje = rs.getString(1);
            }
            cs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return sMensaje;
    }

    public List<Participacionppff> obtenerParticipacionPPFF() {
        Connection con = Conexion.conectar();
        listaAsistencia = new ArrayList<Participacionppff>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_obtenerVarios](?)}");
            cs.setInt(1, 13);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Participacionppff participacion = new Participacionppff();
                participacion.setnIdParticipacionppff(Integer.valueOf(rs.getString(1)));
                participacion.setsDescripcion(rs.getString(2));
                listaAsistencia.add(participacion);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaAsistencia;
    }

    public List<Bimestre> obtenerBimestre() {
        Connection con = Conexion.conectar();
        listaBimestre = new ArrayList<Bimestre>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_habilitarNotas](?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, 3);
            cs.setString(2, null);
            cs.setString(3, null);
            cs.setString(4, null);
            cs.setString(5, null);  
            cs.setString(6, null);  
            cs.setString(7, null);  
            cs.setString(8, null);
            cs.setString(9, null);            
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Bimestre bimestre = new Bimestre();
                bimestre.setnIdBimestre(Integer.valueOf(rs.getString(1)));
                bimestre.setsDescripcion(rs.getString(2));
                bimestre.setsHabilitar(rs.getString(3));
                listaBimestre.add(bimestre);
            }
            System.out.println(listaBimestre.toString());
            cs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaBimestre;
    }
}
