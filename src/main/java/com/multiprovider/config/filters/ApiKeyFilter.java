package com.multiprovider.config.filters;

import com.multiprovider.config.authentication.ApiKeyAuthentication;
import com.multiprovider.config.managers.CostumeAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {

    private final String apiKey;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        CostumeAuthenticationManager costumeAuthenticationManager = new CostumeAuthenticationManager(apiKey);

        var headerKey = request.getHeader("x-api-key");
        if (headerKey == null) {
            // basic authentication
            filterChain.doFilter(request, response);
        }else{
            ApiKeyAuthentication apiKeyAuthentication = new ApiKeyAuthentication(headerKey);
            Authentication authentication = costumeAuthenticationManager.authenticate(apiKeyAuthentication);
            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid API Key");
            }
        }
    }
}
