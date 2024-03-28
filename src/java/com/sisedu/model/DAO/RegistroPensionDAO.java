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

public class RegistroPensionDAO {

    List<Alumno> listaAlumno = new ArrayList<Alumno>();
    List<Nivel> listaNivel = new ArrayList<Nivel>();
    List<Grado> listaGrado = new ArrayList<Grado>();
    List<AlumnoDeuda> listaAlumnoDeuda = new ArrayList<AlumnoDeuda>();

    public RegistroPensionDAO() {
    }

    public List<Alumno> consultaDatosAlumnos(String paciente, int nIdNivel, String sAñoAcademico) {
        Connection con = Conexion.conectar();
        listaAlumno = new ArrayList<Alumno>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroConsultaAlumnosNivelPensiones](?, ?, ?)}");
            cs.setInt(1, nIdNivel);
            cs.setString(2, paciente);
            cs.setString(3, sAñoAcademico);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                listaAlumno.add(new Alumno(rs.getInt(1), new Persona(rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7))));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaAlumno;
    }

    public String ingresarPensiones(int nIdNivel, int nIdGrado, int nIdPersona, String sPension, int nIdAlumnoDeuda, String sAnioAcademico) {
        String mensaje = "";
        try {
            Connection conn = Conexion.conectar();
            CallableStatement cs = conn.prepareCall("{call [dbo].[usp_filtroRegistroPensiones](?, ?, ?, ?, ?, ?, ?)}");
            if (nIdNivel != 0) {
                cs.setInt(1, 1);
            } else if (nIdNivel == 0 && nIdGrado != 0) {
                cs.setInt(1, 2);
            } else if (nIdNivel == 0 && nIdGrado == 0 & nIdPersona != 0) {
                cs.setInt(1, 3);
            } else {
                cs.setInt(1, 4);
            }

            cs.setInt(2, nIdNivel);
            cs.setInt(3, nIdGrado);
            cs.setInt(4, nIdPersona);
            cs.setString(5, sPension);
            cs.setInt(6, nIdAlumnoDeuda);
            cs.setString(7, sAnioAcademico);

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

    public List<Nivel> obtenerNivelPension(String sAñoAcademico) {
        Connection conn = Conexion.conectar();
        CallableStatement cs = null;
        ResultSet rs = null;
        listaNivel = new ArrayList<Nivel>();
        try {
            cs = conn.prepareCall("{call [dbo].[usp_registroPensiones] (?, ?, ?)}");
            cs.setInt(1, 1);
            cs.setString(2, null);
            cs.setString(3, sAñoAcademico);
            rs = cs.executeQuery();
            while (rs.next()) {
                Nivel nivel = new Nivel();
                nivel.setnIdNivel(rs.getInt(1));
                nivel.setsDescripcion(rs.getString(2));
                nivel.setsPension(rs.getString(3));
                nivel.setsAnioAcademico(rs.getString(4));
                listaNivel.add(nivel);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Conexion.cerrar(conn);
        }
        return listaNivel;
    }

    public List<Grado> obtenerGradoPension(String sNivel, String sAñoAcademico) {
        Connection conn = Conexion.conectar();
        CallableStatement cs = null;
        ResultSet rs = null;
        listaGrado = new ArrayList<Grado>();
        try {
            cs = conn.prepareCall("{call usp_registroPensiones (?, ?, ?)}");
            cs.setInt(1, 2);
            cs.setString(2, sNivel);
            cs.setString(3, sAñoAcademico);
            rs = cs.executeQuery();
            while (rs.next()) {
                Grado grado = new Grado();
                grado.setnIdGrado(rs.getInt(1));
                grado.setsDescripcion(rs.getString(2));
                grado.setsPension(rs.getString(3));

                listaGrado.add(new Grado(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Conexion.cerrar(conn);
        }
        return listaGrado;
    }

    public List<AlumnoDeuda> consultaDatosPagos(int nIdPersona, String sAñoAcademico) {
        Connection conn = Conexion.conectar();
        CallableStatement cs = null;
        ResultSet rs = null;
        listaAlumnoDeuda = new ArrayList<AlumnoDeuda>();
        try {
            cs = conn.prepareCall("{call usp_filtroConsultaNivelGradoAlumnoMes (?, ?)}");
            cs.setInt(1, nIdPersona);
            cs.setString(2, sAñoAcademico);
            rs = cs.executeQuery();
            while (rs.next()) {
                AlumnoDeuda alumnoDeuda = new AlumnoDeuda();
                Alumno alumno = new Alumno();
                Persona persona = new Persona();
                Deuda deuda = new Deuda();

                persona.setnIdPersona(Integer.parseInt(rs.getString(1)));

                alumno.setPersona(persona);

                deuda.setsDescripcion(rs.getString(4));

                alumnoDeuda.setAlumno(alumno);
                alumnoDeuda.setMonto(rs.getString(2));
                alumnoDeuda.setnIdAlumnoDeuda(Integer.valueOf(rs.getString(3)));
                alumnoDeuda.setDeuda(deuda);

                listaAlumnoDeuda.add(alumnoDeuda);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(conn);
        }
        return listaAlumnoDeuda;
    }

    public List<Nivel> obtenerNivelMatricula(String sAñoAcademico) {
        Connection conn = Conexion.conectar();
        CallableStatement cs = null;
        ResultSet rs = null;
        listaNivel = new ArrayList<Nivel>();
        try {
            cs = conn.prepareCall("{call [dbo].[usp_registroMatricula] (?, ?, ?)}");
            cs.setInt(1, 1);
            cs.setString(2, null);
            cs.setString(3, sAñoAcademico);
            rs = cs.executeQuery();
            while (rs.next()) {
                Nivel nivel = new Nivel();
                nivel.setnIdNivel(rs.getInt(1));
                nivel.setsDescripcion(rs.getString(2));
                nivel.setsPension(rs.getString(3));
                nivel.setsAnioAcademico(rs.getString(4));
                listaNivel.add(nivel);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Conexion.cerrar(conn);
        }
        return listaNivel;
    }

    public List<Grado> obtenerGradoMatricula(String sdescripcionNivel, String sAñoAcademico) {
        Connection conn = Conexion.conectar();
        CallableStatement cs = null;
        ResultSet rs = null;
        listaGrado = new ArrayList<Grado>();
        try {
            cs = conn.prepareCall("{call usp_registroMatricula (?, ?, ?)}");
            cs.setInt(1, 2);
            cs.setString(2, sdescripcionNivel);
            cs.setString(3, sAñoAcademico);
            rs = cs.executeQuery();
            while (rs.next()) {
                Grado grado = new Grado();
                grado.setnIdGrado(rs.getInt(1));
                grado.setsDescripcion(rs.getString(2));
                grado.setsPension(rs.getString(3));

                listaGrado.add(new Grado(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Conexion.cerrar(conn);
        }
        return listaGrado;
    }

    public List<Alumno> consultaDatosAlumnosMatricula(String paciente, int nIdNivel, String sAñoAcademico) {
        Connection con = Conexion.conectar();
        listaAlumno = new ArrayList<Alumno>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroConsultaAlumnosNivelMatricula](?, ?, ?)}");
            cs.setInt(1, nIdNivel);
            cs.setString(2, paciente);
            cs.setString(3, sAñoAcademico);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                listaAlumno.add(new Alumno(rs.getInt(1), new Persona(rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7))));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaAlumno;
    }
    
    public String ingresarPensionesMatricula(int nIdNivel, int nIdGrado, int nIdPersona, String sPension, int nIdAlumnoDeuda, String sAnioAcademico) {
        String mensaje = "";
        try {
            Connection conn = Conexion.conectar();
            CallableStatement cs = conn.prepareCall("{call [dbo].[usp_filtroRegistroMatricula](?, ?, ?, ?, ?, ?, ?)}");
            if (nIdNivel != 0) {
                cs.setInt(1, 1);
            } else if (nIdNivel == 0 && nIdGrado != 0) {
                cs.setInt(1, 2);
            } else if (nIdNivel == 0 && nIdGrado == 0 & nIdPersona != 0) {
                cs.setInt(1, 3);
            } else {
                cs.setInt(1, 4);
            }

            cs.setInt(2, nIdNivel);
            cs.setInt(3, nIdGrado);
            cs.setInt(4, nIdPersona);
            cs.setString(5, sPension);
            cs.setInt(6, nIdAlumnoDeuda);
            cs.setString(7, sAnioAcademico);

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
}
