/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.model.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;

public class Conexion {
    //private static String dbName = "SiseduDesarrolloWallon";
    private static String dbName = "siseduDesarrolloNotasLordBrain";
    //Sprivate static String dbName = "SiseduProduccion";

    public static Connection conectar() {
        try {
            javax.naming.InitialContext ctx = new javax.naming.InitialContext();
            javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup("jdbc/" + dbName);
            java.sql.Connection conn = ds.getConnection();
            return conn;
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cerrar(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
