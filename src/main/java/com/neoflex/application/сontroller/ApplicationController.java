package com.neoflex.application.сontroller;
import com.neoflex.application.dto.LoanApplicationRequestDTO;
import com.neoflex.application.dto.LoanOfferDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.neoflex.application.service.ApplicationServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "ApplicationController", description = "Interaction with MVP2")
@RestController
public class ApplicationController {

    private final ApplicationServiceImpl applicationService;

    @Autowired
    public ApplicationController(ApplicationServiceImpl applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/application")
    public List<LoanOfferDTO> offers(@Valid @RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        return applicationService.offers(loanApplicationRequestDTO);
    }

    @PutMapping("/application/offer")
    public void offerCalculation(@RequestBody LoanOfferDTO loanOfferDTO) {
        applicationService.offerCalculation(loanOfferDTO);
    }
}
