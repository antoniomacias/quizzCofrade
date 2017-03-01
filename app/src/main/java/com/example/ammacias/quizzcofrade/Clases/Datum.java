package com.example.ammacias.quizzcofrade.Clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ammacias on 01/03/2017.
 */

public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("idHermandad")
    @Expose
    private String idHermandad;
    @SerializedName("nombreTitular")
    @Expose
    private String nombreTitular;
    @SerializedName("foto")
    @Expose
    private Object foto;
    @SerializedName("color_cirio")
    @Expose
    private Object colorCirio;
    @SerializedName("banda")
    @Expose
    private String banda;
    @SerializedName("capataz")
    @Expose
    private Object capataz;
    @SerializedName("numCostaleros")
    @Expose
    private String numCostaleros;
    @SerializedName("llamador")
    @Expose
    private Object llamador;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdHermandad() {
        return idHermandad;
    }

    public void setIdHermandad(String idHermandad) {
        this.idHermandad = idHermandad;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public Object getFoto() {
        return foto;
    }

    public void setFoto(Object foto) {
        this.foto = foto;
    }

    public Object getColorCirio() {
        return colorCirio;
    }

    public void setColorCirio(Object colorCirio) {
        this.colorCirio = colorCirio;
    }

    public String getBanda() {
        return banda;
    }

    public void setBanda(String banda) {
        this.banda = banda;
    }

    public Object getCapataz() {
        return capataz;
    }

    public void setCapataz(Object capataz) {
        this.capataz = capataz;
    }

    public String getNumCostaleros() {
        return numCostaleros;
    }

    public void setNumCostaleros(String numCostaleros) {
        this.numCostaleros = numCostaleros;
    }

    public Object getLlamador() {
        return llamador;
    }

    public void setLlamador(Object llamador) {
        this.llamador = llamador;
    }

}