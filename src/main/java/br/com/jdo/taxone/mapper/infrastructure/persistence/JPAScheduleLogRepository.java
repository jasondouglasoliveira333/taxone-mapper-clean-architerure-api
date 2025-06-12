package br.com.jdo.taxone.mapper.infrastructure.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jdo.taxone.mapper.domain.entity.ScheduleLogStatisticDomain;
import br.com.jdo.taxone.mapper.domain.enums.ScheduleLogStatus;
import br.com.jdo.taxone.mapper.infrastructure.entity.ScheduleLog;

@Repository
public interface JPAScheduleLogRepository extends JpaRepository<ScheduleLog, Integer>{

    List<ScheduleLog> findByStatus(ScheduleLogStatus status);

    Page<ScheduleLog> findByStatus(ScheduleLogStatus status, Pageable pageable);

    @Query("select new br.com.jdo.taxone.mapper.domain.entity.ScheduleLogStatisticDomain(sl.status, count(sl.id)) from ScheduleLog sl group by sl.status")
    List<ScheduleLogStatisticDomain> groupByStatus();

    int countByScheduleIdAndStatus(Integer scheduleId, ScheduleLogStatus status);

}
