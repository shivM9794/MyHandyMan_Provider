package com.secondwarranty.secondwarrantyprovider.DashBoardSection;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.drjacky.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.secondwarranty.secondwarrantyprovider.LoginSection.SetPasswordActivity;
import com.secondwarranty.secondwarrantyprovider.R;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.JobStatus.Result;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.OrderDetailsPage.OrderDetailPage;
import com.secondwarranty.secondwarrantyprovider.ViewModel.DataViewModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class See_DetailsActivity extends AppCompatActivity {
    TextView cancel_btn, happy_code_btn,name_user,address_user,date_of_booking,time_slot,fan_not_working,fan_speed_low,disclaimer_txt;
    ImageView back_arrow;
    DataViewModel dataViewModel;
    Dialog dialog;
    TextInputEditText et_email_1,et_email_2,et_email_3,et_email_4;
    Button btn_submit;
    String e1="",e2="",e3="",e4="";
    CircleImageView profile_image;
    Long number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_details);
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        cancel_btn = findViewById(R.id.cancel_btn);
        name_user = findViewById(R.id.name_user);
        address_user  = findViewById(R.id.address_user);
        profile_image = findViewById(R.id.profile_image);
        date_of_booking = findViewById(R.id.date_of_booking);
        fan_not_working = findViewById(R.id.fan_not_working);
        fan_speed_low = findViewById(R.id.fan_speed_low);
        disclaimer_txt = findViewById(R.id.disclaimer_txt);
        time_slot = findViewById(R.id.time_slot);
        happy_code_btn = findViewById(R.id.happy_code_btn);
        happy_code_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobStatus();
            }
        });
        cancel_btn.setOnClickListener(v -> {
            Intent intent = new Intent(See_DetailsActivity.this, CancelActivity.class);
            startActivity(intent);
            finish();
        });
        back_arrow = findViewById(R.id.back_arrow_icon);
        back_arrow.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
            startActivity(intent);
            finish();
        });
        orderDetails();
    }

    private void orderDetails() {

        dataViewModel.getOrderDetails(getApplicationContext())
                .observe(this, new Observer<com.secondwarranty.secondwarrantyprovider.ResponseModel.OrderDetailsPage.Result>() {
                    @Override
                    public void onChanged(com.secondwarranty.secondwarrantyprovider.ResponseModel.OrderDetailsPage.Result result) {

                        if (result != null){

                            List<OrderDetailPage> orderDetailPages = result.getOrderDetailPage();
                            name_user.setText(orderDetailPages.get(0).getUsersName());
                            address_user.setText(orderDetailPages.get(0).getUsersEmail());
                            date_of_booking.setText(orderDetailPages.get(0).getDate());
                            time_slot.setText(orderDetailPages.get(0).getTime());
                            number = orderDetailPages.get(0).getUsersMobile();
                            fan_not_working.setText(orderDetailPages.get(0).getSubCategoryName() + "(" + orderDetailPages.get(0).getServiceType()
                            + ")");
                            fan_speed_low.setText("Min Charges - "+ orderDetailPages.get(0).getMinCharge());
                            disclaimer_txt.setText(orderDetailPages.get(0).getDisclaimer());




                        }

                    }
                });
    }

    private void jobStatus() {

        dataViewModel.getJobStatusDetails(getApplicationContext())
                .observe(this, new Observer<com.secondwarranty.secondwarrantyprovider.ResponseModel.JobStatus.Result>() {
                    @Override
                    public void onChanged(Result result) {

                        if (result != null) {

                            jobStatus_HappyCode();

                        }
                    }


                });
    }

    private void jobStatus_HappyCode() {
        dialog = new Dialog(See_DetailsActivity.this);
        dialog.setContentView(R.layout.happy_code);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView cancel = dialog.findViewById(R.id.cancel);
         et_email_1 = dialog.findViewById(R.id.et_email_1);
         et_email_2 = dialog.findViewById(R.id.et_email_2);
         et_email_3 = dialog.findViewById(R.id.et_email_3);
         et_email_4 = dialog.findViewById(R.id.et_email_4);

         btn_submit = dialog.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                happyCode_Update();

            }
        });
        addListeners();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    private void happyCode_Update() {

        String e = e1 + e2 + e3 + e4;

        dataViewModel.getHappyCodeDetails(getApplicationContext(),e)
                .observe(this, new Observer<com.secondwarranty.secondwarrantyprovider.ResponseModel.HappyCodeClass.Result>() {


                    @Override
                    public void onChanged(com.secondwarranty.secondwarrantyprovider.ResponseModel.HappyCodeClass.Result result) {

                        if (result != null){
                            dialog.dismiss();

                        }

                    }
                });
    }


    private void addListeners() {

        et_email_1.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                e1 = s.toString();
                if(e1.length()==1)
                    et_email_2.requestFocus();
                checkForBlanks();
            }

            @Override
            public void afterTextChanged(Editable s) {}

        });

        et_email_2.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                e2 = s.toString();
                if(e2.length()==1)
                    et_email_3.requestFocus();
                checkForBlanks();
            }

            @Override
            public void afterTextChanged(Editable s) {}

        });

        et_email_3.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                e3 = s.toString();
                if(e3.length()==1)
                    et_email_4.requestFocus();
                checkForBlanks();
            }

            @Override
            public void afterTextChanged(Editable s) {}

        });

        et_email_4.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                e4 = s.toString();
                checkForBlanks();
                if(e4.length()==1)
                    hideKeyboardFrom(getApplicationContext(), et_email_4);
            }

            @Override
            public void afterTextChanged(Editable s) {}

        });


    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void checkForBlanks() {
        if(e1.equals("")||e2.equals("")||e3.equals("")||e4.equals("")){
            btn_submit.setVisibility(View.GONE);
        }else{

            btn_submit.setVisibility(View.VISIBLE);
        }

    }


    public void callUser(View view) {

        Intent myIntent = new Intent(Intent.ACTION_DIAL);
        myIntent.setData(Uri.parse("tel:"+number));
        startActivity(myIntent);
        finish();
    }
}