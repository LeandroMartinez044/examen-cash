package com.martinez.examencash.dtos;

import com.martinez.examencash.models.Loan;
import com.martinez.examencash.models.User;

import java.io.Serializable;
import java.util.Set;

/**
 * Crea objeto para transportar datos de id, email, firstName, lastName y loands.
 * Es utilizado en las operaciones addUser y findUserById de la implementación
 * UserServiceImpl.
 */
public class UserDto extends User implements Serializable {

    private Integer id;

    private String email;

    private String firstName;

    private String lastName;

    private Set<Loan> loans;

    public UserDto() {

    }

    public UserDto(Integer id, String email, String firstName, String lastName, Set<Loan> loans) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loans = loans;
    }

    public UserDto(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
