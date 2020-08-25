package com.martinez.examencash.services;

import com.martinez.examencash.exceptions.UserNotFoundException;
import com.martinez.examencash.repositories.LoanRepository;
import com.martinez.examencash.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceImplTest {

    @InjectMocks
    private LoanServiceImpl loanServiceImpl;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
        loanServiceImpl = new LoanServiceImpl(loanRepository, userRepository);
    }

    @Test(expected = UserNotFoundException.class)
    public void testUserDoesNotExist() {
        when(userRepository.findById(500)).thenReturn(Optional.empty());
        loanServiceImpl.findLoans(any(), 500);
    }

}