package com.martinez.examencash.dtos;

import java.io.Serializable;

/**
 * Crea objeto para transportar datos de page, size y total.
 * Es utilizado en la clase ResponseLoanDto
 */
public class PageDto implements Serializable {

    private int page;
    private int size;
    private long total;

    public PageDto(int page, int size, long total) {
        this.page = page;
        this.size = size;
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotal() {
        return total;
    }
}
