package com.martinez.examencash.services;

import com.martinez.examencash.dtos.UserDto;
import com.martinez.examencash.exceptions.UserNotFoundException;
import com.martinez.examencash.exceptions.UserRequireParametersException;
import com.martinez.examencash.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
        userServiceImpl = new UserServiceImpl(userRepository);
    }

    @Test(expected = UserNotFoundException.class)
    public void testFindUserByIdIsEmpty() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        userServiceImpl.findUserById(any());
    }

    @Test(expected = UserRequireParametersException.class)
    public void testUserRequiredEmailNull() {
        userServiceImpl.addUser(userRequiredEmailNull());
    }

    @Test(expected = UserRequireParametersException.class)
    public void testUserRequiredEmailEmpty() {
        userServiceImpl.addUser(userRequiredEmailEmpty());
    }

    @Test(expected = UserRequireParametersException.class)
    public void testUserRequiredFirstNameNull() {
        userServiceImpl.addUser(userRequiredFirstNameNull());
    }

    @Test(expected = UserRequireParametersException.class)
    public void testUserRequiredFirstNameEmpty() {
        userServiceImpl.addUser(userRequiredFirstNameEmpty());
    }

    @Test(expected = UserRequireParametersException.class)
    public void testUserRequiredLastNameNull() {
        userServiceImpl.addUser(userRequiredLastNameNull());
    }

    private final Optional<UserDto> userRequiredEmailNull() {
        return Optional.of(new UserDto(
                null,
                "Guillermo",
                "Francella"
        ));
    }

    private final Optional<UserDto> userRequiredEmailEmpty() {
        return Optional.of(new UserDto(
                "",
                "Guillermo",
                "Francella"
        ));
    }

    private final Optional<UserDto> userRequiredFirstNameNull() {
        return Optional.of(new UserDto(
                "a@gmail.com",
                null,
                "Francella"
        ));
    }

    private final Optional<UserDto> userRequiredFirstNameEmpty() {
        return Optional.of(new UserDto(
                "a@gmail.com",
                "",
                "Francella"
        ));
    }

    private final Optional<UserDto> userRequiredLastNameNull() {
        return Optional.of(new UserDto(
                "a@gmail.com",
                "Guillermo",
                null
        ));
    }

    private final Optional<UserDto> userRequiredLastNameEmpty() {
        return Optional.of(new UserDto(
                "a@gmail.com",
                "Guillermo",
                ""
        ));
    }
}