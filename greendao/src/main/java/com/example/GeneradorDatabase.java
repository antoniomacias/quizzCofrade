package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class GeneradorDatabase {
    public static void main(String args[]) {
        // Definimos el esquema de la base de datos
        // PARAM 1: códigeo de versión: 1, 2, 3, 4
        // PARAM 2: el nombre del paquete del módule app de Android
        // donde queremos volcar los POJOS y demás ficheros de la base de datos
        Schema schema = new Schema(1, "com.example.ammacias.quizzcofrade.localdb");

        // Definimos las entidades (tablas) de la base de datos
        Entity usuarioDB = schema.addEntity("UsuarioDB");
        usuarioDB.addIdProperty().autoincrement().primaryKey();
        usuarioDB.addStringProperty("nick");

        // Definimos las entidades (tablas) de la base de datos
        Entity hermandadDB = schema.addEntity("HermandadDB");
        hermandadDB.addIdProperty().autoincrement().primaryKey();
        hermandadDB.addStringProperty("nombre").notNull();
        hermandadDB.addStringProperty("escudo").notNull();
        hermandadDB.addStringProperty("tunica").notNull();
        hermandadDB.addStringProperty("dia").notNull();
        hermandadDB.addIntProperty("numNazarenos").notNull();

        // Definimos las entidades (tablas) de la base de datos
        Entity pasosDB = schema.addEntity("PasosDB");
        pasosDB.addIdProperty().autoincrement().primaryKey();
        pasosDB.addStringProperty("nombreTitular").notNull();
        pasosDB.addStringProperty("foto").notNull();
        pasosDB.addStringProperty("banda").notNull();
        pasosDB.addLongProperty("idHermandad").notNull();

        // Definimos las entidades (tablas) de la base de datos
        Entity usuariosHermandades = schema.addEntity("UsuariosHermandades");
        usuariosHermandades.addIdProperty().autoincrement().primaryKey();
        usuariosHermandades.addLongProperty("idUsuario").notNull();
        usuariosHermandades.addLongProperty("idHermandad").notNull();
        usuariosHermandades.addStringProperty("categoria").notNull();

        try {
            new DaoGenerator().generateAll(schema, "./app/src/main/java/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
