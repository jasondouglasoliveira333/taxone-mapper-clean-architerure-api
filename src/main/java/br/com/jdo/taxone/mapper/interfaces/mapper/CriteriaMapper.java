package br.com.jdo.taxone.mapper.interfaces.mapper;

import br.com.jdo.taxone.mapper.domain.entity.CriteriaDomain;
import br.com.jdo.taxone.mapper.infrastructure.entity.Criteria;
import br.com.jdo.taxone.mapper.infrastructure.entity.Schedule;

public class CriteriaMapper {

    public static CriteriaDomain map(Criteria c) {
        CriteriaDomain cDTO = new CriteriaDomain();
        cDTO.setId(c.getId());
        cDTO.setSafxColumn(SAFXColumnMapper.mapCriteria(c.getSafxColumn()));
        cDTO.setOperator(c.getOperator());
        cDTO.setValue(c.getValue());
        cDTO.setAdditionalValue(c.getAdditionalValue());
        return cDTO;
    }
    
    public static Criteria map(CriteriaDomain cDTO) {
        Criteria c = new Criteria();
        c.setId(cDTO.getId());
        c.setSafxColumn(SAFXColumnMapper.map(cDTO.getSafxColumn()));
        c.setOperator(cDTO.getOperator());
        c.setValue(cDTO.getValue());
        c.setAdditionalValue(cDTO.getAdditionalValue());
        Schedule s = new Schedule();
        s.setId(cDTO.getSchedule().getId());
        c.setSchedule(s);
        return c;
    }
}
