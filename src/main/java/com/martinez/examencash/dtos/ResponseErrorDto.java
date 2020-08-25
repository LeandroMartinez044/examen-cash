package com.martinez.examencash.dtos;

import java.io.Serializable;
import java.util.List;

/**
 * Crea objeto para transportar RequiredFieldDto que contiene los mensajes de error de atributos
 * requeridos para agregar un usuario. El metodo que lo utiliza es addUser de la implementación
 * UserServiceImpl.
 */
public class ResponseErrorDto implements Serializable {

    private List<RequiredFieldDto> errors;

    public ResponseErrorDto(List<RequiredFieldDto> errors) {
        this.errors = errors;
    }

    public List<RequiredFieldDto> getErrors() {
        return errors;
    }
}
