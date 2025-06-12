package br.com.jdo.taxone.mapper.interfaces.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import br.com.jdo.taxone.mapper.domain.entity.EmailDomain;
import br.com.jdo.taxone.mapper.domain.entity.PageResponse;
import br.com.jdo.taxone.mapper.domain.enums.EmailType;
import br.com.jdo.taxone.mapper.infrastructure.entity.Email;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPAEmailRepository;
import br.com.jdo.taxone.mapper.interfaces.mapper.EmailMapper;

public class EmailRepositoryImpl implements EmailRepository{
    
    JPAEmailRepository emailRepository;

    public EmailRepositoryImpl(JPAEmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<EmailDomain> findByTypeIn(List<EmailType> et){
        return emailRepository.findByTypeIn(et).stream().map(EmailMapper::map).collect(Collectors.toList());
    }

    public PageResponse<EmailDomain> findAll(Integer page, Integer size){
        Page<Email> ePage = emailRepository.findAll(PageRequest.of(page, size, Direction.DESC, "id"));
        PageResponse<EmailDomain> sfResponse = new PageResponse<>();
        sfResponse.setContent(ePage.getContent().stream().map(EmailMapper::map).collect(Collectors.toList()));
        sfResponse.setTotalPages(ePage.getTotalPages());
        return sfResponse;
    }

    public void deleteById(Integer emailId) {
        emailRepository.deleteById(emailId);
    }

    public void save(EmailDomain eDomain) {
        Email e = EmailMapper.map(eDomain);
        emailRepository.save(e);
    }

}
