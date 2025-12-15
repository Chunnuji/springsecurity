package com.example.practiceSecurity.Config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        OAuth2AuthenticationToken token =
                (OAuth2AuthenticationToken) authentication;

        OAuth2User user = token.getPrincipal();

        String name = user.getAttribute("name");
        String email = user.getAttribute("email");

        // TODO: generate JWT here
        String jwtToken = "dummy-jwt-token";

        response.setContentType("application/json");
        response.getWriter().write("""
            {
              "name": "%s",
              "email": "%s",
              "token": "%s"
            }
        """.formatted(name, email, jwtToken));

        response.getWriter().flush();
    }
}
