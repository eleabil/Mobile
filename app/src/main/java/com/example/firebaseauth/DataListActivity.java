package com.example.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        recyclerViewInit();

        loadPanels();

        swipeToRefresh();

    }

    private void recyclerViewInit(){
        recyclerView = findViewById(R.id.data_list_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadPanels(){
        final ApiService apiService = getApplicationEx().getApiService();
        final Call<List<Panel>> call = apiService.getPanels();

        call.enqueue(new Callback<List<Panel>>() {
            @Override
            public void onResponse(Call<List<Panel>> call, Response<List<Panel>> response) {
                Log.i("successTag", "SUCCESS");
                List<Panel> panels = response.body();

                PanelAdapter adapter = new PanelAdapter(DataListActivity.this, panels);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Panel>> call, Throwable t) {
                Toast.makeText(DataListActivity.this,
                        t.getMessage(), Toast.LENGTH_LONG).show();

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

    private ApplicationEx getApplicationEx(){
        return ((ApplicationEx) getApplication());
    }
}