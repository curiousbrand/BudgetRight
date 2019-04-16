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

public final class DebtDbHelper {
    private static final DebtDbHelper INSTANCE = new DebtDbHelper();
    private static final String TAG = "DebtDbHelper";

    private FirebaseDatabase db;
    private DatabaseReference debtRef;
    private List<Debt> debtList;


    private DebtDbHelper() {
        db = FirebaseDatabase.getInstance();
        debtList = new ArrayList<>();
        debtRef = db.getReference("Debt");
        debtRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                debtList.add(dataSnapshot.getValue(Debt.class));
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

    public static DebtDbHelper getInstance() {
        return INSTANCE;
    }

    public void addDebt(Debt d) {
        debtRef.push().setValue(d);
    }

    public List<Debt> getDebtList() {
        return debtList;
    }

}


