package com.example.bpear.budgetright;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GoalListFragment extends Fragment {
    RecyclerView recyclerView;
    GoalListRecyclerViewAdapter Gadapter;
    List<Goal> mGoal = new ArrayList<>();
    ProgressDialog progress;
    Toolbar mtoolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_goal, container, false);
        mtoolbar = view.findViewById(R.id.goal_list_toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mtoolbar);
        mtoolbar.setTitle("Goals");

        FloatingActionButton fb = view.findViewById(R.id.addGl);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.screen_area, new GoalEntryFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.goal_list);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(Gadapter);
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                //get position which is swipe

                if (direction == ItemTouchHelper.LEFT) {
                    //Remove swiped item from list and notify the RecyclerView
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext()); //alert for confirm to delete
                    builder.setMessage("Are you sure you want to delete this item?");    //set message

                    builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() { //when click on DELETE
                        @Override
                        public void onClick(DialogInterface dialog, int position) {
                            //remove the item from the recyclerview
                            Gadapter.removeItem(viewHolder.getAdapterPosition());
                            Gadapter.notifyDataSetChanged();
                            Snackbar.make(getView(), " Goal removed from list!", Snackbar.LENGTH_LONG).show();

                            return;
                        }
                    }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {  //not removing items if cancel is done
                        @Override
                        public void onClick(DialogInterface dialog, int position) {
                            return;
                        }
                    }).show();  //show alert dialog
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView); //set swipe to recylcerview
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        progress = new ProgressDialog(getActivity());
        progress.setTitle("Loading Goals");
        progress.setCancelable(false);
        progress.show();

    }




    @Override
    public void onStart() {
        super.onStart();

        DatabaseReference databasegoal = FirebaseDatabase.getInstance().getReference().child("Goal");
        databasegoal.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mGoal.clear();


                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    //getting bills
                    //Bills bills = child.getValue(Bills.class);
                    mGoal.add(child.getValue(Goal.class));
                }
                progress.dismiss();
                if (getActivity() != null)
                    getActivity().runOnUiThread(() -> Gadapter.notifyDataSetChanged());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    private void init() {
        recyclerView = (RecyclerView) getView().findViewById(R.id.goal_list);
        Gadapter = new GoalListRecyclerViewAdapter(getActivity(), mGoal);
        recyclerView.setAdapter(Gadapter);

    }

}
