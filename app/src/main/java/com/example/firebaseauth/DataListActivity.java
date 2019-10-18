package com.example.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    LinearLayout linearLayout;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        fieldsInit();
        loadPanels();
        swipeToRefresh();
    }

    private void fieldsInit(){
        recyclerView = findViewById(R.id.data_list_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        linearLayout = findViewById(R.id.linearLayout);
        spinner = findViewById(R.id.progressBar1);
    }

    private void loadPanels(){
        final ApiService apiService = getApplicationEx().getApiService();
        final Call<List<Panel>> call = apiService.getPanels();

        call.enqueue(new Callback<List<Panel>>() {
            @Override
            public void onResponse(Call<List<Panel>> call, Response<List<Panel>> response) {
                Log.i("successTag", "SUCCESS");
                spinner.setVisibility(View.GONE);
                List<Panel> panels = response.body();

                PanelAdapter adapter = new PanelAdapter(DataListActivity.this, panels);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Panel>> call, Throwable t) {
                showSnackbar();
                Log.i("myInfo", "FAILURE");
            }
        });
    }

    private void swipeToRefresh(){
        swipeRefreshLayout = findViewById(R.id.data_list_swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        loadPanels();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
    }

    private void showSnackbar(){
        Snackbar snackbar = Snackbar
                .make(linearLayout, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadPanels();
                    }
                });
        snackbar.setActionTextColor(ContextCompat.getColor(DataListActivity.this, R.color.colorPrimaryLight));
        snackbar.show();
    }

    private ApplicationEx getApplicationEx(){
        return ((ApplicationEx) getApplication());
    }


}