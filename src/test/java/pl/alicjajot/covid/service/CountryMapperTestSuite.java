package pl.alicjajot.covid.service;

import org.junit.jupiter.api.Test;
import pl.alicjajot.covid.domain.Country;
import pl.alicjajot.covid.domain.CountryCovidStatistics;
import pl.alicjajot.covid.dto.CountryDto;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class CountryMapperTestSuite {

    private StatisticsMapper statisticsMapper = mock(StatisticsMapper.class);
    private CountryMapper countryMapper = new CountryMapper(statisticsMapper);

    @Test
    void testMapToDtos() {
        // given
        LocalDateTime polandTime = LocalDateTime.now();
        LocalDateTime germanyTime = LocalDateTime.now();
        List<Country> countries = new LinkedList<>();
        countries.add(createCountry("Poland", polandTime, 1L));
        countries.add(createCountry("Germany", germanyTime, 2L));

        // when
        List<CountryDto> countryDtos = countryMapper.mapToDtos(countries);

        // then
        CountryDto polandDto = countryDtos.get(0);
        assertThat(polandDto.getName()).isEqualTo("Poland");

        CountryDto germanyDto = countryDtos.get(1);
        assertThat(germanyDto.getName()).isEqualTo("Germany");
    }

    @Test
    void testMapToDto() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Country country = createCountry("Poland", now, 1L);

        // when
        CountryDto countryDto = countryMapper.mapToDtoWithLatestData(country);

        // then
        assertThat(countryDto.getName()).isEqualTo("Poland");
    }

    private Country createCountry(String name, LocalDateTime date, Long value) {
        Country country = new Country();
        country.setName(name);
        return country;
    }
}