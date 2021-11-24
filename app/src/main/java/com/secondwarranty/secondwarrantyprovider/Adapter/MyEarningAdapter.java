package com.secondwarranty.secondwarrantyprovider.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.secondwarranty.secondwarrantyprovider.R;
import com.secondwarranty.secondwarrantyprovider.ResponseModel.EarningClass.MyearningsDatum;

import java.util.List;

public class MyEarningAdapter extends RecyclerView.Adapter<MyEarningAdapter.EarningBookingViewHolder> {

    List<MyearningsDatum> myearningsData;
    Context context;

    public MyEarningAdapter(List<MyearningsDatum> myearningsData, Context context) {
        this.myearningsData = myearningsData;
        this.context = context;
    }

    @NonNull
    @Override
    public MyEarningAdapter.EarningBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_my_earnings, parent, false);
        MyEarningAdapter.EarningBookingViewHolder earningBookingViewHolder = new MyEarningAdapter.EarningBookingViewHolder(view);
        return earningBookingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyEarningAdapter.EarningBookingViewHolder holder, int position) {

        holder.txt_job_id.setText("Job ID : " + myearningsData.get(position).getSequenceOrderId());
        holder.service_name.setText(myearningsData.get(position).getSubCategoryName());
        holder.txt_amount_paid.setText(" ₹ " + myearningsData.get(position).getMinCharge());
        holder.job_details.setText(myearningsData.get(position).getServiceType());
        holder.date_of_booking.setText(myearningsData.get(position).getDate());
        holder.time_slot.setText(myearningsData.get(position).getTime());


    }

    @Override
    public int getItemCount() {
        return myearningsData.size();
    }

    public class EarningBookingViewHolder extends RecyclerView.ViewHolder {
        TextView service_name, txt_job_id, txt_amount_paid, job_details, date_of_booking, time_slot;

        public EarningBookingViewHolder(@NonNull View itemView) {
            super(itemView);

            service_name = itemView.findViewById(R.id.service_name);
            txt_job_id = itemView.findViewById(R.id.txt_job_id);
            txt_amount_paid = itemView.findViewById(R.id.txt_amount_paid);
            job_details = itemView.findViewById(R.id.job_details);
            date_of_booking = itemView.findViewById(R.id.date_of_booking);
            time_slot = itemView.findViewById(R.id.time_slot);

        }
    }
}
