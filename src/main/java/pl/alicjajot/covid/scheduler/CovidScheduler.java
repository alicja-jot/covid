package pl.alicjajot.covid.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.alicjajot.covid.service.UpdateCovidStatisticsService;

@Service
public class CovidScheduler {

    @Autowired
    private UpdateCovidStatisticsService updateCovidStatisticsService;

    @Scheduled(cron = "00 00 01 * * *", zone = "Europe/Warsaw")
    public void updateCovidData(){

    updateCovidStatisticsService.updateCovidStatistics();
    }
}
