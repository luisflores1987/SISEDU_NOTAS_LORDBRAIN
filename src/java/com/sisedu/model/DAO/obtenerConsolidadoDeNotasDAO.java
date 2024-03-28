package com.sisedu.model.DAO;

import com.sisedu.model.bean.Alumno;
import com.sisedu.model.bean.Area;
import com.sisedu.model.bean.Persona;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class obtenerConsolidadoDeNotasDAO {

    List<Area> listaConsolidadoNotas = new ArrayList<Area>();
    List<Alumno> listaAlumnoNotas = new ArrayList<Alumno>();
    List<List<Object>> listaConsolidado = new ArrayList<List<Object>>();

    public List<Area> obtenerAreaxNotas(int nIdGrado) {
        Connection con = Conexion.conectar();
        listaConsolidadoNotas = new ArrayList<Area>();

        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_obtenerVarios](?,?,?)}");
            cs.setInt(1, 12);
            cs.setString(2, null);
            cs.setInt(3, nIdGrado);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Area area = new Area();
                area.setnIdArea(Integer.valueOf(rs.getString(1)));
                area.setsDescripcion(rs.getString(2));
                listaConsolidadoNotas.add(area);
            }
            cs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaConsolidadoNotas;
    }

    public List<List<Object>> obtenerDatosAlumnos(String idGrado) {
        Connection con = Conexion.conectar();
        listaConsolidado = new ArrayList<List<Object>>();

        try {
            PreparedStatement ps = con.prepareStatement("[dbo].[usp_filtroObtenerAlumnosConsolidado] @TIPOTRANSACCION = ?, @IDGRADOP= ?");
            ps.setInt(1, 1);
            ps.setString(2, idGrado);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rmeta = rs.getMetaData();
            int numcols = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                // new list per row
                if (listaConsolidado.isEmpty()) {
                    List<Object> row = new ArrayList<Object>(numcols);
                    for (int i = 1; i <= numcols; i++) {
                        row.add(rmeta.getColumnName(i));
                    }
                    listaConsolidado.add(row);
                }
                List<Object> row = new ArrayList<Object>(numcols);
                for (int j = 1; j <= numcols; j++) {
                    row.add(rs.getString(j));
                }
                listaConsolidado.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaConsolidado;
    }

    public List<List<Object>> obtenerDatosAlumnosNotas(String idGrado, String idArea, String idBimestre) {
        Connection con = Conexion.conectar();
        listaConsolidado = new ArrayList<List<Object>>();

        try {
            PreparedStatement ps = con.prepareStatement("[dbo].[usp_filtroObtenerAlumnosConsolidado] @TIPOTRANSACCION = ?, @IDGRADOP= ?, @IDAREAP= ?, @IDBIMESTREP= ?");
            ps.setInt(1, 2);
            ps.setString(2, idGrado);
            ps.setString(3, idArea);
            ps.setString(4, idBimestre);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rmeta = rs.getMetaData();
            int numcols = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                // new list per row
                if (listaConsolidado.isEmpty()) {
                    List<Object> row = new ArrayList<Object>(numcols);
                    for (int i = 1; i <= numcols; i++) {
                        row.add(rmeta.getColumnName(i));
                    }
                    listaConsolidado.add(row);
                }
                List<Object> row = new ArrayList<Object>(numcols);
                for (int j = 1; j <= numcols; j++) {
                    row.add(rs.getString(j));
                }
                listaConsolidado.add(row);
            }
            for (List<Object> listadoInterno : listaConsolidado) {
                System.out.println(" arregloObject : " + listadoInterno.toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaConsolidado;
    }

    public List<List<Object>> obtenerDatosAlumnosPuntaje(String idGrado, String idBimestre) {
        Connection con = Conexion.conectar();
        listaConsolidado = new ArrayList<List<Object>>();

        try {
            PreparedStatement ps = con.prepareStatement("[dbo].[usp_filtroObtenerAlumnosConsolidado] @TIPOTRANSACCION = ?, @IDGRADOP= ?, @IDBIMESTREP = ?");
            ps.setInt(1, 3);
            ps.setString(2, idGrado);
            ps.setString(3, idBimestre);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rmeta = rs.getMetaData();
            int numcols = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                // new list per row
                if (listaConsolidado.isEmpty()) {
                    List<Object> row = new ArrayList<Object>(numcols);
                    for (int i = 1; i <= numcols; i++) {
                        row.add(rmeta.getColumnName(i));
                    }
                    listaConsolidado.add(row);
                }
                List<Object> row = new ArrayList<Object>(numcols);
                for (int j = 1; j <= numcols; j++) {
                    row.add(rs.getString(j));
                }
                listaConsolidado.add(row);
            }
            for (List<Object> listadoInterno : listaConsolidado) {
                System.out.println(" arregloObject : " + listadoInterno.toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaConsolidado;
    }
}
