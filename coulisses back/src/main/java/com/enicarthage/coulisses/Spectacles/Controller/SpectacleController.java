package com.enicarthage.coulisses.Spectacles.Controller;


import com.enicarthage.coulisses.Spectacles.Models.Spectacle;
import com.enicarthage.coulisses.Spectacles.Service.SpectacleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/spectacle")
public class SpectacleController {

    @Autowired
    private SpectacleService spectacleService;

    @GetMapping
    public List<Spectacle> getAll() {
        return spectacleService.getAllSpectacles();
    }

    @GetMapping("/{id}")
    public Spectacle getById(@PathVariable Long id) {
        return spectacleService.getSpectacleById(id);
    }

    @GetMapping("/featured")
    public List<Spectacle> getFeaturedSpectacles() {
        return spectacleService.getFeaturedSpectacles();
    }

}

