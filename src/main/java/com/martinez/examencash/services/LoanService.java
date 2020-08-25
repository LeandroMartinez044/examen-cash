package com.martinez.examencash.services;

import com.martinez.examencash.dtos.ResponseLoanDto;
import org.springframework.data.domain.Pageable;

/**
 * Metodo a implementar findLoans
 */
public interface LoanService {

    ResponseLoanDto findLoans(Pageable pageable, Integer user_id);
}
