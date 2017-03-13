package com.example.ammacias.quizzcofrade.Clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;

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
    @SerializedName("nick")
    @Expose
    private String  nick;
    @SerializedName("aciertos")
    @Expose
    private Integer  aciertos;
    @SerializedName("fecha")
    @Expose
    private String fecha;

    public Ranking() {
    }

    public Ranking(Long id, Long idUsuario, String nick, Integer aciertos, String fecha) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.nick = nick;
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

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

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
                ", nick='" + nick + '\'' +
                ", aciertos=" + aciertos +
                ", fecha=" + fecha +
                '}';
    }
}
