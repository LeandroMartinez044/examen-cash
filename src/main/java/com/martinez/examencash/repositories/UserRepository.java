package com.martinez.examencash.repositories;

import com.martinez.examencash.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Se utiliza para operaciones de manejo de usuario en la base de datos.
 * Operaciones: insertar, eliminar y obtener .
 * Extiende de JpaRepostory para poder utilizar la API de CrudRepository
 * y PagingAndSortingRepostory.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
