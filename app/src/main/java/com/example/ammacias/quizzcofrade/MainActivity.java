package com.example.ammacias.quizzcofrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ammacias.quizzcofrade.Clases.Paso;
import com.example.ammacias.quizzcofrade.Interfaces.IRetrofit;
import com.example.ammacias.quizzcofrade.Pojos_API.Pasos;
import com.example.ammacias.quizzcofrade.Service.MyReproductor;
import com.example.ammacias.quizzcofrade.Utils.Application_vars;
import com.example.ammacias.quizzcofrade.Interfaces.ICofrade;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.PasosDB;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements ICofrade{
    Intent i;
    TextView escudos, tunicas, pasos, marchas, aleatorio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        escudos = (TextView)findViewById(R.id.escudos);
        tunicas = (TextView)findViewById(R.id.tunicas);
        pasos = (TextView)findViewById(R.id.pasos);
        marchas = (TextView)findViewById(R.id.marchas);
        aleatorio = (TextView)findViewById(R.id.random);


    }

    @Override
    public void onClickCategoria(String categoria) {

        // TODO: Switch con la categoría
        ((Application_vars) this.getApplication()).setCategoriaElegida(categoria);
        String cat_elegida = ((Application_vars) this.getApplication()).getCategoriaElegida();
        Intent i;
        switch (cat_elegida){
            case "Pasos":
                System.out.println("PasosActivity");
                i = new Intent(MainActivity.this, PasosActivity.class);
                startActivity(i);
                break;
            case "Escudos":
                i = new Intent(MainActivity.this, EscudosActivity.class);
                startActivity(i);
                break;
            case "Random":
                i = new Intent(MainActivity.this, RandomActivity.class);
                startActivity(i);
                break;
            case "Tunicas":
                i = new Intent(MainActivity.this, EscudosActivity.class);
                startActivity(i);
                break;
            case "Marchas":
                i = new Intent(MainActivity.this, MarchaActivity.class);
                startActivity(i);
                /*i = new Intent(MainActivity.this, MyReproductor.class);
                startService(i);*/
                break;
            default:
                break;
        }
    }

    @Override
    public void onClickHermandadDB(HermandadDB h, int posicionDeLaLista) {

    }

    @Override
    public void onClickPasosDB(PasosDB p) {
    }

    @Override
    public void onClickMarcha(String s) {

    }

    public void click_escudos(View view) {
        ((Application_vars) this.getApplication()).setCategoriaElegida("Escudos");
        i = new Intent(MainActivity.this, EscudosActivity.class);
        startActivity(i);
    }

    public void click_tunicas(View view) {
        ((Application_vars) this.getApplication()).setCategoriaElegida("Tunicas");
        i = new Intent(MainActivity.this, EscudosActivity.class);
        startActivity(i);
    }

    public void click_pasos(View view) {
        ((Application_vars) this.getApplication()).setCategoriaElegida("Pasos");
        i = new Intent(MainActivity.this, PasosActivity.class);
        startActivity(i);
    }

    public void click_marchas(View view) {
        ((Application_vars) this.getApplication()).setCategoriaElegida("Marchas");
        i = new Intent(MainActivity.this, MarchaActivity.class);
        startActivity(i);
    }

    public void click_random(View view) {
        ((Application_vars) this.getApplication()).setCategoriaElegida("Random");
        i = new Intent(MainActivity.this, RandomActivity.class);
        startActivity(i);
    }
}
