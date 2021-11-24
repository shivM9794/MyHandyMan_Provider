package com.secondwarranty.secondwarrantyprovider.LoginSection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.secondwarranty.secondwarrantyprovider.DashBoardSection.DashboardActivity;
import com.secondwarranty.secondwarrantyprovider.R;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.LoginModel.Result;
import com.secondwarranty.secondwarrantyprovider.ViewModel.DataViewModel;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText  password;
    EditText email_address;
    Button login;
    TextView sign_up,forgot_password;
    private static final int PERMISSION_READ_STATE = 1;
    boolean isAllFieldsChecked = false;
    public static String PREFS_NAME = "MyPrefsFile";
    DataViewModel dataViewModel;
    String user_email, user_password, user_type = "provider";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        }
        email_address = findViewById(R.id.email_address);
        email_address.setHint("Email Address");
        password = findViewById(R.id.password);
        password.setHint("Password");
        login = findViewById(R.id.login_btn);
        sign_up = findViewById(R.id.btn_signup);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
        forgot_password = findViewById(R.id.forgot_password);

    }

    private boolean CheckAllFields() {

        if (email_address.length() == 0) {
            email_address.setError("Email is required");
            return false;
        }
        if (password.length() == 0) {
            password.setError("Password is required");
            return false;
        } else if (password.length() < 8) {
            password.setError("Password must be minimum 8 characters");
            return false;
        }

        return true;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_READ_STATE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_READ_STATE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED
                        && grantResults[3] == PackageManager.PERMISSION_GRANTED
                        && grantResults[4] == PackageManager.PERMISSION_GRANTED) {

                }
            }
            break;
        }

    }

    public void loginInside(View view) {

        loginSuccessful();
    }

    private void loginSuccessful() {

        user_email = email_address.getText().toString();
        user_password = password.getText().toString();

        dataViewModel.getLoginDetails(getApplicationContext(), user_email, user_password, user_type)
                .observe(this, new Observer<com.secondwarranty.secondwarrantyprovider.ResponseModel.LoginModel.Result>() {

                    @Override
                    public void onChanged(Result result) {

                        isAllFieldsChecked = CheckAllFields();
                        if (isAllFieldsChecked) {
                            Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                });
    }

    public void txt_forgot_password(View view) {



        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(LoginActivity.this, R.style.BottomSheetDialogTheme);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_style_sheet, (LinearLayout) findViewById(R.id.bottomSheetContainer));
        EditText forgot_et_email = bottomSheetView.findViewById(R.id.forgot_et_email);
        bottomSheetView.findViewById(R.id.email_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = forgot_et_email.getText().toString();
                otp_sent(mail);
                bottomSheetDialog.dismiss();

            }

        });
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

    }


    private void otp_sent(String mail) {

        dataViewModel.getOTPDetails(getApplicationContext(),mail)
                .observe(this, new Observer<com.secondwarranty.secondwarrantyprovider.ResponseModel.ForgotPassword.Result>() {


                    @Override
                    public void onChanged(com.secondwarranty.secondwarrantyprovider.ResponseModel.ForgotPassword.Result result) {

                        Intent intent = new Intent(getApplicationContext(),ResetPasswordActivity.class);
                        intent.putExtra("user_id",result.getUsersData().getUsersId());
                        startActivity(intent);

                    }
                });
    }
}






