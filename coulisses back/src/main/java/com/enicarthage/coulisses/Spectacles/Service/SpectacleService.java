package com.enicarthage.coulisses.Spectacles.Service;


import com.enicarthage.coulisses.Spectacles.Models.Spectacle;
import com.enicarthage.coulisses.Spectacles.Repository.SpectacleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpectacleService {

    @Autowired
    private SpectacleRepository spectacleRepository;

    public List<Spectacle> getAllSpectacles() {
        return spectacleRepository.findAll();
    }

    public Spectacle getSpectacleById(Long id) {
        return spectacleRepository.findById(id).orElse(null);
    }

    public List<Spectacle> getFeaturedSpectacles() {
        return spectacleRepository.findTop5ByOrderByDateDesc();
    }


}


