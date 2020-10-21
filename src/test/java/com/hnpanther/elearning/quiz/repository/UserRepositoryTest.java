package com.hnpanther.elearning.quiz.repository;

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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    EntityManager entityManager;

    @BeforeEach
    void setUp() {

        User defaultUser = new User();
        defaultUser.setUsername("default");
        defaultUser.setPassword("password");

        Role adminRole = new Role();
        adminRole.setName("Admin");

        Role teacherRole = new Role();
        teacherRole.setName("Teacher");

        Role userRole = new Role();
        userRole.setName("User");

        roleRepository.saveAll(Arrays.asList(adminRole, teacherRole, userRole));


        defaultUser.addRole(userRole);
        userRole.addUser(defaultUser);
        userRepository.save(defaultUser);


        entityManager.flush();
        entityManager.clear();

    }

    @Test
    public void createNewUserWithExistsRoleTest() {

        Optional<Role> role = roleRepository.findByName("User");
        Assertions.assertTrue(role.isPresent());
        Role userRole = role.get();

        role = roleRepository.findByName("Admin");
        Assertions.assertTrue(role.isPresent());
        Role adminRole = role.get();

        String username = "User1";
        String password = "password";
        String firstName = "first name";
        String lastName = "last name";

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        userRole.getUsers().add(user);
        adminRole.getUsers().add(user);

        user.getRoles().add(userRole);
        user.getRoles().add(adminRole);


        userRepository.save(user);

        entityManager.flush();
        entityManager.clear();

        Optional<User> optionalSavedUser = userRepository.findByUsername(username);
        Assertions.assertTrue(optionalSavedUser.isPresent());
        User savedUser = optionalSavedUser.get();

        assertEquals(2, savedUser.getRoles().size());
        Assertions.assertTrue(savedUser.getRoles().contains(userRole));

        Optional<Role> optionalfindedRole = roleRepository.findByName("User");
        Assertions.assertTrue(optionalfindedRole.isPresent());
        Role findedRole = optionalfindedRole.get();
        Assertions.assertTrue(findedRole.getUsers().contains(savedUser));

    }


    @Test
    public void addRoleToUser() {

        Optional<User> optionalUser = userRepository.findByUsername("default");
        Assertions.assertTrue(optionalUser.isPresent());
        User user = optionalUser.get();

        Optional<Role> optionalRole = roleRepository.findByName("Teacher");
        assertTrue(optionalRole.isPresent());
        Role teacherRole = optionalRole.get();

        user.addRole(teacherRole);
        teacherRole.addUser(user);

        userRepository.save(user);

        entityManager.flush();
        entityManager.clear();

        Optional<User> optionalFindUser =userRepository.findByUsername("default");
        assertTrue(optionalFindUser.isPresent());
        User findedUser = optionalFindUser.get();

        assertTrue(findedUser.getRoles().contains(teacherRole));
        assertEquals(2, findedUser.getRoles().size());
    }

    @Test
    public void removeRoleFromUser() {
        Optional<User> optionalUser = this.userRepository.findByUsername("default");
        assertTrue(optionalUser.isPresent());
        User user = optionalUser.get();

        Optional<Role> optionalRole = this.roleRepository.findByName("User");
        assertTrue(optionalRole.isPresent());
        Role userRole = optionalRole.get();

        assertTrue(user.getRoles().contains(userRole));
        assertTrue(userRole.getUsers().contains(user));

        user.removeRole(userRole);
        userRole.removeUser(user);

        this.userRepository.save(user);

        this.entityManager.flush();
        this.entityManager.clear();

        Optional<User> optionalUpdatedUser = this.userRepository.findByUsername("default");
        assertTrue(optionalUpdatedUser.isPresent());
        User updatedUser = optionalUser.get();

        Optional<Role> optionalUpdatedRole = this.roleRepository.findByName("User");
        assertTrue(optionalUpdatedRole.isPresent());
        Role updatedUserRole = optionalRole.get();

        assertFalse(updatedUser.getRoles().contains(updatedUserRole));
        assertFalse(updatedUserRole.getUsers().contains(updatedUser));
        assertEquals(0, updatedUser.getRoles().size());
        assertEquals(0, updatedUserRole.getUsers().size());

    }


    @Test
    public void updateUserDetails() {

        Optional<User> optionalUser = this.userRepository.findByUsername("default");
        assertTrue(optionalUser.isPresent());
        User user = optionalUser.get();

        user.setFirstName("first_name");

        this.userRepository.save(user);

        entityManager.flush();
        entityManager.clear();

        Optional<User> optionalUpdatedUser = this.userRepository.findByUsername("default");
        assertTrue(optionalUpdatedUser.isPresent());
        User updatedUser = optionalUser.get();
        assertEquals("first_name", updatedUser.getFirstName());
    }


}