package br.com.jdo.taxone.mapper.interfaces.repository;

import org.springframework.data.domain.Pageable;

import br.com.jdo.taxone.mapper.domain.entity.PageResponse;
import br.com.jdo.taxone.mapper.domain.entity.ScheduleLogIntergrationErrorDomain;

public interface ScheduleLogIntergrationErrorRepository {

    PageResponse<ScheduleLogIntergrationErrorDomain> findByScheduleLogId(Integer id, Pageable pageable);

    void save(ScheduleLogIntergrationErrorDomain slie);


}
