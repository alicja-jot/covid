package pl.alicjajot.covid.service;

import org.junit.jupiter.api.Test;
import pl.alicjajot.covid.client.dto.Covid19CountryDto;
import pl.alicjajot.covid.client.dto.Covid19GlobalDto;
import pl.alicjajot.covid.client.dto.Covid19SummaryDto;
import pl.alicjajot.covid.domain.CountryCovidStatistics;
import pl.alicjajot.covid.domain.GlobalCovidStatistics;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class CovidStatisticsMapperTestSuite {

    private CovidStatisticsMapper mapper = new CovidStatisticsMapper();

    @Test
    void testMapToEntity() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Covid19CountryDto countryDto = new Covid19CountryDto();

        countryDto.setNewConfirmed(1L);
        countryDto.setTotalConfirmed(2L);
        countryDto.setNewDeaths(3L);
        countryDto.setTotalDeaths(4L);
        countryDto.setNewRecovered(5L);
        countryDto.setTotalRecovered(6L);
        countryDto.setDate(now);

        // when
        CountryCovidStatistics statistics = mapper.mapToCountryStatistics(countryDto);

        // then
        assertThat(statistics.getNewConfirmed()).isEqualTo(1L);
        assertThat(statistics.getTotalConfirmed()).isEqualTo(2L);
        assertThat(statistics.getNewDeaths()).isEqualTo(3L);
        assertThat(statistics.getTotalDeaths()).isEqualTo(4L);
        assertThat(statistics.getNewRecovered()).isEqualTo(5L);
        assertThat(statistics.getTotalRecovered()).isEqualTo(6L);
        assertThat(statistics.getDate()).isEqualTo(now);
    }

    @Test
    void testMapToGlobalStatistics() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Covid19SummaryDto summaryDto = new Covid19SummaryDto();
        Covid19GlobalDto globalDto = new Covid19GlobalDto();
        summaryDto.setGlobal(globalDto);

        globalDto.setNewConfirmed(1L);
        globalDto.setTotalConfirmed(2L);
        globalDto.setNewDeaths(3L);
        globalDto.setTotalDeaths(4L);
        globalDto.setNewRecovered(5L);
        globalDto.setTotalRecovered(6L);

        summaryDto.setDate(now);

        // when
        GlobalCovidStatistics statistics = mapper.mapToGlobalStatistics(summaryDto);

        // then
        assertThat(statistics.getNewConfirmed()).isEqualTo(1L);
        assertThat(statistics.getTotalConfirmed()).isEqualTo(2L);
        assertThat(statistics.getNewDeaths()).isEqualTo(3L);
        assertThat(statistics.getTotalDeaths()).isEqualTo(4L);
        assertThat(statistics.getNewRecovered()).isEqualTo(5L);
        assertThat(statistics.getTotalRecovered()).isEqualTo(6L);
        assertThat(statistics.getDate()).isEqualTo(now);
    }
}