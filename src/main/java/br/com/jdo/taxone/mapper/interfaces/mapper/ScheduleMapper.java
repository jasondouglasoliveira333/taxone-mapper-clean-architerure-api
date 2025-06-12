package br.com.jdo.taxone.mapper.interfaces.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jdo.taxone.mapper.domain.entity.CriteriaDomain;
import br.com.jdo.taxone.mapper.domain.entity.SAFXTableDomain;
import br.com.jdo.taxone.mapper.domain.entity.ScheduleDomain;
import br.com.jdo.taxone.mapper.infrastructure.entity.Criteria;
import br.com.jdo.taxone.mapper.infrastructure.entity.SAFXTable;
import br.com.jdo.taxone.mapper.infrastructure.entity.Schedule;
import br.com.jdo.taxone.mapper.infrastructure.entity.User;

public class ScheduleMapper {

    public static ScheduleDomain convert(Schedule schedule) {
        ScheduleDomain sDTO = new ScheduleDomain();
        sDTO.setId(schedule.getId());
        sDTO.setName(schedule.getName());
        sDTO.setDays(schedule.getDays());
        sDTO.setHours(schedule.getHours());
        sDTO.setStatus(schedule.getStatus());
        sDTO.setUserName(schedule.getUser().getName());
        return sDTO;
    }
    
    public static ScheduleDomain convertWithDetail(Schedule schedule) {
        ScheduleDomain sDTO = convert(schedule);
        List<SAFXTableDomain> safxtList = schedule.getSafxTables().stream().map(SAFXTableMapper::mapIdName).collect(Collectors.toList());
        sDTO.setSafxTables(safxtList);
        System.out.println("schedule.getCriterias().size:" + schedule.getCriterias().size());
        List<CriteriaDomain> criteriaList = schedule.getCriterias().stream().map(CriteriaMapper::map).collect(Collectors.toList());
        sDTO.setCriterias(criteriaList);
        return sDTO;
    }

    public static Schedule convert(ScheduleDomain sDTO) {
        Schedule s = new Schedule();
        s.setId(sDTO.getId());
        s.setName(sDTO.getName());
        s.setDays(sDTO.getDays());
        s.setHours(sDTO.getHours());
        s.setStatus(sDTO.getStatus());
        s.setUser(new User(1)); 
        List<SAFXTable> safxtList = sDTO.getSafxTables().stream().map(SAFXTableMapper::mapIdName).collect(Collectors.toList());
        s.setSafxTables(safxtList);
        List<Criteria> criteriaList = sDTO.getCriterias().stream().map(CriteriaMapper::map).collect(Collectors.toList());
        s.setCriterias(criteriaList);
        return s;
    }

}
