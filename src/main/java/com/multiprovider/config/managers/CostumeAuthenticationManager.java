package com.multiprovider.config.managers;

import com.multiprovider.config.providers.CostumeAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

//@Component
@AllArgsConstructor
public class CostumeAuthenticationManager implements AuthenticationManager {

    private final String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        CostumeAuthenticationProvider costumeAuthenticationProvider = new CostumeAuthenticationProvider(key);
        if(costumeAuthenticationProvider.supports(authentication.getClass())){
            return costumeAuthenticationProvider.authenticate(authentication);
        }
        return authentication;
    }
}
