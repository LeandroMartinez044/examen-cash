package com.martinez.examencash.services;

import com.martinez.examencash.dtos.PageDto;
import com.martinez.examencash.dtos.ResponseLoanDto;
import com.martinez.examencash.exceptions.UserNotFoundException;
import com.martinez.examencash.models.Loan;
import com.martinez.examencash.models.User;
import com.martinez.examencash.repositories.LoanRepository;
import com.martinez.examencash.repositories.UserRepository;
import com.martinez.examencash.utils.Constants;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementación para manejar los prestamos. Contiene el metodo findLoads que se encarga
 * de obtener el objeto ResponseLoanDto que contiene la lista de prestamos y atributos de
 * paginacion
 */
@Service
public class LoanServiceImpl implements LoanService {

    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(LoanServiceImpl.class);

    private LoanRepository loanRepository;
    private UserRepository userRepository;

    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
    }

    /**
     * Obtiene un ResponseLoanDto compuesto por una lista de prestamos y atributos de
     * paginacion: page, size, total. Para realizar esto utiliza el metodo findAll de la clase
     * LoanRepository. En el caso de realizar el filtro por user_id, primero verifica
     * la existencia del usuario y despues busca los prestamos con el metodo findAllByUserId
     * definido en la clase LoanRepository.
     *
     * @param pageable
     * @param userId
     * @return ResponseLoanDto
     */
    @Override
    public ResponseLoanDto findLoans(Pageable pageable, Integer userId) {
        LOG.info(Constants.STRING_EMPTY);

        Page<Loan> loans;

        if (userId == null) {
            LOG.info(Constants.MESSAGE_LOAN_SEARCH);
            loans = loanRepository.findAll(pageable);
        } else {
            checkUserExist(userId);
            loans = loanRepository.findAllByUserId(pageable, userId);
        }
        PageDto pageDto = buildPageDto(loans);

        return new ResponseLoanDto(loans.getContent(), pageDto);
    }

    private void checkUserExist(Integer userId) {
        LOG.info(Constants.MESSAGE_CHECK_USER_EXIST, userId);
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            LOG.error(Constants.MESSAGE_USER_NOT_EXIST);
            throw new UserNotFoundException(Constants.MESSAGE_USER_NOT_EXIST);
        }
        LOG.info(Constants.MESSAGE_USER, user.get());
    }

    private PageDto buildPageDto(Page<Loan> loans) {
        return new PageDto(
                loans.getNumber() + Constants.INT_ONE,
                loans.getSize(),
                loans.getTotalElements());
    }
}
