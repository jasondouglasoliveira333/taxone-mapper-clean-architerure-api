package br.com.jdo.taxone.mapper.interfaces.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.jdo.taxone.mapper.domain.entity.ScheduleLogDomain;
import br.com.jdo.taxone.mapper.domain.entity.ScheduleLogStatisticDomain;
import br.com.jdo.taxone.mapper.domain.enums.ScheduleLogStatus;
import br.com.jdo.taxone.mapper.infrastructure.entity.ScheduleLog;

public interface ScheduleLogRepository {

    List<ScheduleLogDomain> findByStatus(ScheduleLogStatus status);

    Page<ScheduleLog> findByStatus(ScheduleLogStatus status, Pageable pageable);

    List<ScheduleLogStatisticDomain> groupByStatus();

    int countByScheduleIdAndStatus(Integer scheduleId, ScheduleLogStatus status);

    ScheduleLog getOne(Integer id);

    void save(ScheduleLog sLog);

}
