package com.example.ammacias.quizzcofrade.Interfaces;

import com.example.ammacias.quizzcofrade.Clases.Marcha;
import com.example.ammacias.quizzcofrade.Clases.PasoAPI;
import com.example.ammacias.quizzcofrade.Clases.Result;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by ammacias on 16/02/2017.
 */

public interface IRetrofit {
    String ENDPOINT = "http://juegomarcas.esy.es";
    String ENDPOINT1 = "http://gabronio.esy.es/api.php/";

    @GET("/SS/datos.json")
    Call<Result> getDatos();

    @GET("/SS/marchas.json")
    Call<Marcha> getMarchas();

    @GET("pasos")
    Call<PasoAPI> getPasosRetrofit();
}
