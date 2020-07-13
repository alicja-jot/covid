package pl.alicjajot.covid.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.alicjajot.covid.domain.log.RestActionLog;
import pl.alicjajot.covid.dto.RestActionLogDto;
import pl.alicjajot.covid.repository.RestActionLogRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RestActionLogService {

    @Autowired

    private RestActionLogRepository restActionLogRepository;

    @Autowired
    private  RestActionLogMapper restActionLogMapper;

    public void logRestEndpoint(String url) {
        RestActionLog restActionLog = new RestActionLog();
        restActionLog.setTime(LocalDateTime.now());
       restActionLog.setAction("Call to rest endpoint: " + url);
        restActionLogRepository.save(restActionLog);
    }

    public RestActionLogDto getRestAction(Long id) {
        RestActionLog restActionLog = restActionLogRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        RestActionLogDto restActionLogDto = restActionLogMapper.mapToDto(restActionLog);

        return restActionLogDto;
    }

    public List<RestActionLogDto> getRestActions(){
        List<RestActionLog> restActionLogs = restActionLogRepository.findAll();

       List<RestActionLogDto> restActionLogDtos = restActionLogMapper.mapToDtos(restActionLogs);

        return restActionLogDtos;
    }
}
