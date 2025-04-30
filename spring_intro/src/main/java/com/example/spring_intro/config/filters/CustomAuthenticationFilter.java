package com.example.spring_intro.config.filters;

import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null) {
            filterChain.doFilter(request, response);
            return;
        }

        userRepository.findByUsername(username).ifPresent(user -> {
            if (passwordEncoder.matches(password, user.getPassword())) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        });

        filterChain.doFilter(request, response);
    }
}