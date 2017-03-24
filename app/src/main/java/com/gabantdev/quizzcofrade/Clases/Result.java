package com.gabantdev.quizzcofrade.Clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gramos on 16/02/2017.
 */

public class Result {@SerializedName("Usuario")
@Expose
private List<Usuario> usuario = null;
    @SerializedName("Hermandad")
    @Expose
    private List<Hermandad> hermandad = null;
    @SerializedName("UsuariosHermandadesAPI")
    @Expose
    private List<UsuariosHermandades> usuariosHermandades = null;

    public Result() {
    }

    public Result(List<Usuario> usuario, List<Hermandad> hermandad, List<UsuariosHermandades> usuariosHermandades) {
        this.usuario = usuario;
        this.hermandad = hermandad;
        this.usuariosHermandades = usuariosHermandades;
    }

    public List<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<Usuario> usuario) {
        this.usuario = usuario;
    }

    public List<Hermandad> getHermandad() {
        return hermandad;
    }

    public void setHermandad(List<Hermandad> hermandad) {
        this.hermandad = hermandad;
    }

    public List<UsuariosHermandades> getUsuariosHermandades() {
        return usuariosHermandades;
    }

    public void setUsuariosHermandades(List<UsuariosHermandades> usuariosHermandades) {
        this.usuariosHermandades = usuariosHermandades;
    }

    @Override
    public String toString() {
        return "Result{" +
                "usuario=" + usuario +
                ", hermandad=" + hermandad +
                ", usuariosHermandades=" + usuariosHermandades +
                '}';
    }
}
