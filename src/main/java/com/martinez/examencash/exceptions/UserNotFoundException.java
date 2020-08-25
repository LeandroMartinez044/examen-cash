package com.martinez.examencash.exceptions;

/**
 * Excepción creada para manejar la respuesta de usuario cuando no es encontrado.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
