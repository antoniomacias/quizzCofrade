package com.example.ammacias.quizzcofrade.Interfaces;

import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.MarchaDB;
import com.example.ammacias.quizzcofrade.localdb.PasosDB;

import java.util.List;


/**
 * Created by gramos on 16/02/2017.
 */

public interface ICofrade {
    void onClickHermandadDB(HermandadDB h);
    void onClickPasosDB(PasosDB p);
    void onClickMarcha(MarchaDB m);
}
