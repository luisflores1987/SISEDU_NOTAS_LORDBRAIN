
package com.sisedu.model.bean;


public class Documento {
    
    int nIdDocumento ;
    String sDescripcion;

    public Documento() {
    }
    
    public Documento(int nIdDocumento, String sDescripcion) {
        this.nIdDocumento = nIdDocumento;
        this.sDescripcion = sDescripcion;
    }
    
    public int getnIdDocumento() {
        return nIdDocumento;
    }

    public void setnIdDocumento(int nIdDocumento) {
        this.nIdDocumento = nIdDocumento;
    }

    public String getsDescripcion() {
        return sDescripcion;
    }

    public void setsDescripcion(String sDescripcion) {
        this.sDescripcion = sDescripcion;
    }
    
    
    
}
