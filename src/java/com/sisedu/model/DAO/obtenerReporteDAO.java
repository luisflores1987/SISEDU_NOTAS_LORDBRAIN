/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.model.DAO;

import com.sisedu.model.bean.Alumno;
import com.sisedu.model.bean.AlumnoDeuda;
import com.sisedu.model.bean.Grado;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MAQ
 */
public class obtenerReporteDAO {

    public obtenerReporteDAO() {
    }
    List<AlumnoDeuda> listaAlumnoDeuda = new ArrayList<AlumnoDeuda>();
        public List<AlumnoDeuda> obtenerReporteGrado(int nTipoTransaccion, String sAnioAcademico) {
        Connection con = Conexion.conectar();
        listaAlumnoDeuda = new ArrayList<AlumnoDeuda>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroConsultaReporteGerente](?, ?)}");
            cs.setInt(1, nTipoTransaccion);
            cs.setString(2, sAnioAcademico);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                listaAlumnoDeuda.add(new AlumnoDeuda(
                                                        new Alumno(0, new Grado(rs.getInt(1), rs.getString(2))),
                                                        rs.getString(3),
                                                        rs.getString(4),
                                                        rs.getString(5),
                                                        rs.getString(6),
                                                        rs.getString(7),
                                                        rs.getString(8),
                                                        rs.getString(9),
                                                        rs.getString(10),
                                                        rs.getString(11),
                                                        rs.getString(12),
                                                        rs.getString(13),
                                                        rs.getString(14)                        
                                                     )
                                    );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaAlumnoDeuda;
    }
    
}
