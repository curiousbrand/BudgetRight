package com.example.bpear.budgetright;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bpear on 5/3/2018.
 */

public class DebtListRecyclerViewAdapter extends RecyclerView.Adapter<DebtListRecyclerViewAdapter.ViewHolder> {

    private DebtDbHelper db;
    private List<Debt> mDebts;
    Activity activity;

    public DebtListRecyclerViewAdapter(FragmentActivity activity, List<Debt> mDebts) {
        db = DebtDbHelper.getInstance();
        this.activity = activity;
        this.mDebts = mDebts;
    }

    @Override
    public DebtListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_debt_card, parent, false);


        return new DebtListRecyclerViewAdapter.ViewHolder(v);


    }

    @Override
    public void onBindViewHolder(DebtListRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.currentitem = db.getDebtList().get(position);

        holder.mPayee.setText(holder.currentitem.getPayee());
        holder.mDebtAmount.setText(holder.currentitem.getDueDebtAmount());
        holder.mDebtTargetDate.setText(holder.currentitem.getPaybackDate());
        holder.mDNotes.setText(holder.currentitem.getNotes());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mUserName;
        public TextView mPayee;
        public TextView mDebtTargetDate;
        public TextView mDebtAmount;
        public TextView mDNotes;
        public Debt currentitem;


        public ViewHolder(View itemView) {
            super(itemView);

            mUserName = itemView.findViewById(R.id.user_debt_nme);
            mPayee = itemView.findViewById(R.id.payee_name);
            mDebtAmount = itemView.findViewById(R.id.debtowed);
            mDebtTargetDate = itemView.findViewById(R.id.targetpayBackDate);
            mDNotes = itemView.findViewById(R.id.Debtnotes);


        }
    }


    @Override
    public int getItemCount() {
        return db.getDebtList().size();
    }

    public void removeItem(int position){
        db.getDebtList().remove(position);
        //Notify the item removed by the position
        notifyItemRemoved(position);
    }

}
