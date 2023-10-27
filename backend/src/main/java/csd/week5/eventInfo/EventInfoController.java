package csd.week5.eventInfo;

import java.util.List;

import javax.validation.Valid;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
public class EventInfoController {
    private EventInfoService eventInfoService;

    public EventInfoController(EventInfoService bs){
        this.eventInfoService = bs;
    }

    @GetMapping("/home")
    public List<EventInfo> getEventInfo(){
        return eventInfoService.listEventInfo();
    }

    @GetMapping("/eventInfo")
    public EventInfo getEventInfo(@PathVariable Long id){
        EventInfo eventInfo = eventInfoService.getEventInfo(id);
        if(eventInfo == null) throw new EventInfoNotFoundException(id);
        return eventInfoService.getEventInfo(id);

    }

    @ResponseStatus(HttpStatus.CREATED)
    public EventInfo addEventInfo(@Valid @RequestBody EventInfo eventInfo) {
        return eventInfoService.addEventInfo(eventInfo);
    }

    public EventInfo updateEventInfo(@PathVariable Long id, @Valid @RequestBody EventInfo neweventInfoInfo){
        EventInfo eventInfo = eventInfoService.updateEventInfo(id, neweventInfoInfo);
        if(eventInfo == null) throw new EventInfoNotFoundException(id);
        
        return eventInfo;
    }

    public void deleteEventInfo(@PathVariable Long id){
        try{
            eventInfoService.deleteEventInfo(id);
         }catch(EmptyResultDataAccessException e) {
            throw new EventInfoNotFoundException(id);
         }
    }

}