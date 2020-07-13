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
import pl.alicjajot.covid.dto.CountryDto;
import pl.alicjajot.covid.service.CountryService;
import pl.alicjajot.covid.service.UpdateCovidStatisticsService;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CountryControllerTestSuite {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private CountryService countryService;

    @BeforeEach
    void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void testGetWithLatestData() throws Exception {
        // given
        String name = "Poland";

        // when
        mockMvc.perform(get("/v1/country/" + name))
                .andExpect(status().isOk());

        // then
        verify(countryService).getByNameWithLatestData(name);
    }

    @Test
    void testGetAllWithLatestData() throws Exception {
        // when
        mockMvc.perform(get("/v1/country"))
                .andExpect(status().isOk());

        // then
        verify(countryService).getAllWithLatestData();
    }

    @Test
    void testGetAllCountryData() throws Exception {
        // given
        String name = "Poland";

        // when
        mockMvc.perform(get("/v1/country/" + name + "/all"))
                .andExpect(status().isOk());

        // then
        verify(countryService).getByName(name);
    }

    @Test
    void testAddData() throws Exception {
        // given
        CountryDto countryDto = new CountryDto();
        countryDto.setName("Poland");
        String json = toJson(countryDto);

        // when
        mockMvc.perform(post("/v1/country")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk());

        // then
        verify(countryService).addData(countryDto);
    }

    private String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}