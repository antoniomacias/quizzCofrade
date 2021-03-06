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
 * DAO for table "PASOS_DBL".
*/
public class PasosDBLDao extends AbstractDao<PasosDB, Long> {

    public static final String TABLENAME = "PASOS_DBL";

    /**
     * Properties of entity PasosDBL.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property NombreHermandad = new Property(1, String.class, "nombreHermandad", false, "NOMBRE_HERMANDAD");
        public final static Property NombreTitular = new Property(2, String.class, "nombreTitular", false, "NOMBRE_TITULAR");
        public final static Property FotoPaso = new Property(3, String.class, "fotoPaso", false, "FOTO_PASO");
        public final static Property FotoTitular = new Property(4, String.class, "fotoTitular", false, "FOTO_TITULAR");
        public final static Property ColorCirio = new Property(5, String.class, "colorCirio", false, "COLOR_CIRIO");
        public final static Property Banda = new Property(6, String.class, "banda", false, "BANDA");
        public final static Property Capataz = new Property(7, String.class, "capataz", false, "CAPATAZ");
        public final static Property NumCostaleros = new Property(8, String.class, "numCostaleros", false, "NUM_COSTALEROS");
        public final static Property Llamador = new Property(9, String.class, "llamador", false, "LLAMADOR");
    };


    public PasosDBLDao(DaoConfig config) {
        super(config);
    }
    
    public PasosDBLDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PASOS_DBL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NOMBRE_HERMANDAD\" TEXT NOT NULL ," + // 1: nombreHermandad
                "\"NOMBRE_TITULAR\" TEXT NOT NULL ," + // 2: nombreTitular
                "\"FOTO_PASO\" TEXT NOT NULL ," + // 3: fotoPaso
                "\"FOTO_TITULAR\" TEXT," + // 4: fotoTitular
                "\"COLOR_CIRIO\" TEXT NOT NULL ," + // 5: colorCirio
                "\"BANDA\" TEXT NOT NULL ," + // 6: banda
                "\"CAPATAZ\" TEXT NOT NULL ," + // 7: capataz
                "\"NUM_COSTALEROS\" TEXT NOT NULL ," + // 8: numCostaleros
                "\"LLAMADOR\" TEXT NOT NULL );"); // 9: llamador
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PASOS_DBL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PasosDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNombreHermandad());
        stmt.bindString(3, entity.getNombreTitular());
        stmt.bindString(4, entity.getFotoPaso());
 
        String fotoTitular = entity.getFotoTitular();
        if (fotoTitular != null) {
            stmt.bindString(5, fotoTitular);
        }
        stmt.bindString(6, entity.getColorCirio());
        stmt.bindString(7, entity.getBanda());
        stmt.bindString(8, entity.getCapataz());
        stmt.bindString(9, entity.getNumCostaleros());
        stmt.bindString(10, entity.getLlamador());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PasosDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNombreHermandad());
        stmt.bindString(3, entity.getNombreTitular());
        stmt.bindString(4, entity.getFotoPaso());
 
        String fotoTitular = entity.getFotoTitular();
        if (fotoTitular != null) {
            stmt.bindString(5, fotoTitular);
        }
        stmt.bindString(6, entity.getColorCirio());
        stmt.bindString(7, entity.getBanda());
        stmt.bindString(8, entity.getCapataz());
        stmt.bindString(9, entity.getNumCostaleros());
        stmt.bindString(10, entity.getLlamador());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PasosDB readEntity(Cursor cursor, int offset) {
        PasosDB entity = new PasosDB( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // nombreHermandad
            cursor.getString(offset + 2), // nombreTitular
            cursor.getString(offset + 3), // fotoPaso
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // fotoTitular
            cursor.getString(offset + 5), // colorCirio
            cursor.getString(offset + 6), // banda
            cursor.getString(offset + 7), // capataz
            cursor.getString(offset + 8), // numCostaleros
            cursor.getString(offset + 9) // llamador
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PasosDB entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNombreHermandad(cursor.getString(offset + 1));
        entity.setNombreTitular(cursor.getString(offset + 2));
        entity.setFotoPaso(cursor.getString(offset + 3));
        entity.setFotoTitular(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setColorCirio(cursor.getString(offset + 5));
        entity.setBanda(cursor.getString(offset + 6));
        entity.setCapataz(cursor.getString(offset + 7));
        entity.setNumCostaleros(cursor.getString(offset + 8));
        entity.setLlamador(cursor.getString(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PasosDB entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PasosDB entity) {
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
