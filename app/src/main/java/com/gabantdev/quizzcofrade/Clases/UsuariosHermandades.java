package com.gabantdev.quizzcofrade.Clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gramos on 16/02/2017.
 */

public class UsuariosHermandades {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("idUsuario")
    @Expose
    private Long idUsuario;
    @SerializedName("idHermandad")
    @Expose
    private Long idHermandad;
    @SerializedName("categoria")
    @Expose
    private String categoria;

    public UsuariosHermandades() {
    }

    public UsuariosHermandades(Long id, Long idUsuario, Long idHermandad, String categoria) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idHermandad = idHermandad;
        this.categoria = categoria;
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

    public Long getIdHermandad() {
        return idHermandad;
    }

    public void setIdHermandad(Long idHermandad) {
        this.idHermandad = idHermandad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "UsuariosHermandades{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", idHermandad=" + idHermandad +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
