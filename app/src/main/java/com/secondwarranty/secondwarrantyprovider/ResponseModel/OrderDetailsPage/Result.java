package com.secondwarranty.secondwarrantyprovider.ResponseModel.OrderDetailsPage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("orderDetailPage")
    @Expose
    private List<OrderDetailPage> orderDetailPage = null;

    public Result(List<OrderDetailPage> orderDetailPage) {
        this.orderDetailPage = orderDetailPage;
    }

    public List<OrderDetailPage> getOrderDetailPage() {
        return orderDetailPage;
    }

    public void setOrderDetailPage(List<OrderDetailPage> orderDetailPage) {
        this.orderDetailPage = orderDetailPage;
    }
}
