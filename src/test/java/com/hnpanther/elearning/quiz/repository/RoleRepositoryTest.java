package com.hnpanther.elearning.quiz.repository;

import com.hnpanther.elearning.quiz.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    Set<Role> roles = new HashSet<>();


    @BeforeEach
    void setUp() {
        Role role1 = new Role();
        role1.setName("Admin");

        Role role2 = new Role();
        role2.setName("User");

        Role role3= new Role();
        role3.setName("Teacher");

        this.roles.addAll(Arrays.asList(role1,role2,role3));


    }

    @Test
    public void createNewRoleTest() {

        List<Role> saved = roleRepository.saveAll(roles);

        assertEquals(3, roleRepository.findAll().size());

    }

    @Test
    public void editRoleTest() {

        String roleName = "Admin";
        String changedName = "User";

        Role role = new Role();
        role.setName(roleName);
        this.roleRepository.save(role);

        Role findRole = this.roleRepository.findByName(roleName).get();

        findRole.setName(changedName);
        roleRepository.save(findRole);

        assertNotNull(roleRepository.findByName(changedName).get());


    }

    @Test
    public void deleteRoleTest() {

        String roleName = "Test";

        Role role = new Role();
        role.setName(roleName);
        roleRepository.save(role);

        assertEquals(1,roleRepository.findAll().size());

        roleRepository.deleteByName(roleName);

        assertEquals(0, roleRepository.findAll().size());

    }


}