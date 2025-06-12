package br.com.jdo.taxone.mapper.infrastructure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jdo.taxone.mapper.domain.enums.ScheduleLogStatus;
import br.com.jdo.taxone.mapper.interfaces.controller.ScheduleLogController;
import br.com.jdo.taxone.mapper.usecase.ScheduleLogUseCase;

@CrossOrigin
@RestController
@RequestMapping("/schedulelogs")
public class ScheduleLogControllerImpl extends ScheduleLogController{
    
    private Logger log = LoggerFactory.getLogger(getClass());
    
    @SuppressWarnings("unused")
    @Autowired
    private ScheduleLogUseCase scheduleLogUseCase;
    
    public ScheduleLogUseCase scheduleLogUseCase() {
        return scheduleLogUseCase;
    }
    
    @GetMapping
    public Object list(@RequestParam(name="status") ScheduleLogStatus status,  @RequestParam(name="page", defaultValue = "0") Integer page, 
            @RequestParam(name="size", defaultValue = "10") Integer size){
        try {
            Object sPage = super.list(status, page, size);
            return ResponseEntity.ok(sPage);
        }catch(Exception e) {
            log.error("Erro listando os logs de agendamento", e);
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("statistics")
    public Object generateStatitics(){
        try {
            Object slsList = super.generateStatitics();
            return ResponseEntity.ok(slsList);
        }catch(Exception e) {
            log.error("Erro listando os logs de agendamento", e);
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("{id}")
    public Object get(@PathVariable("id") Integer id){
        try {
            Object slDTO = super.get(id);
            return ResponseEntity.ok(slDTO);
        }catch(Exception e) {
            log.error("Erro listando os logs de agendamento", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{id}/taxOneErrors")
    public Object getTaxOneErrors(@PathVariable("id") Integer id, @RequestParam(name="page", defaultValue = "0") Integer page, 
            @RequestParam(name="size", defaultValue = "10") Integer size){
        try {
            Object taxOneErrors = super.getTaxOneErrors(id, page, size);
            return ResponseEntity.ok(taxOneErrors);
        }catch(Exception e) {
            log.error("Erro listando os logs de agendamento", e);
            return ResponseEntity.badRequest().build();
        }
    }


}
