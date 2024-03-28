package com.sisedu.model.bean;

public class Alumno {

    int nId_Alumno;
    private Persona persona;
    private Nivel nivel;
    private Grado grado;
    private String documentos;
    private String seccion;
    private String sEstado;
    private String sAnioAcademico;
    private TipoMatricula tipoMatricula;
    private String sCodigoColor;
    private String sValidarAnioAcademico; // indica que si el alumno seleccionado para habilitar es el ultimo año academico o no ... 

    public Alumno() {
    }

    public String getsEstado() {
        return sEstado;
    }

    public void setsEstado(String sEstado) {
        this.sEstado = sEstado;
    }

    public Alumno(int nId_Alumno, Persona persona, Nivel nivel, Grado grado, String documentos) {
        this.nId_Alumno = nId_Alumno;
        this.persona = persona;
        this.nivel = nivel;
        this.grado = grado;
        this.documentos = documentos;
    }    
    
    public Alumno(int nId_Alumno, Persona persona, Nivel nivel, Grado grado, String documentos, String sEstado) {
        this.nId_Alumno = nId_Alumno;
        this.persona = persona;
        this.nivel = nivel;
        this.grado = grado;
        this.documentos = documentos;
        this.sEstado = sEstado;
    }

    public Alumno(int nId_Alumno, Persona persona, Nivel nivel, Grado grado, String documentos, String sEstado, TipoMatricula tipoMatricula) {   /// constructor para instanciar al metodo de detalle de alumno
        this.nId_Alumno = nId_Alumno;
        this.persona = persona;
        this.nivel = nivel;
        this.grado = grado;
        this.documentos = documentos;
        this.sEstado = sEstado;
        this.tipoMatricula = tipoMatricula;
    }
    
        public Alumno(int nId_Alumno, Persona persona, Nivel nivel, Grado grado, String documentos, String sEstado, TipoMatricula tipoMatricula, String sValidarAnioAcademico) {   /// constructor para instanciar al metodo de detalle de alumno
        this.nId_Alumno = nId_Alumno;
        this.persona = persona;
        this.nivel = nivel;
        this.grado = grado;
        this.documentos = documentos;
        this.sEstado = sEstado;
        this.tipoMatricula = tipoMatricula;
        this.sValidarAnioAcademico = sValidarAnioAcademico;
    }

    public Alumno(int nId_Alumno, Persona persona) {
        this.nId_Alumno = nId_Alumno;
        this.persona = persona;
    }
    
    public Alumno(int nId_Alumno, Grado grado) {
        this.nId_Alumno = nId_Alumno;
        this.grado = grado;
    }

    public int getnId_Alumno() {
        return nId_Alumno;
    }

    public void setnId_Alumno(int nId_Alumno) {
        this.nId_Alumno = nId_Alumno;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public String getDocumentos() {
        return documentos;
    }

    public void setDocumentos(String documentos) {
        this.documentos = documentos;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getsAnioAcademico() {
        return sAnioAcademico;
    }

    public void setsAnioAcademico(String sAnioAcademico) {
        this.sAnioAcademico = sAnioAcademico;
    }

    public TipoMatricula getTipoMatricula() {
        return tipoMatricula;
    }

    public void setTipoMatricula(TipoMatricula tipoMatricula) {
        this.tipoMatricula = tipoMatricula;
    }

    public String getsCodigoColor() {
        return sCodigoColor;
    }

    public void setsCodigoColor(String sCodigoColor) {
        this.sCodigoColor = sCodigoColor;
    }

    public String getsValidarAnioAcademico() {
        return sValidarAnioAcademico;
    }

    public void setsValidarAnioAcademico(String sValidarAnioAcademico) {
        this.sValidarAnioAcademico = sValidarAnioAcademico;
    }    
    
}
