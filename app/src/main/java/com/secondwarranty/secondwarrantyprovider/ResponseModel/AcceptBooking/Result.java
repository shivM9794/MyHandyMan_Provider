package com.secondwarranty.secondwarrantyprovider.ResponseModel.AcceptBooking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("acceptBooking")
    @Expose
    private AcceptBooking acceptBooking;

    public Result(AcceptBooking acceptBooking) {
        this.acceptBooking = acceptBooking;
    }

    public AcceptBooking getAcceptBooking() {
        return acceptBooking;
    }

    public void setAcceptBooking(AcceptBooking acceptBooking) {
        this.acceptBooking = acceptBooking;
    }
}
