package com.sisedu.model.bean;

public class AlumnoDeuda {

    int nIdAlumnoDeuda;
    int nId_Alumno;
    int nIdDeuda;
    String monto;
    String sMontoOtros;
    Alumno alumno;
    Deuda deuda;
    String vacacional;
    String matricula;
    String marzo;
    String abril;    
    String mayo;
    String junio;    
    String julio;    
    String agosto;    
    String setiembre;
    String octubre;    
    String noviembre;    
    String diciembre;

    public AlumnoDeuda() {
    }
    
    public AlumnoDeuda(String monto) {
        this.monto = monto;
    }

    public AlumnoDeuda(int nIdAlumnoDeuda, String monto, String sMontoOtros) {
        this.nIdAlumnoDeuda = nIdAlumnoDeuda;
        this.monto = monto;
        this.sMontoOtros = sMontoOtros;
    }

    public AlumnoDeuda(
                        Alumno alumno,
                        String matricula,
                        String marzo,
                        String abril,    
                        String mayo,
                        String junio,    
                        String julio,    
                        String agosto,    
                        String setiembre,
                        String octubre,  
                        String noviembre,
                        String diciembre,
                        String vacacional
                      ) 
    {
        this.alumno = alumno;
        this.matricula = matricula;
        this.marzo = marzo;
        this.abril = abril;    
        this.mayo = mayo;
        this.junio = junio;    
        this.julio = julio;    
        this.agosto = agosto;    
        this.setiembre = setiembre;
        this.octubre = octubre;    
        this.noviembre = noviembre;    
        this.diciembre = diciembre;
        this.vacacional = vacacional;
            
    }

    public String getAbril() {
        return abril;
    }

    public String getAgosto() {
        return agosto;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public String getDiciembre() {
        return diciembre;
    }

    public String getJulio() {
        return julio;
    }

    public String getJunio() {
        return junio;
    }

    public String getMarzo() {
        return marzo;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMayo() {
        return mayo;
    }

    public String getNoviembre() {
        return noviembre;
    }

    public String getOctubre() {
        return octubre;
    }

    public String getSetiembre() {
        return setiembre;
    }    

    public void setnIdAlumnoDeuda(int nIdAlumnoDeuda) {
        this.nIdAlumnoDeuda = nIdAlumnoDeuda;
    }

    public int getnIdAlumnoDeuda() {
        return nIdAlumnoDeuda;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public int getnIdDeuda() {
        return nIdDeuda;
    }

    public void setnIdDeuda(int nIdDeuda) {
        this.nIdDeuda = nIdDeuda;
    }

    public int getnId_Alumno() {
        return nId_Alumno;
    }

    public void setnId_Alumno(int nId_Alumno) {
        this.nId_Alumno = nId_Alumno;
    }

    public String getsMontoOtros() {
        return sMontoOtros;
    }

    public void setsMontoOtros(String sMontoOtros) {
        this.sMontoOtros = sMontoOtros;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public void setDeuda(Deuda deuda) {
        this.deuda = deuda;
    }

    public Deuda getDeuda() {
        return deuda;
    }

    public String getVacacional() {
        return vacacional;
    }

    public void setVacacional(String vacacional) {
        this.vacacional = vacacional;
    }    
    
}
