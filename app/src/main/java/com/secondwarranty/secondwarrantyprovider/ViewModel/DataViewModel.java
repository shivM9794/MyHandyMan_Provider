package com.secondwarranty.secondwarrantyprovider.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.secondwarranty.secondwarrantyprovider.Repository.DataRepository;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.Result;

public class DataViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    public DataViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);
    }

    public LiveData<Result> getDetails(Context context, String name, String mobile, String email, String password) {
        return dataRepository.getDetails(context, name, mobile, email, password);
    }

    public LiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.LoginModel.Result> getLoginDetails(Context context, String user_email, String user_password, String user_type) {
        return dataRepository.getLoginDetails(context, user_email, user_password, user_type);
    }

    public LiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.LogoutModel.Result> getLogoutDetails(Context context) {
        return dataRepository.getLogoutDetails(context);
    }

    public LiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.ForgotPassword.Result> getOTPDetails(Context context, String mail) {
        return dataRepository.getOTPDetails(context, mail);
    }

    public LiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.ResetPassword.Result> getResetPassword(Context context, String users_forgot_password_code, String users_password) {
        return dataRepository.getResetPassword(context, users_forgot_password_code, users_password);
    }

    public LiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.SideNavHeader.Result> getProviderDetail(Context context) {
        return dataRepository.getProviderDetail(context);
    }

    public LiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.Dashboard.Result> getDashboardDetail(Context context) {
        return dataRepository.getDashboardDetail(context);
    }

    public LiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.RejectBooking.Result> getRejectionDetail(Context context, String cancellation_reason) {
        return dataRepository.getRejectionDetail(context, cancellation_reason);
    }

    public LiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.EarningClass.Result> getEarningDetail(Context context) {
        return dataRepository.getEarningDetail(context);
    }

    public LiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.AcceptBooking.Result> getAcceptDetails(Context context) {
        return dataRepository.getAcceptDetails(context);
    }

    public LiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.JobStatus.Result> getJobStatusDetails(Context context) {
        return dataRepository.getJobStatusDetails(context);
    }

    public LiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.HappyCodeClass.Result> getHappyCodeDetails(Context context, String happy_code) {
        return dataRepository.getHappyCodeDetails(context, happy_code);
    }

    public LiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.OrderDetailsPage.Result> getOrderDetails(Context context) {
        return dataRepository.getOrderDetails(context);
    }

    public LiveData<com.secondwarranty.secondwarrantyprovider.ResponseModel.HelpCentre.Result> getHelpCentreDetails(Context context, String message) {
        return dataRepository.getHelpCentreDetails(context, message);
    }


}
