/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisedu.model.bean;

/**
 *
 * @author MAQ
 */
public class Alumno_Curso {
    
    private int nIDAlumnoCurso;
    private Alumno alumno;
    private Curso curso;
    private TipoNota tipoNota;
    private Bimestre bimestre;
    // INICIO transient
    private String  sNota1;
    private String  sNota2;
    private String sBIMESTRE1Nota1;
    private String sBIMESTRE1Nota2;
    private String sBIMESTRE2Nota1;
    private String sBIMESTRE2Nota2;
    private String sBIMESTRE3Nota1;
    private String sBIMESTRE3Nota2;
    private String sBIMESTRE4Nota1;
    private String sBIMESTRE4Nota2;
    private String sSeccionGrado;
    private Boolean bIngresadoNota1;
    private Boolean bIngresadoNota2;
    private String sTardanzas;
    private String sInasJustificadas;
    private String sInasInjustificadas;
    private int nIdParticipacionPPFF;
    private int nTipoAcceso;
    // FIN transient
    
    public Alumno_Curso() {
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Bimestre getBimestre() {
        return bimestre;
    }

    public void setBimestre(Bimestre bimestre) {
        this.bimestre = bimestre;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getnIDAlumnoCurso() {
        return nIDAlumnoCurso;
    }

    public void setnIDAlumnoCurso(int nIDAlumnoCurso) {
        this.nIDAlumnoCurso = nIDAlumnoCurso;
    }

    public String getsNota1() {
        return sNota1;
    }

    public void setsNota1(String sNota1) {
        this.sNota1 = sNota1;
    }

    public String getsNota2() {
        return sNota2;
    }

    public void setsNota2(String sNota2) {
        this.sNota2 = sNota2;
    }   

    public String getsBIMESTRE1Nota1() {
        return sBIMESTRE1Nota1;
    }

    public void setsBIMESTRE1Nota1(String sBIMESTRE1Nota1) {
        this.sBIMESTRE1Nota1 = sBIMESTRE1Nota1;
    }

    public String getsBIMESTRE1Nota2() {
        return sBIMESTRE1Nota2;
    }

    public void setsBIMESTRE1Nota2(String sBIMESTRE1Nota2) {
        this.sBIMESTRE1Nota2 = sBIMESTRE1Nota2;
    }

    public String getsBIMESTRE2Nota1() {
        return sBIMESTRE2Nota1;
    }

    public void setsBIMESTRE2Nota1(String sBIMESTRE2Nota1) {
        this.sBIMESTRE2Nota1 = sBIMESTRE2Nota1;
    }

    public String getsBIMESTRE2Nota2() {
        return sBIMESTRE2Nota2;
    }

    public void setsBIMESTRE2Nota2(String sBIMESTRE2Nota2) {
        this.sBIMESTRE2Nota2 = sBIMESTRE2Nota2;
    }

    public String getsBIMESTRE3Nota1() {
        return sBIMESTRE3Nota1;
    }

    public void setsBIMESTRE3Nota1(String sBIMESTRE3Nota1) {
        this.sBIMESTRE3Nota1 = sBIMESTRE3Nota1;
    }

    public String getsBIMESTRE3Nota2() {
        return sBIMESTRE3Nota2;
    }

    public void setsBIMESTRE3Nota2(String sBIMESTRE3Nota2) {
        this.sBIMESTRE3Nota2 = sBIMESTRE3Nota2;
    }

    public String getsBIMESTRE4Nota1() {
        return sBIMESTRE4Nota1;
    }

    public void setsBIMESTRE4Nota1(String sBIMESTRE4Nota1) {
        this.sBIMESTRE4Nota1 = sBIMESTRE4Nota1;
    }

    public String getsBIMESTRE4Nota2() {
        return sBIMESTRE4Nota2;
    }

    public void setsBIMESTRE4Nota2(String sBIMESTRE4Nota2) {
        this.sBIMESTRE4Nota2 = sBIMESTRE4Nota2;
    }    

    public TipoNota getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(TipoNota tipoNota) {
        this.tipoNota = tipoNota;
    }

    public String getsSeccionGrado() {
        return sSeccionGrado;
    }

    public void setsSeccionGrado(String sSeccionGrado) {
        this.sSeccionGrado = sSeccionGrado;
    }

    public Boolean getbIngresadoNota1() {
        return bIngresadoNota1;
    }

    public void setbIngresadoNota1(Boolean bIngresadoNota1) {
        this.bIngresadoNota1 = bIngresadoNota1;
    }

    public Boolean getbIngresadoNota2() {
        return bIngresadoNota2;
    }

    public void setbIngresadoNota2(Boolean bIngresadoNota2) {
        this.bIngresadoNota2 = bIngresadoNota2;
    }
    
    @Override
    public String toString() {
        return "Alumno_Curso{" + "nIDAlumnoCurso=" + nIDAlumnoCurso + ", alumno=" + alumno + ", curso=" + curso + ", tipoNota=" + tipoNota + ", bimestre=" + bimestre + ", sNota1=" + sNota1 + ", sNota2=" + sNota2 + '}';
    }

    public int getnIdParticipacionPPFF() {
        return nIdParticipacionPPFF;
    }

    public void setnIdParticipacionPPFF(int nIdParticipacionPPFF) {
        this.nIdParticipacionPPFF = nIdParticipacionPPFF;
    }

    public String getsInasInjustificadas() {
        return sInasInjustificadas;
    }

    public void setsInasInjustificadas(String sInasInjustificadas) {
        this.sInasInjustificadas = sInasInjustificadas;
    }

    public String getsInasJustificadas() {
        return sInasJustificadas;
    }

    public void setsInasJustificadas(String sInasJustificadas) {
        this.sInasJustificadas = sInasJustificadas;
    }

    public String getsTardanzas() {
        return sTardanzas;
    }

    public void setsTardanzas(String sTardanzas) {
        this.sTardanzas = sTardanzas;
    }

    public int getnTipoAcceso() {
        return nTipoAcceso;
    }

    public void setnTipoAcceso(int nTipoAcceso) {
        this.nTipoAcceso = nTipoAcceso;
    }    
    
}
