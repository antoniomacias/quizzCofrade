package com.gabantdev.quizzcofrade.Pojos_API;

import com.gabantdev.quizzcofrade.Clases.Paso;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gramos on 23/02/2017.
 */

public class Pasos {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Paso> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Paso> getData() {
        return data;
    }

    public void setData(List<Paso> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Pasos{" +
                "Paso=" + data +
                '}';
    }
}
