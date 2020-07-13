package pl.alicjajot.covid.service;

import org.springframework.stereotype.Component;
import pl.alicjajot.covid.domain.log.CovidApiActionLog;
import pl.alicjajot.covid.dto.CovidApiActionLogDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CovidApiActionLogMapper{

    public CovidApiActionLogDto mapToDto(CovidApiActionLog covidApiActionLog) {
        CovidApiActionLogDto covidApiActionLogDto = new CovidApiActionLogDto.CovidApiActionLogDtoBuilder()
                .action(covidApiActionLog.getAction()).time(covidApiActionLog.getTime()).build();
        return covidApiActionLogDto;
    }

    public List<CovidApiActionLogDto> mapToDtos(List<CovidApiActionLog> covidApiActionLogs) {
        return covidApiActionLogs.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
