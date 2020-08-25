package com.martinez.examencash.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Clase definida para mapear la tabla prestamo(Loan).
 */
@Entity
public class Loan implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private Double total;

    @JsonIgnore
    @ManyToOne
    private User user;

    public Loan() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
