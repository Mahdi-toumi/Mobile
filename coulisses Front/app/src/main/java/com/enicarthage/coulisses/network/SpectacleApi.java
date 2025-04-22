package com.enicarthage.coulisses.network;

import com.enicarthage.coulisses.models.Spectacle;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SpectacleApi {

    // Tous les spectacles
    @GET("api/spectacle")
    Call<List<Spectacle>> getAllSpectacles();

    // Détail d’un spectacle
    @GET("api/spectacle/{id}")
    Call<Spectacle> getSpectacleById(@Path("id") Long id);
}