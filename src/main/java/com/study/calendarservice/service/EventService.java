package com.study.calendarservice.service;

import com.study.calendarservice.exception.EventDoesNotExistException;
import com.study.calendarservice.exception.UserDoesNotExistException;
import com.study.calendarservice.model.Event;
import com.study.calendarservice.model.User;
import com.study.calendarservice.repository.EventRepo;
import com.study.calendarservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private EventRepo eventRepo;
    private UserRepo userRepo;

    @Autowired
    public EventService(EventRepo eventRepo, UserRepo userRepo) {
        this.eventRepo = eventRepo;
        this.userRepo = userRepo;
    }

    public List<Event> getAllEventsByUserId(long userId){
        User user = userRepo.findById(userId).orElseThrow(UserDoesNotExistException::new);
        return eventRepo.findAllByAuthor(userId);
    }

    public Event addEvent(long userId, Event event){
        User user = userRepo.findById(userId).orElseThrow(UserDoesNotExistException::new);
        return eventRepo.save(event);
    }

    public Event ediEvent(long userId, long eventId, Event event){
        User user = userRepo.findById(userId).orElseThrow(UserDoesNotExistException::new);
        Event event1 = eventRepo.findById(eventId).orElseThrow(EventDoesNotExistException::new);
        return eventRepo.save(event);
    }
}
