package com.example.ammacias.quizzcofrade.Interfaces;

import com.example.ammacias.quizzcofrade.Clases.Marcha;
import com.example.ammacias.quizzcofrade.Clases.Result;
import com.example.ammacias.quizzcofrade.Clases.UsuariosHermandades;
import com.example.ammacias.quizzcofrade.Pojos_API.Hermandades;
import com.example.ammacias.quizzcofrade.Pojos_API.Marchas;
import com.example.ammacias.quizzcofrade.Pojos_API.Pasos;
import com.example.ammacias.quizzcofrade.Pojos_API.Usuarios;
import com.example.ammacias.quizzcofrade.Pojos_API.UsuariosHermandadesAPI;

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

    @GET("marchas")
    Call<Marchas> getMarchas();

    @GET("pasos")
    Call<Pasos> getPasosRetrofit();

    @GET("hermandades")
    Call<Hermandades> getHermandadesRetrofit();

    @GET("usuarios")
    Call<Usuarios> getUsuariosRetrofit();

    @GET("usuarios_hermandades")
    Call<UsuariosHermandadesAPI> getUsuariosHermandadesRetrofit();
}
