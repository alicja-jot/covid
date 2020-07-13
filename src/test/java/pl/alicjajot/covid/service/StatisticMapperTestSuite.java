package pl.alicjajot.covid.service;

import org.junit.jupiter.api.Test;
import pl.alicjajot.covid.domain.CountryCovidStatistics;
import pl.alicjajot.covid.dto.StatisticsDto;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class StatisticMapperTestSuite {

    private StatisticsMapper statisticsMapper = new StatisticsMapper();

    @Test
    void testMapToDto() {
        // given
        CountryCovidStatistics statistics = new CountryCovidStatistics();
        LocalDateTime now = LocalDateTime.now();

        statistics.setTotalConfirmed(1L);
        statistics.setNewConfirmed(2L);
        statistics.setNewDeaths(3L);
        statistics.setTotalDeaths(4L);
        statistics.setNewRecovered(5L);
        statistics.setTotalRecovered(6L);
        statistics.setDate(now);

        // when
        StatisticsDto statisticsDto = statisticsMapper.mapToDto(statistics);

        // then
        assertThat(statisticsDto.getTotalConfirmed()).isEqualTo(1L);
        assertThat(statisticsDto.getNewConfirmed()).isEqualTo(2L);
        assertThat(statisticsDto.getNewDeaths()).isEqualTo(3L);
        assertThat(statisticsDto.getTotalDeaths()).isEqualTo(4L);
        assertThat(statisticsDto.getNewRecovered()).isEqualTo(5L);
        assertThat(statisticsDto.getTotalRecovered()).isEqualTo(6L);
        assertThat(statisticsDto.getDate()).isEqualTo(now);
    }
}
