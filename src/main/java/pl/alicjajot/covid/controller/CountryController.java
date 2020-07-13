package pl.alicjajot.covid.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.alicjajot.covid.dto.CountryDto;
import pl.alicjajot.covid.service.CountryService;
import pl.alicjajot.covid.service.RestActionLogService;

import java.util.List;

@RestController
@RequestMapping("/v1/country")
public class CountryController {

    @Autowired
    private RestActionLogService restActionLogService;

    @Autowired private CountryService countryService;

    @GetMapping("/{name}")
    public CountryDto getWithLatestData(@PathVariable String name){
        restActionLogService.logRestEndpoint("GET /v1/country/" + name);


        CountryDto countryDto = countryService.getByNameWithLatestData(name);
        return countryDto;
    }

    @GetMapping
    public List<CountryDto> getAllWithLatestData(){
        restActionLogService.logRestEndpoint("GET /v1/country");


        List<CountryDto> countryDtos = countryService.getAllWithLatestData();

        return countryDtos;

    }


    @GetMapping("/{name}/all")
    public List<CountryDto> getAllCountryData(@PathVariable String name){
        restActionLogService.logRestEndpoint("GET /v1/country/" + name + "/all");


        List<CountryDto> countryDtos = countryService.getByName(name);
        return countryDtos;
    }

    @PostMapping
    public CountryDto addData(@RequestBody CountryDto countryDto){
        restActionLogService.logRestEndpoint("POST /v1/country");

        CountryDto newCountry = countryService.addData(countryDto);

        return newCountry;
    }
}
