package ru.job4j.application_2513;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersServletEditLoginTest {

    @Test
    public void editLoginForItemWhoseId2() throws ServletException, IOException {

        UsersServletEditLogin usersServletEditLogin = new UsersServletEditLogin();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String id = "2";
        String newLogin = "22";

        when(request.getParameter("id")).thenReturn(id);
        when(request.getParameter("login")).thenReturn(newLogin);

        usersServletEditLogin.doPost(request, response);

        User user = UserStore.getInstance().selectById(Integer.parseInt(id));

        assertThat(user.getLogin(), is(newLogin));
    }

}