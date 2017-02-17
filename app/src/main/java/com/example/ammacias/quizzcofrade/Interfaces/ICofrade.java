package com.example.ammacias.quizzcofrade.Interfaces;

import com.example.ammacias.quizzcofrade.Clases.Result;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.PasosDB;


/**
 * Created by gramos on 16/02/2017.
 */

public interface ICofrade {
    void onClickCategoria(String categoria);
    void onClickHermandadDB(HermandadDB h);
    void onClickPasosDB(PasosDB p);
}
