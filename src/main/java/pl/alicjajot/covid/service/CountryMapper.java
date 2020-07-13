package pl.alicjajot.covid.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.alicjajot.covid.domain.Country;
import pl.alicjajot.covid.domain.CountryCovidStatistics;
import pl.alicjajot.covid.dto.CountryDto;
import pl.alicjajot.covid.dto.StatisticsDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CountryMapper {

    private final StatisticsMapper statisticsMapper;

    public List<CountryDto> mapToDtos(List<Country> countries) {
        return countries.stream().map(country -> mapToDtoWithLatestData(country)).collect(Collectors.toList());
    }

    public CountryDto mapToDtoWithLatestData(Country country) {
        CountryDto countryDto = CountryDto.CountryDtoBuilder.builder().name(country.getName()).build();

        country.getLatestStatistics()
                .ifPresent(covidStatistics -> {

                    countryDto.setStatistics(statisticsMapper.mapToDto(covidStatistics));
                });
        return countryDto;
    }

    public List<CountryDto> mapToDtosWithAllData(Country country) {
        return country.getStatistics().stream().map(statistics -> mapStatisticsToCountryDto(statistics))
                .collect(Collectors.toList());
    }

    public CountryCovidStatistics mapToStatistics(Country country, StatisticsDto statisticsDto) {
        CountryCovidStatistics statistics = new CountryCovidStatistics();
        statistics.setCountry(country);
        statisticsMapper.update(statistics, statisticsDto);
        return statistics;
    }

    public CountryDto mapStatisticsToCountryDto(CountryCovidStatistics statistics) {
        CountryDto countryDto = new CountryDto();
        countryDto.setName(statistics.getCountry().getName());
        StatisticsDto statistics1 = statisticsMapper.mapToDto(statistics);
        countryDto.setStatistics(statistics1);
        return countryDto;
    }
}
