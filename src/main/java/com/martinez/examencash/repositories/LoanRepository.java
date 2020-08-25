package com.martinez.examencash.repositories;

import com.martinez.examencash.models.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Se utiliza para poder obtener los prestamos de la base de datos.
 * Extiende de JpaRepostory para poder utilizar la API de CrudRepository
 * y PagingAndSortingRepostory.
 */
@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

    /**
     * Obtiene los prestamos filtrando por un id_user.
     *
     * @param pageable
     * @param userId
     * @return Page<Loan>
     */
    @Query("FROM Loan l " +
            "WHERE l.user.id=:idUser ")
    Page<Loan> findAllByUserId(Pageable pageable, @Param(value = "idUser") Integer userId);
}
