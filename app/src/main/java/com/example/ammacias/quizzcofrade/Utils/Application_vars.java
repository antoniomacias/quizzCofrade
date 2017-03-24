package com.example.ammacias.quizzcofrade.Utils;

import android.app.Application;

import com.example.ammacias.quizzcofrade.Clases.Usuario;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;

import java.util.List;

/**
 * Created by gramos on 17/02/2017.
 **/

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