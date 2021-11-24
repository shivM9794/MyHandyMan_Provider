package com.secondwarranty.secondwarrantyprovider.DashBoardSection;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.secondwarranty.secondwarrantyprovider.R;

public class SettingsActivity extends AppCompatActivity {

    TextView txt_on, txt_off, not_on, not_off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        txt_on = findViewById(R.id.txt_on);
        txt_off = findViewById(R.id.txt_off);
        not_on = findViewById(R.id.not_on);
        not_off = findViewById(R.id.not_off);

        txt_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_on.setBackgroundResource(R.color.stroke_yellow);
                txt_off.setBackgroundResource(R.color.gray_color);
            }
        });

        txt_off.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                txt_on.setBackgroundResource(R.color.gray_color);
                txt_off.setBackgroundResource(R.color.stroke_yellow);
            }
        });

        not_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                not_on.setBackgroundResource(R.color.stroke_yellow);
                not_off.setBackgroundResource(R.color.gray_color);
            }
        });

        not_off.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                not_on.setBackgroundResource(R.color.gray_color);
                not_off.setBackgroundResource(R.color.stroke_yellow);
            }
        });
    }
}