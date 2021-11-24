package com.secondwarranty.secondwarrantyprovider.Retrofit;

import com.secondwarranty.secondwarrantyprovider.ResponseModel.AcceptBooking.AcceptBookingResponse;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.Dashboard.DashboardResponseClass;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.EarningClass.EarningResponse;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.ForgotPassword.ForgotPasswordResponse;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.HappyCodeClass.HappyCodeResponse;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.HelpCentre.HelpCentreResponse;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.JobStatus.JobStatusResponse;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.LoginModel.LoginResponseClass;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.LogoutModel.LogoutResponseClass;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.OrderDetailsPage.OrderDetailsResponse;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.RejectBooking.RejectBookingResponse;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.ResetPassword.ResetPasswordResponse;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.SideNavHeader.SideNavHeaderResponse;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.SignUpResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("users/putSignupForm")
    Call<SignUpResponse> signUp(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("users/setLogin")
    Call<LoginResponseClass> loginResponse(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("users/setLogout")
    Call<LogoutResponseClass> logoutResponse(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("users/setForgotPassword")
    Call<ForgotPasswordResponse> forgotPassword(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("users/setResetPassword")
    Call<ResetPasswordResponse> reset_Password(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("customer/getcustomerdetails")
    Call<SideNavHeaderResponse> sideNav(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("provider/getProviderHomePageData")
    Call<DashboardResponseClass> dashboard_details(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("provider/rejecttbooking")
    Call<RejectBookingResponse> rejectBooking(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("provider/getmyEarningData")
    Call<EarningResponse> myEarnings(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("provider/acceptbooking")
    Call<AcceptBookingResponse> accept_Booking(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("provider/updatejobcompletestatus")
    Call<JobStatusResponse> job_Status(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("provider/updatejobstatusviaHappycode")
    Call<HappyCodeResponse> happy_code(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("provider/orderDetailPage")
    Call<OrderDetailsResponse> order_Details(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("customer/contacthelpcenter")
    Call<HelpCentreResponse> helpCentre_Response(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);





}
