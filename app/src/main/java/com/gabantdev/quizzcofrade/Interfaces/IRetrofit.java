package com.gabantdev.quizzcofrade.Interfaces;

import com.gabantdev.quizzcofrade.Clases.Ranking;
import com.gabantdev.quizzcofrade.Clases.Result;
import com.gabantdev.quizzcofrade.Clases.Usuario;
import com.gabantdev.quizzcofrade.Pojos_API.Hermandades;
import com.gabantdev.quizzcofrade.Pojos_API.Marchas;
import com.gabantdev.quizzcofrade.Pojos_API.Pasos;
import com.gabantdev.quizzcofrade.Pojos_API.Rankings;
import com.gabantdev.quizzcofrade.Pojos_API.Usuarios;
import com.gabantdev.quizzcofrade.Pojos_API.UsuariosHermandadesAPI;

import retrofit.Call;
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
    Call<Usuario> createUser(@Field("nombre") String nombre, @Field("apellidos") String apellidos,
                             @Field("email") String email,
                             @Field("idface") String idface, @Field("authToken") String authToken);

    @FormUrlEncoded
    @POST("ranking") // Nombre y apellidos no haría falta actualizarlos => cambiar API
    Call<Ranking> createRanking(@Field("idUsuario") Long idUsuario,   @Field("nombre") String nombre,
                                @Field("apellidos") String apellidos, @Field("idface") String idface,
                                @Field("aciertos")  Integer aciertos, @Field("fecha") String fecha);

    @FormUrlEncoded
    @POST("update2-ranking") // TODO: HAY QUE PASARLE EL PARÁMETRO ID
    Call<Ranking> updateRanking(@Field("idUsuario") Long idUsuario,   @Field("nombre") String nombre,
                                @Field("apellidos") String apellidos, @Field("idface") String idface,
                                @Field("aciertos")  Integer aciertos, @Field("fecha") String fecha);
}
