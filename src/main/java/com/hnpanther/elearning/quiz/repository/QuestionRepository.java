package com.hnpanther.elearning.quiz.repository;

import com.hnpanther.elearning.quiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
