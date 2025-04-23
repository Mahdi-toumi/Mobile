package com.enicarthage.coulisses.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.enicarthage.coulisses.R;
import com.enicarthage.coulisses.models.Spectacle;

public class GuestReservationActivity extends AppCompatActivity {

    private Spectacle spectacle;
    private String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_reservation);

        // Get data from intent
        spectacle = getIntent().getParcelableExtra("spectacle");
        selectedCategory = getIntent().getStringExtra("category");

        // Initialize views
        EditText guestName = findViewById(R.id.guestName);
        EditText guestEmail = findViewById(R.id.guestEmail);
        EditText guestPhone = findViewById(R.id.guestPhone);
        Button btnConfirm = findViewById(R.id.btnConfirm);

        btnConfirm.setOnClickListener(v -> {
            // Validate inputs
            if (validateInputs(guestName, guestEmail, guestPhone)) {
                // Proceed to ticket generation
                generateGuestTicket(
                        guestName.getText().toString(),
                        guestEmail.getText().toString(),
                        guestPhone.getText().toString()
                );
            }
        });
    }

    private boolean validateInputs(EditText name, EditText email, EditText phone) {
        // Add your validation logic here
        return true;
    }

    private void generateGuestTicket(String name, String email, String phone) {
        // Create ticket intent
        Intent intent = new Intent(this, TicketActivity.class);
        intent.putExtra("spectacle", spectacle);
        intent.putExtra("category", selectedCategory);
        intent.putExtra("guest_name", name);
        intent.putExtra("guest_email", email);
        intent.putExtra("guest_phone", phone);
        startActivity(intent);
        finish();
    }
}