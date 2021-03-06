package com.gabantdev.quizzcofrade.DetalleActivity;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ceylonlabs.imageviewpopup.ImagePopup;

import com.gabantdev.quizzcofrade.R;
import com.gabantdev.quizzcofrade.Utils.Application_vars;
import com.gabantdev.quizzcofrade.localdb.DatabaseConnection;
import com.gabantdev.quizzcofrade.localdb.HermandadDB;
import com.gabantdev.quizzcofrade.localdb.PasosDB;
import com.gabantdev.quizzcofrade.localdb.PasosDBDao;
import com.gabantdev.quizzcofrade.localdb.UsuarioDB;
import com.gabantdev.quizzcofrade.localdb.UsuarioDBDao;
import com.gabantdev.quizzcofrade.localdb.UsuariosHermandadesDB;
import com.gabantdev.quizzcofrade.localdb.UsuariosHermandadesDBDao;
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
    String cat_elegida = "", s ="";
    TextView titulo;
    TextView pregunta_detalle_escudos;
    FloatingActionButton fab;

    UsuariosHermandadesDBDao tabla_intermedia=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_escudos);

        imageView =(ImageView)findViewById(R.id.fotoDetalle);
        respuesta =(EditText)findViewById(R.id.respuesta_escudo);
        titulo = (TextView)findViewById(R.id.titulo);

        pregunta_detalle_escudos = (TextView)findViewById(R.id.pregunta_detalle_escudos);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        // EMPIEZA LA FIESTA
        cat_elegida = ((Application_vars) this.getApplication()).getCategoriaElegida();

        if (cat_elegida.equalsIgnoreCase("Pasos")){
            pregunta_detalle_escudos.setText("Al cielo con... ¿cuál?");
            titulo.setText("Pasos");//Hermandad seleccionada
            paso = DatabaseConnection.getPasosDBDao(this).load(getIntent().getExtras().getLong("IDPaso"));
            ListaDesordenada = DatabaseConnection.getPasosDBDao(this).loadAll();

        }else { // Llamadores
            pregunta_detalle_escudos.setText("¿A esta es, o no es?");
            titulo.setText("Llamadores");//Hermandad seleccionada
            paso = DatabaseConnection.getPasosDBLDao(this).load(getIntent().getExtras().getLong("IDPaso"));
            ListaDesordenada = DatabaseConnection.getPasosDBLDao(this).loadAll();

        }

        id_aux = paso.getId();
        // Sólo queremos las hermandades para igualar el nombre
        hermandades =DatabaseConnection.getHermandadDBDao(this).loadAll();
        for(HermandadDB h:hermandades){
            if(h.getNombre().equals(paso.getNombreHermandad()))hermandad = h;
        }
        System.out.println("PASO ESCOGIDO : "+paso);
        System.out.println("De la hermandad "+hermandad);

        for(int ii=0;ii<ListaDesordenada.size();ii++){
            if(ListaDesordenada.get(ii).getId().equals(id_aux) || ListaDesordenada.get(ii).getId() == id_aux){
                posicionLista = ii;
            }
        }
        //posicionLista = ListaDesordenada.indexOf(herma);

        String s=null;
        if(checkAcertado(id_aux)){
            s = hermandad.getNombre();
            respuesta.setText(s);
        }
        jugar(id_aux);
    } // Fin onCreate

    public void jugar(final Long id_aux){
        if (cat_elegida.equalsIgnoreCase("Pasos")){
            pasosDBDao = DatabaseConnection.getPasosDBDao(this);
        }else{ // Llamadores
            pasosDBDao = DatabaseConnection.getPasosDBLDao(this);

        }
        s = "Pertenece a una cofradía que sale en ";

        for(HermandadDB h:hermandades){
            if(h.getNombre().equals(pasosDBDao.load(id_aux).getNombreHermandad()))hermandad = h;
        }

        if (cat_elegida.equalsIgnoreCase("Pasos")){
            fab.setVisibility(View.GONE);
            Picasso.with(this)
                    .load(pasosDBDao.load(id_aux).getFotoPaso())
                    .resize(500, 400)
                    .into(imageView);
        }else { // Llamadores
            fab.setVisibility(View.VISIBLE);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, s+hermandad.getDia(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

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