package com.etec.team.museum_b3.Museum.B3.controllers;

import com.etec.team.museum_b3.Museum.B3.entities.MuseumData;
import com.etec.team.museum_b3.Museum.B3.repositories.MuseumDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/museum")
@RequiredArgsConstructor
public class MuseumController {
    private final MuseumDataRepository repository;

    @GetMapping
    public List<MuseumData> findAll(){
        return repository.findAll();
    }

}
