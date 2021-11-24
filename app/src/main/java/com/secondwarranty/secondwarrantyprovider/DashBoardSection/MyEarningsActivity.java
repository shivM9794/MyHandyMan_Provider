package com.secondwarranty.secondwarrantyprovider.DashBoardSection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.secondwarranty.secondwarrantyprovider.Adapter.MyEarningAdapter;
import com.secondwarranty.secondwarrantyprovider.R;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.EarningClass.MyearningsDatum;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.EarningClass.Result;
import com.secondwarranty.secondwarrantyprovider.ViewModel.DataViewModel;

import java.util.List;

public class MyEarningsActivity extends AppCompatActivity {
    ImageView imageView;
    TextView service_name, txt_job_id, txt_amount_paid, job_details, date_of_booking, time_slot;
    DataViewModel dataViewModel;
    RecyclerView earning_recyler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earnings);
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        earning_recyler = findViewById(R.id.earning_recyler);
        earning_recyler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        service_name = findViewById(R.id.service_name);
        txt_job_id = findViewById(R.id.txt_job_id);
        txt_amount_paid = findViewById(R.id.txt_amount_paid);
        job_details = findViewById(R.id.job_details);
        date_of_booking = findViewById(R.id.date_of_booking);
        time_slot = findViewById(R.id.time_slot);
        my_Earnings();
        imageView = findViewById(R.id.back_arrow);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void my_Earnings() {

        dataViewModel.getEarningDetail(getApplicationContext())
                .observe(this, new Observer<com.secondwarranty.secondwarrantyprovider.ResponseModel.EarningClass.Result>() {

                    @Override
                    public void onChanged(Result result) {

                        if (result != null) {

                            List<MyearningsDatum> myearningsDatum = result.getMyearningsData();
                            MyEarningAdapter myEarningAdapter = new MyEarningAdapter(myearningsDatum, getApplicationContext());
                            earning_recyler.setAdapter(myEarningAdapter);
                        }


                    }
                });
    }
}