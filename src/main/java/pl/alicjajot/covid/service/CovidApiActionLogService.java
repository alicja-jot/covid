package pl.alicjajot.covid.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.alicjajot.covid.domain.log.CovidApiActionLog;
import pl.alicjajot.covid.dto.CovidApiActionLogDto;
import pl.alicjajot.covid.dto.RestActionLogDto;
import pl.alicjajot.covid.repository.CovidApiActionLogRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CovidApiActionLogService {

    @Autowired
    private CovidApiActionLogRepository covidApiActionLogRepository;

    @Autowired
    private CovidApiActionLogMapper covidApiActionLogMapper;

    public void logCovidApiCall(){
        CovidApiActionLog restActionLog = new CovidApiActionLog();
        restActionLog.setTime(LocalDateTime.now());
        restActionLog.setAction("Call to Covid19Api endpoint");
        covidApiActionLogRepository.save(restActionLog);;

        return;
    }

    public CovidApiActionLogDto getCovidApiAction(Long id){
        CovidApiActionLog covidApiActionLog = covidApiActionLogRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return covidApiActionLogMapper.mapToDto(covidApiActionLog);
    }

    public List<CovidApiActionLogDto> getCovidApiActions() {
        List<CovidApiActionLog> covidApiActionLogs = covidApiActionLogRepository.findAll();

        return covidApiActionLogMapper.mapToDtos(covidApiActionLogs);
    }
}
