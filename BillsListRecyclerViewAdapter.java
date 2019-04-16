
package com.example.bpear.budgetright;

import android.app.Activity;
import android.content.ClipData;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


/**
 * Created by bpear on 4/22/2018.
 */


public class BillsListRecyclerViewAdapter extends RecyclerView.Adapter<BillsListRecyclerViewAdapter.ViewHolder> {

    private FirebaseDatabase db;

    public List<Bills> getBills;
    Activity activity;


    public List<Bills> getGetBills() {
        return getBills;
    }

    public BillsListRecyclerViewAdapter(FragmentActivity activity, List<Bills> getBills) {
        db = FirebaseDatabase.getInstance();
        this.activity = activity;
        this.getBills = getBills;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_bills_card, parent, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.currentitem = getBills.get(position);

        holder.mBillName.setText(holder.currentitem.getBillName());
        holder.mBillDueAmount.setText(holder.currentitem.getDueAmount());
        holder.mBillDueDate.setText(holder.currentitem.getDueDate());
        holder.mBillNotes.setText(holder.currentitem.getNotes());


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mBillName;
        public TextView mBillDueDate;
        public TextView mBillDueAmount;
        public TextView mBillNotes;
        public Bills currentitem;


        public ViewHolder(View itemView) {
            super(itemView);

            mBillName = itemView.findViewById(R.id.list_bill_name);
            mBillDueAmount = itemView.findViewById(R.id.dueAmount);
            mBillDueDate = itemView.findViewById(R.id.dueDate);
            mBillNotes = itemView.findViewById(R.id.notes);


        }
    }


    @Override
    public int getItemCount() {
        return getBills.size();

    }

    public void removeItem(int position){
        getBills.remove(position);
        //Notify the item removed by the position
        notifyDataSetChanged();
       notifyItemRemoved(position);
    }



}




