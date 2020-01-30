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

    public List<Event> getAllEventsByUserId(Long userId){
        isUserExist(userId);
        return eventRepo.findAllByAuthor_Id(userId);
    }

    public Event addEvent(long userId, Event event){
        User user = userRepo.findById(userId).orElseThrow(()->new UserDoesNotExistException(userId));
        List<Event> listEvents = user.getEvents();
        listEvents.add(event);
        user.setEvents(listEvents);
        event.setAuthor(user);
        userRepo.save(user);
        return eventRepo.save(event);
    }

    public Event getEventById(long userId, long eventId){
        isUserExist(userId);
        isEventExist(eventId);
        return eventRepo.getOne(eventId);
    }

    public Event editEvent(long userId, long eventId, Event event){
       isUserExist(userId);
       isEventExist(eventId);
        return eventRepo.save(event);
    }

    public void deleteEvent(long userId, long eventId){
        isUserExist(userId);
        eventRepo.deleteById(eventId);
    }


    private void isUserExist(long userId){
        userRepo.findById(userId).orElseThrow(()-> new UserDoesNotExistException(userId));
    }

    private void isEventExist(long eventId){
        eventRepo.findById(eventId).orElseThrow(()-> new EventDoesNotExistException(eventId));
    }
}
