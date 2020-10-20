package com.hnpanther.elearning.quiz.repository;

import com.hnpanther.elearning.quiz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
