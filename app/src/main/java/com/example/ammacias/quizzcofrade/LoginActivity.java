package com.example.ammacias.quizzcofrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

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
        System.out.println("****************\n****************\n****************\n****************\n");
        System.out.println("****************\n****************\n****************\n****************\n");
        for (UsuarioDB u : ran){
            System.out.println(u);
        }
        System.out.println("****************\n****************\n****************\n****************\n");
        System.out.println("****************\n****************\n****************\n****************\n");


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                info.setText(
                        "User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                );
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

    public void crearUsuario(String nick, String email, String idface, String authToken){
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(IRetrofit.ENDPOINT1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofit1.create(IRetrofit.class).createUser(nick, email, idface, authToken).enqueue(new Callback<Usuario>() {

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
