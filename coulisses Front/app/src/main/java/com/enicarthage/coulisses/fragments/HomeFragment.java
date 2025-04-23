package com.enicarthage.coulisses.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.enicarthage.coulisses.R;
import com.enicarthage.coulisses.activities.SpectacleListActivity;
import com.enicarthage.coulisses.adapters.SpectaclePagerAdapter;
import com.enicarthage.coulisses.models.Spectacle;
import com.enicarthage.coulisses.network.ApiClient;
import com.enicarthage.coulisses.network.SpectacleApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private ViewPager2 spectaclePager;
    private SpectaclePagerAdapter pagerAdapter;
    private List<Spectacle> featuredSpectacles = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialiser les vues
        spectaclePager = view.findViewById(R.id.spectaclePager);
        Button btnShowAll = view.findViewById(R.id.btnShowAll);

        // Configurer le ViewPager
        pagerAdapter = new SpectaclePagerAdapter(requireContext(), featuredSpectacles);
        spectaclePager.setAdapter(pagerAdapter);

        // Configurer le bouton "Voir tous"
        btnShowAll.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SpectacleListActivity.class);
            startActivity(intent);
        });

        // Charger les spectacles en vedette
        loadFeaturedSpectacles();

        return view;
    }

    private void loadFeaturedSpectacles() {
        SpectacleApi api = ApiClient.getClient().create(SpectacleApi.class);
        Call<List<Spectacle>> call = api.getFeaturedSpectacles();

        call.enqueue(new Callback<List<Spectacle>>() {
            @Override
            public void onResponse(Call<List<Spectacle>> call, Response<List<Spectacle>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    featuredSpectacles.clear();
                    featuredSpectacles.addAll(response.body());
                    pagerAdapter.notifyDataSetChanged();

                    // Ajouter un effet de marge entre les éléments
                    spectaclePager.setPadding(60, 0, 60, 0);
                    spectaclePager.setClipToPadding(false);
                    spectaclePager.setClipChildren(false);
                    spectaclePager.setOffscreenPageLimit(3);

                    // Ajouter un effet de profondeur
                    spectaclePager.setPageTransformer(new DepthPageTransformer());
                }
            }

            @Override
            public void onFailure(Call<List<Spectacle>> call, Throwable t) {
                // Gérer l'erreur
            }
        });
    }

    // Animation personnalisée pour le ViewPager
    private static class DepthPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.85f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                view.setAlpha(0f);
            } else if (position <= 0) { // [-1,0]
                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);
            } else if (position <= 1) { // (0,1]
                view.setAlpha(1 - position);
                view.setTranslationX(pageWidth * -position);
                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
            } else { // (1,+Infinity]
                view.setAlpha(0f);
            }
        }
    }
}