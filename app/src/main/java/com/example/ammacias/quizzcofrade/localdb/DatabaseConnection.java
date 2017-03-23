package com.example.ammacias.quizzcofrade.localdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ammacias.quizzcofrade.MainActivity;

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
    public static HermandadDBDao getHermandadDBTDao(Context ctx) {
        DaoSession daoSession = DatabaseConnection.getConnection(ctx);
        return daoSession.getHermandadDBTDao();
    }
    public static PasosDBDao getPasosDBDao (Context ctx) {
        DaoSession daoSession = DatabaseConnection.getConnection(ctx);
        return daoSession.getPasosDBDao();
    }
    public static PasosDBDao getPasosDBLDao (Context ctx) {
        DaoSession daoSession = DatabaseConnection.getConnection(ctx);
        return daoSession.getPasosDBLDao();
    }

    public static UsuariosHermandadesDBDao getUsuariosHermandadesDBDao (Context ctx) {
        DaoSession daoSession = DatabaseConnection.getConnection(ctx);
        return daoSession.getUsuariosHermandadesDBDao();
    }

    public static MarchaDBDao getMarchasDBDao (Context ctx) {
        DaoSession daoSession = DatabaseConnection.getConnection(ctx);
        return daoSession.getMarchaDBDao();
    }

    public static RankingDBDao getRankingDBDao (Context ctx) {
        DaoSession daoSession = DatabaseConnection.getConnection(ctx);
        return daoSession.getRankingDBDao();
    }

    public static void closeConnection(){
        daoMaster.getDatabase().close();
    }


}
