package com.enicarthage.coulisses.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enicarthage.coulisses.R;
import com.enicarthage.coulisses.activities.DetailsSpectacleActivity;
import com.enicarthage.coulisses.models.Spectacle;

import java.util.ArrayList;
import java.util.List;

public class SpectacleAdapter extends RecyclerView.Adapter<SpectacleAdapter.SpectacleViewHolder> implements Filterable {

    private Context context;
    private List<Spectacle> spectacleList;
    private List<Spectacle> spectacleListFiltered; // List used for filtering

    public SpectacleAdapter(Context context, List<Spectacle> spectacleList) {
        this.context = context;
        this.spectacleList = spectacleList;
        this.spectacleListFiltered = spectacleList; // Initially, no filtering
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

        holder.titreTextView.setText(spectacle.getTitre());
        holder.dateTextView.setText(spectacle.getDate());
        holder.heureTextView.setText(String.valueOf(spectacle.getHeureDebut()));
        holder.villeTextView.setText(spectacle.getLieu().getVille());

        // Handle click to open detail activity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsSpectacleActivity.class);
            intent.putExtra("titre", spectacle.getTitre());
            intent.putExtra("date", spectacle.getDate());
            intent.putExtra("heure", spectacle.getHeureDebut());
            intent.putExtra("ville", spectacle.getLieu().getVille());
            context.startActivity(intent);
        });
    }




    @Override
    public int getItemCount() {
        return spectacleListFiltered.size();
    }

    // Filterable implementation
    @Override
    public Filter getFilter() {
        return spectacleFilter;
    }

    private Filter spectacleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Spectacle> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(spectacleList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Spectacle spectacle : spectacleList) {
                    if (spectacle.getTitre().toLowerCase().contains(filterPattern) ||
                            spectacle.getLieu().getVille().toLowerCase().contains(filterPattern)) {
                        filteredList.add(spectacle);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            spectacleListFiltered.clear();
            if (results.values != null) {
                spectacleListFiltered.addAll((List) results.values);
            }
            notifyDataSetChanged();
        }
    };

    public static class SpectacleViewHolder extends RecyclerView.ViewHolder {

        TextView titreTextView;
        TextView dateTextView;
        TextView heureTextView;
        TextView villeTextView;

        public SpectacleViewHolder(View itemView) {
            super(itemView);

            titreTextView = itemView.findViewById(R.id.tvTitre );
            dateTextView = itemView.findViewById(R.id.tvDate);
            heureTextView = itemView.findViewById(R.id.tvHeure);
            villeTextView = itemView.findViewById(R.id.tvVille);
        }
    }
}
