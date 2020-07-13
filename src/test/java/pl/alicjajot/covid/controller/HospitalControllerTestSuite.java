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
import pl.alicjajot.covid.service.HospitalService;

import java.util.LinkedList;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class HospitalControllerTestSuite {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private HospitalService hospitalService;

    @BeforeEach
    void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void testGetHospital() throws Exception {
        // given
        Long id = 1L;

        // when
        mockMvc.perform(get("/v1/hospital/" + id))
                .andExpect(status().isOk());

        // then
        verify(hospitalService).getHospital(id);
    }

    @Test
    void testGetHospitals() throws Exception {
        // when
        mockMvc.perform(get("/v1/hospital"))
                .andExpect(status().isOk());

        // then
        verify(hospitalService).getHospitals();
    }

    @Test
    void testAddHospital() throws Exception {
        // given
        HospitalDto hospitalDto = new HospitalDto();
        hospitalDto.setName("Polish hospital");
        hospitalDto.setCovidCases(new LinkedList<>());

        String json = toJson(hospitalDto);

        // when
        mockMvc.perform(post("/v1/hospital")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk());

        // then
        verify(hospitalService).addHospital(hospitalDto);
    }

    @Test
    void testUpdateHospital() throws Exception {
        // given
        Long id = 1L;
        HospitalDto hospitalDto = new HospitalDto();
        hospitalDto.setName("Polish hospital");
        hospitalDto.setCovidCases(new LinkedList<>());

        String json = toJson(hospitalDto);

        // when
        mockMvc.perform(put("/v1/hospital/" + id)
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk());

        // then
        verify(hospitalService).updateHospital(id, hospitalDto);
    }

    @Test
    void testDeleteHospital() throws Exception {
        // given
        Long id = 1L;

        // when
        mockMvc.perform(delete("/v1/hospital/" + id))
                .andExpect(status().isOk());

        // then
        verify(hospitalService).deleteHospital(id);
    }

    private String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}