package com.secondwarranty.secondwarrantyprovider.ResponseModel.Dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Upcomingbooking {

    @SerializedName("order_id")
    @Expose
    private Long orderId;
    @SerializedName("sequence_order_id")
    @Expose
    private String sequenceOrderId;
    @SerializedName("users_id")
    @Expose
    private Long usersId;
    @SerializedName("service_type")
    @Expose
    private String serviceType;
    @SerializedName("min_charge")
    @Expose
    private Integer minCharge;
    @SerializedName("sub_category_id")
    @Expose
    private Long subCategoryId;
    @SerializedName("sub_category_name")
    @Expose
    private String subCategoryName;
    @SerializedName("category_id")
    @Expose
    private Long categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("order_step")
    @Expose
    private Integer orderStep;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("order_assigned")
    @Expose
    private String orderAssigned;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("order_status")
    @Expose
    private String orderStatus;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("creation_date")
    @Expose
    private Integer creationDate;
    @SerializedName("schedule_date")
    @Expose
    private Integer scheduleDate;
    @SerializedName("schedule_time")
    @Expose
    private Boolean scheduleTime;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("current_location")
    @Expose
    private String currentLocation;
    @SerializedName("landmark")
    @Expose
    private String landmark;
    @SerializedName("provider_id")
    @Expose
    private Long providerId;
    @SerializedName("assigned_date")
    @Expose
    private Integer assignedDate;
    @SerializedName("users_name")
    @Expose
    private String usersName;
    @SerializedName("users_email")
    @Expose
    private String usersEmail;
    @SerializedName("users_mobile")
    @Expose
    private Long usersMobile;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;

    public Upcomingbooking(Long orderId, String sequenceOrderId, Long usersId, String serviceType, Integer minCharge, Long subCategoryId, String subCategoryName, Long categoryId, String categoryName, Integer orderStep, String paymentStatus, String orderAssigned, String paymentMode, String orderStatus, String status, Integer creationDate, Integer scheduleDate, Boolean scheduleTime, String address, String currentLocation, String landmark, Long providerId, Integer assignedDate, String usersName, String usersEmail, Long usersMobile, String date, String time) {
        this.orderId = orderId;
        this.sequenceOrderId = sequenceOrderId;
        this.usersId = usersId;
        this.serviceType = serviceType;
        this.minCharge = minCharge;
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.orderStep = orderStep;
        this.paymentStatus = paymentStatus;
        this.orderAssigned = orderAssigned;
        this.paymentMode = paymentMode;
        this.orderStatus = orderStatus;
        this.status = status;
        this.creationDate = creationDate;
        this.scheduleDate = scheduleDate;
        this.scheduleTime = scheduleTime;
        this.address = address;
        this.currentLocation = currentLocation;
        this.landmark = landmark;
        this.providerId = providerId;
        this.assignedDate = assignedDate;
        this.usersName = usersName;
        this.usersEmail = usersEmail;
        this.usersMobile = usersMobile;
        this.date = date;
        this.time = time;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getSequenceOrderId() {
        return sequenceOrderId;
    }

    public void setSequenceOrderId(String sequenceOrderId) {
        this.sequenceOrderId = sequenceOrderId;
    }

    public Long getUsersId() {
        return usersId;
    }

    public void setUsersId(Long usersId) {
        this.usersId = usersId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getMinCharge() {
        return minCharge;
    }

    public void setMinCharge(Integer minCharge) {
        this.minCharge = minCharge;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getOrderStep() {
        return orderStep;
    }

    public void setOrderStep(Integer orderStep) {
        this.orderStep = orderStep;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getOrderAssigned() {
        return orderAssigned;
    }

    public void setOrderAssigned(String orderAssigned) {
        this.orderAssigned = orderAssigned;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Integer scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Boolean getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Boolean scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public Integer getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Integer assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    public String getUsersEmail() {
        return usersEmail;
    }

    public void setUsersEmail(String usersEmail) {
        this.usersEmail = usersEmail;
    }

    public Long getUsersMobile() {
        return usersMobile;
    }

    public void setUsersMobile(Long usersMobile) {
        this.usersMobile = usersMobile;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
