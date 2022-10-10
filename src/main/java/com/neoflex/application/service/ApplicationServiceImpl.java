package com.neoflex.application.service;

import com.neoflex.application.dto.LoanApplicationRequestDTO;
import com.neoflex.application.dto.LoanOfferDTO;
import com.neoflex.application.feign_client.DealMC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ApplicationServiceImpl {
    private final DealMC dealMC;
    @Autowired
    public ApplicationServiceImpl(DealMC dealMC) {
        this.dealMC = dealMC;
    }

    public List<LoanOfferDTO> offers(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        List<LoanOfferDTO> offers = dealMC.offersDeal(loanApplicationRequestDTO);
        log.trace("GETTING LoanApplicationRequestDTO: {}, RETURNING offers FROM MC DEAL: {}", loanApplicationRequestDTO, offers);
        return offers;
    }

    public void offerCalculation(LoanOfferDTO loanOfferDTO) {
        log.trace("GETTING LoanOfferDTO, VALUE: {}, CREATING OFFERS: MC DEAL", loanOfferDTO);
        dealMC.addOffer(loanOfferDTO);
    }
}
