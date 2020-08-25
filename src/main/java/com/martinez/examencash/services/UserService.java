package com.martinez.examencash.services;

import com.martinez.examencash.dtos.UserDto;

import java.util.Optional;

/**
 * Metodos a implentar findUserById, addUser y deleteUserById.
 */
public interface UserService {

    Optional<UserDto> findUserById(Integer id);

    void addUser(Optional<UserDto> userDto);

    void deleteUserById(Integer id);
}
