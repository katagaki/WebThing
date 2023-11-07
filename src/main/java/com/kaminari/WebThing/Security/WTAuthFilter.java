package com.kaminari.WebThing.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class WTAuthFilter extends OncePerRequestFilter {
    private final AuthenticationManager authManager;

    public WTAuthFilter(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String username = request.getHeader("username");
        final String password = request.getHeader("password");
        authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        filterChain.doFilter(request, response);
    }
}
