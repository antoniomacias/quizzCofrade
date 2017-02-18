package com.example.ammacias.quizzcofrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ammacias.quizzcofrade.Utils.Application_vars;
import com.example.ammacias.quizzcofrade.Interfaces.ICofrade;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.PasosDB;

public class MainActivity extends AppCompatActivity implements ICofrade{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                i = new Intent(MainActivity.this, EscudosActivity.class);
                startActivity(i);
                break;
            case "Tunicas":
                i = new Intent(MainActivity.this, EscudosActivity.class);
                startActivity(i);
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
