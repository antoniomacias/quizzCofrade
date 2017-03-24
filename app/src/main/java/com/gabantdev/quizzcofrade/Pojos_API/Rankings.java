package com.gabantdev.quizzcofrade.Pojos_API;

import com.gabantdev.quizzcofrade.Clases.Ranking;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gabri_neno on 13/03/2017.
 */

public class Rankings {
    @SerializedName("data")
    @Expose
    private List<Ranking> data = null;

    public List<Ranking> getData() {
        return data;
    }

    public void setData(List<Ranking> data) {
        this.data = data;
    }

}
