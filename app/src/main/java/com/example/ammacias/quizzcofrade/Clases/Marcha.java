package com.example.ammacias.quizzcofrade.Clases;

/**
 * Created by macias on 22/02/2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Marcha {

    @SerializedName("marcha")
    @Expose
    private List<String> marcha = null;

    public List<String> getMarcha() {
        return marcha;
    }

    public void setMarcha(List<String> marcha) {
        this.marcha = marcha;
    }

    public Marcha() {
    }

    @Override
    public String toString() {
        return "Marcha{" +
                "marcha=" + marcha +
                '}';
    }
}
