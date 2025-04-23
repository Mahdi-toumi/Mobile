package com.enicarthage.coulisses;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.enicarthage.coulisses.models.*;
import com.enicarthage.coulisses.network.ApiClient;
import com.enicarthage.coulisses.network.AuthApi;
import com.enicarthage.coulisses.network.BilletApi;
import com.enicarthage.coulisses.network.LieuApi;
import com.enicarthage.coulisses.network.SpectacleApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "API_TEST";
    private SpectacleApi spectacleApi;
    private LieuApi lieuApi;
    private AuthApi authApi;
    private BilletApi billetApi;
    private User loggedInUser;
    private String authToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spectacleApi = ApiClient.getClient().create(SpectacleApi.class);
        lieuApi = ApiClient.getClient().create(LieuApi.class);
        authApi = ApiClient.getClient().create(AuthApi.class);
        billetApi = ApiClient.getClient().create(BilletApi.class);

      //  testRegister();
        testLogin();
     //   testPasswordResetRequest();
      //  testPasswordReset();

        testGetAllLieux();
        testGetLieuById(1L);

        testGetAllBillets();
        testGetBilletById(1L);
        testGetBilletBySpectacleId(1L);

        testGetAllSpectacles();
        testGetSpectacleById(1L);
    }

    private void testRegister() {
        RegisterRequest request = new RegisterRequest("Hama", "Salhi", "hama@gmail.com","92303698","Password");
        authApi.register(request).enqueue(new Callback<AuthenticationResponse>() {
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                Log.d(TAG, "Register: " + response.code());
            }
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                Log.e(TAG, "Register failed: " + t.getMessage());
            }
        });
    }

    private void testLogin() {
        AuthenticationRequest request = new AuthenticationRequest("hama@gmail.com", "newPassword123");
        authApi.login(request).enqueue(new Callback<AuthenticationResponse>() {
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                if (response.isSuccessful()) {
                    AuthenticationResponse authResponse = response.body();
                    if (authResponse != null) {
                        loggedInUser = authResponse.getUser();  // Save the logged-in user
                        authToken = authResponse.getToken();    // Save the auth token
                        Log.d(TAG, "Login: User: " + loggedInUser.getNom() + " " + loggedInUser.getPrenom()+" "+loggedInUser.getEmail());
                        Log.d(TAG, "Login: Token: " + authToken);
                    }
                } else {
                    Log.e(TAG, "Login failed: " + response.code());
                }
            }
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                Log.e(TAG, "Login failed: " + t.getMessage());
            }
        });
    }

    private void testPasswordResetRequest() {
        authApi.requestPasswordReset("hama@gmail.com").enqueue(new Callback<String>() {
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, "Password Reset Request: " + response.body());
            }
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "Reset request failed: " + t.getMessage());
            }
        });
    }

    private void testPasswordReset() {
        // Make sure to use the reset token from the loggedInUser

            String newPassword = "newPassword123";
            authApi.resetPassword("b9f22e7a-7e46-486e-9ad2-fb9d46b09e9e", newPassword).enqueue(new Callback<String>() {
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "Password Reset Done: " + response.body());
                    } else {
                        Log.e(TAG, "Password Reset failed: " + response.code());
                    }
                }
                public void onFailure(Call<String> call, Throwable t) {
                    Log.e(TAG, "Reset failed: " + t.getMessage());
                }
            });

    }


    private void testGetAllLieux() {
        lieuApi.getAllLieux().enqueue(new Callback<List<Lieu>>() {
            public void onResponse(Call<List<Lieu>> call, Response<List<Lieu>> response) {
                Log.d(TAG, "Lieux: " + response.body().size());
            }
            public void onFailure(Call<List<Lieu>> call, Throwable t) {
                Log.e(TAG, "Lieux failed: " + t.getMessage());
            }
        });
    }

    private void testGetLieuById(Long id) {
        lieuApi.getLieuById(id).enqueue(new Callback<Lieu>() {
            public void onResponse(Call<Lieu> call, Response<Lieu> response) {
                Log.d(TAG, "Lieu: " + response.body().getNom());
            }
            public void onFailure(Call<Lieu> call, Throwable t) {
                Log.e(TAG, "Lieu failed: " + t.getMessage());
            }
        });
    }

    private void testGetAllBillets() {
        billetApi.getAllBillets().enqueue(new Callback<List<Billet>>() {
            public void onResponse(Call<List<Billet>> call, Response<List<Billet>> response) {
                Log.d(TAG, "Billets: " + response.body().size());
            }
            public void onFailure(Call<List<Billet>> call, Throwable t) {
                Log.e(TAG, "Billets failed: " + t.getMessage());
            }
        });
    }

    private void testGetBilletById(Long id) {
        billetApi.getBilletById(id).enqueue(new Callback<Billet>() {
            public void onResponse(Call<Billet> call, Response<Billet> response) {
                Log.d(TAG, "Billet: " + response.body().getId());
            }
            public void onFailure(Call<Billet> call, Throwable t) {
                Log.e(TAG, "Billet failed: " + t.getMessage());
            }
        });
    }

    private void testGetBilletBySpectacleId(Long spectacleId) {
        billetApi.getBilletBySpectacleId(spectacleId).enqueue(new Callback<List<Billet>>() {
            @Override
            public void onResponse(Call<List<Billet>> call, Response<List<Billet>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "Billet by Spectacle: " + response.body().size());
                } else {
                    Log.e(TAG, "Billet by Spectacle: response not successful or body is null");
                }
            }

            @Override
            public void onFailure(Call<List<Billet>> call, Throwable t) {
                Log.e(TAG, "Billet by Spectacle failed: " + t.getMessage());
            }
        });
    }

    private void testGetAllSpectacles() {
        spectacleApi.getAllSpectacles().enqueue(new Callback<List<Spectacle>>() {
            public void onResponse(Call<List<Spectacle>> call, Response<List<Spectacle>> response) {
                Log.d(TAG, "Spectacles: " + response.body().size());
            }
            public void onFailure(Call<List<Spectacle>> call, Throwable t) {
                Log.e(TAG, "Spectacles failed: " + t.getMessage());
            }
        });
    }

    private void testGetSpectacleById(Long id) {
        spectacleApi.getSpectacleById(id).enqueue(new Callback<Spectacle>() {
            public void onResponse(Call<Spectacle> call, Response<Spectacle> response) {
                Log.d(TAG, "Spectacle: " + response.body().getTitre());
            }
            public void onFailure(Call<Spectacle> call, Throwable t) {
                Log.e(TAG, "Spectacle failed: " + t.getMessage());
            }
        });
    }
}
