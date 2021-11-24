package com.secondwarranty.secondwarrantyprovider.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.secondwarranty.secondwarrantyprovider.DashBoardSection.See_DetailsActivity;
import com.secondwarranty.secondwarrantyprovider.R;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.Dashboard.Completedbooking;

import java.util.ArrayList;
import java.util.List;

public class CompleteBookingAdapter extends RecyclerView.Adapter<CompleteBookingAdapter.CompleteBookingViewHolder> {

    List<Completedbooking> completeBooking;
    Context context;

    public CompleteBookingAdapter(List<Completedbooking> completeBooking, Context context) {
        this.completeBooking = completeBooking;
        this.context = context;
    }

    @NonNull
    @Override
    public CompleteBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cpmpleted_booking_recycler,parent,false);
        CompleteBookingAdapter.CompleteBookingViewHolder completeBookingViewHolder = new CompleteBookingViewHolder(view);
        return completeBookingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CompleteBookingViewHolder holder, int position) {

        holder.name.setText(completeBooking.get(position).getUsersName());
//        holder.mobile.setText(completeBooking.get(position).getUsersMobile());
        holder.total_amt.setText("₹" + completeBooking.get(position).getMinCharge());
        holder.date_of_booking.setText(completeBooking.get(position).getDate());
        holder.time_slot.setText(completeBooking.get(position).getTime());


    }

    @Override
    public int getItemCount() {
        return completeBooking.size();
    }

    public class CompleteBookingViewHolder extends RecyclerView.ViewHolder {

        TextView name,mobile,address,total_amt,date_of_booking,time_slot;
        public CompleteBookingViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.username);
            mobile = itemView.findViewById(R.id.user_number);
            address = itemView.findViewById(R.id.user_loc);
            total_amt = itemView.findViewById(R.id.total_amt);
            date_of_booking = itemView.findViewById(R.id.date_of_booking);
            time_slot = itemView.findViewById(R.id.time_slot);


        }
    }
}
