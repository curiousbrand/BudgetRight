/*

package com.example.bpear.budgetright;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


*/
/**
 * Created by bpear on 5/1/2018.
 *//*



public final class BillsDbHelper {
    private static final BillsDbHelper INSTANCE = new BillsDbHelper();
    private static final String TAG = "BillsDbHelper";

    public FirebaseDatabase db;
    public  DatabaseReference billsRef;
    public  List<Bills> billsList;


    public BillsDbHelper() {
        db = FirebaseDatabase.getInstance();
        billsList = new ArrayList<>();
        billsRef = db.getReference("Bills");
        billsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                billsList.add(dataSnapshot.getValue(Bills.class));
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
            public void onC `ancelled(DatabaseError databaseError) {

            }
        });
    }

    public static BillsDbHelper getInstance() {
        return INSTANCE;
    }

    public void addBill(Bills b) {
        billsRef.push().setValue(b);
    }

    public List<Bills> getBillsList() {
        return billsList;
    }

}

*/
