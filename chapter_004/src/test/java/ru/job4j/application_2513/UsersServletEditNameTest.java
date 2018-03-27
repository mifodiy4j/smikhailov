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

public class UsersServletEditNameTest {

    @Test
    public void editNameForItemWhoseId2() throws ServletException, IOException {

        UsersServletEditName usersServletEditName = new UsersServletEditName();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String id = "2";
        String newName = "22";

        when(request.getParameter("id")).thenReturn(id);
        when(request.getParameter("name")).thenReturn(newName);

        usersServletEditName.doPost(request, response);

        User user = UserStore.getInstance().selectById(Integer.parseInt(id));

        assertThat(user.getName(), is(newName));
    }

}