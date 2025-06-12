package br.com.jdo.taxone.mapper.interfaces.repository;

import br.com.jdo.taxone.mapper.domain.entity.PageResponse;
import br.com.jdo.taxone.mapper.domain.entity.UserDomain;

public interface UserRepository{

    UserDomain findByName(String username);

    void updateName(String name, Integer id);

    void deleteById(Integer id);

    PageResponse<UserDomain> findAll(Integer page, Integer size);

    UserDomain getOne(Integer id);

    void save(UserDomain u);

}
