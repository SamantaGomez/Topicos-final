package com.example.efinal.model;

public class Alimento {
    private String uid;

    public Alimento() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTiempo() {
        return Tiempo;
    }

    public void setTiempo(String tiempo) {
        Tiempo = tiempo;
    }

    public String getProteina() {
        return proteina;
    }

    public void setProteina(String proteina) {
        this.proteina = proteina;
    }

    public String getFruta() {
        return fruta;
    }

    public void setFruta(String fruta) {
        this.fruta = fruta;
    }

    public String getLiquido() {
        return liquido;
    }

    public void setLiquido(String liquido) {
        this.liquido = liquido;
    }


    public String getOtro() {
        return otro;
    }

    public void setOtro(String otro) {
        this.otro = otro;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    private String fecha;
    private String Tiempo;
    private String proteina;
    private String fruta;
    private String liquido;

    public String getGuarnicion() {
        return guarnicion;
    }

    public void setGuarnicion(String guarnicion) {
        this.guarnicion = guarnicion;
    }

    private String guarnicion;
    private String otro;
}
