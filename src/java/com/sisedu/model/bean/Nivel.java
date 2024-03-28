/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.model.bean;

/**
 *
 * @author lflores
 */
public class Nivel {

    int nIdNivel;
    String sDescripcion;
    String sPension;
    String sAnioAcademico;

    public Nivel() {
    }

    public Nivel(int nIdNivel, String sDescripcion) {
        this.nIdNivel = nIdNivel;
        this.sDescripcion = sDescripcion;
    }

    public Nivel(int nIdNivel, String sDescripcion, String sPension) {
        this.nIdNivel = nIdNivel;
        this.sDescripcion = sDescripcion;
        this.sPension = sPension;
    }

    public Nivel(int nIdNivel) {
        this.nIdNivel = nIdNivel;
    }

    public Nivel(String sDescripcion) {
        this.sDescripcion = sDescripcion;
    }

    public int getnIdNivel() {
        return nIdNivel;
    }

    public void setnIdNivel(int nIdNivel) {
        this.nIdNivel = nIdNivel;
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

    public String getsAnioAcademico() {
        return sAnioAcademico;
    }

    public void setsAnioAcademico(String sAnioAcademico) {
        this.sAnioAcademico = sAnioAcademico;
    }
        
}
