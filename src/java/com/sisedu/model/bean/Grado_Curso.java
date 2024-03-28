package com.sisedu.model.bean;


public class Grado_Curso {
    private int nIdGrado_Curso;
    private Curso curso;
    private Grado grado;
    private Profesor profesor;
    private String Seccion;

    public Grado_Curso() {
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public int getnIdGrado_Curso() {
        return nIdGrado_Curso;
    }

    public void setnIdGrado_Curso(int nIdGrado_Curso) {
        this.nIdGrado_Curso = nIdGrado_Curso;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public String getSeccion() {
        return Seccion;
    }

    public void setSeccion(String Seccion) {
        this.Seccion = Seccion;
    }    
}
