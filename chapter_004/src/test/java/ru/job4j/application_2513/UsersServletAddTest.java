package ru.job4j.application_2513;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersServletAddTest {
    @Test
    public void addUser() throws ServletException, IOException {
        UsersServletAdd usersServletAdd = new UsersServletAdd();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String name = "bbb";
        String login = "bbb";
        String email = "bbb@bbb";
        String createDate = "2010-10-10";

        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("createDate")).thenReturn(createDate);

        usersServletAdd.doPost(request, response);

        List<Integer> listId = UserStore.INSTANCE.getListId();
        Collections.sort(listId);

        User userWhomAdd = new User(name, login, email, createDate);
        userWhomAdd.setRole("User");
        userWhomAdd.setCreateDate(createDate + " 00:00:00");

        User user = UserStore.INSTANCE.selectById(listId.get(listId.size() - 1));
        assertThat(user, is(userWhomAdd));
    }

}