package com.secondwarranty.secondwarrantyprovider.DashBoardSection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.secondwarranty.secondwarrantyprovider.R;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.HelpCentre.Result;
import com.secondwarranty.secondwarrantyprovider.ViewModel.DataViewModel;

public class HelpCentre extends AppCompatActivity {

    MaterialCardView materialCardView1,materialCardView2,materialCardView3,materialCardView4,
            materialCardView_et1,materialCardView_et2,materialCardView_et3,materialCardView_et4;
    Button submit;
    DataViewModel dataViewModel;
    String message = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_centre);
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        materialCardView1 = findViewById(R.id.service_experience);
        materialCardView2 = findViewById(R.id.materialCard2);
        materialCardView3 = findViewById(R.id.materialCard3);
        materialCardView4 = findViewById(R.id.materialCard4);
        materialCardView_et1 = findViewById(R.id.et_cardView1);
        materialCardView_et2 = findViewById(R.id.et_cardView2);
        materialCardView_et3 = findViewById(R.id.et_cardView3);
        materialCardView_et4 = findViewById(R.id.et_cardView4);




        materialCardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialCardView_et1.setVisibility(View.VISIBLE);
                materialCardView_et2.setVisibility(View.GONE);
                materialCardView_et3.setVisibility(View.GONE);
                materialCardView_et4.setVisibility(View.GONE);
                submit.setVisibility(View.VISIBLE);
            }
        });
        materialCardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialCardView_et2.setVisibility(View.VISIBLE);
                materialCardView_et1.setVisibility(View.GONE);
                materialCardView_et3.setVisibility(View.GONE);
                materialCardView_et4.setVisibility(View.GONE);
                submit.setVisibility(View.VISIBLE);
            }
        });
        materialCardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialCardView_et3.setVisibility(View.VISIBLE);
                materialCardView_et1.setVisibility(View.GONE);
                materialCardView_et2.setVisibility(View.GONE);
                materialCardView_et4.setVisibility(View.GONE);
                submit.setVisibility(View.VISIBLE);
            }
        });
        materialCardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialCardView_et4.setVisibility(View.VISIBLE);
                materialCardView_et1.setVisibility(View.GONE);
                materialCardView_et2.setVisibility(View.GONE);
                materialCardView_et3.setVisibility(View.GONE);
                submit.setVisibility(View.VISIBLE);
            }
        });
        submit = findViewById(R.id.btn_submit);



    }

    public void submitQuery(View view) {

        submitQueryResponse();
    }

    private void submitQueryResponse() {

        dataViewModel.getHelpCentreDetails(getApplicationContext(), message)
                .observe(this, new Observer<com.secondwarranty.secondwarrantyprovider.ResponseModel.HelpCentre.Result>() {
                    @Override
                    public void onChanged(Result result) {

                        if (result != null){

                            Intent intent = new Intent(HelpCentre.this,DashboardActivity.class);
                            startActivity(intent);
                            finish();

                        }

                    }
                });
    }
}