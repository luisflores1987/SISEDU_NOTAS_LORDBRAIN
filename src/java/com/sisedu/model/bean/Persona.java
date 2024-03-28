package com.sisedu.model.bean;

public class Persona {

    int nIdPersona;
    String sApPaterno;
    String sApMaterno;
    String sNombreCompleto;
    String sDatosPersonales;
    String sSexo;
    int nNumeroDocumento;
    String sFechaNacimiento;
    String sDireccionActual;
    String sPension;
    int nIdTipoAcceso;

    public Persona() {
    }

    public Persona(int nIdPersona, String sDatosPersonales) {
        this.nIdPersona = nIdPersona;
        this.sDatosPersonales = sDatosPersonales;
    }

    public Persona(int nIdPersona, String sDatosPersonales, int nIdTipoAcceso) {
        this.nIdPersona = nIdPersona;
        this.sDatosPersonales = sDatosPersonales;
        this.nIdTipoAcceso = nIdTipoAcceso;
    }

    public Persona(int nIdPersona, int nNumeroDocumento, String sDatosPersonales) {
        this.nIdPersona = nIdPersona;
        this.nNumeroDocumento = nNumeroDocumento;
        this.sDatosPersonales = sDatosPersonales;
    }

    public Persona(int nIdPersona, int nNumeroDocumento, String sApPaterno, String sApMaterno, String sNombreCompleto) {
        this.nIdPersona = nIdPersona;
        this.sApPaterno = sApPaterno;
        this.sApMaterno = sApMaterno;
        this.sNombreCompleto = sNombreCompleto;
        this.nNumeroDocumento = nNumeroDocumento;
    }

    public Persona(int nIdPersona, int nNumeroDocumento, String sApPaterno, String sApMaterno, String sNombreCompleto, String sFechaNacimiento,String sSexo) {
        this.nIdPersona = nIdPersona;
        this.sApPaterno = sApPaterno;
        this.sApMaterno = sApMaterno;
        this.sNombreCompleto = sNombreCompleto;
        this.nNumeroDocumento = nNumeroDocumento;
        this.sFechaNacimiento = sFechaNacimiento;
        this.sSexo = sSexo;
    }

    public Persona(int nIdPersona, int nNumeroDocumento, String sApPaterno, String sApMaterno, String sNombreCompleto, String sPension) {
        this.nIdPersona = nIdPersona;
        this.sApPaterno = sApPaterno;
        this.sApMaterno = sApMaterno;
        this.sNombreCompleto = sNombreCompleto;
        this.nNumeroDocumento = nNumeroDocumento;
        this.sPension = sPension;
    }

    public Persona(int nIdPersona, int nNumeroDocumento, String sApPaterno, String sApMaterno, String sNombreCompleto, String sFechaNacimiento, String sSexo, String sDireccionActual) {
        this.nIdPersona = nIdPersona;
        this.nNumeroDocumento = nNumeroDocumento;
        this.sApPaterno = sApPaterno;
        this.sApMaterno = sApMaterno;
        this.sNombreCompleto = sNombreCompleto;
        this.sFechaNacimiento = sFechaNacimiento;
        this.sSexo = sSexo;
        this.sDireccionActual = sDireccionActual;
    }

    public String getsNombreCompleto() {
        return sNombreCompleto;
    }

    public void setsNombreCompleto(String sNombreCompleto) {
        this.sNombreCompleto = sNombreCompleto;
    }

    public int getnIdPersona() {
        return nIdPersona;
    }

    public void setnIdPersona(int nIdPersona) {
        this.nIdPersona = nIdPersona;
    }

    public int getnNumeroDocumento() {
        return nNumeroDocumento;
    }

    public void setnNumeroDocumento(int nNumeroDocumento) {
        this.nNumeroDocumento = nNumeroDocumento;
    }

    public String getsSexo() {
        return sSexo;
    }

    public void setnSexo(String sSexo) {
        this.sSexo = sSexo;
    }

    public String getsApMaterno() {
        return sApMaterno;
    }

    public void setsApMaterno(String sApMaterno) {
        this.sApMaterno = sApMaterno;
    }

    public String getsApPaterno() {
        return sApPaterno;
    }

    public void setsApPaterno(String sApPaterno) {
        this.sApPaterno = sApPaterno;
    }

    public String getsFechaNacimiento() {
        return sFechaNacimiento;
    }

    public void setsFechaNacimiento(String sFechaNacimiento) {
        this.sFechaNacimiento = sFechaNacimiento;
    }

    public String getsDatosPersonales() {
        return sDatosPersonales;
    }

    public void setsDatosPersonales(String sDatosPersonales) {
        this.sDatosPersonales = sDatosPersonales;
    }

    public String getsDireccionActual() {
        return sDireccionActual;
    }

    public void setsDireccionActual(String sDireccionActual) {
        this.sDireccionActual = sDireccionActual;
    }

    public String getsPension() {
        return sPension;
    }

    public void setsPension(String sPension) {
        this.sPension = sPension;
    }

    public int getnIdTipoAcceso() {
        return nIdTipoAcceso;
    }
}
