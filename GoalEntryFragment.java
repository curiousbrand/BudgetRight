package com.example.bpear.budgetright;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class GoalEntryFragment extends Fragment {
    private Button doneButton, cnclBttn;
    private EditText pickdate, inputGoName, inputGoAmount, inputGoSaved;
    private GoalDbHelper db;
    Toolbar mgtoolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_goal_maker, container, false);

        // Inflate the layout for this fragment


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pickdate = getView().findViewById(R.id.date_pick);
        inputGoAmount = getView().findViewById(R.id.get_goal_amount);
        inputGoName = getView().findViewById(R.id.get_goal_name);
        inputGoSaved = getView().findViewById(R.id.saved_amount);

        mgtoolbar = getView().findViewById(R.id.bill_entry_toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mgtoolbar);
        mgtoolbar.setNavigationIcon(R.drawable.ic_closebutton);
        mgtoolbar.setTitle("Enter New Goal");
        mgtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getContext());

                builder.setTitle("Exit");
                builder.setMessage("Are you sure you want to exit this page?");

                builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.screen_area, new GoalListFragment())
                                .addToBackStack(null)
                                .commit();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                android.support.v7.app.AlertDialog dialog = builder.show();
            }
        });


        pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        db = GoalDbHelper.getInstance();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.entry_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.tool_save:
                String goalName = inputGoName.getText().toString();
                String goalAmount = inputGoAmount.getText().toString();
                String savedAlready = inputGoSaved.getText().toString();

                if (TextUtils.isEmpty(goalName)) {
                    Snackbar.make(getView(), "Please enter a Name for your Goal", Snackbar.LENGTH_LONG).show();

                }

                if (TextUtils.isEmpty(goalAmount)) {
                    Snackbar.make(getView(), "Please enter the amount you need", Snackbar.LENGTH_LONG).show();
                }

                if ((TextUtils.isEmpty(savedAlready))) {
                    Snackbar.make(getView(), "Please enter 0 or more to complete your goal", Snackbar.LENGTH_LONG).show();
                } else {
                    addGoalButton();
                }

        }

        return super.onOptionsItemSelected(item);
    }


    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            pickdate.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1)
                    + "-" + String.valueOf(year));
        }
    };


    public void addGoalButton() {
        Goal g = new Goal();
        g.setGoalName(inputGoName.getText().toString());
        g.setTargetMoney(inputGoAmount.getText().toString());
        g.setSavedMoney(inputGoSaved.getText().toString());
        g.setDesiredDate(pickdate.getText().toString());
        db.addGoal(g);
        clearTextFields();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.screen_area, new DebtListFragment())
                .addToBackStack(null)
                .commit();
        Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
    }

    public void clearTextFields() {
        inputGoName.setText("");
        inputGoSaved.setText("");
        inputGoAmount.setText("");
        pickdate.setText("");
    }
}




