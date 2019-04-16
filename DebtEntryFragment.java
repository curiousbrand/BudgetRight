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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class DebtEntryFragment extends Fragment {

    private final String TAG = "DebtList";
    Button cnclBttn, dnBttn;
    int day, month, year;
    Calendar mCurrentDate;
    Spinner inputType;
    Toolbar mdtoolbar;
    EditText inputPayee, inputAmont, pickstartdate, payBackDate;
    private DebtDbHelper db;

    public DebtEntryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_debt_entry, container, false);


        Spinner spinner = (Spinner) view.findViewById(R.id.debtspinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.Type_of_loans, android.R.layout.simple_spinner_item);


        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        inputAmont = view.findViewById(R.id.amount_owed);
        inputPayee = view.findViewById(R.id.to_who);
        inputType = view.findViewById(R.id.debtspinner);
        payBackDate = view.findViewById(R.id.payback_date);
        mCurrentDate = Calendar.getInstance();
        mdtoolbar = getView().findViewById(R.id.bill_entry_toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mdtoolbar);
        mdtoolbar.setNavigationIcon(R.drawable.ic_closebutton);
        mdtoolbar.setTitle("Enter New Debt");
        mdtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getContext());

                builder.setTitle("Exit");
                builder.setMessage("Are you sure you want to exit this page?");

                builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.screen_area, new DebtListFragment())
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

        payBackDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });



        db = DebtDbHelper.getInstance();

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
                String debtPayee = inputPayee.getText().toString();
                String debtOwed = inputAmont.getText().toString();


                if (TextUtils.isEmpty(debtOwed)) {
                    Snackbar.make(getView(), "Please Enter the amount owed.", Snackbar.LENGTH_LONG).show();

                }

                if (TextUtils.isEmpty(debtPayee)) {
                    Snackbar.make(getView(), "Please Enter Payee Name", Snackbar.LENGTH_LONG).show();
                } else {
                    addDebtButton();
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

            payBackDate.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1) + "-" + String.valueOf(year));

        }
    };


    public void addDebtButton() {
        Debt d = new Debt();
        d.setPayee(inputPayee.getText().toString());
        d.setDueDebtAmount(inputAmont.getText().toString());
        d.setDnotes(inputType.getSelectedItem().toString());
        d.setPaybackDate(payBackDate.getText().toString());
        db.addDebt(d);
        clearTextFields();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.screen_area, new DebtListFragment())
                .addToBackStack(null)
                .commit();
        Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
    }

    public void clearTextFields() {
        inputPayee.setText("");
        inputAmont.setText("");
        payBackDate.setText("");
    }

}
