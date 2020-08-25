package com.martinez.examencash.dtos;

import java.io.Serializable;

/**
 * Crea objeto para transportar datos de id y message.
 * Es utilizado en la clase ResponseErrorDto
 */
public class RequiredFieldDto implements Serializable {

    private String id;
    private String message;

    public RequiredFieldDto(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
