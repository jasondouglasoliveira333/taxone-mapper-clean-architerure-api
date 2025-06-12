package br.com.jdo.taxone.mapper.infrastructure.integration;

import org.springframework.security.core.userdetails.UserDetails;

import br.com.jdo.taxone.mapper.usecase.JwtUserDetailsUseCase;

public class JwtUserDetailsUseCaseWrapper {
    
    private JwtUserDetailsUseCase userDetailsService;

    public JwtUserDetailsUseCaseWrapper(JwtUserDetailsUseCase userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public UserDetails loadUserByUsername(String username) {
        return userDetailsService.loadUserByUsername(username);
    }

}
