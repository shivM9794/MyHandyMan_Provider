package com.secondwarranty.secondwarrantyprovider.LoginSection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.secondwarranty.secondwarrantyprovider.R;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.Result;
import com.secondwarranty.secondwarrantyprovider.ViewModel.DataViewModel;

public class SignUpActivity extends AppCompatActivity {
    EditText full_name,mobile,email_address,password,cnf_password;
    Button signup;
    TextView txt_login;
    DataViewModel dataViewModel;
    RadioButton radio_btn_male, radio_btn_female, radio_btn_others;
    String full_names, mobileP, email_addresss, passwordP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        full_name = findViewById(R.id.ed_full_name);
        mobile = findViewById(R.id.et_mobile_no);
//        radio_btn_male = findViewById(R.id.radio_btn_male);
//        radio_btn_female = findViewById(R.id.radio_btn_female);
//        radio_btn_others = findViewById(R.id.radio_btn_others);
        email_address = findViewById(R.id.et_email_address);
        signup = findViewById(R.id.button);
        password = findViewById(R.id.pass);
        password.setHint("Password");
        cnf_password = findViewById(R.id.pass_cnf);
        cnf_password.setHint("Confirm Password");
        txt_login = findViewById(R.id.txt_login);
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    public void signUp(View view) {
        
        signpVerfication();
    }

    private void signpVerfication() {

        full_names = full_name.getText().toString();
        mobileP = mobile.getText().toString();
        email_addresss = email_address.getText().toString();
        passwordP = password.getText().toString();

        dataViewModel.getDetails(getApplicationContext(), full_names, mobileP, email_addresss, passwordP)
                .observe(this, new Observer<Result>() {
                    @Override
                    public void onChanged(Result result) {

                        if (result != null) {

                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                            finish();

                        }
                    }
                });
    }
}