package com.enicarthage.coulisses.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.enicarthage.coulisses.R;
import com.enicarthage.coulisses.models.Spectacle;
import com.enicarthage.coulisses.utils.DateUtils;

import java.util.List;

public class SpectacleAdapter extends RecyclerView.Adapter<SpectacleAdapter.SpectacleViewHolder> {

    private final List<Spectacle> spectacles;
    private final Context context;
    private final OnSpectacleClickListener listener;

    public interface OnSpectacleClickListener {
        void onSpectacleClick(Spectacle spectacle);
    }

    public SpectacleAdapter(List<Spectacle> spectacles, Context context, OnSpectacleClickListener listener) {
        this.spectacles = spectacles;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SpectacleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_spectacle, parent, false);
        return new SpectacleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpectacleViewHolder holder, int position) {
        Spectacle spectacle = spectacles.get(position);

        Glide.with(context)
                .load(spectacle.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .into(holder.spectacleImage);

        holder.spectacleTitle.setText(spectacle.getTitre());
        holder.spectacleDate.setText(DateUtils.formatDate(spectacle.getDate()));
        holder.spectacleLocation.setText(spectacle.getLieu().getNom());
        holder.spectacleTime.setText(DateUtils.formatTime(spectacle.getHeureDebut()));

        holder.itemView.setOnClickListener(v -> listener.onSpectacleClick(spectacle));
    }

    @Override
    public int getItemCount() {
        return spectacles.size();
    }

    static class SpectacleViewHolder extends RecyclerView.ViewHolder {
        ImageView spectacleImage;
        TextView spectacleTitle;
        TextView spectacleDate;
        TextView spectacleLocation;
        TextView spectacleTime;

        public SpectacleViewHolder(@NonNull View itemView) {
            super(itemView);
            spectacleImage = itemView.findViewById(R.id.spectacleImage);
            spectacleTitle = itemView.findViewById(R.id.spectacleTitle);
            spectacleDate = itemView.findViewById(R.id.spectacleDate);
            spectacleLocation = itemView.findViewById(R.id.spectacleLocation);
            spectacleTime = itemView.findViewById(R.id.spectacleTime);
        }
    }
}