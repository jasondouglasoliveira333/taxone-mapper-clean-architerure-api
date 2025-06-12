package br.com.jdo.taxone.mapper.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.jdo.taxone.mapper.infrastructure.entity.User;

@Repository
public interface JPAUserRepository extends JpaRepository<User, Integer>{

    User findByName(String username);

    @Query("update User u set u.name = :name where u.id = :id")
    @Modifying
    void updateName(@Param("name") String name, @Param("id") Integer id);

}
