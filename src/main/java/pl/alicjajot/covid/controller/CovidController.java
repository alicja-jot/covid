package pl.alicjajot.covid.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.alicjajot.covid.service.UpdateCovidStatisticsService;
import pl.alicjajot.covid.service.RestActionLogService;

@RestController
@RequestMapping("/v1/covid")
public class CovidController {

    @Autowired
    private RestActionLogService restActionLogService;

    @Autowired
    private UpdateCovidStatisticsService updateCovidStatisticsService;

    @PostMapping("/update")
    public void update() {
        restActionLogService.logRestEndpoint("POST /v1/covid/update");;


    updateCovidStatisticsService.updateCovidStatistics();
    }
}
