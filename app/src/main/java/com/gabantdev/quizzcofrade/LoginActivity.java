package com.gabantdev.quizzcofrade;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.gabantdev.quizzcofrade.Clases.Usuario;
import com.gabantdev.quizzcofrade.Interfaces.IRetrofit;
import com.gabantdev.quizzcofrade.Pojos_API.Usuarios;
import com.gabantdev.quizzcofrade.Utils.Application_vars;
import com.gabantdev.quizzcofrade.localdb.DatabaseConnection;
import com.gabantdev.quizzcofrade.localdb.UsuarioDB;
import com.gabantdev.quizzcofrade.localdb.UsuarioDBDao;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class LoginActivity extends AppCompatActivity {

    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    String nombre, apellidos, email, idface, authToken;
    Usuario current_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);

        info = (TextView)findViewById(R.id.info);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        getUsuarios();

        // Le damos permisos específicos para almacenar su email. La app le avisará automáticamente.
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email")); //, "user_birthday", "user_friends"

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //requestUserProfile(loginResult);
                info.setText(
                        "User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                );
                idface = loginResult.getAccessToken().getUserId();
                authToken = loginResult.getAccessToken().getToken();

                String accessToken = loginResult.getAccessToken().getToken();
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // Get facebook data from login
                        Bundle bFacebookData = getFacebookData(object);
                        System.out.println("NOMBRE Y APELLIDOS: "+bFacebookData.get("first_name")
                                +bFacebookData.get("last_name"));
                        email = (String) bFacebookData.get("email");
                        nombre = (String) bFacebookData.get("first_name");
                        apellidos = (String) bFacebookData.get("last_name");




                        //TODO: Cambiarlo hacia arriba
                        String idSharedPreferences = "";
                        SharedPreferences settings = getSharedPreferences("PREFS_FACEBOOK", 0);
                        idSharedPreferences = settings.getString("FIRST_LOGIN", "N");

                        System.out.println("La prefs de face id es: "+idSharedPreferences);
                        System.out.println("El id que acaba de devolver Facebook es: "+idface);
                        //Primera vez
                        if (idSharedPreferences.equals("N") || idface.equals("")) {
                            System.out.println("*****************************\n****************************\nLOGIN POR PRIMERA VEZ");
                            boolean bandera = false;

                            //Busco que no exista en la BBDD
                            UsuarioDBDao usuarioDBDao = DatabaseConnection.getUsuarioDBDao(LoginActivity.this);
                            List<UsuarioDB> u = usuarioDBDao.loadAll();

                            //Busco al usuario => TODO: aquí nunca va a entrar
                            for (UsuarioDB us:u) {
                                if (us.getIdface().equals(idface)){
                                    bandera = true;
                                    current_user = new Usuario(us.getId(), nombre, apellidos, email, idface, authToken);
                                    ((Application_vars) getApplication()).setU(current_user);
                                    System.out.println("Logueo al usuario: "+current_user);
                                }
                            }

                            //Si no existe: Creo al usuario
                            if (!bandera) {
                                crearUsuario(nombre, apellidos, email, idface, authToken);
                            }

                            //Creo el ID de las SharedPreferences
                            settings = getSharedPreferences("PREFS_FACEBOOK", 0);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("FIRST_LOGIN", idface);
                            editor.commit();

                        }else{ // No es su primer login
                            Long id_aux = null;

                            //Busco su id en la BD local
                            UsuarioDBDao usuarioDBDao = DatabaseConnection.getUsuarioDBDao(LoginActivity.this);
                            List<UsuarioDB> u = usuarioDBDao.loadAll();

                            for (UsuarioDB us:u) {
                                if (us.getIdface().equals(idface)) {
                                    id_aux = us.getId();
                                }
                            }

                            // Subo el usuario a Application
                            current_user = new Usuario(id_aux, nombre, apellidos, email, idface, authToken);
                            ((Application_vars) getApplication()).setU(current_user);
                            System.out.println("No es tu primer login y vales: "+current_user);

                            //Si el id de Facebook es diferente al de las SharedPrefs = Otra persona
                            //if (!idSharedPreferences.equals(idface)){

                            //Machaco el ID de las SharedPreferences
                                settings = getSharedPreferences("PREFS_FACEBOOK", 0);
                                SharedPreferences.Editor editor = settings.edit();
                                editor.putString("FIRST_LOGIN", idface);
                                editor.commit();
                            //}
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText("Login attempt failed.\n" + e.getMessage() + "\n" +e.getCause());
            }
        });
    }
    // RETROFIT USUARIOS
    private void getUsuarios() {
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(IRetrofit.ENDPOINT1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRetrofit service1 = retrofit1.create(IRetrofit.class);

        Call<Usuarios> autocompleteList1 = service1.getUsuariosRetrofit();

        autocompleteList1.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Response<Usuarios> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    Usuarios result= response.body();
                    UsuarioDBDao hermandadDBDao = DatabaseConnection.getUsuarioDBDao(LoginActivity.this);

                    for (Usuario h:result.getData()) {
                        // " id, nick, email "
                        UsuarioDB usuarioDB = new UsuarioDB();
                        usuarioDB.setId(h.getId());
                        usuarioDB.setNombre(h.getNombre());
                        usuarioDB.setApellidos(h.getApellidos());
                        usuarioDB.setEmail(h.getEmail());
                        usuarioDB.setIdface(h.getIdface());
                        usuarioDB.setAuthToken(h.getAuthToken());

                        hermandadDBDao.insertOrReplace(usuarioDB);

                        if (h.getIdface().equals(idface)){
                            current_user = new Usuario(h.getId(), nombre, apellidos, email, idface, authToken);
                            System.out.println("CREO AL USUARIO"+current_user);
                            ((Application_vars) getApplication()).setU(current_user);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }
    private Bundle getFacebookData(JSONObject object) {

        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));

            return bundle;
        }
        catch(JSONException e) {
            System.out.println("Error parsing JSON");
        }
        return null; //¿?
    }

    // nombre, apellidos, email, idface, authToken
    public void crearUsuario(final String nombre, final String apellidos, final String email, final String idface, final String authToken){
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(IRetrofit.ENDPOINT1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofit1.create(IRetrofit.class).createUser(nombre, apellidos, email, idface, authToken).enqueue(new Callback<Usuario>() {

            @Override
            public void onResponse(Response<Usuario> response, Retrofit retrofit) {
                Toast.makeText(LoginActivity.this, "¡Bienvenido a QuizzCofrade!", Toast.LENGTH_LONG).show();
                // AHORA NECESITO SU ID PARA GUARDARLO EN LOCAL
                // Puedo llamar a getUsuarios() y cuando recorra el que es, lo inserto en Application
                getUsuarios();

                /*UsuarioDBDao usuarioDBDao = DatabaseConnection.getUsuarioDBDao(LoginActivity.this);
                List<UsuarioDB> u = usuarioDBDao.loadAll();

                //Busco al usuario
                for (UsuarioDB us:u) {
                    if (us.getIdface().equals(idface)){
                        // TODO: Estoy guardando en Application un usuario sin ID
                        current_user = new Usuario(us.getId(), nombre, apellidos, email, idface, authToken);
                        System.out.println("CREO AL USUARIO"+current_user);
                        ((Application_vars) getApplication()).setU(current_user);
                    }
                }*/
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(LoginActivity.this, "ERROR  al crear el usuario", Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());

            }
        });
    }
}
