package br.com.jdo.taxone.mapper.interfaces.mapper;

import br.com.jdo.taxone.mapper.domain.entity.EmailDomain;
import br.com.jdo.taxone.mapper.infrastructure.entity.Email;

public class EmailMapper {

    public static EmailDomain map(Email e) {
        EmailDomain eDTO = new EmailDomain();
        eDTO.setId(e.getId());
        eDTO.setEmail(e.getEmail());
        eDTO.setType(e.getType());
        return eDTO;
    }

    public static Email map(EmailDomain eDTO) {
        Email e = new Email();
        e.setId(eDTO.getId());
        e.setEmail(eDTO.getEmail());
        e.setType(eDTO.getType());
        return e;
    }
}
