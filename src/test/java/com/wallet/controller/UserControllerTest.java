package com.wallet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.dto.UserDTO;
import com.wallet.entity.User;
import com.wallet.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

    private static final String EMAIL = "email@teste.com";
    private static final String NAME = "User Test";
    private static final String PASSWORD = "123456";
    private static final String URL = "/user";

    @MockBean
    UserService service;

    @Autowired
    MockMvc mvc;

    @Test
    public void testSave() throws Exception {

        BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getUserMock());

        mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayLoad())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    public User getUserMock(){
        User u = new User();
        u.setEmail(EMAIL);
        u.setName(NAME);
        u.setPassword(PASSWORD);

        return u;
    }

    public String getJsonPayLoad() throws JsonProcessingException {
        UserDTO dto = new UserDTO();
        dto.setEmail(EMAIL);
        dto.setName(NAME);
        dto.setPassword(PASSWORD);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dto);

    }

}
