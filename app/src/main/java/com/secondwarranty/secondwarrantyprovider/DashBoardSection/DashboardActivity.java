package com.secondwarranty.secondwarrantyprovider.DashBoardSection;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.secondwarranty.secondwarrantyprovider.Adapter.CompleteBookingAdapter;
import com.secondwarranty.secondwarrantyprovider.Adapter.PreviousBookingAdapter;
import com.secondwarranty.secondwarrantyprovider.LoginSection.LoginActivity;
import com.secondwarranty.secondwarrantyprovider.R;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.Dashboard.Completedbooking;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.Dashboard.Upcomingbooking;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.LogoutModel.Result;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.SideNavHeader.Userdetails;
import com.secondwarranty.secondwarrantyprovider.ViewModel.DataViewModel;

import java.util.List;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, PreviousBookingAdapter.OnclickListener {
    private static final String APP_NAME = "Second Warranty";
    SmoothBottomBar bottomNavigationView;
    RecyclerView previous_recycler, completed_recycler;
    RecyclerView.Adapter adapter;
    MaterialCardView materialCardView1, materialCardView2;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView imageView;
    Button logout;
    DataViewModel dataViewModel;
    boolean doubleBackToExitPressedOnce = false;
    TextView name_txt, txt_mobile_no;
    List<Completedbooking> completeBooking;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        previous_recycler = findViewById(R.id.previous_recycler);
        completed_recycler = findViewById(R.id.complete_recycler);
        navigationView = findViewById(R.id.navigation_view);
        materialCardView1 = findViewById(R.id.previous_booking_cardView);
        materialCardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previous_recycler.setVisibility(View.VISIBLE);
                completed_recycler.setVisibility(View.GONE);
                materialCardView1.setStrokeColor(getResources().getColor(R.color.yellow_stroke));
                materialCardView2.setStrokeColor(getResources().getColor(R.color.gray_color));
            }
        });
        materialCardView2 = findViewById(R.id.completed_booking_cardView);
        materialCardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completed_recycler.setVisibility(View.VISIBLE);
                previous_recycler.setVisibility(View.GONE);
                materialCardView2.setStrokeColor(getResources().getColor(R.color.yellow_stroke));
                materialCardView1.setStrokeColor(getResources().getColor(R.color.gray_color));

                completed_recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                adapter = new CompleteBookingAdapter(completeBooking, getApplicationContext());
                completed_recycler.setAdapter(adapter);

            }
        });
        logout = findViewById(R.id.logout_btn);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener((OnItemSelectedListener) i -> {
            switch (i) {

                case 1:
                    Intent intent1 = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent1);
                    break;
                case 2:
                    Intent intent2 = new Intent(getApplicationContext(), SettingsActivity.class);
                    startActivity(intent2);
                    break;


            }

            return true;
        });
        View navHeaderView = navigationView.getHeaderView(0);
        name_txt = navHeaderView.findViewById(R.id.name_txt);
        txt_mobile_no = navHeaderView.findViewById(R.id.txt_mobile_no);
        drawerLayout = findViewById(R.id.drawer_layout);
        imageView = findViewById(R.id.menu);
        navigationView = findViewById(R.id.navigation_view);
        previousBooking();
//        completedBooking();
        navigationdrawer();
        side_nav_details();

    }

    private void side_nav_details() {

        dataViewModel.getProviderDetail(getApplicationContext())
                .observe(this, new Observer<com.secondwarranty.secondwarrantyprovider.ResponseModel.SideNavHeader.Result>() {

                    @Override
                    public void onChanged(com.secondwarranty.secondwarrantyprovider.ResponseModel.SideNavHeader.Result result) {

                        if (result != null) {

                            Userdetails userdetails = result.getUserdetails();
                            name_txt.setText(userdetails.getUsersName());
                            txt_mobile_no.setText(String.valueOf(userdetails.getUsersMobile()));

                        }

                    }
                });
    }

    private void navigationdrawer() {


        //navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.help_centre);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 1000);

    }

