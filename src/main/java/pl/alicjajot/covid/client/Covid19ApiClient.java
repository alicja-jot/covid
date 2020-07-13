package pl.alicjajot.covid.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.alicjajot.covid.client.dto.Covid19SummaryDto;
import pl.alicjajot.covid.service.CovidApiActionLogService;

@Component
public class Covid19ApiClient implements CovidApiFacade {

    @Autowired
    private CovidApiActionLogService covidApiActionLogService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Covid19SummaryDto getData(){
        covidApiActionLogService.logCovidApiCall();


        ResponseEntity<Covid19SummaryDto> forEntity = restTemplate.getForEntity("https://api.covid19api.com/summary", Covid19SummaryDto.class);
        Covid19SummaryDto covid19SummaryDto = forEntity.getBody();

        return covid19SummaryDto;

    }
}
