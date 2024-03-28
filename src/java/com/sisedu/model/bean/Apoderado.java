package com.sisedu.model.bean;

public class Apoderado {

    int nIdApoderado;
    String sNombreCompleto;
    String sTelefono;
    GradoInstruccion gradoInstruccion;

    public Apoderado() {
    }

    public Apoderado(String sNombreCompleto, String sTelefono, GradoInstruccion gradoInstruccion) {
        this.sNombreCompleto = sNombreCompleto;
        this.sTelefono = sTelefono;
        this.gradoInstruccion = gradoInstruccion;
    }

    public int getnIdApoderado() {
        return nIdApoderado;
    }

    public void setnIdApoderado(int nIdApoderado) {
        this.nIdApoderado = nIdApoderado;
    }

    public GradoInstruccion getGradoInstruccion() {
        return gradoInstruccion;
    }

    public void setGradoInstruccion(GradoInstruccion gradoInstruccion) {
        this.gradoInstruccion = gradoInstruccion;
    }

    public String getsTelefono() {
        return sTelefono;
    }

    public void setsTelefono(String sTelefono) {
        this.sTelefono = sTelefono;
    }

    public String getsNombreCompleto() {
        return sNombreCompleto;
    }

    public void setsNombreCompleto(String sNombreCompleto) {
        this.sNombreCompleto = sNombreCompleto;
    }

    public String toString() {
        return "Apoderado{" + "nIdApoderado=" + nIdApoderado + ", sNombreCompleto=" + sNombreCompleto + ", sTelefono=" + sTelefono + ", gradoInstruccion=" + gradoInstruccion + '}';
    }    
    
}
