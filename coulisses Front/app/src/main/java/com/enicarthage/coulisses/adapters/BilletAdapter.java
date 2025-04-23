package com.enicarthage.coulisses.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enicarthage.coulisses.R;
import com.enicarthage.coulisses.models.Billet;

import java.util.List;
import java.util.Locale;

public class BilletAdapter extends RecyclerView.Adapter<BilletAdapter.BilletViewHolder> {

    private final List<Billet> billets;
    private final Context context;

    public BilletAdapter(List<Billet> billets, Context context) {
        this.billets = billets;
        this.context = context;
    }

    @NonNull
    @Override
    public BilletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_billet, parent, false);
        return new BilletViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BilletViewHolder holder, int position) {
        Billet billet = billets.get(position);

        holder.billetCategory.setText(billet.getCategorie());
        holder.billetPrice.setText(String.format(Locale.getDefault(), "%.2f TND", billet.getPrix()));
    }

    @Override
    public int getItemCount() {
        return billets.size();
    }

    static class BilletViewHolder extends RecyclerView.ViewHolder {
        TextView billetCategory;
        TextView billetPrice;

        public BilletViewHolder(@NonNull View itemView) {
            super(itemView);
            billetCategory = itemView.findViewById(R.id.billetCategory);
            billetPrice = itemView.findViewById(R.id.billetPrice);
        }
    }
}