package br.com.jdo.taxone.mapper.interfaces.repository;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.jdo.taxone.mapper.domain.entity.PageResponse;
import br.com.jdo.taxone.mapper.domain.entity.ScheduleLogIntergrationErrorDomain;
import br.com.jdo.taxone.mapper.infrastructure.entity.ScheduleLogIntergrationError;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPAScheduleLogIntergrationErrorRepository;
import br.com.jdo.taxone.mapper.interfaces.mapper.ScheduleLogIntergrationErrorMapper;

public class ScheduleLogIntergrationErrorRepositoryImpl implements ScheduleLogIntergrationErrorRepository{
    
    JPAScheduleLogIntergrationErrorRepository scheduleLogIntergrationErrorRepository;

    public ScheduleLogIntergrationErrorRepositoryImpl(JPAScheduleLogIntergrationErrorRepository scheduleLogIntergrationErrorRepository) {
        this.scheduleLogIntergrationErrorRepository = scheduleLogIntergrationErrorRepository;
    }

    public PageResponse<ScheduleLogIntergrationErrorDomain> findByScheduleLogId(Integer id, Pageable pageable){
        Page<ScheduleLogIntergrationError> slPage = scheduleLogIntergrationErrorRepository.findByScheduleLogId(id, pageable);
        PageResponse<ScheduleLogIntergrationErrorDomain> sfResponse = new PageResponse<>();
        sfResponse.setContent(slPage.getContent().stream().map(ScheduleLogIntergrationErrorMapper::map).collect(Collectors.toList()));
        sfResponse.setTotalPages(slPage.getTotalPages());
        return sfResponse;
    }

    public void save(ScheduleLogIntergrationErrorDomain slieDOmain) {
        ScheduleLogIntergrationError slie = null;//ScheduleLogIntergrationErrorConverter.
        scheduleLogIntergrationErrorRepository.save(slie);
    }


}
