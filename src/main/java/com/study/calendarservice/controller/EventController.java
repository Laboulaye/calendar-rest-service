package com.study.calendarservice.controller;

import com.study.calendarservice.model.Event;
import com.study.calendarservice.model.SearchPeriod;
import com.study.calendarservice.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api
@RequestMapping("/users/{userId}")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    @ApiOperation(value = "View a list of available user events", response = List.class)
    public List<Event> getAllEvents(@ApiParam(value = "User Id from which events objects will retrieve", required = true)
                                        @PathVariable ("userId") long userId){
        return eventService.getAllEventsByUserId(userId);
    }

    @PostMapping("/events")
    @ApiOperation(value = "Add event")
    public Event addEvent(@ApiParam(value = "User Id to add user event object", required = true)
                              @PathVariable("userId") long userId,
                          @ApiParam(value = "Event object for add. DateTime format: 'HH:mm dd-MM-yyyy'", required = true)
                          @RequestBody Event event){
        return eventService.addEvent(userId, event);
    }

    @GetMapping("/events/{eventId}")
    @ApiOperation(value = "Get event by Id")
    public Event getEventById(@ApiParam(value = "User Id to get user event object", required = true)
                                  @PathVariable("userId") long userId,
                              @ApiParam(value = "Event Id from which event object will retrieve", required = true)
                              @PathVariable("eventId") long eventId){
        return eventService.getEventById(userId, eventId);
    }

    @PutMapping("/events/{eventId}")
    @ApiOperation(value = "Edit event")
    public Event editEventById(@ApiParam(value = "User Id to update user event object", required = true)
                                @PathVariable("userId") long userId,
                               @ApiParam(value = "Event Id from which event object will edit", required = true)
                               @PathVariable("eventId") long eventId,
                               @ApiParam(value = "Edit event object. DateTime format: 'HH:mm dd-MM-yyyy'", required = true)
                               @RequestBody Event event){
        return eventService.editEvent(userId, eventId, event);
    }

    @DeleteMapping("/events/{eventId}")
    @ApiOperation(value = "Delete event")
    public void deleteEvent(@ApiParam(value = "User Id to delete user event object", required = true)
                                @PathVariable("userId") long userId,
                            @ApiParam(value = "Event Id from which event object will delete", required = true)
                            @PathVariable("eventId") long eventId){
        eventService.deleteEvent(userId, eventId);
    }

    @PostMapping("/search")
    @ApiOperation(value = "Search events over current datetime period")
    public List<Event> getEventsOverCurrentPeriod(@ApiParam(value = "User Id to find user event objects")
                                                   @PathVariable("userId") long userId,
                                        @ApiParam(value = "Search period object. DateTime format: 'HH:mm dd-MM-yyyy'")
                                        @RequestBody SearchPeriod search){
        return eventService.getEventsOverCurrentPeriod(userId, search);
    }
}
