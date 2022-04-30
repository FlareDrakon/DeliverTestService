package com.web.app.dao;

import com.auth.app.dao.RoleRepository;
import com.auth.app.dao.UserRepository;
import com.auth.app.dao.entity.RoleEntity;
import com.auth.app.dao.entity.UserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Transactional
    public void adminUserAddTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(passwordEncoder.encode("12"));
        userEntity.setUsername("any");
        RoleEntity roleEntity = roleRepository.getOne(1L);
        Assert.assertNotNull(roleEntity);
        userEntity.setRoles(Collections.singleton(roleEntity));
        userRepository.save(userEntity);
        UserEntity byUsername = userRepository.getByUsername("any");
        assertNotNull(byUsername);
    }
}
