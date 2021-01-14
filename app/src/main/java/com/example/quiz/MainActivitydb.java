package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivitydb extends AppCompatActivity {
    EditText emailId,passwrd;
    Button btnSignUp;
    TextView tvsignIn;
    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activitydb);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editText);
        passwrd = findViewById(R.id.editText2);
        btnSignUp = findViewById(R.id.button3);
        tvsignIn = findViewById(R.id.textView);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(MainActivitydb.this, "fields are empty!", Toast.LENGTH_LONG).show();
                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivitydb.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivitydb.this, "SignUp Unsuccessful,please try again!", Toast.LENGTH_LONG).show();

                            } else {
                                startActivity(new Intent(MainActivitydb.this, StartActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(MainActivitydb.this, "Error Occured!", Toast.LENGTH_LONG).show();
                }


            }
        });
        tvsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivitydb.this,LoginActivity.class);
                        startActivity(i);

            }
        });
    }
}