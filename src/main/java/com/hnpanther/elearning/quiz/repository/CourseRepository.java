package com.hnpanther.elearning.quiz.repository;

import com.hnpanther.elearning.quiz.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    public Optional<Course> findByName(String name);
}
