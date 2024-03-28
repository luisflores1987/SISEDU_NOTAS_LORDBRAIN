package com.sisedu.model.bean;

public class TipoAcceso {

    int nIdTipoAcceso;
    String sDescripcion;

    public TipoAcceso(int nIdTipoAcceso, String sDescripcion) {
        this.nIdTipoAcceso = nIdTipoAcceso;
        this.sDescripcion = sDescripcion;
    }

    public int getnIdTipoAcceso() {
        return nIdTipoAcceso;
    }

    public String getsDescripcion() {
        return sDescripcion;
    }
}
