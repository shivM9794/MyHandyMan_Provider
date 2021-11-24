package com.secondwarranty.secondwarrantyprovider.ResponseModel.EarningClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("myearningsData")
    @Expose
    private List<MyearningsDatum> myearningsData = null;

    public Result(List<MyearningsDatum> myearningsData) {
        this.myearningsData = myearningsData;
    }

    public List<MyearningsDatum> getMyearningsData() {
        return myearningsData;
    }

    public void setMyearningsData(List<MyearningsDatum> myearningsData) {
        this.myearningsData = myearningsData;
    }
}
