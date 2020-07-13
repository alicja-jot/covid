package pl.alicjajot.covid.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.alicjajot.covid.dto.StatisticsDto;
import pl.alicjajot.covid.service.GlobalService;
import pl.alicjajot.covid.service.RestActionLogService;

import java.util.List;

@RestController
@RequestMapping("/v1/global")
public class GlobalController {

    @Autowired private RestActionLogService restActionLogService;

    @Autowired
    private GlobalService globalService;

    @GetMapping("/latest")
    public StatisticsDto getLatestData(){
      restActionLogService.logRestEndpoint("GET /v1/global/latest");


        return globalService.getLatestData();
    }

    @GetMapping
    public List<StatisticsDto> getAllData() {
        restActionLogService.logRestEndpoint("GET /v1/global");

        List<StatisticsDto> allData = globalService.getAllData();
        return allData;
    }
}
