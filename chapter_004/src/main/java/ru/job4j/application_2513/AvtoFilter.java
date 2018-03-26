package ru.job4j.application_2513;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AvtoFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String role = (String) request.getSession().getAttribute("role");
        if (role != null && role.equals("Admin")) {
            //response.sendRedirect("/WEB-INF/views/UserViewForAdmin.jsp");
            request.getRequestDispatcher("/WEB-INF/views/UserViewForAdmin.jsp").forward(request, response);
            //response.sendRedirect(String.format("%s/", request.getContextPath()));
            //chain.doFilter(req, resp);

        } else if (role != null && role.equals("User")) {
            request.getRequestDispatcher("/WEB-INF/views/UserViewForUser.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
