package com.martinez.examencash.dtos;

/**
 * Se utiliza para dar respuesta de mensaje exito cuando se agrega un usuario nuevo.
 * Es utilizado en el metodo addUser de la implementación UserServiceImpl
 */
public class ResponseUserDto {

    private String message;

    public ResponseUserDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
