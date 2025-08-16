package com.etec.team.museum_b3.Museum.B3.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledFuture;

@Getter
@Setter
public class RoomState {
    private final String code;
    private final Map<String, Integer> scores = new ConcurrentHashMap<>();
    private final Set<String> players = ConcurrentHashMap.newKeySet();
    private Quiz quiz;
    private int currentIndex = -1;
    private boolean running;
    private ScheduledFuture<?> currentTimer;

    public RoomState(String code, Quiz quiz) {
        this.code = code;
        this.quiz = quiz;
    }
}
