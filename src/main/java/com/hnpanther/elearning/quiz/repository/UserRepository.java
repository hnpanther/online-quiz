package com.hnpanther.elearning.quiz.repository;

import com.hnpanther.elearning.quiz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);

    public Optional<User> findByUsernameAndRoles_Name(String username, String roleName);

}
