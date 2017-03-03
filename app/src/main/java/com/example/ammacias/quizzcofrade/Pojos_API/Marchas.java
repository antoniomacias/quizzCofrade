package com.example.ammacias.quizzcofrade.Pojos_API;

import com.example.ammacias.quizzcofrade.Clases.Marcha;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gabri_neno on 03/03/2017.
 */

public class Marchas {
    @SerializedName("data")
    @Expose
    private List<Marcha> data = null;

    public List<Marcha> getData() {
        return data;
    }

    public void setData(List<Marcha> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Marchas{" +
                "data=" + data +
                '}';
    }
}
