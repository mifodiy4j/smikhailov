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

public class UsersServletEditRoleTest {

    @Test
    public void editRoleForItemWhoseId2() throws ServletException, IOException {

        UsersServletEditRole usersServletEditRole = new UsersServletEditRole();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String id = "2";
        String newRole = "1";

        when(request.getParameter("id")).thenReturn(id);
        when(request.getParameter("role")).thenReturn(newRole);

        usersServletEditRole.doPost(request, response);
        User user = UserStore.INSTANCE.selectById(Integer.parseInt(id));
        assertThat(user.getRole(), is("Admin"));
    }

}