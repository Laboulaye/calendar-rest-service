package com.study.calendarservice.controller;

import com.study.calendarservice.model.Event;
import com.study.calendarservice.repository.EventRepo;
import com.study.calendarservice.service.EventService;
import org.aspectj.weaver.ConcreteTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public List<Event> getAllEvents(@PathVariable ("userId") long userId){
        return eventService.getAllEventsByUserId(userId);
    }

    @PostMapping("/events")
    public Event addEvent(@PathVariable("userId") long userId, @RequestBody Event event){
        return eventService.addEvent(userId, event);
    }

    @GetMapping("/events/{eventId}")
    public Event getEventById(@PathVariable("userId") long userId, @PathVariable("eventId") long eventId){
        return eventService.getEventById(userId, eventId);
    }

    @PutMapping("/events/{eventId}")
    public Event editEventById(@PathVariable("userId") long userId, @PathVariable("eventId") long eventId,
                               @RequestBody Event event){
        return eventService.editEvent(userId, eventId, event);
    }

    @DeleteMapping("/events/{eventId}")
    public void deleteEvent(@PathVariable("userId") long userId, @PathVariable("eventId") long eventId){
        eventService.deleteEvent(userId, eventId);
    }
}
