package com.gabantdev.quizzcofrade.Clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gramos on 16/02/2017.
 */

public class Paso {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("nombreHermandad")
    @Expose
    private String nombreHermandad;
    @SerializedName("nombreTitular")
    @Expose
    private String nombreTitular;
    @SerializedName("fotoPaso")
    @Expose
    private String fotoPaso;
    @SerializedName("fotoTitular")
    @Expose
    private String fotoTitular;
    @SerializedName("color_cirio")
    @Expose
    private String colorCirio;
    @SerializedName("banda")
    @Expose
    private String banda;
    @SerializedName("capataz")
    @Expose
    private String capataz;
    @SerializedName("numCostaleros")
    @Expose
    private String numCostaleros;
    @SerializedName("llamador")
    @Expose
    private String llamador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreHermandad() {
        return nombreHermandad;
    }

    public void setNombreHermandad(String idHermandad) {
        this.nombreHermandad = idHermandad;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getFotoPaso() { return fotoPaso; }

    public void setFotoPaso(String fotoPaso) { this.fotoPaso = fotoPaso; }

    public String getFotoTitular() { return fotoTitular; }

    public void setFotoTitular(String fotoTitular) { this.fotoTitular = fotoTitular; }

    public String getColorCirio() {
        return colorCirio;
    }

    public void setColorCirio(String colorCirio) {
        this.colorCirio = colorCirio;
    }

    public String getBanda() {
        return banda;
    }

    public void setBanda(String banda) {
        this.banda = banda;
    }

    public String getCapataz() {
        return capataz;
    }

    public void setCapataz(String capataz) {
        this.capataz = capataz;
    }

    public String getNumCostaleros() {
        return numCostaleros;
    }

    public void setNumCostaleros(String numCostaleros) {
        this.numCostaleros = numCostaleros;
    }

    public String getLlamador() {
        return llamador;
    }

    public void setLlamador(String llamador) {
        this.llamador = llamador;
    }

    @Override
    public String toString() {
        return "Paso{" +
                "id=" + id +
                ", nombreHermandad='" + nombreHermandad + '\'' +
                ", nombreTitular='" + nombreTitular + '\'' +
                ", fotoPaso='" + fotoPaso + '\'' +
                ", fotoTitular='" + fotoTitular + '\'' +
                ", colorCirio='" + colorCirio + '\'' +
                ", banda='" + banda + '\'' +
                ", capataz='" + capataz + '\'' +
                ", numCostaleros='" + numCostaleros + '\'' +
                ", llamador='" + llamador + '\'' +
                '}';
    }
}
