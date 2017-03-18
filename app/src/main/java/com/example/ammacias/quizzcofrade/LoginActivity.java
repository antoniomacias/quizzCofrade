package com.example.ammacias.quizzcofrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ammacias.quizzcofrade.Clases.Ranking;
import com.example.ammacias.quizzcofrade.Clases.Usuario;
import com.example.ammacias.quizzcofrade.Interfaces.IRetrofit;
import com.example.ammacias.quizzcofrade.localdb.DatabaseConnection;
import com.example.ammacias.quizzcofrade.localdb.UsuarioDB;
import com.example.ammacias.quizzcofrade.localdb.UsuarioDBDao;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class LoginActivity extends AppCompatActivity {

    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    String nombre, apellidos, email, idface, authToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);

        info = (TextView)findViewById(R.id.info);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        UsuarioDBDao rankingDBDao = DatabaseConnection.getUsuarioDBDao(LoginActivity.this);
        List<UsuarioDB> ran = rankingDBDao.loadAll();

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
                        crearUsuario(nombre, apellidos, email, idface, authToken);
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
    public void crearUsuario(String nombre, String apellidos, String email, String idface, String authToken){
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(IRetrofit.ENDPOINT1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofit1.create(IRetrofit.class).createUser(nombre, apellidos, email, idface, authToken).enqueue(new Callback<Usuario>() {

            @Override
            public void onResponse(Response<Usuario> response, Retrofit retrofit) {
                Toast.makeText(LoginActivity.this, "EXITO al crear el usuario", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(LoginActivity.this, "ERROR  al crear el usuario", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
