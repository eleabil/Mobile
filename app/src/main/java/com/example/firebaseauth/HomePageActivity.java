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

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button logOutBtn = findViewById(R.id.home_activity_logoutBtn);
        TextView name = findViewById(R.id.home_activity_name);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user!=null){
            name.setText(user.getEmail());
        }else {
            Toast.makeText(HomePageActivity.this, "Error", Toast.LENGTH_LONG).show();
        }

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomePageActivity.this, MainActivity.class));
            }
        });
    }
}
