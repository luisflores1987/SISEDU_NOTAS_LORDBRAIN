/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.model.bean;

public class Bimestre {
    private int nIdBimestre;
    private String sDescripcion;
    //trasient
    private String sHabilitar;

    public Bimestre() {
    }

    public String getsHabilitar() {
        return sHabilitar;
    }

    public void setsHabilitar(String sHabilitar) {
        this.sHabilitar = sHabilitar;
    }    
    
    public int getnIdBimestre() {
        return nIdBimestre;
    }

    public void setnIdBimestre(int nIdBimestre) {
        this.nIdBimestre = nIdBimestre;
    }

    public String getsDescripcion() {
        return sDescripcion;
    }

    public void setsDescripcion(String sDescripcion) {
        this.sDescripcion = sDescripcion;
    }

    @Override
    public String toString() {
        return "Bimestre{" + "nIdBimestre=" + nIdBimestre + ", sDescripcion=" + sDescripcion + ", sHabilitar=" + sHabilitar + '}';
    }
    
}
