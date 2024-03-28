package com.sisedu.model.DAO;

import com.sisedu.model.bean.Alumno;
import com.sisedu.model.bean.AlumnoDeuda;
import com.sisedu.model.bean.Boleta;
import com.sisedu.model.bean.Deuda;
import com.sisedu.model.bean.Grado;
import com.sisedu.model.bean.Nivel;
import com.sisedu.model.bean.Persona;
import com.sisedu.model.bean.Procedencia;
import com.sisedu.model.bean.TipoMatricula;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistroPagosDAO {

    List<Boleta> listaBoleta = new ArrayList<Boleta>();
    List<Alumno> listaAlumno = new ArrayList<Alumno>();

    public RegistroPagosDAO() {
    }

    public List<Boleta> consultaDatosPagos(String nDocumento, int tipoTransacccion) {
        Connection con = Conexion.conectar();
        listaBoleta = new ArrayList<Boleta>();

        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroConsultaPagos](?, ?)}");
            cs.setInt(1, tipoTransacccion);
            cs.setString(2, nDocumento);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {

                Boleta boleta = new Boleta();
                Alumno alumno = new Alumno();
                Deuda deuda = new Deuda();
                AlumnoDeuda alumnoDeuda = new AlumnoDeuda();
                Persona persona = new Persona();

                alumno.setnId_Alumno(rs.getInt(1));
                deuda.setNidDeuda(rs.getInt(2));
                deuda.setsDescripcion(rs.getString(3));
                alumnoDeuda.setMonto(rs.getString(4));
                persona.setnIdPersona(rs.getInt(6));
                alumnoDeuda.setnIdAlumnoDeuda(rs.getInt(7));
                alumnoDeuda.setsMontoOtros(rs.getString(8));
                boleta.setsBoletaReimpresion(rs.getString(9));
                alumno.setsAnioAcademico(rs.getString(10));

                alumno.setPersona(persona);

                boleta.setAlumno(alumno);
                boleta.setDeuda(deuda);
                boleta.setAlumnoDeuda(alumnoDeuda);

                listaBoleta.add(boleta);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaBoleta;
    }

