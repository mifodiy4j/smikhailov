package ru.job4j.application_2513;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        if ("Admin".equals(role)) {
            response.sendRedirect(String.format("%s/user/role/admin", request.getContextPath()));

        } else if ("User".equals(role)) {
            response.sendRedirect(String.format("%s/user/role/user", request.getContextPath()));
        }

    }

    @Override
    public void destroy() {

    }
}
