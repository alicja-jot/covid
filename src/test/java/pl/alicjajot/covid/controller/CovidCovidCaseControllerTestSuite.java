package pl.alicjajot.covid.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.alicjajot.covid.dto.CovidCaseDto;
import pl.alicjajot.covid.dto.CaseStatus;
import pl.alicjajot.covid.service.CovidCaseService;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CovidCovidCaseControllerTestSuite {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private CovidCaseService covidCaseService;

    @BeforeEach
    void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void testGetCase() throws Exception {
        // when
        mockMvc.perform(get("/v1/case/" + 1))
                .andExpect(status().isOk());

        // then
        verify(covidCaseService).getCase(1L);
    }

    @Test
    void testGetCases() throws Exception {
        // when
        mockMvc.perform(get("/v1/case"))
                .andExpect(status().isOk());

        // then
        verify(covidCaseService).getCases();
    }

    @Test
    void testAddCase() throws Exception {
        // given
        CovidCaseDto covidCaseDto = CovidCaseDto.CaseDtoBuilder.builder()
                .name("John")
                .surname("Rambo")
                .status(CaseStatus.ACTIVE)
                .build();

        String json = toJson(covidCaseDto);

        // when
        mockMvc.perform(post("/v1/case/")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk());

        // then
        verify(covidCaseService).addCase(covidCaseDto);
    }

    @Test
    void testUpdateCase() throws Exception {
        // given
        CovidCaseDto covidCaseDto = CovidCaseDto.CaseDtoBuilder.builder()
                .name("John")
                .surname("Rambo")
                .status(CaseStatus.ACTIVE)
                .build();

        String json = toJson(covidCaseDto);

        // when
        mockMvc.perform(put("/v1/case/" + 1)
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk());

        // then
        verify(covidCaseService).updateCase(1L, covidCaseDto);
    }

    private String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}