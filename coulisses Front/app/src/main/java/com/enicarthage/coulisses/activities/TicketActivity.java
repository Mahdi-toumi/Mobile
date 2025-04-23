package com.enicarthage.coulisses.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.enicarthage.coulisses.R;
import com.enicarthage.coulisses.models.Spectacle;
import com.enicarthage.coulisses.models.User;
import com.enicarthage.coulisses.utils.DateUtils;

public class TicketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        // Get data from intent
        Spectacle spectacle = getIntent().getParcelableExtra("spectacle");
        String category = getIntent().getStringExtra("category");
        User user = getIntent().getParcelableExtra("user");
        String guestName = getIntent().getStringExtra("guest_name");
        String guestEmail = getIntent().getStringExtra("guest_email");
        String guestPhone = getIntent().getStringExtra("guest_phone");

        // Set up toolbar
        setSupportActionBar(findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.ticket_details));
        }

        // Initialize views
        TextView ticketNumber = findViewById(R.id.ticketNumber);
        TextView eventName = findViewById(R.id.eventName);
        TextView dateTime = findViewById(R.id.dateTime);
        TextView location = findViewById(R.id.location);
        TextView ticketCategory = findViewById(R.id.ticketCategory);
        TextView price = findViewById(R.id.price);
        TextView userName = findViewById(R.id.userName);
        TextView userEmail = findViewById(R.id.userEmail);
        TextView userPhone = findViewById(R.id.userPhone); // Add this TextView to your layout
        Button btnBackToHome = findViewById(R.id.btnBackToHome);

        // Generate a random ticket number
        String generatedTicketNumber = "TKT" + System.currentTimeMillis() % 1000000;
        ticketNumber.setText(String.format(getString(R.string.ticket_number), generatedTicketNumber));

        // Populate spectacle data
        eventName.setText(String.format(getString(R.string.event), spectacle.getTitre()));
        dateTime.setText(String.format(getString(R.string.date_time),
                DateUtils.formatDate(spectacle.getDate()),
                DateUtils.formatTime(spectacle.getHeureDebut())));
        location.setText(String.format(getString(R.string.location),
                spectacle.getLieu().getNom() + ", " + spectacle.getLieu().getVille()));
        ticketCategory.setText(String.format(getString(R.string.category), category));

        // Calculate price based on category
        double ticketPrice = calculatePrice(category);
        price.setText(String.format(getString(R.string.price), ticketPrice));

        // Handle user/guest information
        if (user != null) {
            // Authenticated user reservation
            userName.setText(String.format("%s %s", user.getPrenom(), user.getNom()));
            userEmail.setText(user.getEmail());
            userPhone.setText(user.getTel()); // Make sure to add this field to your User class
        } else if (guestName != null) {
            // Guest reservation
            userName.setText(guestName);
            userEmail.setText(guestEmail != null ? guestEmail : "N/A");
            userPhone.setText(guestPhone != null ? guestPhone : "N/A");
        }

        // Set up back to home button
        btnBackToHome.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    private double calculatePrice(String category) {
        switch (category) {
            case "Standard":
                return 20.0;
            case "VIP":
                return 50.0;
            case "Premium":
                return 100.0;
            default:
                return 20.0;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}