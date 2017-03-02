package com.example.ammacias.quizzcofrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ammacias.quizzcofrade.Clases.Paso;
import com.example.ammacias.quizzcofrade.Interfaces.IRetrofit;
import com.example.ammacias.quizzcofrade.Pojos_API.Pasos;
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

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.random);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RandomActivity.class);
                startActivity(i);
            }
        });
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
            case "escudos":
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
            case "Marcha":
                i = new Intent(MainActivity.this, MyReproductor.class);
                startService(i);
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

}
