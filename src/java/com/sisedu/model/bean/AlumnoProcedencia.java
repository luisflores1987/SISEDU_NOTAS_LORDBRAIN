
package com.sisedu.model.bean;


public class AlumnoProcedencia {
    
    int nIdAlumnoProcedencia;
    Alumno alumno;
    Procedencia procedencia;
    String sObservacion;

    public AlumnoProcedencia() {
    }

    public AlumnoProcedencia(Alumno alumno, Procedencia procedencia, int nIdAlumnoProcedencia, String sObservacion) {
        this.alumno = alumno;
        this.nIdAlumnoProcedencia = nIdAlumnoProcedencia;
        this.sObservacion = sObservacion;
        this.procedencia=procedencia;
    }

    public int getnIdAlumnoProcedencia() {
        return nIdAlumnoProcedencia;
    }

    public String getsObservacion() {
        return sObservacion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Procedencia getProcedencia() {
        return procedencia;
    }
    
}
