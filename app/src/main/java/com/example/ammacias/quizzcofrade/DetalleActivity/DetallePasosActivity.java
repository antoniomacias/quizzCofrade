package com.example.ammacias.quizzcofrade.DetalleActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.example.ammacias.quizzcofrade.Clases.Hermandad;
import com.example.ammacias.quizzcofrade.R;
import com.example.ammacias.quizzcofrade.Utils.Application_vars;
import com.example.ammacias.quizzcofrade.localdb.DatabaseConnection;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.HermandadDBDao;
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

    PasosDB paso;
    PasosDBDao pasosDBDao;
    List<PasosDB> ListaDesordenada;
    List<HermandadDB> hermandades;
    HermandadDB hermandad;
    Long id_aux;
    int posicionLista;
    String cat_elegida = "";
    TextView titulo;
    TextView pregunta_detalle_escudos;

    UsuariosHermandadesDBDao tabla_intermedia=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_escudos);

        imageView =(ImageView)findViewById(R.id.fotoDetalle);
        respuesta =(EditText)findViewById(R.id.respuesta_escudo);
        titulo = (TextView)findViewById(R.id.titulo);

        pregunta_detalle_escudos = (TextView)findViewById(R.id.pregunta_detalle_escudos);

        //Hermandad seleccionada
        paso = DatabaseConnection.getPasosDBDao(this).load(getIntent().getExtras().getLong("IDPaso"));
        id_aux = paso.getId();
        hermandades =DatabaseConnection.getHermandadDBDao(this).loadAll();
        for(HermandadDB h:hermandades){
            if(h.getNombre().equals(paso.getNombreHermandad()))hermandad = h;
        }
        System.out.println("PASO ESCOGIDO : "+paso);
        System.out.println("De la hermandad "+hermandad);

        ListaDesordenada = DatabaseConnection.getPasosDBDao(this).loadAll();
        for(int ii=0;ii<ListaDesordenada.size();ii++){
            if(ListaDesordenada.get(ii).getId().equals(id_aux) || ListaDesordenada.get(ii).getId() == id_aux){
                posicionLista = ii;
            }
        }

        //posicionLista = ListaDesordenada.indexOf(herma);

        cat_elegida = ((Application_vars) this.getApplication()).getCategoriaElegida();

        if (cat_elegida.equalsIgnoreCase("Pasos")){
            pregunta_detalle_escudos.setText("Al cielo con... ¿cuál?");
            titulo.setText("Pasos");
        }else { // Llamadores
            pregunta_detalle_escudos.setText("¿A esta es, o no es?");
            titulo.setText("Llamadores");
        }

        String s=null;
        if(checkAcertado(id_aux)){
            s = hermandad.getNombre();
            respuesta.setText(s);
        }
        jugar(id_aux);
    } // Fin onCreate

    public void jugar(final Long id_aux){
        pasosDBDao = DatabaseConnection.getPasosDBDao(this);
        for(HermandadDB h:hermandades){
            if(h.getNombre().equals(pasosDBDao.load(id_aux).getNombreHermandad()))hermandad = h;
        }

        if (cat_elegida.equalsIgnoreCase("Pasos")){
            Picasso.with(this)
                    .load(pasosDBDao.load(id_aux).getFotoPaso())
                    .resize(500, 400)
                    .into(imageView);
        }else { // Llamadores
            Picasso.with(this)
                    .load(pasosDBDao.load(id_aux).getLlamador())
                    .resize(500, 400)
                    .into(imageView);
        }



        //hermandad =DatabaseConnection.getHermandadDBDao(this).load(pasosDBDao.load(id_aux).getIdHermandad());

        respuesta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (respuesta.getText().toString().equalsIgnoreCase(hermandad.getNombre())){
                    muestradialogo();
                    //System.out.println("Acertaste y guardo");
                }
            }
        });


        final ImagePopup imagePopup = new ImagePopup(this);
        imagePopup.setBackgroundColor(Color.BLACK);

        imagePopup.setHideCloseIcon(false);
        imagePopup.setImageOnClickClose(true);
        //this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        final Boolean[] grande = {false};

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DetallePasosActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                //imageView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                if(grande[0]) {
                    if (cat_elegida.equalsIgnoreCase("Pasos")){
                        Picasso.with(DetallePasosActivity.this)
                                .load(pasosDBDao.load(id_aux).getFotoPaso())
                                .resize(500, 400)
                                .into(imageView);
                    }else { // Llamadores
                        Picasso.with(DetallePasosActivity.this)
                                .load(pasosDBDao.load(id_aux).getLlamador())
                                .resize(500, 400)
                                .into(imageView);
                    }
                    grande[0] = !grande[0];
                }else{
                    if (cat_elegida.equalsIgnoreCase("Pasos")){
                        Picasso.with(DetallePasosActivity.this)
                                .load(pasosDBDao.load(id_aux).getFotoPaso())
                                .resize(1000, 800)
                                .into(imageView);
                    }else { // Llamadores
                        Picasso.with(DetallePasosActivity.this)
                                .load(pasosDBDao.load(id_aux).getLlamador())
                                .resize(1000, 800)
                                .into(imageView);
                    }
                    grande[0] = !grande[0];
                }
                //imagePopup.initiatePopup(imageView.getDrawable());
            }
        });
    }

    private void guardarAcierto(Long ide) {
        UsuariosHermandadesDBDao usuariosHermandadesDBDao =
                DatabaseConnection.getUsuariosHermandadesDBDao(DetallePasosActivity.this);

        // Los valores actuales a insertar => Necesito el usuario
        UsuariosHermandadesDB usuariosHermandadesDB = new UsuariosHermandadesDB();
        UsuarioDBDao usuario = DatabaseConnection.getUsuarioDBDao(DetallePasosActivity.this);
        List<UsuarioDB> usuario_actual = usuario.loadAll();
        /*if(usuario_actual.size()==1) System.out.println("Eres el único usuario");
        else System.out.println("¡Hay más de uno! "+usuario_actual.size());*/

        usuariosHermandadesDB.setIdUsuario(usuario_actual.get(0).getId());
        usuariosHermandadesDB.setCategoria(((Application_vars) this.getApplication()).getCategoriaElegida());
        usuariosHermandadesDB.setIdHermandad(ide);

        usuariosHermandadesDBDao.insertOrReplace(usuariosHermandadesDB);
        //System.out.println(usuariosHermandadesDBDao.getEntidad(usuariosHermandadesDB));
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
                        next_escudo(findViewById(R.id.activity_detalle));
                    }
                })
                .show();
    }


    public void next_escudo(View view) {
        if(posicionLista==ListaDesordenada.size()-1){
            posicionLista=0;
        }else{
            posicionLista++;
        }

        id_aux = ListaDesordenada.get(posicionLista).getId();

        // Busco si está acertada (Tabla_Intermedia)
        while(checkAcertado(id_aux)){
            if(posicionLista==ListaDesordenada.size()-1){
                posicionLista=0;
            }else{
                posicionLista++;
            }
            id_aux = ListaDesordenada.get(posicionLista).getId();
        }
        //posicionLista++;
        respuesta.setText("");
        jugar(id_aux);
    }

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

    public void previous_escudo(View view) {
        if( posicionLista==0 ){
            posicionLista=ListaDesordenada.size()-1;
        }else{
            posicionLista--;
        }
        id_aux = ListaDesordenada.get(posicionLista).getId();

        while(checkAcertado(id_aux)){
            if( posicionLista==0 ){
                posicionLista=ListaDesordenada.size()-1;
            }else{
                posicionLista--;
            }
            id_aux = ListaDesordenada.get(posicionLista).getId();
        }

        respuesta.setText("");
        jugar(id_aux);
    }

}