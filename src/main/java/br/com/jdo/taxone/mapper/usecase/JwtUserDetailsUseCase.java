package br.com.jdo.taxone.mapper.usecase;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.jdo.taxone.mapper.domain.entity.POCUser;
import br.com.jdo.taxone.mapper.domain.entity.UserDomain;
import br.com.jdo.taxone.mapper.interfaces.repository.UserRepository;

public class JwtUserDetailsUseCase implements UserDetailsService {
    
    private UserRepository userRepository;
    
    public JwtUserDetailsUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDomain u = userRepository.findByName(username);
        if (u != null) {
            return new POCUser(u.getId(), u.getName(), u.getPassword(), new ArrayList<>());
        }else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        
//        if ("javainuse".equals(username)) {
//            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//                    new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
    }
}