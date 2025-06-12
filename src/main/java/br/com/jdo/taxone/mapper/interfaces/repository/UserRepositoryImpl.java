package br.com.jdo.taxone.mapper.interfaces.repository;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.jdo.taxone.mapper.domain.entity.PageResponse;
import br.com.jdo.taxone.mapper.domain.entity.UserDomain;
import br.com.jdo.taxone.mapper.infrastructure.entity.User;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPAUserRepository;
import br.com.jdo.taxone.mapper.interfaces.mapper.UserMapper;

public class UserRepositoryImpl implements UserRepository{

    JPAUserRepository userRepository;
    
    public UserRepositoryImpl(JPAUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDomain findByName(String username) {
        return UserMapper.map(userRepository.findByName(username));
    }

    public void updateName(String name, Integer id) {
        userRepository.updateName(name, id);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public PageResponse<UserDomain> findAll(Integer page, Integer size){
        Page<User> uPage = userRepository.findAll(PageRequest.of(page, size));
        PageResponse<UserDomain> uResponse = new PageResponse<>();
        uResponse.setContent(uPage.getContent().stream().map(UserMapper::map).collect(Collectors.toList())); 
        return uResponse; 
    }

    public UserDomain getOne(Integer id) {
        return UserMapper.map(userRepository.getOne(id));
    }

    public void save(UserDomain u) {
        userRepository.save(UserMapper.map(u));
    }

}
