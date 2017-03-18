package com.example.ammacias.quizzcofrade.Interfaces;

import com.example.ammacias.quizzcofrade.Clases.Marcha;
import com.example.ammacias.quizzcofrade.Clases.Ranking;
import com.example.ammacias.quizzcofrade.Clases.Result;
import com.example.ammacias.quizzcofrade.Clases.Usuario;
import com.example.ammacias.quizzcofrade.Clases.UsuariosHermandades;
import com.example.ammacias.quizzcofrade.Pojos_API.Hermandades;
import com.example.ammacias.quizzcofrade.Pojos_API.Marchas;
import com.example.ammacias.quizzcofrade.Pojos_API.Pasos;
import com.example.ammacias.quizzcofrade.Pojos_API.Rankings;
import com.example.ammacias.quizzcofrade.Pojos_API.Usuarios;
import com.example.ammacias.quizzcofrade.Pojos_API.UsuariosHermandadesAPI;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

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

    @GET("ranking")
    Call<Rankings> getRankingRetrofit();

    @FormUrlEncoded
    @POST("usuario")
    Call<Usuario> createUser(@Field("nick") String idUsuario, @Field("email") String email,
                                @Field("idface") String idface, @Field("authToken") String authToken);

    @FormUrlEncoded
    @POST("ranking")
    Call<Ranking> createRanking(@Field("idUsuario") Long idUsuario, @Field("nick") String nick,
                                @Field("aciertos") Integer aciertos, @Field("fecha") String fecha);

    @FormUrlEncoded
    @POST("update-ranking") // TODO: HAY QUE PASARLE EL PARÁMETRO ID
    Call<Ranking> updateRanking(@Field("idUsuario") Long idUsuario, @Field("nick") String nick,
                                @Field("aciertos") Integer aciertos, @Field("fecha") String fecha);
}
