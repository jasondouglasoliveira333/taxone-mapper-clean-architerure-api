package br.com.jdo.taxone.mapper.usecase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import br.com.jdo.taxone.mapper.domain.entity.CriteriaDomain;
import br.com.jdo.taxone.mapper.domain.entity.PageResponse;
import br.com.jdo.taxone.mapper.domain.entity.PeriodeDomain;
import br.com.jdo.taxone.mapper.domain.entity.ScheduleDomain;
import br.com.jdo.taxone.mapper.domain.enums.ScheduleLogStatus;
import br.com.jdo.taxone.mapper.domain.enums.ScheduleStatus;
import br.com.jdo.taxone.mapper.interfaces.repository.CriteriaRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.ScheduleLogRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.ScheduleRepository;

public class ScheduleUseCase {
    
    private static final Logger log = LoggerFactory.getLogger(ScheduleUseCase.class);
    
    private ScheduleRepository scheduleRepository; 

    private CriteriaRepository criteriaRepository;
    
    private ScheduleLogRepository scheduleLogRepository;
    
    public ScheduleUseCase(ScheduleRepository scheduleRepository,  CriteriaRepository criteriaRepository, 
            ScheduleLogRepository scheduleLogRepository) {
        this.scheduleRepository = scheduleRepository;
        this.criteriaRepository = criteriaRepository;
        this.scheduleLogRepository = scheduleLogRepository;
    }

    public PageResponse<ScheduleDomain> list(Integer page, Integer size) {
        PageResponse<ScheduleDomain> sPage = scheduleRepository.findByStatus(ScheduleStatus.ACTIVE, page, size);
        return sPage;
    }

    public ScheduleDomain get(Integer id) {
        return scheduleRepository.getOneDetailed(id);
    }

    public void save(ScheduleDomain s) {
        final List<Integer> cDeleted = new ArrayList<>();
        if (s.getId() != null) {
            cDeleted.addAll(scheduleRepository.getOneDetailed(s.getId()).getCriterias().stream().map(CriteriaDomain::getId).collect(Collectors.toList()));
        }
//        Schedule s = ScheduleConverter.convert(sDTO);
        if (s.getId() == null) {
            s.setLastExecution(LocalDateTime.MIN);
        }
        s.setStatus(ScheduleStatus.ACTIVE);
        scheduleRepository.save(s);
        s.getCriterias().stream().forEach(c -> {
            cDeleted.remove(c.getId());
            c.setSchedule(s);
            criteriaRepository.save(c);
        });
        System.out.println("cDeleted:" + cDeleted);
        cDeleted.stream().forEach(cId -> {
            criteriaRepository.deleteById(cId);
        });
    }

//    public void delete(Integer id) {
//        Schedule s = scheduleRepository.getOne(id);
//        s.getCriterias().stream().forEach(c -> criteriaRepository.delete(c));
//        scheduleRepository.delete(s);
//    }

    public PeriodeDomain getPeriode(Integer id) {
        ScheduleDomain s = scheduleRepository.getOne(id);
        PeriodeDomain p = new PeriodeDomain();
        p.setDays(s.getDays());
        p.setHours(s.getHours());
        return p;
    }

    public boolean isWaitingTaxoneResponse(Integer scheduleId) {
        int count = scheduleLogRepository.countByScheduleIdAndStatus(scheduleId, ScheduleLogStatus.SENT);
        log.info(">>>count:" + count);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    public void updateStatus(Integer scheduleId, ScheduleStatus status) {
        scheduleRepository.updateStatus(scheduleId, status);
    }

}
