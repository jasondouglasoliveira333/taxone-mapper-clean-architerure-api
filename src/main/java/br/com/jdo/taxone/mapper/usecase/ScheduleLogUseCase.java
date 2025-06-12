package br.com.jdo.taxone.mapper.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jdo.taxone.mapper.domain.entity.PageResponse;
import br.com.jdo.taxone.mapper.domain.entity.ScheduleLogDomain;
import br.com.jdo.taxone.mapper.domain.entity.ScheduleLogIntergrationErrorDomain;
import br.com.jdo.taxone.mapper.domain.entity.ScheduleLogStatisticDomain;
import br.com.jdo.taxone.mapper.domain.enums.ScheduleLogStatus;
import br.com.jdo.taxone.mapper.infrastructure.entity.ScheduleLog;
import br.com.jdo.taxone.mapper.interfaces.mapper.ScheduleLogMapper;
import br.com.jdo.taxone.mapper.interfaces.repository.ScheduleLogIntergrationErrorRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.ScheduleLogRepository;

public class ScheduleLogUseCase {
    
    private ScheduleLogRepository scheduleLogRepository;

    private ScheduleLogIntergrationErrorRepository scheduleLogIntergrationErrorRepository;

    public ScheduleLogUseCase(ScheduleLogRepository scheduleLogRepository, ScheduleLogIntergrationErrorRepository scheduleLogIntergrationErrorRepository) {
        this.scheduleLogRepository = scheduleLogRepository;
        this.scheduleLogIntergrationErrorRepository = scheduleLogIntergrationErrorRepository;
    }
    
    //Just for that
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(ScheduleLog sLog) {
        scheduleLogRepository.save(sLog);
    }

    public PageResponse<ScheduleLogDomain> findAll(ScheduleLogStatus status, Integer page, Integer size) {
        Page<ScheduleLog> slPage = scheduleLogRepository.findByStatus(status, PageRequest.of(page, size, Direction.DESC, "executionDate"));
        PageResponse<ScheduleLogDomain> pageResponse = new PageResponse<>();
        pageResponse.setTotalPages(slPage.getTotalPages());
        pageResponse.setContent(slPage.getContent().stream().map(ScheduleLogMapper::map).collect(Collectors.toList()));
        return pageResponse;
    }

    public List<ScheduleLogStatisticDomain> groupByStatus() {
        return scheduleLogRepository.groupByStatus();
    }

    public ScheduleLogDomain get(Integer id) {
        return ScheduleLogMapper.map(scheduleLogRepository.getOne(id));
    }

    public PageResponse<ScheduleLogIntergrationErrorDomain> getTaxtOneErrors(Integer id, Integer page, Integer size) {
        PageResponse<ScheduleLogIntergrationErrorDomain> pageResponse = scheduleLogIntergrationErrorRepository.findByScheduleLogId(id, PageRequest.of(page, size));
        return pageResponse;
    }

}
