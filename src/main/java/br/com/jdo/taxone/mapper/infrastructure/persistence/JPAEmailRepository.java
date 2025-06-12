package br.com.jdo.taxone.mapper.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jdo.taxone.mapper.domain.enums.EmailType;
import br.com.jdo.taxone.mapper.infrastructure.entity.Email;

public interface JPAEmailRepository extends JpaRepository<Email, Integer>{

    List<Email> findByTypeIn(List<EmailType> et);

}
