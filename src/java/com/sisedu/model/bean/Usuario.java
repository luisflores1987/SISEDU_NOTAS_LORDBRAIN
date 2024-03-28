package com.sisedu.model.bean;

public class Usuario {
    int nidUsuario;
    String sNombreUsuario;
    String sPassword;
    Persona persona;

    public Usuario() {
    }

    public Usuario(int nidUsuario, String sNombreUsuario, String sPassword) {
        this.nidUsuario = nidUsuario;
        this.sNombreUsuario = sNombreUsuario;
        this.sPassword = sPassword;
        this.persona = persona;
    }
    
    public Usuario(String sNombreUsuario, String sPassword) {
        this.sNombreUsuario = sNombreUsuario;
        this.sPassword = sPassword;
    }    

    public int getNidUsuario() {
        return nidUsuario;
    }

    public void setNidUsuario(int nidUsuario) {
        this.nidUsuario = nidUsuario;
    }

    public String getsNombreUsuario() {
        return sNombreUsuario;
    }

    public void setsNombreUsuario(String sNombreUsuario) {
        this.sNombreUsuario = sNombreUsuario;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
}
