package pl.alicjajot.covid.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.alicjajot.covid.dto.CovidApiActionLogDto;
import pl.alicjajot.covid.dto.RestActionLogDto;
import pl.alicjajot.covid.service.CovidApiActionLogService;
import pl.alicjajot.covid.service.RestActionLogService;

import java.util.List;

@RestController
@RequestMapping("/v1/log")
public class LogController{
    @Autowired
    private RestActionLogService restActionLogService;

    @Autowired

    private CovidApiActionLogService covidApiActionLogService;

    @GetMapping("/rest/{id}")
    public RestActionLogDto getRestAction(@PathVariable Long id){
        return restActionLogService.getRestAction(id);
    }

    @GetMapping("/rest")
    public List<RestActionLogDto> getRestActions(){
        return restActionLogService.getRestActions();
    }

    @GetMapping("/covidApi/{id}")
    public CovidApiActionLogDto getCovidApiAction(@PathVariable Long id){
        return covidApiActionLogService.getCovidApiAction(id);

    }

    @GetMapping("/covidApi")
    public List<CovidApiActionLogDto> getCovidApiActions(){
        return covidApiActionLogService.getCovidApiActions();
    }

}
