package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText emailId, passwrd;
    Button btnSignIn;
    TextView tvsignUp;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editTextTextEmailAddress);
        passwrd = findViewById(R.id.editTextTextPassword);
        btnSignIn = findViewById(R.id.button3);
        tvsignUp = findViewById(R.id.textViewS);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(LoginActivity.this, "You are Logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, StartActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this, "Please Login", Toast.LENGTH_SHORT).show();

                }

            }
        };
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailId.getText().toString();
                String pwd = passwrd.getText().toString();
                if (email.isEmpty()) {
                    emailId.setError("Provide an Email");
                    emailId.requestFocus();

                } else if (pwd.isEmpty()) {
                    passwrd.setError("Enter A Password");
                    passwrd.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "fields are empty!", Toast.LENGTH_LONG).show();

                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Log.d("hhhh", task.getException().getLocalizedMessage());
                                Toast.makeText(LoginActivity.this, "Login Error,Try Again", Toast.LENGTH_LONG).show();
                            } else {
                                Intent intToHome = new Intent(LoginActivity.this, StartActivity.class);
                                startActivity(intToHome);
                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "Error Occured!", Toast.LENGTH_LONG).show();
                }


            }


        });
        tvsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(LoginActivity.this,MainActivitydb.class);
                startActivity(intSignUp);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}

