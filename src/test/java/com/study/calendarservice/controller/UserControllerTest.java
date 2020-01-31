package com.study.calendarservice.controller;

import com.study.calendarservice.exception.UserDoesNotExistException;
import com.study.calendarservice.model.User;
import com.study.calendarservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    private User user1;
    private User user2;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Before
    public void init(){
        user1 = new User(1, "Anthony", "Soprano", new ArrayList<>());
        user2 = new User(2, "Carmella", "Soprano", new ArrayList<>());
    }

    @Test
    public void welcomeShouldReturnString() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome to Homepage REST web App"));
    }

    @Test
    public void getAllUsersShouldReturnList()  {
        List<User> list = Arrays.asList(user1, user2);
        when(userService.getAllUsers()).thenReturn(list);

        List<User> listResponse = userController.getAllUsers();
        assertEquals(2, listResponse.size());
    }

    @Test
    public void addUserShouldReturnJsonObject(){
        when(userService.addUser(user1)).thenReturn(user1);

        User userResponse = userController.addUser(user1);
        assertEquals(1, userResponse.getId());
        assertEquals("Anthony", userResponse.getFirstName());
        assertEquals("Soprano", userResponse.getLastName());
    }

    @Test
    public void getUserByIdShouldReturnJsonObject(){
        when(userService.getUserById(1)).thenReturn(user1);

        User userResponse = userController.getUserById(1);
        assertEquals(1, userResponse.getId());
        assertEquals("Anthony", userResponse.getFirstName());
        assertEquals("Soprano", userResponse.getLastName());
    }

    @Test
    public void getUserByIdShouldThrowDoesNotExistException(){
        List<Long> userIdList = Arrays.asList(user1.getId(), user2.getId());
        long idError = 3;
        if(!userIdList.contains(idError)){
            when(userService.getUserById(idError)).thenThrow(new UserDoesNotExistException(idError));
        }
        assertThatThrownBy(() -> mockMvc.perform(get("/users/3")).andExpect(status().is4xxClientError()))
                .hasCause(new UserDoesNotExistException(idError));
    }

    @Test
    public void editUserShouldReturnJsonObject(){
        when(userService.editUser(1, user2)).thenReturn(user2);

        User userResponse = userController.editUser(1, user2);
        assertEquals(2, user2.getId());
        assertEquals("Carmella", userResponse.getFirstName());
        assertEquals("Soprano", userResponse.getLastName());
    }

    @Test
    public void deleteUserShouldTransmitToUserService(){
        userController.deleteUser(1);
        verify(userService, times(1)).deleteUser(1);
    }
}
