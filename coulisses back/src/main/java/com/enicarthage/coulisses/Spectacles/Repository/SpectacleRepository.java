package com.enicarthage.coulisses.Spectacles.Repository;

import com.enicarthage.coulisses.Spectacles.Models.Spectacle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpectacleRepository extends JpaRepository<Spectacle, Long> {
    List<Spectacle> findByTitreContainingIgnoreCase(String titre);

    List<Spectacle> findTop5ByOrderByDateDesc();
}

