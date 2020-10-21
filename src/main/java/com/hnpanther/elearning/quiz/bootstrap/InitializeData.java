package com.hnpanther.elearning.quiz.bootstrap;

import com.hnpanther.elearning.quiz.repository.RoleRepository;
import com.hnpanther.elearning.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitializeData implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

//
//        Role role1 = new Role();
//        role1.setName("Admin");
//
//        Role role2 = new Role();
//        role2.setName("Teacher");
//
//        Role role3 = new Role();
//        role3.setName("User");
//
//        roleRepository.saveAll(Arrays.asList(role1, role2, role3));
//
//        String username = "User1";
//        String password = "password";
//        String firstName = "name";
//        String lastName = "last name";
//
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//
//        Role findedUserRole = roleRepository.findByName("User").get();
//        Role findedAdminRole = roleRepository.findByName("Admin").get();
//
//        user.addRole(findedUserRole);
//        user.addRole(findedAdminRole);
//
//        userRepository.save(user);

    }
}
