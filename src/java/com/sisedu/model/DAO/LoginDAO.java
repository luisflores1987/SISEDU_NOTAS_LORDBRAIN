package com.sisedu.model.DAO;

import com.sisedu.model.bean.Grado;
import com.sisedu.model.bean.Nivel;
import com.sisedu.model.bean.Persona;
import com.sisedu.model.bean.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO {

    List<Persona> listaPersona = new ArrayList<Persona>();
    List<Grado> listaGrado = new ArrayList<Grado>();

    public LoginDAO() {
    }

    public boolean obtenerUsuarios(String sUserName, String sPassword) {
        Boolean logueado = false;
        Connection conn = Conexion.conectar();
        CallableStatement cs = null;
        ResultSet rs = null;
        listaPersona = new ArrayList<Persona>();
        try {
            cs = conn.prepareCall("{call usp_AutenticarUsuario(?, ?)}");
            cs.setString(1, sUserName);
            cs.setString(2, sPassword);
            rs = cs.executeQuery();
            while (rs.next()) {
                listaPersona.add(new Persona(rs.getInt(1), rs.getString(2), rs.getInt(3)));
                logueado = true;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Conexion.cerrar(conn);
        }
        return logueado;
    }

    public List<Grado> obtenerGrado(String sNivel) {
        Connection conn = Conexion.conectar();
        CallableStatement cs = null;
        ResultSet rs = null;
        listaGrado = new ArrayList<Grado>();
        try {
            cs = conn.prepareCall("{call usp_obtenerDatosGenerales (?, ?)}");
            cs.setInt(1, 2);
            cs.setString(2, sNivel);
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

    public List<Persona> getListaPersona() {
        return listaPersona;
    }

    public void setListaPersona(List<Persona> listaPersona) {
        this.listaPersona = listaPersona;
    }
}
