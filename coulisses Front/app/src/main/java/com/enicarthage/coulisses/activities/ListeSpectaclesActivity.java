package com.enicarthage.coulisses.activities;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enicarthage.coulisses.R;
import com.enicarthage.coulisses.adapters.SpectacleAdapter;
import com.enicarthage.coulisses.models.Spectacle;
import com.enicarthage.coulisses.network.SpectacleApi;
import com.enicarthage.coulisses.network.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.enicarthage.coulisses.R;

public class ListeSpectaclesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SpectacleAdapter spectacleAdapter;
    private List<Spectacle> spectacleList;
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_spectacles);

        // Initialisation de la RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialisation du filtre de recherche
        searchEditText = findViewById(R.id.search_input);

        // Appel à l'API pour récupérer les spectacles
        fetchSpectaclesFromApi();

        // Mettre en place le listener pour le champ de recherche
        searchEditText.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                spectacleAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(android.text.Editable editable) {}
        });
    }

    // Fonction pour récupérer les spectacles depuis l'API
    private void fetchSpectaclesFromApi() {
        SpectacleApi apiService = ApiClient.getClient().create(SpectacleApi.class);
        Call<List<Spectacle>> call = apiService.getAllSpectacles();

        // Appel à l'API
        call.enqueue(new Callback<List<Spectacle>>() {
            @Override
            public void onResponse(Call<List<Spectacle>> call, Response<List<Spectacle>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    spectacleList = response.body(); // Récupère les spectacles
                    spectacleAdapter = new SpectacleAdapter(ListeSpectaclesActivity.this, spectacleList);
                    recyclerView.setAdapter(spectacleAdapter); // Met à jour l'adaptateur avec les données récupérées
                } else {
                    Toast.makeText(ListeSpectaclesActivity.this, "Erreur dans la récupération des spectacles.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Spectacle>> call, Throwable t) {
                Toast.makeText(ListeSpectaclesActivity.this, "Erreur de connexion : " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}