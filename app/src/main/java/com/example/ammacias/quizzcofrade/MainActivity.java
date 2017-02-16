package com.example.ammacias.quizzcofrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ammacias.quizzcofrade.Clases.Result;
import com.example.ammacias.quizzcofrade.Interfaces.ICofrade;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements ICofrade{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClickCategoria(String categoria) {
        Toast.makeText(this, "Te queas muerto", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Call<Result> getDatos() {
        return null;
    }
}
