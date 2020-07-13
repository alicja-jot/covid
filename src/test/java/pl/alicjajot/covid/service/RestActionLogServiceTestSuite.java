package pl.alicjajot.covid.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.alicjajot.covid.domain.log.RestActionLog;
import pl.alicjajot.covid.dto.RestActionLogDto;
import pl.alicjajot.covid.repository.RestActionLogRepository;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Transactional
@SpringBootTest
class RestActionLogServiceTestSuite {

    @Autowired
    private RestActionLogRepository restActionLogRepository;

    @Autowired
    private RestActionLogService restActionLogService;

    @Test
    void testLogRestEndpoint() {
        // when
        restActionLogService.logRestEndpoint("GET /v1/country");

        // then
        assertThat(restActionLogRepository.findAll()).hasSize(1);
    }

    @Test
    void testGetRestAction() {
        // given
        restActionLogService.logRestEndpoint("GET /v1/country");
        Long id = restActionLogRepository.findAll().get(0).getId();

        // when
        RestActionLogDto restActionLogDto = restActionLogService.getRestAction(id);

        // then
        assertThat(restActionLogDto.getAction()).isEqualTo("Call to rest endpoint: GET /v1/country");
        assertThat(restActionLogDto.getTime()).isNotNull();
    }

    @Test
    void testGetRestActions() {
        // given
        restActionLogService.logRestEndpoint("GET /v1/country");

        // when
        List<RestActionLogDto> restActionLogDtos = restActionLogService.getRestActions();

        // then
        assertThat(restActionLogDtos).hasSize(1);
        RestActionLogDto restActionLogDto = restActionLogDtos.get(0);
        assertThat(restActionLogDto.getAction()).isEqualTo("Call to rest endpoint: GET /v1/country");
        assertThat(restActionLogDto.getTime()).isNotNull();
    }
}