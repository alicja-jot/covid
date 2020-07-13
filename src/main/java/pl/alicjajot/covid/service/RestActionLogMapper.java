package pl.alicjajot.covid.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.alicjajot.covid.domain.log.RestActionLog;
import pl.alicjajot.covid.dto.RestActionLogDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestActionLogMapper {

    public RestActionLogDto mapToDto(RestActionLog restActionLog) {
        RestActionLogDto restActionLogDto = new RestActionLogDto();
        restActionLogDto.setAction(restActionLog.getAction());
        restActionLogDto.setTime(restActionLog.getTime());
        return restActionLogDto;
    }

    public List<RestActionLogDto> mapToDtos(List<RestActionLog> restActionLogs) {
        return restActionLogs.stream().map(restActionLog -> mapToDto(restActionLog)).collect(Collectors.toList());
    }
}
