package com.ofss.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {

        // Set 401 status
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Custom message
        response.getWriter().write("401..(Unauthorised) Username or Password is incorrect. Please try again!");
    }
}