//    @Override
//    public void onBackPressed() {
//        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else
//            super.onBackPressed();
//
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.share_second_warranty:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Second Warranty\nThe perfect online solution for installment and repair of your electronic appliances";
                String shareSubject = "abc ";
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
                break;

            case R.id.rate_second_warranty:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + APP_NAME)));
                break;

            case R.id.about_second_warranty:
                Intent intent = new Intent(getApplicationContext(), About_UsActivity.class);
                startActivity(intent);
                break;

            case R.id.terms:
                Intent intent1 = new Intent(getApplicationContext(), TermsAndConditionActivity.class);
                startActivity(intent1);
                break;

            case R.id.privacy:
                Intent intent2 = new Intent(getApplicationContext(), PrivacyPolicyActivity.class);
                startActivity(intent2);
                break;

            case R.id.my_earnings:
                Intent intent3 = new Intent(getApplicationContext(), MyEarningsActivity.class);
                startActivity(intent3);
                break;

            case R.id.help_centre:
                Intent intent4 = new Intent(getApplicationContext(), HelpCentre.class);
                startActivity(intent4);
                break;


        }
        return true;
    }


//    private void completedBooking() {
//
//        dataViewModel.getDashboardDetail(getApplicationContext())
//                .observe(this, new Observer<com.secondwarranty.secondwarrantyprovider.ResponseModel.Dashboard.Result>() {
//                    @Override
//                    public void onChanged(com.secondwarranty.secondwarrantyprovider.ResponseModel.Dashboard.Result result) {
//
//                        if (result != null) {
//
//
////                            completed_recycler.setHasFixedSize(true);
//                            completed_recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
////                            List<Completedbooking> completeBooking = result.getCompletedbookings();
////                            adapter = new CompleteBookingAdapter(completeBooking, getApplicationContext());
////                            completed_recycler.setAdapter(adapter);
//                        }
////                    });
////                }
//    }

    private void previousBooking() {

        dataViewModel.getDashboardDetail(getApplicationContext())
                .observe(this, new Observer<com.secondwarranty.secondwarrantyprovider.ResponseModel.Dashboard.Result>() {
                    @Override
                    public void onChanged(com.secondwarranty.secondwarrantyprovider.ResponseModel.Dashboard.Result result) {
                        if (result != null) {

                            previous_recycler.setHasFixedSize(true);
                            previous_recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                            List<Upcomingbooking> upcomingbookings = result.getUpcomingbookings();
                            completeBooking = result.getCompletedbookings();
                            adapter = new PreviousBookingAdapter(upcomingbookings, getApplicationContext(), DashboardActivity.this);
                            previous_recycler.setAdapter(adapter);
                        }
                    }
                });
    }


    public void logoutBtn(View view) {

        logoutSuccseeful();
    }

    private void logoutSuccseeful() {

        dataViewModel.getLogoutDetails(getApplicationContext())
                .observe(this, new Observer<com.secondwarranty.secondwarrantyprovider.ResponseModel.LogoutModel.Result>() {

                    @Override
                    public void onChanged(Result result) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
                        builder.setTitle("Second Warranty");
                        builder.setMessage("Are you sure you want to exit ?");
                        builder.setCancelable(false);
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent1);
                                finish();

                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        });
                        builder.create().show();
                    }
                });

    }

    @Override
    public void onClick() {
        show_bidalert();
    }

    private void show_bidalert() {
        dialog = new Dialog(DashboardActivity.this);
        dialog.setContentView(R.layout.alert_bid);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView cancel = dialog.findViewById(R.id.cancel);
//        TextView product_name = dialog.findViewById(R.id.product_name);
//        TextView service_type_name = dialog.findViewById(R.id.service_type_name);
        TextView title = dialog.findViewById(R.id.title);
        TextView view = dialog.findViewById(R.id.view);
        TextView view_msg = dialog.findViewById(R.id.view_msg);
        dataViewModel.getAcceptDetails(getApplicationContext())
                .observe(this, new Observer<com.secondwarranty.secondwarrantyprovider.ResponseModel.AcceptBooking.Result>() {

                    @Override
                    public void onChanged(com.secondwarranty.secondwarrantyprovider.ResponseModel.AcceptBooking.Result result) {

                        if (result != null) {

//                            product_name.setText("Product Type :"+ result.getAcceptBooking().getSubCategoryName());
//                            service_type_name.setText("Service Type :"+  result.getAcceptBooking().getServiceType());

                        }
                    }


                });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
        dialog.show();
    }


}