package com.example.ammacias.quizzcofrade.localdb;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "PASOS_DBL".
 */
@Entity
public class PasosDBL {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String nombreHermandad;

    @NotNull
    private String nombreTitular;

    @NotNull
    private String fotoPaso;
    private String fotoTitular;

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
    public PasosDBL() {
    }

    public PasosDBL(Long id) {
        this.id = id;
    }

    @Generated
    public PasosDBL(Long id, String nombreHermandad, String nombreTitular, String fotoPaso, String fotoTitular, String colorCirio, String banda, String capataz, String numCostaleros, String llamador) {
        this.id = id;
        this.nombreHermandad = nombreHermandad;
        this.nombreTitular = nombreTitular;
        this.fotoPaso = fotoPaso;
        this.fotoTitular = fotoTitular;
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
    public String getFotoPaso() {
        return fotoPaso;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setFotoPaso(@NotNull String fotoPaso) {
        this.fotoPaso = fotoPaso;
    }

    public String getFotoTitular() {
        return fotoTitular;
    }

    public void setFotoTitular(String fotoTitular) {
        this.fotoTitular = fotoTitular;
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

}