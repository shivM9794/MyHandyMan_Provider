package com.secondwarranty.secondwarrantyprovider.ResponseModel.JobStatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("rejecttbooking")
    @Expose
    private Rejecttbooking rejecttbooking;

    public Result(Rejecttbooking rejecttbooking) {
        this.rejecttbooking = rejecttbooking;
    }

    public Rejecttbooking getRejecttbooking() {
        return rejecttbooking;
    }

    public void setRejecttbooking(Rejecttbooking rejecttbooking) {
        this.rejecttbooking = rejecttbooking;
    }
}
