package com.martinez.examencash.controllers;

import com.martinez.examencash.dtos.ResponseUserDto;
import com.martinez.examencash.dtos.ResponseErrorDto;
import com.martinez.examencash.dtos.UserDto;
import com.martinez.examencash.exceptions.UserNotFoundException;
import com.martinez.examencash.exceptions.UserRequireParametersException;
import com.martinez.examencash.services.UserService;
import com.martinez.examencash.utils.Constants;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * Controller definido para menejar las operaciones de usuario.
 * Operaciones: findUser, addUser y deleteUser.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Obtiene UserDto mediante un id de usuario.
     * Para buscar el usuario utiliza el metodo findUser implementado
     * en la clase UserServiceImpl.
     *
     * @param id
     * @return ResponseEntity<UserDto>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> findUser(@PathVariable String id) {
        LOG.info(Constants.STRING_EMPTY);
        try {
            return new ResponseEntity(userService.findUserById(Integer.valueOf(id)), HttpStatus.OK);
        } catch (UserNotFoundException n) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, n.getMessage());
        } catch (NumberFormatException n) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.MESSAGE_ONLY_NUMERS);
        } catch (Exception e) {
            LOG.error(Constants.MESSAGE_USER_ERROR_GENERAL, e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Agrega un usuario.
     * Atributos obligatorios: email, firstName y LastName
     * Para agregar el usuario utiliza el metodo addUser implementado
     * en la clase UserServiceImpl.
     *
     * @param userDto
     * @return
     */
    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody Optional<UserDto> userDto) {
        LOG.info(Constants.STRING_EMPTY);
        try {
            userService.addUser(userDto);
            return new ResponseEntity<ResponseUserDto>(
                    new ResponseUserDto(Constants.MESSAGE_USER_ADD_SUCCESS),
                    HttpStatus.OK
            );
        } catch (UserRequireParametersException r) {
            return new ResponseEntity<ResponseErrorDto>(
                    new ResponseErrorDto(r.getRequiredFieldDtos()),
                    HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            LOG.error(Constants.MESSAGE_USER_ERROR_GENERAL, e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Elimina usuario mediante id de usuario.
     * Para eliminar el usuario utiliza el metodo deleteUserById implementado
     * en la clase UserServiceImpl.
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        LOG.info(Constants.STRING_EMPTY);
        try {
            userService.deleteUserById(Integer.valueOf(id));
            return new ResponseEntity<ResponseUserDto>(
                    new ResponseUserDto(Constants.MESSAGE_USER_DELETE_SUCCESS), HttpStatus.OK
            );
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.MESSAGE_USER_NOT_EXIST);
        } catch (NumberFormatException n) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.MESSAGE_ONLY_NUMERS);
        } catch (Exception e) {
            LOG.error(Constants.MESSAGE_USER_ERROR_GENERAL, e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
