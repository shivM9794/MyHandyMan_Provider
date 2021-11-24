package com.secondwarranty.secondwarrantyprovider.DashBoardSection;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.drjacky.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.secondwarranty.secondwarrantyprovider.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    ImageView back_arrow;
    CircleImageView profileupdate;
    ImageView add_license,add_aadhaar;
    TextInputEditText upload_lic,aadhaar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        back_arrow = findViewById(R.id.back);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
        profileupdate = findViewById(R.id.profileupdate);
        profileupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(ProfileActivity.this)
                        .crop()
                        .cropOval()
                        .maxResultSize(1080, 1080)
                        .start(0);
            }
        });
//        upload_lic = findViewById(R.id.upload_lic);
//        add_license = findViewById(R.id.add_license);
//        add_license.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ImagePicker.Companion.with(ProfileActivity.this)
//                        .crop()
//                        .cropOval()
//                        .galleryOnly()
//                        .maxResultSize(1080, 1080)
//                        .start(1);
//            }
//        });
        aadhaar = findViewById(R.id.aadhaar);
        add_aadhaar = findViewById(R.id.add_aadhaar);
        add_aadhaar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(ProfileActivity.this)
                        .crop()
                        .cropOval()
                        .galleryOnly()
                        .maxResultSize(1080, 1080)
                        .start(2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {

            Uri uri = data.getData();
            profileupdate.setImageURI(uri);

        }
        if (requestCode==1){
            Uri uri = data.getData();
            String warranty_id = String.valueOf(uri);
            String[] parts = warranty_id.split("/");
            String imge_filename = parts[parts.length - 1];
            upload_lic.setText(imge_filename);
        }
        if (requestCode==2){
            Uri uri = data.getData();
            String aadhar_id = String.valueOf(uri);
            String[] parts = aadhar_id.split("/");
            String imge_filename = parts[parts.length - 1];
            aadhaar.setText(imge_filename);
        }
    }
}