package com.martinez.examencash.exceptions;

import com.martinez.examencash.dtos.RequiredFieldDto;

import java.util.List;

/**
 * Excepción creada para listar los atributos requeridos faltantes al crear un usuario.
 */
public class UserRequireParametersException extends RuntimeException {

    private List<RequiredFieldDto> requiredFieldDtos;

    public UserRequireParametersException(List<RequiredFieldDto> requiredFieldDtos) {
        this.requiredFieldDtos = requiredFieldDtos;
    }

    public List<RequiredFieldDto> getRequiredFieldDtos() {
        return requiredFieldDtos;
    }

}
