package com.example.ammacias.quizzcofrade.Utils;

import android.app.Application;

/**
 * Created by gramos on 17/02/2017.
 */

public class Application_vars extends Application {

    public String categoriaElegida;

    public String getCategoriaElegida() {
        return categoriaElegida;
    }

    public void setCategoriaElegida(String categoriaElegida) {
        this.categoriaElegida = categoriaElegida;
    }
}