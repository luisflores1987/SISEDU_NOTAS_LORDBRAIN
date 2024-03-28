/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.model.bean;

public class ProfesorHorario {
    private int nIdProfesorHorario;
    private Profesor profesor;
    private Horario horario;
    private String sHoraIngreso;
    private String sHoraSalida;

    public ProfesorHorario() {
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public int getnIdProfesorHorario() {
        return nIdProfesorHorario;
    }

    public void setnIdProfesorHorario(int nIdProfesorHorario) {
        this.nIdProfesorHorario = nIdProfesorHorario;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public String getsHoraIngreso() {
        return sHoraIngreso;
    }

    public void setsHoraIngreso(String sHoraIngreso) {
        this.sHoraIngreso = sHoraIngreso;
    }

    public String getsHoraSalida() {
        return sHoraSalida;
    }

    public void setsHoraSalida(String sHoraSalida) {
        this.sHoraSalida = sHoraSalida;
    }
        
}
