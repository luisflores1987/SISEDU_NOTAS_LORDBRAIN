
package com.sisedu.model.bean;


public class Parentesco {
    
    int nIdParentesco;
    String sDescripcion;

    public Parentesco() {
    }

    public Parentesco(int nIdParentesco, String sDescripcion) {
        this.nIdParentesco = nIdParentesco;
        this.sDescripcion = sDescripcion;
    }

    public int getnIdParentesco() {
        return nIdParentesco;
    }

    public void setnIdParentesco(int nIdParentesco) {
        this.nIdParentesco = nIdParentesco;
    }

    public String getsDescripcion() {
        return sDescripcion;
    }

    public void setsDescripcion(String sDescripcion) {
        this.sDescripcion = sDescripcion;
    }

    public String toString() {
        return "Parentesco{" + "nIdParentesco=" + nIdParentesco + ", sDescripcion=" + sDescripcion + '}';
    }
    
    
    
}
