package pl.alicjajot.covid.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
public class StatisticsDto {
    private Long totalConfirmed;
    private Long newConfirmed;
    private Long newDeaths;
    private Long totalDeaths;
    private Long newRecovered;
    private Long totalRecovered;
    private LocalDateTime date;
}
