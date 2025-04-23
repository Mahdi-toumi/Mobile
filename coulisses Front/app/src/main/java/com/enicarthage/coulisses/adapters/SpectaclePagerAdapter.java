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

public class SpectaclePagerAdapter extends RecyclerView.Adapter<SpectaclePagerAdapter.SpectacleViewHolder> {

    private final Context context;
    private final List<Spectacle> spectacles;

    public SpectaclePagerAdapter(Context context, List<Spectacle> spectacles) {
        this.context = context;
        this.spectacles = spectacles;
    }

    @NonNull
    @Override
    public SpectacleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_featured_spectacle, parent, false);
        return new SpectacleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpectacleViewHolder holder, int position) {
        Spectacle spectacle = spectacles.get(position);

        Glide.with(context)
                .load(spectacle.getImageUrl())
                .placeholder(R.drawable.placeholder) // Utilise votre drawable
                .into(holder.imageView);

        holder.title.setText(spectacle.getTitre());
        holder.date.setText(DateUtils.formatDate(spectacle.getDate()));
    }

    @Override
    public int getItemCount() {
        return spectacles.size();
    }

    static class SpectacleViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView date;

        public SpectacleViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.featuredImage);
            title = itemView.findViewById(R.id.featuredTitle);
            date = itemView.findViewById(R.id.featuredDate);
        }
    }
}