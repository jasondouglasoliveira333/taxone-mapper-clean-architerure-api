package br.com.jdo.taxone.mapper.interfaces.repository;

import java.util.List;

import br.com.jdo.taxone.mapper.domain.entity.EmailDomain;
import br.com.jdo.taxone.mapper.domain.entity.PageResponse;
import br.com.jdo.taxone.mapper.domain.enums.EmailType;

public interface EmailRepository {

    List<EmailDomain> findByTypeIn(List<EmailType> et);

    PageResponse<EmailDomain> findAll(Integer page, Integer size);

    void deleteById(Integer emailId);

    void save(EmailDomain e);

}
