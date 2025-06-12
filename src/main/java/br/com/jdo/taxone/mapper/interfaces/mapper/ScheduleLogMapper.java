package br.com.jdo.taxone.mapper.interfaces.mapper;

import br.com.jdo.taxone.mapper.domain.entity.ScheduleLogDomain;
import br.com.jdo.taxone.mapper.domain.entity.ScheduleLogIntergrationErrorDomain;
import br.com.jdo.taxone.mapper.infrastructure.entity.ScheduleLog;
import br.com.jdo.taxone.mapper.infrastructure.entity.ScheduleLogIntergrationError;

public class ScheduleLogMapper {

    public static ScheduleLogDomain map(ScheduleLog sl) {
        ScheduleLogDomain slDTO = new ScheduleLogDomain();
        slDTO.setId(sl.getId());
        slDTO.setScheduleName(sl.getSchedule().getName());
        slDTO.setExecutionDate(sl.getExecutionDate());
        slDTO.setStatus(sl.getStatus());
        return slDTO;
    }

//    public static ScheduleLogDTO convertWithErrors(ScheduleLog sl) {
//        ScheduleLogDTO slDTO = convert(sl);
//        slDTO.setTaxOneErrors(sl.getTaxOneErrors().stream().map(ScheduleLogConverter::convert).collect(Collectors.toList()));
//        return slDTO;
//    }
    
    public static ScheduleLogIntergrationErrorDomain map(ScheduleLogIntergrationError slie) {
        ScheduleLogIntergrationErrorDomain slieDTO = new ScheduleLogIntergrationErrorDomain();
        slieDTO.setId(slie.getId());
        slieDTO.setNumeroReg(slie.getNumeroReg());
        slieDTO.setNomeCampo(slie.getNomeCampo());
        slieDTO.setCodigoErro(slie.getCodigoErro());
        slieDTO.setDescricaoErro(slie.getDescricaoErro());
        slieDTO.setChaveRegistro(slie.getChaveRegistro());
        return slieDTO;
    }
}
