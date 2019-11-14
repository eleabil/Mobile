package com.example.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Objects;

public class PanelDetailsActivity extends AppCompatActivity {

    private TextView panelType;
    private TextView power;
    private TextView capacity;
    private TextView usagePeriod;
    private TextView address;
    private ImageView imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_details);

        getIncomingIntent();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("panel_type") &&
                getIntent().hasExtra("power") &&
                getIntent().hasExtra("capacity") &&
                getIntent().hasExtra("usage_period") &&
                getIntent().hasExtra("address")) {
            String panelTypeInfo = getIntent().getStringExtra("panel_type");
            String powerInfo = getIntent().getStringExtra("power");
            String capacityInfo = getIntent().getStringExtra("capacity");
            String usagePeriodInfo = getIntent().getStringExtra("usage_period");
            String addressInfo = getIntent().getStringExtra("address");
            String imageUrlInfo = getIntent().getStringExtra("image");

            setInfo(panelTypeInfo, powerInfo, capacityInfo, usagePeriodInfo, addressInfo, imageUrlInfo);
        }
    }

    private void initViews(){
        panelType = findViewById(R.id.panel_details_type);
        power = findViewById(R.id.panel_details_power);
        capacity = findViewById(R.id.panel_details_capacity);
        usagePeriod = findViewById(R.id.panel_details_usage_period);
        address = findViewById(R.id.panel_details_address);
        imageUrl = findViewById(R.id.panel_details_image_view);
    }

    private void setInfo(String panelTypeInfo, String powerInfo, String capacityInfo, String usagePeriodInfo, String addressInfo, String imageUrlInfo) {
        initViews();
        panelType.setText(panelTypeInfo);
        power.setText(powerInfo);
        capacity.setText(capacityInfo);
        usagePeriod.setText(usagePeriodInfo);
        address.setText(addressInfo);
        Picasso.get().load(imageUrlInfo).into(imageUrl);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(PanelDetailsActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}