package com.stnikolay.sweater.authentication;

import com.stnikolay.sweater.model.User;
import com.stnikolay.sweater.repository.UserRepository;
import com.stnikolay.sweater.service.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(value = "/test-application.properties")
public class RegistrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    public void successfulRegistration() throws Exception{
        mockMvc.perform(post("/join")
                    .param("username", "NewUser")
                    .param("password", "password")
                    .param("email", "new.user@example.com")
                    .with(csrf()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        User user = (User) userService.loadUserByUsername("NewUser");
        userService.deleteUser(user);
    }

    @Test
    public void deniedRegistration() throws Exception{
        mockMvc.perform(post("/join")
                    .param("username", "ExistUser")
                    .param("password", "password")
                    .param("email", "exist.user@example.com")
                    .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("User is already exist")));
    }

}
