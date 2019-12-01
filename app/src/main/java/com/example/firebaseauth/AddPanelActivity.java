package com.example.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPanelActivity extends AppCompatActivity {

    private TextInputLayout panelTypeFieldLayout;
    private TextInputLayout powerFieldLayout;
    private TextInputLayout capacityFieldLayout;
    private TextInputLayout addressFieldLayout;
    private TextInputEditText panelTypeField;
    private TextInputEditText powerField;
    private TextInputEditText capacityField;
    private TextInputEditText addressField;
    private Button addBtn;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_panel);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        initViews();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String model = Objects.requireNonNull(panelTypeField.getText()).toString().trim();
                String power = Objects.requireNonNull(powerField.getText()).toString().trim();
                String capacity = Objects.requireNonNull(capacityField.getText()).toString().trim();
                String address = Objects.requireNonNull(addressField.getText()).toString().trim();
                if (isDataValid(model, power, capacity, address)){
                    Panel panel = new Panel(model, power, capacity, address);
                    addPanel(panel);
                }
            }
        });
    }

    private void addPanel(Panel panel){
        ApiService apiService = getApplicationEx().getApiService();
        Call<Panel> call = apiService.createPanel(panel);
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<Panel>() {
            @Override
            public void onResponse(Call<Panel> call, Response<Panel> response) {
                progressBar.setVisibility(View.INVISIBLE);
                startActivity(new Intent(AddPanelActivity.this, MainActivity.class));
            }

            @Override
            public void onFailure(Call<Panel> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                startActivity(new Intent(AddPanelActivity.this, MainActivity.class));
            }
        });
    }

    private void initViews() {
        panelTypeFieldLayout = findViewById(R.id.add_panel_layout_panel_type);
        powerFieldLayout = findViewById(R.id.add_panel_layout_power);
        capacityFieldLayout = findViewById(R.id.add_panel_layout_capacity);
        addressFieldLayout = findViewById(R.id.add_panel_layout_address);
        panelTypeField = findViewById(R.id.add_panel_activity_panel_type);
        powerField = findViewById(R.id.add_panel_activity_power);
        capacityField = findViewById(R.id.add_panel_activity_capacity);
        addressField = findViewById(R.id.add_panel_activity_address);
        addBtn = findViewById(R.id.add_panel_activity_addBtn);
        progressBar = findViewById(R.id.progress_bar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(AddPanelActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private ApplicationEx getApplicationEx(){
        return ((ApplicationEx) Objects.requireNonNull(this.getApplication()));
    }

    private boolean isDataValid(String model, String power, String capacity, String address){
        boolean modelValid = isModelValid(model);
        boolean powerValid = isPowerValid(power);
        boolean capacityValid = isCapacityValid(capacity);
        boolean addressValid = isAddressValid(address);

        return modelValid && powerValid && capacityValid && addressValid;
    }

    private boolean isAddressValid(String address) {
        if (address.isEmpty()){
            addressFieldLayout.setError(getString(R.string.required));
            addressFieldLayout.requestFocus();
            return false;
        } else {
            addressFieldLayout.setError(null);
            return true;
        }
    }

    private boolean isCapacityValid(String capacity) {
        if (capacity.isEmpty()){
            capacityFieldLayout.setError(getString(R.string.required));
            capacityFieldLayout.requestFocus();
            return false;
        } else {
            capacityFieldLayout.setError(null);
            return true;
        }
    }

    private boolean isPowerValid(String power) {
        if (power.isEmpty()){
            powerFieldLayout.setError(getString(R.string.required));
            powerFieldLayout.requestFocus();
            return false;
        } else {
            powerFieldLayout.setError(null);
            return true;
        }
    }

    private boolean isModelValid(String model) {
        if (model.isEmpty()){
            panelTypeFieldLayout.setError(getString(R.string.required));
            panelTypeFieldLayout.requestFocus();
            return false;
        } else {
            panelTypeFieldLayout.setError(null);
            return true;
        }
    }
}