package com.wallet.repository;

import com.wallet.entity.User;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {

    private static final String EMAIL = "email@test.com";

    @Autowired
    UserRepository userRepository;

    @BeforeAll
    public void setUp(){
        User u = new User();
        u.setName("Set up user");
        u.setPassword("Senha123");
        u.setEmail(EMAIL);
        userRepository.save(u);

    }

    @AfterAll
    public void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    public void testSave(){
        User u = new User();
        u.setName("Teste");
        u.setPassword("123456");
        u.setEmail("test@test.com");
        User response = userRepository.save(u);
        assertNotNull(response);
    }

    @Test
    public void testFindByEmail(){
        Optional<User> response = userRepository.findByEmailEquals(EMAIL);

        assertTrue(response.isPresent());
        assertEquals(response.get().getEmail(), EMAIL);
    }
}
