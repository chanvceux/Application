package com.neoflex.application.feign_client;

import com.neoflex.application.dto.LoanApplicationRequestDTO;
import com.neoflex.application.dto.LoanOfferDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "Deal", url = "${deal.url}")
public interface DealMC {

    @PostMapping("/deal/application")
    List<LoanOfferDTO> offersDeal(@Valid @RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO);

    @PutMapping("/deal/offer")
    void addOffer(@RequestBody LoanOfferDTO loanOfferDTO);

}