package com.example.ammacias.quizzcofrade.DetalleActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ammacias.quizzcofrade.R;
import com.example.ammacias.quizzcofrade.Utils.Application_vars;
import com.example.ammacias.quizzcofrade.localdb.DatabaseConnection;
import com.example.ammacias.quizzcofrade.localdb.PasosDB;
import com.example.ammacias.quizzcofrade.localdb.PasosDBDao;
import com.example.ammacias.quizzcofrade.localdb.UsuarioDB;
import com.example.ammacias.quizzcofrade.localdb.UsuarioDBDao;
import com.example.ammacias.quizzcofrade.localdb.UsuariosHermandadesDB;
import com.example.ammacias.quizzcofrade.localdb.UsuariosHermandadesDBDao;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DetallePasosActivity extends AppCompatActivity {


    ImageView imageView;
    EditText respuesta;

    //Gestion MarchasDB
    PasosDBDao pasosDBDao;
    List<PasosDB> listaPasos;
    Long id_aux;
    int posicionLista;
    String cat_elegida = "";

    //Tabla intermedia
    UsuariosHermandadesDBDao tabla_intermedia=null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pasos);

        imageView =(ImageView)findViewById(R.id.fotoDetalle);
        respuesta =(EditText)findViewById(R.id.respuesta_marcha);

        Intent i = getIntent();
        //id = i.getExtras().getLong("IDHermandad");
        posicionLista = i.getExtras().getInt("IDPaso");

        //TODO:Meter la lista en APPLICATION
        //listaPasos = new LinkedList<>(((Application_vars) this.getApplication()).getListHermandadEscudos());
        pasosDBDao = DatabaseConnection.getPasosDBDao(this);
        listaPasos = pasosDBDao.loadAll();

        id_aux = listaPasos.get(posicionLista).getId();
        cat_elegida = ((Application_vars) this.getApplication()).getCategoriaElegida();
        

        String s=null;
        if(checkAcertado(id_aux)){
            for(PasosDB d: listaPasos){
                if(d.getId() == id_aux){
                    s = d.getNombreTitular();
                }
            }
            respuesta.setText(s);
        }
        jugar(id_aux);
    } // Fin onCreate

    private Boolean checkAcertado(Long id_actual) {
        System.out.println("Compruebo si acertado");
        Boolean res = false;
        tabla_intermedia = DatabaseConnection.getUsuariosHermandadesDBDao(this);
        List<UsuariosHermandadesDB> registros = tabla_intermedia.loadAll();
        for(UsuariosHermandadesDB uh : registros){
            System.out.println("Checkeo: "+uh.getIdHermandad()+"-"+id_actual+"-"+uh.getCategoria()+"-"+cat_elegida);
            if(uh.getIdHermandad() == id_actual && uh.getCategoria().equals(cat_elegida) /* Sólo hay un usuario && uh.getIdUsuario() ==*/ ){
                // Si entra es que existe y está acertada
                res = true;
                System.out.println(res);
            }
        }
        return res;
    }

    public void jugar(final Long id_aux){
        pasosDBDao = DatabaseConnection.getPasosDBDao(this);
        Picasso.with(this)
                .load(R.drawable.emptystar)
                .resize(250, 200)
                .into(imageView);

        System.out.println("Nombre paso: "+pasosDBDao.load(id_aux).getNombreTitular());
        respuesta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (respuesta.getText().toString().equalsIgnoreCase(pasosDBDao.load(id_aux).getNombreTitular())){
                    muestradialogo();
                    //System.out.println("Acertaste y guardo");
                }
            }
        });
    }

    private void muestradialogo() {
        guardarAcierto(pasosDBDao.load(id_aux).getId());
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("¡FLAMA HERMANO!")
                .setContentText("¿Quieres pasar al siguiente nivel?")
                .setConfirmText("Pasar al siguiente nivel")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        next_marcha(findViewById(R.id.activity_detalle));
                    }
                })
                .show();
    }

    private void guardarAcierto(Long ide) {
        UsuariosHermandadesDBDao usuariosHermandadesDBDao =
                DatabaseConnection.getUsuariosHermandadesDBDao(this);

        // Los valores actuales a insertar => Necesito el usuario
        UsuariosHermandadesDB usuariosHermandadesDB = new UsuariosHermandadesDB();
        UsuarioDBDao usuario = DatabaseConnection.getUsuarioDBDao(this);
        List<UsuarioDB> usuario_actual = usuario.loadAll();
        /*if(usuario_actual.size()==1) System.out.println("Eres el único usuario");
        else System.out.println("¡Hay más de uno! "+usuario_actual.size());*/

        usuariosHermandadesDB.setIdUsuario(usuario_actual.get(0).getId());
        usuariosHermandadesDB.setCategoria(((Application_vars) this.getApplication()).getCategoriaElegida());
        usuariosHermandadesDB.setIdHermandad(ide);

        usuariosHermandadesDBDao.insertOrReplace(usuariosHermandadesDB);
        //System.out.println(usuariosHermandadesDBDao.getEntidad(usuariosHermandadesDB));
    }

    public void next_marcha(View view) {

        //TODO: Stop mediaplayer (Exception = null)
        /*MyReproductor reproductor = new MyReproductor();
        reproductor.stopAudio();*/
        posicionLista++;
        id_aux = listaPasos.get(posicionLista).getId();

        // Busco si está acertada (Tabla_Intermedia)
        while(checkAcertado(id_aux)){
            posicionLista++;
            id_aux = listaPasos.get(posicionLista).getId();
        }
        //posicionLista++;

        Toast.makeText(this, "Marcha "+posicionLista+" de "+listaPasos.size(), Toast.LENGTH_SHORT).show();

        if(posicionLista==listaPasos.size()-1){
            Toast.makeText(this, "Llegaste al límite", Toast.LENGTH_SHORT).show();
            posicionLista=-1;
        }
        respuesta.setText("");
        jugar(id_aux);
    }

    public void previous_marcha(View view) {
        posicionLista--;
        id_aux = listaPasos.get(posicionLista).getId();

        while(checkAcertado(id_aux)){
            posicionLista--;
            id_aux = listaPasos.get(posicionLista).getId();
        }
        if( posicionLista==0 ){
            posicionLista=listaPasos.size();
        }
        respuesta.setText("");
        jugar(id_aux);
    }
}