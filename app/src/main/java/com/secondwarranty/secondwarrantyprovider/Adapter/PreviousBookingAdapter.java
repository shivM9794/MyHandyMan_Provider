package com.secondwarranty.secondwarrantyprovider.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.secondwarranty.secondwarrantyprovider.DashBoardSection.CancelActivity;
import com.secondwarranty.secondwarrantyprovider.DashBoardSection.See_DetailsActivity;
import com.secondwarranty.secondwarrantyprovider.R;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.Dashboard.Upcomingbooking;
import com.secondwarranty.secondwarrantyprovider.Utility.PreferenceUtility;

import java.util.ArrayList;
import java.util.List;

public class PreviousBookingAdapter extends RecyclerView.Adapter<PreviousBookingAdapter.PreviousBookingViewHolder> {

    List<Upcomingbooking> upcomingbookings;
    Context context;

    public OnclickListener onclickListener;



    public PreviousBookingAdapter(List<Upcomingbooking> upcomingbookings, Context context,OnclickListener onclickListener) {
        this.upcomingbookings = upcomingbookings;
        this.context = context;
        this.onclickListener = onclickListener;
    }

    @NonNull
    @Override
    public PreviousBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.previous_booking_recycler, parent, false);
        PreviousBookingAdapter.PreviousBookingViewHolder previousBookingViewHolder = new PreviousBookingViewHolder(view);
        return previousBookingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PreviousBookingViewHolder holder, int position) {
        holder.see_detalis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, See_DetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context, CancelActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);
            }
        });

        holder.btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickListener.onClick();
            }
        });

        holder.name.setText(upcomingbookings.get(position).getUsersName());
//        holder.mobile.setText(upcomingbookings.get(position).getUsersMobile());
        holder.service_name.setText("Service Type : " +upcomingbookings.get(position).getServiceType() + " ( "+upcomingbookings.get(position).getSubCategoryName()+" )");
        holder.date_of_booking.setText(upcomingbookings.get(position).getDate());
        holder.time_slot.setText(upcomingbookings.get(position).getTime());

        PreferenceUtility.setStringValue(context.getApplicationContext(), PreferenceUtility.order_id, String.valueOf(upcomingbookings.get(position).getOrderId()));


    }

    @Override
    public int getItemCount() {
        return upcomingbookings.size();
    }

    public class PreviousBookingViewHolder extends RecyclerView.ViewHolder {
        TextView name, mobile, address, see_detalis,service_name,date_of_booking,time_slot;
        Button btn_reject,btn_accept;

        public PreviousBookingViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.user_name);
            mobile = itemView.findViewById(R.id.user_mobile_no);
            address = itemView.findViewById(R.id.user_location);
            service_name = itemView.findViewById(R.id.service_name);
            see_detalis = itemView.findViewById(R.id.see_details);
            btn_reject= itemView.findViewById(R.id.btn_reject);
            btn_accept = itemView.findViewById(R.id.btn_accept);
            date_of_booking = itemView.findViewById(R.id.date_of_booking);
            time_slot = itemView.findViewById(R.id.time_slot);
        }


    }

    public interface OnclickListener{

        public void onClick();

    }


}
