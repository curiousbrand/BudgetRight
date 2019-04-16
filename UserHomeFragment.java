package com.example.bpear.budgetright;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserHomeFragment extends Fragment {


    Toolbar mtoolbar;
    private FirebaseAuth auth;
    private TextView bills, debts, goals;
    private TextView nameFragTxt;
    DrawerLayout mDrawerLayout;
    public UserHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_home, container, false);


        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        mtoolbar = getView().findViewById(R.id.home_toolbar);
        mtoolbar.setNavigationIcon(R.drawable.ic_open_nav);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mtoolbar);
        mtoolbar.setTitle("Home");


        bills = getView().findViewById(R.id.category_link);
        bills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.screen_area, new BillListFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        debts = getView().findViewById(R.id.category_link2);
        debts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.screen_area, new DebtListFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        goals = getView().findViewById(R.id.category_link3);
        goals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.screen_area, new GoalListFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }


}





