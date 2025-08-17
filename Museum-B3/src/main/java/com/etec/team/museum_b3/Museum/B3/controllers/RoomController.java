package com.etec.team.museum_b3.Museum.B3.controllers;

import com.etec.team.museum_b3.Museum.B3.dtos.CreateRoomRequestDTO;
import com.etec.team.museum_b3.Museum.B3.dtos.CreateRoomResponseDTO;
import com.etec.team.museum_b3.Museum.B3.dtos.JoinRoomRequestDTO;
import com.etec.team.museum_b3.Museum.B3.dtos.StartRequestDTO;
import com.etec.team.museum_b3.Museum.B3.services.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {
    @Autowired
    private final RoomService roomService;

    @PostMapping
    public CreateRoomResponseDTO create(@RequestBody @Valid CreateRoomRequestDTO requestDTO){
        String code = roomService.createRoom(1L, requestDTO.hostName());
        return new CreateRoomResponseDTO(code);
    }

    @PostMapping("/join")
    public void join(@RequestBody @Valid JoinRoomRequestDTO joinRoomRequestDTO){
        roomService.join(joinRoomRequestDTO.code(), joinRoomRequestDTO.username());
    }

    @PostMapping("/start")
    public void start(@RequestBody @Valid StartRequestDTO startRequestDTO){
        roomService.start(startRequestDTO.code());
    }
}
