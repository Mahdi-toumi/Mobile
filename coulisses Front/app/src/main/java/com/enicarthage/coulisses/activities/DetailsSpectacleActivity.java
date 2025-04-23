package com.enicarthage.coulisses.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.enicarthage.coulisses.R;
import com.google.android.material.button.MaterialButton;

public class DetailsSpectacleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_spectacle);

        // Initialisation des vues
        ImageView imageView = findViewById(R.id.detailImage);
        TextView titre = findViewById(R.id.detailTitre);
        TextView dateTime = findViewById(R.id.detailDateTime);
        TextView duree = findViewById(R.id.detailDuree);
        TextView capacity = findViewById(R.id.detailCapacity);
        TextView lieuNom = findViewById(R.id.detailLieuNom);
        TextView lieuAdresse = findViewById(R.id.detailLieuAdresse);
        TextView ville = findViewById(R.id.detailVille);
        TextView description = findViewById(R.id.detailDescription);
        MaterialButton siteWeb = findViewById(R.id.detailSiteWeb);
        MaterialButton btnReservation = findViewById(R.id.btnReservation);

        Intent intent = getIntent();
        if (intent != null) {
            bindData(intent, imageView, titre, dateTime, duree, capacity,
                    lieuNom, lieuAdresse, ville, description, siteWeb);
        }

        btnReservation.setOnClickListener(v -> openReservationWebsite());
    }

    private void bindData(Intent intent, ImageView imageView, TextView titre,
                          TextView dateTime, TextView duree, TextView capacity,
                          TextView lieuNom, TextView lieuAdresse,
                          TextView ville, TextView description,
                          MaterialButton siteWeb) {

        // Données de base
        titre.setText(intent.getStringExtra("titre"));
        description.setText(intent.getStringExtra("description"));

        // Date et heure combinées
        String dateTimeText = String.format("%s • %s",
                intent.getStringExtra("date"),
                intent.getStringExtra("heure"));
        dateTime.setText(dateTimeText);

        // Durée et capacité
        duree.setText(intent.getStringExtra("duree"));
        capacity.setText(getString(R.string.capacity_format,
                intent.getIntExtra("capacity", 0)));

        // Lieu détaillé
        lieuNom.setText(intent.getStringExtra("lieu_nom"));
        lieuAdresse.setText(intent.getStringExtra("lieu_adresse"));
        ville.setText(intent.getStringExtra("ville"));

        // Image
        Glide.with(this)
                .load(intent.getStringExtra("image"))
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.default_image)
                .into(imageView);

        // Site web
        String website = intent.getStringExtra("siteweb");
        siteWeb.setText(website != null ? website : getString(R.string.website));
        siteWeb.setOnClickListener(v -> openWebsite(website));
    }

    private void openWebsite(String url) {
        if (url == null || url.isEmpty()) {
            Toast.makeText(this, R.string.url_invalid, Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        } catch (Exception e) {
            Toast.makeText(this, R.string.browser_error, Toast.LENGTH_SHORT).show();
        }
    }

    private void openReservationWebsite() {
        String reservationUrl = "https://reservation.coulisses.com"; // URL à personnaliser
        openWebsite(reservationUrl);
    }
}