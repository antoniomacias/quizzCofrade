package com.gabantdev.quizzcofrade.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import com.gabantdev.quizzcofrade.R;


/**
 * Created by ammacias on 16/02/2017.
 */

public class CheckInternet {
    private AlertDialog.Builder builder;


    // Este método comprueba si hay Internet
    private void checkInternet(final Context ctx) {
        ConnectivityManager conMgr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo i = conMgr.getActiveNetworkInfo();
        if (i == null || !i.isConnected() || !i.isAvailable()) {
            builder = new AlertDialog.Builder(ctx);

            builder.setTitle(R.string.alert_title_internet);
            builder.setMessage(R.string.alert_need_internet_to_download);
            builder.setPositiveButton(R.string.dialog_settings_internet,
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Lanzo un intent implícito que hace que se abra
                            // la pantalla de Configuración del móvil
                            // para que el usuario se conecte a Internet
                            // Internet
                            Intent intent = new Intent(
                                    Settings.ACTION_SETTINGS);
                            ctx.startActivity(intent);
                        }
                    });

            builder.setNegativeButton(R.string.alert_dialog_cancel,
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();


                        }
                    });

            builder.create();
            builder.show();

        } else {

            // Si hay conexión...
        }

    }

}
