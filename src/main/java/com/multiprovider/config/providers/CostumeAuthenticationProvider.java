package com.multiprovider.config.providers;

import com.multiprovider.config.authentication.ApiKeyAuthentication;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

//@Component
@AllArgsConstructor
public class CostumeAuthenticationProvider implements AuthenticationProvider {

    private final String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ApiKeyAuthentication apiKeyAuthentication = (ApiKeyAuthentication) authentication;
        if(key.equals(apiKeyAuthentication.getApiKey())){
            apiKeyAuthentication.setAuthenticated(true);
            return apiKeyAuthentication;
        }
        throw new BadCredentialsException("Opps");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthentication.class.isAssignableFrom(authentication);
    }
}
