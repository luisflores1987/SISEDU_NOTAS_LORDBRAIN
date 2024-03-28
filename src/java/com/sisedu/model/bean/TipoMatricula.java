/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.model.bean;

/**
 *
 * @author MAQ
 */
public class TipoMatricula {
    
    private int nIdMatricula;
    private String sDescripcion;

    public TipoMatricula() {
    }

    public TipoMatricula(int nIdMatricula) {
        this.nIdMatricula = nIdMatricula;
    }

    public TipoMatricula(String sDescripcion) {
        this.sDescripcion = sDescripcion;
    }    

    public int getnIdMatricula() {
        return nIdMatricula;
    }

    public void setnIdMatricula(int nIdMatricula) {
        this.nIdMatricula = nIdMatricula;
    }

    public String getsDescripcion() {
        return sDescripcion;
    }

    public void setsDescripcion(String sDescripcion) {
        this.sDescripcion = sDescripcion;
    }

    @Override
    public String toString() {
        return "TipoMatricula{" + "nIdMatricula=" + nIdMatricula + ", sDescripcion=" + sDescripcion + '}';
    }    
    
}
