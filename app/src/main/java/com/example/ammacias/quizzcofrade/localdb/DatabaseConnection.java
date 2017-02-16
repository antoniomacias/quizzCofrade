package com.example.ammacias.quizzcofrade.localdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ammacias on 16/02/2017.
 */

public class DatabaseConnection {
    private static SQLiteDatabase db;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    public static DaoSession getConnection(Context ctx) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(ctx,"marca-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

        return daoSession;
    }

    public static UsuarioDBDao getUsuarioDBDao (Context ctx) {
        DaoSession daoSession = DatabaseConnection.getConnection(ctx);
        return daoSession.getUsuarioDBDao();
    }

    public static HermandadDBDao getHermandadDBDao (Context ctx) {
        DaoSession daoSession = DatabaseConnection.getConnection(ctx);
        return daoSession.getHermandadDBDao();
    }

    public static PasosDBDao getPasosDBDao (Context ctx) {
        DaoSession daoSession = DatabaseConnection.getConnection(ctx);
        return daoSession.getPasosDBDao();
    }

    public static UsuariosHermandadesDao getUsuariosHermandadesDao (Context ctx) {
        DaoSession daoSession = DatabaseConnection.getConnection(ctx);
        return daoSession.getUsuariosHermandadesDao();
    }
}
