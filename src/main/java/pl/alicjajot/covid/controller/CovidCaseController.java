package pl.alicjajot.covid.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.alicjajot.covid.dto.CovidCaseDto;
import pl.alicjajot.covid.service.CovidCaseService;
import pl.alicjajot.covid.service.RestActionLogService;

import java.util.List;

@RestController
@RequestMapping("/v1/case")
public class CovidCaseController {

    @Autowired
    private RestActionLogService restActionLogService;
    @Autowired
    private CovidCaseService casesService;

    @GetMapping("/{id}")
    public CovidCaseDto getCase(@PathVariable Long id) {
        restActionLogService.logRestEndpoint("GET /v1/case/" + id);


      return casesService.getCase(id);
    }

    @GetMapping
    public List<CovidCaseDto> getCases(){
        restActionLogService.logRestEndpoint("GET /v1/case");


       List<CovidCaseDto> cases = casesService.getCases();
        return cases;
    }

    @PostMapping
    public CovidCaseDto addCase(@RequestBody CovidCaseDto covidCaseDto) {
        restActionLogService.logRestEndpoint("POST /v1/country");

        return casesService.addCase(covidCaseDto);
    }

    @PutMapping("/{id}")
    public CovidCaseDto updateCase(@PathVariable Long id, @RequestBody CovidCaseDto covidCaseDto) {
        restActionLogService.logRestEndpoint("PUT /v1/country/" + id);

        CovidCaseDto updatedCaseDto = casesService.updateCase(id, covidCaseDto);
        return updatedCaseDto;
    }
}
