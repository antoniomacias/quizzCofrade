package com.example.ammacias.quizzcofrade.Clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gramos on 16/02/2017.
 */

public class Usuario {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("nick")
    @Expose
    private String nick;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("idface")
    @Expose
    private String idface;
    @SerializedName("authToken")
    @Expose
    private String authToken;

    public Usuario() {
    }

    public Usuario(Long id, String nick, String email, String idface, String authToken) {
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

    public String getIdface() { return idface; }

    public void setIdface(String idface) { this.idface = idface; }

    public String getAuthToken() { return authToken; }

    public void setAuthToken(String authToken) { this.authToken = authToken; }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", email='" + email + '\'' +
                ", idface='" + idface + '\'' +
                ", authToken='" + authToken + '\'' +
                '}';
    }
}
