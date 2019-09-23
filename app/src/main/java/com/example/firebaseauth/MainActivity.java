package com.example.firebaseauth;

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
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity {

    private EditText emailField, passwordField, usernameField, phoneField;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        emailField = findViewById(R.id.signup_activity_email);
        passwordField = findViewById(R.id.signup_activity_password);
        usernameField = findViewById(R.id.signup_activity_username);
        phoneField = findViewById(R.id.signup_activity_phone);
        Button signupBtn = findViewById(R.id.signup_activity_signupBtn);
        TextView loginLink = findViewById(R.id.signup_activity_loginLink);


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = emailField.getText().toString();
                final String password = passwordField.getText().toString();
                final String username = usernameField.getText().toString();
                final String phone = phoneField.getText().toString();
                signUp(email, password, username, phone);
            }
        });

       loginLink.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(MainActivity.this, LoginActivity.class));
           }
       });

    }

    private void signUp(final String email, final String password, final String username, final String phone){

        if (!isValid(email, password, username, phone)){
            Log.i("Tag", "Validation error");
        } else {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Sign Up is unsuccessful", Toast.LENGTH_LONG).show();
                    }else{
                        onCompleteSuccess(username);
                    }

                }
            });
        }
    }

    public boolean isValid(final String email, final String password, final String username, final String phone){
        boolean isValid = true;

        if (!username.matches("^[A-Za-z]$")) {
            usernameField.setError("Please enter username");
            usernameField.requestFocus();
            isValid = false;
        } else {
            usernameField.setError(null);
        }

        if (phone.isEmpty() || (!phone.matches("^[0-9]$") && phone.length() < 10)) {
            phoneField.setError("Please enter valid phone number");
            phoneField.requestFocus();
            isValid = false;
        } else {
            phoneField.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailField.setError("Please enter a valid email");
            emailField.requestFocus();
            isValid = false;
        } else {
            emailField.setError(null);
        }

        if (password.isEmpty() || password.length() < 8) {
            passwordField.setError("Minimum 8 characters");
            passwordField.requestFocus();
            isValid = false;
        } else {
            passwordField.setError(null);
        }

        return isValid;
    }

    private void onCompleteSuccess(final String username) {
        FirebaseUser user = auth.getCurrentUser();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(username).build();


        if (user != null) {
            user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                MainActivity.this.startActivity(new Intent(MainActivity.this, HomePageActivity.class));
                            }
                        }
                    });
        }
    }

}


