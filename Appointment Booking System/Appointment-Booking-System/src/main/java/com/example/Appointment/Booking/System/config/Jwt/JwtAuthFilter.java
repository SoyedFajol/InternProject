package com.example.Appointment.Booking.System.config.Jwt;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Extract JWT token from the "Authorization" header
        String token = getJwtFromRequest(request);

        // Validate the token
        if (token != null && jwtTokenProvider.validateToken(token)) {
            // Extract email from the token
            String email = jwtTokenProvider.getEmailFromToken(token);

            // Create authentication object with roles
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    new User(email, "", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))), // Default role USER
                    null, // No credentials
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
            );

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Set the authentication object in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }

    // Extract JWT token from "Authorization" header
    private String getJwtFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); // Remove "Bearer " prefix
        }
        return null;
    }
}