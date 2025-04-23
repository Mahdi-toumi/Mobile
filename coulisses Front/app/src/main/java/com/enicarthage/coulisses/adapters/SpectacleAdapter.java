package com.enicarthage.coulisses.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.enicarthage.coulisses.R;
import com.enicarthage.coulisses.activities.DetailsSpectacleActivity;
import com.enicarthage.coulisses.models.Spectacle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SpectacleAdapter extends RecyclerView.Adapter<SpectacleAdapter.SpectacleViewHolder> implements Filterable {

    private final Context context;
    private final List<Spectacle> spectacleList;
    private List<Spectacle> spectacleListFiltered;

    public SpectacleAdapter(Context context, List<Spectacle> spectacleList) {
        this.context = context;
        this.spectacleList = spectacleList;
        this.spectacleListFiltered = new ArrayList<>(spectacleList);
    }

    @NonNull
    @Override
    public SpectacleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_spectacle, parent, false);
        return new SpectacleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpectacleViewHolder holder, int position) {
        Spectacle spectacle = spectacleListFiltered.get(position);

        // Gestion des nulls
        String ville = spectacle.getLieu() != null ?
                spectacle.getLieu().getVille() :
                context.getString(R.string.ville_inconnue);

        String siteWeb = spectacle.getSiteWeb() != null ?
                spectacle.getSiteWeb() :
                context.getString(R.string.site_non_disponible);

        holder.titreTextView.setText(spectacle.getTitre());
        holder.dateTextView.setText(spectacle.getDate());
        holder.heureTextView.setText(formatTime(spectacle.getHeureDebut()));
        holder.dureeTextView.setText(context.getString(R.string.duree_format, formatDuration(spectacle.getDuree()) ));
        holder.villeTextView.setText(ville);

        loadImage(spectacle.getImageUrl(), holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsSpectacleActivity.class);

            // Données de base
            intent.putExtra("titre", spectacle.getTitre());
            intent.putExtra("date", spectacle.getDate());
            intent.putExtra("heure", formatTime(spectacle.getHeureDebut()));
            intent.putExtra("image", spectacle.getImageUrl());
            intent.putExtra("description", spectacle.getDescription());
            intent.putExtra("siteweb", siteWeb);

            // Nouveaux champs
            intent.putExtra("duree", formatDuration(spectacle.getDuree()));
            intent.putExtra("capacity", spectacle.getNbSpectateurs());

            // Gestion null-safe du lieu
            if(spectacle.getLieu() != null) {
                intent.putExtra("lieu_nom", spectacle.getLieu().getNom());
                intent.putExtra("lieu_adresse", spectacle.getLieu().getAdresse());
                intent.putExtra("ville", spectacle.getLieu().getVille());
            } else {
                intent.putExtra("lieu_nom","");
                intent.putExtra("lieu_adresse", "");
                intent.putExtra("ville", "");
            }

            context.startActivity(intent);
        });
    }

    private void loadImage(String imageUrl, ImageView imageView) {
        if (imageUrl == null || imageUrl.isEmpty()) {
            Glide.with(context)
                    .load(R.drawable.default_image)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
            return;
        }

        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return spectacleListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Spectacle> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(spectacleList);
                } else {
                    String pattern = constraint.toString().toLowerCase().trim();
                    for (Spectacle s : spectacleList) {
                        if (s.getTitre().toLowerCase().contains(pattern) ||
                                (s.getLieu() != null && s.getLieu().getVille().toLowerCase().contains(pattern)) ||
                                formatDuration(s.getDuree()).contains(pattern)) {
                            filteredList.add(s);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                spectacleListFiltered.clear();
                if (results.values != null) {
                    spectacleListFiltered.addAll((List<Spectacle>) results.values);
                }
                notifyDataSetChanged();
            }
        };
    }

    private String formatTime(BigDecimal heure) {
        try {
            int hours = heure.intValue();
            int minutes = heure.subtract(BigDecimal.valueOf(hours))
                    .multiply(BigDecimal.valueOf(60))
                    .intValue();
            return String.format("%02d:%02d", hours, minutes);
        } catch (Exception e) {
            return context.getString(R.string.heure_inconnue);
        }
    }

    private String formatDuration(BigDecimal duree) {
        try {
            int hours = duree.intValue();
            int minutes = duree.subtract(BigDecimal.valueOf(hours))
                    .multiply(BigDecimal.valueOf(60))
                    .intValue();
            return String.format("%dh%02d", hours, minutes);
        } catch (Exception e) {
            return context.getString(R.string.duree_inconnue);
        }
    }

    static class SpectacleViewHolder extends RecyclerView.ViewHolder {
        final TextView titreTextView, dateTextView, heureTextView;
        final TextView dureeTextView, villeTextView;
        final ImageView imageView;

        SpectacleViewHolder(View itemView) {
            super(itemView);
            titreTextView = itemView.findViewById(R.id.tvTitre);
            dateTextView = itemView.findViewById(R.id.tvDate);
            heureTextView = itemView.findViewById(R.id.tvHeure);
            dureeTextView = itemView.findViewById(R.id.tvDuree);
            villeTextView = itemView.findViewById(R.id.tvVille);
            imageView = itemView.findViewById(R.id.itemImage);
        }
    }
}