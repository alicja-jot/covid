package pl.alicjajot.covid.service;

import org.springframework.stereotype.Component;
import pl.alicjajot.covid.client.dto.Covid19CountryDto;
import pl.alicjajot.covid.client.dto.Covid19GlobalDto;
import pl.alicjajot.covid.client.dto.Covid19SummaryDto;
import pl.alicjajot.covid.domain.CountryCovidStatistics;
import pl.alicjajot.covid.domain.GlobalCovidStatistics;

@Component
public class CovidStatisticsMapper{

    public CountryCovidStatistics mapToCountryStatistics(Covid19CountryDto countryDto){
        CountryCovidStatistics countrystatistics = new CountryCovidStatistics();

        countrystatistics.setTotalConfirmed(countryDto.getTotalConfirmed());
        countrystatistics.setNewConfirmed(countryDto.getNewConfirmed());
        countrystatistics.setNewDeaths(countryDto.getNewDeaths());
        countrystatistics.setTotalDeaths(countryDto.getTotalDeaths());
        countrystatistics.setNewRecovered(countryDto.getNewRecovered());
        countrystatistics.setTotalRecovered(countryDto.getTotalRecovered());
        countrystatistics.setDate(countryDto.getDate());

        return countrystatistics;
    }

    public GlobalCovidStatistics mapToGlobalStatistics(Covid19SummaryDto summaryDto){
        Covid19GlobalDto globalDto = summaryDto.getGlobal();

        GlobalCovidStatistics globalstatistics = new GlobalCovidStatistics();

        globalstatistics.setTotalConfirmed(globalDto.getTotalConfirmed());
        globalstatistics.setNewConfirmed(globalDto.getNewConfirmed());
        globalstatistics.setNewDeaths(globalDto.getNewDeaths());
        globalstatistics.setTotalDeaths(globalDto.getTotalDeaths());
        globalstatistics.setNewRecovered(globalDto.getNewRecovered());
        globalstatistics.setTotalRecovered(globalDto.getTotalRecovered());
        globalstatistics.setDate(summaryDto.getDate());

        return globalstatistics;
    }
}
