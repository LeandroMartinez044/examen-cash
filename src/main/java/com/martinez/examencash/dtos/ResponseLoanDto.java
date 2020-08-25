package com.martinez.examencash.dtos;

import com.martinez.examencash.models.Loan;

import java.io.Serializable;
import java.util.List;

/**
 * Crea objeto que contiene el listado de prestamos y atributos de paginación.
 */
public class ResponseLoanDto implements Serializable {

    private List<Loan> items;
    private PageDto page;

    public ResponseLoanDto(List<Loan> items, PageDto page) {
        this.items = items;
        this.page = page;
    }

    public List<Loan> getItems() {
        return items;
    }

    public PageDto getPage() {
        return page;
    }
}
