package br.com.jdo.taxone.mapper.interfaces.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.jdo.taxone.mapper.domain.entity.ScheduleLogDomain;
import br.com.jdo.taxone.mapper.domain.entity.ScheduleLogStatisticDomain;
import br.com.jdo.taxone.mapper.domain.enums.ScheduleLogStatus;
import br.com.jdo.taxone.mapper.infrastructure.entity.ScheduleLog;
import br.com.jdo.taxone.mapper.interfaces.mapper.ScheduleLogMapper;

public class ScheduleLogRepositoryImpl implements ScheduleLogRepository{
    
    br.com.jdo.taxone.mapper.infrastructure.persistence.JPAScheduleLogRepository scheduleLogRepository;

    public ScheduleLogRepositoryImpl(br.com.jdo.taxone.mapper.infrastructure.persistence.JPAScheduleLogRepository scheduleLogRepository) {
        this.scheduleLogRepository = scheduleLogRepository;
    }

    public List<ScheduleLogDomain> findByStatus(ScheduleLogStatus status){
        return scheduleLogRepository.findByStatus(status).stream().map(ScheduleLogMapper::map).collect(Collectors.toList()); 
    }

    public Page<ScheduleLog> findByStatus(ScheduleLogStatus status, Pageable pageable){
        return scheduleLogRepository.findByStatus(status, pageable);
    }

    public List<ScheduleLogStatisticDomain> groupByStatus(){
        return scheduleLogRepository.groupByStatus();
    }

    public int countByScheduleIdAndStatus(Integer scheduleId, ScheduleLogStatus status) {
        return scheduleLogRepository.countByScheduleIdAndStatus(scheduleId, status);
    }

    public ScheduleLog getOne(Integer id) {
        return scheduleLogRepository.getOne(id);
    }

    public void save(ScheduleLog sLog) {
        scheduleLogRepository.save(sLog);
    }

}
