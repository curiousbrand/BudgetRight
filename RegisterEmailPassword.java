package com.example.bpear.budgetright;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterEmailPassword extends AppCompatActivity {

    private Button createaccnt, signin;
    private EditText emailField;
    private EditText passwordField;
    private EditText usernameField;
    private EditText nameField;
    String Name;
    boolean EditTextStatus;
    //START declaring_auth
    private FirebaseAuth mAuth;
    // END declaring_auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_email_password);

        // Views
        emailField = findViewById(R.id.Email);
        passwordField = findViewById(R.id.createPassword);
        nameField = findViewById(R.id.fullName);
        usernameField = findViewById(R.id.getuserName);
        createaccnt = findViewById(R.id.createAccount);
        signin = findViewById(R.id.signIn);


        //START initialize auth
        mAuth = FirebaseAuth.getInstance();

        createaccnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calling method to check EditText is empty or no status.
                CheckEditTextIsEmptyOrNot();

                // If EditText is true then this block with execute.
                if (EditTextStatus) {

                    // If EditText is not empty than UserRegistrationFunction method will call.
                    UserRegister();


                }
                // If EditText is false then this block with execute.
                else {

                    Toast.makeText(RegisterEmailPassword.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterEmailPassword.this, LoginPage.class));
            }
        });
    }


    //STart check user
    @Override
    public void onStart() {
        super.onStart();
        //check if user is signed in (non null and update UI accordingly
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

    }


    public void UserRegister() {


        mAuth.createUserWithEmailAndPassword(emailField.getText().toString(), passwordField.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //Sign in success, update UI with the signed in users information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegisterEmailPassword.this, "Account Created",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(user);


                            Intent mainPageIntent = new Intent(RegisterEmailPassword.this, NavigationDrActivity.class);
                            mainPageIntent.putExtra("Full Name", Name);
                            startActivity(mainPageIntent);


                        } else {
                            //if Sign in fails, display a message to the user .
                            Log.w("UserWithEmail:Failure", task.getException());
                            Toast.makeText(RegisterEmailPassword.this, "Authentication Failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }


    public void CheckEditTextIsEmptyOrNot() {

        // Getting name and email from EditText and save into string variables.
        String email = emailField.getText().toString().trim();
        String passwrd = passwordField.getText().toString().trim();
        String fllname = nameField.getText().toString().trim();
        String usrnam = usernameField.getText().toString().trim();


        if (TextUtils.isEmpty(email) || Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailField.setError("Please Enter A Valid Email");
        }

        if (TextUtils.isEmpty(passwrd) || passwrd.length() < 5) {
            passwordField.setError("Please Enter 5 or more characters");
        }
        if (TextUtils.isEmpty(fllname) || fllname.length() > 25) {
            nameField.setError("Please Enter A Valid Name");
        }
        if (TextUtils.isEmpty(usrnam)) {
            usernameField.setError("Please Enter a UserName");
        } else {

            EditTextStatus = true;
        }
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {

        }
    }
}



