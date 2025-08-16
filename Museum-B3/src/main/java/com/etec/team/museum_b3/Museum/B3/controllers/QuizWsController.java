package com.etec.team.museum_b3.Museum.B3.controllers;

import com.etec.team.museum_b3.Museum.B3.dtos.AnswerMessageDTO;
import com.etec.team.museum_b3.Museum.B3.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class QuizWsController {
    private final RoomService service;

    @MessageMapping("/rooms/{code}/answer")
    public void answer(@DestinationVariable String code, @Payload AnswerMessageDTO msg) {
        service.receiveAnswer(code, msg);
    }
}

