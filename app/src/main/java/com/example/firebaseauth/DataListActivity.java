package com.example.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.textview.MaterialTextView;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataListActivity extends AppCompatActivity {

    private MaterialTextView textViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        textViewResult = findViewById(R.id.text_view_result);

        loadPanels();
    }

    private void loadPanels(){
        final ApiService apiService = getApplicationEx().getApiService();
        final Call<List<Panel>> call = apiService.getPanels();

        call.enqueue(new Callback<List<Panel>>() {
            @Override
            public void onResponse(Call<List<Panel>> call, Response<List<Panel>> response) {
                Log.i("successTag", "SUCCESS");
                List<Panel> panels = response.body();

                showPanelsInTextView(Objects.requireNonNull(panels));
            }

            @Override
            public void onFailure(Call<List<Panel>> call, Throwable t) {
                Toast.makeText(DataListActivity.this,
                        t.getMessage(), Toast.LENGTH_LONG).show();

                Log.i("myInfo", "FAILURE");
            }
        });
    }

    private void showPanelsInTextView(List<Panel> panels){
        for (Panel panel : panels){
            String content = "";
            content += "Panel Type: " + panel.getPanelType() + "\n";
            content += "Power: " + panel.getPower() + "\n";
            content += "Usage Period: " + panel.getUsagePeriod() +  "\n\n";
            textViewResult.append(content);
        }
    }

    private ApplicationEx getApplicationEx(){
        return ((ApplicationEx) getApplication());
    }
}