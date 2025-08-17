package com.etec.team.museum_b3.Museum.B3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_question")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String text;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private List<Alternative> alternatives = new ArrayList<>();

    @ManyToOne
    private Alternative answer;

    private int timeLimitSec = 15;

    public Question(String text, List<Alternative> alternatives, Alternative answer) {
        this.text = text;
        this.alternatives = alternatives;
        this.answer = answer;
    }
}
