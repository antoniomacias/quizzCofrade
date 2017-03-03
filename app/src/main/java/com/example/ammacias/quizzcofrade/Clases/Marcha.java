package com.example.ammacias.quizzcofrade.Clases;

/**
 * Created by macias on 22/02/2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Marcha {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("banda")
    @Expose
    private String banda;
    @SerializedName("fecha")
    @Expose
    private String fecha;
    @SerializedName("ruta")
    @Expose
    private String ruta;

    public Marcha() {
    }

    public Marcha(Long id, String nombre, String banda, String fecha, String ruta) {
        this.id = id;
        this.nombre = nombre;
        this.banda = banda;
        this.fecha = fecha;
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "Marcha{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", banda='" + banda + '\'' +
                ", fecha='" + fecha + '\'' +
                ", ruta='" + ruta + '\'' +
                '}';
    }
    /*

    @SerializedName("marcha")
    @Expose
    private List<String> marcha = null;

    public List<String> getMarcha() {
        return marcha;
    }

    public void setMarcha(List<String> marcha) {
        this.marcha = marcha;
    }

    public Marcha() {
    }

    @Override
    public String toString() {
        return "Marcha{" +
                "marcha=" + marcha +
                '}';
    }*/
}
