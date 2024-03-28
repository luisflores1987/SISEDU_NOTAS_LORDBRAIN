/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.model.bean;

/**
 *
 * @author lflores
 */
public class Grado {

    int nIdGrado;
    String sDescripcion;
    String sPension;
    //Trasient
    String sSeccion;

    public Grado() {
    }

    public Grado(int nIdGrado, String sDescripcion) {
        this.nIdGrado = nIdGrado;
        this.sDescripcion = sDescripcion;
    }
    
    public Grado(int nIdGrado, String sDescripcion, String sPension) {
        this.nIdGrado = nIdGrado;
        this.sDescripcion = sDescripcion;
        this.sPension = sPension;        
    }    

    public Grado(int nIdGrado) {
        this.nIdGrado = nIdGrado;
    }

    public Grado(String sDescripcion) {
        this.sDescripcion = sDescripcion;
    }

    public int getnIdGrado() {
        return nIdGrado;
    }

    public void setnIdGrado(int nIdGrado) {
        this.nIdGrado = nIdGrado;
    }

    public String getsDescripcion() {
        return sDescripcion;
    }

    public void setsDescripcion(String sDescripcion) {
        this.sDescripcion = sDescripcion;
    }

    public String getsPension() {
        return sPension;
    }

    public void setsPension(String sPension) {
        this.sPension = sPension;
    }

    public String getsSeccion() {
        return sSeccion;
    }

    public void setsSeccion(String sSeccion) {
        this.sSeccion = sSeccion;
    }   
    
}
