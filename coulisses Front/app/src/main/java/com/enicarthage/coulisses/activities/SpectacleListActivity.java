package com.enicarthage.coulisses.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enicarthage.coulisses.R;
import com.enicarthage.coulisses.adapters.SpectacleAdapter;
import com.enicarthage.coulisses.models.Spectacle;
import com.enicarthage.coulisses.network.ApiClient;
import com.enicarthage.coulisses.network.SpectacleApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpectacleListActivity extends AppCompatActivity implements SpectacleAdapter.OnSpectacleClickListener {

    private RecyclerView recyclerView;
    private SpectacleAdapter adapter;
    private List<Spectacle> spectacleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spectacle_list);

        // Set up toolbar
        setSupportActionBar(findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.spectacleRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SpectacleAdapter(spectacleList, this, this);
        recyclerView.setAdapter(adapter);

        // Fetch spectacles
        fetchSpectacles();
    }

    private void fetchSpectacles() {
        SpectacleApi api = ApiClient.getClient().create(SpectacleApi.class);
        Call<List<Spectacle>> call = api.getAllSpectacles();

        call.enqueue(new Callback<List<Spectacle>>() {
            @Override
            public void onResponse(Call<List<Spectacle>> call, Response<List<Spectacle>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    spectacleList.clear();
                    spectacleList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(SpectacleListActivity.this, "Failed to load spectacles", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Spectacle>> call, Throwable t) {
                Toast.makeText(SpectacleListActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSpectacleClick(Spectacle spectacle) {
        Intent intent = new Intent(this, SpectacleDetailActivity.class);
        intent.putExtra("spectacle", spectacle);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}