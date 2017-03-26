package com.gabantdev.quizzcofrade.Interfaces;

import com.gabantdev.quizzcofrade.localdb.HermandadDB;
import com.gabantdev.quizzcofrade.localdb.MarchaDB;
import com.gabantdev.quizzcofrade.localdb.PasosDB;


/**
 * Created by gramos on 16/02/2017.
 */

public interface ICofrade {
    void onClickHermandadDB(HermandadDB h);
    void onClickPasosDB(PasosDB p);
    void onClickMarcha(MarchaDB m);
}
