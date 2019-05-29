package com.grsu.game.platform.web.filters;

import com.grsu.game.platform.web.services.UsersRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
@RequiredArgsConstructor
@Component
public class SessonFilter implements Filter {
    private final UsersRegistry usersRegistry;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Cookie sessionCookie = WebUtils.getCookie((HttpServletRequest) request, "session");

        if (sessionCookie != null && sessionCookie.getValue() != null && usersRegistry.isSessionValid(sessionCookie.getValue())) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/sign-up").forward(request, response);
        }
    }
}
