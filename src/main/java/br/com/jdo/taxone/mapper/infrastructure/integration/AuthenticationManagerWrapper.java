package br.com.jdo.taxone.mapper.infrastructure.integration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


public class AuthenticationManagerWrapper implements br.com.jdo.taxone.mapper.interfaces.integration.AuthenticationManager{

    private AuthenticationManager authenticationManager;
    
    public AuthenticationManagerWrapper(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public Object authenticate(String userName, String password) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
    }

}
