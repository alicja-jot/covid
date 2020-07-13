package pl.alicjajot.covid.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.alicjajot.covid.domain.Country;
import pl.alicjajot.covid.domain.CountryCovidStatistics;
import pl.alicjajot.covid.dto.CountryDto;
import pl.alicjajot.covid.repository.CountryRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CountryService{

    @Autowired
    private CountryRepository countryRepository;
    @Autowired private CountryMapper countryMapper;

    @Transactional
    public List<CountryDto> getByName(String name) {
        Country country = countryRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException());

        return countryMapper.mapToDtosWithAllData(country);
    }

    @Transactional
    public CountryDto getByNameWithLatestData(String name) {
        Country country = countryRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException());

        return countryMapper.mapToDtoWithLatestData(country);
    }

    @Transactional
    public List<CountryDto> getAllWithLatestData() {
        List<Country> countries = countryRepository.findAll();

        return countryMapper.mapToDtos(countries);
    }

    @Transactional
    public CountryDto addData(CountryDto countryDto) {
        Country country = countryRepository.findByName(countryDto.getName())
                .orElseThrow(() -> new EntityNotFoundException());

        CountryCovidStatistics statistics = countryMapper.mapToStatistics(country, countryDto.getStatistics());

        country.getStatistics().add(statistics);
        countryRepository.save(country);
        return countryMapper.mapToDtoWithLatestData(country);
    }

    @Transactional
    public Country getCountry(String country) {
        return countryRepository
                .findByName(country)
                .orElseGet(() -> createNewCountry(country));
    }

    private Country createNewCountry(String name) {
        Country country = new Country();
        country.setName(name);
        return countryRepository.save(country);
    }
}
