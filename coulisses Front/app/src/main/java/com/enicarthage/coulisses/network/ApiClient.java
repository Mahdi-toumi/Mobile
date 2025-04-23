package com.enicarthage.coulisses.network;


import okhttp3.Interceptor;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class ApiClient {
    private static final String BASE_URL = "http://192.168.1.14:8081/"; // 10.0.2.2 = localhost depuis l'émulateur Android   PC = 192.168.1.14
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            // Logger HTTP pour déboguer les requêtes
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            // Intercepteur pour ajouter automatiquement un token si nécessaire (optionnel)
            Interceptor headerInterceptor = chain -> {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .header("Accept", "application/json")
                        .header("Content-Type", "application/json");
                // Vous pouvez ajouter ici un token si vous le gérez côté client
                // requestBuilder.header("Authorization", "Bearer " + token);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            };

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(headerInterceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}