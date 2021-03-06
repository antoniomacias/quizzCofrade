package com.gabantdev.quizzcofrade.DetalleActivity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.gabantdev.quizzcofrade.R;
import com.gabantdev.quizzcofrade.Service.MyReproductor;
import com.gabantdev.quizzcofrade.Utils.Application_vars;
import com.gabantdev.quizzcofrade.localdb.DatabaseConnection;
import com.gabantdev.quizzcofrade.localdb.MarchaDB;
import com.gabantdev.quizzcofrade.localdb.MarchaDBDao;
import com.gabantdev.quizzcofrade.localdb.UsuarioDB;
import com.gabantdev.quizzcofrade.localdb.UsuarioDBDao;
import com.gabantdev.quizzcofrade.localdb.UsuariosHermandadesDB;
import com.gabantdev.quizzcofrade.localdb.UsuariosHermandadesDBDao;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import co.mobiwise.library.InteractivePlayerView;
import co.mobiwise.library.OnActionClickedListener;

public class DetalleMarchaActivity extends AppCompatActivity {


    ImageView imageView;
    EditText respuesta;

    //Gestion MarchasDB
    MarchaDBDao marchasDBDao;
    List<MarchaDB> listaMarchas;
    Long id_aux;
    int posicionLista;
    String cat_elegida = "";

    //Tabla intermedia
    UsuariosHermandadesDBDao tabla_intermedia=null;

    //Reproductor
    InteractivePlayerView ipv;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_marcha);

        //imageView =(ImageView)findViewById(R.id.fotoDetalle);
        respuesta =(EditText)findViewById(R.id.respuesta_marcha);
        ipv = (InteractivePlayerView) findViewById(R.id.ipv);


        posicionLista = getIntent().getExtras().getInt("posicion");

        //listaMarchas = new LinkedList<>(((Application_vars) this.getApplication()).getListHermandadEscudos());
        marchasDBDao = DatabaseConnection.getMarchasDBDao(this);
        listaMarchas = marchasDBDao.loadAll();

        id_aux = listaMarchas.get(posicionLista).getId();
        cat_elegida = ((Application_vars) this.getApplication()).getCategoriaElegida();

        /*imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Parar si estuviera reproduciendo, ya que se enciende al iniciar el Detalle
                MyReproductor mr = new MyReproductor();
                mr.stopSelf();
                System.out.println("Parado");
                Intent i = new Intent(DetalleMarchaActivity.this, MyReproductor.class);
                i.putExtra("cancion", marchasDBDao.load(id_aux).getRuta());
                startService(i);
            }
        });*/


        String s=null;
        if(checkAcertado(id_aux)){
            respuesta.setText(listaMarchas.get(posicionLista).getNombre());
        }
        jugar(id_aux);


    } // Fin onCreate

    private Boolean checkAcertado(Long id_actual) {
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

        //Inicio el servicio
        i = new Intent(DetalleMarchaActivity.this, MyReproductor.class);
        i.putExtra("cancion", marchasDBDao.load(id_aux).getRuta());
        startService(i);

        //Inicio el reproductor
        iniciarMediaPlayer();


        marchasDBDao = DatabaseConnection.getMarchasDBDao(this);
        /*Picasso.with(this)
                .load(R.drawable.emptystar)
                .resize(250, 200)
                .into(imageView);*/
        System.out.println("********************************");
        System.out.println("Nombre marcha: "+marchasDBDao.load(id_aux).getNombre());
        System.out.println("********************************");
        respuesta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (respuesta.getText().toString().equalsIgnoreCase(marchasDBDao.load(id_aux).getNombre())){
                    muestradialogo();
                    //System.out.println("Acertaste y guardo");
                }
            }
        });
    }

    private void muestradialogo() {
        guardarAcierto(marchasDBDao.load(id_aux).getId());
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
        stopService(i);
        if(posicionLista==listaMarchas.size()-1){
            posicionLista=0;
        }else{
            posicionLista++;
        }

        id_aux = listaMarchas.get(posicionLista).getId();

        // Busco si está acertada (Tabla_Intermedia)
        while(checkAcertado(id_aux)){
            if(posicionLista==listaMarchas.size()-1){
                posicionLista=0;
            }else{
                posicionLista++;
            }
            id_aux = listaMarchas.get(posicionLista).getId();
        }

        respuesta.setText("");
        jugar(id_aux);
    }

    public void previous_marcha(View view) {
        stopService(i);
        if( posicionLista==0 ){
            posicionLista=listaMarchas.size()-1;
        }else{
            posicionLista--;
        }
        id_aux = listaMarchas.get(posicionLista).getId();

        while(checkAcertado(id_aux)){
            if( posicionLista==0 ){
                posicionLista=listaMarchas.size()-1;
            }else{
                posicionLista--;
            }
            id_aux = listaMarchas.get(posicionLista).getId();
        }

        respuesta.setText("");
        jugar(id_aux);
    }

    void iniciarMediaPlayer(){
        //Skin reproductor
        ipv.stop();
        ipv.setProgress(0);
        ipv.setMax(20);
        ipv.setProgressEmptyColor(Color.GRAY);
        ipv.setProgressEmptyColor(Color.BLACK);
        ipv.start();
        ipv.setOnActionClickedListener(new OnActionClickedListener() {
            @Override
            public void onActionClicked(int id) {
                switch (id){
                    case 1:
                        Toast.makeText(DetalleMarchaActivity.this, "Play", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(DetalleMarchaActivity.this, "Pause", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        //Called when 3. action is clicked.
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(i);
    }
}
