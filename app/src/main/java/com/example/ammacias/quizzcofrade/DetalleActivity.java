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

import com.example.ammacias.quizzcofrade.Clases.Result;
import com.example.ammacias.quizzcofrade.Clases.UsuariosHermandades;
import com.example.ammacias.quizzcofrade.Interfaces.IRetrofit;
import com.example.ammacias.quizzcofrade.Utils.Application_vars;
import com.example.ammacias.quizzcofrade.localdb.DatabaseConnection;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.HermandadDBDao;
import com.example.ammacias.quizzcofrade.localdb.UsuarioDB;
import com.example.ammacias.quizzcofrade.localdb.UsuarioDBDao;
import com.example.ammacias.quizzcofrade.localdb.UsuariosHermandadesDB;
import com.example.ammacias.quizzcofrade.localdb.UsuariosHermandadesDBDao;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

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
                    //TODO: Inserto en la tabla intermedia
                    System.out.println("Acertaste y guardo");
/*                  marcaDB.setAcertado(true);
                    marcaDBDao.update(marcaDB);*/

                }
            }
        });
    }

    private void guardarAcierto(Long ide) {
        UsuariosHermandadesDBDao usuariosHermandadesDBDao =
                DatabaseConnection.getUsuariosHermandadesDBDao(DetalleActivity.this);

        // Los valores actuales a insertar => Necesito el usuario
        UsuariosHermandadesDB usuariosHermandadesDB = new UsuariosHermandadesDB();
        //UsuarioDB usuarioDB = new UsuarioDB();
        UsuarioDBDao usuario = DatabaseConnection.getUsuarioDBDao(DetalleActivity.this);
        List<UsuarioDB> usuario_actual = usuario.loadAll();
        if(usuario_actual.size()==1) System.out.println("Eres el único usuario");
        else System.out.println("¡Hay más de uno! "+usuario_actual.size());

        //usuariosHermandadesDB.setId(uh.getId());
        usuariosHermandadesDB.setIdUsuario(usuario_actual.get(0).getId());
        usuariosHermandadesDB.setCategoria(((Application_vars) this.getApplication()).getCategoriaElegida());
        usuariosHermandadesDB.setIdHermandad(ide);

        usuariosHermandadesDBDao.insertOrReplace(usuariosHermandadesDB);
        System.out.println(usuariosHermandadesDBDao.getEntidad(usuariosHermandadesDB));
    }


    private void muestradialogo() {
        guardarAcierto(hermandadDBDao.load(id_aux).getId());
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
