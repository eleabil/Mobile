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

public class LoginActivity extends AppCompatActivity {

    private EditText emailField, passwordField;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        emailField = findViewById(R.id.login_activity_email);
        passwordField = findViewById(R.id.login_activity_password);
        Button loginBtn = findViewById(R.id.login_activity_loginBtn);
        TextView loginLink = findViewById(R.id.login_activity_loginLink);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = emailField.getText().toString();
                final String password = passwordField.getText().toString();
                logIn(email, password);

            }
        });
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }

    private void logIn(final String email, final String password){
        if(email.isEmpty()){
            emailField.setError("Please enter email");
            emailField.requestFocus();
        }
        else if (password.isEmpty()){
            passwordField.setError("Please enter password");
            passwordField.requestFocus();
        }
        else {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                     //   Toast.makeText(LoginActivity.this, "Log In success", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this, "Log In error", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }

}