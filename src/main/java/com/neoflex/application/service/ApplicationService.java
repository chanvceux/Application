package com.neoflex.application.service;

import com.neoflex.application.dto.LoanApplicationRequestDTO;
import com.neoflex.application.dto.LoanOfferDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public interface ApplicationService {

    List<LoanOfferDTO> offers(LoanApplicationRequestDTO loanApplicationRequestDTO);
    void offerCalculation(LoanOfferDTO loanOfferDTO);
    static Integer calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
