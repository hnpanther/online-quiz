package com.hnpanther.elearning.quiz.repository;

import com.hnpanther.elearning.quiz.model.UserQuizAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuizAnswerRepository extends JpaRepository<UserQuizAnswer, Long> {
}
