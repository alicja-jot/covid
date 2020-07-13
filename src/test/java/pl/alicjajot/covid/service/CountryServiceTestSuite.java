package pl.alicjajot.covid.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.alicjajot.covid.domain.Country;
import pl.alicjajot.covid.domain.CountryCovidStatistics;
import pl.alicjajot.covid.dto.CountryDto;
import pl.alicjajot.covid.dto.StatisticsDto;
import pl.alicjajot.covid.repository.CountryRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CountryServiceTestSuite {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryRepository countryRepository;

    @Test
    void testGetByNameWithLatestData() {
        // given
        LocalDateTime oldDate = LocalDateTime.of(2020, 1, 1, 0, 0);
        LocalDateTime mediumDate = LocalDateTime.of(2020, 2, 1, 0, 0);
        LocalDateTime newDate = LocalDateTime.of(2020, 3, 1, 0, 0);

        Country poland = new Country();
        poland.setName("Poland");
        poland.getStatistics().add(createStatistics(poland, mediumDate, 2L));
        poland.getStatistics().add(createStatistics(poland, newDate, 1L));
        poland.getStatistics().add(createStatistics(poland, oldDate, 3L));

        Country germany = new Country();
        germany.setName("Germany");

        countryRepository.save(poland);
        countryRepository.save(germany);

        // when
        CountryDto countryDto = countryService.getByNameWithLatestData("Poland");

        // then
        assertThat(countryDto).isNotNull();
        assertThat(countryDto.getName()).isEqualTo("Poland");
        assertThat(countryDto.getStatistics().getDate()).isEqualTo(newDate);
        assertThat(countryDto.getStatistics().getTotalConfirmed()).isEqualTo(1L);
        assertThat(countryDto.getStatistics().getNewConfirmed()).isEqualTo(1L);
        assertThat(countryDto.getStatistics().getNewDeaths()).isEqualTo(1L);
        assertThat(countryDto.getStatistics().getTotalDeaths()).isEqualTo(1L);
        assertThat(countryDto.getStatistics().getNewRecovered()).isEqualTo(1L);
        assertThat(countryDto.getStatistics().getTotalRecovered()).isEqualTo(1L);

        // cleanup
        countryRepository.deleteAll();
    }

    @Test
    void testGetAllWithLatestData() {
        // given
        LocalDateTime oldDate = LocalDateTime.of(2020, 1, 1, 0, 0);
        LocalDateTime mediumDate = LocalDateTime.of(2020, 2, 1, 0, 0);
        LocalDateTime newDate = LocalDateTime.of(2020, 3, 1, 0, 0);

        Country poland = new Country();
        poland.setName("Poland");
        poland.getStatistics().add(createStatistics(poland, mediumDate, 2L));
        poland.getStatistics().add(createStatistics(poland, newDate, 1L));
        poland.getStatistics().add(createStatistics(poland, oldDate, 3L));

        Country germany = new Country();
        germany.setName("Germany");
        germany.getStatistics().add(createStatistics(germany, mediumDate, 5L));
        germany.getStatistics().add(createStatistics(germany, newDate, 4L));
        germany.getStatistics().add(createStatistics(germany, oldDate, 6L));

        countryRepository.save(poland);
        countryRepository.save(germany);

        // when
        List<CountryDto> countriesDtos = countryService.getAllWithLatestData();

        // then
        assertThat(countriesDtos).hasSize(2);

        CountryDto polandDto = countriesDtos.get(0);
        assertThat(polandDto).isNotNull();
        assertThat(polandDto.getName()).isEqualTo("Poland");
        assertThat(polandDto.getStatistics().getDate()).isEqualTo(newDate);
        assertThat(polandDto.getStatistics().getTotalConfirmed()).isEqualTo(1L);
        assertThat(polandDto.getStatistics().getNewConfirmed()).isEqualTo(1L);
        assertThat(polandDto.getStatistics().getNewDeaths()).isEqualTo(1L);
        assertThat(polandDto.getStatistics().getTotalDeaths()).isEqualTo(1L);
        assertThat(polandDto.getStatistics().getNewRecovered()).isEqualTo(1L);
        assertThat(polandDto.getStatistics().getTotalRecovered()).isEqualTo(1L);

        CountryDto germanyDto = countriesDtos.get(1);
        assertThat(germanyDto).isNotNull();
        assertThat(germanyDto.getName()).isEqualTo("Germany");
        assertThat(germanyDto.getStatistics().getDate()).isEqualTo(newDate);
        assertThat(germanyDto.getStatistics().getTotalConfirmed()).isEqualTo(4L);
        assertThat(germanyDto.getStatistics().getNewConfirmed()).isEqualTo(4L);
        assertThat(germanyDto.getStatistics().getNewDeaths()).isEqualTo(4L);
        assertThat(germanyDto.getStatistics().getTotalDeaths()).isEqualTo(4L);
        assertThat(germanyDto.getStatistics().getNewRecovered()).isEqualTo(4L);
        assertThat(germanyDto.getStatistics().getTotalRecovered()).isEqualTo(4L);

        // cleanup
        countryRepository.deleteAll();
    }

    @Test
    void testGetByName() {
        // given
        LocalDateTime mediumDate = LocalDateTime.of(2020, 2, 1, 0, 0);
        LocalDateTime newDate = LocalDateTime.of(2020, 3, 1, 0, 0);

        Country poland = new Country();
        poland.setName("Poland");
        poland.getStatistics().add(createStatistics(poland, mediumDate, 2L));
        poland.getStatistics().add(createStatistics(poland, newDate, 1L));

        Country germany = new Country();
        germany.setName("Germany");
        germany.getStatistics().add(createStatistics(germany, mediumDate, 5L));

        countryRepository.save(poland);
        countryRepository.save(germany);

        // when
        List<CountryDto> countriesDtos = countryService.getByName("Poland");

        // then
        assertThat(countriesDtos).hasSize(2);

        CountryDto polandDto = countriesDtos.get(0);
        assertThat(polandDto).isNotNull();
        assertThat(polandDto.getName()).isEqualTo("Poland");
        assertThat(polandDto.getStatistics().getDate()).isEqualTo(mediumDate);
        assertThat(polandDto.getStatistics().getTotalConfirmed()).isEqualTo(2L);
        assertThat(polandDto.getStatistics().getNewConfirmed()).isEqualTo(2L);
        assertThat(polandDto.getStatistics().getNewDeaths()).isEqualTo(2L);
        assertThat(polandDto.getStatistics().getTotalDeaths()).isEqualTo(2L);
        assertThat(polandDto.getStatistics().getNewRecovered()).isEqualTo(2L);
        assertThat(polandDto.getStatistics().getTotalRecovered()).isEqualTo(2L);

        CountryDto germanyDto = countriesDtos.get(1);
        assertThat(germanyDto).isNotNull();
        assertThat(germanyDto.getName()).isEqualTo("Poland");
        assertThat(germanyDto.getStatistics().getDate()).isEqualTo(newDate);
        assertThat(germanyDto.getStatistics().getTotalConfirmed()).isEqualTo(1L);
        assertThat(germanyDto.getStatistics().getNewConfirmed()).isEqualTo(1L);
        assertThat(germanyDto.getStatistics().getNewDeaths()).isEqualTo(1L);
        assertThat(germanyDto.getStatistics().getTotalDeaths()).isEqualTo(1L);
        assertThat(germanyDto.getStatistics().getNewRecovered()).isEqualTo(1L);
        assertThat(germanyDto.getStatistics().getTotalRecovered()).isEqualTo(1L);

        // cleanup
        countryRepository.deleteAll();
    }

    @Test
    @Transactional
    void testAddData() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Country poland = new Country();
        poland.setName("Poland");
        countryRepository.save(poland);

        CountryDto countryDto = new CountryDto();
        countryDto.setName("Poland");
        countryDto.setStatistics(new StatisticsDto());
        countryDto.getStatistics().setTotalConfirmed(1L);
        countryDto.getStatistics().setNewConfirmed(1L);
        countryDto.getStatistics().setNewDeaths(1L);
        countryDto.getStatistics().setTotalDeaths(1L);
        countryDto.getStatistics().setNewRecovered(1L);
        countryDto.getStatistics().setTotalRecovered(1L);
        countryDto.getStatistics().setDate(now);

        // when
        CountryDto createdDto = countryService.addData(countryDto);

        // then
        assertThat(createdDto).isEqualTo(countryDto);
        Country country = countryRepository.findByName("Poland").orElseThrow(EntityNotFoundException::new);
        assertThat(country.getName()).isEqualTo("Poland");
        assertThat(country.getStatistics()).isNotEmpty();

        CountryCovidStatistics statistics = country.getStatistics().get(0);
        assertThat(statistics.getCountry()).isEqualTo(country);
        assertThat(statistics.getDate()).isEqualTo(now);
        assertThat(statistics.getTotalConfirmed()).isEqualTo(1L);
        assertThat(statistics.getNewConfirmed()).isEqualTo(1L);
        assertThat(statistics.getTotalDeaths()).isEqualTo(1L);
        assertThat(statistics.getNewDeaths()).isEqualTo(1L);
        assertThat(statistics.getTotalRecovered()).isEqualTo(1L);
        assertThat(statistics.getNewRecovered()).isEqualTo(1L);

        // cleanup
        countryRepository.deleteAll();
    }

    private CountryCovidStatistics createStatistics(Country country, LocalDateTime date, Long value) {
        CountryCovidStatistics statistics = new CountryCovidStatistics();
        statistics.setCountry(country);
        statistics.setDate(date);
        statistics.setTotalConfirmed(value);
        statistics.setNewConfirmed(value);
        statistics.setNewDeaths(value);
        statistics.setTotalDeaths(value);
        statistics.setNewRecovered(value);
        statistics.setTotalRecovered(value);
        return statistics;
    }
}