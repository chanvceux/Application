package com.neoflex.application.service;

import com.neoflex.application.dto.LoanApplicationRequestDTO;
import com.neoflex.application.dto.LoanOfferDTO;
import com.neoflex.application.feign_client.DealMC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl {

    private final DealMC dealMC;

    @Autowired
    public ApplicationServiceImpl(DealMC dealMC) {
        this.dealMC = dealMC;
    }

    public List<LoanOfferDTO> offers(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        List<LoanOfferDTO> offers = dealMC.offersDeal(loanApplicationRequestDTO);
        return offers;
    }

    public void offerCalculation(LoanOfferDTO loanOfferDTO) {
        dealMC.addOffer(loanOfferDTO);
    }
}
