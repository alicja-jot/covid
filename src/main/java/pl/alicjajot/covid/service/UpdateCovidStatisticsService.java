package pl.alicjajot.covid.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.alicjajot.covid.client.CovidApiFacade;
import pl.alicjajot.covid.client.dto.Covid19CountryDto;
import pl.alicjajot.covid.client.dto.Covid19SummaryDto;
import pl.alicjajot.covid.domain.Country;
import pl.alicjajot.covid.domain.CountryCovidStatistics;
import pl.alicjajot.covid.domain.GlobalCovidStatistics;
import pl.alicjajot.covid.dto.CovidCaseDto;
import pl.alicjajot.covid.repository.CountryRepository;
import pl.alicjajot.covid.repository.GlobalCovidStatisticsRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class UpdateCovidStatisticsService {

    @Autowired
    private CountryService countryService;
    @Autowired
    private CovidApiFacade covidApiFacade;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CovidStatisticsMapper covidStatisticsMapper;
    @Autowired
    private GlobalCovidStatisticsRepository globalCovidStatisticsRepository;

    @Transactional
    public void updateCovidStatistics(){
        Covid19SummaryDto data = covidApiFacade.getData();

        List<Covid19CountryDto> countriesDtos = data.getCountries();
        // countriesDto
        for (Covid19CountryDto countryDto : countriesDtos) {
          Country country = countryService.getCountry(countryDto.getCountry());
          CountryCovidStatistics statistics = covidStatisticsMapper.mapToCountryStatistics(countryDto);
          country.getStatistics().add(statistics);;

          statistics.setCountry(country);
          countryRepository.save(country);
        }

        GlobalCovidStatistics globalStatistics = covidStatisticsMapper.mapToGlobalStatistics(data);
        globalCovidStatisticsRepository.save(globalStatistics);

        return;
    }

    @Transactional
    public void addCase(String countryName, CovidCaseDto covidCaseDto) {
        Country country = countryService.getCountry(countryName);

        Optional<CountryCovidStatistics> latestStatistics = country.getLatestStatistics();

        if (latestStatistics.isPresent()) {
            LocalDateTime now = LocalDateTime.now();

            CountryCovidStatistics latest = latestStatistics.get();
            if (latest.getDate().getYear() == now.getYear() && latest.getDate().getMonth() == now.getMonth() && latest.getDate().getDayOfMonth() == now.getDayOfMonth()) {
                addToCurrentStatistics(covidCaseDto, latest);
            } else
                addToNextDayStatistics(covidCaseDto, country, latest);
        } else
          addToNewStatistics(covidCaseDto, country);

        countryRepository.save(country);
    }

    private void addToCurrentStatistics(CovidCaseDto covidCaseDto, CountryCovidStatistics latest) {
        switch (covidCaseDto.getStatus()) {
            case ACTIVE:
                latest.setNewConfirmed(latest.getNewConfirmed() + 1);
                latest.setTotalConfirmed(latest.getTotalConfirmed() + 1);
                break;
            case RECOVERED:
                latest.setNewRecovered(latest.getNewRecovered() + 1);
                latest.setTotalRecovered(latest.getTotalRecovered() + 1);
                break;
            case DEATH:
                latest.setNewDeaths(latest.getNewDeaths() + 1);
                latest.setTotalDeaths(latest.getTotalDeaths() + 1);
                break;
            default:
                throw new IllegalStateException("Cannot find status: " + covidCaseDto.getStatus());
        }
    }

    private void addToNextDayStatistics(CovidCaseDto covidCaseDto, Country country, CountryCovidStatistics latest) {
        CountryCovidStatistics nextDayStatistics = new CountryCovidStatistics();
        nextDayStatistics.setCountry(country);
        country.getStatistics().add(nextDayStatistics);

        switch (covidCaseDto.getStatus()) {
            case ACTIVE:
                nextDayStatistics.setNewConfirmed(1L);
                nextDayStatistics.setTotalConfirmed(latest.getTotalConfirmed() + 1);
                break;
            case RECOVERED:
                nextDayStatistics.setNewRecovered(1L);
                nextDayStatistics.setTotalRecovered(latest.getTotalRecovered() + 1);
                break;
            case DEATH:
                nextDayStatistics.setNewDeaths(1L);
                nextDayStatistics.setTotalDeaths(latest.getTotalDeaths() + 1);
                break;
            default:
                throw new IllegalStateException("Cannot find status: " + covidCaseDto.getStatus());
        }
    }

    private void addToNewStatistics(CovidCaseDto covidCaseDto, Country country) {
        CountryCovidStatistics newStatistics = new CountryCovidStatistics();
        newStatistics.setCountry(country);
        country.getStatistics().add(newStatistics);

        switch (covidCaseDto.getStatus()) {
            case ACTIVE:
                newStatistics.setNewConfirmed(1L);
                newStatistics.setTotalConfirmed(1L);
                break;
            case RECOVERED:
                newStatistics.setNewRecovered(1L);
                newStatistics.setTotalRecovered(1L);
                break;
            case DEATH:
                newStatistics.setNewDeaths(1L);
                newStatistics.setTotalDeaths(1L);
                break;
            default:
                throw new IllegalStateException("Cannot find status: " + covidCaseDto.getStatus());
        }
    }
}
