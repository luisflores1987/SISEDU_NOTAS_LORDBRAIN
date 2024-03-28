package com.sisedu.model.bean;


public class Boleta {
    int nIdBoleta;
    String sNumeroRecibo;
    String sMonto;
    String sMora;
    String sFechaCreacion;
    String sUserName;
    Alumno alumno;
    Deuda deuda;
    AlumnoDeuda alumnoDeuda;
    String sObservaciones;
    Boolean bCancelado;
    String sBoletaReimpresion;

    public Boleta() {
    }

    public String getsBoletaReimpresion() {
        return sBoletaReimpresion;
    }

    public void setsBoletaReimpresion(String sBoletaReimpresion) {
        this.sBoletaReimpresion = sBoletaReimpresion;
    }    
    
    public Boleta(int nIdBoleta, String sNumeroRecibo, Deuda deuda, AlumnoDeuda alumnoDeuda, String sMonto, String sMora, Alumno alumno, String sObservaciones, Boolean bCancelado) {
        this.nIdBoleta = nIdBoleta;
        this.sNumeroRecibo = sNumeroRecibo;
        this.sMonto = sMonto;
        this.sMora = sMora;
        this.alumno = alumno;
        this.deuda = deuda;
        this.alumnoDeuda = alumnoDeuda;
        this.sObservaciones = sObservaciones;
        this.bCancelado = bCancelado;           
    }
    
    public Boleta(int nIdBoleta, Alumno alumno, Deuda deuda, AlumnoDeuda alumnoDeuda, String sNumeroRecibo, String sMora, String sMonto, String sFechaCreacion, String sUserName, String sBoleta) {
        this.nIdBoleta = nIdBoleta;
        this.sMonto = sMonto;
        this.sMora = sMora;
        this.alumno = alumno;
        this.deuda = deuda;
        this.alumnoDeuda = alumnoDeuda;
        this.sNumeroRecibo = sNumeroRecibo;
        this.sFechaCreacion = sFechaCreacion;
        this.sUserName = sUserName;
        this.sBoletaReimpresion = sBoleta;
     }    

    public Alumno getAlumno() {
        return alumno;
    }

    public Deuda getDeuda() {
        return deuda;
    }

    public int getnIdBoleta() {
        return nIdBoleta;
    }

    public String getsNumeroRecibo() {
        return sNumeroRecibo;
    }

    public String getsMonto() {
        return sMonto;
    }

    public String getsMora() {
        return sMora;
    }

    public String getsObservaciones() {
        return sObservaciones;
    }

    public AlumnoDeuda getAlumnoDeuda() {
        return alumnoDeuda;
    }

    public Boolean getbCancelado() {
        return bCancelado;
    }

    public void setbCancelado(Boolean bCancelado) {
        this.bCancelado = bCancelado;
    }

    public String getsFechaCreacion() {
        return sFechaCreacion;
    }

    public String getsUserName() {
        return sUserName;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public void setAlumnoDeuda(AlumnoDeuda alumnoDeuda) {
        this.alumnoDeuda = alumnoDeuda;
    }

    public void setDeuda(Deuda deuda) {
        this.deuda = deuda;
    }    
    
}
