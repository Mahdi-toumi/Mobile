package com.enicarthage.coulisses.network;

import com.enicarthage.coulisses.models.Lieu;
import com.enicarthage.coulisses.models.Spectacle;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LieuApi {

    // Tous les Lieux
    @GET("api/lieu")
    Call<List<Lieu>> getAllLieux();

    // Détail d’un Lieu
    @GET("api/lieu/{id}")
    Call<Lieu> getLieuById(@Path("id") Long id);
}