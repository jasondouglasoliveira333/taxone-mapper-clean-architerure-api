package br.com.jdo.taxone.mapper.interfaces.controller;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.jdo.taxone.mapper.domain.entity.AutenticationRequest;
import br.com.jdo.taxone.mapper.infrastructure.integration.JwtUserDetailsUseCaseWrapper;
import br.com.jdo.taxone.mapper.interfaces.integration.AuthenticationManager;
import br.com.jdo.taxone.mapper.interfaces.integration.JwtTokenUtil;

public class AuthenticationController {
    
    private AuthenticationManager authenticationManager;
    
    private JwtTokenUtil jwtTokenUtil;
    
    private JwtUserDetailsUseCaseWrapper userDetailsService;
    
    public JwtUserDetailsUseCaseWrapper userDetailsService() {
        return userDetailsService;
    }
    
    public JwtTokenUtil jwtTokenUtil() {
        return jwtTokenUtil;
    }
    
    public AuthenticationManager authenticationManager() {
        return authenticationManager;
    }
    
    
    public Object createAuthenticationToken(AutenticationRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService().loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil().generateToken(userDetails.getUsername());
        return token;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            Object o = authenticationManager().authenticate(username, password);
            System.out.println("authenticated:" + o);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}