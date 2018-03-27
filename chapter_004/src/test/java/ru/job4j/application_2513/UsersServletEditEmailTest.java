package ru.job4j.application_2513;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersServletEditEmailTest {

    @Test
    public void editEmailForItemWhoseId2() throws ServletException, IOException {

        UsersServletEditEmail usersServletEditEmail = new UsersServletEditEmail();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String id = "2";
        String newEmail = "2@2";

        when(request.getParameter("id")).thenReturn(id);
        when(request.getParameter("email")).thenReturn(newEmail);

        usersServletEditEmail.doPost(request, response);

        User user = UserStore.getInstance().selectById(Integer.parseInt(id));

        assertThat(user.getEmail(), is(newEmail));
    }
}