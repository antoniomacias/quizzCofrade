package com.gabantdev.quizzcofrade.Utils;

import android.app.Application;

import com.gabantdev.quizzcofrade.Clases.Usuario;

/**
 * Created by gramos on 17/02/2017.
 */

public class Application_vars extends Application {

    public String categoriaElegida;
    public Usuario u;

    public String getCategoriaElegida() {
        return categoriaElegida;
    }

    public void setCategoriaElegida(String categoriaElegida) {
        this.categoriaElegida = categoriaElegida;
    }

    public Usuario getU() {
        return u;
    }

    public void setU(Usuario u) {
        this.u = u;
    }
}