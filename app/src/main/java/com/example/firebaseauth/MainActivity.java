package com.example.firebaseauth;

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

public class MainActivity extends AppCompatActivity {

    private EditText emailField, passwordField;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        emailField = findViewById(R.id.signup_activity_email);
        passwordField = findViewById(R.id.signup_activity_password);
        Button signupBtn = findViewById(R.id.signup_activity_signupBtn);
        TextView loginLink = findViewById(R.id.signup_activity_loginLink);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = emailField.getText().toString();
                final String password = passwordField.getText().toString();
                signUp(email, password);
            }
        });

       loginLink.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(MainActivity.this, LoginActivity.class));
           }
       });

    }

    private void signUp(final String email, final String password){
        if(email.isEmpty()){
            emailField.setError("Please enter email");
            emailField.requestFocus();
        }
        else if (password.isEmpty()){
            passwordField.setError("Please enter password");
            passwordField.requestFocus();
        }
        else {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Sign Up is unsuccessful", Toast.LENGTH_LONG).show();
                    }else{
                        startActivity(new Intent(MainActivity.this, HomePageActivity.class));
                    }

                }
            });
        }
    }
}
