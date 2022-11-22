package com.neoflex.application.сontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoflex.application.service.ApplicationServiceImpl;
import com.neoflex.application.test_data.LoanApplicationRequestTestData;
import com.neoflex.application.test_data.LoanOfferTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ApplicationControllerTest {

    @MockBean
    private final ApplicationServiceImpl applicationService;
    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
    private final MockMvc mockMvc;

    @Autowired
    ApplicationControllerTest(ApplicationServiceImpl applicationService, MockMvc mockMvc) {
        this.applicationService = applicationService;
        this.mockMvc = mockMvc;
    }

    @Test
    void correctOffersAnswer() throws Exception {

        when(applicationService.offers(LoanApplicationRequestTestData.getCorrectData())).thenReturn(LoanOfferTestData.getLoanOfferTestData());

        mockMvc.perform(post("/application")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(LoanApplicationRequestTestData.getCorrectData())))
                .andExpect(status().isOk());

        verify(applicationService, times(1)).offers(any());

    }

    @Test
    void incorrectFirstNameOffersAnswer() throws Exception {

        mockMvc.perform(post("/application")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(LoanApplicationRequestTestData.getDataWithWrongFirstName())))
                .andExpect(status().is4xxClientError());

        verify(applicationService, times(0)).offers(any());

    }

    @Test
    void incorrectLastNameOffersAnswer() throws Exception {

        mockMvc.perform(post("/application")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(LoanApplicationRequestTestData.getDataWithWrongLastName())))
                .andExpect(status().is4xxClientError());

        verify(applicationService, times(0)).offers(any());
    }

    @Test
    void incorrectMiddleNameOffersAnswer() throws Exception {

        mockMvc.perform(post("/application")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(LoanApplicationRequestTestData.getDataWithWrongMiddleName())))
                .andExpect(status().is4xxClientError());

        verify(applicationService, times(0)).offers(any());
    }

    @Test
    void incorrectBirthDateOffersAnswer() throws Exception {

        mockMvc.perform(post("/application")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(LoanApplicationRequestTestData.getDataWithWrongBirthDate())))
                .andExpect(status().is4xxClientError());

        verify(applicationService, times(0)).offers(any());
    }

    @Test
    void incorrectAmountOffersAnswer() throws Exception {

        mockMvc.perform(post("/application")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(LoanApplicationRequestTestData.getDataWithWrongAmount())))
                .andExpect(status().is4xxClientError());

        verify(applicationService, times(0)).offers(any());
    }

    @Test
    void incorrectEmailOffersAnswer() throws Exception {

        mockMvc.perform(post("/application")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(LoanApplicationRequestTestData.getDataWithWrongEmail())))
                .andExpect(status().is4xxClientError());

        verify(applicationService, times(0)).offers(any());
    }

    @Test
    void incorrectPassportSeriesOffersAnswer() throws Exception {

        mockMvc.perform(post("/application")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(LoanApplicationRequestTestData.getDataWithWrongPassportSeries())))
                .andExpect(status().is4xxClientError());

        verify(applicationService, times(0)).offers(any());
    }

    @Test
    void offerCalculation() throws Exception {

        mockMvc.perform(put("/application/offer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(LoanOfferTestData.getOneLoanOffer())))
                .andExpect(status().isOk());

        verify(applicationService, times(1)).offerCalculation(any());

    }
}