package br.com.jdo.taxone.mapper.infrastructure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jdo.taxone.mapper.domain.entity.ScheduleDomain;
import br.com.jdo.taxone.mapper.interfaces.controller.ScheduleController;
import br.com.jdo.taxone.mapper.usecase.ScheduleUseCase;

@RestController
@CrossOrigin
@RequestMapping("schedules")
public class ScheduleControllerImpl extends ScheduleController{

    private Logger log = LoggerFactory.getLogger(ScheduleControllerImpl.class);
    
    @SuppressWarnings("unused")
    @Autowired
    private ScheduleUseCase scheduleUseCase;
    
    public ScheduleUseCase scheduleUseCase() {
        return scheduleUseCase;
    }
    
    @GetMapping
    public ResponseEntity<?> list(@RequestParam(name="page", defaultValue = "0") Integer page, 
            @RequestParam(name="size", defaultValue = "10") Integer size){
        try {
            Object sPage = super.list(page, size);
            return ResponseEntity.ok(sPage);
        }catch (Exception e) { 
            log.error("Erro listando os agendamentos", e);
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("{scheduleId}")
    public ResponseEntity<?> get(@PathVariable("scheduleId") Integer id){
        try {
            Object sDTO = super.get(id);
            return ResponseEntity.ok(sDTO);
        }catch (Exception e) {
            log.error("Erro obtendo o agendamento", e);
            return ResponseEntity.badRequest().build();
        }
    }

    //Will be removed soon
    @GetMapping("{scheduleId}/periodes")
    public ResponseEntity<?> getPeriodes(@PathVariable("scheduleId") Integer id){
        try {
            Object pDTO = super.getPeriodes(id);
            return ResponseEntity.ok(pDTO);
        }catch (Exception e) {
            log.error("Erro obtendo o periodo do agendamento", e);
            return ResponseEntity.badRequest().build();
        }
    }

    
    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody ScheduleDomain sDTO){
        try {
            sDTO.getCriterias().stream().forEach(c -> {
                c.setSchedule(sDTO);
            });
            super.save(sDTO);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            log.error("Erro salvando o agendamento", e);
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("{scheduleId}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable("scheduleId") Integer scheduleId){
        try {
            super.delete(scheduleId);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            log.error("Erro delete o agendamento", e);
            return ResponseEntity.badRequest().build();
        }
    }

}
