package com.example.ammacias.quizzcofrade.Clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gramos on 16/02/2017.
 */

public class Paso {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("idHermandad")
    @Expose
    private Long idHermandad;
    @SerializedName("nombreTitular")
    @Expose
    private String nombreTitular;
    @SerializedName("foto")
    @Expose
    private String foto;
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
    private Integer numCostaleros;
    @SerializedName("llamador")
    @Expose
    private String llamador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdHermandad() {
        return idHermandad;
    }

    public void setIdHermandad(Long idHermandad) {
        this.idHermandad = idHermandad;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

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

    public Integer getNumCostaleros() {
        return numCostaleros;
    }

    public void setNumCostaleros(Integer numCostaleros) {
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
                ", idHermandad=" + idHermandad +
                ", nombreTitular='" + nombreTitular + '\'' +
                ", foto=" + foto +
                ", colorCirio='" + colorCirio + '\'' +
                ", banda='" + banda + '\'' +
                ", capataz='" + capataz + '\'' +
                ", numCostaleros=" + numCostaleros +
                ", llamador='" + llamador + '\'' +
                '}';
    }
}
