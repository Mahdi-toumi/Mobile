package com.enicarthage.coulisses.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.enicarthage.coulisses.R;

public class DetailsSpectacleActivity extends AppCompatActivity {
    ImageView imageView;
    TextView titre, date, heure, ville, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_spectacle);

        imageView = findViewById(R.id.detailImage);
        titre = findViewById(R.id.detailTitre);
        date = findViewById(R.id.detailDate);
        heure = findViewById(R.id.detailHeure);
        ville = findViewById(R.id.detailVille);
        description = findViewById(R.id.detailDescription);

        Intent intent = getIntent();
        if (intent != null) {
            titre.setText(intent.getStringExtra("titre"));
            date.setText(intent.getStringExtra("date"));
            heure.setText(intent.getStringExtra("heure"));
            ville.setText(intent.getStringExtra("ville"));
            description.setText(intent.getStringExtra("description"));

            String imageUrl = intent.getStringExtra("image");
            Glide.with(this).load(imageUrl).into(imageView); // Assure-toi d’avoir Glide
        }
    }
}
