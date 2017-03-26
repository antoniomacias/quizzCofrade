package com.gabantdev.quizzcofrade.Pojos_API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gramos on 02/03/2017.
 */

public class UsuariosHermandadesAPI {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<com.gabantdev.quizzcofrade.Clases.UsuariosHermandades> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<com.gabantdev.quizzcofrade.Clases.UsuariosHermandades> getData() {
        return data;
    }

    public void setData(List<com.gabantdev.quizzcofrade.Clases.UsuariosHermandades> data) {
        this.data = data;
    }
}
