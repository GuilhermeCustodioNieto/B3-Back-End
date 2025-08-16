package com.etec.team.museum_b3.Museum.B3.dtos;

import com.etec.team.museum_b3.Museum.B3.entities.Alternative;
import com.etec.team.museum_b3.Museum.B3.entities.enums.Format;

import java.util.List;

public record QuestionEventDTO(String type, String text, List<AlternativeDTO> alternatives, int index, int total, int timeLimitSec) {
    public record AlternativeDTO(Long id, String text, Format format){}
}
