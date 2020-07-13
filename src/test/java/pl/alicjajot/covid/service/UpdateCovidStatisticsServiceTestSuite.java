package pl.alicjajot.covid.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.alicjajot.covid.domain.Country;
import pl.alicjajot.covid.domain.CountryCovidStatistics;
import pl.alicjajot.covid.dto.CovidCaseDto;
import pl.alicjajot.covid.dto.CaseStatus;
import pl.alicjajot.covid.repository.CountryRepository;
import pl.alicjajot.covid.repository.GlobalCovidStatisticsRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UpdateCovidStatisticsServiceTestSuite {

    @Autowired
    private UpdateCovidStatisticsService updateCovidStatisticsService;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private GlobalCovidStatisticsRepository globalCovidStatisticsRepository;

    @Test
    void testUpdateCovidStatistics() {
        // when
        updateCovidStatisticsService.updateCovidStatistics();

        // then
        assertThat(globalCovidStatisticsRepository.findAll()).hasSize(1);
        assertThat(countryRepository.findAll()).isNotEmpty();

        // cleanup
        globalCovidStatisticsRepository.deleteAll();
        countryRepository.deleteAll();
    }

    @Test
    @Transactional
    void testShouldAddActiveCase() {
        // given
        CovidCaseDto covidCaseDto = CovidCaseDto.CaseDtoBuilder.builder()
                .name("John")
                .surname("Rambo")
                .status(CaseStatus.ACTIVE)
                .build();

        // when
        updateCovidStatisticsService.addCase("Poland", covidCaseDto);

        // then
        Country country = countryRepository.findByName("Poland")
                .orElseThrow(EntityNotFoundException::new);
        assertThat(country.getName()).isEqualTo("Poland");
        assertThat(country.getStatistics()).hasSize(1);

        CountryCovidStatistics statistics = country.getStatistics().get(0);
        assertThat(statistics.getNewConfirmed()).isEqualTo(1L);
        assertThat(statistics.getTotalConfirmed()).isEqualTo(1L);
        assertThat(statistics.getNewDeaths()).isEqualTo(0L);
        assertThat(statistics.getTotalDeaths()).isEqualTo(0L);
        assertThat(statistics.getNewRecovered()).isEqualTo(0L);
        assertThat(statistics.getTotalRecovered()).isEqualTo(0L);

        // cleanup
        countryRepository.deleteAll();
    }

    @Test
    @Transactional
    void testShouldAddRecoveredCaseToExistingCountry() {
        // given
        Country country = new Country();
        country.setName("Poland");
        CountryCovidStatistics statistics = new CountryCovidStatistics();
        statistics.setNewConfirmed(50L);
        statistics.setTotalConfirmed(1050L);
        statistics.setNewRecovered(10L);
        statistics.setTotalRecovered(110L);
        statistics.setNewDeaths(1L);
        statistics.setTotalDeaths(11L);
        statistics.setCountry(country);
        statistics.setDate(LocalDateTime.now());
        country.getStatistics().add(statistics);
        countryRepository.save(country);

        CovidCaseDto covidCaseDto = CovidCaseDto.CaseDtoBuilder.builder()
                .name("John")
                .surname("Rambo")
                .status(CaseStatus.ACTIVE)
                .build();

        // when
        updateCovidStatisticsService.addCase("Poland", covidCaseDto);

        // then
        Country updatedCountry = countryRepository.findByName("Poland")
                .orElseThrow(EntityNotFoundException::new);
        assertThat(updatedCountry.getName()).isEqualTo("Poland");
        assertThat(updatedCountry.getStatistics()).hasSize(1);

        CountryCovidStatistics newStatistics = updatedCountry.getStatistics().get(0);
        assertThat(newStatistics.getNewConfirmed()).isEqualTo(51L);
        assertThat(newStatistics.getTotalConfirmed()).isEqualTo(1051);
        assertThat(newStatistics.getNewRecovered()).isEqualTo(10L);
        assertThat(newStatistics.getTotalRecovered()).isEqualTo(110L);
        assertThat(newStatistics.getNewDeaths()).isEqualTo(1L);
        assertThat(newStatistics.getTotalDeaths()).isEqualTo(11L);

        // cleanup
        countryRepository.deleteAll();
    }
}