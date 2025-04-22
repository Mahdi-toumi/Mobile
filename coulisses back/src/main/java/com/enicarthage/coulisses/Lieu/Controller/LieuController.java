package com.enicarthage.coulisses.Lieu.Controller;

import com.enicarthage.coulisses.Lieu.Models.Lieu;
import com.enicarthage.coulisses.Lieu.Service.LieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lieu")
public class LieuController {

    @Autowired
    private LieuService lieuService;

    @GetMapping
    public List<Lieu> getAll() {
        return lieuService.getAllLieux();
    }

    @GetMapping("/{id}")
    public Lieu getById(@PathVariable Long id) {
        return lieuService.getLieuById(id);
    }


}