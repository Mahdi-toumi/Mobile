package com.enicarthage.coulisses.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.enicarthage.coulisses.R;
import com.enicarthage.coulisses.models.Spectacle;
import com.enicarthage.coulisses.utils.DateUtils;
import com.google.android.material.imageview.ShapeableImageView;

public class SpectacleDetailActivity extends AppCompatActivity {

    private Spectacle spectacle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spectacle_detail);

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
            getSupportActionBar().setTitle(spectacle.getTitre());
        }

        // Initialize views
        ShapeableImageView spectacleImage = findViewById(R.id.spectacleDetailImage);
        TextView title = findViewById(R.id.spectacleDetailTitle);
        TextView date = findViewById(R.id.spectacleDetailDate);
        TextView time = findViewById(R.id.spectacleDetailTime);
        TextView location = findViewById(R.id.spectacleDetailLocation);
        TextView description = findViewById(R.id.spectacleDetailDescription);
        TextView website = findViewById(R.id.spectacleDetailWebsite);
        TextView capacity = findViewById(R.id.spectacleDetailCapacity);
        Button btnReserve = findViewById(R.id.btnReserve);

        // Populate data
        Glide.with(this)
                .load(spectacle.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .into(spectacleImage);

        title.setText(spectacle.getTitre());
        date.setText(DateUtils.formatDate(spectacle.getDate()));
        time.setText(DateUtils.formatTime(spectacle.getHeureDebut()));
        location.setText(String.format("%s, %s", spectacle.getLieu().getNom(), spectacle.getLieu().getVille()));
        description.setText(spectacle.getDescription());

        if (spectacle.getSiteWeb() != null && !spectacle.getSiteWeb().isEmpty()) {
            website.setText(spectacle.getSiteWeb());
            website.setOnClickListener(v -> {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(spectacle.getSiteWeb()));
                startActivity(browserIntent);
            });
        } else {
            website.setVisibility(View.GONE);
        }

        capacity.setText(String.format("Capacity: %d/%d",
                spectacle.getNbSpectateurs(),
                spectacle.getLieu().getCapacite()));

        // Set up reservation button
        btnReserve.setOnClickListener(v -> {
            Intent intent = new Intent(this, ReservationActivity.class);
            intent.putExtra("spectacle", spectacle);
            startActivity(intent);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}