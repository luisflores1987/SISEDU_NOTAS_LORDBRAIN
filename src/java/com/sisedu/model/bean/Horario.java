package com.sisedu.model.bean;

import java.util.List;


public class Horario {
    
    private int nIdHorario;
    private String sDescripcion;

    public Horario() {
    }

    public Horario(int nIdHorario, String sDescripcion) {
        this.nIdHorario = nIdHorario;
        this.sDescripcion = sDescripcion;
    }    
    public int getnIdHorario() {
        return nIdHorario;
    }

    public void setnIdHorario(int nIdHorario) {
        this.nIdHorario = nIdHorario;
    }

    public String getsDescripcion() {
        return sDescripcion;
    }

    public void setsDescripcion(String sDescripcion) {
        this.sDescripcion = sDescripcion;
    }

}
