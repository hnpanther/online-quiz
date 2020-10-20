package com.hnpanther.elearning.quiz.repository;

import com.hnpanther.elearning.quiz.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
