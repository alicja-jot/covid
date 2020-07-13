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
import pl.alicjajot.covid.service.GlobalService;

import java.util.LinkedList;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class GlobalControllerTestSuite {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private GlobalService globalService;

    @BeforeEach
    void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void testGetLatestDate() throws Exception {
        // given
        Long id = 1L;

        // when
        mockMvc.perform(get("/v1/global/latest"))
                .andExpect(status().isOk());

        // then
        verify(globalService).getLatestData();
    }

    @Test
    void testGetHospitals() throws Exception {
        // when
        mockMvc.perform(get("/v1/global"))
                .andExpect(status().isOk());

        // then
        verify(globalService).getAllData();
    }
}