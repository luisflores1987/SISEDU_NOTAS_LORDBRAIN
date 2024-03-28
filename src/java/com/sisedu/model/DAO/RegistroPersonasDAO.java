package com.sisedu.model.DAO;

import com.sisedu.model.bean.Alumno;
import com.sisedu.model.bean.AlumnoApoderado;
import com.sisedu.model.bean.AlumnoProcedencia;
import com.sisedu.model.bean.Apoderado;
import com.sisedu.model.bean.Documento;
import com.sisedu.model.bean.General;
import com.sisedu.model.bean.Grado;
import com.sisedu.model.bean.GradoInstruccion;
import com.sisedu.model.bean.Nivel;
import com.sisedu.model.bean.Parentesco;
import com.sisedu.model.bean.Persona;
import com.sisedu.model.bean.Procedencia;
import com.sisedu.model.bean.Profesor;
import com.sisedu.model.bean.TipoMatricula;
import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class RegistroPersonasDAO {

    List<Alumno> listaAlumno = new ArrayList<Alumno>();
    List<Profesor> listaProfesor = new ArrayList<Profesor>();
    List<AlumnoApoderado> listaAlumnoApoderado = new ArrayList<AlumnoApoderado>();
    List<Documento> listaDocumento = new ArrayList<Documento>();
    List<Parentesco> listaParentesco = new ArrayList<Parentesco>();
    List<GradoInstruccion> listaGradoInstruccion = new ArrayList<GradoInstruccion>();
    List<Nivel> listaNivel = new ArrayList<Nivel>();
    List<Grado> listaGrado = new ArrayList<Grado>();
    List<Procedencia> listaProcedencia = new ArrayList<Procedencia>();
    List<AlumnoProcedencia> listaAlumnoProcedencia = new ArrayList<AlumnoProcedencia>();
    List<TipoMatricula> listaTipoMatricula = new ArrayList<TipoMatricula>();
    List<General> listaGeneral = new ArrayList<General>();

    public RegistroPersonasDAO() {
    }

    public String ingresarDatosAlumnos(int nNumeroDocumento, String sApPaterno, String sApMaterno,
            String sNombres, String sFechaNacimiento, String nSexo, int nIdNivel, int nIdGrado, String sUsuarioCreador) {
        String mensaje = "";
        try {
            Connection conn = Conexion.conectar();
            CallableStatement cs = conn.prepareCall("{call [dbo].[usp_filtroRegistroAlumnos](?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, nNumeroDocumento);
            cs.setString(2, sApPaterno);
            cs.setString(3, sApMaterno);
            cs.setString(4, sNombres);
            cs.setString(5, sFechaNacimiento);
            cs.setString(6, nSexo);
            cs.setInt(7, nIdNivel);
            cs.setInt(8, nIdGrado);
            cs.setString(9, sUsuarioCreador);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                mensaje = rs.getString(1) + "," + rs.getString(2);
            }
            Conexion.cerrar(conn);
            return mensaje;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Alumno> consultaDatosAlumnos(String paciente, String filtro) {
        Connection con = Conexion.conectar();
        listaAlumno = new ArrayList<Alumno>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroConsultaAlumnos](?, ?, ?, ?)}");
            cs.setString(1, filtro);
            cs.setString(2, (filtro.equalsIgnoreCase("1")) ? paciente : null);
            cs.setString(3, (filtro.equalsIgnoreCase("2")) ? paciente : null);
            cs.setString(4, "1");
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {

                Alumno alumno = new Alumno();
                Persona persona = new Persona();

                alumno.setnId_Alumno(rs.getInt(1));
                persona.setnIdPersona(rs.getInt(2));
                persona.setnNumeroDocumento(rs.getInt(3));
                persona.setsApPaterno(rs.getString(4));
                persona.setsApMaterno(rs.getString(5));
                persona.setsNombreCompleto(rs.getString(6));
                alumno.setsAnioAcademico(rs.getString(7));
                alumno.setTipoMatricula(new TipoMatricula(rs.getString(8)));
                alumno.setPersona(persona);
                alumno.setsCodigoColor(rs.getString(9));

                listaAlumno.add(alumno);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaAlumno;
    }

    public List<AlumnoProcedencia> consultaDatosAlumnosDetalle(int nIdPersona, String sAnio) {
        Connection con = Conexion.conectar();
        listaAlumnoProcedencia = new ArrayList<AlumnoProcedencia>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroConsultaAlumnosDetalle](?, ?, ?)}");
            cs.setInt(1, nIdPersona);
            cs.setInt(2, 1);
            cs.setString(3, sAnio);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                listaAlumnoProcedencia.add(new AlumnoProcedencia(
                        new Alumno(
                        rs.getInt(1),
                        new Persona(rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(11)),
                        new Nivel(rs.getInt(9)),
                        new Grado(rs.getInt(10)),
                        rs.getString(13),
                        rs.getString(17),
                        new TipoMatricula(rs.getInt(18)),
                        rs.getString(19)),
                        new Procedencia(rs.getInt(14), ""),
                        rs.getInt(15),
                        rs.getString(16)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaAlumnoProcedencia;
    }

    public List<AlumnoApoderado> consultaDatosAlumnosApoderadoDetalle(int nIdPersona, String sAnio) {
        Connection con = Conexion.conectar();
        listaAlumnoApoderado = new ArrayList<AlumnoApoderado>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroConsultaAlumnosDetalle](?, ?, ?)}");
            cs.setInt(1, nIdPersona);
            cs.setInt(2, 2);
            cs.setString(3, sAnio);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                listaAlumnoApoderado.add(new AlumnoApoderado(
                        new Apoderado(rs.getString(3), rs.getString(6), new GradoInstruccion(rs.getInt(7), "")),
                        new Parentesco(rs.getInt(4), ""),
                        rs.getString(5)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaAlumnoApoderado;
    }

    public List<Nivel> obtenerNivel() {
        Connection conn = Conexion.conectar();
        CallableStatement cs = null;
        ResultSet rs = null;
        listaNivel = new ArrayList<Nivel>();
        try {
            cs = conn.prepareCall("{call [dbo].[usp_obtenerVarios] (?)}");
            cs.setInt(1, 1);
            rs = cs.executeQuery();
            while (rs.next()) {
                listaNivel.add(new Nivel(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Conexion.cerrar(conn);
        }
        return listaNivel;
    }

    public List<Grado> obtenerGrado() {
        Connection conn = Conexion.conectar();
        CallableStatement cs = null;
        ResultSet rs = null;
        listaGrado = new ArrayList<Grado>();
        try {
            cs = conn.prepareCall("{call [dbo].[usp_obtenerVarios] (?)}");
            cs.setInt(1, 2);
            rs = cs.executeQuery();
            while (rs.next()) {
                listaGrado.add(new Grado(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Conexion.cerrar(conn);
        }
        return listaGrado;
    }

    public List<Documento> obtenerDocumentos() {
        Connection con = Conexion.conectar();
        listaDocumento = new ArrayList<Documento>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_obtenerVarios] (?)}");
            cs.setInt(1, 3);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                listaDocumento.add(new Documento(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaDocumento;
    }

    public List<Parentesco> obtenerParentesco() {

        Connection con = Conexion.conectar();
        listaParentesco = new ArrayList<Parentesco>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_obtenerVarios] (?)}");
            cs.setInt(1, 4);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                listaParentesco.add(new Parentesco(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaParentesco;

    }

    public List<GradoInstruccion> obtenerGradoInstruccion() {
        Connection con = Conexion.conectar();
        listaGradoInstruccion = new ArrayList<GradoInstruccion>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_obtenerVarios] (?)}");
            cs.setInt(1, 5);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                listaGradoInstruccion.add(new GradoInstruccion(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaGradoInstruccion;
    }

    public List<Procedencia> obtenerProcedencia() {

        Connection con = Conexion.conectar();
        listaProcedencia = new ArrayList<Procedencia>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_obtenerVarios] (?, ?)}");
            cs.setInt(1, 6);
            cs.setString(2, null);

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                listaProcedencia.add(new Procedencia(rs.getInt(1), rs.getString(2)));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaProcedencia;

    }

    public String ingresarDatosAlumnos(
            int nNumeroDocumento,  //1
            String apellidoPaterno,     //2
            String apellidoMaterno,     //3
            String nombres,                  //4
            String fechaNacimiento,    //5
            String sexo,                          //6
            int nIdNivel,                           //7
            int nIdGrado,                         //8
            String txtUsuario,                 //9 
            String DireccionActual,       //10
            String NombresAp,             //11
            String nTelefonoAp,            //12
            int nProcedencia,                //13
            String OtroProc,                   //14
            int nParentescoAp,             //15 
            int nGradoInstruccionAp,  //16 
            String sObservacion,         //17
            String sDocumentos,        //18
            String NombresAa,            //19
            String nTelefonoAa,          //20
            int nParentescoAa,           //21
            String sObservacionAa,  //22 
            int nGradoInstruccionAa, //23
            String anioAcademico,    //24
            int nTipoMatricula) {
        String mensaje = "";
        Connection conn = Conexion.conectar();
        try {
            CallableStatement cs = conn.prepareCall("{call [dbo].[usp_filtroRegistroAlumnos](?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, nNumeroDocumento);
            cs.setString(2, apellidoPaterno);
            cs.setString(3, apellidoMaterno);
            cs.setString(4, nombres);
            cs.setString(5, fechaNacimiento);
            cs.setString(6, sexo);
            cs.setInt(7, nIdNivel);
            cs.setInt(8, nIdGrado);
            cs.setString(9, txtUsuario);
            cs.setString(10, DireccionActual);
            cs.setString(11, NombresAp);
            cs.setString(12, nTelefonoAp);
            cs.setInt(13, nProcedencia);
            cs.setInt(14, nParentescoAp);
            cs.setInt(15, nGradoInstruccionAp);
            cs.setString(16, sObservacion);
            cs.setString(17, sDocumentos);

            //----
            cs.setString(18, NombresAa);
            cs.setString(19, nTelefonoAa);
            cs.setInt(20, nParentescoAa);
            cs.setString(21, sObservacionAa);
            cs.setInt(22, nGradoInstruccionAa);
            cs.setString(23, OtroProc);
            cs.setString(24, anioAcademico);
            cs.setInt(25, nTipoMatricula);
            cs.registerOutParameter(26, java.sql.Types.VARCHAR, 100);
            cs.execute();
            mensaje = cs.getString(26);
        } catch (SQLException ex) {
            mensaje = "Error : " + ex.getMessage();
            System.out.println(mensaje);
        } finally {
            Conexion.cerrar(conn);
            return mensaje;
        }
    }

    public String editarDatosAlumnos(int nNumeroDocumento, String apellidoPaterno, String apellidoMaterno, String nombres, String fechaNacimiento, String sexo,
            int nIdNivel, int nIdGrado, String txtUsuario, String DireccionActual, String NombresAp, String nTelefonoAp, int nProcedencia,
            String OtroProc, int nParentescoAp, int nGradoInstruccionAp, String sObservacionAp, String sDocumentos, String NombresAa, String nTelefonoAa,
            int nParentescoAa, String sObservacionAa, int nGradoInstruccionAa, int nIdPersona, int nTipoGuardar, String sAnioAcademico, int nTipoMatricula, Boolean bRepite) {
        String mensaje = "";
        Connection conn = Conexion.conectar();
        try {
            CallableStatement cs = conn.prepareCall("{call [dbo].[usp_filtroEditarAlumno](?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, nNumeroDocumento);
            cs.setString(2, apellidoPaterno);
            cs.setString(3, apellidoMaterno);
            cs.setString(4, nombres);
            cs.setString(5, fechaNacimiento);
            cs.setString(6, sexo);
            cs.setInt(7, nIdNivel);
            cs.setInt(8, nIdGrado);
            cs.setString(9, txtUsuario);
            cs.setString(10, DireccionActual);
            cs.setString(11, NombresAp);
            cs.setString(12, nTelefonoAp);
            cs.setInt(13, nProcedencia);
            cs.setInt(14, nParentescoAp);
            cs.setInt(15, nGradoInstruccionAp);
            cs.setString(16, sObservacionAp);
            cs.setString(17, sDocumentos);
            cs.setString(18, NombresAa);
            cs.setString(19, nTelefonoAa);
            cs.setInt(20, nParentescoAa);
            cs.setString(21, sObservacionAa);
            cs.setInt(22, nGradoInstruccionAa);
            cs.setString(23, OtroProc);
            cs.setInt(24, nIdPersona);
            cs.setString(25, sAnioAcademico);
            cs.setInt(26, nTipoMatricula);
            cs.registerOutParameter(27, java.sql.Types.VARCHAR, 100);
            cs.setBoolean(28, bRepite);
            System.out.println("brepite es  : " + bRepite);
            cs.execute();
            mensaje = cs.getString(27);

        } catch (SQLException ex) {
            mensaje = "Error : " + ex.getMessage();
            System.out.println(mensaje);
        } finally {
            Conexion.cerrar(conn);
            return mensaje;
        }
    }

    public String ingresarDatosProfesor(
            int nNumeroDocumento, //1
            String apellidoPaterno, //2
            String apellidoMaterno, //3
            String nombres, //4
            String fechaNacimiento, //5
            String sexo, //6
            String sCodigo, //7
            String txtUsuario, //8
            String DireccionActual, //9
            int nTipoPersona, //10            
            String sFechaIngreso) //11
    {
        String mensaje = "";
        try {
            Connection conn = Conexion.conectar();
            CallableStatement cs = conn.prepareCall("{call [dbo].[usp_filtroRegistroProfesor](?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, nNumeroDocumento);
            cs.setString(2, apellidoPaterno);
            cs.setString(3, apellidoMaterno);
            cs.setString(4, nombres);
            cs.setString(5, fechaNacimiento);
            cs.setString(6, sexo);
            cs.setInt(7, Integer.parseInt(sCodigo));
            cs.setString(8, txtUsuario);
            cs.setString(9, DireccionActual);
            cs.setString(10, sFechaIngreso);
            cs.setInt(11, nTipoPersona);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                mensaje = rs.getString(1) + "," + rs.getString(2);
            }
            Conexion.cerrar(conn);
            return mensaje;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String editarDatosProfesor(
            int nIdPersona, //1 
            int nNumeroDocumento, //2
            String apellidoPaterno, //3
            String apellidoMaterno, //4
            String nombres, //5
            String fechaNacimiento, //6
            String sexo, //7
            String sCodigo, //8
            String txtUsuario, //9
            String DireccionActual, //10      
            String sFechaIngreso) //11
    {

        String mensaje = "";
        try {
            Connection conn = Conexion.conectar();
            CallableStatement cs = conn.prepareCall("{call [dbo].[usp_filtroEditarProfesor](?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, nNumeroDocumento);
            cs.setString(2, apellidoPaterno);
            cs.setString(3, apellidoMaterno);
            cs.setString(4, nombres);
            cs.setString(5, fechaNacimiento);
            cs.setString(6, sexo);
            cs.setInt(7, Integer.valueOf(sCodigo));
            cs.setString(8, txtUsuario);
            cs.setString(9, DireccionActual);
            cs.setString(10, sFechaIngreso);
            cs.setInt(11, nIdPersona);

            System.out.println("fechaNacimiento  " + fechaNacimiento);
            System.out.println("sFechaIngreso  " + sFechaIngreso);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                mensaje = rs.getString(1) + "," + rs.getString(2);
            }
            Conexion.cerrar(conn);
            return mensaje;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Profesor> consultaDatosProfesor(String profesor, String filtro) {
        Connection con = Conexion.conectar();
        listaProfesor = new ArrayList<Profesor>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroConsultaProfesor](?, ?, ?, ?)}");
            cs.setInt(1, 1);
            cs.setString(2, filtro);
            cs.setString(3, (filtro.equalsIgnoreCase("1")) ? profesor : null);
            cs.setString(4, (filtro.equalsIgnoreCase("2")) ? profesor : null);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Persona persona = new Persona();
                persona.setnNumeroDocumento(Integer.valueOf(rs.getString(2)));
                persona.setsDatosPersonales(rs.getString(3));
                persona.setnIdPersona(Integer.parseInt(rs.getString(4)));
                Profesor profesor1 = new Profesor();
                profesor1.setnId_Profesor(Integer.valueOf(rs.getString(1)));
                profesor1.setPersona(persona);
                listaProfesor.add(profesor1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaProfesor;
    }

    public List<Profesor> obtenerDatosProfesor(int nIdPersona) {
        Connection con = Conexion.conectar();
        listaProfesor = new ArrayList<Profesor>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroObtenerDatosProfesor](?)}");
            cs.setInt(1, nIdPersona);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                listaProfesor.add(new Profesor(
                        rs.getInt(1),
                        new Persona(rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)),
                        rs.getString(9),
                        rs.getInt(10)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaProfesor;
    }

    public String eliminarDatosAlumnos(int nIdPersona, String sFechaRegistro, String sUsuario, String sAnioAcademico) {
        String mensaje = "";
        Connection conn = Conexion.conectar();
        try {
            CallableStatement cs = conn.prepareCall("{call [dbo].[usp_filtroEliminarAlumno](?, ?, ?, ?)}");
            cs.setInt(1, nIdPersona);
            cs.setString(2, sFechaRegistro);
            cs.setString(3, sUsuario);
            cs.setString(4, sAnioAcademico);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                mensaje = rs.getString(1) + "," + rs.getString(2);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            mensaje = ex.getMessage();

        } finally {

            Conexion.cerrar(conn);
            return mensaje;
        }
    }

    public List<TipoMatricula> obtenerTipoMatricula() {
        Connection conn = Conexion.conectar();
        CallableStatement cs = null;
        ResultSet rs = null;
        listaTipoMatricula = new ArrayList<TipoMatricula>();
        try {
            cs = conn.prepareCall("{call [dbo].[usp_obtenerVarios] (?)}");
            cs.setInt(1, 14);
            rs = cs.executeQuery();
            while (rs.next()) {

                TipoMatricula tipoMatricula = new TipoMatricula();
                tipoMatricula.setnIdMatricula(Integer.valueOf(rs.getString(1)));
                tipoMatricula.setsDescripcion(rs.getString(2));
                listaTipoMatricula.add(tipoMatricula);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Conexion.cerrar(conn);
        }
        return listaTipoMatricula;
    }

    public List<General> obtenerSeccion() {
        Connection conn = Conexion.conectar();
        CallableStatement cs = null;
        ResultSet rs = null;
        listaGeneral = new ArrayList<General>();
        try {
            cs = conn.prepareCall("{call [dbo].[usp_obtenerVarios] (?)}");
            cs.setInt(1, 15);
            rs = cs.executeQuery();
            while (rs.next()) {

                General general = new General();
                general.setnIdGeneral(Integer.valueOf(rs.getString(1)));
                general.setsDescripcion(rs.getString(2));
                listaGeneral.add(general);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Conexion.cerrar(conn);
        }
        return listaGeneral;
    }

    public List<AlumnoApoderado> obtenerAlumnoApoderado() {
        Connection conn = Conexion.conectar();
        CallableStatement cs = null;
        ResultSet rs = null;
        listaAlumnoApoderado = new ArrayList<AlumnoApoderado>();
        try {
            cs = conn.prepareCall("{call [dbo].[usp_obtenerVarios] (?)}");
            cs.setInt(1, 16);
            rs = cs.executeQuery();
            while (rs.next()) {
                AlumnoApoderado alumnoApoderado = new AlumnoApoderado();
                Alumno alumno = new Alumno();
                Persona persona = new Persona();
                persona.setsDatosPersonales(rs.getString(1));
                alumno.setPersona(persona);
                alumnoApoderado.setAlumno(alumno);
                listaAlumnoApoderado.add(alumnoApoderado);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Conexion.cerrar(conn);
        }
        return listaAlumnoApoderado;
    }

    public List<AlumnoApoderado> conseguirApoderado(String snombreApoderado) {
        Connection conn = Conexion.conectar();
        CallableStatement cs = null;
        ResultSet rs = null;
        listaAlumnoApoderado = new ArrayList<AlumnoApoderado>();
        try {
            cs = conn.prepareCall("{call [dbo].[usp_obtenerApoderado] (?,?)}");
            cs.setInt(1, 1);
            cs.setString(2, snombreApoderado);
            rs = cs.executeQuery();
            while (rs.next()) {

                AlumnoApoderado alumnoApoderado = new AlumnoApoderado();
                Apoderado apoderado = new Apoderado();
                Parentesco parentesco = new Parentesco();
                GradoInstruccion gradoInstruccion = new GradoInstruccion();
                parentesco.setnIdParentesco(Integer.valueOf(rs.getString(1)));
                alumnoApoderado.setsObservacion(rs.getString(2));
                apoderado.setsTelefono(rs.getString(3));
                gradoInstruccion.setnIdGradoInstruccion(Integer.valueOf(rs.getString(4)));

                apoderado.setGradoInstruccion(gradoInstruccion);
                alumnoApoderado.setApoderado(apoderado);
                alumnoApoderado.setParentesco(parentesco);

                listaAlumnoApoderado.add(alumnoApoderado);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Conexion.cerrar(conn);
        }
        return listaAlumnoApoderado;
    }

    public String habilitarDatosAlumnos(int nIdPersona, String sFechaRegistro, String sUsuario, String sAnioAcademico) {
        String mensaje = "";
        Connection conn = Conexion.conectar();
        try {
            CallableStatement cs = conn.prepareCall("{call [dbo].[usp_habilitarDatosAlumnos](?, ?, ?, ?)}");
            cs.setInt(1, nIdPersona);
            cs.setString(2, sFechaRegistro);
            cs.setString(3, sUsuario);
            cs.setString(4, sAnioAcademico);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                mensaje = rs.getString(1) + "," + rs.getString(2);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            mensaje = ex.getMessage();
        } finally {
            Conexion.cerrar(conn);
            return mensaje;
        }
    }
}
