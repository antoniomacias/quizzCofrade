package com.example.ammacias.quizzcofrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ammacias.quizzcofrade.Clases.Marcha;
import com.example.ammacias.quizzcofrade.Clases.Result;
import com.example.ammacias.quizzcofrade.Interfaces.IRetrofit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MarchaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcha);

        //RETROFIT
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IRetrofit.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRetrofit service = retrofit.create(IRetrofit.class);

        Call<Marcha> autocompleteList = service.getMarchas();

        autocompleteList.enqueue(new Callback<Marcha>() {
            @Override
            public void onResponse(Response<Marcha> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Marcha r = response.body();

                    for (String a:r.getMarcha()) {
                        System.out.println(a.toString());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
