package com.example.ammacias.quizzcofrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ammacias.quizzcofrade.Utils.Application_vars;
import com.example.ammacias.quizzcofrade.Interfaces.ICofrade;

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


        Intent jugar = new Intent(MainActivity.this, JuegoActivity.class);
        //jugar.putExtra("cat",categoria);
        startActivity(jugar);
    }


}
