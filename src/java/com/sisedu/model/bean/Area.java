/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.model.bean;

public class Area {
    
    private int nIdArea;
    private String sDescripcion;

    public Area() {
    }

    public int getnIdArea() {
        return nIdArea;
    }

    public void setnIdArea(int nIdArea) {
        this.nIdArea = nIdArea;
    }

    public String getsDescripcion() {
        return sDescripcion;
    }

    public void setsDescripcion(String sDescripcion) {
        this.sDescripcion = sDescripcion;
    }

    @Override
    public String toString() {
        return "Area{" + "nIdArea=" + nIdArea + ", sDescripcion=" + sDescripcion + '}';
    }
    
    
}
