package com.study.calendarservice.controller;

import com.study.calendarservice.exception.EventDoesNotExistException;
import com.study.calendarservice.exception.UserDoesNotExistException;
import com.study.calendarservice.model.Event;
import com.study.calendarservice.model.SearchPeriod;
import com.study.calendarservice.model.User;
import com.study.calendarservice.service.EventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class EventControllerTest {

    private MockMvc mockMvc;

    @Mock
    EventService eventService;

    @InjectMocks
    EventController eventController;

    private User user1;
    private User user2;
    private Event event1;
    private Event event2;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
    }

    @Before
    public void init(){
        user1 = new User(1, "Anthony", "Soprano", Arrays.asList(event1, event2));
        user2 = new User(2, "Carmella", "Soprano", new ArrayList<>());

        LocalDateTime beginEvent1 = LocalDateTime.of(2020, 1, 31, 10, 0);
        LocalDateTime endEvent1 = LocalDateTime.of(2020, 1, 31, 12, 0);
        event1 = new Event(1, beginEvent1, endEvent1, "Meeting", "Morning meeting in marketing dept", user1);

        LocalDateTime beginEvent2 = LocalDateTime.of(2020, 1, 31, 15, 0);
        LocalDateTime endEvent2 = LocalDateTime.of(2020, 1, 31, 16, 0);
        event2 = new Event(1, beginEvent2, endEvent2, "Meeting 2", "Afternoon meeting in marketing dept", user1);
    }

    @Test
    public void getAllEventsShouldReturnList(){
        List<Event> list = Arrays.asList(event1, event2);
        when(eventService.getAllEventsByUserId(1)).thenReturn(list);

        List<Event> listResponse = eventController.getAllEvents(1);
        assertEquals(2, listResponse.size());
    }

    @Test
    public void getAllEventsShouldThrowUserDoesNotExistException(){
        long idError = 3;
        when(eventService.getAllEventsByUserId(idError)).thenThrow(new UserDoesNotExistException(idError));

        assertThatThrownBy(() -> mockMvc.perform(get("/users/3/events")).andExpect(status().is4xxClientError()))
                .hasCause(new UserDoesNotExistException(idError));
    }

    @Test
    public void addEventShouldReturnObject(){
        when(eventService.addEvent(1, event1)).thenReturn(event1);

        Event eventResponse = eventController.addEvent(1, event1);
        assertEquals(eventResponse.getId(), event1.getId());
        assertEquals(eventResponse.getBeginDateTime(), event1.getBeginDateTime());
        assertEquals(eventResponse.getEndDateTime(), event1.getEndDateTime());
        assertEquals(eventResponse.getName(), event1.getName());
        assertEquals(eventResponse.getDescription(), event1.getDescription());
    }

    @Test
    public void getEventByIdShouldReturnObject() {
        when(eventService.getEventById(1,1)).thenReturn(event1);

        Event eventResponse = eventController.getEventById(1, 1);
        assertEquals(eventResponse.getId(), event1.getId());
        assertEquals(eventResponse.getBeginDateTime(), event1.getBeginDateTime());
        assertEquals(eventResponse.getEndDateTime(), event1.getEndDateTime());
        assertEquals(eventResponse.getName(), event1.getName());
        assertEquals(eventResponse.getDescription(), event1.getDescription());
    }

    @Test
    public void getEventByIdShouldThrowUserDoesNotExistException(){
        List<Long> userIdList = Arrays.asList(user1.getId(), user2.getId());
        long userIdError = 3;
        if(!userIdList.contains(userIdError)){
            when(eventService.getEventById(eq(userIdError), anyLong())).thenThrow(new UserDoesNotExistException(userIdError));
        }

        assertThatThrownBy(() -> mockMvc.perform(get("/users/3/events/1")).andExpect(status().is4xxClientError()))
                .hasCause(new UserDoesNotExistException(userIdError));
    }

    @Test
    public void getEventByIdShouldThrowEventDoesNotExistException(){
        List<Long> contactIdList = Arrays.asList(event1.getId(), event2.getId());
        long eventIdError = 3;
        if(!contactIdList.contains(eventIdError)){
            when(eventService.getEventById(anyLong(), eq(eventIdError))).thenThrow(new EventDoesNotExistException(eventIdError));
        }

        assertThatThrownBy(() -> mockMvc.perform(get("/users/1/events/3")).andExpect(status().is4xxClientError()))
                .hasCause(new EventDoesNotExistException(eventIdError));
    }

    @Test
    public void editEventShouldReturnObject(){
        when(eventService.editEvent(1, 2, event1)).thenReturn(event1);

        Event eventResponse = eventController.editEventById(1, 2, event1);
        assertEquals(eventResponse.getId(), event1.getId());
        assertEquals(eventResponse.getBeginDateTime(), event1.getBeginDateTime());
        assertEquals(eventResponse.getEndDateTime(), event1.getEndDateTime());
        assertEquals(eventResponse.getName(), event1.getName());
        assertEquals(eventResponse.getDescription(), event1.getDescription());
    }

    @Test
    public void deleteEventShouldTransmitToEventService(){
        eventController.deleteEvent(1, 1);
        verify(eventService, times(1)).deleteEvent(1, 1);
    }

    @Test
    public void getEventsOverCurrentPeriodShouldReturnList(){
        SearchPeriod search = new SearchPeriod();
        List<Event> list = Collections.singletonList(event1);

        when(eventService.getEventsOverCurrentPeriod(1, search)).thenReturn(list);

        List<Event> listResponse = eventController.getEventsOverCurrentPeriod(1, search);
        assertEquals(listResponse.size(),list.size());
    }

}
