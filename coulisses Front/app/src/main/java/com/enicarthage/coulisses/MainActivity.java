package com.enicarthage.coulisses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.enicarthage.coulisses.activities.ListeSpectaclesActivity;
import com.enicarthage.coulisses.activities.SplashActivity;
import com.enicarthage.coulisses.models.Spectacle;
import com.enicarthage.coulisses.network.ApiClient;
import com.enicarthage.coulisses.network.SpectacleApi;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SpectacleApi spectacleApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // Layout vide pour l’instant

            startActivity(new Intent(MainActivity.this, SplashActivity.class));

        // Initialiser Retrofit
        spectacleApi = ApiClient.getClient().create(SpectacleApi.class);

        // Appel API pour tester
        testGetAllSpectacles();
    }

    private void testGetAllSpectacles() {
        Call<List<Spectacle>> call = spectacleApi.getAllSpectacles();

        call.enqueue(new Callback<List<Spectacle>>() {
            @Override
            public void onResponse(Call<List<Spectacle>> call, Response<List<Spectacle>> response) {
                if (response.isSuccessful()) {
                    List<Spectacle> spectacles = response.body();
                    Log.d(TAG, "Spectacles reçus : " + spectacles.size());

                    for (Spectacle s : spectacles) {
                        Log.d(TAG, "Titre: " + s.getTitre() + " | Lieu: " + s.getLieu().getNom());
                    }

                    Toast.makeText(MainActivity.this, "API OK : " + spectacles.size() + " spectacles", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG, "Erreur API: " + response.code());
                    Toast.makeText(MainActivity.this, "Erreur API: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Spectacle>> call, Throwable t) {
                Log.e(TAG, "Échec de connexion: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Connexion échouée", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
