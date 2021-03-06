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
 * DAO for table "USUARIO_DB".
*/
public class UsuarioDBDao extends AbstractDao<UsuarioDB, Long> {

    public static final String TABLENAME = "USUARIO_DB";

    /**
     * Properties of entity UsuarioDB.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Nombre = new Property(1, String.class, "nombre", false, "NOMBRE");
        public final static Property Apellidos = new Property(2, String.class, "apellidos", false, "APELLIDOS");
        public final static Property Email = new Property(3, String.class, "email", false, "EMAIL");
        public final static Property Idface = new Property(4, String.class, "idface", false, "IDFACE");
        public final static Property AuthToken = new Property(5, String.class, "authToken", false, "AUTH_TOKEN");
    };


    public UsuarioDBDao(DaoConfig config) {
        super(config);
    }
    
    public UsuarioDBDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USUARIO_DB\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NOMBRE\" TEXT," + // 1: nombre
                "\"APELLIDOS\" TEXT," + // 2: apellidos
                "\"EMAIL\" TEXT," + // 3: email
                "\"IDFACE\" TEXT," + // 4: idface
                "\"AUTH_TOKEN\" TEXT);"); // 5: authToken
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USUARIO_DB\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UsuarioDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String nombre = entity.getNombre();
        if (nombre != null) {
            stmt.bindString(2, nombre);
        }
 
        String apellidos = entity.getApellidos();
        if (apellidos != null) {
            stmt.bindString(3, apellidos);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(4, email);
        }
 
        String idface = entity.getIdface();
        if (idface != null) {
            stmt.bindString(5, idface);
        }
 
        String authToken = entity.getAuthToken();
        if (authToken != null) {
            stmt.bindString(6, authToken);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UsuarioDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String nombre = entity.getNombre();
        if (nombre != null) {
            stmt.bindString(2, nombre);
        }
 
        String apellidos = entity.getApellidos();
        if (apellidos != null) {
            stmt.bindString(3, apellidos);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(4, email);
        }
 
        String idface = entity.getIdface();
        if (idface != null) {
            stmt.bindString(5, idface);
        }
 
        String authToken = entity.getAuthToken();
        if (authToken != null) {
            stmt.bindString(6, authToken);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UsuarioDB readEntity(Cursor cursor, int offset) {
        UsuarioDB entity = new UsuarioDB( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // nombre
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // apellidos
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // email
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // idface
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // authToken
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UsuarioDB entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNombre(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setApellidos(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setEmail(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setIdface(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setAuthToken(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UsuarioDB entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UsuarioDB entity) {
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
