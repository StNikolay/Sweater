package com.stnikolay.sweater.posting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(value = "/test-application.properties")
@Sql(value = {"classpath:/prepareDatabase.sql"})
class PostTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails(value = "ExistUser")
    @Sql(value = "classpath:/post/deletePost.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void successfulPost() throws Exception {
        this.mockMvc.perform(post("/feed")
                    .param("title", "Title")
                    .param("text", "Text")
                    .with(csrf()))
                .andExpect(status().is3xxRedirection());

        this.mockMvc.perform(get("/feed"))
                .andDo(print())
                .andExpect(xpath("/html/body/div/div/div[2]/h5").nodeCount(1))
                .andExpect(xpath("/html/body/div/div/div[2]/h5").string(containsString("Title")));
    }

    @Test
    @WithUserDetails(value = "ExistUser")
    public void missingTitlePost() throws Exception {
        this.mockMvc.perform(post("/feed")
                .param("title", "")
                .param("text", "Text")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());

        this.mockMvc.perform(get("/feed"))
                .andDo(print())
                .andExpect(xpath("/html/body/div/div/div[2]/h5").nodeCount(0));
    }

    @Test
    @WithUserDetails(value = "ExistUser")
    @Sql(value = "classpath:/post/addPost.sql")
    public void deletePost() throws Exception {
        this.mockMvc.perform(get("/feed"))
                .andExpect(xpath("/html/body/div/div/div[2]/h5").nodeCount(1));

        this.mockMvc.perform(post("/feed/1")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());

        this.mockMvc.perform(get("/feed"))
                .andDo(print())
                .andExpect(xpath("/html/body/div/div/div[2]/h5").nodeCount(0));
    }

}