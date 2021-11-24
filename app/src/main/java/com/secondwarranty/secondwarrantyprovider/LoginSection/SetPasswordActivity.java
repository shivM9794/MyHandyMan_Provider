package com.secondwarranty.secondwarrantyprovider.LoginSection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.secondwarranty.secondwarrantyprovider.R;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.ResetPassword.Result;
import com.secondwarranty.secondwarrantyprovider.ViewModel.DataViewModel;

public class SetPasswordActivity extends AppCompatActivity {

    TextInputEditText edit_pwd, edit_con_pwd;
    DataViewModel dataViewModel;
    EditText num;
    String pass = "", con_pass = "";
    String users_forgot_password_code = "", user_id = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        user_id = getIntent().getStringExtra("user_id");
        users_forgot_password_code = getIntent().getStringExtra("verification_code");

        initviews();


    }


    private void initviews() {
        edit_pwd = findViewById(R.id.edit_pwd);
        edit_con_pwd = findViewById(R.id.edit_con_pwd);

        edit_pwd.setHint("Password");
        edit_con_pwd.setHint("Confirm Password");
    }

    public void save_data(View view) {

        pass = edit_pwd.getText().toString().trim();
        con_pass = edit_con_pwd.getText().toString().trim();

        if (pass.equals("") || con_pass.equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_LONG).show();

        } else {
            if (pass.charAt(0) == '-') {
                Toast.makeText(getApplicationContext(), "Password cannot start with -", Toast.LENGTH_LONG).show();
                return;
            } else {

            }
        }

        if (!pass.equals(con_pass)) {
            Toast.makeText(getApplicationContext(), "Passwords do not match!!", Toast.LENGTH_LONG).show();
            return;
        } else {

        }

        setNewPassword();


    }

    private void setNewPassword() {

        dataViewModel.getResetPassword(getApplicationContext(), users_forgot_password_code, pass)
                .observe(this, new Observer<com.secondwarranty.secondwarrantyprovider.ResponseModel.ResetPassword.Result>() {
                    @Override
                    public void onChanged(Result result) {

                        Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent1);
                        finish();


                    }
                });
    }


}