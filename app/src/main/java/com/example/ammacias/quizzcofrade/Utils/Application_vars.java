package com.example.ammacias.quizzcofrade.Utils;

import android.app.Application;

import com.example.ammacias.quizzcofrade.localdb.HermandadDB;

import java.util.List;

/**
 * Created by gramos on 17/02/2017.
 */

public class Application_vars extends Application {

    public String categoriaElegida;
    List<HermandadDB> listHermandadEscudos;
    // TODAS LAS HERMANDAES, pero le pongo escudos para saber q es de ese Activity
    // Si hay que volver a usarlo pues se cambia el nombre por otro más genérico

    public List<HermandadDB> getListHermandadEscudos() {
        return listHermandadEscudos;
    }

    public void setListHermandadEscudos(List<HermandadDB> listHermandadEscudos) {
        this.listHermandadEscudos = listHermandadEscudos;
    }

    public String getCategoriaElegida() {
        return categoriaElegida;
    }

    public void setCategoriaElegida(String categoriaElegida) {
        this.categoriaElegida = categoriaElegida;
    }
}