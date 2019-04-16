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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import faranjit.currency.edittext.CurrencyEditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class BillsEntryFragment extends Fragment {
    int day, month, year, money;
    private BigDecimal value;

    FirebaseDatabase db;
    DatabaseReference databaseReference;
    List<Bills> billsList;
    EditText pickdate, inputBillAmount, inputBillDate, inputBillNotes;
    Calendar mCurrentDate;
    Spinner inputBillName;
    Toolbar mtoolbar;
    public BillsEntryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bills_make, container, false);


        Spinner spinner = (Spinner) v.findViewById(R.id.spinner2);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.Type_of_bills, android.R.layout.simple_spinner_item);


        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("Bills");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

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

        inputBillName = (Spinner) getView().findViewById(R.id.spinner2);
        inputBillAmount = (EditText) getView().findViewById(R.id.input_money);
        inputBillDate = (EditText) getView().findViewById(R.id.pick_due_date);
        inputBillNotes = (EditText) getView().findViewById(R.id.input_notes);
        mtoolbar = getView().findViewById(R.id.bill_entry_toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mtoolbar);
        mtoolbar.setNavigationIcon(R.drawable.ic_closebutton);
        mtoolbar.setTitle("Enter New Bill");
        mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getContext());

                builder.setTitle("Exit");
                builder.setMessage("Are you sure you want to exit this page?");

                builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.screen_area, new BillListFragment())
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


        final CurrencyEditText currencyEditText = (CurrencyEditText) getView().findViewById(R.id.input_money);
        currencyEditText.setLocale(new Locale("en", "GB"));

        pickdate = getView().findViewById(R.id.pick_due_date);

        mCurrentDate = Calendar.getInstance();

        day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrentDate.get(Calendar.MONTH);
        year = mCurrentDate.get(Calendar.YEAR);

        month = month + 1;


        pickdate.setText(day + "/" + month + "/" + year);

        pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

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
                String budgetname = inputBillName.getSelectedItem().toString();
                String budgetamount = inputBillAmount.getText().toString();

                // The edittext below is is me declaring them so that the app get the data from the edittext
                // and set them to a string so that i can extract them and pass them into new activity


                Spinner budgname = (Spinner) getView().findViewById(R.id.spinner2);
                EditText budgamount = (EditText) getView().findViewById(R.id.input_money);


//                bdName = budgname.getSelectedItem().toString();
//                bdMoney = budgamount.getText().toString();

                if (TextUtils.isEmpty(budgetname)) {
                    Snackbar.make(getView(), "Please enter a name for the budget", Snackbar.LENGTH_LONG).show();
                }

                if (TextUtils.isEmpty(budgetamount)) {
                    Snackbar.make(getView(), "Please enter the budget amount", Snackbar.LENGTH_LONG).show();
                } else {
                    AddBillButton();
                }

                return true;
        }


        return super.onOptionsItemSelected(item);


    }

    public void addBill(Bills b) {
        databaseReference.push().setValue(b);

    }

    public List<Bills> getBillsList() {
        return billsList;
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

            pickdate.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1) + "-" + String.valueOf(year));
        }
    };


    public void AddBillButton() {
        Bills b = new Bills();

        b.setBillName(inputBillName.getSelectedItem().toString());
        b.setDueAmount(inputBillAmount.getText().toString());
        b.setDueDate(inputBillDate.getText().toString());
        b.setNotes(inputBillNotes.getText().toString());
        addBill(b);
        clearTextFields();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.screen_area, new BillListFragment())
                .addToBackStack(null)
                .commit();
        Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
    }

    public void clearTextFields() {
        inputBillAmount.setText("");
        inputBillDate.setText("");
        inputBillNotes.setText("");
    }
}




