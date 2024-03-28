
package com.sisedu.model.bean;


public class AlumnoApoderado {
    
    Alumno alumno;
    Apoderado apoderado;
    Parentesco parentesco;
    String sObservacion;

    public AlumnoApoderado() {
    }

    public AlumnoApoderado(Apoderado apoderado, Parentesco parentesco, String sObservacion) {
        this.alumno = alumno;
        this.apoderado = apoderado;
        this.parentesco = parentesco;
        this.sObservacion = sObservacion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Apoderado getApoderado() {
        return apoderado;
    }

    public void setApoderado(Apoderado apoderado) {
        this.apoderado = apoderado;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }

    public String getsObservacion() {
        return sObservacion;
    }

    public void setsObservacion(String sObservacion) {
        this.sObservacion = sObservacion;
    }

    public String toString() {
        return "\nAlumnoApoderado{" +  "apoderado=" + apoderado + ", parentesco=" + parentesco + ", sObservacion=" + sObservacion + '}';
    }    
}
