package com.sisedu.model.DAO;

import com.sisedu.model.bean.ListaMenu;
import com.sisedu.model.bean.ListaMenu_TipoAcceso;
import com.sisedu.model.bean.TipoAcceso;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObtenerMenusDAO {

    List<ListaMenu_TipoAcceso> listaAccesoMenu = new ArrayList<ListaMenu_TipoAcceso>();

    public ObtenerMenusDAO() {
    }

    public List<ListaMenu_TipoAcceso> obtenerMenus(int nTipoAcceso) {
        Connection con = Conexion.conectar();
        listaAccesoMenu = new ArrayList<ListaMenu_TipoAcceso>();
        try {
            CallableStatement cs = con.prepareCall("{call [dbo].[usp_filtroVerificarMenus](?)}");
            cs.setInt(1 ,nTipoAcceso);            
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                listaAccesoMenu.add(new ListaMenu_TipoAcceso
                                                (
                                                    rs.getInt(1),
                                                    new ListaMenu(rs.getInt(2), rs.getString(3), new ListaMenu(rs.getInt(4),"", null,""), rs.getString(8)),
                                                    new TipoAcceso(rs.getInt(5), rs.getString(6)),
                                                    rs.getString(7)        
                                                 )
                                          );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrar(con);
        }
        return listaAccesoMenu;
    }
}
