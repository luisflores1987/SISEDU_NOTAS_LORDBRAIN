package com.sisedu.model.bean;

public class GradoInstruccion {

    int nIdGradoInstruccion;
    String sDescripcion;

    public GradoInstruccion() {
    }

    public GradoInstruccion(int nIdGradoInstruccion, String sDescripcion) {
        this.nIdGradoInstruccion = nIdGradoInstruccion;
        this.sDescripcion = sDescripcion;
    }

    public int getnIdGradoInstruccion() {
        return nIdGradoInstruccion;
    }

    public void setnIdGradoInstruccion(int nIdGradoInstruccion) {
        this.nIdGradoInstruccion = nIdGradoInstruccion;
    }

    public String getsDescripcion() {
        return sDescripcion;
    }

    public void setsDescripcion(String sDescripcion) {
        this.sDescripcion = sDescripcion;
    }
    
    
}
