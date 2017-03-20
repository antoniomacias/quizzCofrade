package com.example.ammacias.quizzcofrade.DetalleActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.ammacias.quizzcofrade.R;
import com.example.ammacias.quizzcofrade.Utils.Application_vars;
import com.example.ammacias.quizzcofrade.localdb.DatabaseConnection;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.PasosDB;
import com.example.ammacias.quizzcofrade.localdb.PasosDBDao;
import com.example.ammacias.quizzcofrade.localdb.UsuarioDB;
import com.example.ammacias.quizzcofrade.localdb.UsuarioDBDao;
import com.example.ammacias.quizzcofrade.localdb.UsuariosHermandadesDB;
import com.example.ammacias.quizzcofrade.localdb.UsuariosHermandadesDBDao;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by gabri_neno on 07/03/2017.
 */
public class DetalleLlamadoresActivity extends AppCompatActivity{

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

    UsuariosHermandadesDBDao tabla_intermedia=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pasos);

        imageView =(ImageView)findViewById(R.id.fotoDetalle);
        respuesta =(EditText)findViewById(R.id.respuesta_escudo);

        //Hermandad seleccionada
        paso = DatabaseConnection.getPasosDBDao(this).load(getIntent().getExtras().getLong("IDPaso"));
        id_aux = paso.getId();
        hermandades = DatabaseConnection.getHermandadDBDao(this).loadAll();
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
        Picasso.with(this)
                .load(pasosDBDao.load(id_aux).getLlamador())
                .resize(500, 400)
                .into(imageView);
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
    }

    private void guardarAcierto(Long ide) {
        UsuariosHermandadesDBDao usuariosHermandadesDBDao =
                DatabaseConnection.getUsuariosHermandadesDBDao(DetalleLlamadoresActivity.this);

        // Los valores actuales a insertar => Necesito el usuario
        UsuariosHermandadesDB usuariosHermandadesDB = new UsuariosHermandadesDB();
        UsuarioDBDao usuario = DatabaseConnection.getUsuarioDBDao(DetalleLlamadoresActivity.this);
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
