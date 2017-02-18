package com.example.ammacias.quizzcofrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ammacias.quizzcofrade.Utils.Application_vars;
import com.example.ammacias.quizzcofrade.localdb.DatabaseConnection;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.HermandadDBDao;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DetalleActivity extends AppCompatActivity {

    ImageView imageView;
    EditText respuesta;

    HermandadDBDao hermandadDBDao;
    List<HermandadDB> lista;
    Long id_aux;
    int posicionLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        imageView =(ImageView)findViewById(R.id.fotoDetalle);
        respuesta =(EditText)findViewById(R.id.respuesta_escudo);

        Intent i = getIntent();
        //id = i.getExtras().getLong("IDHermandad");
        posicionLista = i.getExtras().getInt("posicion");
        lista = new LinkedList<>(((Application_vars) this.getApplication()).getListHermandadEscudos());

        id_aux = lista.get(posicionLista).getId();


        jugar(id_aux);
    } // Fin onCreate

    public void jugar(final Long id_aux){
        hermandadDBDao = DatabaseConnection.getHermandadDBDao(this);
        Picasso.with(this)
                .load(hermandadDBDao.load(id_aux).getEscudo())
                .resize(250, 200)
                .into(imageView);

        respuesta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (respuesta.getText().toString().equalsIgnoreCase(hermandadDBDao.load(id_aux).getNombre())){
                    muestradialogo();
                    //TODO: Machaco el booleano
/*                  marcaDB.setAcertado(true);
                    marcaDBDao.update(marcaDB);*/
                }
            }
        });
    }

    private void muestradialogo() {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("¡FLAMA HERMANO!")
                .setContentText("¿Quieres pasar al siguiente nivel?")
                .setConfirmText("Pasar al siguiente nivel")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        next_escudo(findViewById(R.id.activity_detalle));
                    }
                })
                .show();
    }


    public void next_escudo(View view) {
        posicionLista++;
        Toast.makeText(this, "Escudo "+posicionLista+" de "+lista.size(), Toast.LENGTH_SHORT).show();
        id_aux = lista.get(posicionLista).getId();

        if(posicionLista==lista.size()-1){
            Toast.makeText(this, "Llegaste al límite", Toast.LENGTH_SHORT).show();
            posicionLista=-1;
        }
        respuesta.setText("");
        jugar(id_aux);
    }

    public void previous_escudo(View view) {
        posicionLista--;
        id_aux = lista.get(posicionLista).getId();

        if(posicionLista==0){
            Toast.makeText(this, "Llegaste al límite", Toast.LENGTH_SHORT).show();
            posicionLista=lista.size();
        }
        respuesta.setText("");
        jugar(id_aux);
    }
}
