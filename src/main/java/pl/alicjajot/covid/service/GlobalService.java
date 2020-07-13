package pl.alicjajot.covid.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.alicjajot.covid.domain.GlobalCovidStatistics;
import pl.alicjajot.covid.dto.StatisticsDto;
import pl.alicjajot.covid.repository.GlobalCovidStatisticsRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class GlobalService {

    @Autowired
    private GlobalCovidStatisticsRepository globalCovidStatisticsRepository;
    @Autowired
    private StatisticsMapper statisticsMapper;

    public StatisticsDto getLatestData() {
        GlobalCovidStatistics globalCovidStatistics = globalCovidStatisticsRepository.findFirst1ByOrderByDateDesc().orElseThrow(() -> new EntityNotFoundException());

        StatisticsDto statisticsDto = statisticsMapper.mapToDto(globalCovidStatistics);

      return statisticsDto;
    }

    public List<StatisticsDto> getAllData() {
        List<GlobalCovidStatistics> globalCovidStatistics = globalCovidStatisticsRepository.findAll();

        List<StatisticsDto> statisticsDtos = statisticsMapper.mapToDtos(globalCovidStatistics);

        return statisticsDtos;

    }
}
