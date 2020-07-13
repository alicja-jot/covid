package pl.alicjajot.covid.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.alicjajot.covid.client.dto.Covid19CountryDto;
import pl.alicjajot.covid.client.dto.Covid19GlobalDto;
import pl.alicjajot.covid.client.dto.Covid19SummaryDto;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Covid19ApiClientTestSuite {

    @Autowired
    private Covid19ApiClient client;

    @Test
    void testGetData() {
        // when
        Covid19SummaryDto summary = client.getData();

        // then
        assertThat(summary.getDate()).isNotNull();
        Covid19GlobalDto global = summary.getGlobal();
        assertThat(global.getNewConfirmed()).isNotNegative();
        assertThat(global.getTotalConfirmed()).isNotNegative();
        assertThat(global.getNewDeaths()).isNotNegative();
        assertThat(global.getTotalDeaths()).isNotNegative();
        assertThat(global.getNewRecovered()).isNotNegative();
        assertThat(global.getTotalRecovered()).isNotNegative();
        assertThat(summary.getCountries()).isNotEmpty();
        Covid19CountryDto country = summary.getCountries().get(0);
        assertThat(country.getCountry()).isNotEmpty();
        assertThat(country.getCountryCode()).isNotEmpty();
        assertThat(country.getSlug()).isNotEmpty();
        assertThat(country.getNewConfirmed()).isNotNegative();
        assertThat(country.getTotalConfirmed()).isNotNegative();
        assertThat(country.getNewDeaths()).isNotNegative();
        assertThat(country.getTotalDeaths()).isNotNegative();
        assertThat(country.getNewRecovered()).isNotNegative();
        assertThat(country.getTotalRecovered()).isNotNegative();
        assertThat(country.getDate()).isNotNull();
        assertThat(country.getPremium()).isNotNull();
    }
}