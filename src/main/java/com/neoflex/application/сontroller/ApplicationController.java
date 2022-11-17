package com.neoflex.application.сontroller;
import com.neoflex.application.dto.LoanApplicationRequestDTO;
import com.neoflex.application.dto.LoanOfferDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.neoflex.application.service.ApplicationServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "ApplicationController", description = "Interaction with MVP2")
@Slf4j
@RestController
public class ApplicationController {

    private final ApplicationServiceImpl applicationService;

    @Autowired
    public ApplicationController(ApplicationServiceImpl applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/application")
    @Operation(description = "Creating offers, interaction with method \"offersDeal\" from Deal")
    public List<LoanOfferDTO> offers(@Valid @RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        List<LoanOfferDTO> offers = applicationService.offers(loanApplicationRequestDTO);
        log.trace("GETTING LoanApplicationRequestDTO: {}, RETURNING offers: {}", loanApplicationRequestDTO, offers);
        return offers;
    }

    @PutMapping("/application/offer")
    @Operation(description = "Saving offers to PostgreSQL database, interaction with method \"addOffer\" from Deal")
    public void offerCalculation(@RequestBody LoanOfferDTO loanOfferDTO) {
        log.trace("GETTING LoanOfferDTO, VALUE: {}", loanOfferDTO);
        applicationService.offerCalculation(loanOfferDTO);
    }
}
