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

        loadMovies();
    }

    private void loadMovies(){
        final ApiService apiService = getApplicationEx().getApiService();
        final Call<List<Movie>> call = apiService.getMovies();

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                Log.i("successTag", "SUCCESS");
                List<Movie> movies = response.body();

                showMoviesInTextView(Objects.requireNonNull(movies));
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(DataListActivity.this,
                        t.getMessage(), Toast.LENGTH_LONG).show();

                Log.i("myInfo", "FAILURE");
            }
        });
    }

    private void showMoviesInTextView(List<Movie> movies){
        for (Movie movie: movies){
            String content = "";
            content += "Title: " + movie.getTitle() + "\n";
            content += "Year: " + movie.getYear() + "\n";
            content += "Description: " + movie.getText() +  "\n\n";
            textViewResult.append(content);
        }
    }

    private ApplicationEx getApplicationEx(){
        return ((ApplicationEx) getApplication());
    }
}