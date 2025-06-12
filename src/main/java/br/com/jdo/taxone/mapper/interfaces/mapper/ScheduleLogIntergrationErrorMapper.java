package br.com.jdo.taxone.mapper.interfaces.mapper;

import br.com.jdo.taxone.mapper.domain.entity.ScheduleLogIntergrationErrorDomain;
import br.com.jdo.taxone.mapper.infrastructure.entity.ScheduleLogIntergrationError;

public class ScheduleLogIntergrationErrorMapper {

    public static ScheduleLogIntergrationErrorDomain map(ScheduleLogIntergrationError sle) {
        ScheduleLogIntergrationErrorDomain sleD = new ScheduleLogIntergrationErrorDomain();
        sleD.setId(sle.getId());
        sleD.setChaveRegistro(sle.getChaveRegistro());
        sleD.setCodigoErro(sle.getCodigoErro());
        sleD.setDescricaoErro(sle.getDescricaoErro());
        sleD.setNomeCampo(sle.getNomeCampo());
        sleD.setNumeroReg(sle.getNumeroReg());
        sleD.setScheduleLog(ScheduleLogMapper.map(sle.getScheduleLog()));
        return sleD;
    }
}
