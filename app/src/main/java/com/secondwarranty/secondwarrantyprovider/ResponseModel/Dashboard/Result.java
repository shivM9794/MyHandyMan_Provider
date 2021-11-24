package com.secondwarranty.secondwarrantyprovider.ResponseModel.Dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("upcomingbookings")
    @Expose
    private List<Upcomingbooking> upcomingbookings = null;
    @SerializedName("completedbookings")
    @Expose
    private List<Completedbooking> completedbookings = null;

    public Result(List<Upcomingbooking> upcomingbookings, List<Completedbooking> completedbookings) {
        this.upcomingbookings = upcomingbookings;
        this.completedbookings = completedbookings;
    }

    public List<Upcomingbooking> getUpcomingbookings() {
        return upcomingbookings;
    }

    public void setUpcomingbookings(List<Upcomingbooking> upcomingbookings) {
        this.upcomingbookings = upcomingbookings;
    }

    public List<Completedbooking> getCompletedbookings() {
        return completedbookings;
    }

    public void setCompletedbookings(List<Completedbooking> completedbookings) {
        this.completedbookings = completedbookings;
    }
}
