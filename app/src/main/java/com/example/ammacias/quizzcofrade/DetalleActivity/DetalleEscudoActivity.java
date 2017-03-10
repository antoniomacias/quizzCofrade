package com.example.ammacias.quizzcofrade.DetalleActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.example.ammacias.quizzcofrade.R;
import com.example.ammacias.quizzcofrade.Utils.Application_vars;
import com.example.ammacias.quizzcofrade.localdb.DatabaseConnection;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.HermandadDBDao;
import com.example.ammacias.quizzcofrade.localdb.UsuarioDB;
import com.example.ammacias.quizzcofrade.localdb.UsuarioDBDao;
import com.example.ammacias.quizzcofrade.localdb.UsuariosHermandadesDB;
import com.example.ammacias.quizzcofrade.localdb.UsuariosHermandadesDBDao;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import rm.com.longpresspopup.LongPressPopup;
import rm.com.longpresspopup.LongPressPopupBuilder;

public class DetalleEscudoActivity extends AppCompatActivity {

    ImageView imageView;
    EditText respuesta;
    boolean isImageFitToScreen;

    HermandadDBDao hermandadDBDao;
    HermandadDB herma;
    List<HermandadDB> ListaDesordenada;
    Long id_aux;
    int posicionLista;
    String cat_elegida = "";

    UsuariosHermandadesDBDao tabla_intermedia=null;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_escudos);

        imageView =(ImageView)findViewById(R.id.fotoDetalle);
        respuesta =(EditText)findViewById(R.id.respuesta_escudo);

        //Hermandad seleccionada
        herma = DatabaseConnection.getHermandadDBDao(this).load(getIntent().getExtras().getLong("IDHermandad"));
        id_aux = herma.getId();
        System.out.println("hermandad repe : "+herma.getNombre());

        ListaDesordenada = DatabaseConnection.getHermandadDBDao(this).loadAll();
        for(int ii=0;ii<ListaDesordenada.size();ii++){
            if(ListaDesordenada.get(ii).getId().equals(id_aux) || ListaDesordenada.get(ii).getId() == id_aux){
                posicionLista = ii;
            }
        }

        //posicionLista = ListaDesordenada.indexOf(herma);

        cat_elegida = ((Application_vars) this.getApplication()).getCategoriaElegida();

        String s=null;
        if(checkAcertado(id_aux)){
            s = herma.getNombre();
            respuesta.setText(s);
        }
        jugar(id_aux);

        //imgview.setScaleType(ImageView.ScaleType.FIT_XY);

        //ImagePopup imagePopup = new ImagePopup(this);
       /* final ImagePopup imagePopup = new ImagePopup(this);
        imagePopup.setBackgroundColor(Color.BLACK);

        imagePopup.setHideCloseIcon(false);
        imagePopup.setImageOnClickClose(true);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imagePopup.initiatePopup(imageView.getDrawable());
            }
        });*/

        /*imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isImageFitToScreen) {
                    isImageFitToScreen=false;
                    imageView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    imageView.setAdjustViewBounds(true);
                }else{
                    isImageFitToScreen=true;
                    imageView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });*/
    } // Fin onCreate

    public void jugar(final Long id_aux){
        hermandadDBDao = DatabaseConnection.getHermandadDBDao(this);
        if (cat_elegida.contains("Escudos")) {
            Picasso.with(this)
                    .load(hermandadDBDao.load(id_aux).getEscudo())
                    .resize(500, 400)
                    .into(imageView);
        }else{ // Ha escogido Túnicas
            Picasso.with(this)
                    .load(hermandadDBDao.load(id_aux).getFotoTunica())
                    .resize(500, 400)
                    .into(imageView);
        }

        respuesta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (respuesta.getText().toString().equalsIgnoreCase(hermandadDBDao.load(id_aux).getNombre())){
                    muestradialogo();
                    //System.out.println("Acertaste y guardo");
                }
            }
        });
    }

    private void guardarAcierto(Long ide) {
        UsuariosHermandadesDBDao usuariosHermandadesDBDao =
                DatabaseConnection.getUsuariosHermandadesDBDao(DetalleEscudoActivity.this);

        // Los valores actuales a insertar => Necesito el usuario
        UsuariosHermandadesDB usuariosHermandadesDB = new UsuariosHermandadesDB();
        UsuarioDBDao usuario = DatabaseConnection.getUsuarioDBDao(DetalleEscudoActivity.this);
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
