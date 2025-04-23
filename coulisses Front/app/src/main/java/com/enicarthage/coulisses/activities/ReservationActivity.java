package com.enicarthage.coulisses.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.enicarthage.coulisses.R;
import com.enicarthage.coulisses.models.Billet;
import com.enicarthage.coulisses.models.Spectacle;
import com.enicarthage.coulisses.network.ApiClient;
import com.enicarthage.coulisses.network.BilletApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationActivity extends AppCompatActivity {

    private Spectacle spectacle;
    private String selectedCategory;
    private List<Billet> availableBillets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        // Get spectacle from intent
        spectacle = getIntent().getParcelableExtra("spectacle");
        if (spectacle == null) {
            Toast.makeText(this, "Spectacle not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Set up toolbar
        setSupportActionBar(findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Reservation");
        }

        // Initialize views
        RadioGroup ticketOptions = findViewById(R.id.ticketOptions);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnSignUp = findViewById(R.id.btnSignUp);
        Button btnGuestReserve = findViewById(R.id.btnGuestReserve);

        // Fetch available tickets
        fetchAvailableBillets(spectacle.getId());

        // Set up radio group listener
        ticketOptions.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.optionStandard) {
                selectedCategory = "Standard";
            } else if (checkedId == R.id.optionVIP) {
                selectedCategory = "VIP";
            } else if (checkedId == R.id.optionPremium) {
                selectedCategory = "Premium";
            }
        });

        // Set up button listeners
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("spectacle", spectacle);
            intent.putExtra("category", selectedCategory);
            startActivity(intent);
        });

        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            intent.putExtra("spectacle", spectacle);
            intent.putExtra("category", selectedCategory);
            startActivity(intent);
        });

        btnGuestReserve.setOnClickListener(v -> {
            // Directly proceed to reservation form
            Intent intent = new Intent(this, GuestReservationActivity.class);
            intent.putExtra("spectacle", spectacle);
            intent.putExtra("category", selectedCategory);
            startActivity(intent);
        });
    }

    private void fetchAvailableBillets(Long spectacleId) {
        BilletApi api = ApiClient.getClient().create(BilletApi.class);
        Call<List<Billet>> call = api.getBilletBySpectacleId(spectacleId);

        call.enqueue(new Callback<List<Billet>>() {
            @Override
            public void onResponse(Call<List<Billet>> call, Response<List<Billet>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    availableBillets = response.body();
                    // Update UI based on available tickets
                } else {
                    Toast.makeText(ReservationActivity.this, "Failed to load tickets", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Billet>> call, Throwable t) {
                Toast.makeText(ReservationActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}