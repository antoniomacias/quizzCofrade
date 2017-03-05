package com.example.ammacias.quizzcofrade.Interfaces;

import com.example.ammacias.quizzcofrade.Clases.Result;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.PasosDB;

import java.util.List;


/**
 * Created by gramos on 16/02/2017.
 */

public interface ICofrade {
    void onClickCategoria(String categoria);
    void onClickHermandadDB(HermandadDB h, List<HermandadDB>l, int posicionDeLaLista);
    void onClickPasosDB(PasosDB p);
    void onClickMarcha(String s);
}
