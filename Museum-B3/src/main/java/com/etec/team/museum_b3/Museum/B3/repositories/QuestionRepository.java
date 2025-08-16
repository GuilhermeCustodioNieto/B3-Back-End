package com.etec.team.museum_b3.Museum.B3.repositories;

import com.etec.team.museum_b3.Museum.B3.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
