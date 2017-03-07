package com.example.ammacias.quizzcofrade.localdb;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "PASOS_DB".
 */
@Entity
public class PasosDB {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String nombreHermandad;

    @NotNull
    private String nombreTitular;

    @NotNull
    private String foto;

    @NotNull
    private String colorCirio;

    @NotNull
    private String banda;

    @NotNull
    private String capataz;

    @NotNull
    private String numCostaleros;

    @NotNull
    private String llamador;

    @Generated
    public PasosDB() {
    }

    public PasosDB(Long id) {
        this.id = id;
    }

    @Generated
    public PasosDB(Long id, String nombreHermandad, String nombreTitular, String foto, String colorCirio, String banda, String capataz, String numCostaleros, String llamador) {
        this.id = id;
        this.nombreHermandad = nombreHermandad;
        this.nombreTitular = nombreTitular;
        this.foto = foto;
        this.colorCirio = colorCirio;
        this.banda = banda;
        this.capataz = capataz;
        this.numCostaleros = numCostaleros;
        this.llamador = llamador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getNombreHermandad() {
        return nombreHermandad;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setNombreHermandad(@NotNull String nombreHermandad) {
        this.nombreHermandad = nombreHermandad;
    }

    @NotNull
    public String getNombreTitular() {
        return nombreTitular;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setNombreTitular(@NotNull String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    @NotNull
    public String getFoto() {
        return foto;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setFoto(@NotNull String foto) {
        this.foto = foto;
    }

    @NotNull
    public String getColorCirio() {
        return colorCirio;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setColorCirio(@NotNull String colorCirio) {
        this.colorCirio = colorCirio;
    }

    @NotNull
    public String getBanda() {
        return banda;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setBanda(@NotNull String banda) {
        this.banda = banda;
    }

    @NotNull
    public String getCapataz() {
        return capataz;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCapataz(@NotNull String capataz) {
        this.capataz = capataz;
    }

    @NotNull
    public String getNumCostaleros() {
        return numCostaleros;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setNumCostaleros(@NotNull String numCostaleros) {
        this.numCostaleros = numCostaleros;
    }

    @NotNull
    public String getLlamador() {
        return llamador;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setLlamador(@NotNull String llamador) {
        this.llamador = llamador;
    }

    @Override
    public String toString() {
        return "PasosDB{" +
                "id=" + id +
                ", nombreHermandad='" + nombreHermandad + '\'' +
                ", nombreTitular='" + nombreTitular + '\'' +
                ", foto='" + foto + '\'' +
                ", colorCirio='" + colorCirio + '\'' +
                ", banda='" + banda + '\'' +
                ", capataz='" + capataz + '\'' +
                ", numCostaleros='" + numCostaleros + '\'' +
                ", llamador='" + llamador + '\'' +
                '}';
    }
}