//    public List<Boleta> consultaDatosPagosDetalle(String nIdDeuda, String nIdAlumno) {
//        Connection con = Conexion.conectar();
//        listaBoleta = new ArrayList<Boleta>();
//        try {
//            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroConsultaPagosDetalle](?, ?)}");
//            cs.setString(1, nIdDeuda);
//            cs.setString(2, nIdAlumno);
//            ResultSet rs = cs.executeQuery();
//            if (rs.next()) {
//                listaBoleta.add(new Boleta(rs.getInt(1), rs.getInt(2), new Deuda(rs.getInt(3), rs.getString(4)), new AlumnoDeuda(rs.getInt(11), rs.getString(5)), rs.getString(6), rs.getString(7), new Alumno(rs.getInt(8), new Persona(rs.getInt(13), rs.getString(9))), rs.getString(10), rs.getBoolean(12)));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            Conexion.cerrar(con);
//        }
//        return listaBoleta;
//    }
    public List<Alumno> consultaDatosAlumnosPagoDetalle(int nIdPersona) {
        Connection con = Conexion.conectar();
        listaAlumno = new ArrayList<Alumno>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroConsultaAlumnosPagoDetalle](?)}");
            cs.setInt(1, nIdPersona);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                listaAlumno.add(new Alumno(rs.getInt(1), new Persona(rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), ""), new Nivel(rs.getString(9)), new Grado(rs.getString(10)), ""));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaAlumno;
    }

    public String ingresarDatosPagos(String sNumeroRecibo, String sMonto, String sMontoOtros, String sMora, int nIdAlumnoDeuda, int nIdAlumno, int nIdDeuda, String Usuario, boolean bCancelado) {
        String mensaje = "";
        Connection conn = Conexion.conectar();
        try {

            CallableStatement cs = conn.prepareCall("{call [dbo].[usp_filtroRegistroPagos](?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            cs.setString(1, sNumeroRecibo);
            cs.setString(2, sMonto);
            cs.setString(3, sMontoOtros);
            cs.setString(4, sMora);
            cs.setInt(5, nIdAlumnoDeuda);
            cs.setInt(6, nIdAlumno);
            cs.setInt(7, nIdDeuda);
            cs.setString(8, Usuario);
            cs.registerOutParameter(9, java.sql.Types.VARCHAR, 100);
            cs.execute();
            mensaje = cs.getString(9);
            return mensaje;
        } catch (SQLException ex) {
            mensaje = "Error : " + ex.getMessage();
            System.out.println(mensaje);
        } finally {
            Conexion.cerrar(conn);
            return mensaje;
        }
    }

    public String editarDatosPagos(int nNumeroRecibo, String sMonto, String sMora, String sObservaciones, int nIdAlumno, int nIdDeuda, int IdAlumnoDeuda, String Usuario, boolean bCancelado) {
        String mensaje = "";
        try {
            Connection conn = Conexion.conectar();
            CallableStatement cs = conn.prepareCall("{call [dbo].[usp_filtroEditarPagos](?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, nNumeroRecibo);
            cs.setString(2, sMonto);
            cs.setString(3, sMora);
            cs.setString(4, sObservaciones);
            cs.setInt(5, nIdAlumno);
            cs.setInt(6, nIdDeuda);
            cs.setInt(7, IdAlumnoDeuda);
            cs.setString(8, (Usuario == null || Usuario.equals("")) ? "" : Usuario);
            cs.setBoolean(9, bCancelado);

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

    public String verificarBoleta(String sNumeroRecibo) {
        String mensaje = "";
        try {
            Connection conn = Conexion.conectar();
            CallableStatement cs = conn.prepareCall("{call [dbo].[usp_filtroVerificarBoleta](?,?)}");
            cs.setString(1,sNumeroRecibo);
            cs.setString(2, "1"); //verificar si existe o no
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
    
        public String obtenerNumeroRecibo() {
        String mensaje = "";
        try {
            Connection conn = Conexion.conectar();
            CallableStatement cs = conn.prepareCall("{call [dbo].[usp_filtroVerificarBoleta](?, ?)}");
            cs.setString(1, "");
            cs.setString(2, "2"); //obtener el recibo actual

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
        
        public String actualizarNumeroRecibo() {
        String mensaje = "";
        try {
            Connection conn = Conexion.conectar();
            CallableStatement cs = conn.prepareCall("{call [dbo].[usp_filtroVerificarBoleta](?, ?)}");
            cs.setString(1, "");
            cs.setString(2, "3"); //obtener el recibo actual

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

    public List<Boleta> consultaDatosPagos(boolean bDni, boolean bApenom, boolean bNivel, boolean bBoleta, boolean bFecha, int Dni, String sNombres, int nNivel, String sBoleta, String sFechaINICIAL, String sFechaFINAL) {
        Connection con = Conexion.conectar();
        listaBoleta = new ArrayList<Boleta>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroConsultaPagosAlumno](?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            cs.setBoolean(1, bDni);
            cs.setBoolean(2, bApenom);
            cs.setBoolean(3, bNivel);
            cs.setBoolean(4, bBoleta);
            cs.setBoolean(5, bFecha);
            cs.setInt(6, Dni);
            cs.setString(7, sNombres);
            cs.setInt(8, nNivel);
            cs.setString(9, sBoleta);
            cs.setString(10, sFechaINICIAL);
            cs.setString(11, sFechaFINAL);


            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                listaBoleta.add(new Boleta(
                        rs.getInt(1),
                        new Alumno(0, new Persona(0, rs.getInt(11), rs.getString(2)), new Nivel(rs.getString(3)), new Grado(rs.getString(4)), ""),
                        new Deuda(0, rs.getString(6)),
                        new AlumnoDeuda(""),
                        rs.getString(5),
                        rs.getString(8),
                        rs.getString(7),
                        rs.getString(9),
                        rs.getString(10), ""));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaBoleta;

    }

    public String obtenerMonto(int nIdAlumnoDeuda) {
        String mensaje = "";
        try {
            Connection conn = Conexion.conectar();
            CallableStatement cs = conn.prepareCall("{call [dbo].[usp_filtroObtenerMonto](?)}");
            cs.setInt(1, nIdAlumnoDeuda);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                mensaje = rs.getString(1);
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
            cs.setString(4, "2");            
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
}
