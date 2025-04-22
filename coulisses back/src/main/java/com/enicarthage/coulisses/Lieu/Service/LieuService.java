package com.enicarthage.coulisses.Lieu.Service;


import com.enicarthage.coulisses.Lieu.Models.Lieu;
import com.enicarthage.coulisses.Lieu.Repository.LieuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LieuService {

    @Autowired
    private LieuRepository lieuRepository;

    public List<Lieu> getAllLieux() {
        return lieuRepository.findAll();
    }

    public Lieu getLieuById(Long id) {
        return lieuRepository.findById(id).orElse(null);
    }


}

