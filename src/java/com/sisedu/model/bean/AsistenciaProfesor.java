/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.model.bean;

/**
 *
 * @author MAQ
 */
public class AsistenciaProfesor {
    int nIdAsistencia;
    String sHoraRegistro;
    int nIdProfesor;

    public AsistenciaProfesor() {
    }

    public AsistenciaProfesor(int nIdAsistencia, String sHoraRegistro, int nIdProfesor) {
        this.nIdAsistencia = nIdAsistencia;
        this.sHoraRegistro = sHoraRegistro;
        this.nIdProfesor = nIdProfesor;
    }

    public int getnIdAsistencia() {
        return nIdAsistencia;
    }

    public int getnIdProfesor() {
        return nIdProfesor;
    }

    public String getsHoraRegistro() {
        return sHoraRegistro;
    }

    public void setsHoraRegistro(String sHoraRegistro) {
        this.sHoraRegistro = sHoraRegistro;
    }    
    
    
}
