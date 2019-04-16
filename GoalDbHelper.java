package com.example.bpear.budgetright;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bpear on 5/3/2018.
 */

public final class GoalDbHelper {
    private static final GoalDbHelper INSTANCE = new GoalDbHelper();
    private static final String TAG = "GoalDbHelper";

    private FirebaseDatabase db;
    private DatabaseReference goalRef;
    private List<Goal> goalList;


    private GoalDbHelper() {
        db = FirebaseDatabase.getInstance();
        goalList = new ArrayList<>();
        goalRef = db.getReference("Goal");
        goalRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                goalList.add(dataSnapshot.getValue(Goal.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static GoalDbHelper getInstance() {
        return INSTANCE;
    }

    public void addGoal(Goal g) {
        goalRef.push().setValue(g);
    }

    public List<Goal> getGoalList() {
        return goalList;
    }

}




