package com.neoflex.application.service;

import com.neoflex.application.feign_client.DealMC;
import com.neoflex.application.test_data.LoanApplicationRequestTestData;
import com.neoflex.application.test_data.LoanOfferTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ApplicationServiceImplTest {

    @Mock
    private final DealMC dealMC;

    @Autowired
    ApplicationServiceImplTest(DealMC dealMC) {
        this.dealMC = dealMC;
    }

    @Test
    public void correctOffers() throws Exception {
        when(dealMC.offersDeal(LoanApplicationRequestTestData.getCorrectData())).thenReturn(LoanOfferTestData.getLoanOfferTestData());
        ApplicationService applicationService_next = new ApplicationServiceImpl(dealMC);
        assertEquals(applicationService_next.offers(LoanApplicationRequestTestData.getCorrectData()), LoanOfferTestData.getLoanOfferTestData());
        verify(dealMC, times(1)).offersDeal(any());
    }

    @Test
    public void wrongFirstNameOffers() throws Exception {

        when(dealMC.offersDeal(LoanApplicationRequestTestData.getDataWithWrongFirstName())).thenThrow(new RuntimeException("Wrong firstName"));
        ApplicationService applicationService_next = new ApplicationServiceImpl(dealMC);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            applicationService_next.offers(LoanApplicationRequestTestData.getDataWithWrongFirstName());
        });

        assertEquals("Wrong firstName", thrown.getMessage());
        verify(dealMC, times(1)).offersDeal(any());
    }

    @Test
    public void wrongMiddleNameOffers() throws Exception {

        when(dealMC.offersDeal(LoanApplicationRequestTestData.getDataWithWrongMiddleName())).thenThrow(new RuntimeException("Wrong middleName"));
        ApplicationService applicationService_next = new ApplicationServiceImpl(dealMC);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            applicationService_next.offers(LoanApplicationRequestTestData.getDataWithWrongMiddleName());
        });

        assertEquals("Wrong middleName", thrown.getMessage());
        verify(dealMC, times(1)).offersDeal(any());
    }

    @Test
    public void wrongEmailOffers() {

        when(dealMC.offersDeal(any())).thenThrow(new RuntimeException("Wrong email"));
        ApplicationService applicationService_next = new ApplicationServiceImpl(dealMC);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            applicationService_next.offers(LoanApplicationRequestTestData.getDataWithWrongEmail());
        });

        assertEquals("Wrong email", thrown.getMessage());
        verify(dealMC, times(1)).offersDeal(any());
    }

    @Test
    public void wrongBirthDateOffers() throws IOException {

        when(dealMC.offersDeal(LoanApplicationRequestTestData.getDataWithWrongBirthDate())).thenThrow(new RuntimeException("Wrong birth date"));
        ApplicationService applicationService_next = new ApplicationServiceImpl(dealMC);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            applicationService_next.offers(LoanApplicationRequestTestData.getDataWithWrongBirthDate());
        });

        assertEquals("Wrong birth date", thrown.getMessage());
        verify(dealMC, times(1)).offersDeal(any());
    }

    @Test
    public void offerCalculation() throws IOException {

        ApplicationService applicationService_next = new ApplicationServiceImpl(dealMC);
        applicationService_next.offerCalculation(LoanOfferTestData.getOneLoanOffer());
        verify(dealMC, times(1)).addOffer(any());
    }

}