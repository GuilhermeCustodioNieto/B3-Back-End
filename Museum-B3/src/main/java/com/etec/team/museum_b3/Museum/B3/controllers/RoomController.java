package com.etec.team.museum_b3.Museum.B3.controllers;

import com.etec.team.museum_b3.Museum.B3.dtos.CreateRoomRequestDTO;
import com.etec.team.museum_b3.Museum.B3.dtos.CreateRoomResponseDTO;
import com.etec.team.museum_b3.Museum.B3.dtos.JoinRoomRequestDTO;
import com.etec.team.museum_b3.Museum.B3.dtos.StartRequestDTO;
import com.etec.team.museum_b3.Museum.B3.services.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@Tag(name = "Rooms", description = "Gerenciamento de salas do Quiz")
public class RoomController {

    @Autowired
    private final RoomService roomService;

    @Operation(
            summary = "Criar uma nova sala",
            description = "Cria uma sala de quiz e retorna o código único para os jogadores entrarem.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sala criada com sucesso",
                            content = @Content(schema = @Schema(implementation = CreateRoomResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida")
            }
    )
    @PostMapping
    public CreateRoomResponseDTO create(
            @RequestBody(
                    description = "Dados para criação da sala (nome do host)",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CreateRoomRequestDTO.class))
            )
            @Valid @org.springframework.web.bind.annotation.RequestBody CreateRoomRequestDTO requestDTO
    ){
        String code = roomService.createRoom(1L, requestDTO.hostName());
        return new CreateRoomResponseDTO(code);
    }

    @Operation(
            summary = "Entrar em uma sala",
            description = "Permite que um usuário entre em uma sala existente através do código."
    )
    @PostMapping("/join")
    public void join(
            @RequestBody(
                    description = "Dados de entrada na sala",
                    required = true,
                    content = @Content(schema = @Schema(implementation = JoinRoomRequestDTO.class))
            )
            @Valid @org.springframework.web.bind.annotation.RequestBody JoinRoomRequestDTO joinRoomRequestDTO
    ){
        roomService.join(joinRoomRequestDTO.code(), joinRoomRequestDTO.username());
    }

    @Operation(
            summary = "Iniciar o quiz em uma sala",
            description = "O host da sala inicia o quiz, disparando a primeira pergunta para todos os jogadores conectados."
    )
    @PostMapping("/start")
    public void start(
            @RequestBody(
                    description = "Código da sala a ser iniciada",
                    required = true,
                    content = @Content(schema = @Schema(implementation = StartRequestDTO.class))
            )
            @Valid @org.springframework.web.bind.annotation.RequestBody StartRequestDTO startRequestDTO
    ){
        roomService.start(startRequestDTO.code());
    }
}
