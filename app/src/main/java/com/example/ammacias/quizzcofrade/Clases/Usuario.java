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

    public Usuario() {
    }

    public Usuario(Long id, String nick) {
        this.id = id;
        this.nick = nick;
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

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }
}
