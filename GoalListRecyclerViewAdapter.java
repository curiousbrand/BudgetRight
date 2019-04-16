package com.example.bpear.budgetright;

import android.app.Activity;
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

public class GoalListRecyclerViewAdapter extends RecyclerView.Adapter<GoalListRecyclerViewAdapter.ViewHolder> {

    private GoalDbHelper db;
private List<Goal> mGoal;
Activity activity;

    public GoalListRecyclerViewAdapter(FragmentActivity activity, List<Goal> mGoal) {
        db = GoalDbHelper.getInstance();
        this.activity = activity;
        this.mGoal = mGoal;
    }

    @Override
    public GoalListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_goal_card, parent, false);


        return new GoalListRecyclerViewAdapter.ViewHolder(v);


    }

    @Override
    public void onBindViewHolder(GoalListRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.currentitem = db.getGoalList().get(position);
        holder.mGoalName.setText(holder.currentitem.getGoalName());
        holder.mGoalTargetDate.setText(holder.currentitem.getDesiredDate());
        holder.mGoalTargetAmount.setText(holder.currentitem.getTargetMoney());
        holder.mGoalSavedAmount.setText(holder.currentitem.getSavedMoney());


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mGoalName;
        public TextView mGoalTargetDate;
        public TextView mGoalTargetAmount;
        public TextView mGoalSavedAmount;
        public Goal currentitem;


        public ViewHolder(View itemView) {
            super(itemView);

            mGoalName = itemView.findViewById(R.id.user_debt_nme);
            mGoalSavedAmount = itemView.findViewById(R.id.savedamount);
            mGoalTargetAmount = itemView.findViewById(R.id.targetamount);
            mGoalTargetDate = itemView.findViewById(R.id.targetDate);


        }
    }


    @Override
    public int getItemCount() {
        return db.getGoalList().size();
    }

    public void removeItem(int position){
        db.getGoalList().remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
}





