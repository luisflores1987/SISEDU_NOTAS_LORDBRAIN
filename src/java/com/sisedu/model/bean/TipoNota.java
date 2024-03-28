/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.model.bean;

/**
 *
 * @author MAQ
 */
public class TipoNota {
    private int nIdTipoNota;
    private String sDescripcion;

    public TipoNota() {
    }

    public int getnIdTipoNota() {
        return nIdTipoNota;
    }

    public void setnIdTipoNota(int nIdTipoNota) {
        this.nIdTipoNota = nIdTipoNota;
    }

    public String getsDescripcion() {
        return sDescripcion;
    }

    public void setsDescripcion(String sDescripcion) {
        this.sDescripcion = sDescripcion;
    }
    
    
}
