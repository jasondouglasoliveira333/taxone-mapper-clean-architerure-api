package br.com.jdo.taxone.mapper.interfaces.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.jdo.taxone.mapper.domain.entity.PageResponse;
import br.com.jdo.taxone.mapper.domain.entity.ScheduleDomain;
import br.com.jdo.taxone.mapper.domain.enums.ScheduleStatus;
import br.com.jdo.taxone.mapper.infrastructure.entity.Schedule;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPAScheduleRepository;
import br.com.jdo.taxone.mapper.interfaces.mapper.ScheduleMapper;

public class ScheduleRepositoryImpl implements ScheduleRepository{
    
    JPAScheduleRepository scheduleRepository;

    public ScheduleRepositoryImpl(JPAScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<ScheduleDomain> findByDaysContainingAndLastExecutionLessThanOrDaysAndLastExecutionLessThan(String days, LocalDateTime data, String wildcard, LocalDateTime data2){
        return scheduleRepository.findByDaysContainingAndLastExecutionLessThanOrDaysAndLastExecutionLessThan(days, data, wildcard, data2).stream().map(ScheduleMapper::convert).collect(Collectors.toList());
    }

    public void updateStatus(Integer id, ScheduleStatus status) {
        scheduleRepository.updateStatus(id, status);
    }

    public PageResponse<ScheduleDomain> findByStatus(ScheduleStatus status, Integer page, Integer size){
        Page<Schedule> sPage = scheduleRepository.findByStatus(status, PageRequest.of(page, size));
        PageResponse<ScheduleDomain> srPage = new PageResponse<>();
        srPage.setContent(sPage.stream().map(ScheduleMapper::convert).collect(Collectors.toList()));
        srPage.setTotalPages(sPage.getTotalPages());
        return srPage;
    }

    public ScheduleDomain getOne(Integer scheduleId) {
        return ScheduleMapper.convert(scheduleRepository.getOne(scheduleId));
    }

    public ScheduleDomain getOneDetailed(Integer scheduleId) {
        return ScheduleMapper.convertWithDetail(scheduleRepository.getOne(scheduleId));
    }

    public void save(ScheduleDomain sDomain) {
        Schedule s = ScheduleMapper.convert(sDomain);
        scheduleRepository.save(s);
    }

}
