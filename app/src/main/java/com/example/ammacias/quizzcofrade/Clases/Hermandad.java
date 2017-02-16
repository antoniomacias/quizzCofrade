package com.example.ammacias.quizzcofrade.Clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gramos on 16/02/2017.
 */

public class Hermandad {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("escudo")
    @Expose
    private String escudo;
    @SerializedName("tunica")
    @Expose
    private String tunica;
    @SerializedName("dia")
    @Expose
    private String dia;
    @SerializedName("numNazarenos")
    @Expose
    private Integer numNazarenos;
    @SerializedName("Paso")
    @Expose
    private List<Paso> paso = null;

    public Hermandad() {
    }

    public Hermandad(Long id, String nombre, String escudo, String tunica, String dia, Integer numNazarenos, List<Paso> paso) {
        this.id = id;
        this.nombre = nombre;
        this.escudo = escudo;
        this.tunica = tunica;
        this.dia = dia;
        this.numNazarenos = numNazarenos;
        this.paso = paso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEscudo() {
        return escudo;
    }

    public void setEscudo(String escudo) {
        this.escudo = escudo;
    }

    public String getTunica() {
        return tunica;
    }

    public void setTunica(String tunica) {
        this.tunica = tunica;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Integer getNumNazarenos() {
        return numNazarenos;
    }

    public void setNumNazarenos(Integer numNazarenos) {
        this.numNazarenos = numNazarenos;
    }

    public List<Paso> getPaso() {
        return paso;
    }

    public void setPaso(List<Paso> paso) {
        this.paso = paso;
    }

    @Override
    public String toString() {
        return "Hermandad{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", escudo='" + escudo + '\'' +
                ", tunica='" + tunica + '\'' +
                ", dia='" + dia + '\'' +
                ", numNazarenos=" + numNazarenos +
                ", paso=" + paso +
                '}';
    }
}
