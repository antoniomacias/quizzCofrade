package com.gabantdev.quizzcofrade.localdb;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "HERMANDAD_DB".
*/
public class HermandadDBDao extends AbstractDao<HermandadDB, Long> {

    public static final String TABLENAME = "HERMANDAD_DB";

    /**
     * Properties of entity HermandadDB.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Nombre = new Property(1, String.class, "nombre", false, "NOMBRE");
        public final static Property Escudo = new Property(2, String.class, "escudo", false, "ESCUDO");
        public final static Property Tunica = new Property(3, String.class, "tunica", false, "TUNICA");
        public final static Property FotoTunica = new Property(4, String.class, "fotoTunica", false, "FOTO_TUNICA");
        public final static Property Dia = new Property(5, String.class, "dia", false, "DIA");
        public final static Property NumNazarenos = new Property(6, int.class, "numNazarenos", false, "NUM_NAZARENOS");
        public final static Property AnyoFundacion = new Property(7, String.class, "anyoFundacion", false, "ANYO_FUNDACION");
    };


    public HermandadDBDao(DaoConfig config) {
        super(config);
    }
    
    public HermandadDBDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HERMANDAD_DB\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NOMBRE\" TEXT NOT NULL ," + // 1: nombre
                "\"ESCUDO\" TEXT NOT NULL ," + // 2: escudo
                "\"TUNICA\" TEXT NOT NULL ," + // 3: tunica
                "\"FOTO_TUNICA\" TEXT NOT NULL ," + // 4: fotoTunica
                "\"DIA\" TEXT NOT NULL ," + // 5: dia
                "\"NUM_NAZARENOS\" INTEGER NOT NULL ," + // 6: numNazarenos
                "\"ANYO_FUNDACION\" TEXT NOT NULL );"); // 7: anyoFundacion
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HERMANDAD_DB\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, HermandadDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNombre());
        stmt.bindString(3, entity.getEscudo());
        stmt.bindString(4, entity.getTunica());
        stmt.bindString(5, entity.getFotoTunica());
        stmt.bindString(6, entity.getDia());
        stmt.bindLong(7, entity.getNumNazarenos());
        stmt.bindString(8, entity.getAnyoFundacion());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, HermandadDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNombre());
        stmt.bindString(3, entity.getEscudo());
        stmt.bindString(4, entity.getTunica());
        stmt.bindString(5, entity.getFotoTunica());
        stmt.bindString(6, entity.getDia());
        stmt.bindLong(7, entity.getNumNazarenos());
        stmt.bindString(8, entity.getAnyoFundacion());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public HermandadDB readEntity(Cursor cursor, int offset) {
        HermandadDB entity = new HermandadDB( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // nombre
            cursor.getString(offset + 2), // escudo
            cursor.getString(offset + 3), // tunica
            cursor.getString(offset + 4), // fotoTunica
            cursor.getString(offset + 5), // dia
            cursor.getInt(offset + 6), // numNazarenos
            cursor.getString(offset + 7) // anyoFundacion
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, HermandadDB entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNombre(cursor.getString(offset + 1));
        entity.setEscudo(cursor.getString(offset + 2));
        entity.setTunica(cursor.getString(offset + 3));
        entity.setFotoTunica(cursor.getString(offset + 4));
        entity.setDia(cursor.getString(offset + 5));
        entity.setNumNazarenos(cursor.getInt(offset + 6));
        entity.setAnyoFundacion(cursor.getString(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(HermandadDB entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(HermandadDB entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }


    
}