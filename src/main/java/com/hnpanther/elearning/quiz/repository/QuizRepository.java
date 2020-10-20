package com.hnpanther.elearning.quiz.repository;

import com.hnpanther.elearning.quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
