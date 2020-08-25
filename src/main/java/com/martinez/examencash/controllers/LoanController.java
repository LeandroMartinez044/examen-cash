package com.martinez.examencash.controllers;

import com.martinez.examencash.exceptions.UserNotFoundException;
import com.martinez.examencash.services.LoanService;
import com.martinez.examencash.utils.Constants;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


/**
 * Controller definido para poder acceder a los prestamos.
 */
@RestController
@RequestMapping("/loans")
public class LoanController {

    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(LoanController.class);

    private LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    /**
     * Obtiene listado de prestamos, estos se pueden paginar con parametros page y size.
     * Ademas se puede realizar filtro de prestamos mediante un user_id.
     * Utiliza el metodo findLoans implementado en la clase LoanServiceImpl.
     *
     * @param page
     * @param size
     * @return ResponseEntity
     * @Param user_id
     */
    @GetMapping
    public ResponseEntity<Page> findloads(@RequestParam int page,
                                          @RequestParam int size,
                                          @RequestParam(required = false) Integer user_id) {
        LOG.info(Constants.STRING_EMPTY);
        try {
            Pageable pageable = PageRequest.of(page - Constants.INT_ONE, size);
            return new ResponseEntity(loanService.findLoans(pageable, user_id), HttpStatus.OK);
        } catch (UserNotFoundException u) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, u.getMessage());
        } catch (IllegalArgumentException i) {
            LOG.error(i.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, i.getMessage());
        } catch (Exception e) {
            LOG.error(Constants.MESSAGE_USER_ERROR_GENERAL, e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
