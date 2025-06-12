package br.com.jdo.taxone.mapper.usecase.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jdo.taxone.mapper.usecase.ScheduleSenderUseCase;

@Component
public class ScheduleSenderJob {
    
    private Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ScheduleSenderUseCase scheduleSenderService;
    
//    @Scheduled(cron = "${lkm.taxonemapper.jobs.schedulesender.cron}")
    public void send() {
        log.info("Inicio do processamento dos agendamentos");
        try {
            scheduleSenderService.process();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Fim do processamento dos agendamentos");
    }
}
