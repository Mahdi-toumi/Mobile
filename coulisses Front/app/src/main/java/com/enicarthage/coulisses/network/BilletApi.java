package com.enicarthage.coulisses.network;

import com.enicarthage.coulisses.models.Billet;
import com.enicarthage.coulisses.models.Lieu;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BilletApi {

    // Tous les Billets
    @GET("api/billet")
    Call<List<Billet>> getAllBillets();

    // Détail d’un Lieu
    @GET("api/billet/{id}")
    Call<Billet> getBilletById(@Path("id") Long id);

    // Détail d’un Lieu
    @GET("api/billet/spectacle/{spectacleId}")
    Call<List<Billet>> getBilletBySpectacleId(@Path("spectacleId") Long id);
}
