package com.enicarthage.coulisses.Billet.Service;

import com.enicarthage.coulisses.Billet.Models.Billet;
import com.enicarthage.coulisses.Billet.Repository.BilletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BilletService {

    @Autowired
    private BilletRepository billetRepository;

    public List<Billet> getAllBillets() {
        return billetRepository.findAll();
    }

    public Billet getBilletById(Long id) {
        return billetRepository.findById(id).orElse(null);
    }

    public List<Billet> getBilletsBySpectacleId(Long spectacleId) {
        return billetRepository.findBySpectacleId(spectacleId);
    }

}

