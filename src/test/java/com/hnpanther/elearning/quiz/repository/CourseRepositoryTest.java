package com.hnpanther.elearning.quiz.repository;

import com.hnpanther.elearning.quiz.model.Course;
import com.hnpanther.elearning.quiz.model.Role;
import com.hnpanther.elearning.quiz.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        Role adminRole = new Role();
        adminRole.setName("Admin");
        Role teacherRole = new Role();
        teacherRole.setName("Teacher");
        Role userRole = new Role();
        userRole.setName("User");

        this.roleRepository.saveAll(Arrays.asList(adminRole, teacherRole, userRole));


        User teacher = new User();
        teacher.setUsername("teacher1");
        teacher.setPassword("password");
        teacher.addRole(teacherRole);
        teacherRole.addUser(teacher);

        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("password");
        user1.addRole(userRole);
        userRole.addUser(user1);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("password");
        user2.addRole(userRole);
        userRole.addUser(user2);

        this.userRepository.saveAll(Arrays.asList(teacher, user1, user2));


    }

    @Test
    public void createNewCourse() {

        Optional<User> userOptional =
                userRepository.findByUsernameAndRoles_Name("teacher1", "Teacher");
        Assertions.assertTrue(userOptional.isPresent());
        User teacher = userOptional.get();

        Course course = new Course();
        course.setName("Math");
        course.setGrade(12);
        course.setTeacher(teacher);
        teacher.addCourse(course);

        courseRepository.save(course);

        entityManager.flush();
        entityManager.clear();

        Optional<Course> optionalCourse = courseRepository.findByName("Math");
        Assertions.assertTrue(optionalCourse.isPresent());
        Course mathCourse = optionalCourse.get();
        assertEquals("teacher1", mathCourse.getTeacher().getUsername());

    }


    @Test
    public void editCourseTest() {
        Optional<User> userOptional =
                userRepository.findByUsernameAndRoles_Name("teacher1", "Teacher");
        Assertions.assertTrue(userOptional.isPresent());
        User teacher = userOptional.get();

        Course course = new Course();
        course.setName("Math");
        course.setGrade(12);
        course.setTeacher(teacher);
        teacher.addCourse(course);

        courseRepository.save(course);

        entityManager.flush();
        entityManager.clear();

        Optional<Course> optionalCourse = courseRepository.findByName("Math");
        Assertions.assertTrue(optionalCourse.isPresent());
        Course mathCourse = optionalCourse.get();

        mathCourse.setName("Math Course");
        mathCourse.getTeacher().removeCourse(mathCourse);
        mathCourse.setTeacher(null);

        courseRepository.save(mathCourse);

        entityManager.flush();
        entityManager.clear();

        Optional<Course> optionalUpdatedCourse = courseRepository.findByName("Math Course");
        Assertions.assertTrue(optionalUpdatedCourse.isPresent());
        Course updatedCourse = optionalCourse.get();

        Assertions.assertNull(updatedCourse.getTeacher());

    }

    @Test
    public void deleteCourseTest() {
        Optional<User> userOptional =
                userRepository.findByUsernameAndRoles_Name("teacher1", "Teacher");
        Assertions.assertTrue(userOptional.isPresent());
        User teacher = userOptional.get();

        Course course = new Course();
        course.setName("Math");
        course.setGrade(12);
        course.setTeacher(teacher);
        teacher.addCourse(course);

        courseRepository.save(course);

        entityManager.flush();
        entityManager.clear();

        Optional<Course> optionalCourse = courseRepository.findByName("Math");
        Assertions.assertTrue(optionalCourse.isPresent());
        Course mathCourse = optionalCourse.get();

        mathCourse.getTeacher().removeCourse(mathCourse);
        mathCourse.setTeacher(null);

        courseRepository.delete(mathCourse);

        entityManager.flush();
        entityManager.clear();

        Optional<Course> deletedCourse = courseRepository.findByName("Math");
        Assertions.assertFalse(deletedCourse.isPresent());

        Optional<User> updatedUserOptional =
                userRepository.findByUsernameAndRoles_Name("teacher1", "Teacher");
        Assertions.assertTrue(updatedUserOptional.isPresent());
        User updatedTeacher = updatedUserOptional.get();
        assertEquals(0, updatedTeacher.getCourses().size());

    }

















}