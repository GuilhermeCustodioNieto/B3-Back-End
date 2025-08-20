package com.etec.team.museum_b3.Museum.B3.controllers;

import com.etec.team.museum_b3.Museum.B3.entities.MuseumData;
import com.etec.team.museum_b3.Museum.B3.repositories.MuseumDataRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/museum")
@RequiredArgsConstructor
@Tag(name = "Museum", description = "Informações do Museu B3")
public class MuseumController {

    private final MuseumDataRepository repository;

    @Operation(
            summary = "Listar cards do Museu",
            description = "Retorna todos os cards de informação exibidos na aba de informações do app.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de cards retornada com sucesso")
            }
    )
    @GetMapping
    public List<MuseumData> findAll(){
        return repository.findAll();
    }
}
