package com.secondwarranty.secondwarrantyprovider.Repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
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
import com.secondwarranty.secondwarrantyprovider.ResponseModel.Result;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.SideNavHeader.SideNavHeaderResponse;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.SignUpResponse;
import com.secondwarranty.secondwarrantyprovider.Retrofit.RetrofitClient;
import com.secondwarranty.secondwarrantyprovider.Utility.PreferenceUtility;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {
    public DataRepository(Context application) {
    }

//    For SignUp

    private MutableLiveData<Result> getDetails = new MutableLiveData<>();
    public MutableLiveData<Result> getDetails(Context context, String name, String mobile, String email, String password) {
        RetrofitClient apiService = RetrofitClient.getInstance();

        HashMap<String, String> headerMap = PreferenceUtility.getHeaderMap(context);
        HashMap<String, String> params = new HashMap<>();

        params.put("users_name", name);
        params.put("users_email", email);
        params.put("users_mobile", mobile);
        params.put("users_password", password);
        params.put("users_type", "provider");
        params.put("users_gender", "Male");

        Call<SignUpResponse> call = apiService
                .getApi()
                .signUp(headerMap, params);

        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("SignUp", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (response.body().getSuccess() == 1) {
                        getDetails.setValue(response.body().getResult());
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        getDetails.setValue(null);
//                        Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    getDetails.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                getDetails.setValue(null);
                Log.e("SignUp", " - > Error    " + t.getMessage());
            }
        });
        return getDetails;
    }

