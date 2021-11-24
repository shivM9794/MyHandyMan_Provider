package com.secondwarranty.secondwarrantyprovider.DashBoardSection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.secondwarranty.secondwarrantyprovider.R;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.RejectBooking.Result;
import com.secondwarranty.secondwarrantyprovider.ViewModel.DataViewModel;

public class CancelActivity extends AppCompatActivity {
    Button submit;
    ImageView back_arrow;
    EditText others_reason_box;
    RadioButton radio_btn1, radio_btn2, radio_btn3, radio_btn4, radio_btn5, radio_btn6, radio_btn7;
    DataViewModel dataViewModel;
    String cancellation_reason = "";
    RadioGroup radio_grp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        submit = findViewById(R.id.btn_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cancellationDone();

            }
        });
        back_arrow = findViewById(R.id.back_icon);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CancelActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();


            }
        });
        radio_grp = findViewById(R.id.radio_grp);
        radio_grp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                Log.e("RejectBookingResponse", "a");
                if (radio_btn1.isChecked()) {
                    cancellation_reason = radio_btn1.getText().toString();
                } else if (radio_btn2.isChecked()) {
                    cancellation_reason = radio_btn2.getText().toString();
                } else if (radio_btn3.isChecked()) {
                    cancellation_reason = radio_btn3.getText().toString();
                } else if (radio_btn4.isChecked()) {
                    cancellation_reason = radio_btn4.getText().toString();
                } else if (radio_btn5.isChecked()) {
                    cancellation_reason = radio_btn5.getText().toString();
                } else if (radio_btn6.isChecked()) {
                    cancellation_reason = radio_btn6.getText().toString();
                }

            }
        });
        radio_btn1 = findViewById(R.id.radio_btn1);
        radio_btn2 = findViewById(R.id.radio_btn2);
        radio_btn3 = findViewById(R.id.radio_btn3);
        radio_btn4 = findViewById(R.id.radio_btn4);
        radio_btn5 = findViewById(R.id.radio_btn5);
        radio_btn6 = findViewById(R.id.radio_btn6);
        others_reason_box = findViewById(R.id.others_reason_box);
        radio_btn7 = findViewById(R.id.radio_btn7);
        radio_btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                others_reason_box.setVisibility(View.VISIBLE);
            }
        });


    }

    private void cancellationDone() {


        dataViewModel.getRejectionDetail(getApplicationContext(), cancellation_reason)
                .observe(this, new Observer<com.secondwarranty.secondwarrantyprovider.ResponseModel.RejectBooking.Result>() {

                    @Override
                    public void onChanged(Result result) {

                        if (result != null) {

                            Intent intent = new Intent(CancelActivity.this, DashboardActivity.class);
                            startActivity(intent);
                            finish();

                        }
                    }

                });
    }
}