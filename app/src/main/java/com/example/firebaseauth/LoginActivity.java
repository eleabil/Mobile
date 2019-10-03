package com.example.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText emailField;
    private TextInputEditText passwordField;
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
                final String email = Objects.requireNonNull(emailField.getText()).toString().trim();
                final String password = Objects.requireNonNull(passwordField.getText()).toString().trim();
                logIn(email, password);
            }
        });
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }

    private void logIn(final String email, final String password) {

        if (isEmailValid(email) && isPasswordValid(password)) {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            } else {
                                error();
                            }
                        }
                    });
        }
    }

    private boolean isEmailValid(final String email) {
        boolean isValid = true;

        if (email.isEmpty()) {
            emailField.setError(getString(R.string.enter_email));
            emailField.requestFocus();
            isValid = false;
        } else {
            emailField.setError(null);
        }

        return isValid;
    }

    private boolean isPasswordValid(final String password) {
        boolean isValid = true;

        if (password.isEmpty() || password.length() < 8) {
            passwordField.setError(getString(R.string.password_limit));
            passwordField.requestFocus();
            isValid = false;
        } else {
            passwordField.setError(null);
        }

        return isValid;
    }

    private void error(){
        Toast.makeText(LoginActivity.this, getString(R.string.login_error), Toast.LENGTH_LONG).show();
    }
}