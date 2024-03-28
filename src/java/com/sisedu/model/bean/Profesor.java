
package com.sisedu.model.bean;

import java.util.List;


public class Profesor {
    
    private int nId_Profesor;
    private Persona persona;
    private String sFechaIngreso;
    private int nCodigo;
    private AsistenciaProfesor asistenciaProfesor;
    //transient
    private String sMensaje;
    private String sColorMensaje;
    private Double fCantidad;
   

    public Profesor() {
    }

    public Profesor(int nId_Profesor, Persona persona, String sFechaIngreso, int nCodigo) {
        this.nId_Profesor = nId_Profesor;
        this.persona = persona;
        this.sFechaIngreso = sFechaIngreso;
        this.nCodigo = nCodigo;
    }         
    
    public Profesor(int nId_Profesor, Persona persona, String sFechaIngreso, int nCodigo, AsistenciaProfesor asistenciaProfesor) {
        this.nId_Profesor = nId_Profesor;
        this.persona = persona;
        this.sFechaIngreso = sFechaIngreso;
        this.nCodigo = nCodigo;
        this.asistenciaProfesor = asistenciaProfesor;
    }    

    public int getnCodigo() {
        return nCodigo;
    }

    public int getnId_Profesor() {
        return nId_Profesor;
    }

    public Persona getPersona() {
        return persona;
    }

    public String getsFechaIngreso() {
        return sFechaIngreso;
    }

    public AsistenciaProfesor getAsistenciaProfesor() {
        return asistenciaProfesor;
    }

    public void setAsistenciaProfesor(AsistenciaProfesor asistenciaProfesor) {
        this.asistenciaProfesor = asistenciaProfesor;
    }

    public void setnCodigo(int nCodigo) {
        this.nCodigo = nCodigo;
    }

    public void setnId_Profesor(int nId_Profesor) {
        this.nId_Profesor = nId_Profesor;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void setsFechaIngreso(String sFechaIngreso) {
        this.sFechaIngreso = sFechaIngreso;
    }

    public String getsMensaje() {
        return sMensaje;
    }

    public void setsMensaje(String sMensaje) {
        this.sMensaje = sMensaje;
    }

    public String getsColorMensaje() {
        return sColorMensaje;
    }

    public void setsColorMensaje(String sColorMensaje) {
        this.sColorMensaje = sColorMensaje;
    }

    public Double getfCantidad() {
        return fCantidad;
    }

    public void setfCantidad(Double fCantidad) {
        this.fCantidad = fCantidad;
    }

}
