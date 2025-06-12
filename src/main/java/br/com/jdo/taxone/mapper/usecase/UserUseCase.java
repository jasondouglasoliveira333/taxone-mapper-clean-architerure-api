package br.com.jdo.taxone.mapper.usecase;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import br.com.jdo.taxone.mapper.domain.entity.PageResponse;
import br.com.jdo.taxone.mapper.domain.entity.UserDomain;
import br.com.jdo.taxone.mapper.interfaces.repository.UserRepository;

public class UserUseCase {

    private UserRepository userRepository;
    
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserUseCase(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public PageResponse<UserDomain> findAll(Integer page, Integer size) {
        PageResponse<UserDomain> prUser = userRepository.findAll(page, size);
        return prUser;
    }
    
    public UserDomain getOne(Integer id) {
        UserDomain u = userRepository.getOne(id);
        return u;
    }
    
    @Transactional
    public void save(UserDomain u) {
        if (u.getPassword() != null) {//updating the password or insert a new user
            u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
            if (u.getId() == null) {
                u.setCreationDate(LocalDateTime.now());
            }
            userRepository.save(u);
        }else {
            userRepository.updateName(u.getName(), u.getId());
        }
    }
    
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

}
