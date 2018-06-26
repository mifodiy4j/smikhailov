package ru.job4j.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.services.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@TestPropertySource("classpath:application.properties")
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenUnavailableForRoleUser() throws Exception {
        this.mvc
                .perform(
                        get("/users").accept(MediaType.TEXT_HTML)
                ).andExpect(
                    status().isForbidden()
                );
    }

    @Test
    @WithMockUser(username = "root", roles = {"ADMIN"})
    public void whenGetCarsThenPageCars() throws Exception {
        this.mvc
                .perform(
                        get("/users").accept(MediaType.TEXT_HTML)
                ).andExpect(
                    status().isOk()
                ).andExpect(
                    view().name("users")
                );
    }
}