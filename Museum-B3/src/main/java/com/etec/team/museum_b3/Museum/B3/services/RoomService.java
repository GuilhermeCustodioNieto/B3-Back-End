package com.etec.team.museum_b3.Museum.B3.services;

import com.etec.team.museum_b3.Museum.B3.dtos.AnswerMessageDTO;
import com.etec.team.museum_b3.Museum.B3.dtos.QuestionEventDTO;
import com.etec.team.museum_b3.Museum.B3.dtos.ScoreboardEvent;
import com.etec.team.museum_b3.Museum.B3.dtos.SystemEvent;
import com.etec.team.museum_b3.Museum.B3.entities.Question;
import com.etec.team.museum_b3.Museum.B3.entities.Quiz;
import com.etec.team.museum_b3.Museum.B3.entities.RoomState;
import com.etec.team.museum_b3.Museum.B3.repositories.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final QuizRepository quizRepository;
    private final SimpMessagingTemplate messaging;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);

    private final Map<String, RoomState> rooms = new ConcurrentHashMap<>();

    public String createRoom(Long quizId, String hostName){
        Quiz quiz = quizRepository.findById(quizId).orElseThrow();
        String code = randomCode();
        RoomState room = new RoomState(code, quiz);
        room.getPlayers().add(hostName);
        room.getScores().put(hostName, 0);
        rooms.put(code, room);
        return code;
    }

    public void join(String code, String username){
        RoomState roomState = get(code);
        roomState.getPlayers().add(username);
        roomState.getScores().putIfAbsent(username, 0);
        messaging.convertAndSend("/topic/rooms/"+code, new SystemEvent("PLAYER_JOINNED", username));
    }

    public void start(String code){
        RoomState room = get(code);
        if(room.isRunning()) return;
        room.setRunning(true);
        room.setCurrentIndex(-1);
        nextQuestion(room);
    }

    public void receiveAnswer(String code, AnswerMessageDTO msg){
        RoomState room = get(code);
        if(!room.isRunning())return;

        Question question = room.getQuiz().getQuestions().get(room.getCurrentIndex());
        if(question.getAnswer().getFormat() == msg.format()){
            room.getScores().merge(msg.username(), 1000, Integer::sum);
        }
    }

    private void nextQuestion(RoomState room){
        room.setCurrentIndex(room.getCurrentIndex() + 1);
        if(room.getCurrentIndex() >= room.getQuiz().getQuestions().size()){
            room.setRunning(false);
            messaging.convertAndSend("/topic/rooms/"+room.getCode(),
                    new ScoreboardEvent("SCOREBOARD", room.getScores(), true));
            return;
        }

        Question question = room.getQuiz().getQuestions().get(room.getCurrentIndex());
        var event = new QuestionEventDTO(
                "QUESTION",
                question.getText(),
                question.getAlternatives().stream()
                        .map(alternative -> new QuestionEventDTO.AlternativeDTO(alternative.getId(), alternative.getText(), alternative.getFormat()))
                        .toList(),
                room.getCurrentIndex()+1,
                room.getQuiz().getQuestions().size(),
                question.getTimeLimitSec()
        );
        messaging.convertAndSend("/topic/rooms/"+room.getCode(), event);

        cancelTimer(room);
        room.setCurrentTimer(scheduler.schedule(() -> {
            messaging.convertAndSend("/topic/rooms/"+room.getCode(),
                    new ScoreboardEvent("SCOREBOARD", room.getScores(), false));
            room.setCurrentTimer(scheduler.schedule(() -> nextQuestion(room), 3, TimeUnit.SECONDS));
        },
                question.getTimeLimitSec(), TimeUnit.SECONDS
        ));
    }

    private void cancelTimer(RoomState roomState){
        if(roomState.getCurrentTimer() != null) roomState.getCurrentTimer().cancel(false);

    }


    private RoomState get(String code){
        RoomState roomState = rooms.get(code);
        if(roomState == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "room not found");
        return roomState;
    }

    private String randomCode(){
        return UUID.randomUUID().toString().substring(0,6).toUpperCase();
    }
}
