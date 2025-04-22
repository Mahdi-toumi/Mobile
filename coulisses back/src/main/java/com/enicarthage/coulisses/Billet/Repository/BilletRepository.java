package com.enicarthage.coulisses.Billet.Repository;

import com.enicarthage.coulisses.Billet.Models.Billet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BilletRepository extends JpaRepository<Billet, Long> {
    List<Billet> findBySpectacleId(Long spectacleId);
}
