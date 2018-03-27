package ru.job4j.application_2513;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.booleanThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersServletDeleteTest {

    @Test
    public void deleteItemWhoseId2() throws ServletException, IOException {

        UsersServletDelete usersServletDelete = new UsersServletDelete();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String id = "2";

        when(request.getParameter("id")).thenReturn(id);

        usersServletDelete.doPost(request, response);

        List<Integer> listId = UserStore.getInstance().getListId();

        boolean result = false;

        for (int idInList : listId) {
            if (idInList == Integer.parseInt(id)) {
                result = true;
            }
        }

        assertThat(result, is(false));
    }

}