package com.etec.team.museum_b3.Museum.B3.dtos;

import java.util.Map;

public record ScoreboardEvent(String type, Map<String, Integer> scores, boolean finished) {
}
