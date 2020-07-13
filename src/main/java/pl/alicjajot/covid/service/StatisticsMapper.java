package pl.alicjajot.covid.service;

import org.springframework.stereotype.Component;
import pl.alicjajot.covid.domain.CountryCovidStatistics;
import pl.alicjajot.covid.domain.CovidStatistics;
import pl.alicjajot.covid.domain.GlobalCovidStatistics;
import pl.alicjajot.covid.dto.CountryDto;
import pl.alicjajot.covid.dto.StatisticsDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatisticsMapper {

    public StatisticsDto mapToDto(CovidStatistics covidStatistics) {
        StatisticsDto statisticsDto = new StatisticsDto();
        statisticsDto.setTotalConfirmed(covidStatistics.getTotalConfirmed());
        statisticsDto.setNewConfirmed(covidStatistics.getNewConfirmed());
        statisticsDto.setNewDeaths(covidStatistics.getNewDeaths());
        statisticsDto.setTotalDeaths(covidStatistics.getTotalDeaths());
        statisticsDto.setNewRecovered(covidStatistics.getNewRecovered());
        statisticsDto.setTotalRecovered(covidStatistics.getTotalRecovered());
        statisticsDto.setDate(covidStatistics.getDate());
        return statisticsDto;
    }

    public void update(CountryCovidStatistics statistics, StatisticsDto statisticsDto) {
        statistics.setTotalConfirmed(statisticsDto.getTotalConfirmed());
        statistics.setNewConfirmed(statisticsDto.getNewConfirmed());
        statistics.setNewDeaths(statisticsDto.getNewDeaths());
        statistics.setTotalDeaths(statisticsDto.getTotalDeaths());
        statistics.setNewRecovered(statisticsDto.getNewRecovered());
        statistics.setTotalRecovered(statisticsDto.getTotalRecovered());
        statistics.setDate(statisticsDto.getDate());
    }

    public List<StatisticsDto> mapToDtos(List<GlobalCovidStatistics> covidStatistics) {
        return covidStatistics.stream().map(covidStatistics1 -> mapToDto(covidStatistics1)).collect(Collectors.toList());
    }
}
