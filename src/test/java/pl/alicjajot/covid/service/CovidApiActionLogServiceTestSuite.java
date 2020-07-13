package pl.alicjajot.covid.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.alicjajot.covid.domain.log.CovidApiActionLog;
import pl.alicjajot.covid.dto.CovidApiActionLogDto;
import pl.alicjajot.covid.dto.RestActionLogDto;
import pl.alicjajot.covid.repository.CovidApiActionLogRepository;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Transactional
@SpringBootTest
class CovidApiActionLogServiceTestSuite {

    @Autowired
    private CovidApiActionLogRepository covidApiActionLogRepository;

    @Autowired
    private CovidApiActionLogService covidApiActionLogService;

    @Test
    void testLogCovidApiCall() {
        // when
        covidApiActionLogService.logCovidApiCall();

        // then
        assertThat(covidApiActionLogRepository.findAll()).hasSize(1);
    }

    @Test
    void testGetRestAction() {
        // given
        covidApiActionLogService.logCovidApiCall();
        Long id = covidApiActionLogRepository.findAll().get(0).getId();

        // when
        CovidApiActionLogDto covidApiActionLogDto = covidApiActionLogService.getCovidApiAction(id);

        // then
        assertThat(covidApiActionLogDto.getAction()).isEqualTo("Call to Covid19Api endpoint");
        assertThat(covidApiActionLogDto.getTime()).isNotNull();
    }

    @Test
    void testGetRestActions() {
        // given
        covidApiActionLogService.logCovidApiCall();

        // when
        List<CovidApiActionLogDto> covidApiActionLogDtos = covidApiActionLogService.getCovidApiActions();

        // then
        assertThat(covidApiActionLogDtos).hasSize(1);
        CovidApiActionLogDto restActionLogDto = covidApiActionLogDtos.get(0);
        assertThat(restActionLogDto.getAction()).isEqualTo("Call to Covid19Api endpoint");
        assertThat(restActionLogDto.getTime()).isNotNull();
    }
}