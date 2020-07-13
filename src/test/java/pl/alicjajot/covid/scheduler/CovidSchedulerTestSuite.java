package pl.alicjajot.covid.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.alicjajot.covid.service.UpdateCovidStatisticsService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
class CovidSchedulerTestSuite{

    @MockBean
    private UpdateCovidStatisticsService updateCovidStatisticsService;
    @Autowired
    private CovidScheduler scheduler = new CovidScheduler();

    @Test
    void testUpdateCovidData() {
        // when
        scheduler.updateCovidData();

        // then
        verify(updateCovidStatisticsService).updateCovidStatistics();
    }
}