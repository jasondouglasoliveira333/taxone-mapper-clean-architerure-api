package br.com.jdo.taxone.mapper.interfaces.repository;

import java.time.LocalDateTime;
import java.util.List;

import br.com.jdo.taxone.mapper.domain.entity.PageResponse;
import br.com.jdo.taxone.mapper.domain.entity.ScheduleDomain;
import br.com.jdo.taxone.mapper.domain.enums.ScheduleStatus;

public interface ScheduleRepository{

    List<ScheduleDomain> findByDaysContainingAndLastExecutionLessThanOrDaysAndLastExecutionLessThan(String days, LocalDateTime data, String wildcard, LocalDateTime data2);

    void updateStatus(Integer id, ScheduleStatus status);

    PageResponse<ScheduleDomain> findByStatus(ScheduleStatus status, Integer page, Integer size);

    ScheduleDomain getOne(Integer scheduleId);

    ScheduleDomain getOneDetailed(Integer scheduleId);

    void save(ScheduleDomain s);

}
