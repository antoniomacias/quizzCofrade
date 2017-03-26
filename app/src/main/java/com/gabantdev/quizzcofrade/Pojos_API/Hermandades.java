package com.gabantdev.quizzcofrade.Pojos_API;

import com.gabantdev.quizzcofrade.Clases.Hermandad;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gramos on 23/02/2017.
 */

public class Hermandades {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Hermandad> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Hermandad> getData() {
        return data;
    }

    public void setData(List<Hermandad> data) {
        this.data = data;
    }
}
