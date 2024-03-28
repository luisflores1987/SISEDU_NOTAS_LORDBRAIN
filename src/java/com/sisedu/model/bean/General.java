/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.model.bean;

/**
 *
 * @author MAQ
 */
public class General {
    private int nIdGeneral;
    private String sDescripcion;
    private String sTipo;
    private String sEstado;

    public int getnIdGeneral() {
        return nIdGeneral;
    }

    public void setnIdGeneral(int nIdGeneral) {
        this.nIdGeneral = nIdGeneral;
    }

    public String getsDescripcion() {
        return sDescripcion;
    }

    public void setsDescripcion(String sDescripcion) {
        this.sDescripcion = sDescripcion;
    }

    public String getsEstado() {
        return sEstado;
    }

    public void setsEstado(String sEstado) {
        this.sEstado = sEstado;
    }

    public String getsTipo() {
        return sTipo;
    }

    public void setsTipo(String sTipo) {
        this.sTipo = sTipo;
    }

    public String toString() {
        return "General{" + "nIdGeneral=" + nIdGeneral + ", sDescripcion=" + sDescripcion + ", sTipo=" + sTipo + ", sEstado=" + sEstado + '}';
    }
    
    
}
