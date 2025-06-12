package br.com.jdo.taxone.mapper.usecase;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.jdo.taxone.mapper.domain.entity.EmailDomain;
import br.com.jdo.taxone.mapper.domain.entity.PageResponse;
import br.com.jdo.taxone.mapper.domain.entity.ScheduleDomain;
import br.com.jdo.taxone.mapper.domain.enums.EmailType;
import br.com.jdo.taxone.mapper.interfaces.repository.EmailRepository;
import br.com.jdo.taxone.mapper.util.DateUtil;

public class EmailUseCase {
    
    private EmailRepository emailRepository;
    
    private SmtpEmailUseCase smtpEmailService; 

    public EmailUseCase(EmailRepository emailRepository, SmtpEmailUseCase smtpEmailService) {
        this.emailRepository = emailRepository;
        this.smtpEmailService = smtpEmailService;
    }

    public PageResponse<EmailDomain> findAll(Integer page, Integer size) {
        PageResponse<EmailDomain> eResponse = emailRepository.findAll(page, size);
        return eResponse;
    }

    public void save(EmailDomain eDomain) {
        emailRepository.save(eDomain);
    }

    public void delete(Integer emailId) {
        emailRepository.deleteById(emailId);
    }

    public void sendErrorEmail(ScheduleDomain s, String step) throws Exception {
        List<EmailDomain> emails = emailRepository.findByTypeIn(Arrays.asList(EmailType.ERROR,EmailType.ALL));
        List<String> emailsList = emails.stream().map(email -> email.getEmail()).collect(Collectors.toList());
        String corpo = step + " - id do Agendamento:" + s.getId() + " - nome:" + s.getName() 
            + " - data:" + DateUtil.formatyyyyMMdd(s.getLastExecution());
        smtpEmailService.sendMail(emailsList, "Erro no Agendamento", corpo);
    }
    
    

}
