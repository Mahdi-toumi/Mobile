package com.enicarthage.coulisses.Billet.Controller;

import com.enicarthage.coulisses.Billet.Models.Billet;
import com.enicarthage.coulisses.Billet.Service.BilletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/billet")
public class BilletController {

    @Autowired
    private BilletService billetService;

    @GetMapping
    public List<Billet> getAll() {
        return billetService.getAllBillets();
    }

    @GetMapping("/{id}")
    public Billet getById(@PathVariable Long id) {
        return billetService.getBilletById(id);
    }

    @GetMapping("/spectacle/{spectacleId}")
    public List<Billet> getBySpectacleId(@PathVariable Long spectacleId) {
        return billetService.getBilletsBySpectacleId(spectacleId);
    }

}
