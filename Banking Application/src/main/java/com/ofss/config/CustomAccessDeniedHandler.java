package com.ofss.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, 
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) 
                       throws IOException, ServletException {
        // Set HTTP 403 status
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        // Write a custom error message
        response.getWriter().write("Access Denied: You do not have permission to access this resource.");
    }
}
