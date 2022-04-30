package com.web.app.rest.controller;

import com.web.app.dao.RoleRepository;
import com.web.app.dao.UserRepository;
import com.web.app.dao.entity.RoleEntity;
import com.web.app.dao.entity.UserEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.Collections;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CourierControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private WebApplicationContext context;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Before
    @Transactional
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setPassword(passwordEncoder.encode("12"));
        userEntity2.setUsername("courier");
        RoleEntity roleEntity2 = roleRepository.getByName("ROLE_COURIER");
        Assert.assertNotNull(roleEntity2);
        userEntity2.setRoles(Collections.singleton(roleEntity2));
        userRepository.save(userEntity2);
    }

    @Test
    public void testGetList() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/courier/list").with(httpBasic("admin", "12345"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(request().asyncStarted())
                .andDo(print())
                .andReturn();
        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userDtoList[0].name").value("courier"));
    }
}
