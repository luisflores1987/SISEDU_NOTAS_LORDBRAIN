
package com.sisedu.model.bean;


public class Deuda {
    int nidDeuda;
    String sDescripcion;

    public Deuda() {
    }    

    public Deuda(int nidDeuda, String sDescripcion) {
        this.nidDeuda = nidDeuda;
        this.sDescripcion = sDescripcion;
    }

    public int getNidDeuda() {
        return nidDeuda;
    }

    public void setNidDeuda(int nidDeuda) {
        this.nidDeuda = nidDeuda;
    }

    public String getsDescripcion() {
        return sDescripcion;
    }

    public void setsDescripcion(String sDescripcion) {
        this.sDescripcion = sDescripcion;
    }
    
    
}
