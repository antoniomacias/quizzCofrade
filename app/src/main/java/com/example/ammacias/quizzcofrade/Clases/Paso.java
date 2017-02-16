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
    @SerializedName("nombreTitular")
    @Expose
    private String nombreTitular;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("banda")
    @Expose
    private String banda;
    @SerializedName("idHermandad")
    @Expose
    private Integer idHermandad;

    public Paso() {
    }

    public Paso(Long id, String nombreTitular, String foto, String banda, Integer idHermandad) {
        this.id = id;
        this.nombreTitular = nombreTitular;
        this.foto = foto;
        this.banda = banda;
        this.idHermandad = idHermandad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBanda() {
        return banda;
    }

    public void setBanda(String banda) {
        this.banda = banda;
    }

    public Integer getIdHermandad() {
        return idHermandad;
    }

    public void setIdHermandad(Integer idHermandad) {
        this.idHermandad = idHermandad;
    }

    @Override
    public String toString() {
        return "Paso{" +
                "id=" + id +
                ", nombreTitular='" + nombreTitular + '\'' +
                ", foto='" + foto + '\'' +
                ", banda='" + banda + '\'' +
                ", idHermandad=" + idHermandad +
                '}';
    }
}
