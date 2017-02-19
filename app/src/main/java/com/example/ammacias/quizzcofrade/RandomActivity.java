package com.example.ammacias.quizzcofrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ammacias.quizzcofrade.localdb.DatabaseConnection;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.HermandadDBDao;
import com.example.ammacias.quizzcofrade.localdb.PasosDB;
import com.example.ammacias.quizzcofrade.localdb.PasosDBDao;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RandomActivity extends AppCompatActivity {

    List<HermandadDB> listaH;
    List<PasosDB> listaP;

    ImageView imageView;
    EditText respuesta;

    String nombreRespuesta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        respuesta =(EditText)findViewById(R.id.respuesta_escudo);

        HermandadDBDao hermandadDBDao = DatabaseConnection.getHermandadDBDao(this);
        listaH = hermandadDBDao.loadAll();

        PasosDBDao PasosDBDao = DatabaseConnection.getPasosDBDao(this);
        listaP = PasosDBDao.loadAll();

        respuesta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (respuesta.getText().toString().equalsIgnoreCase(nombreRespuesta)){
                    muestradialogo();
                    //TODO: Inserto en la tabla intermedia
                    System.out.println("Acertaste y guardo");
/*                  marcaDB.setAcertado(true);
                    marcaDBDao.update(marcaDB);*/

                }
            }
        });



        imageView =(ImageView)findViewById(R.id.fotoDetalle);

        //Si es 1 -> Hermandad
        if ((int) (Math.random() * 1)== 1 ){
            Picasso.with(this)
                    .load(listaH.get(0).getEscudo())
                    .resize(250, 200)
                    .into(imageView);
        }else{
            Picasso.with(this)
                    .load(listaP.get(0).getFoto())
                    .resize(250, 200)
                    .into(imageView);
        }
    }

    private void muestradialogo() {
        //guardarAcierto(hermandadDBDao.load(id_aux).getId());
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
        //Si es 0 -> Hermandad
        //Si es 1 -> Paso
        //Si es 2 -> Tunica
        int aux = (int) (Math.random() * 3);
        System.out.println("Numero: "+aux);
        if (aux== 0){
            Picasso.with(this)
                    .load(listaH.get((int) (Math.random() * listaH.size())).getEscudo())
                    .resize(250, 200)
                    .into(imageView);
            nombreRespuesta = listaH.get(0).getNombre();
        }else if(aux ==1){
            Picasso.with(this)
                    .load(listaP.get((int) (Math.random() * listaP.size())).getFoto())
                    .resize(250, 200)
                    .into(imageView);
            nombreRespuesta = listaP.get(0).getNombreTitular();
        }else{
            Picasso.with(this)
                    .load("http://juegomarcas.esy.es/SS/images/ncage.jpg")
                    .resize(250, 200)
                    .into(imageView);
            nombreRespuesta = listaH.get(0).getNombre();
        }
    }


}
