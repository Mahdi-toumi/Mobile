package com.enicarthage.coulisses.Lieu.Repository;

import com.enicarthage.coulisses.Lieu.Models.Lieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LieuRepository extends JpaRepository<Lieu, Long> {
    List<Lieu> findByNomContainingIgnoreCase(String nom);
}