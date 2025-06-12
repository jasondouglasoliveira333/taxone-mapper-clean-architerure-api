package br.com.jdo.taxone.mapper.usecase.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jdo.taxone.mapper.usecase.UpdateScheduleStatusUseCase;

@Service
public class UpdateScheduleStatusJob {
    
    private Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private UpdateScheduleStatusUseCase updateScheduleStatusService;

//    @Scheduled(cron = "${lkm.taxonemapper.jobs.updateschedulestatus.cron}")
    public void process() {
        log.info("Inicio do processamento da atualizacao dos agendamentos");
        updateScheduleStatusService.process();
        log.info("Fim do processamento da atualizacao dos agendamentos");
    }
}
