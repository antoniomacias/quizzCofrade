package com.example.ammacias.quizzcofrade.localdb;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "USUARIO_DB".
 */
@Entity
public class UsuarioDB {

    @Id(autoincrement = true)
    private Long id;
    private String nick;
    private String email;
    private String idface;
    private String authToken;

    @Generated
    public UsuarioDB() {
    }

    public UsuarioDB(Long id) {
        this.id = id;
    }

    @Generated
    public UsuarioDB(Long id, String nick, String email, String idface, String authToken) {
        this.id = id;
        this.nick = nick;
        this.email = email;
        this.idface = idface;
        this.authToken = authToken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdface() {
        return idface;
    }

    public void setIdface(String idface) {
        this.idface = idface;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public String toString() {
        return "UsuarioDB{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", email='" + email + '\'' +
                ", idface='" + idface + '\'' +
                ", authToken='" + authToken + '\'' +
                '}';
    }
}
