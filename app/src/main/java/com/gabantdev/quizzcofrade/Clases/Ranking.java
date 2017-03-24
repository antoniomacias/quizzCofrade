package com.gabantdev.quizzcofrade.Clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gabri_neno on 13/03/2017.
 */

public class Ranking {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("idUsuario")
    @Expose
    private Long  idUsuario;
    @SerializedName("nombre")
    @Expose
    private String  nombre;
    @SerializedName("apellidos")
    @Expose
    private String  apellidos;
    @SerializedName("idface")
    @Expose
    private String  idface;
    @SerializedName("aciertos")
    @Expose
    private Integer  aciertos;
    @SerializedName("fecha")
    @Expose
    private String fecha;

    public Ranking() {
    }

    public Ranking(Long id, Long idUsuario, String nombre, String apellidos, String idface, Integer aciertos, String fecha) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.idface = idface;
        this.aciertos = aciertos;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }

    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getIdface() { return idface; }

    public void setIdface(String idface) { this.idface = idface; }

    public Integer getAciertos() {
        return aciertos;
    }

    public void setAciertos(Integer aciertos) {
        this.aciertos = aciertos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Ranking{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", idface='" + idface + '\'' +
                ", aciertos=" + aciertos +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
