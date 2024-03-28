
package com.sisedu.model.bean;


public class AreaEspecialidad {
   private int nIdArea;
   private String sDescripcion;
   private String sEstado;

    public AreaEspecialidad() {
    }

    public AreaEspecialidad(int nIdArea, String sDescripcion, String sEstado) {
        this.nIdArea = nIdArea;
        this.sDescripcion = sDescripcion;
        this.sEstado = sEstado;
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

    public String getsEstado() {
        return sEstado;
    }

    public void setsEstado(String sEstado) {
        this.sEstado = sEstado;
    }
   
   
    
}
