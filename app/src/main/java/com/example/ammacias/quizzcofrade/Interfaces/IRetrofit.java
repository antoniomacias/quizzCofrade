package com.example.ammacias.quizzcofrade.Interfaces;

import com.example.ammacias.quizzcofrade.Clases.Result;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by ammacias on 16/02/2017.
 */

public interface IRetrofit {
    String ENDPOINT = "http://juegomarcas.esy.es";

    @GET("/SS/datos.json")
    Call<Result> getDatos();
}
