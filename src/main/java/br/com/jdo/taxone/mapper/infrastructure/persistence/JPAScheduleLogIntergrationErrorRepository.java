package br.com.jdo.taxone.mapper.infrastructure.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdo.taxone.mapper.infrastructure.entity.ScheduleLogIntergrationError;

@Repository
public interface JPAScheduleLogIntergrationErrorRepository extends JpaRepository<ScheduleLogIntergrationError, Integer>{

    Page<ScheduleLogIntergrationError> findByScheduleLogId(Integer id, Pageable pageable);


}