//    For Logging

    private MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.LoginModel.Result> getLoginDetails = new MutableLiveData<>();
    public MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.LoginModel.Result> getLoginDetails(Context context,
                                                                                                                      String user_email,
                                                                                                                      String user_password,
                                                                                                                      String user_type) {
        RetrofitClient apiService = RetrofitClient.getInstance();

        HashMap<String, String> headerMap = PreferenceUtility.getHeaderMap(context);
        HashMap<String, String> params = new HashMap<>();

        params.put("users_email", user_email);
        params.put("users_password", user_password);
        params.put("users_type", user_type);


        Call<LoginResponseClass> call = apiService
                .getApi()
                .loginResponse(headerMap, params);

        call.enqueue(new Callback<LoginResponseClass>() {
            @Override
            public void onResponse(Call<LoginResponseClass> call, Response<LoginResponseClass> response) {
                Log.e("LoginResponse", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (response.body().getSuccess() == 1) {
                        getLoginDetails.setValue(response.body().getResult());
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        PreferenceUtility.setStringValue(context.getApplicationContext(), PreferenceUtility.users_id, String.valueOf(response.body().getResult().getUsersData().getUsersId()));
                        PreferenceUtility.setStringValue(context.getApplicationContext(), PreferenceUtility.users_token, response.body().getResult().getUsersData().getUsersToken());
                        PreferenceUtility.setStringValue(context.getApplicationContext(), PreferenceUtility.users_email, response.body().getResult().getUsersData().getUsersEmail());
                        PreferenceUtility.setBoolValue(context.getApplicationContext(), PreferenceUtility.isLogin, true);


                    } else {
                        getLoginDetails.setValue(null);
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    getLoginDetails.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseClass> call, Throwable t) {
                getLoginDetails.setValue(null);
                Log.e("LoginResponse", " - > Error    " + t.getMessage());
            }
        });
        return getLoginDetails;
    }


//    For Logout

    private MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.LogoutModel.Result> getLogoutDetails = new MutableLiveData<>();
    public MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.LogoutModel.Result> getLogoutDetails(Context context) {
        RetrofitClient apiService = RetrofitClient.getInstance();

        HashMap<String, String> headerMap = PreferenceUtility.getHeaderMap(context);
        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtility.getStringValue(context.getApplicationContext(), PreferenceUtility.users_id));
        params.put("users_token", PreferenceUtility.getStringValue(context.getApplicationContext(), PreferenceUtility.users_token));

        PreferenceUtility.setBoolValue(context.getApplicationContext(), PreferenceUtility.isLogin, false);


        Call<LogoutResponseClass> call = apiService
                .getApi()
                .logoutResponse(headerMap, params);

        call.enqueue(new Callback<LogoutResponseClass>() {
            @Override
            public void onResponse(Call<LogoutResponseClass> call, Response<LogoutResponseClass> response) {
                Log.e("LogoutResponse", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (response.body().getSuccess() == 1) {
                        getLogoutDetails.setValue(response.body().getResult());
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        getLogoutDetails.setValue(null);
//                        Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    getLogoutDetails.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LogoutResponseClass> call, Throwable t) {
                getLogoutDetails.setValue(null);
                Log.e("LogoutResponse", " - > Error    " + t.getMessage());
            }
        });
        return getLogoutDetails;
    }


//    For Forgot Password

    private MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.ForgotPassword.Result> getOTPDetails = new MutableLiveData<>();
    public MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.ForgotPassword.Result> getOTPDetails(Context context,String mail) {
        RetrofitClient apiService = RetrofitClient.getInstance();

        HashMap<String, String> headerMap = PreferenceUtility.getHeaderMap(context);
        HashMap<String, String> params = new HashMap<>();

        params.put("users_email", mail);

        Call<ForgotPasswordResponse> call = apiService
                .getApi()
                .forgotPassword(headerMap, params);

        call.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                Log.e("ForgotPasswordResponse", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (response.body().getSuccess() == 1) {
                        getOTPDetails.setValue(response.body().getResult());
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        getOTPDetails.setValue(null);
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    getOTPDetails.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                getOTPDetails.setValue(null);
                Log.e("ForgotPasswordResponse", " - > Error    " + t.getMessage());
            }
        });
        return getOTPDetails;
    }


//    For Resetting Password

    private MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.ResetPassword.Result> getResetPassword = new MutableLiveData<>();
    public MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.ResetPassword.Result> getResetPassword(Context context, String users_forgot_password_code, String users_password) {
        RetrofitClient apiService = RetrofitClient.getInstance();

        HashMap<String, String> headerMap = PreferenceUtility.getHeaderMap(context);
        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtility.getStringValue(context.getApplicationContext(), PreferenceUtility.users_id));
        params.put("users_forgot_password_code",users_forgot_password_code);
        params.put("users_password",users_password);

        Call<ResetPasswordResponse> call = apiService
                .getApi()
                .reset_Password(headerMap, params);

        call.enqueue(new Callback<ResetPasswordResponse>() {
            @Override
            public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {
                Log.e("ResetPasswordResponse", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (response.body().getSuccess() == 1) {
                        getResetPassword.setValue(response.body().getResult());
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        getResetPassword.setValue(null);
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    getResetPassword.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {
                getResetPassword.setValue(null);
                Log.e("ResetPasswordResponse", " - > Error    " + t.getMessage());
            }
        });
        return getResetPassword;
    }


    //  For Provider Details

    private MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.SideNavHeader.Result> getProviderDetail = new MutableLiveData<>();
    public MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.SideNavHeader.Result> getProviderDetail(Context context) {
        RetrofitClient apiService = RetrofitClient.getInstance();

        HashMap<String, String> headerMap = PreferenceUtility.getHeaderMap(context);
        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtility.getStringValue(context.getApplicationContext(), PreferenceUtility.users_id));
        params.put("users_token", PreferenceUtility.getStringValue(context.getApplicationContext(), PreferenceUtility.users_token));


        Call<SideNavHeaderResponse> call = apiService
                .getApi()
                .sideNav(headerMap, params);

        call.enqueue(new Callback<SideNavHeaderResponse>() {
            @Override
            public void onResponse(Call<SideNavHeaderResponse> call, Response<SideNavHeaderResponse> response) {
                Log.e("SideNavHeaderResponse", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (response.body().getSuccess() == 1) {
                        getProviderDetail.setValue(response.body().getResult());
//                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        getProviderDetail.setValue(null);
//                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    getProviderDetail.setValue(null);
//                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SideNavHeaderResponse> call, Throwable t) {
                getProviderDetail.setValue(null);
                Log.e("SideNavResponse", " - > Error    " + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return getProviderDetail;
    }


//    For Dashboard(Upcoming & Completed)

    private MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.Dashboard.Result> getDashboardDetail = new MutableLiveData<>();
    public MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.Dashboard.Result> getDashboardDetail(Context context) {
        RetrofitClient apiService = RetrofitClient.getInstance();

        HashMap<String, String> headerMap = PreferenceUtility.getHeaderMap(context);
        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtility.getStringValue(context.getApplicationContext(), PreferenceUtility.users_id));
        params.put("users_token", PreferenceUtility.getStringValue(context.getApplicationContext(), PreferenceUtility.users_token));


        Call<DashboardResponseClass> call = apiService
                .getApi()
                .dashboard_details(headerMap, params);

        call.enqueue(new Callback<DashboardResponseClass>() {
            @Override
            public void onResponse(Call<DashboardResponseClass> call, Response<DashboardResponseClass> response) {
                Log.e("DashboardResponseClass", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (response.body().getSuccess() == 1) {
                        getDashboardDetail.setValue(response.body().getResult());
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        getDashboardDetail.setValue(null);
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    getDashboardDetail.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DashboardResponseClass> call, Throwable t) {
                getDashboardDetail.setValue(null);
                Log.e("DashboardResponseClass", " - > Error    " + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return getDashboardDetail;
    }


//    For Rejection

    private MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.RejectBooking.Result> getRejectionDetail = new MutableLiveData<>();
    public MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.RejectBooking.Result> getRejectionDetail(Context context, String cancellation_reason) {
        RetrofitClient apiService = RetrofitClient.getInstance();

        HashMap<String, String> headerMap = PreferenceUtility.getHeaderMap(context);
        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtility.getStringValue(context.getApplicationContext(), PreferenceUtility.users_id));
        params.put("users_token", PreferenceUtility.getStringValue(context.getApplicationContext(), PreferenceUtility.users_token));
        params.put("order_id",PreferenceUtility.getStringValue(context.getApplicationContext(),PreferenceUtility.order_id));
        params.put("cancellation_reason",cancellation_reason);
        Log.e("RejectBookingResponse", params.toString());


        Call<RejectBookingResponse> call = apiService
                .getApi()
                .rejectBooking(headerMap, params);

        call.enqueue(new Callback<RejectBookingResponse>() {
            @Override
            public void onResponse(Call<RejectBookingResponse> call, Response<RejectBookingResponse> response) {
                Log.e("RejectBookingResponse", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (response.body().getSuccess() == 1) {
                        getRejectionDetail.setValue(response.body().getResult());
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        getRejectionDetail.setValue(null);
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    getRejectionDetail.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RejectBookingResponse> call, Throwable t) {
                getRejectionDetail.setValue(null);
                Log.e("RejectBookingResponse", " - > Error    " + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return getRejectionDetail;
    }


//    For Earnings

    private MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.EarningClass.Result> getEarningDetail = new MutableLiveData<>();
    public MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.EarningClass.Result> getEarningDetail(Context context) {
        RetrofitClient apiService = RetrofitClient.getInstance();

        HashMap<String, String> headerMap = PreferenceUtility.getHeaderMap(context);
        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtility.getStringValue(context.getApplicationContext(), PreferenceUtility.users_id));
        params.put("users_token", PreferenceUtility.getStringValue(context.getApplicationContext(), PreferenceUtility.users_token));


        Call<EarningResponse> call = apiService
                .getApi()
                .myEarnings(headerMap, params);

        call.enqueue(new Callback<EarningResponse>() {
            @Override
            public void onResponse(Call<EarningResponse> call, Response<EarningResponse> response) {
                Log.e("EarningResponse", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (response.body().getSuccess() == 1) {
                        getEarningDetail.setValue(response.body().getResult());
//                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        getEarningDetail.setValue(null);
//                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    getEarningDetail.setValue(null);
//                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EarningResponse> call, Throwable t) {
                getEarningDetail.setValue(null);
                Log.e("EarningResponse", " - > Error    " + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return getEarningDetail;
    }


//    For Accept Booking

    private MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.AcceptBooking.Result> getAcceptDetails = new MutableLiveData<>();
    public MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.AcceptBooking.Result> getAcceptDetails(Context context) {
        RetrofitClient apiService = RetrofitClient.getInstance();

        HashMap<String, String> headerMap = PreferenceUtility.getHeaderMap(context);
        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtility.getStringValue(context.getApplicationContext(), PreferenceUtility.users_id));
        params.put("users_token", PreferenceUtility.getStringValue(context.getApplicationContext(), PreferenceUtility.users_token));
        params.put("order_id",PreferenceUtility.getStringValue(context.getApplicationContext(),PreferenceUtility.order_id));


        Call<AcceptBookingResponse> call = apiService
                .getApi()
                .accept_Booking(headerMap, params);

        call.enqueue(new Callback<AcceptBookingResponse>() {
            @Override
            public void onResponse(Call<AcceptBookingResponse> call, Response<AcceptBookingResponse> response) {
                Log.e("AcceptBookingResponse", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (response.body().getSuccess() == 1) {
                        getAcceptDetails.setValue(response.body().getResult());
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        getAcceptDetails.setValue(null);
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    getAcceptDetails.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AcceptBookingResponse> call, Throwable t) {
                getAcceptDetails.setValue(null);
                Log.e("AcceptBookingResponse", " - > Error    " + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return getAcceptDetails;
    }


//    For Job Status

    private MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.JobStatus.Result> getJobStatusDetails = new MutableLiveData<>();
    public MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.JobStatus.Result> getJobStatusDetails(Context context) {
        RetrofitClient apiService = RetrofitClient.getInstance();

        HashMap<String, String> headerMap = PreferenceUtility.getHeaderMap(context);
        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtility.getStringValue(context.getApplicationContext(), PreferenceUtility.users_id));
        params.put("users_token", PreferenceUtility.getStringValue(context.getApplicationContext(), PreferenceUtility.users_token));
        params.put("order_id",PreferenceUtility.getStringValue(context.getApplicationContext(),PreferenceUtility.order_id));
        params.put("job_status","D");


        Call<JobStatusResponse> call = apiService
                .getApi()
                .job_Status(headerMap, params);

        call.enqueue(new Callback<JobStatusResponse>() {
            @Override
            public void onResponse(Call<JobStatusResponse> call, Response<JobStatusResponse> response) {
                Log.e("AcceptBookingResponse", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (response.body().getSuccess() == 1) {
                        getJobStatusDetails.setValue(response.body().getResult());
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        getJobStatusDetails.setValue(null);
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    getJobStatusDetails.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JobStatusResponse> call, Throwable t) {
                getJobStatusDetails.setValue(null);
                Log.e("AcceptBookingResponse", " - > Error    " + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return getJobStatusDetails;
    }

//    For Happy Code

    private MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.HappyCodeClass.Result> getHappyCodeDetails = new MutableLiveData<>();
    public MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.HappyCodeClass.Result> getHappyCodeDetails(Context context, String happy_code) {
        RetrofitClient apiService = RetrofitClient.getInstance();

        HashMap<String, String> headerMap = PreferenceUtility.getHeaderMap(context);
        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtility.getStringValue(context.getApplicationContext(), PreferenceUtility.users_id));
        params.put("users_token", PreferenceUtility.getStringValue(context.getApplicationContext(), PreferenceUtility.users_token));
        params.put("order_id",PreferenceUtility.getStringValue(context.getApplicationContext(),PreferenceUtility.order_id));
        params.put("happy_code",happy_code);



        Call<HappyCodeResponse> call = apiService
                .getApi()
                .happy_code(headerMap, params);

        call.enqueue(new Callback<HappyCodeResponse>() {
            @Override
            public void onResponse(Call<HappyCodeResponse> call, Response<HappyCodeResponse> response) {
                Log.e("HappyCodeResponse", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (response.body().getSuccess() == 1) {
                        getHappyCodeDetails.setValue(response.body().getResult());
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        getHappyCodeDetails.setValue(null);
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    getHappyCodeDetails.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HappyCodeResponse> call, Throwable t) {
                getHappyCodeDetails.setValue(null);
                Log.e("HappyCodeResponse", " - > Error    " + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return getHappyCodeDetails;
    }


//    For Order Details Page

    private MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.OrderDetailsPage.Result> getOrderDetails = new MutableLiveData<>();
    public MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.OrderDetailsPage.Result> getOrderDetails(Context context) {
        RetrofitClient apiService = RetrofitClient.getInstance();

        HashMap<String, String> headerMap = PreferenceUtility.getHeaderMap(context);
        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtility.getStringValue(context.getApplicationContext(), PreferenceUtility.users_id));
        params.put("users_token", PreferenceUtility.getStringValue(context.getApplicationContext(), PreferenceUtility.users_token));
        params.put("order_id",PreferenceUtility.getStringValue(context.getApplicationContext(),PreferenceUtility.order_id));



        Call<OrderDetailsResponse> call = apiService
                .getApi()
                .order_Details(headerMap, params);

        call.enqueue(new Callback<OrderDetailsResponse>() {
            @Override
            public void onResponse(Call<OrderDetailsResponse> call, Response<OrderDetailsResponse> response) {
                Log.e("OrderDetailsResponse", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (response.body().getSuccess() == 1) {
                        getOrderDetails.setValue(response.body().getResult());
//                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        getOrderDetails.setValue(null);
//                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    getOrderDetails.setValue(null);
//                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderDetailsResponse> call, Throwable t) {
                getOrderDetails.setValue(null);
                Log.e("OrderDetailsResponse", " - > Error    " + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return getOrderDetails;
    }


//    For Help Centre

    private MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.HelpCentre.Result> getHelpCentreDetails = new MutableLiveData<>();
    public MutableLiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.HelpCentre.Result> getHelpCentreDetails(Context context, String message) {
        RetrofitClient apiService = RetrofitClient.getInstance();

        HashMap<String, String> headerMap = PreferenceUtility.getHeaderMap(context);
        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtility.getStringValue(context.getApplicationContext(), PreferenceUtility.users_id));
        params.put("users_token", PreferenceUtility.getStringValue(context.getApplicationContext(), PreferenceUtility.users_token));
        params.put("message",message);


        Call<HelpCentreResponse> call = apiService
                .getApi()
                .helpCentre_Response(headerMap, params);

        call.enqueue(new Callback<HelpCentreResponse>() {
            @Override
            public void onResponse(Call<HelpCentreResponse> call, Response<HelpCentreResponse> response) {
                Log.e("HelpCentreResponse", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (response.body().getSuccess() == 1) {
                        getHelpCentreDetails.setValue(response.body().getResult());
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        getHelpCentreDetails.setValue(null);
//                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    getHelpCentreDetails.setValue(null);
//                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HelpCentreResponse> call, Throwable t) {
                getHelpCentreDetails.setValue(null);
                Log.e("HelpCentreResponse", " - > Error    " + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return getHelpCentreDetails;
    }





}
