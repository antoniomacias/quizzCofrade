package com.example.ammacias.quizzcofrade.localdb;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "RANKING_DB".
 */
@Entity
public class RankingDB {

    @Id(autoincrement = true)
    private Long id;
    private Long idUsuario;
    private String nick;
    private Integer aciertos;
    private java.util.Date fecha;

    @Generated
    public RankingDB() {
    }

    public RankingDB(Long id) {
        this.id = id;
    }

    @Generated
    public RankingDB(Long id, Long idUsuario, String nick, Integer aciertos, java.util.Date fecha) {
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

    public java.util.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.util.Date fecha) {
        this.fecha = fecha;
    }

}
