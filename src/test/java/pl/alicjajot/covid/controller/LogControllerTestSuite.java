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
import pl.alicjajot.covid.dto.HospitalDto;
import pl.alicjajot.covid.service.CovidApiActionLogService;
import pl.alicjajot.covid.service.HospitalService;
import pl.alicjajot.covid.service.RestActionLogService;

import java.util.LinkedList;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class LogControllerTestSuite {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private RestActionLogService restActionLogService;

    @MockBean
    private CovidApiActionLogService covidApiActionLogService;

    @BeforeEach
    void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void testGetRestAction() throws Exception {
        // given
        Long id = 1L;

        // when
        mockMvc.perform(get("/v1/log/rest/" + id))
                .andExpect(status().isOk());

        // then
        verify(restActionLogService).getRestAction(id);
    }

    @Test
    void testGetRestActions() throws Exception {
        // when
        mockMvc.perform(get("/v1/log/rest"))
                .andExpect(status().isOk());

        // then
        verify(restActionLogService).getRestActions();
    }

    @Test
    void testGetCovidApiAction() throws Exception {
        // given
        Long id = 1L;

        // when
        mockMvc.perform(get("/v1/log/covidApi/" + id))
                .andExpect(status().isOk());

        // then
        verify(covidApiActionLogService).getCovidApiAction(id);
    }

    @Test
    void testGetCovidApiActions() throws Exception {
        // when
        mockMvc.perform(get("/v1/log/covidApi"))
                .andExpect(status().isOk());

        // then
        verify(covidApiActionLogService).getCovidApiActions();
    }
}