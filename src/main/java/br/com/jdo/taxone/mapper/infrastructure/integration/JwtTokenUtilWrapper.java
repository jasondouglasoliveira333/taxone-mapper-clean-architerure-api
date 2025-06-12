package br.com.jdo.taxone.mapper.infrastructure.integration;

import br.com.jdo.taxone.mapper.util.JwtTokenUtil;

public class JwtTokenUtilWrapper implements br.com.jdo.taxone.mapper.interfaces.integration.JwtTokenUtil{

    private JwtTokenUtil jwtTokenUtil;
    
    public JwtTokenUtilWrapper(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public String generateToken(String userName) {
        return jwtTokenUtil.generateToken(userName);
    }

}
