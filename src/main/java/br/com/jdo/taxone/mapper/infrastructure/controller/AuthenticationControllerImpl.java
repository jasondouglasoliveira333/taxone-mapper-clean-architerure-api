package br.com.jdo.taxone.mapper.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jdo.taxone.mapper.domain.entity.AutenticationRequest;
import br.com.jdo.taxone.mapper.domain.entity.AutenticationResponse;
import br.com.jdo.taxone.mapper.infrastructure.integration.JwtUserDetailsUseCaseWrapper;
import br.com.jdo.taxone.mapper.interfaces.controller.AuthenticationController;
import br.com.jdo.taxone.mapper.interfaces.integration.AuthenticationManager;
import br.com.jdo.taxone.mapper.interfaces.integration.JwtTokenUtil;

@RestController
@CrossOrigin
public class AuthenticationControllerImpl extends AuthenticationController{
    
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
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

    
    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AutenticationRequest authenticationRequest) throws Exception {
        final String token = (String) super.createAuthenticationToken(authenticationRequest); 
        return ResponseEntity.ok(new AutenticationResponse(token));
    }

}