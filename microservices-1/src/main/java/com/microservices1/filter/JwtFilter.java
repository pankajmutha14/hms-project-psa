package com.microservices1.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.microservices1.client.AuthServiceFeignClient;
import com.microservices1.controller.WelcomeControllerApi;
import com.microservices1.dto.User;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final WelcomeControllerApi welcomeControllerApi;

    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private AuthServiceFeignClient authServiceFeignClient;


    JwtFilter(WelcomeControllerApi welcomeControllerApi) {
        this.welcomeControllerApi = welcomeControllerApi;
    }

  

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            String username = jwtService.validateTokenAndRetrieveSubject(jwt);
            

            User user = authServiceFeignClient.getUserByUsername(username,authHeader);
            
        
            
                var authToken = new UsernamePasswordAuthenticationToken(
                		user, null, Collections.singleton(new SimpleGrantedAuthority(user.getRole())));

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
       

        filterChain.doFilter(request, response);


    }
}
