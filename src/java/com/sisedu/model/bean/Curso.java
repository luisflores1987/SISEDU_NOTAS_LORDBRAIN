package com.sisedu.model.bean;



public class Curso {

    private int nIdCurso;
    private String sDescripcion;
    private String sEstado;
    private AreaEspecialidad area;

    public Curso() {
    }

    public AreaEspecialidad getArea() {
        return area;
    }

    public void setArea(AreaEspecialidad area) {
        this.area = area;
    }

    
    public int getnIdCurso() {
        return nIdCurso;
    }

    public void setnIdCurso(int nIdCurso) {
        this.nIdCurso = nIdCurso;
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
    
}
