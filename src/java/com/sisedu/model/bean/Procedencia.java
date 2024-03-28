
package com.sisedu.model.bean;

public class Procedencia {
    
    int nIdProcedencia;
    String sDescripcion;

    public Procedencia() {
    }

    public Procedencia(int nIdProcedencia, String sDescripcion) {
        this.nIdProcedencia = nIdProcedencia;
        this.sDescripcion = sDescripcion;
    }

    public int getnIdProcedencia() {
        return nIdProcedencia;
    }

    public void setnIdProcedencia(int nIdProcedencia) {
        this.nIdProcedencia = nIdProcedencia;
    }

    public String getsDescripcion() {
        return sDescripcion;
    }

    public void setsDescripcion(String sDescripcion) {
        this.sDescripcion = sDescripcion;
    }
    
        
}
