package com.example.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        Button logOutBtn = findViewById(R.id.home_activity_logoutBtn);
        Button nextBtn = findViewById(R.id.home_activity_nextBtn);
        final TextView name = findViewById(R.id.home_activity_name);

        final FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            name.setText(user.getDisplayName());
        } else {
            error();
        }

                logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                startActivity(new Intent(HomeActivity.this, SignUpActivity.class));
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, DataListActivity.class));
            }
        });
    }

    private void error(){
        Toast.makeText(HomeActivity.this, getString(R.string.error), Toast.LENGTH_LONG).show();
    }
}
