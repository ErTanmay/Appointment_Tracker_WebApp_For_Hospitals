package com.tsl.appointmenttracker.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import java.io.IOException;

@Configuration
public class CustomLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        try {

            ServletContext context = request.getServletContext();
            context.setAttribute("logout",true);

            response.sendRedirect("admin/home");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
