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
        usuarioDB.addStringProperty("nombre");
        usuarioDB.addStringProperty("apellidos");
        usuarioDB.addStringProperty("email");
        usuarioDB.addStringProperty("idface");
        usuarioDB.addStringProperty("authToken");

        // Definimos las entidades (tablas) de la base de datos
        Entity hermandadDB = schema.addEntity("HermandadDB");
        hermandadDB.addIdProperty().autoincrement().primaryKey();
        hermandadDB.addStringProperty("nombre").notNull();
        hermandadDB.addStringProperty("escudo").notNull();
        hermandadDB.addStringProperty("tunica").notNull();
        hermandadDB.addStringProperty("fotoTunica").notNull();
        hermandadDB.addStringProperty("dia").notNull();
        hermandadDB.addIntProperty("numNazarenos").notNull();
        hermandadDB.addStringProperty("anyoFundacion").notNull();

        // Definimos las entidades (tablas) de la base de datos
        Entity pasosDB = schema.addEntity("PasosDB");
        pasosDB.addIdProperty().autoincrement().primaryKey();
        pasosDB.addStringProperty("nombreHermandad").notNull();
        pasosDB.addStringProperty("nombreTitular").notNull();
        pasosDB.addStringProperty("foto").notNull();
        pasosDB.addStringProperty("colorCirio").notNull();
        pasosDB.addStringProperty("banda").notNull();
        pasosDB.addStringProperty("capataz").notNull();
        pasosDB.addStringProperty("numCostaleros").notNull();
        pasosDB.addStringProperty("llamador").notNull();

        // Definimos las entidades (tablas) de la base de datos
        Entity marchaDB = schema.addEntity("MarchaDB");
        marchaDB.addIdProperty().autoincrement().primaryKey();
        marchaDB.addStringProperty("nombre");
        marchaDB.addStringProperty("banda");
        marchaDB.addStringProperty("fecha");
        marchaDB.addStringProperty("ruta");

        // Definimos las entidades (tablas) de la base de datos
        Entity rankingDB = schema.addEntity("RankingDB");
        rankingDB.addIdProperty().autoincrement().primaryKey();
        rankingDB.addLongProperty("idUsuario");
        rankingDB.addStringProperty("nick");
        rankingDB.addIntProperty("aciertos");
        rankingDB.addStringProperty("fecha");

        // Definimos las entidades (tablas) de la base de datos
        Entity usuariosHermandades = schema.addEntity("UsuariosHermandadesDB");
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
